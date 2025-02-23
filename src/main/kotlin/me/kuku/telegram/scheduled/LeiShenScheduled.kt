package me.kuku.telegram.scheduled

import com.pengrad.telegrambot.TelegramBot
import me.kuku.telegram.entity.LeiShenService
import me.kuku.telegram.entity.Status
import me.kuku.telegram.logic.LeiShenLogic
import me.kuku.telegram.utils.sendTextMessage
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class LeiShenScheduled(
    private val leiShenService: LeiShenService,
    private val telegramBot: TelegramBot
) {


    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    suspend fun leiShenRemind() {
        val entities = leiShenService.findByStatus(Status.ON)
        for (entity in entities) {
            val expiryTime = entity.expiryTime
            try {
                if (System.currentTimeMillis() > expiryTime) {
                    val newEntity = LeiShenLogic.login(entity.username, entity.password)
                    entity.accountToken = newEntity.accountToken
                    entity.nnToken = newEntity.nnToken
                    entity.expiryTime = newEntity.expiryTime
                    leiShenService.save(entity)
                }
            } catch (e: Exception) {
                telegramBot.sendTextMessage(entity.tgId, """
                    #雷神加速器登录失败提醒
                    您的雷神加速器cookie已失效，重新登录失败，原因：${e.message}
                """.trimIndent())
                continue
            }
            val userInfo = try {
                LeiShenLogic.userInfo(entity)
            } catch (e: Exception) {
                entity.expiryTime = 0
                leiShenService.save(entity)
                continue
            }
            if (userInfo.pauseStatusId == 0) {
                telegramBot.sendTextMessage(entity.tgId, """
                    #雷神加速器未暂停时间提醒
                    您的雷神加速器未暂停时间，如果您未在玩游戏，请尽快暂停
                """.trimIndent())
            }
        }
    }



}

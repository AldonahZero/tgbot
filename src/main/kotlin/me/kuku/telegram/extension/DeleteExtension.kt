package me.kuku.telegram.extension

import com.pengrad.telegrambot.model.request.InlineKeyboardButton
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup
import me.kuku.telegram.entity.*
import me.kuku.telegram.utils.*
import org.springframework.stereotype.Component

@Component
class DeleteExtension(
    private val baiduService: BaiduService,
    private val biliBiliService: BiliBiliService,
    private val douYuService: DouYuService,
    private val hostLocService: HostLocService,
    private val huYaService: HuYaService,
    private val kuGouService: KuGouService,
    private val miHoYoService: MiHoYoService,
    private val netEaseService: NetEaseService,
    private val stepService: StepService,
    private val weiboService: WeiboService,
    private val douYinService: DouYinService,
    private val twitterService: TwitterService,
    private val pixivService: PixivService,
    private val buffService: BuffService,
    private val smZdmService: SmZdmService,
    private val aliDriverService: AliDriverService,
    private val leiShenService: LeiShenService
) {

    private fun markup(): InlineKeyboardMarkup {
        val baiduButton = InlineKeyboardButton("百度").callbackData("baiduDelete")
        val biliBiliButton = InlineKeyboardButton("哔哩哔哩").callbackData("biliBiliDelete")
        val douYuButton = InlineKeyboardButton("斗鱼").callbackData("douYuDelete")
        val hostLocButton = InlineKeyboardButton("HostLoc").callbackData("hostLocDelete")
        val huYaButton = InlineKeyboardButton("虎牙").callbackData("huYaDelete")
        val kuGouButton = InlineKeyboardButton("酷狗").callbackData("kuGouDelete")
        val miHoYoButton = InlineKeyboardButton("米忽悠").callbackData("miHoYoDelete")
        val netEaseButton = InlineKeyboardButton("网易云音乐").callbackData("netEaseDelete")
        val stepButton = InlineKeyboardButton("修改步数").callbackData("stepDelete")
        val weiboStepButton = InlineKeyboardButton("微博").callbackData("weiboDelete")
        val douYinButton = InlineKeyboardButton("抖音").callbackData("douYinDelete")
        val twitterButton = InlineKeyboardButton("twitter").callbackData("twitterDelete")
        val pixivButton = InlineKeyboardButton("pixiv").callbackData("pixivDelete")
        val buffButton = InlineKeyboardButton("网易buff").callbackData("buffDelete")
        val smZdmButton = inlineKeyboardButton("什么值得买", "smZdmDelete")
        val aliDriverButton = inlineKeyboardButton("阿里云盘", "aliDriveDelete")
        val leiShenDelete = inlineKeyboardButton("雷神加速器", "leiShenDelete")
        return InlineKeyboardMarkup(
            arrayOf(baiduButton, biliBiliButton),
            arrayOf(douYuButton, hostLocButton),
            arrayOf(huYaButton, kuGouButton),
            arrayOf(miHoYoButton, netEaseButton),
            arrayOf(stepButton, weiboStepButton),
            arrayOf(douYinButton, twitterButton),
            arrayOf(pixivButton, buffButton),
            arrayOf(smZdmButton, aliDriverButton),
            arrayOf(leiShenDelete)
        )
    }

    fun AbilitySubscriber.delete() {
        sub("delete") {
            sendMessage("请点击按钮，以删除对应账号", markup())
        }
    }

    fun TelegramSubscribe.deleteOperate() {
        callback("baiduDelete") {
            baiduService.deleteByTgId(tgId)
            editMessageText("删除百度成功")
        }
        callback("biliBiliDelete") {
            biliBiliService.deleteByTgId(tgId)
            editMessageText("删除哔哩哔哩成功")
        }
        callback("douYuDelete") {
            douYuService.deleteByTgId(tgId)
            editMessageText("删除斗鱼成功")
        }
        callback("hostLocDelete") {
            hostLocService.deleteByTgId(tgId)
            editMessageText("删除HostLoc成功")
        }
        callback("huYaDelete") {
            huYaService.deleteByTgId(tgId)
            editMessageText("删除虎牙成功")
        }
        callback("kuGouDelete") {
            kuGouService.deleteByTgId(tgId)
            editMessageText("删除酷狗成功")
        }
        callback("miHoYoDelete") {
            miHoYoService.deleteByTgId(tgId)
            editMessageText("删除米哈游成功")
        }
        callback("netEaseDelete") {
            netEaseService.deleteByTgId(tgId)
            editMessageText("删除网易云音乐成功")
        }
        callback("stepDelete") {
            stepService.deleteByTgId(tgId)
            editMessageText("删除修改步数成功")
        }
        callback("weiboDelete") {
            weiboService.deleteByTgId(tgId)
            editMessageText("删除微博成功")
        }
        callback("douYinDelete") {
            douYinService.deleteByTgId(tgId)
            editMessageText("删除抖音成功")
        }
        callback("twitterDelete") {
            twitterService.deleteByTgId(tgId)
            editMessageText("删除Twitter成功")
        }
        callback("pixivDelete") {
            pixivService.deleteByTgId(tgId)
            editMessageText("删除Pixiv成功")
        }
        callback("buffDelete") {
            buffService.deleteByTgId(tgId)
            editMessageText("删除网易Buff成功")
        }
        callback("smZdmDelete") {
            smZdmService.deleteByTgId(tgId)
            editMessageText("删除什么值得买成功")
        }
        callback("aliDriveDelete") {
            aliDriverService.deleteByTgId(tgId)
            editMessageText("删除阿里云盘成功")
        }
        callback("leiShenDelete") {
            leiShenService.deleteByTgId(tgId)
            editMessageText("删除雷神加速器成功")
        }
    }


}

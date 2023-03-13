package com.qlive.uikit

import android.content.Context
import android.util.AttributeSet
import com.qlive.rtclive.QInnerVideoFrameHook
import com.qlive.uikit.RoomListActivity.Companion.start
import com.qlive.sdk.QLiveUIKit
import com.qlive.sdk.QPage
import com.qlive.uikit.component.*
import com.qlive.uikitcore.*
import com.qlive.uikitdanmaku.DanmakuTrackManagerView
import com.qlive.uikitdanmaku.SendDanmakuView
import com.qlive.uikitlike.LikeView
import com.qlive.uikitlinkmic.FuncCPTBeInvitedLinkMicMonitor
import com.qlive.uikitlinkmic.FuncCPTLinkMicApplyMonitor
import com.qlive.uikitlinkmic.MicLinkersView
import com.qlive.uikitlinkmic.StartLinkView
import com.qlive.uikitpk.*
import com.qlive.uikitpublicchat.PublicChatView
import com.qlive.uikitpublicchat.RoomNoticeView
import com.qlive.uikitshopping.ExplainingQItemCardView
import com.qlive.uikitshopping.GoShoppingImgView
import com.qlive.uikitshopping.WatchExplainingPage
import com.qlive.uikitshopping.palyer.*
import com.qlive.uikituser.*

/**
 * ui库实现类 反射创建勿动
 * @property appContext
 * @constructor Create empty Q live u i kit impl
 */
class QLiveUIKitImpl(private val appContext: Context) : QLiveUIKit {

    companion object {
        private val mRoomListPage = RoomListPage()
        private val mRoomPage = RoomPage()
        private val mWatchExplainingPage = WatchExplainingPage()
        private val mLiveRecordPage = LiveRecordPage()
    }

    init {
        UIJsonConfigurator.init(appContext)
        QInnerVideoFrameHook.checkHasHooker()
        if (QInnerVideoFrameHook.isEnable) {
            //如果依赖的内置美颜 初始化美颜插件
            QInnerVideoFrameHook.mBeautyHooker?.init(appContext)
        }
        KITInflaterFactory.checkCreateView = { name: String,
                                               context: Context,
                                               attrs: AttributeSet
            ->
            when (name) {
                QBackNavigationImg::class.java.canonicalName -> QBackNavigationImg(context, attrs)
                RoomListView::class.java.canonicalName -> RoomListView(context, attrs)
                CreateRoomButton::class.java.canonicalName -> CreateRoomButton(context, attrs)
                else -> null
            }
        }

        UIJsonConfigurator.uiJsonConfigNameMap.apply {
            put(StartPKView::class.java.canonicalName!!, UIJsonConfigurator.key_relay)
            put(StartLinkView::class.java.canonicalName!!, UIJsonConfigurator.key_mic)
            put(GoShoppingImgView::class.java.canonicalName!!, UIJsonConfigurator.key_item)
            put(SendDanmakuView::class.java.canonicalName!!, UIJsonConfigurator.key_bulletScreen)
            put(LikeView::class.java.canonicalName!!, UIJsonConfigurator.key_like)
            put(RoomNoticeView::class.java.canonicalName!!, UIJsonConfigurator.key_announcement)
        }

        KITLiveInflaterFactory.checkCreateView = { name: String,
                                                   context: Context,
                                                   attrs: AttributeSet
            ->
            when (name) {
                FuncCPTPKApplyMonitor::class.java.canonicalName -> FuncCPTPKApplyMonitor(context)
                FuncCPTLinkMicApplyMonitor::class.java.canonicalName -> FuncCPTLinkMicApplyMonitor(
                    context
                )
                FuncCPTBeInvitedLinkMicMonitor::class.java.canonicalName -> FuncCPTBeInvitedLinkMicMonitor(
                    context
                )
                FuncCPTRoomStatusMonitor::class.java.canonicalName -> FuncCPTRoomStatusMonitor(
                    context
                )
                FuncCPTAnchorStatusMonitor::class.java.canonicalName -> FuncCPTAnchorStatusMonitor(
                    context
                )
                FuncCPTDefaultKeyDownMonitor::class.java.canonicalName -> FuncCPTDefaultKeyDownMonitor(
                    context
                )
                AnchorOfflineTipView::class.java.canonicalName -> AnchorOfflineTipView(
                    context,
                    attrs
                )
                FuncCPTRoomWarnMonitor::class.java.canonicalName -> FuncCPTRoomWarnMonitor(
                    context,
                    attrs
                )

                QKitImageView::class.java.canonicalName -> QKitImageView(context, attrs)
                PKAnchorPreview::class.java.canonicalName -> PKAnchorPreview(context, attrs)
                MicLinkersView::class.java.canonicalName -> MicLinkersView(context, attrs)
                LivePreView::class.java.canonicalName -> LivePreView(context, attrs)
                QBackRoomNavigationImg::class.java.canonicalName -> QBackRoomNavigationImg(
                    context,
                    attrs
                )
                ShowBeautyPreview::class.java.canonicalName -> ShowBeautyPreview(context, attrs)
                SwitchCameraView::class.java.canonicalName -> SwitchCameraView(context, attrs)
                TouchEventBusViewPager::class.java.canonicalName -> TouchEventBusViewPager(
                    context,
                    attrs
                )
                RoomHostView::class.java.canonicalName -> RoomHostView(context, attrs)
                OnlineUserView::class.java.canonicalName -> OnlineUserView(context, attrs)
                RoomMemberCountView::class.java.canonicalName -> RoomMemberCountView(context, attrs)
                RoomIdView::class.java.canonicalName -> RoomIdView(context, attrs)
                RoomTimerView::class.java.canonicalName -> RoomTimerView(context, attrs)
                SendDanmakuView::class.java.canonicalName -> SendDanmakuView(context, attrs)
                GoShoppingImgView::class.java.canonicalName -> GoShoppingImgView(context, attrs)
                ShowBeautyView::class.java.canonicalName -> ShowBeautyView(context, attrs)
                ShowStickerBeautyView::class.java.canonicalName -> ShowStickerBeautyView(
                    context,
                    attrs
                )
                ExplainingQItemCardView::class.java.canonicalName -> ExplainingQItemCardView(
                    context,
                    attrs
                )
                RoomNoticeView::class.java.canonicalName -> RoomNoticeView(context, attrs)
                PublicChatView::class.java.canonicalName -> PublicChatView(context, attrs)
                PKCoverView::class.java.canonicalName -> PKCoverView(context, attrs)
                DanmakuTrackManagerView::class.java.canonicalName -> DanmakuTrackManagerView(
                    context,
                    attrs
                )
                CloseRoomView::class.java.canonicalName -> CloseRoomView(context, attrs)
                StartLinkView::class.java.canonicalName -> StartLinkView(context, attrs)
                PKPlayerPreview::class.java.canonicalName -> PKPlayerPreview(context, attrs)
                BottomMoreFuncButton::class.java.canonicalName -> BottomMoreFuncButton(
                    context,
                    attrs
                )
                LiveStatisticsView::class.java.canonicalName -> LiveStatisticsView(context, attrs)
                AnchorStartTrailerLiveView::class.java.canonicalName -> AnchorStartTrailerLiveView(
                    context,
                    attrs
                )
                else -> null
            }
        }
        KITRoomDependsInflaterFactory.checkCreateView = { name: String,
                                                          context: Context,
                                                          attrs: AttributeSet
            ->
            when (name) {
                RoomDependsHostView::class.java.canonicalName -> RoomDependsHostView(context, attrs)
                RoomDependsIdView::class.java.canonicalName -> RoomDependsIdView(context, attrs)
                RoomDependsMemberCountView::class.java.canonicalName -> RoomDependsMemberCountView(
                    context,
                    attrs
                )
                RoomDependsOnlineUserView::class.java.canonicalName -> RoomDependsOnlineUserView(
                    context,
                    attrs
                )
                ShoppingPlayerControl::class.java.canonicalName -> ShoppingPlayerControl(
                    context,
                    attrs
                )
                ShoppingPlayerGoShoppingView::class.java.canonicalName -> ShoppingPlayerGoShoppingView(
                    context,
                    attrs
                )
                ShoppingPlayerNavigationView::class.java.canonicalName -> ShoppingPlayerNavigationView(
                    context,
                    attrs
                )
                WatchExplainingCardView::class.java.canonicalName -> WatchExplainingCardView(
                    context,
                    attrs
                )
                else -> null
            }
        }
    }

    override fun <T : QPage> getPage(pageClass: Class<T>): T? {
        val page: T? = when (pageClass.simpleName) {
            RoomListPage::class.simpleName -> mRoomListPage as T?
            RoomPage::class.simpleName -> mRoomPage as T?
            WatchExplainingPage::class.simpleName -> mWatchExplainingPage as T?
            LiveRecordPage::class.java.simpleName -> mLiveRecordPage as T?
            else -> null
        }
        return page
    }

    override fun launch(context: Context) {
        start(context)
    }
}
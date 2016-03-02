package com.letv.cases.leui.ContentDesktop;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class ContentDesktop extends LetvTestCase {
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		
	}
	public void openWifi() throws UiObjectNotFoundException {
			launchApp(AppName.SETTING);
			sleepInt(4);
			LeUiObject wlan =new LeUiObject(new UiSelector().className(
					"android.widget.LinearLayout").index(2).
					childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
							.childSelector(new UiSelector().className("android.widget.TextView").index(0))));
			verify("Can't find Wi-Fi button.", wlan.exists());
			wlan.click();
			sleepInt(2);
			LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
					"com.letv.leui.widget.LeSwitch").resourceId(
					"com.android.settings:id/switch_widget"));
			verify("Can't find switchWidget button.", switchWidget.exists());
			if (switchWidget.isChecked()) {
				return;
			} else {
				switchWidget.click();
				sleepInt(10);
				verify("Can't open wifi.",
						switchWidget.isChecked());
			}
	}

	@CaseName("启动内容桌面，退出内容桌面")
	public void testOpenContenDesktop() throws UiObjectNotFoundException {
		openWifi();
		addStep("step1:进入内容桌面");
		launchAppByPackage(IntentConstants.content);
		sleepInt(2);
		LeUiObject myNews = new LeUiObject(new UiSelector().text("我的资讯").className("android.widget.TextView"));
		verify("没有成功进入内容桌面",myNews.exists());
		addStep("step2:home键后台，再次进入内容桌面");
		sleepInt(2);
		verify("没有成功进入内容桌面",myNews.exists());
		/*addStep("step3:home键后台调用多任务，多任务中进入内容桌面");
		press_home(1);
		sleepInt(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));*/
		press_menu(1);
		sleepInt(3);
		LeUiObject clear1 = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").textContains("可用"))));
		LeUiObject clear2 = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").textContains("立即清理"))));
		verify("清除不存在",clear1.exists()||clear2.exists());
		if(!clear1.exists()){
			clear2.click();
			sleepInt(3);
			press_menu(1);
		}
		sleepInt(3);
		LeUiObject recent = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		LeUiObject recent2 = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		verify("最近不存在",recent.exists()||recent2.exists());
		if(recent.exists()){
			recent.click();
		}else{
			recent2.click();
		}
		sleepInt(3);
		verify("没有成功进入天气",myNews.exists());
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	
	@CaseName("视频直接播放")
	public void testPlayVideo() throws UiObjectNotFoundException {
		openWifi();
		addStep("step1:进入内容桌面");
		launchAppByPackage(IntentConstants.content);
		sleepInt(5);
		LeUiObject live = new LeUiObject(new UiSelector().text("直播").className("android.widget.TextView"));
		verify("没有成功进入内容桌面",live.exists());
		live.click();
		addStep("进入直播播放直播");
		sleepInt(8);
		LeUiObject livePlay = new LeUiObject(new UiSelector().resourceId("com.letv.android.letvstar:id/live_play").className("android.widget.ImageView"));
		verify("直播按钮不存在",live.exists());
		livePlay.click();
		LeUiObject playtag = new LeUiObject(new UiSelector().resourceId("com.letv.android.letvstar:id/id_vide_view_layout").className("android.widget.RelativeLayout"));
		for (int i = 0; i < 60; i++) {
			if(playtag.exists()){
				break;
			}
		}
		
		verify("视频未能播放成功",playtag.exists());
		sleepInt(30);
		LeUiObject news = new LeUiObject(new UiSelector().text("我的资讯").className("android.widget.TextView"));
		verify("没有成功进入内容桌面",news.exists());
		news.click();
		sleepInt(2);
	}
	
	
	@CaseName("视频轮播播放")
	public void testPlayInOrder() throws UiObjectNotFoundException {
		openWifi();
		addStep("step1:进入内容桌面");
		launchAppByPackage(IntentConstants.content);
		sleepInt(2);
		LeUiObject live = new LeUiObject(new UiSelector().text("直播").className("android.widget.TextView"));
		verify("没有成功进入内容桌面",live.exists());
		live.click();
		addStep("进入直播播放直播");
		sleepInt(8);
		LeUiObject lunbo = new LeUiObject(new UiSelector().className("android.widget.TextView").text("轮播台"));
		verify("轮播按钮不存在",lunbo.exists());
		lunbo.click();
		sleepInt(3);
		LeUiObject movie = new LeUiObject(new UiSelector().className("android.widget.TextView").text("电影"));
		verify("轮播按钮不存在",movie.exists());
		movie.click();
		LeUiObject playtag = new LeUiObject(new UiSelector().resourceId("com.letv.android.letvstar:id/play_gestrue"));
		for (int i = 0; i < 60; i++) {
			if(playtag.exists()){
				break;
			}
		}
		
		verify("视频未能播放成功",playtag.exists());
		sleepInt(30);
		LeUiObject news = new LeUiObject(new UiSelector().text("我的资讯").className("android.widget.TextView"));
		verify("没有成功进入内容桌面",news.exists());
		news.click();
		sleepInt(2);
	}
}

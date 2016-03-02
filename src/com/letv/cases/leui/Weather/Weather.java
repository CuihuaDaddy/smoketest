package com.letv.cases.leui.Weather;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Weather extends LetvTestCase {
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
	private void sendMsg() throws UiObjectNotFoundException {
		
		LeUiObject newMsgBtn1 = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("New message"));
		LeUiObject newMsgBtn2 = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("新信息"));
		verify("未能跳到信息界面",newMsgBtn1.exists()||newMsgBtn2.exists());
		sleepInt(1);
		LeUiObject receiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.view.View").resourceId("com.android.mms:id/recipients_editor"));
		verify("填写收件人的地方不存在",receiverEdit.exists());
		receiverEdit.setText(getStrParams("dialNum"));
		sleepInt(2);
		LeUiObject sim1card = new LeUiObject(new UiSelector()
		.resourceId("com.android.mms:id/sub_sim1_selected_layout"));
		verify("sim1卡不存在",sim1card.exists());
		sim1card.click();
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.EditText").resourceId("com.android.mms:id/embedded_text_editor"));
		verify("编辑短信内容的地方不存在",contentEdit.exists());
		contentEdit.setText(getStrParams("smsContent"));
		sleepInt(2);
		addStep("发送新信息");
		LeUiObject sendBtn = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"com.android.mms:id/send_button_mms"));
		verify("发送信息的button不存在",sendBtn.exists());
		sendBtn.click();
		sleepInt(10);
		LeUiObject date_view = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
						"com.android.mms:id/date_view"));
		verify("未能找到发送信息时间", date_view.waitForExists(120));
		
	}
	@CaseName("启动天气应用")
	public void testOpenWeather() throws UiObjectNotFoundException {
		addStep("step1:启动天气");
		launchApp(AppName.WEATHER);
		sleepInt(2);
		
		sleepInt(12);
		LeUiObject failure = new LeUiObject(new UiSelector().textContains("无法定位"));
		if(failure.exists()){
			screenShot();
			fail("定位失败");
		}
		
		LeUiObject yes = new LeUiObject(new UiSelector().resourceId("sina.mobile.tianqitongletv:id/alert_confirm").textContains("确定"));
		if(yes.exists()){
			yes.click();
			sleepInt(5);
		}

		
		LeUiObject swipeUp = new LeUiObject(new UiSelector().resourceId("sina.mobile.tianqitongletv:id/homepage_tip_up_fling"));
		if(swipeUp.exists()){
			swipeUp.click();
			sleepInt(2);
		}
		
		LeUiObject enterCity = new LeUiObject(new UiSelector().packageName("sina.mobile.tianqitongletv"));
		verify("没有成功进入天气",enterCity.exists());
		sleepInt(2);
		addStep("step2:home键后台，再次点击天气");
		press_home(1);
		sleepInt(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.WEATHER);
		sleepInt(2);
		verify("没有成功进入天气",enterCity.exists());
		addStep("step3:home键后台调用多任务，多任务中点击天气");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		sleepInt(1);
		press_menu(1);
		sleepInt(2);
		LeUiObject clear = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").textContains("立即清理"))));
		if(clear.exists()){
			clear.click();
			sleepInt(3);
			press_home(1);
		}
		sleepInt(2);
		launchApp(AppName.WEATHER);
		sleepInt(5);
		verify("没有成功进入天气",enterCity.exists());
		addStep("step3:home键后台调用多任务，多任务中点击天气");
		press_home(1);
		sleepInt(2);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		press_menu(1);
		sleepInt(2);
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
		verify("没有成功进入天气",enterCity.waitForExists(20));
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	@CaseName("添加城市")
	public void testAddCity() throws UiObjectNotFoundException {
		addStep("step1:启动天气");
		launchApp(AppName.WEATHER);
		sleepInt(12);
		LeUiObject failure = new LeUiObject(new UiSelector().textContains("无法定位"));
		if(failure.exists()){
			screenShot();
			fail("定位失败");
		}
		
		LeUiObject yes = new LeUiObject(new UiSelector().resourceId("sina.mobile.tianqitongletv:id/alert_confirm").textContains("确定"));
		if(yes.exists()){
			yes.click();
			sleepInt(5);
		}

		
		LeUiObject swipeUp = new LeUiObject(new UiSelector().resourceId("sina.mobile.tianqitongletv:id/homepage_tip_up_fling"));
		if(swipeUp.exists()){
			swipeUp.click();
			sleepInt(2);
		}
		LeUiObject addCity = new LeUiObject(new UiSelector().resourceId("sina.mobile.tianqitongletv:id/city_setting_icon"));
		verify("未能找到添加天气的按钮",addCity.exists());
		addCity.click();
		sleepInt(2);
		LeUiObject add = new LeUiObject(new UiSelector().resourceId("sina.mobile.tianqitongletv:id/item_2")
				.childSelector(new UiSelector().className("android.widget.ImageView")));
		verify("未能找到添加天气的按钮",add.exists());
		add.click();
		sleepInt(3);
		LeUiObject beijing = new LeUiObject(new UiSelector().text("北京"));
		verify("没有北京这个城市",beijing.exists());
		sleepInt(2);
		beijing.click();
		sleepInt(5);
		verify("添加不成功",beijing.exists());
		addStep("退出应用");
		press_back(3);
		press_home(1);
	}
	
	@CaseName("分享天气通过短信")
	public void testShareWeather() throws UiObjectNotFoundException {
		addStep("step1:启动天气");
		launchApp(AppName.WEATHER);
		sleepInt(12);
		LeUiObject failure = new LeUiObject(new UiSelector().textContains("无法定位"));
		if(failure.exists()){
			screenShot();
			fail("定位失败");
		}
		
		LeUiObject yes = new LeUiObject(new UiSelector().resourceId("sina.mobile.tianqitongletv:id/alert_confirm").textContains("确定"));
		if(yes.exists()){
			yes.click();
			sleepInt(5);
		}

		
		LeUiObject swipeUp = new LeUiObject(new UiSelector().resourceId("sina.mobile.tianqitongletv:id/homepage_tip_up_fling"));
		if(swipeUp.exists()){
			swipeUp.click();
			sleepInt(2);
		}
		
		LeUiObject shareBtn = new LeUiObject(new UiSelector().resourceId("sina.mobile.tianqitongletv:id/share"));
		verify("没有找到分享键",shareBtn.exists());
		shareBtn.click();
		sleepInt(3);
		LeUiObject shareWithSMS = new LeUiObject(new UiSelector().resourceId("android:id/share_title").text("信息"));
		verify("没有找到通过短信分享按钮",shareWithSMS.exists());
		shareWithSMS.click();
		sleepInt(3);
		sendMsg();
		addStep("退出应用");
		press_back(5);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

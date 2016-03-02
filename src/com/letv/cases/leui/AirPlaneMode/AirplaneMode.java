package com.letv.cases.leui.AirPlaneMode;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class AirplaneMode extends LetvTestCase {
	 static int LOOP = 3;
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		closeAPM();
	}
	
	

	@CaseName("1.打开设置>飞行模式2.多次打开/关闭飞行模式")
	public void testAPMConnection() throws UiObjectNotFoundException {

		for (int i = 0;i<LOOP;i++){
			addStep("Open APM");
			openAPM();
			sleepInt(5);
			press_back(2);
			addStep("Close APM");
			closeAPM();
			sleepInt(5);
			press_back(2);
		}
		addStep("退出应用程序");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	@CaseName("飞行模式打电话")
//	1.打开飞行模式（设置>飞行模式）
//	2.打电话给一有效号码
	public void testAPMCall() throws UiObjectNotFoundException {
	
		addStep("step1:打开飞行模式（设置>飞行模式）");
		openAPM();
		sleepInt(5);
		press_back(2);
		addStep("step2:打电话给一有效号码(10086/10010)");
		launchApp(AppName.PHONE);
		LeUiObject dialApp = new LeUiObject(new UiSelector().className("android.widget.TabWidget")
				.index(1).childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
						.childSelector(new UiSelector().className("android.widget.TextView").resourceId("android:id/title"))));
		verify("拨号界面不存在",dialApp.exists());
		dialApp.click();
		sleepInt(2);
		LeUiObject deleteBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId("com.android.dialer:id/deleteButton"));
		if(deleteBtn.exists()){
			deleteBtn.longClick();
			sleepInt(2);
		}
		addStep("拨打号码" + getStrParams("dialNum"));
		pressDialPad(getStrParams("dialNum"));
		sleepInt(3);
		addStep("拨出电话");
		LeUiObject sim1card = new LeUiObject(new UiSelector().className(
		"android.widget.FrameLayout").resourceId("com.android.dialer:id/show_call_1"));
		verify("SIM1卡不存在",sim1card.exists());
		sim1card.click();
		sleepInt(6);
		
		LeUiObject planePrompt = new LeUiObject(new UiSelector().textContains("飞行模式"));
		verify("未能弹出飞行模式已启动的弹窗", planePrompt.exists());
		sleepInt(2);
		press_back(3);
		addStep("step4:关闭AMP");
		closeAPM();
		sleepInt(5);
		addStep("退出应用程序");
		press_back(4);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	@CaseName("飞行模式浏览网页")
//	1.打开飞行模式（设置>飞行模式）
//	2.打开浏览器，浏览网页
	public void testAPMBrowser() throws UiObjectNotFoundException {
		addStep("step1:打开飞行模式（设置>飞行模式）");
		openAPM();
		sleepInt(5);
		press_back(2);
		addStep("step2:打开浏览器，浏览网页");
		launchApp(AppName.BROWSER);
		sleepInt(2);
		LeUiObject notFound = new LeUiObject(new UiSelector().textContains("不可用"));
		if(notFound.exists()){
			return;
		}
		LeUiObject baidu = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("新浪"));
		verify("未能发现乐视网",baidu.exists());
		baidu.click();
		sleepInt(5);
		
		verify("未能发现找不到网页字段",notFound.exists());
		press_back(3);
		closeAPM();
	}
	
	@CaseName("飞行模式与WIFI")
	public void testAPMWIFI () throws UiObjectNotFoundException {
		addStep("step1:打开飞行模式（设置>飞行模式）");
		openAPM();
		sleepInt(5);
		connectWifi();
		closeWifi();
	}
}

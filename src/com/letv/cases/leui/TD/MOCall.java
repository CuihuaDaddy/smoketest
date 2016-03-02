package com.letv.cases.leui.TD;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class MOCall extends LetvTestCase {

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		
		super.tearDown();
		switch4G();
	}
	@CaseName("SIM1: MO call ")
	public void testMOCallSim1() throws UiObjectNotFoundException {
		connectData();
		switch3G();
		addStep("step1:SIM1切换到3G网络下");
		SIM1dataUse();
		addStep("step2:使用SIM1打电话");
		launchApp(AppName.PHONE);
		sleepInt(3);
		LeUiObject dialApp = new LeUiObject(new UiSelector().className("android.widget.TabWidget")
				.index(1).childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
						.childSelector(new UiSelector().className("android.widget.TextView").resourceId("android:id/title"))));
		verify("拨号界面不存在",dialApp.exists());
		dialApp.clickAndWaitForNewWindow();
		sleepInt(2);
		LeUiObject deleteBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId("com.android.dialer:id/deleteButton"));
		if(deleteBtn.exists()){
			deleteBtn.longClick();			
		}
		sleepInt(3);
		addStep("拨打号码" + getStrParams("dialNum"));
		pressDialPad(getStrParams("dialNum"));
		addStep("拨出电话");
		LeUiObject sim1card = new LeUiObject(new UiSelector().className(
		"android.widget.LinearLayout").resourceId("com.android.dialer:id/show_call_1"));
		verify("sim1卡不存在",sim1card.exists());
		sim1card.clickAndWaitForNewWindow();
		sleepInt(20);
//		LeUiObject dialBtn = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/floating_view").className("android.widget.ImageView"));
//		dialBtn.click();
//		LeUiObject diallingPrompt = new LeUiObject(new UiSelector().className(
//				"android.widget.TextView").text("Dialing"));
//		verify("未能成功拨出号码", diallingPrompt.waitForExists(5000));
//		addStep("验证成功拨号");
		LeUiObject endBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.dialer:id/end_call_button"));
//		verify("未能成功接通", diallingPrompt.waitUntilGone(10000) && endBtn.exists());
		verify("未能成功接通", endBtn.exists());
		sleepInt(5);
		addStep("step3:挂断电话");
		endBtn.click();
		sleepInt(2);
		press_back(3);
	}
	@CaseName("SIM2: MO call ")
	public void testMOCallSim2() throws UiObjectNotFoundException {
		connectData();
		switch3G();
		addStep("step1:SIM2切换到3G网络下");
		SIM2dataUse();
		addStep("step2:使用SIM2打电话");
		launchApp(AppName.PHONE);
		LeUiObject dialApp = new LeUiObject(new UiSelector().className("android.widget.TabWidget")
				.index(1).childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
						.childSelector(new UiSelector().className("android.widget.TextView").resourceId("android:id/title"))));
		verify("拨号界面不存在",dialApp.exists());
		dialApp.clickAndWaitForNewWindow();
		sleepInt(2);
		LeUiObject deleteBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId("com.android.dialer:id/deleteButton"));
		if(deleteBtn.exists()){
			deleteBtn.longClick();			
		}
		sleepInt(3);
		addStep("拨打号码" + getStrParams("dialNum"));
		pressDialPad(getStrParams("dialNum"));
		addStep("拨出电话");
		LeUiObject sim2card = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").resourceId("com.android.dialer:id/show_call_2"));
		verify("sim2卡不存在",sim2card.exists());
		sim2card.clickAndWaitForNewWindow();
		sleepInt(20);
//		LeUiObject dialBtn = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/floating_view").className("android.widget.ImageView"));
//		dialBtn.click();
//		LeUiObject diallingPrompt = new LeUiObject(new UiSelector().className(
//				"android.widget.TextView").text("Dialing"));
//		verify("未能成功拨出号码", diallingPrompt.waitForExists(5000));
//		addStep("验证成功拨号");
		LeUiObject endBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.dialer:id/end_call_button"));
//		verify("未能成功接通", diallingPrompt.waitUntilGone(10000) && endBtn.exists());
		verify("未能成功接通", endBtn.exists());
		sleepInt(5);
		addStep("step3:挂断电话");
		endBtn.click();
		sleepInt(2);
		press_back(3);
	}
}

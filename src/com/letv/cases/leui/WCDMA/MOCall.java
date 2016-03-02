package com.letv.cases.leui.WCDMA;

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
		connectData();
		switch3G();
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		switch4G();
		disconnectData();
		super.tearDown();
	}
	@CaseName("SIM1: MO call ")
	public void testMOCallSim1() throws UiObjectNotFoundException {
		addStep("step1:SIM1切换到3G网络下");
		SIM1dataUse();
		addStep("step2:使用SIM1打电话");
		launchApp(AppName.PHONE);
		LeUiObject dialApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Dial"));
		dialApp.clickAndWaitForNewWindow();
		sleepInt(2);
		LeUiObject simcard = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId("com.android.dialer:id/sim"));
		verify(simcard.exists());
		simcard.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject sim1card = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId("com.android.dialer:id/simName").text("CMCC 01"));
		verify(sim1card.exists());
		sim1card.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("拨打号码" + getStrParams("dialNum"));
		pressDialPad(getStrParams("dialNum"));
		addStep("拨出电话");
		LeUiObject dialBtn = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/floating_view").className("android.widget.ImageView"));
		dialBtn.click();
		LeUiObject diallingPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Dialing"));
		verify("未能成功拨出号码", diallingPrompt.waitForExists(5000));
		addStep("验证成功拨号");
		LeUiObject endBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.dialer:id/end_call_button"));
		verify("未能成功接通", diallingPrompt.waitUntilGone(10000) && endBtn.exists());
		sleepInt(5);
		addStep("step3:挂断电话");
		endBtn.click();
		sleepInt(2);
		press_back(3);
	}
	@CaseName("SIM2: MO call ")
	public void testMOCallSim2() throws UiObjectNotFoundException {
		
		addStep("step1:SIM2切换到3G网络下");
		SIM2dataUse();
		addStep("step2:使用SIM2打电话");
		launchApp(AppName.PHONE);
		LeUiObject dialApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Dial"));
		dialApp.clickAndWaitForNewWindow();
		sleepInt(2);
		LeUiObject simcard = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId("com.android.dialer:id/sim"));
		verify(simcard.exists());
		simcard.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject sim2card = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId("com.android.dialer:id/simName").text("CMCC 02"));
		verify(sim2card.exists());
		sim2card.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("拨打号码" + getStrParams("dialNum"));
		pressDialPad(getStrParams("dialNum"));
		addStep("拨出电话");
		LeUiObject dialBtn = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/floating_view").className("android.widget.ImageView"));
		dialBtn.click();
		LeUiObject diallingPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Dialing"));
		verify("未能成功拨出号码", diallingPrompt.waitForExists(5000));
		addStep("验证成功拨号");
		LeUiObject endBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.dialer:id/end_call_button"));
		verify("未能成功接通", diallingPrompt.waitUntilGone(10000) && endBtn.exists());
		sleepInt(5);
		addStep("step3:挂断电话");
		endBtn.click();
		sleepInt(2);
		press_back(3);
	}
}

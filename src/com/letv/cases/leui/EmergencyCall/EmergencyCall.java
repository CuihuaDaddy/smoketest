package com.letv.cases.leui.EmergencyCall;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class EmergencyCall extends LetvTestCase {

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
	
	/**  拨打紧急电话
	 * 1.打开拨号应用
	 * 2.确认当前是否为拨号界面
	 * 3.输入紧急号码112
	 * 4.点击拨号按钮
	 * 5.确认是否正确呼出紧急通话
	 * 6.挂断电话
	 * @throws UiObjectNotFoundException
	 */
	@CaseName("Emergency call")
	public void testEmergencyCall() throws UiObjectNotFoundException {
		addStep("Step1:Launch call application");
		launchApp(AppName.PHONE);
		sleepInt(3);
		LeUiObject dialApp = new LeUiObject(new UiSelector().className("android.widget.TabWidget")
				.index(1).childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
						.childSelector(new UiSelector().className("android.widget.TextView").resourceId("android:id/title"))));
		verify("Enter dial interface successful",dialApp.exists());
		dialApp.clickAndWaitForNewWindow();
		sleepInt(2);
		addStep("Step2:dial 112");
		pressDialPad("112");
		addStep("Step3:dialling");
		LeUiObject sim1card = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").resourceId("com.android.dialer:id/show_call_1"));
		verify("sim1 doesn't existed",sim1card.exists());
		sim1card.clickAndWaitForNewWindow();
		sleepInt(10);
		LeUiObject diallingPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Alerting"));
		LeUiObject diallingPrompt1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("正在"));
		//verify("未能成功拨出号码", diallingPrompt.exists()||diallingPrompt1.exists());
		addStep("Step4:Verify emergency call success");
		LeUiObject endBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.dialer:id/end_call_button"));
		verify("Failed to dial", !diallingPrompt.exists() && endBtn.exists());
		sleepInt(5);
		addStep("step5:end the call");
		verify("Verify end call button exist",endBtn.exists());
		endBtn.click();
		sleepInt(2);
		press_back(3);
	}
}

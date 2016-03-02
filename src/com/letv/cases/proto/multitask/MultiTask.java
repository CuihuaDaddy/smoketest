package com.letv.cases.proto.multitask;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class MultiTask extends LetvTestCase {

	private String url = "www.sina.cn";

	private void callNum(String number) throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.proto_telephone);
		sleepInt(2);
		LeUiObject dialApp = new LeUiObject(new UiSelector().resourceId(
				"com.android.dialer:id/floating_action_button").description(
				"dial pad"));
		if (dialApp.exists()) {
			dialApp.clickAndWaitForNewWindow();
			sleepInt(1);
		}
		for (int i = 0; i < number.length(); i++) {
			LeUiObject numBtn = new LeUiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("com.android.dialer:id/dialpad_key_number")
					.text(String.valueOf(number.charAt(i))));
			numBtn.click();
			sleepInt(1);
		}
		LeUiObject dialBtn = new LeUiObject(new UiSelector().resourceId(
				"com.android.dialer:id/dialpad_floating_action_button")
				.description("dial"));

		dialBtn.click();

		addStep("验证成功拨号");
		LeUiObject endBtn = new LeUiObject(new UiSelector().resourceId(
				"com.android.dialer:id/floating_end_call_action_button")
				.description("End"));
		verify("未能成功接通", endBtn.waitForExists(10000));
	}

	private void browseWebPage() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.proto_browser);
		sleepInt(2);
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url"));
		verify("Can't find url input EditText.", urlEdit.exists());
		urlEdit.click();
		callShell("input text " + url);
		sleepInt(1);
		getUiDevice().pressEnter();
		sleepInt(10);
	}

	@CaseName("电话和浏览器切换")
	public void testPhoneBrowser() throws UiObjectNotFoundException {
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("拨通电话");
			callNum(getStrParams("dialNum"));
			addStep("返回主页面，打开网页");
			browseWebPage();
			addStep("返回电话页面");
			launchAppByPackage(IntentConstants.proto_telephone);
			sleepInt(2);
			addStep("返回浏览器页面");
			launchAppByPackage(IntentConstants.browser);
			sleepInt(2);
			addStep("退出浏览器");
			press_back(5);
			addStep("挂断电话并退出");
			LeUiObject phoneApp = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text("Phone").description("Phone"));
			launchApp("电话");			
			LeUiObject ReturnCall = new LeUiObject(new UiSelector().resourceId(
					"com.android.dialer:id/text").text("Return to call in progress"));
			verify("通话已中断", ReturnCall.exists());
			ReturnCall.click();
			sleepInt(1);
			LeUiObject endBtn = new LeUiObject(new UiSelector().resourceId(
					"com.android.dialer:id/floating_end_call_action_button")
					.description("End"));
			endBtn.click();
			sleepInt(4);
			press_back(5);
		}
	}
}

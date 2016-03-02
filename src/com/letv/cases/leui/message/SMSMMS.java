package com.letv.cases.leui.message;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class SMSMMS extends LetvTestCase {
	private final String RECEIVER = "10086";
	private final String CONTENT = "123";
	private final int LOOP = 20;

	@CaseName("发送短信")
	public void testSendSMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		LeUiObject msgApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("信息"));
		msgApp.clickAndWaitForNewWindow();
		verify("未能打开信息程序", phone.getCurrentPackageName().equals("com.android.mms"));

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		for (int i = 1; i <= LOOP; i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			LeUiObject newMsgBtn = new LeUiObject(new UiSelector()
					.className("android.widget.ImageView").index(0).instance(1));
			newMsgBtn.click();
			sleepInt(1);
			LeUiObject receiverEdit = new LeUiObject(
					new UiSelector()
							.className("android.widget.MultiAutoCompleteTextView"));
			receiverEdit.setText(RECEIVER);
			sleepInt(1);
			LeUiObject contentEdit = new LeUiObject(
					new UiSelector()
							.className("com.letv.leui.widget.LeEditText"));
			contentEdit.setText(CONTENT);
			sleepInt(1);
			addStep("发送新信息");
			LeUiObject sendBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").resourceId(
					"com.android.mms:id/send_button_sms"));
			sendBtn.click();

			LeUiObject sendingMark = new LeUiObject(new UiSelector().className(
					"android.widget.ProgressBar").resourceId(
					"com.android.mms:id/status_sending"));
			LeUiObject errorMark = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text("发送失败"));
			verify("未能发送信息",
					sendingMark.exists() && sendingMark.waitUntilGone(10000));
			sleepInt(1);
			verify("发送失败", !errorMark.exists());

			addStep("删除发送的信息");
			LeUiObject backBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.mms:id/up"));
			backBtn.click();
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	@CaseName("打开短信")
	public void testOpenSMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		LeUiObject msgApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("信息"));
		msgApp.clickAndWaitForNewWindow();
		verify("未能打开信息程序", phone.getCurrentPackageName().equals("com.android.mms"));

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());

		addStep("编写新信息");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector()
				.className("android.widget.ImageView").index(0).instance(1));
		newMsgBtn.click();
		sleepInt(1);
		LeUiObject receiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.MultiAutoCompleteTextView"));
		receiverEdit.setText(RECEIVER);
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector().className("com.letv.leui.widget.LeEditText"));
		contentEdit.setText(CONTENT);
		sleepInt(1);
		verify("输入信息有误", receiverEdit.getText().equals(RECEIVER + ", ")
				&& contentEdit.getText().equals(CONTENT));

		addStep("保存信息");
		LeUiObject backBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId("com.android.mms:id/up"));
		backBtn.click();
		sleepInt(1);
		LeUiObject draft = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text(RECEIVER));
		verify("信息未保存", draft.exists());
		for (int i = 1; i <= LOOP; i++) {
			addStep("打开已保存的信息，第" + String.valueOf(i) + "遍");
			draft.click();
			sleepInt(1);
			verify("保存的信息有误", receiverEdit.getText().equals(RECEIVER + ", ")
					&& contentEdit.getText().equals(CONTENT));
			backBtn.click();
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	@CaseName("发送彩信")
	public void testSendMMS() throws UiObjectNotFoundException {
		
	}
	
	private void deleteExistingMsg() throws UiObjectNotFoundException {
		LeUiObject msgList = new LeUiObject(new UiSelector()
				.className("android.widget.ListView")
				.packageName("com.android.mms").resourceId("android:id/list"));
		if (!msgList.exists())
			return;
		LeUiObject editLabel = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("选择"));
		editLabel.click();
		sleepInt(1);
		LeUiObject selAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("全选"));
		selAll.click();
		sleepInt(1);
		LeUiObject delAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("删除"));
		delAll.click();
		sleepInt(1);
		// workaround for the invisiable popup prompt
		phone.click(770, 1690);
	}
}

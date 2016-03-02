package com.letv.cases.leui.WCDMA;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class SMS extends LetvTestCase {

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
	@CaseName("SIM1: 收发短信 ")
	public void testSMSSim1() throws UiObjectNotFoundException {
		addStep("step1:SIM1切换到3G网络下");
		SIM1dataUse();
		addStep("step2:使用SIM1发短信");
		addStep("打开信息程序");
		launchApp(AppName.MESSAGE);
		sleepInt(2);
		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("No conversations"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		addStep("编写新信息");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").resourceId("com.android.mms:id/action_compose_new"));
		newMsgBtn.click();
		sleepInt(1);
		LeUiObject receiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.view.View").resourceId("com.android.mms:id/recipients_editor"));
		receiverEdit.setText(getStrParams("smsReceiver"));
		sleepInt(1);
		LeUiObject simcard = new LeUiObject(new UiSelector()
			.className("android.widget.ImageButton").resourceId("com.android.mms:id/selected_sim"));
		verify(simcard.exists());
		simcard.click();
		sleepInt(1);
		LeUiObject sim1card = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").resourceId("com.android.mms:id/simName").text("CMCC 01"));
		verify(sim1card.exists());
		sim1card.click();
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.EditText").resourceId("com.android.mms:id/embedded_text_editor"));
		contentEdit.setText(getStrParams("smsContent"));
		sleepInt(1);
		addStep("发送新信息");
		LeUiObject sendBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.mms:id/send_button_sms"));
		sendBtn.click();
		LeUiObject sendingMark = new LeUiObject(new UiSelector()
		.className("android.widget.TextView")
		.resourceId("com.android.mms:id/date_view")
		.textContains("SENDING"));
		verify("未能发送信息", sendingMark.waitUntilGone(20000));
		LeUiObject date_view = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
						"com.android.mms:id/date_view"));
		verify("未能找到发送信息时间", date_view.exists());
		addStep("step3:查看SIM1收到的短信");
		LeUiObject receiveSMS = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").resourceId(
						"com.android.mms:id/msg_list_item_recv").index(1));
		addStep("等待10秒");
		sleepInt(10);
		verify("未收到短信", receiveSMS.exists());
		addStep("删除发送的信息");
		LeUiObject backBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.mms").description("Navigate up"));
		backBtn.click();
		sleepInt(3);
		deleteExistingMsg();
		sleepInt(1);
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	@CaseName("SIM2: 收发短信  ")
	public void testSMSSim2() throws UiObjectNotFoundException {
		addStep("step1:SIM2切换到3G网络下");
		SIM2dataUse();
		addStep("step2:使用SIM2发短信");
		addStep("打开信息程序");
		launchApp(AppName.MESSAGE);
		sleepInt(2);
		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("No conversations"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		addStep("编写新信息");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").resourceId("com.android.mms:id/action_compose_new"));
		newMsgBtn.click();
		sleepInt(1);
		LeUiObject receiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.view.View").resourceId("com.android.mms:id/recipients_editor"));
		receiverEdit.setText(getStrParams("smsReceiver"));
		sleepInt(1);
		LeUiObject simcard = new LeUiObject(new UiSelector()
			.className("android.widget.ImageButton").resourceId("com.android.mms:id/selected_sim"));
		verify(simcard.exists());
		simcard.click();
		sleepInt(1);
		LeUiObject sim2card = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").resourceId("com.android.mms:id/simName").text("CMCC 02"));
		verify(sim2card.exists());
		sim2card.click();
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.EditText").resourceId("com.android.mms:id/embedded_text_editor"));
		contentEdit.setText(getStrParams("smsContent"));
		sleepInt(1);
		addStep("发送新信息");
		LeUiObject sendBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.mms:id/send_button_sms"));
		sendBtn.click();
		LeUiObject sendingMark = new LeUiObject(new UiSelector()
		.className("android.widget.TextView")
		.resourceId("com.android.mms:id/date_view")
		.textContains("SENDING"));
		verify("未能发送信息", sendingMark.waitUntilGone(20000));
		LeUiObject date_view = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
						"com.android.mms:id/date_view"));
		verify("未能找到发送信息时间", date_view.exists());
		addStep("step3:查看SIM1收到的短信");
		LeUiObject receiveSMS = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").resourceId(
						"com.android.mms:id/msg_list_item_recv").index(1));
		addStep("等待10秒");
		sleepInt(10);
		verify("未收到短信", receiveSMS.exists());
		addStep("删除发送的信息");
		LeUiObject backBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.mms").description("Navigate up"));
		backBtn.click();
		sleepInt(3);
		deleteExistingMsg();
		sleepInt(1);
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	private void deleteExistingMsg() throws UiObjectNotFoundException {
		LeUiObject msg = new LeUiObject(new UiSelector()
		.className("android.widget.ListView").childSelector(new UiSelector().className(
				"android.widget.RelativeLayout").index(0)));
		verify(msg.exists());
		int dx = msg.getBounds().centerX();
		int dy = msg.getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		sleepInt(1);
		LeUiObject selectAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Select all"));
		if(selectAll.exists()){
			selectAll.click();
			sleepInt(2);
		}
		LeUiObject del = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Delete"));
		verify(del.exists());
		del.click();
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("Delete"));
		verify(confirm.exists());
		confirm.click();
		sleepInt(4);
	}
}

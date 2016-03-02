package com.letv.cases.leui.TDLTE;

import android.R.integer;

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
		
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	@CaseName("SIM1: 收发短信 ")
	public void testSMSSim1() throws UiObjectNotFoundException {
		connectData();
		switch4G();
		addStep("step1:SIM1切换到4G网络下");
		SIM1dataUse();
		addStep("step2:使用SIM1发短信");
		addStep("打开信息程序");
		launchApp(AppName.MESSAGE);
		sleepInt(3);
		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().textContains("无会话"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(3);
		verify("信息列表不为空", emptyPrompt.exists());
		addStep("编写新信息");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").resourceId("com.android.mms:id/action_compose_new"));
		newMsgBtn.click();
		sleepInt(3);
		LeUiObject receiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.view.View").resourceId("com.android.mms:id/recipients_editor"));
		verify("填写收件人的地方不存在",receiverEdit.exists());
		receiverEdit.setText(getStrParams("dialNum"));
		sleepInt(3);
		/*LeUiObject simcard = new LeUiObject(new UiSelector()
			.className("android.widget.ImageButton").resourceId("com.android.mms:id/selected_sim"));
		verify(simcard.exists());
		simcard.click();*/
		sleepInt(3);
		LeUiObject sim1card = new LeUiObject(new UiSelector().className("android.widget.FrameLayout").resourceId("com.android.mms:id/sim1View"));
		verify("sim1卡不存在",sim1card.exists());
		sim1card.click();
		sleepInt(3);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.EditText").resourceId("com.android.mms:id/embedded_text_editor"));
		contentEdit.setText(getStrParams("smsContent"));
		sleepInt(3);
		addStep("发送新信息");
		LeUiObject sendBtn = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"com.android.mms:id/send_button_sms"));
		sendBtn.click();
		sleepInt(2);
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
		for(int i=0;i<60;i++){
			sleepInt(1);
			if(receiveSMS.exists()){
				break;
			}
		}
		verify("未收到短信", receiveSMS.exists());
		addStep("删除发送的信息");
		press_back(1);
		sleepInt(2);
		verify("没有退回到短信列表",newMsgBtn.exists());
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
		connectData();
		switch4G();
		addStep("step1:SIM2切换到4G网络下");
		SIM2dataUse();
		addStep("step2:使用SIM2发短信");
		addStep("打开信息程序");
		launchApp(AppName.MESSAGE);
		sleepInt(2);
		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().textContains("无会话"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		addStep("编写新信息");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").resourceId("com.android.mms:id/action_compose_new"));
		newMsgBtn.click();
		sleepInt(3);
		LeUiObject receiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.view.View").resourceId("com.android.mms:id/recipients_editor"));
		verify("填写收件人的地方不存在",receiverEdit.exists());
		receiverEdit.setText(getStrParams("dialNumSim2"));
		sleepInt(2);
		/*LeUiObject simcard = new LeUiObject(new UiSelector()
			.className("android.widget.ImageButton").resourceId("com.android.mms:id/selected_sim"));
		verify(simcard.exists());
		simcard.click();*/
		sleepInt(1);
		LeUiObject sim2card = new LeUiObject(new UiSelector().className("android.widget.FrameLayout").resourceId("com.android.mms:id/sim2View"));
		verify("sim2卡不存在",sim2card.exists());
		sim2card.click();
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.EditText").resourceId("com.android.mms:id/embedded_text_editor"));
		contentEdit.setText(getStrParams("smsContent"));
		sleepInt(1);
		addStep("发送新信息");
		LeUiObject sendBtn = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"com.android.mms:id/send_button_sms"));
		sendBtn.click();
		sleepInt(2);
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
		addStep("等待90秒");
		for(int i=0;i<90;i++){
			sleepInt(1);
			if(receiveSMS.exists()){
				break;
			}
		}
		verify("未收到短信", receiveSMS.exists());
		addStep("删除发送的信息");
		press_back(1);
		sleepInt(2);
		verify("没有退回到短信列表",newMsgBtn.exists());
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
				"android.widget.RelativeLayout").index(1)));
		verify("短信不存在",msg.exists());
		int dx = msg.getBounds().centerX();
		int dy = msg.getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 300);
		LeUiObject selectAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("全选"));
		LeUiObject unselectAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("全不选"));
		verify("全选或者全不选不存在",selectAll.exists()||unselectAll.exists());
		for (int i = 0; i < 10; i++) {
			if((selectAll.exists())||(unselectAll.exists())){
				break;
			}
			sleepInt(1);
		}
		if(!unselectAll.exists()){
			selectAll.click();
		}
		
		sleepInt(2);
		LeUiObject del = new LeUiObject(new UiSelector().resourceId("com.android.mms:id/linear_bottom_action")
				.index(1).childSelector(new UiSelector().resourceId("com.android.mms:id/bottom_action").index(0)
						.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)
								.childSelector(new UiSelector().className("android.widget.TextView").resourceId("android:id/title")))));
		verify("删除按钮不存在",del.exists());
		del.click();
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().className("android.widget.Button").resourceId("android:id/le_bottomsheet_default_confirm"));
		verify("确认按钮不存在",confirm.exists());
		confirm.click();
		sleepInt(4);
	}
}

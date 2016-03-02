package com.letv.cases.leui.Settings;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class AirPlaneMode extends LetvTestCase {
	
	@CaseName("Mobile-35366:飞行模式下短信功能")
	public void testAirPlaneSMS() throws UiObjectNotFoundException {
		addStep("1.开启飞行模式4.关闭飞行模式");
		openAPM();
		press_back(2);
		addStep("2.发短信");
		launchApp(AppName.MESSAGE);
		sleepInt(2);
		deleteExistingMsg();
		addStep("编写新信息");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").resourceId("com.android.mms:id/action_compose_new"));
		verify("新建信息的button不存在",newMsgBtn.exists());
		newMsgBtn.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject receiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.view.View").resourceId("com.android.mms:id/recipients_editor"));
		verify("填写收件人的地方不存在",receiverEdit.exists());
		receiverEdit.setText(getStrParams("sim1Receiver"));
		sleepInt(2);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.EditText").resourceId("com.android.mms:id/embedded_text_editor"));
		verify("编辑彩信内容的地方不存在",contentEdit.exists());
		contentEdit.setText(getStrParams("smsContent"));
		sleepInt(2);
		LeUiObject sim1card = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").resourceId("com.android.mms:id/sim1Name").textContains("01"));
		verify("sim1卡不存在",sim1card.exists());
		sim1card.click();
		sleepInt(1);
		addStep("发送新信息");
		LeUiObject sendBtn = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"com.android.mms:id/send_button_mms"));
		verify("发送信息的button不存在",sendBtn.exists());
		sendBtn.click();
		sleepInt(3);
		LeUiObject planePrompt = new LeUiObject(new UiSelector().text("确定"));
		verify("未能弹出飞行模式已启动的弹窗", planePrompt.exists());
		sleepInt(2);
		planePrompt.click();
		sleepInt(2);
		LeUiObject dropConfirm = new LeUiObject(new UiSelector().text("确定"));
		for(int i=0;i<5;i++){
			if(dropConfirm.exists()){
				dropConfirm.click();
				break;
			}
			press_back(1);
		}
		addStep("step4:关闭AMP");
		closeAPM();
		sleepInt(5);
		addStep("退出应用程序");
	}
	
	@CaseName("Mobile-35366:飞行模式下短彩信功能")
	public void testAirPlaneMMS() throws UiObjectNotFoundException {
		addStep("1.开启飞行模式4.关闭飞行模式");
		openAPM();
		press_back(2);
		addStep("2.收/发彩信");
		launchApp(AppName.MESSAGE);
		sleepInt(2);
		deleteExistingMsg();
		addStep("编写新信息");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").resourceId("com.android.mms:id/action_compose_new"));
		verify("新建信息的button不存在",newMsgBtn.exists());
		newMsgBtn.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject receiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.view.View").resourceId("com.android.mms:id/recipients_editor"));
		verify("填写收件人的地方不存在",receiverEdit.exists());
		receiverEdit.setText(getStrParams("sim1Receiver"));
		sleepInt(2);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.EditText").resourceId("com.android.mms:id/embedded_text_editor"));
		verify("编辑彩信内容的地方不存在",contentEdit.exists());
		contentEdit.setText(getStrParams("smsContent"));
		sleepInt(1);
		LeUiObject attach = new LeUiObject(
				new UiSelector()
						.className("android.widget.ImageButton").resourceId("com.android.mms:id/share_button").index(0));
		verify("添加附件的按钮不存在",attach.exists());
		attach.click();
		sleepInt(3);
		LeUiObject pic = new LeUiObject(
				new UiSelector()
						.className("android.widget.TextView").resourceId("com.android.mms:id/tv_share_name").text("拍摄照片"));
		verify("拍摄照片不存在",pic.exists());
		pic.click();
		sleepInt(3);
		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/shutter_button"));
		verify("Can't find shutter button.", shutter.exists());
		shutter.click();
		sleepInt(3);
		LeUiObject ok = new LeUiObject(new UiSelector().resourceId(
						"com.android.camera2:id/btn_done"));
		verify("拍摄完照片后确定的对勾不存在",ok.exists());
		ok.clickAndWaitForNewWindow();
		sleepInt(2);
		LeUiObject sim1card = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").resourceId("com.android.mms:id/sim1Name").textContains("01"));
		verify("sim1卡不存在",sim1card.exists());
		sim1card.click();
		sleepInt(1);
		addStep("发送新信息");
		LeUiObject sendBtn = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"com.android.mms:id/send_button_mms"));
		verify("发送信息的button不存在",sendBtn.exists());
		sendBtn.click();
		sleepInt(3);
		LeUiObject planePrompt = new LeUiObject(new UiSelector().text("确定"));
		verify("未能弹出飞行模式已启动的弹窗", planePrompt.exists());
		sleepInt(2);
		planePrompt.click();
		sleepInt(2);
		LeUiObject dropConfirm = new LeUiObject(new UiSelector().text("确定"));
		for(int i=0;i<5;i++){
			if(dropConfirm.exists()){
				dropConfirm.click();
				break;
			}
			press_back(1);
		}
		addStep("step4:关闭AMP");
		closeAPM();
		sleepInt(5);
		addStep("退出应用程序");
	}
	private void deleteExistingMsg() throws UiObjectNotFoundException {
		LeUiObject msg = new LeUiObject(new UiSelector()
		.className("android.widget.ListView").childSelector(new UiSelector().className(
				"android.widget.RelativeLayout").index(1)));
		verify("彩信不存在",msg.exists());
		int dx = msg.getBounds().centerX();
		int dy = msg.getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		sleepInt(2);
		LeUiObject selectAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("全选"));
		LeUiObject unselectAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("全不选"));
		verify("全选或者全不选不存在",selectAll.exists()||unselectAll.exists());
		if(!unselectAll.exists()){
			selectAll.click();
		}
		
		sleepInt(2);
		LeUiObject del = new LeUiObject(new UiSelector().resourceId("com.android.mms:id/linear_bottom_action")
				.index(1).childSelector(new UiSelector().resourceId("com.android.mms:id/bottom_action").index(0)
						.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
								.childSelector(new UiSelector().className("android.widget.TextView").resourceId("android:id/title")))));
		verify("删除按钮不存在",del.exists());
		del.click();
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().className("android.widget.Button").text("删除"));
		verify("确认按钮不存在",confirm.exists());
		confirm.click();
		sleepInt(4);
	}
}

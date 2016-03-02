package com.letv.cases.proto.message;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class SMSMMS extends LetvTestCase {

	private final String SUBJECT = "010";
	private final String CONTENT = "123";
	private final String picture = "Image 100K.JPG";
	private final String audio = "Sound 100k.amr";
	private final String video = "Video 100K.3gp";

	// insert the attachments
	private void attach(String path, String att)
			throws UiObjectNotFoundException {
		LeUiObject attBtn = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").description("Attach"));
		verify("Can't find attach button.", attBtn.exists());
		attBtn.click();
		sleepInt(1);
		LeUiObject attPic = new LeUiObject(new UiSelector().resourceId(
				"com.android.mms:id/text1").text(path));
		verify("Can't find picture button.", attPic.exists());
		attPic.click();
		sleepInt(2);
		LeUiObject filemanager = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("File Manager"));
		if (filemanager.exists()) {
			filemanager.click();
			sleepInt(2);
		}
		LeUiObject always = new LeUiObject(new UiSelector().resourceId(
				"android:id/button_always").text("Always"));
		if (always.exists()) {
			always.click();
			sleepInt(2);
		}
		UiScrollable list = new UiScrollable(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.rhmsoft.fm:id/entryList"));
		verify("Can't find list view.", list.exists());
		list.setAsVerticalList();
		UiObject resource = list
				.getChildByText(new UiSelector()
						.className(android.widget.TextView.class.getName()),
						"Resource");
		verify("Can't find Resource folder.", resource.exists());
		resource.click();
		sleepInt(1);
		LeUiObject mms = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("MMS"));
		verify("Can't find MMS folder.", mms.exists());
		mms.click();
		sleepInt(1);
		LeUiObject image = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text(att));
		verify("Can't find attachment.", image.exists());
		image.click();
		sleepInt(2);
		LeUiObject image_content = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.mms:id/image_content"));
		if (!image_content.exists()) {
			image_content = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.mms:id/video_thumbnail"));
		}
		if (!image_content.exists()) {
			image_content = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/audio_name"));
		}
		verify("Can't attach accessory.", image_content.exists());
	}

	@CaseName("发送短信")
	public void testSendSMS() throws UiObjectNotFoundException {
		addStep("Open Messaging");
		LeUiObject msgApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Messaging"));
		verify("Can't found Messaging icon", msgApp.waitForExists(5000));
		msgApp.clickAndWaitForNewWindow();
		verify("Can't open Messaging", phone.getCurrentPackageName().equals("com.android.mms"));
		
		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("No conversations"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("Message list not null", emptyPrompt.exists());
		for (int i = 1; i <= getIntParams("Loop"); i++) {
//		for (int i = 1; i <= 3; i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			LeUiObject newMsgBtn = new LeUiObject(new UiSelector().resourceId(
					"com.android.mms:id/action_compose_new").description(
					"New message"));
			newMsgBtn.click();
			sleepInt(2);
			LeUiObject PhoneSMSReceiverEdit = new LeUiObject(
					new UiSelector()
							.className("android.widget.MultiAutoCompleteTextView"));
			verify("未找到收信人输入框", PhoneSMSReceiverEdit.waitForExists(5000));
			PhoneSMSReceiverEdit.setText(getStrParams("dialNum"));
//			PhoneSMSReceiverEdit.setText("10086");
			sleepInt(2);
			LeUiObject contentEdit = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/embedded_text_editor"));
			contentEdit.setText(CONTENT);
			sleepInt(2);
			addStep("Send the new message");
			LeUiObject sendBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").resourceId(
					"com.android.mms:id/send_button_sms"));
			sendBtn.click();
			sleepInt(2);
			LeUiObject sendingMark = new LeUiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("com.android.mms:id/date_view")
					.textContains("SENDING"));
			// sleepInt(10);
			LeUiObject errorMark = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/delivered_indicator"));
			verify("未能发送信息", sendingMark.waitUntilGone(20000));
			LeUiObject date_view = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/date_view"));
			verify("未能找到发送信息时间", date_view.exists());
			addStep("Delete the already send SMS");
			sleepInt(3);
			press_back(1);
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("打开短信")
	public void testOpenSMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		LeUiObject msgApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Messaging"));
		verify("找不到信息图标", msgApp.waitForExists(5000));
		msgApp.clickAndWaitForNewWindow();
		verify("未能打开信息程序", phone.getCurrentPackageName().equals("com.android.mms"));

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("No conversations"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());

		addStep("编写新信息");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector().resourceId(
				"com.android.mms:id/action_compose_new").description(
				"New message"));
		newMsgBtn.click();
		sleepInt(1);
		LeUiObject PhoneSMSReceiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.MultiAutoCompleteTextView"));
		verify("找不到收信人编辑框", PhoneSMSReceiverEdit.waitForExists(5000));
		PhoneSMSReceiverEdit.click();
		sleepInt(1);
		PhoneSMSReceiverEdit.setText(getStrParams("dialNum"));
//		PhoneSMSReceiverEdit.setText("10086");
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/embedded_text_editor"));
		verify("找不到信息内容编辑框", contentEdit.waitForExists(5000));
		contentEdit.click();
		sleepInt(1);
		contentEdit.setText(CONTENT);
		sleepInt(1);
		verify("输入信息有误",
				PhoneSMSReceiverEdit.getText().equals(
						getStrParams("dialNum") + ", ")
//						"10086" + ", ")
						&& contentEdit.getText().equals(CONTENT));

		addStep("保存信息");
		// LeUiObject backBtn = new LeUiObject(new UiSelector().className(
		// "android.widget.ImageView").resourceId("com.android.mms:id/up"));
		// backBtn.click();
		press_back(1);
		sleepInt(1);
		LeUiObject draft = new LeUiObject(new UiSelector().className(
				"android.widget.TextView")
				.textContains(getStrParams("dialNum")));
//				.textContains("10086"));
		verify("信息未保存", draft.waitForExists(10000));
		LeUiObject smsReceiver = new LeUiObject(new UiSelector().resourceIdMatches(
				"com.android.mms:id/recipients_editor").className("android.widget.MultiAutoCompleteTextView"));
		for (int i = 1; i <= getIntParams("Loop"); i++) {
//		for (int i = 1; i <= 10; i++) {
			addStep("打开已保存的信息，第" + String.valueOf(i) + "遍");
			draft.click();
			sleepInt(1);
			verify("收信人或信息内容未找到.", smsReceiver.exists() && contentEdit.exists());
			verify("保存的信息有误",
					smsReceiver.getText()
							.equals(getStrParams("dialNum")+", ")
//					        .equals("10086, ")
							&& contentEdit.getText().equals(CONTENT));
			// backBtn.click();
			press_back(1);
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("发送图片彩信")
	public void testSendPicMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		LeUiObject msgApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Messaging"));
		msgApp.clickAndWaitForNewWindow();
		verify("未能打开信息程序", phone.getCurrentPackageName().equals("com.android.mms"));

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("No conversations."));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			LeUiObject newMsgBtn = new LeUiObject(new UiSelector().resourceId(
					"com.android.mms:id/action_compose_new").description(
					"New message"));
			newMsgBtn.click();
			sleepInt(1);
			LeUiObject PhoneSMSReceiverEdit = new LeUiObject(
					new UiSelector()
							.className("android.widget.MultiAutoCompleteTextView"));
			PhoneSMSReceiverEdit.setText(getStrParams("dialNum"));
			sleepInt(1);
			LeUiObject more = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").description("More options"));
			verify("Can't find more button.", more.exists());
			more.click();
			sleepInt(1);
			LeUiObject add_subject = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("Add subject"));
			verify("Can't find add subject button.", add_subject.exists());
			add_subject.click();
			sleepInt(1);
			LeUiObject subjectEdit = new LeUiObject(
					new UiSelector().resourceId("com.android.mms:id/subject"));
			verify("Can't find subject edit.", subjectEdit.exists());
			subjectEdit.click();
			sleepInt(1);
			callShell("input text " + SUBJECT);
			sleepInt(1);
			LeUiObject contentEdit = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/embedded_text_editor"));
			contentEdit.setText(CONTENT);
			sleepInt(1);
			addStep("插入图片附件");
			attach("Pictures", picture);
			addStep("发送新信息");
			LeUiObject sendBtn = new LeUiObject(new UiSelector().resourceId(
					"com.android.mms:id/send_button_mms").description("发送彩信"));
			sendBtn.click();

			LeUiObject sendingMark = new LeUiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("com.android.mms:id/date_view")
					.textContains("正在发送"));
			// sleepInt(10);
			LeUiObject errorMark = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/delivered_indicator"));
			// verify("未能发送信息", sendingMark.waitUntilGone(10000));
			// verify("发送失败", errorMark.exists());

			addStep("删除发送的信息");
			press_back(1);
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("发送音频彩信")
	public void testSendAudioMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		LeUiObject msgApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("短信"));
		msgApp.clickAndWaitForNewWindow();
		verify("未能打开信息程序", phone.getCurrentPackageName().equals("com.android.mms"));

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		for (int i = 1; i <= TestCaseLoop; i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			LeUiObject newMsgBtn = new LeUiObject(new UiSelector().resourceId(
					"com.android.mms:id/action_compose_new").description("新信息"));
			newMsgBtn.click();
			sleepInt(1);
			LeUiObject PhoneSMSReceiverEdit = new LeUiObject(
					new UiSelector()
							.className("android.widget.MultiAutoCompleteTextView"));
			PhoneSMSReceiverEdit.setText(PhoneSMSReceiver);
			sleepInt(1);
			LeUiObject more = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").description("更多选项"));
			verify("Can't find more button.", more.exists());
			more.click();
			sleepInt(1);
			LeUiObject add_subject = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("添加主题"));
			verify("Can't find add subject button.", add_subject.exists());
			add_subject.click();
			sleepInt(1);
			LeUiObject subjectEdit = new LeUiObject(
					new UiSelector().resourceId("com.android.mms:id/subject"));
			verify("Can't find subject edit.", subjectEdit.exists());
			subjectEdit.click();
			sleepInt(1);
			callShell("input text " + SUBJECT);
			sleepInt(1);
			LeUiObject contentEdit = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/embedded_text_editor"));
			contentEdit.setText(CONTENT);
			sleepInt(1);
			addStep("插入音频附件");
			attach("音频", audio);
			addStep("发送新信息");
			LeUiObject sendBtn = new LeUiObject(new UiSelector().resourceId(
					"com.android.mms:id/send_button_mms").description("发送彩信"));
			sendBtn.click();

			LeUiObject sendingMark = new LeUiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("com.android.mms:id/date_view")
					.textContains("正在发送"));
			// sleepInt(10);
			LeUiObject errorMark = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/delivered_indicator"));
			// verify("未能发送信息", sendingMark.waitUntilGone(10000));
			// verify("发送失败", errorMark.exists());

			addStep("删除发送的信息");
			press_back(1);
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("发送视频彩信")
	public void testSendVideoMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		LeUiObject msgApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("短信"));
		msgApp.clickAndWaitForNewWindow();
		verify("未能打开信息程序", phone.getCurrentPackageName().equals("com.android.mms"));

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		for (int i = 1; i <= TestCaseLoop; i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			LeUiObject newMsgBtn = new LeUiObject(new UiSelector().resourceId(
					"com.android.mms:id/action_compose_new").description("新信息"));
			newMsgBtn.click();
			sleepInt(1);
			LeUiObject PhoneSMSReceiverEdit = new LeUiObject(
					new UiSelector()
							.className("android.widget.MultiAutoCompleteTextView"));
			PhoneSMSReceiverEdit.setText(PhoneSMSReceiver);
			sleepInt(1);
			LeUiObject more = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").description("更多选项"));
			verify("Can't find more button.", more.exists());
			more.click();
			sleepInt(1);
			LeUiObject add_subject = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("添加主题"));
			verify("Can't find add subject button.", add_subject.exists());
			add_subject.click();
			sleepInt(1);
			LeUiObject subjectEdit = new LeUiObject(
					new UiSelector().resourceId("com.android.mms:id/subject"));
			verify("Can't find subject edit.", subjectEdit.exists());
			subjectEdit.click();
			sleepInt(1);
			callShell("input text " + SUBJECT);
			sleepInt(1);
			LeUiObject contentEdit = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/embedded_text_editor"));
			contentEdit.setText(CONTENT);
			sleepInt(1);
			addStep("插入视频附件");
			attach("视频", video);
			addStep("发送新信息");
			LeUiObject sendBtn = new LeUiObject(new UiSelector().resourceId(
					"com.android.mms:id/send_button_mms").description("发送彩信"));
			sendBtn.click();

			LeUiObject sendingMark = new LeUiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("com.android.mms:id/date_view")
					.textContains("正在发送"));
			// sleepInt(10);
			LeUiObject errorMark = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/delivered_indicator"));
			// verify("未能发送信息", sendingMark.waitUntilGone(10000));
			// verify("发送失败", errorMark.exists());

			addStep("删除发送的信息");
			press_back(1);
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("打开图片彩信")
	public void testOpenPicMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		LeUiObject msgApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("短信"));
		msgApp.clickAndWaitForNewWindow();
		verify("未能打开信息程序", phone.getCurrentPackageName().equals("com.android.mms"));

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());

		addStep("编写新彩信");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector().resourceId(
				"com.android.mms:id/action_compose_new").description("新信息"));
		newMsgBtn.click();
		sleepInt(1);
		LeUiObject PhoneSMSReceiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.MultiAutoCompleteTextView"));
		PhoneSMSReceiverEdit.setText(PhoneSMSReceiver);
		sleepInt(1);
		LeUiObject more = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").description("更多选项"));
		verify("Can't find more button.", more.exists());
		more.click();
		sleepInt(1);
		LeUiObject add_subject = new LeUiObject(new UiSelector().resourceId(
				"android:id/title").text("添加主题"));
		verify("Can't find add subject button.", add_subject.exists());
		add_subject.click();
		sleepInt(1);
		LeUiObject subjectEdit = new LeUiObject(
				new UiSelector().resourceId("com.android.mms:id/subject"));
		verify("Can't find subject edit.", subjectEdit.exists());
		subjectEdit.click();
		sleepInt(1);
		callShell("input text " + SUBJECT);
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/embedded_text_editor"));
		contentEdit.setText(CONTENT);
		sleepInt(1);
		addStep("插入图片附件");
		attach("照片", picture);
		verify("输入信息有误",
				PhoneSMSReceiverEdit.getText().equals(PhoneSMSReceiver + ", ")
						&& contentEdit.getText().equals(CONTENT));

		addStep("保存信息");
		// LeUiObject backBtn = new LeUiObject(new UiSelector().className(
		// "android.widget.ImageView").resourceId("com.android.mms:id/up"));
		// backBtn.click();
		press_back(1);
		sleepInt(1);
		LeUiObject draft = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains(PhoneSMSReceiver));
		verify("信息未保存", draft.exists());
		for (int i = 1; i <= TestCaseLoop; i++) {
			addStep("打开已保存的信息，第" + String.valueOf(i) + "遍");
			draft.click();
			sleepInt(1);
			addStep("验证彩信内容及附件");
			verify("Can't find draft message.", PhoneSMSReceiverEdit.exists()
					&& contentEdit.exists());
			verify("保存的信息有误",
					PhoneSMSReceiverEdit.getText().equals(
							PhoneSMSReceiver + ", ")
							&& contentEdit.getText().equals(CONTENT));
			// backBtn.click();
			LeUiObject image_content = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.mms:id/image_content"));
			verify("未发现图片附件", image_content.exists());
			press_back(1);
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("打开音频彩信")
	public void testOpenAudioMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		LeUiObject msgApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("短信"));
		msgApp.clickAndWaitForNewWindow();
		verify("未能打开信息程序", phone.getCurrentPackageName().equals("com.android.mms"));

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());

		addStep("编写新彩信");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector().resourceId(
				"com.android.mms:id/action_compose_new").description("新信息"));
		newMsgBtn.click();
		sleepInt(1);
		LeUiObject PhoneSMSReceiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.MultiAutoCompleteTextView"));
		PhoneSMSReceiverEdit.setText(PhoneSMSReceiver);
		sleepInt(1);
		LeUiObject more = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").description("更多选项"));
		verify("Can't find more button.", more.exists());
		more.click();
		sleepInt(1);
		LeUiObject add_subject = new LeUiObject(new UiSelector().resourceId(
				"android:id/title").text("添加主题"));
		verify("Can't find add subject button.", add_subject.exists());
		add_subject.click();
		sleepInt(1);
		LeUiObject subjectEdit = new LeUiObject(
				new UiSelector().resourceId("com.android.mms:id/subject"));
		verify("Can't find subject edit.", subjectEdit.exists());
		subjectEdit.click();
		sleepInt(1);
		callShell("input text " + SUBJECT);
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/embedded_text_editor"));
		contentEdit.setText(CONTENT);
		sleepInt(1);
		addStep("插入音频附件");
		attach("音频", audio);
		verify("输入信息有误",
				PhoneSMSReceiverEdit.getText().equals(PhoneSMSReceiver + ", ")
						&& contentEdit.getText().equals(CONTENT));

		addStep("保存信息");
		// LeUiObject backBtn = new LeUiObject(new UiSelector().className(
		// "android.widget.ImageView").resourceId("com.android.mms:id/up"));
		// backBtn.click();
		press_back(1);
		sleepInt(1);
		LeUiObject draft = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains(PhoneSMSReceiver));
		verify("信息未保存", draft.exists());
		for (int i = 1; i <= TestCaseLoop; i++) {
			addStep("打开已保存的信息，第" + String.valueOf(i) + "遍");
			draft.click();
			sleepInt(1);
			addStep("验证彩信内容及附件");
			verify("Can't find draft message.", PhoneSMSReceiverEdit.exists()
					&& contentEdit.exists());
			verify("保存的信息有误",
					PhoneSMSReceiverEdit.getText().equals(
							PhoneSMSReceiver + ", ")
							&& contentEdit.getText().equals(CONTENT));
			// backBtn.click();
			LeUiObject audio_content = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/audio_name"));
			verify("未发现音频附件", audio_content.exists());
			press_back(1);
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("打开视频彩信")
	public void testOpenVideoMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		LeUiObject msgApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("短信"));
		msgApp.clickAndWaitForNewWindow();
		verify("未能打开信息程序", phone.getCurrentPackageName().equals("com.android.mms"));

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());

		addStep("编写新彩信");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector().resourceId(
				"com.android.mms:id/action_compose_new").description("新信息"));
		newMsgBtn.click();
		sleepInt(1);
		LeUiObject PhoneSMSReceiverEdit = new LeUiObject(
				new UiSelector()
						.className("android.widget.MultiAutoCompleteTextView"));
		PhoneSMSReceiverEdit.setText(PhoneSMSReceiver);
		sleepInt(1);
		LeUiObject more = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").description("更多选项"));
		verify("Can't find more button.", more.exists());
		more.click();
		sleepInt(1);
		LeUiObject add_subject = new LeUiObject(new UiSelector().resourceId(
				"android:id/title").text("添加主题"));
		verify("Can't find add subject button.", add_subject.exists());
		add_subject.click();
		sleepInt(1);
		LeUiObject subjectEdit = new LeUiObject(
				new UiSelector().resourceId("com.android.mms:id/subject"));
		verify("Can't find subject edit.", subjectEdit.exists());
		subjectEdit.click();
		sleepInt(1);
		callShell("input text " + SUBJECT);
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/embedded_text_editor"));
		contentEdit.setText(CONTENT);
		sleepInt(1);
		addStep("插入视频附件");
		attach("视频", video);
		verify("输入信息有误",
				PhoneSMSReceiverEdit.getText().equals(PhoneSMSReceiver + ", ")
						&& contentEdit.getText().equals(CONTENT));

		addStep("保存信息");
		// LeUiObject backBtn = new LeUiObject(new UiSelector().className(
		// "android.widget.ImageView").resourceId("com.android.mms:id/up"));
		// backBtn.click();
		press_back(1);
		sleepInt(1);
		LeUiObject draft = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains(PhoneSMSReceiver));
		verify("信息未保存", draft.exists());
		for (int i = 1; i <= TestCaseLoop; i++) {
			addStep("打开已保存的信息，第" + String.valueOf(i) + "遍");
			draft.click();
			sleepInt(1);
			addStep("验证彩信内容及附件");
			verify("Can't find draft message.", PhoneSMSReceiverEdit.exists()
					&& contentEdit.exists());
			verify("保存的信息有误",
					PhoneSMSReceiverEdit.getText().equals(
							PhoneSMSReceiver + ", ")
							&& contentEdit.getText().equals(CONTENT));
			// backBtn.click();
			LeUiObject video_content = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.mms:id/video_thumbnail"));
			verify("未发现视频附件", video_content.exists());
			press_back(1);
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(5);
	}

	private void deleteExistingMsg() throws UiObjectNotFoundException {
		LeUiObject msgList = new LeUiObject(new UiSelector()
				.className("android.widget.ListView")
				.packageName("com.android.mms").resourceId("android:id/list"));
		msgList.waitForExists(5000);
		if (!msgList.exists())
			return;
		LeUiObject editLabel = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").description("More options"));
		verify("找不到更多按钮", editLabel.waitForExists(5000));
		editLabel.click();
		sleepInt(1);
		// LeUiObject selAll = new LeUiObject(new UiSelector().className(
		// "android.widget.TextView").text("全选"));
		// selAll.click();
		// sleepInt(1);
		LeUiObject delAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Delete all threads"));
		verify("找不到全部删除按钮", delAll.waitForExists(5000));
		delAll.click();
		sleepInt(1);
		// workaround for the invisiable popup prompt
		// phone.click(770, 1690);
		LeUiObject confirm = new LeUiObject(new UiSelector().resourceId(
				"android:id/button1").text("Delete"));
		verify("Can't find confirm button.", confirm.waitForExists(10000));
		confirm.click();
	}
}

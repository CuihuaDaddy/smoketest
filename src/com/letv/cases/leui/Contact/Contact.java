package com.letv.cases.leui.Contact;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;
import com.letv.uf.Utf7ImeHelper;

public class Contact extends LetvTestCase {
	private final String CONTENT = "123";
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

	// add contact
	private void addContact() throws UiObjectNotFoundException {
		// find add contact button
		LeUiObject add1 = new LeUiObject(new UiSelector().textContains("新建联系人"));
		LeUiObject add2 = new LeUiObject(new UiSelector().resourceId(
				"com.android.dialer:id/menu_add"));
		verify("Can't find add image button.", add1.exists()||add2.exists());
		if(add1.exists()){
			add1.click();
		}else{
			add2.click();
		}
		sleepInt(3);
		// fill name field
		LeUiObject name1 = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text("Name"));
		LeUiObject name2 = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text("姓名"));
		verify("Can't find name field.", name1.exists() || name2.exists());
		if (name1.exists()) {
			name1.setText(getStrParams("dialNum"));
			
		} else {
			name2.setText(Utf7ImeHelper.e("张三"));
		}
		sleepInt(2);
		LeUiObject cancelWindow = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text(getStrParams("dialNum")));
		if (cancelWindow.exists()) {
			cancelWindow.click();
			sleepInt(2);
		}
		LeUiObject cancel = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("取消"));
		sleepInt(2);
		if(cancel.exists()){
			
			press_back(1);
			sleepInt(2);
		}
		// fill number field
		LeUiObject number1 = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text("Phone"));
		LeUiObject number2 = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text("电话"));
		verify("Can't find number field.", number1.exists() || number2.exists());
		if (number1.exists()) {
			number1.setText(getStrParams("dialNum"));
		} else {
			number2.setText(getStrParams("dialNum"));
		}
		// click complete button
		LeUiObject complete = new LeUiObject(
				new UiSelector().className("android.widget.TextView").text("完成"));
		verify("Can't find complete button.", complete.exists());
		sleepInt(1);
		complete.click();
		sleepInt(3);
		LeUiObject no = new LeUiObject(
				new UiSelector().textContains("暂不"));
		if(no.exists()){
			no.click();
			sleepInt(4);
		}
		// confirm add contact success
		addStep("确认联系人新建成功");
		LeUiObject detail = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.contacts:id/card_container"));
		verify("Add contact failed.", detail.exists());
		// phone.pressBack();
	}

	// delete contact
	private void deleteContact() throws UiObjectNotFoundException {
		
		LeUiObject delete = new LeUiObject(new UiSelector().textContains("删除"));
		verify("Can't find delete button.",
				delete.exists());
		delete.click();
		sleepInt(3);
		LeUiObject delConta = new LeUiObject(new UiSelector().textContains("删除联系人"));
		if(delConta.exists()){
			delConta.click();
			sleepInt(2);
		}

		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定")
				.className("android.widget.Button").index(2));
		if(confirm.exists()){
			confirm.click();
			sleepInt(2);
		}
		if(delete.exists()){
			delete.click();
		}
		sleepInt(4);
		verify("delete contact failed.", !confirm.exists()&&(!delConta.exists()));
	}

	// 联系人详情页拨打电话
	private void callContact() throws UiObjectNotFoundException {
		LeUiObject delete1 = new LeUiObject(new UiSelector().text("Delete")
				.resourceId("android:id/title"));
		LeUiObject delete2 = new LeUiObject(new UiSelector().textContains("删除"));
		verify("Can't find delete button.",
				delete1.exists() || delete2.exists());
		LeUiObject callContact = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout")
		.index(0)
		.childSelector(
				new UiSelector()
						.className("android.widget.LinearLayout")
						.index(0)
						.childSelector(
								new UiSelector().className(
										"android.widget.RelativeLayout")
										.index(0)
										.childSelector(new UiSelector().className("android.widget.ImageView").index(0)))));
		verify("拨号按钮不存在",callContact.exists());
		callContact.click();
		sleepInt(4);
		LeUiObject chooseSIM1 = new LeUiObject(new UiSelector()
			.resourceId("android:id/le_bottomsheet_gridview")
			.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)));
		if(chooseSIM1.exists()){
			chooseSIM1.click();
			sleepInt(10);
			return;
		}
		LeUiObject dialapp =new LeUiObject(new UiSelector()
		.className("android.widget.FrameLayout").resourceId("com.android.dialer:id/show_call_1").index(0));
		verify("没有进入在拨号界面", dialapp.exists());
		dialapp.click();
		sleepInt(5);
	}
	private void sendsms() throws UiObjectNotFoundException{
		LeUiObject smsbtn =new LeUiObject(
				new UiSelector().resourceId("com.android.contacts:id/icon_alternate"));
		verify("没有发现短信按钮",smsbtn.exists());
		phone.click(1340, 1155);
		sleepInt(2);
		LeUiObject insertmsg = new LeUiObject(
				 new UiSelector()
				 .className("android.widget.EditText").resourceId("com.android.mms:id/embedded_text_editor"));
		verify("input messages is not exists",insertmsg.exists());
		insertmsg.click();
		insertmsg.setText(getStrParams("smsContent"));
		sleepInt(2);
		LeUiObject chooseSIM1 = new LeUiObject(new UiSelector()
			.resourceId("com.android.mms:id/sub_sim2_selected_layout"));		
		if(chooseSIM1.exists()){
			chooseSIM1.click();
			sleepInt(4);
		}
		addStep("发送新信息");
		LeUiObject sendBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.mms:id/send_button_sms"));
		verify("发送信息的button不存在",sendBtn.exists());
		sendBtn.click();
		LeUiObject date_view = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
						"com.android.mms:id/date_view"));
		verify("未能找到发送信息时间", date_view.exists());
	}
	@CaseName("启动联系人应用")
	// 1.启动联系人应用
	// 2.home键后台联系人，再次点击联系人
	// 3.home键后台联系人 调用多任务，多任务中点击联系人
	public void testOpenContact() throws UiObjectNotFoundException {
		addStep("Step1:启动联系人应用");
		launchApp(AppName.PHONE);
		sleepInt(2);
		// find contact image button
		LeUiObject contact1 = new LeUiObject(new UiSelector()
				.packageName("com.android.dialer")
				.className("android.widget.TextView").text("Contacts"));
		LeUiObject contact2 = new LeUiObject(new UiSelector()
				.packageName("com.android.dialer")
				.className("android.widget.TextView").text("联系人"));
		verify("Can't find contact.", contact1.exists() || contact2.exists());
		if (contact1.exists()) {
			contact1.click();
		} else {
			contact2.click();
		}
		sleepInt(2);
		addStep("step2:home键后台图库，再次点击图库");
		press_home(1);
		sleepInt(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.PHONE);
		sleepInt(5);
		verify("Can't find contact image button.", contact1.exists()
				|| contact2.exists());
		addStep("step3:home键后台图库调用多任务，多任务中点击图库");
		sleepInt(1);
		press_menu(1);
		sleepInt(2);
		LeUiObject clear = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").textContains("立即清理"))));
		if(clear.exists()){
			clear.click();
			sleepInt(3);
			press_menu(1);
		}
		sleepInt(1);
		LeUiObject recent = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		LeUiObject recent2 = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		verify("最近不存在",recent.exists()||recent2.exists());
		if(recent.exists()){
			recent.click();
		}else{
			recent2.click();
		}
		sleepInt(3);
		verify("Can't find contact.", contact1.exists() || contact2.exists());
		addStep("退出应用");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	@CaseName("联系人基本功能")
	// 1.查看联系人
	// 2.编辑联系人
	// 3.删除联系人
	public void testAddDelContacts() throws UiObjectNotFoundException {
		addStep("打开通讯录");
		launchApp(AppName.PHONE);
		// find contact image button
		LeUiObject contact1 = new LeUiObject(new UiSelector()
				.packageName("com.android.dialer")
				.className("android.widget.TextView").text("Contacts"));
		LeUiObject contact2 = new LeUiObject(new UiSelector()
				.packageName("com.android.dialer")
				.className("android.widget.TextView").text("联系人"));
		verify("Can't find contact.", contact1.exists() || contact2.exists());
		if (contact1.exists()) {
			contact1.click();
		} else {
			contact2.click();
		}
		sleepInt(2);
		addStep("新建联系人");
		addContact();
		addStep("删除联系人");
		sleepInt(3);
		deleteContact();
		addStep("退出通讯录");
		press_back(5);
	}

	@CaseName("通过联系人发起任务")
	// 1.联系人详情页拨打电话
	// 2.联系人详情页发送短信
	public void testCallSmsContacts() throws UiObjectNotFoundException {
		addStep("打开通讯录");
		launchApp(AppName.PHONE);
		// find contact image button
		LeUiObject contact1 = new LeUiObject(new UiSelector()
				.packageName("com.android.dialer")
				.className("android.widget.TextView").text("Contacts"));
		LeUiObject contact2 = new LeUiObject(new UiSelector()
				.packageName("com.android.dialer")
				.className("android.widget.TextView").text("联系人"));
		verify("Can't find contact.", contact1.exists() || contact2.exists());
		if (contact1.exists()) {
			contact1.click();
		} else {
			contact2.click();
		}
		sleepInt(1);
		addStep("新建联系人");
		addContact();
		addStep("联系人详情页拨打电话");
		callContact();
		addStep("挂电话");
		LeUiObject endcall =new LeUiObject(new UiSelector().className("android.widget.ImageButton").index(0));
		verify("没有在打电话界面", endcall.exists());
		LeUiObject contactInfo =new LeUiObject(new UiSelector().textContains("张三"));
		verify("拨号界面联系人信息有误",contactInfo.exists());
		endcall.click();
		sleepInt(4);
		launchApp(AppName.PHONE);
		verify("通话记录中未能找到打电话的记录",contactInfo.exists());
		contact2.click();
		sleepInt(2);
		addContact();
		addStep("联系人详情页发送短信");
		sendsms();
		press_back(5);
	}
}

package com.letv.cases.leui.Email;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Email extends LetvTestCase {
	
	// get send count
	private int getSendCount() throws UiObjectNotFoundException, RemoteException {
		LeUiObject menu = new LeUiObject(new UiSelector()
			.className("android.widget.ImageButton").description("打开抽屉式导航栏"));
		LeUiObject outbox = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/name").text("已发送邮件"));
		verify("Can't find menu button.", menu.exists());
		menu.click();
		sleepInt(2);
		verify("发件箱没找到",outbox.exists());
		outbox.click();
		sleepInt(4);
		int count = 0;
		LeUiObject listView = new LeUiObject(new UiSelector().resourceId("android:id/list"));
		verify("Cant't find list view.", listView.exists());
		count = listView.getChildCount();
		press_back(1);
		return count;
	}

	public void addAP() throws UiObjectNotFoundException {
				
		sleepInt(2);
		LeUiObject add= new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"android:id/title").text("添加网络"));
		verify("添加网络按钮不存在",add.exists());	
		add.click();
		sleepInt(1);
		LeUiObject APname = new LeUiObject(
				new UiSelector().resourceId("android:id/edit").text("网络名称"));
		verify("AP名称不存在",APname.exists());
		APname.click();
		sleepInt(2);
		APname.setText(getStrParams("WifiAP"));
		sleepInt(2);
		LeUiObject APsecurity = new LeUiObject(new UiSelector().resourceId("android:id/title").text("安全性"));
		verify("安全性不存在",APsecurity.exists());
		APsecurity.click();
		sleepInt(2);
		LeUiObject WPA2 = new LeUiObject(
				new UiSelector().resourceId("android:id/le_bottomsheet_text").text("WPA/WPA2 PSK"));
		verify("WPA/WPA2 PSK不存在",WPA2.exists());
		WPA2.click();
		sleepInt(2);
		LeUiObject WPA = new LeUiObject(
				new UiSelector().text("WPA/WPA2 PSK").index(1));
		verify("WPA/WPA2 PSK选择不成功", WPA.exists());
		sleepInt(2);
		LeUiObject APpw= new LeUiObject(
				new UiSelector().resourceId("android:id/title").text("密码"));
		verify("密码不存在",APpw.exists());
		APpw.click();
		sleepInt(2);
		APpw.setText(getStrParams("WifiPWD"));
		LeUiObject OK3 = new LeUiObject(
				new UiSelector().className("android.view.View").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)));
		OK3.click();
		sleepInt(6);
	}

	private void deleteEmail() throws UiObjectNotFoundException {
		LeUiObject firstItem = new LeUiObject(new UiSelector()
		.className("android.widget.RelativeLayout")
		.resourceId("com.android.email:id/back").index(0));
		if(!firstItem.exists()){
			System.out.println("邮箱里没有多余的邮件");
			return;
		}
		int dx = firstItem.getBounds().centerX();
		int dy = firstItem.getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 300);
		sleepInt(2);
		LeUiObject allNot = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.email:id/select_none").text("全不选"));
		LeUiObject all = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("com.android.email:id/select_all").text("全选"));
		if(!allNot.exists()){
			verify("Can't find all button.", all.exists());		
			all.click();
		}
		
		sleepInt(1);
		LeUiObject delete = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/conversation_bottom_bar")
				.className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)));
		for(int i=0;i<30;i++){
			if(delete.exists()){
				break;
			}
			sleepInt(1);
		}
		verify("Can't find delete button.", delete.exists());
		delete.click();
		
		/*
		LeUiObject delete2 = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/conversation_bottom_bar")
				.className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(2)));
		verify("Can't find delete2 button.", delete2.exists());
		delete2.click();
		*/
		
		LeUiObject empty = new LeUiObject(new UiSelector().resourceId(
				"com.android.email:id/empty_view"));
		for(int i=0;i<30;i++){
			if(empty.exists()){
				break;
			}
			sleepInt(1);
		}
		verify("Can't delete all mail.", empty.exists());
	}
		
	private void clearAllBox() throws UiObjectNotFoundException {
		launchApp(AppName.EMAIL);
		sleepInt(2);
		LeUiObject empty = new LeUiObject(new UiSelector().resourceId(
				"com.android.email:id/empty_view"));
		LeUiObject menu = new LeUiObject(new UiSelector()
				.className("android.widget.ImageButton").description("打开抽屉式导航栏"));
		verify("Can't find menu button.", menu.exists());
		menu.click();
		sleepInt(2);
		LeUiObject load = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/conversation_bottom_bar")
			.className("android.widget.LinearLayout").index(1)
			.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)));
		LeUiObject inbox = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.email:id/name").text("收件箱"));
		verify("Can't find inbox button.", inbox.exists());
		inbox.click();
		sleepInt(2);
		verify("Can't find load mail button.", load.exists());
		sleepInt(15);
		if(!empty.exists()){
			sleepInt(2);
			deleteEmail();
		}
		LeUiObject outbox = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("com.android.email:id/name").text("已发送邮件"));
		menu.click();
		sleepInt(2);
		outbox.click();
		sleepInt(2);
		if(!empty.exists()){
			sleepInt(2);
			deleteEmail();
		}
		LeUiObject draft = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("com.android.email:id/name").text("草稿"));
		menu.click();
		sleepInt(2);
		verify("Can't find draft button.", draft.exists());
		draft.click();
		sleepInt(2);
		if(!empty.exists()){
			sleepInt(2);
			deleteEmail();
		}
		phone.pressBack();
		phone.pressBack();
		sleepInt(2);
		phone.pressHome();
		sleepInt(2);
	}
	private void setUpAccount() throws UiObjectNotFoundException {
		launchApp(AppName.EMAIL);
		sleepInt(3);
		LeUiObject accountSet = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("帐户设置"));
		if(accountSet.exists()){
			addStep("设置邮箱账号");
			LeUiObject accountName = new LeUiObject(new UiSelector()
			.className("android.widget.EditText")
			.resourceId("com.android.email:id/account_email"));
			verify("账号不存在",accountName.exists());
			accountName.click();
			accountName.setText(getStrParams("email"));
			sleepInt(2);
			phone.pressEnter();
			LeUiObject accountpwd = new LeUiObject(new UiSelector()
			.className("android.widget.EditText")
			.resourceId("com.android.email:id/account_password"));
			verify("密码不存在",accountpwd.exists());
			accountpwd.setText(getStrParams("emailpwd"));
			sleepInt(2);
			LeUiObject type = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("com.android.email:id/account_type"));
			verify("类型不存在",type.exists());
			type.click();
			sleepInt(2);
			LeUiObject pop3 = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("android:id/le_bottomsheet_text").text("POP3"));
			LeUiObject IMAP = new LeUiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("android:id/le_bottomsheet_text").textContains("IMAP"));
			verify("IMAP没找到",IMAP.exists());
			IMAP.click();
			LeUiObject next = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("com.android.email:id/next"));
			sleepInt(2);
			verify("下一步不存在",next.exists());
			next.click();
			sleepInt(2);
			for(int i=0;i<3;i++){
				if(!next.exists()){
					sleepInt(1);
				}else{
					next.click();
					break;
				}
			}
			for(int i=0;i<10;i++){
				if(!next.exists()){
					sleepInt(1);
				}else{
					next.click();
					break;
				}
			}
			for(int i=0;i<10;i++){
				if(!next.exists()){
					sleepInt(1);
				}else{
					next.click();
				}
			}
			sleepInt(2);		
		}
		
		LeUiObject inbox = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").textContains("收件箱"));
		verify("未添加邮箱账号",inbox.exists());
		sleepInt(20);
		phone.pressBack();
		phone.pressBack();
		sleepInt(2);
		phone.pressHome();
		sleepInt(2);
	}
	
	@CaseName("发送邮件")
	public void testsendEmail() throws UiObjectNotFoundException, RemoteException {
		setUpAccount();
		clearAllBox();
		launchApp(AppName.EMAIL);
		addStep("进入邮箱");
		sleepInt(2);
		LeUiObject newMail = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/conversation_bottom_bar")
			.className("android.widget.LinearLayout").index(1)
			.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)));
		verify("Can't find new mail button.", newMail.exists());
		newMail.click();
		addStep("新建邮件");
		sleepInt(2);
		
		//LeUiObject recipient = new LeUiObject(new UiSelector()
		//	.resourceId("com.android.email:id/to_content"));
		LeUiObject recipient = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/edittext"));
		verify("没有找到收件人", recipient.exists());
		recipient.click();
		// **** only for single testing *****
		//recipient.setText("letvrdtestauto1@sina.com");
		// **** only for single testing *****
		System.out.print("****************************************");
		System.out.print(getStrParams("email"));
		System.out.print("****************************************");
		
		recipient.setText(getStrParams("email"));
		sleepInt(2);
		phone.pressEnter();
		addStep("填收件人");
		
		
		//LeUiObject subject = new LeUiObject(new UiSelector()
		//	.resourceId("com.android.email:id/subject_content"));
		LeUiObject subject = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/subject"));				
		verify("没有找到主题", subject.exists());
		subject.click();
		subject.setText("TestEmail");
		sleepInt(2);
		phone.pressEnter();
		addStep("填主题");
		
		
		//LeUiObject body = new LeUiObject(new UiSelector()
		//	.resourceId("com.android.email:id/body_wrapper"));
		LeUiObject body = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/body"));
		verify("没有找到正文", body.exists());
		body.click();
		body.setText("TestEmail");
		sleepInt(2);
		phone.pressEnter();
		addStep("填正文");
		
		sleepInt(2);
		LeUiObject send = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/send"));
		verify("没有找到发送", body.exists());
		send.click();
		addStep("发送邮件");
		sleepInt(10);
		int count = getSendCount();
		for (int i = 0; i < 40; i++) {
			if(count==1){
				break;
			}
			sleepInt(1);
		}
		if (!(count == 1)) {
			screenShot();
			fail("Didn't sent mail successfully.");
		}
		addStep("查看邮件是否发送成功");
		LeUiObject menu = new LeUiObject(new UiSelector()
		.className("android.widget.ImageButton").description("打开抽屉式导航栏"));
		verify("Can't find menu button.", menu.exists());
		menu.click();
		sleepInt(2);
		LeUiObject inbox = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.email:id/name").text("收件箱"));
		inbox.click();
		LeUiObject fresh = new LeUiObject(new UiSelector()
			.className("android.widget.LinearLayout").resourceId("com.android.email:id/conversation_bottom_bar")
			.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
					.childSelector(new UiSelector().className("android.widget.ImageView").index(0))));
		LeUiObject isReceive = new LeUiObject(new UiSelector()
		.className("android.widget.ListView")
		.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0))));
		for (int i = 0; i < 36; i++) {
			if(isReceive.exists()){
				System.out.print("****************************************");
				System.out.print("Email received!");
				System.out.print("****************************************");
				sleepInt(3);
				break;
			}else{
				System.out.print("****************************************");
				System.out.print("current wait loop:");
				System.out.print(i);
				System.out.print("****************************************");
				fresh.click();
				sleepInt(10);
			}
		}
		verify("没有收到发送的邮件",isReceive.exists());
		
		addStep("删除所有邮件");
		clearAllBox();
		addStep("退出邮箱");
		press_back(3);
		press_home(1);
	}
	
	@CaseName("发送带附件的邮件邮件")
	public void testsendEmailAttach() throws UiObjectNotFoundException, RemoteException {
		setUpAccount();
		clearAllBox();
		launchApp(AppName.EMAIL);
		addStep("进入邮箱");
		sleepInt(2);
		LeUiObject newMail = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/conversation_bottom_bar")
			.className("android.widget.LinearLayout").index(1)
			.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)));
		verify("Can't find new mail button.", newMail.exists());
		newMail.click();
		addStep("新建邮件");
		sleepInt(2);
		LeUiObject recipient = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/to_content"));
		verify("没有找到收件人", recipient.exists());
		recipient.click();
		recipient.setText(getStrParams("email"));
		sleepInt(2);
		phone.pressEnter();
		addStep("填收件人");
		LeUiObject subject = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/subject_content"));
		verify("没有找到主题", subject.exists());
		subject.click();
		subject.setText("TestEmail");
		sleepInt(2);
		phone.pressEnter();
		addStep("填主题");
		LeUiObject body = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/body_wrapper"));
		verify("没有找到正文", body.exists());
		body.click();
		body.setText("TestEmail");
		sleepInt(2);
		phone.pressEnter();
		addStep("填正文");
		sleepInt(2);
		LeUiObject attach = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/add_attachments_control"));
		verify("没有找到附件按钮", attach.exists());
		attach.click();
		sleepInt(3);
		LeUiObject photo = new LeUiObject(new UiSelector().textContains("相册"));
		verify("没有找到相册按钮", photo.exists());
		photo.click();
		sleepInt(2);
		LeUiObject res = new LeUiObject(new UiSelector().text("Resource"));
		verify("没有找到Resource相册", res.exists());
		res.click();
		sleepInt(5);
		phone.click(250, 350);
		addStep("填附件");
		sleepInt(5);
		LeUiObject attachNote = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/attachments_header").text("附件：共有1个附件"));
		verify("附件没有添加成功", attachNote.exists());
		addStep("验证附件是否添加成功");
		LeUiObject send = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/send"));
		verify("没有找到发送", body.exists());
		send.click();
		addStep("发送邮件");
		sleepInt(10);
		int count = getSendCount();
		if (!(count == 1)) {
			screenShot();
			fail("Can't sent mail");
		}
		
		addStep("查看邮件是否发送成功");
		LeUiObject menu = new LeUiObject(new UiSelector()
		.className("android.widget.ImageButton").description("打开抽屉式导航栏"));
		verify("Can't find menu button.", menu.exists());
		menu.click();
		sleepInt(2);
		LeUiObject inbox = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.email:id/name").text("收件箱"));
		inbox.click();
		LeUiObject fresh = new LeUiObject(new UiSelector()
			.className("android.widget.LinearLayout").resourceId("com.android.email:id/conversation_bottom_bar")
			.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
					.childSelector(new UiSelector().className("android.widget.ImageView").index(0))));
		LeUiObject isReceive = new LeUiObject(new UiSelector()
		.className("android.widget.ListView")
		.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0))));
		for (int j= 0; j < 18; j++) {
			if(isReceive.exists()){
				sleepInt(3);
				break;
			}else{
				fresh.click();
				sleepInt(10);
			}
		}
		verify("没有收到发送的邮件",isReceive.exists());
		addStep("删除所有邮件");
		clearAllBox();
		addStep("退出邮箱");
		press_back(3);
		press_home(1);
	}
	

	@CaseName("启动邮件应用")
	public void testopenEmail() throws UiObjectNotFoundException {
		addStep("Step1:启动邮箱应用");
		launchApp(AppName.EMAIL);
		sleepInt(2);
		LeUiObject emailTag = new LeUiObject(new UiSelector().packageName("com.android.email"));
		verify("没有成功进入邮箱",emailTag.exists());
		sleepInt(2);
		addStep("step2:home键后台应用，再次点击应用");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.EMAIL);
		sleepInt(5);
		verify("没有成功进入邮箱",emailTag.exists());
		addStep("step3:home键后台应用调用多任务，多任务中点击应用");
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
		sleepInt(3);
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
		verify("没有成功进入邮箱",emailTag.exists());
		addStep("退出应用");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

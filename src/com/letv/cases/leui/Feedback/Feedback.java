package com.letv.cases.leui.Feedback;


import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Feedback extends LetvTestCase {
	
	
	@CaseName("问题反馈")
	public void testFeedback() throws UiObjectNotFoundException {
		
		addStep("1.进入问题反馈");
		launchApp(AppName.FEEDBACK);
		sleepInt(3);
			
		LeUiObject FeedbackTag = new LeUiObject(new UiSelector().packageName("com.letv.android.bugreporter"));
		verify("没有成功进入问题反馈",FeedbackTag.exists());
		sleepInt(3);
		
		addStep("2.启动在线反馈，编辑内容，提交在线内容");
		LeUiObject OnLineType = new LeUiObject(new UiSelector()
				.resourceId("com.letv.android.bugreporter:id/online_layout")
				.packageName("com.letv.android.bugreporter"));
		verify("没有成功进入在线反馈",OnLineType.exists());
		OnLineType.click();
		sleepInt(5);
		
		LeUiObject QType = new LeUiObject(new UiSelector().resourceId("com.letv.android.bugreporter:id/preferenceView_bug_type"));
		verify("问题类型不存在",QType.exists());
		QType.click();
		sleepInt(2);
		LeUiObject chooseQType = new LeUiObject(new UiSelector().text("通话和短信"));
		verify("通话和短信选项不存在",chooseQType.exists());
		chooseQType.click();
		sleepInt(3);
		verify("通话和短信类型选择不成功",chooseQType.exists());
		LeUiObject inputContent = new LeUiObject(new UiSelector().resourceId("com.letv.android.bugreporter:id/et_description"));
		verify("问题描述不存在",inputContent.exists());
		inputContent.setText("测试用例提交，请忽略");
		sleepInt(3);
		LeUiObject addPic = new LeUiObject(new UiSelector().resourceId("com.letv.android.bugreporter:id/add_pic"));
		verify("添加图片的地方 不存在",addPic.exists());
		addPic.click();
		sleepInt(4);
		LeUiObject camera = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/menu_camera"));
		verify("未能进入到相册",camera.exists());
		camera.click();
		sleepInt(3);
		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/shutter_button"));
		verify("Can't find shutter button.", shutter.exists());
		shutter.click();
		sleepInt(6);
		press_back(1);
		sleepInt(3);
		LeUiObject myCamera = new LeUiObject(new UiSelector().text("我的相机"));
		myCamera.click();
		sleepInt(3);
		phone.click(138, 356);
		sleepInt(3);
		LeUiObject inputPhoneNo = new LeUiObject(new UiSelector().resourceId("com.letv.android.bugreporter:id/let_contact"));
		inputPhoneNo.setText("13904741111");
		sleepInt(3);
		LeUiObject save = new LeUiObject(new UiSelector().text("提交"));
		verify("提交按钮不存在",save.exists());
		save.click();
		sleepInt(4);
		LeUiObject leAccount = new LeUiObject(new UiSelector().packageName("com.letv.android.account"));
		if(leAccount.exists()){
			addStep("进入乐视账号");
			LeUiObject logout = new LeUiObject(new UiSelector()
			.resourceId("com.letv.android.account:id/button1").text("退出账号"));
			if(logout.exists()){
				System.out.println("账号已经登录");
				return;
			}
			LeUiObject LeUI = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.letv.android.account:id/account_img"));
			sleepInt(5);
			verify("没有进入乐视账号登陆界面", LeUI.exists());
			// 手机号/邮箱
			LeUiObject useExistAccount = new LeUiObject(new UiSelector().textContains("使用已有"));
			if(useExistAccount.exists()){
				useExistAccount.click();
				sleepInt(2);
			}
			LeUiObject account = new LeUiObject(new UiSelector()
					.className("com.letv.leui.widget.LeEditText").text("手机号/邮箱"));
			LeUiObject clear = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"android:id/le_editor_clear_btn").index(1));
			if (clear.exists()) {
				clear.click();
				sleepInt(3);
			}
			verify("输入账号的地方不存在", account.exists());
			account.click();
			account.setText(getStrParams("LetvUser1"));
			sleepInt(2);
			phone.pressEnter();
			LeUiObject pwd = new LeUiObject(new UiSelector().className(
					"com.letv.leui.widget.LeTitleEditText").resourceId(
					"com.letv.android.account:id/password"));
			pwd.setText(getStrParams("LetvPWD1"));
			sleepInt(2);
			phone.pressEnter();
			LeUiObject login = new LeUiObject(new UiSelector()
					.className("android.widget.Button")
					.resourceId("com.letv.android.account:id/btn_login")
					.text("登    录"));
			verify("登录按钮不存在",login.exists());
			login.click();
			
			for (int i = 0; i < 20; i++) {
				if (login.exists()) {
					sleepInt(5);
				} else {
					break;
				}
			}
			verify("登陆乐视账号失败", !login.exists());
		}
		
		sleepInt(3);
		addStep("退出");
	}
	
	@CaseName("启动问题反馈应用")
	public void testOpenFeedback() throws UiObjectNotFoundException {
		
		addStep("启动问题反馈应用");
		launchApp(AppName.FEEDBACK);
		sleepInt(2);
		LeUiObject FeedbackTag = new LeUiObject(new UiSelector().packageName("com.letv.android.bugreporter"));
		verify("没有成功进入问题反馈",FeedbackTag.exists());
		sleepInt(2);
		addStep("step2:home键后台，再次点击");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.FEEDBACK);
		sleepInt(3);
		verify("没有成功进入问题反馈",FeedbackTag.waitForExists(10));
		addStep("step3:home键后台调用多任务，多任务中点击");
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
		verify("没有成功进入问题反馈",FeedbackTag.exists());
		addStep("退出应用");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

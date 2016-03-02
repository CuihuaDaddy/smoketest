package com.letv.cases.leui.LeStore;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class LeStore extends LetvTestCase {
	@CaseName("启动乐视商城应用,乐视账号登录,浏览商品，退出乐视商城")
	public void testLeStore() throws UiObjectNotFoundException {
		launchApp(AppName.LESTORE);
		sleepInt(5);
		LeUiObject letvshopTag = new LeUiObject(new UiSelector()
			.packageName("com.letv.letvshop"));
		verify("未能成功进入乐视商城",letvshopTag.exists());
		sleepInt(3);
		addStep("step1:启动乐视商城");
		LeUiObject welcomePage = new LeUiObject(new UiSelector()
		.resourceId("com.letv.letvshop:id/lsvp_welcome_use"));
		if (welcomePage.exists()) {
			phone.swipe(1050, 800, 50, 800, 10);
			sleepInt(2);
			phone.swipe(1050, 800, 50, 800, 10);
			sleepInt(2);
			phone.swipe(1050, 800, 50, 800, 10);
			sleepInt(2);
			phone.click(553,1671);
		}
		
		sleepInt(2);
		addStep("step2:登陆乐视商城");
		LeUiObject my = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").text("我的"));
		verify("没有进入到乐视商城",my.exists());
		my.click();
		sleepInt(2);
		addStep("step３:输入用户名密码");
		LeUiObject loginNow = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").resourceId("com.letv.letvshop:id/welcome_login_tv"));
		if (loginNow.exists()) {
			loginNow.click();
			sleepInt(2);
			
			LeUiObject LeUI = new LeUiObject(new UiSelector().textContains("已有"));
			//sleepInt(5);
			if(LeUI.exists()){
				LeUI.clickAndWaitForNewWindow();
				sleepInt(2);
			}
				// 手机号/邮箱
				LeUiObject account = new LeUiObject(new UiSelector().resourceId("com.letv.android.account:id/username"));
				LeUiObject clear = new LeUiObject(new UiSelector().className(
						"android.widget.ImageView").resourceId(
						"android:id/le_editor_clear_btn").index(1));
				if (clear.exists()) {
					clear.click();
					sleepInt(3);
				}
				verify("输入账号的地方不存在", account.exists());
				account.click();
				account.clearTextField();
				sleepInt(1);
				//account.setText(getStrParams("LetvUser1"));
				//account.setText("13718300737");
				account.setText(getStrParams("LetvUser1"));
				sleepInt(2);
				phone.pressEnter();
				LeUiObject pwd = new LeUiObject(new UiSelector().resourceId(
						"com.letv.android.account:id/password").childSelector(new UiSelector().className("com.letv.leui.widget.LeEditText")));
				verify("输入密码不存在", pwd.exists());
				pwd.click();
				pwd.clearTextField();
				sleepInt(1);
				//pwd.setText(getStrParams("LetvPWD1"));
				//pwd.setText("123456");
				pwd.setText(getStrParams("LetvPWD1"));
				sleepInt(2);
				phone.pressEnter();
				LeUiObject login = new LeUiObject(new UiSelector()
						.className("android.widget.Button")
						.resourceId("com.letv.android.account:id/btn_login")
						.text("登    录"));
				verify("登录按钮不存在",login.exists());
				login.click();
				sleepInt(3);
				LeUiObject unlockPasswd = new LeUiObject(new UiSelector().className(
						"com.letv.leui.widget.LeEditText"));
				if(unlockPasswd.exists()){
					unlockPasswd.setText(getStrParams("LetvPWD1"));
					sleepInt(1);
					LeUiObject unlock = new LeUiObject(new UiSelector().className(
							"android.widget.TextView").text("解绑"));
					verify("解绑不存在", unlock.exists());
					unlock.click();
					sleepInt(3);
				}
				LeUiObject continueStep = new LeUiObject(new UiSelector().resourceId("android:id/le_bottom_layout1_btn")
						.text("继续"));
				for (int i = 0; i < 30; i++) {
					if (continueStep.exists()) {
						break;
					} else {
						sleepInt(1);
					}
				}
				if(continueStep.exists()){
					continueStep.click();
					sleepInt(2);
				}		
			
		}
		addStep("step４:进入分类");
		LeUiObject item = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("分类"));
		for (int i = 0; i < 30; i++) {
			sleepInt(1);
			if(item.exists()){
				break;
			}
		}
		verify("分类选项不存在",item.exists());
		item.click();
		
		LeUiObject letv = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").textContains("超级电视"));
		for(int i =0;i<30;i++){
			if(letv.exists()){
				break;
			}
			sleepInt(1);
		}
		verify("超级电视不存在",letv.exists());
	}
}

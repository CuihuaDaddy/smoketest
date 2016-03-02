package com.letv.cases.leui.LeAccount;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class LeAccount extends LetvTestCase {
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

	@CaseName("账号登录")
	public void testLoginAccount() throws UiObjectNotFoundException {
		
		launchApp(AppName.LEACCOUNT2);
		sleepInt(2);
		addStep("step1:进入乐视账号");
		LeUiObject myService = new LeUiObject(new UiSelector().textContains("我的服务"));
		LeUiObject loginAccount = new LeUiObject(new UiSelector().className("android.widget.TextView").textContains("登录"));
		LeUiObject logout = new LeUiObject(new UiSelector().resourceId("com.letv.android.account:id/button1").textContains("退出"));
		for (int i = 0; i < 2; i++) {
		  if (!logout.exists()) {
			   phone.swipe(700, 1200, 700, 200, 30);
			   sleepInt(2);
		     } else {
			    break;
		     }
	    }
		if (logout.exists()){
			System.out.println("账号已经登录");
			logout.click();/////new added
			sleepInt(2);
			String letvpassword = getStrParams("LetvPWD1");
			System.out.println("input text "+letvpassword);
			callShell("input text "+letvpassword);
			System.out.println("input text "+letvpassword);
			//pwd.setText(getStrParams("LetvPWD1"));
			sleepInt(2);
			LeUiObject OK = new LeUiObject(new UiSelector().className(
					"android.widget.Button").resourceId("android:id/button1"));
			//verify("OK按钮不存在",OK.exists());
			OK.click();
			//sleepInt(10);
			LeUiObject deleteinfo = new LeUiObject(new UiSelector().text("从手机上删除"));
			for (int i = 0; i < 30; i++) {
				if (deleteinfo.exists()) {
					break;
				} else {
					sleepInt(1);
				}
			}
			verify("从手机上删除字段不存在",deleteinfo.exists());
			deleteinfo.click();
			LeUiObject deleteCloud = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_btn_1").textContains("直接退出账号"));
			sleepInt(4);
			if (deleteCloud.exists()) {
				deleteCloud.click();
				sleepInt(2);		
			}
			return;
		
		}else if(myService.exists()){
			swipe_screen((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8), (int)(phone.getDisplayWidth()*0.5),
					(int)(phone.getDisplayHeight()*0.2), 30);
			System.out.println("账号已经登录");
			return;
		}
		
		if(loginAccount.exists()){
			loginAccount.click();
			sleepInt(2);	
			// 手机号/邮箱
			LeUiObject useExistAccount = new LeUiObject(new UiSelector().textContains("已有"));
			if(useExistAccount.exists()){
				useExistAccount.clickAndWaitForNewWindow();
				sleepInt(2);
			}
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
			account.setText(getStrParams("LetvUser1"));
			sleepInt(2);
			phone.pressEnter();
			LeUiObject pwd = new LeUiObject(new UiSelector().resourceId(
					"com.letv.android.account:id/password").childSelector(new UiSelector().className("com.letv.leui.widget.LeEditText")));
			verify("输入密码不存在", pwd.exists());
			pwd.click();
			pwd.clearTextField();
			sleepInt(1);
			System.out.println("************debug**************");
			pwd.setText(getStrParams("LetvPWD1"));
			sleepInt(2);
			phone.pressEnter();
			LeUiObject login = new LeUiObject(new UiSelector()
					.className("android.widget.Button")
					.resourceId("com.letv.android.account:id/btn_login")
					.text("登    录"));
			verify("登录按钮不存在",login.exists());
			login.click();
			sleepInt(6);
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
				if (myService.exists()||continueStep.exists()) {
					break;
				} else {
					sleepInt(1);
				}
			}
			if(continueStep.exists()){
				continueStep.click();
				sleepInt(2);
			}
			if(!logout.exists()){
				swipe_screen((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8), (int)(phone.getDisplayWidth()*0.5),
						(int)(phone.getDisplayHeight()*0.2), 30);
			}
	        verify("登陆乐视账号失败", logout.exists());
		}		
		
		
		addStep("退出乐视账号");
		logout.click();
		sleepInt(2);
		//LeUiObject pwd = new LeUiObject(new UiSelector().
				//className("android.widget.LinearLayout").index(0).childSelector(new UiSelector().
				//className("com.letv.leui.widget.LeTitleEditText").resourceId("com.letv.android.account:id/password").index(1).childSelector(new UiSelector().
				//className("android.widget.LinearLayout").resourceId("android:id/le_editor_frame").index(0).childSelector(new UiSelector().
				//className("android.widget.LinearLayout").resourceId("android:id/le_editor_plate").index(0).childSelector(new UiSelector().
				//className("com.letv.leui.widget.LeEditText").resourceId("android:id/le_edit_text_content").index(0))))));
		String letvpassword = getStrParams("LetvPWD1");
		
		callShell("input text "+letvpassword);
		//pwd.setText(getStrParams("LetvPWD1"));
		sleepInt(2);
		LeUiObject OK = new LeUiObject(new UiSelector().className(
				"android.widget.Button").resourceId("android:id/button1"));
		//verify("OK按钮不存在",OK.exists());
		OK.click();
		//sleepInt(10);
		LeUiObject deleteinfo = new LeUiObject(new UiSelector().text("从手机上删除"));
		for (int i = 0; i < 30; i++) {
			if (deleteinfo.exists()) {
				break;
			} else {
				sleepInt(1);
			}
		}
		verify("从手机上删除字段不存在",deleteinfo.exists());
		deleteinfo.click();
		LeUiObject deleteCloud = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_btn_1").textContains("直接退出账号"));
		sleepInt(4);
		if (deleteCloud.exists()) {
			deleteCloud.click();
			sleepInt(2);		
		}
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		
	}
	
public void testLoginAccount2() throws UiObjectNotFoundException {
		
		launchApp("我的");
		sleepInt(2);
		addStep("step1:进入乐视账号");
		LeUiObject myService = new LeUiObject(new UiSelector().textContains("我的服务"));
		LeUiObject loginAccount = new LeUiObject(new UiSelector().className("android.widget.TextView").textContains("登录"));
		LeUiObject logout = new LeUiObject(new UiSelector().resourceId("com.letv.android.account:id/button1").textContains("退出"));
		for (int i = 0; i < 2; i++) {
		  if (!logout.exists()) {
			   phone.swipe(700, 1200, 700, 200, 30);
			   sleepInt(2);
		     } else {
			    break;
		     }
	    }
		if (logout.exists()){
			System.out.println("账号已经登录");
			logout.click();/////new added
			sleepInt(2);
			String letvpassword = getStrParams("LetvPWD1");
			System.out.println("input text "+letvpassword);
			callShell("input text "+letvpassword);
			System.out.println("input text "+letvpassword);
			//pwd.setText(getStrParams("LetvPWD1"));
			sleepInt(2);
			LeUiObject OK = new LeUiObject(new UiSelector().className(
					"android.widget.Button").resourceId("android:id/button1"));
			//verify("OK按钮不存在",OK.exists());
			OK.click();
			//sleepInt(10);
			LeUiObject deleteinfo = new LeUiObject(new UiSelector().text("从手机上删除"));
			for (int i = 0; i < 30; i++) {
				if (deleteinfo.exists()) {
					break;
				} else {
					sleepInt(1);
				}
			}
			verify("从手机上删除字段不存在",deleteinfo.exists());
			deleteinfo.click();
			LeUiObject deleteCloud = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_btn_1").textContains("直接退出账号"));
			sleepInt(4);
			if (deleteCloud.exists()) {
				deleteCloud.click();
				sleepInt(2);		
			}
			return;
		
		}else if(myService.exists()){
			swipe_screen((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8), (int)(phone.getDisplayWidth()*0.5),
					(int)(phone.getDisplayHeight()*0.2), 30);
			System.out.println("账号已经登录");
			return;
		}
		
		if(loginAccount.exists()){
			loginAccount.click();
			sleepInt(2);	
			// 手机号/邮箱
			LeUiObject useExistAccount = new LeUiObject(new UiSelector().textContains("已有"));
			if(useExistAccount.exists()){
				useExistAccount.clickAndWaitForNewWindow();
				sleepInt(2);
			}
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
			account.setText(getStrParams("LetvUser1"));
			sleepInt(2);
			phone.pressEnter();
			LeUiObject pwd = new LeUiObject(new UiSelector().resourceId(
					"com.letv.android.account:id/password").childSelector(new UiSelector().className("com.letv.leui.widget.LeEditText")));
			verify("输入密码不存在", pwd.exists());
			pwd.click();
			pwd.clearTextField();
			sleepInt(1);
			System.out.println("************debug**************");
			pwd.setText(getStrParams("LetvPWD1"));
			sleepInt(2);
			phone.pressEnter();
			LeUiObject login = new LeUiObject(new UiSelector()
					.className("android.widget.Button")
					.resourceId("com.letv.android.account:id/btn_login")
					.text("登    录"));
			verify("登录按钮不存在",login.exists());
			login.click();
			sleepInt(6);
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
				if (myService.exists()||continueStep.exists()) {
					break;
				} else {
					sleepInt(1);
				}
			}
			if(continueStep.exists()){
				continueStep.click();
				sleepInt(2);
			}
			if(!logout.exists()){
				swipe_screen((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8), (int)(phone.getDisplayWidth()*0.5),
						(int)(phone.getDisplayHeight()*0.2), 30);
			}
	        verify("登陆乐视账号失败", logout.exists());
		}		
		
		
		addStep("退出乐视账号");
		logout.click();
		sleepInt(2);
		//LeUiObject pwd = new LeUiObject(new UiSelector().
				//className("android.widget.LinearLayout").index(0).childSelector(new UiSelector().
				//className("com.letv.leui.widget.LeTitleEditText").resourceId("com.letv.android.account:id/password").index(1).childSelector(new UiSelector().
				//className("android.widget.LinearLayout").resourceId("android:id/le_editor_frame").index(0).childSelector(new UiSelector().
				//className("android.widget.LinearLayout").resourceId("android:id/le_editor_plate").index(0).childSelector(new UiSelector().
				//className("com.letv.leui.widget.LeEditText").resourceId("android:id/le_edit_text_content").index(0))))));
		String letvpassword = getStrParams("LetvPWD1");
		
		callShell("input text "+letvpassword);
		//pwd.setText(getStrParams("LetvPWD1"));
		sleepInt(2);
		LeUiObject OK = new LeUiObject(new UiSelector().className(
				"android.widget.Button").resourceId("android:id/button1"));
		//verify("OK按钮不存在",OK.exists());
		OK.click();
		//sleepInt(10);
		LeUiObject deleteinfo = new LeUiObject(new UiSelector().text("从手机上删除"));
		for (int i = 0; i < 30; i++) {
			if (deleteinfo.exists()) {
				break;
			} else {
				sleepInt(1);
			}
		}
		verify("从手机上删除字段不存在",deleteinfo.exists());
		deleteinfo.click();
		LeUiObject deleteCloud = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_btn_1").textContains("直接退出账号"));
		sleepInt(4);
		if (deleteCloud.exists()) {
			deleteCloud.click();
			sleepInt(2);		
		}
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		
	}
}

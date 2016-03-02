package com.letv.cases.leui.LetvSport;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class LetvSport extends LetvTestCase {
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
	
	@CaseName("启动乐视体育应用,观看5大联赛,查看联赛资讯或数据,退出乐视体育")
	public void testLetvSport() throws UiObjectNotFoundException {
		launchApp(AppName.LESPORT);
		//launchApp("体育");
		//launchApp("com.lesports.glivesports");
		LeUiObject iterm = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("同意并继续"));
		if(iterm.exists()){
			iterm.click();
		}
		sleepInt(6);
		LeUiObject livetelecast = new LeUiObject(new UiSelector()
		.resourceId("com.lesports.glivesports:id/toolbar_title").text("直播"));
		verify("没有成功进入到乐视体育中",livetelecast.exists());
		/*addStep("step1:启动应用乐视体育应用");
		livetelecast.click();
		sleepInt(5);
		LeUiObject center = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("直播中心"));
		verify("没有成功进入到直播中心模块",center.exists());
		LeUiObject team = new LeUiObject(new UiSelector().resourceId("com.letv.android.sports:id/match_teams"));
		verify("没有赛事",team.exists());
		team.exists();
		LeUiObject LeUI = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.letv.android.account:id/account_img"));
		if(LeUI.exists()){
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
			LeUiObject logout = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.letv.android.account:id/button1").text("退出账号"));
			for (int i = 0; i < 20; i++) {
				if (!logout.exists()) {
					sleepInt(5);
				} else {
					break;
				}
			}
			verify("登陆乐视账号失败", logout.exists());
		}
			
		*/
		addStep("step1:退出乐视体育");
		press_back(3);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	@CaseName("启动乐视体育应用, 退出乐视体育")
	public void testLaunchLetvSport() throws UiObjectNotFoundException {
		//launchApp(AppName.LESPORT);
		//launchApp("体育");
		launchAppByPackage("com.lesports.glivesports/.LeLicenceDialogActivity");
		LeUiObject iterm = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("同意并继续"));
		if(iterm.exists()){
			iterm.click();
		}
		sleepInt(6);
		LeUiObject cancle = new LeUiObject(new UiSelector().resourceId(
				"com.lesports.glivesports:id/le_bottomsheet_default_cancel"));
		if(cancle.exists()){
			cancle.click();
		}
		sleepInt(2);
		
		LeUiObject ignore = new LeUiObject(
				new UiSelector().resourceId("com.lesports.glivesports:id/btn_ignore"));
		if(ignore.exists()){
			ignore.click();
		}
		sleepInt(2);
		
		LeUiObject livetelecast = new LeUiObject(new UiSelector()
		.resourceId("com.lesports.glivesports:id/toolbar_title").text("直播"));
		verify("没有成功进入到乐视体育中",livetelecast.exists());
		
		
		addStep("step3:退出乐视体育");
		press_back(3);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

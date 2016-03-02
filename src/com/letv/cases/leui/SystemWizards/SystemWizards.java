package com.letv.cases.leui.SystemWizards;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

import android.os.RemoteException;

public class SystemWizards extends LetvTestCase {
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
	}

	@Override
	protected void tearDown() throws Exception 
	{
		// TODO Auto-generated method stub
		/*
		for (int i = 0; i < 8; i++) {
			phone.click(469,1721);
			sleepInt(1);
			phone.click(946,141);
			sleepInt(1);
			phone.click(812,1608);
			sleepInt(1);
		}
		*/
		press_back(3);
		super.tearDown();
		
	}
	
	@CaseName("系统刷机开机后向导设置")
	public void testSystemWizards() throws UiObjectNotFoundException 
	{	
		normal_OOBE();
		
		setAutoEnvironment();
		
		setScreenLock();
		
		enableDeveloperMode(); 
		
	}
	@CaseName("系统刷机开机后允许安装软件")
	public void testAllowInstall() throws UiObjectNotFoundException 
	{	
		checkBackgroundInstall();
		
		
	}
	
	/*
	private void enableMTP() throws UiObjectNotFoundExeception
	{
		phone.swipe(700, 0, 700, 2500, 10);
		
		LeUiObject usb_option = new LeUiObject(new UiSelector()
			.className("android.widget.LinearLayout").index(2)
			.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
			.childSelector(new UiSelector().className("android.widget.TextView").index(0)
			.resourceId("android:id/title").textContains("USB"))));
		verify("USB option not exsits",usb_option.exists());		
		int dx_usb_option = usb_option.getBounds().centerX();
		int dy_usb_option = usb_option.getBounds().centerY();
		phone.click(dx_usb_option, dy_usb_option);
		sleepInt(2);
		
		LeUiObject mtp_option = new LeUiObject(new UiSelector()
			.className("android.widget.LinearLayout").index(1)
			.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
			.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
			.childSelector(new UiSelector().className("android.widget.TextView").index(0)
			.resourceId("android:id/title").textContains("MTP")))));
		verify("MTP option not exsits",mtp_option.exists());		
		int dx_mtp_option = mtp_option.getBounds().centerX();
		int dy_mtp_option = mtp_option.getBounds().centerY();
		phone.click(dx_mtp_option, dy_mtp_option);
		sleepInt(2);		
	}
	*/
	
	private void normal_OOBE() throws UiObjectNotFoundException
	{
		//checkBackgroundInstall();
		addStep("step 1 => 选择系统语言");
		/*LeUiObject language = new LeUiObject(new UiSelector()
		.className("android.widget.TextView")
		.resourceId("com.letv.android.setupwizard:id/title_language").text("LANGUAGE"));
		LeUiObject English = new LeUiObject(new UiSelector()
		.className("com.letv.leui.widget.LeRadioButton")
		.resourceId("com.letv.android.setupwizard:id/language_english").text("English"));
		verify(language.exists());
		verify(English.exists());
		English.click();*/
		try {
			if (!phone.isScreenOn()) {
				phone.wakeUp();
				sleepInt(1);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LeUiObject chinese = new LeUiObject(new UiSelector()
		.className("com.letv.leui.widget.LeRadioButton").text("中文简体"));
		verify("未能进入到选择语言项",chinese.exists());
		chinese.click();
		sleepInt(2);
		LeUiObject Next = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("继续"));
		verify("未能发现继续按钮",Next.exists());
		Next.click();
		sleepInt(2);
		
		addStep("step 2 => 检查sim卡，开启数据流量");
		LeUiObject checksim = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").textContains("检测SIM卡"));
		verify("未能到检查sim卡界面",checksim.exists());
		/*LeUiObject opendata = new LeUiObject(new UiSelector()
		.className("com.letv.leui.widget.LeSwitch")
		.resourceId("com.letv.android.setupwizard:id/enablemobiledata").checkable(true));
		verify("打开数据按钮的button不存在",opendata.exists());
		if(!opendata.isChecked())
		{
			opendata.click();
		}*/
		verify("继续按钮不存在",Next.exists());
		sleepInt(2);
		Next.click();
		sleepInt(2);
		checkLetAccount();
		
		
		addStep("step 3 => 选择wifi,跳过此项");
		LeUiObject selectwifi = new LeUiObject(new UiSelector().className("android.widget.TextView")
		.text("选择无线网络"));
		verify("未能发现选择wifi的页面",selectwifi.exists());
		LeUiObject Skip = new LeUiObject(new UiSelector()
		.className("android.widget.TextView")
		.resourceId("com.android.settings:id/footer_center_button").text("跳过"));
		verify("跳过按钮不存在",Skip.exists());
		Skip.click();
		sleepInt(2);
		checkLetAccount();
		
		
		addStep("step 4 => 使用条款,同意并且继续");
		LeUiObject UsageTerms = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").textContains("使用条款"));
		verify("未能进入到使用条款界面",UsageTerms.exists());
		LeUiObject agreed = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("同意并继续"));
		verify("未能找到同意并继续的按钮",agreed.exists());
		agreed.click();
		sleepInt(2);
		checkLetAccount();
		
				
		addStep("step 5 => 其他服务，选择默认");
		LeUiObject otherservices = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").textContains("其他服务"));
		verify("其他服务页面不存在",otherservices.exists());
		verify("未能发现继续按钮",Next.exists());
		Next.click();
		sleepInt(2);
		checkLetAccount();
		
		
		addStep("step 6 => Finger prints");
		LeUiObject skip_btn = new LeUiObject(new UiSelector()
				.className("android.widget.LinearLayout").index(4)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.TextView").index(0)
					.resourceId("com.letv.android.setupwizard:id/footer_center_button")))));
		verify("Finger prints not exsits",skip_btn.exists());		
		int dx_skip_btn = skip_btn.getBounds().centerX();
		int dy_skip_btn = skip_btn.getBounds().centerY();
		phone.click(dx_skip_btn, dy_skip_btn);
		sleepInt(2);
		checkLetAccount();
		
		
		addStep("step 7 => Size of Screen View");
		LeUiObject continue_btn = new LeUiObject(new UiSelector()
				.className("android.widget.LinearLayout").index(3)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.TextView").index(0)
					.resourceId("com.letv.android.setupwizard:id/footer_center_button")))));
		verify("Finger prints not exsits",continue_btn.exists());		
		int dx_continue_btn = continue_btn.getBounds().centerX();
		int dy_continue_btn = continue_btn.getBounds().centerY();
		phone.click(dx_continue_btn, dy_continue_btn);
		sleepInt(2);
		checkLetAccount();
		
		
		addStep("step 8 => Letv Hello Screen");
		LeUiObject final_confirm_btn = new LeUiObject(new UiSelector()
				.className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.TextView").index(0)
					.resourceId("com.letv.android.setupwizard:id/footer_center_button")))));
		verify("Finger prints not exsits",final_confirm_btn.exists());		
		int dx_final_confirm_btn = final_confirm_btn.getBounds().centerX();
		int dy_final_confirm_btn = final_confirm_btn.getBounds().centerY();
		phone.click(dx_final_confirm_btn, dy_final_confirm_btn);
		sleepInt(10);
		
		/*addStep("step 9 => Skip SIM contacts import");
		LeUiObject sim_contact_skip = new LeUiObject(new UiSelector()
				.className("android.widget.Button").resourceId("android:id/le_bottomsheet_default_cancel"));
		if (sim_contact_skip.exists())
		{
			sim_contact_skip.click();
			sleepInt(4);				
		}
		
		for (int i = 0; i < 10; i++) {
			if (sim_contact_skip.exists() && !(sim_contact_skip.isChecked()) ) 
			{
				addStep("step 9 => skip click");
				sim_contact_skip.click();	
				sleepInt(1);
				break;
			} 
			else 
			{
				addStep("step 9 => sleep");
				sleepInt(1);
				
			}
		}*/
		
		/*
		addStep("step 10 => Skip Notification");
		LeUiObject known_btn = new LeUiObject(new UiSelector()
				.className("android.widget.ImageView").resourceId("com.letv.android.quicksearchbox:id/btn_known"));
		if (known_btn.exists())
		{
			known_btn.click();
			sleepInt(4);				
		}
		*/
		
		
	}
	
	private void checkBackgroundInstall() throws UiObjectNotFoundException
	{
		addStep("step 0000 => 允许通过后台安装");
		try {
			if (!phone.isScreenOn()) {
				phone.wakeUp();
				sleepInt(1);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LeUiObject letvCheckBox = new LeUiObject(
				new UiSelector().className("com.letv.leui.widget.LeCheckBox").index(0)
				.resourceId("android:id/le_bottomsheet_switchbutton_checkbox").text("不再提示"));
		LeUiObject letvConfirmButton = new LeUiObject(
				new UiSelector().className("android.widget.Button").index(3)
				.resourceId("android:id/le_bottomsheet_btn_confirm_5").text("允许"));
		addStep("step 1 => install");
		callShell("pm install -r /data/local/tmp/Utf7Ime.apk");
		addStep("step 2 => install");
		for (int i = 0; i < 60; i++) {
			if (letvCheckBox.exists() && !(letvCheckBox.isChecked()) ) 
			{
				addStep("step 3 => chekbox click");
				letvCheckBox.click();	
				addStep("step 4 => confirm click");
				//sleepInt(1);
				letvConfirmButton.click();	
				//sleepInt(1);
				break;
			} 
			else 
			{
				addStep("step 5 => sleep");
				sleepInt(1);
				
			}
		}
		
		
		
		//for (int i = 0; i < 60; i++) {
			//if (! letvConfirmButton.exists()  ) 
			//{
				//sleepInt(1);
			//} 
			//else 
			//{
			//	letvConfirmButton.click();	
			//	break;
			//}
		//}
	}
	
	private void checkLetAccount() throws UiObjectNotFoundException
	{
		addStep("step 00 => 跳过输入乐视账号");
		LeUiObject letvAccount = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").textContains("乐视帐号"));
		if(letvAccount.exists())
		{
			verify("未能到达输入乐视账号界面",letvAccount.exists());
			LeUiObject skipStep = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").textContains("跳过"));
			verify("未能找到跳过此步的按钮",skipStep.exists());
			skipStep.click();
			sleepInt(2);
			LeUiObject confirm = new LeUiObject(new UiSelector()
			.className("android.widget.Button").resourceId("android:id/le_bottomsheet_default_confirm").index(2).text("跳过"));
			verify("未能发现跳过按钮",confirm.exists());
			confirm.click();
			sleepInt(4);
		}
	}
	
	
	
	private void setAutoEnvironment() throws UiObjectNotFoundException
	{
		addStep("step 11 => 允许手机安装第三方应用");
		launchApp(AppName.SETTING);
		sleepInt(2);
		UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().resourceId("android:id/decor_content_parent").packageName("com.android.settings")));	
		LeUiObject security = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("密码安全"));
		verify("没有进入到设置中",settingPanel.exists());
		settingPanel.setAsVerticalList();
		settingPanel.scrollBackward();
		settingPanel.scrollBackward();
		for (int i = 0; i < 10; i++) {
			if (!security.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}
		verify("没有找到密码安全", security.exists());
		security.click();
		sleepInt(2);
		LeUiObject source = new LeUiObject(new UiSelector().text("未知来源"));
		verify("未找到未知来源",source.exists());
		source.click();
		sleepInt(4);
		LeUiObject sure = new LeUiObject(new UiSelector()
		.className("android.widget.Button").text("确定"));
		verify("未找到确定",sure.exists());
		sure.click();
		sleepInt(2);
		
		addStep("step 12 => 后台安装应用");
		LeUiObject backStage = new LeUiObject(new UiSelector().text("后台安装应用"));
		verify("未找到后台安装应用",backStage.exists());
		backStage.click();
		sleepInt(1);
		LeUiObject allow = new LeUiObject(new UiSelector().text("允许"));
		verify("未找到允许",allow.exists());
		allow.click();
		sleepInt(3);
		
		addStep("step 13 => 辅助功能-锁屏防误触");
		press_back(1);
		verify("没有进入到设置中",settingPanel.exists());
		settingPanel.setAsVerticalList();
		settingPanel.scrollBackward();
		settingPanel.scrollBackward();
		LeUiObject auxiliary = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("辅助功能"));
		for (int i = 0; i < 5; i++) {
			if (!auxiliary.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}
		verify("没有找到辅助功能", auxiliary.exists());
		auxiliary.click();
		sleepInt(2);
		LeUiObject lockscreen = new LeUiObject(new UiSelector().text("锁屏防误触"));
		verify("未找到锁屏防触控",lockscreen.exists());
		lockscreen.click();
		sleepInt(2);
	}
	private void setScreenLock() throws UiObjectNotFoundException
	{
		addStep("设置锁屏时间");
		launchApp(AppName.SETTING);
		sleepInt(2);
		UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().resourceId("android:id/decor_content_parent").packageName("com.android.settings")));	
		LeUiObject show = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("显示"));
		verify("没有进入到设置中",settingPanel.exists());
		settingPanel.setAsVerticalList();
		settingPanel.scrollBackward();
		settingPanel.scrollBackward();
		for (int i = 0; i < 10; i++) {
			if (!show.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}
		verify("没有找到显示", show.exists());
		show.click();
		sleepInt(2);
		LeUiObject screenlock = new LeUiObject(new UiSelector().text("休眠"));
		verify("未找到休眠",screenlock.exists());
		screenlock.click();
		sleepInt(1);
		UiScrollable screenlockpanel = new UiScrollable(new UiSelector()
				.className("android.widget.FrameLayout")
				.packageName("com.android.settings")
				.childSelector(
						new UiSelector().resourceId("android:id/content").packageName("com.android.settings")));	
		LeUiObject locktime = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("30分钟"));
		verify("没有进入到休眠中",screenlockpanel.exists());
		phone.swipe(700, 2000, 700, 1700, 10);
		verify("没有找到30分钟", locktime.exists());
		locktime.click();
		sleepInt(2);
		
	}
		
	private void enableDeveloperMode() throws UiObjectNotFoundException
	{
		addStep("step 14 => enable developer mode");
		launchApp(AppName.SETTING);
		sleepInt(2);
		UiScrollable settingPanel = new UiScrollable(new UiSelector()
			.className("android.widget.FrameLayout")
			.packageName("com.android.settings")
			.childSelector(new UiSelector().resourceId("android:id/decor_content_parent")
					.packageName("com.android.settings")));
		verify("没有进入到设置中",settingPanel.exists());
		settingPanel.setAsVerticalList();
		settingPanel.scrollBackward();
		settingPanel.scrollBackward();
		LeUiObject about_phone = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("关于手机"));
		for (int i = 0; i < 5; i++) 
		{
			if (!about_phone.exists()) 
			{
				settingPanel.scrollForward();
			} 
			else 
			{
				break;
			}
		}
		verify("没有找到关于手机", about_phone.exists());
		about_phone.click();		
		sleepInt(4);
		addStep("disable user feedback");
		LeUiObject user_feedback = new LeUiObject(new UiSelector()
				.className("com.letv.leui.widget.LeSwitch").resourceId("android:id/switchWidget").index(0));
		verify("没有找到用户体验计划", user_feedback.exists());
		if (user_feedback.isChecked())
		{
			user_feedback.click();		
		}
		
		
		addStep("step 15 => Clicking version number");
		LeUiObject version_number = new LeUiObject(new UiSelector()
				.className("android.widget.LinearLayout").index(7)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.TextView").index(1)
					.resourceId("android:id/summary")))));
		verify("Finger prints not exsits",version_number.exists());		
		int dx_version_number = version_number.getBounds().centerX();
		int dy_version_number = version_number.getBounds().centerY();
		for (int i=0; i<9; i++)
		{
			phone.click(dx_version_number, dy_version_number);	
		}
		press_back(1);
		sleepInt(2);
		addStep("step 16 => Enter developer option");
		settingPanel.setAsVerticalList();
		settingPanel.scrollBackward();
		settingPanel.scrollBackward();
		LeUiObject developer_option = new LeUiObject(new UiSelector()
				.className("android.widget.LinearLayout").index(9)
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.TextView").index(0)
				.text("开发者选项"))));
		for (int i = 0; i < 5; i++) 
		{
			if (!developer_option.exists()) 
			{
				settingPanel.scrollForward();
			} 
			else 
			{
				break;
			}
		}
		verify("没有找到开发者选项", developer_option.exists());
		developer_option.click();		
		
		addStep("Step17 : Verify developer option");
		UiObject switchDeveloperWidget = new UiObject(new UiSelector()		
				.className("android.widget.FrameLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("com.letv.leui.widget.LeSwitch").index(1)
				.resourceId("com.android.settings:id/switch_widget"))))))));
		
		if (!switchDeveloperWidget.isChecked()) 
		{
			switchDeveloperWidget.click();
			sleepInt(2);
			verify("Can't open developer option.", switchDeveloperWidget.isChecked());
		}
		
		UiObject switchUSBDebugWidget = new UiObject(new UiSelector()		
				.className("android.widget.FrameLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.FrameLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.ListView").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(8)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("com.letv.leui.widget.LeSwitch").index(0)
				.resourceId("android:id/switchWidget"))))))))));
		
		if (!switchUSBDebugWidget.isChecked()) 
		{
			switchUSBDebugWidget.click();
			sleepInt(2);
			verify("Can't open USB debug.", switchUSBDebugWidget.isChecked());
		}
		
		UiObject switchLockScreenWidget = new UiObject(new UiSelector()		
				.className("android.widget.FrameLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.FrameLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.ListView").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("com.letv.leui.widget.LeSwitch").index(0)
				.resourceId("android:id/switchWidget"))))))))));
		
		if (!switchLockScreenWidget.isChecked()) {
			switchLockScreenWidget.click();
			sleepInt(2);
			verify("Can't keep screen unlock.", switchLockScreenWidget.isChecked());
		}
		
	}
	
}

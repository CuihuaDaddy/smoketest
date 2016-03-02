package com.letv.cases.leui.WallPaperOnline;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class WallPaperOnline extends LetvTestCase {
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		//closeWifi();
	}
	@CaseName("启动在线壁纸应用")
	public void testWallPaperOnline() throws UiObjectNotFoundException {
		openWifi();
		addStep("Step1:启动在线壁纸应用");
		launchApp(AppName.WALLPAPER);
		sleepInt(2);
		LeUiObject isEnter = new LeUiObject(new UiSelector()
			.packageName("com.letv.android.wallpaperonline"));
		verify("没有进入到在线壁纸中.", isEnter.exists());
		sleepInt(2);
		addStep("step2:home键后台在线壁纸，再次点击在线壁纸");
		press_home(1);
		sleepInt(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.WALLPAPER);
		sleepInt(2);
		verify("没有进入到在线壁纸中.", isEnter.exists());
		addStep("step3:home键后台在线壁纸，再次点击在线壁纸");
		/******************************************
		 * Need to remove to proceed following step
		 ******************************************
		press_menu(1);
		sleepInt(1);
		LeUiObject clear = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").textContains("立即清理"))));
		verify("清除不存在",clear.exists());
		clear.click();
		sleepInt(3);
		*******************************************
		*******************************************
		*/
		press_menu(1);
		sleepInt(3);
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
		verify("没有进入到在线壁纸中.", isEnter.exists());
		addStep("退出应用");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	
	@CaseName("下载壁纸")
	public void testDownloadPaper() throws UiObjectNotFoundException {
		openWifi();
		addStep("Step1:启动在线壁纸应用");
		launchApp(AppName.WALLPAPER);
		sleepInt(2);
		
	}
	
	
	public void openWifi() throws UiObjectNotFoundException {
		
		launchApp(AppName.SETTING);
		sleepInt(4);
		LeUiObject wlan =new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(2).
				childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
						.childSelector(new UiSelector().className("android.widget.TextView").index(0))));
		verify("Can't find Wi-Fi button.", wlan.exists());
		wlan.click();
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(10);
			verify("Can't open wifi.",
					switchWidget.isChecked());
		}
	}

	public void closeWifi() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(4);
		LeUiObject wlan =new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(2).
				childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
						.childSelector(new UiSelector().className("android.widget.TextView").index(0))));
		verify("Can't find Wi-fi button.", wlan.exists());
		wlan.click();
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			switchWidget.click();
			sleepInt(5);
			verify("Can't close wifi.", !(switchWidget.isChecked()));
		} else {
			return;
		}
	}
}

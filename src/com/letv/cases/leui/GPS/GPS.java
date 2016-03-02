package com.letv.cases.leui.GPS;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class GPS extends LetvTestCase {
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
	public void openGPS() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().resourceId("android:id/decor_content_parent").packageName("com.android.settings")));		
		LeUiObject location1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Location").resourceId("android:id/title"));
		LeUiObject location2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("位置信息").resourceId("android:id/title"));
		verify("Can't into settings",settingPanel.exists());
		settingPanel.setAsVerticalList();
		for(int i=0;i<3;i++){
			swipe_screen((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.5),
					(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8), 20);
		}
		
		for (int i = 0; i < 3; i++) {
			if (!location1.exists()&&!location2.exists()) {
				swipe_screen((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8),
						(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.5), 20);			
				} else {
				break;
			}
		}
		verify("Can't find Location button.", location1.exists()||location2.exists());
		if(location1.exists()){
			location1.click();
		}
		if(location2.exists()){
			location2.click();
		}		
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(5);
			LeUiObject connectInfo1 = new LeUiObject(new UiSelector().resourceId(
					"com.android.settings:id/switch_text").text("ON"));
			LeUiObject connectInfo2 = new LeUiObject(new UiSelector().resourceId(
					"com.android.settings:id/switch_text").text("开启"));
			verify("Can't open Location.",
					switchWidget.isChecked());
			verify("Can't open Location.",
					connectInfo1.exists()||connectInfo2.exists());
		}
	}

	public void closeGPS() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		UiScrollable settingPanel = new UiScrollable(new UiSelector()
				.className("android.widget.FrameLayout")
				.packageName("com.android.settings")
				.childSelector(
						new UiSelector().resourceId("android:id/decor_content_parent").packageName("com.android.settings")));		
				LeUiObject location1 = new LeUiObject(new UiSelector().className(
						"android.widget.TextView").text("Location").resourceId("android:id/title"));
				LeUiObject location2 = new LeUiObject(new UiSelector().className(
						"android.widget.TextView").text("位置信息").resourceId("android:id/title"));
		
		verify("Can't into settings",settingPanel.exists());
		settingPanel.setAsVerticalList();
		for(int i=0;i<3;i++){
			swipe_screen((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.5),
					(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8), 20);
		}
		
		for (int i = 0; i < 3; i++) {
			if (!location1.exists()&&!location2.exists()) {
				swipe_screen((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8),
						(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.5), 20);			
				} else {
				break;
			}
		}
		verify("Can't find Location button.", location1.exists()||location2.exists());
		
		/*for (int i = 0; i < 20; i++) {
			if (!location1.exists()&&!location2.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}*/
		
		if(location1.exists()){
			location1.click();
		}
		if(location2.exists()){
			location2.click();
		}		
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			switchWidget.click();
			sleepInt(5);
			verify("Can't close GPS.", !(switchWidget.isChecked()));
		} else {
			return;
		}
	}

	@CaseName("GPS")
	public void testGPSConnection() throws UiObjectNotFoundException {
			addStep("开启GPS");
			openGPS();
			sleepInt(2);
			press_back(2);
			addStep("关闭GPS");
			closeGPS();
			sleepInt(2);
			addStep("提出应用程序");
			press_back(4);
			press_home(1);
			verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

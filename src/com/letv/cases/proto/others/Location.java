package com.letv.cases.proto.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Location extends LetvTestCase {
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		closeGPS();
		press_back(5);
	}
	
	public void openGPS() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		
		UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().resourceId("android:id/decor_content_parent").packageName("com.android.settings")));		
		LeUiObject location = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Location").resourceId("com.android.settings:id/title"));
		verify("Can't into settings",settingPanel.exists());
		settingPanel.setAsVerticalList();
		for (int i = 0; i < 20; i++) {
			if (!location.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}
		verify("Can't find Location button.", location.exists());
		location.click();
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"android.widget.Switch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(5);
			LeUiObject connectInfo = new LeUiObject(new UiSelector().resourceId(
					"com.android.settings:id/switch_text").text("ON"));
			verify("Can't open Location.",
					switchWidget.isChecked() || connectInfo.exists());
		}
	}

	public void closeGPS() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		
		UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().resourceId("android:id/decor_content_parent").packageName("com.android.settings")));	
		LeUiObject location = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Location").resourceId("com.android.settings:id/title"));
		verify("Can't into settings",settingPanel.exists());
		settingPanel.setAsVerticalList();
		for (int i = 0; i < 20; i++) {
			if (!location.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}
		verify("Can't find Location button.", location.exists());
		location.click();
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"android.widget.Switch").resourceId(
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
		for (int i = 0;i< getIntParams("Loop");i++){
			addStep("Open GPS");
			openGPS();
			sleepInt(2);
			press_back(2);
			addStep("Close GPS");
			closeGPS();
			sleepInt(2);
			press_back(2);
		}
			press_back(5);
	}
}

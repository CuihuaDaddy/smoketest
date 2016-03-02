package com.letv.cases.proto.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Bluetooth extends LetvTestCase {
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		closeBT();
		press_back(5);
	}
	
	public void openBT() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		LeUiObject BT = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("Bluetooth").resourceId("com.android.settings:id/title"));
		verify("Can't find Bluetooth button.", BT.exists());
		BT.click();
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
					"com.android.settings:id/switch_text").text("On"));
			verify("Can't open Bluetooth.",
					switchWidget.isChecked() || connectInfo.exists());
		}
	}

	public void closeBT() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		LeUiObject BT = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("Bluetooth").resourceId("com.android.settings:id/title"));
		verify("Can't find Bluetooth button.", BT.exists());
		BT.click();
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"android.widget.Switch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			switchWidget.click();
			sleepInt(5);
			verify("Can't close Bluetooth.", !(switchWidget.isChecked()));
		} else {
			return;
		}
	}

	@CaseName("Bluetooth")
	public void testBluetoothConnection() throws UiObjectNotFoundException {
		for (int i = 0;i< getIntParams("Loop");i++){
			addStep("Open Bluetooth");
			openBT();
			sleepInt(5);
			press_back(2);
			addStep("Close Bluetooth");
			closeBT();
			sleepInt(5);
			press_back(2);
		}
			press_back(5);
	}
}

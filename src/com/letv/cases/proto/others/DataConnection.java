package com.letv.cases.proto.others;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class DataConnection extends LetvTestCase {	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		disconnectData();
		press_back(5);
	}
	
	public void connectData() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		LeUiObject dataUsage = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Data usage").resourceId("com.android.settings:id/title"));
		verify("Can't find data usage button.", dataUsage.exists());
		dataUsage.click();
		sleepInt(2);
		LeUiObject cmcc = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("CMCC 01").resourceId("android:id/title"));
		verify("Can't find data usage button.", cmcc.exists());
		cmcc.click();
		sleepInt(2);
		UiObject switchWidget =new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)).getChild(
						new UiSelector().className("android.widget.LinearLayout").index(1)).getChild(
								new UiSelector().className("android.widget.Switch"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(3);
			LeUiObject connectInfo = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("Set cellular data limit"));
			verify("Can't enabled data.",switchWidget.isChecked()|| connectInfo.exists());
		}
	}

	public void disconnectData() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		LeUiObject dataUsage = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Data usage").resourceId("com.android.settings:id/title"));
		verify("Can't find data usage button.", dataUsage.exists());
		dataUsage.click();
		sleepInt(2);
		sleepInt(2);
		LeUiObject cmcc = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("CMCC 01").resourceId("android:id/title"));
		verify("Can't find data usage button.", cmcc.exists());
		cmcc.click();
		sleepInt(2);
		UiObject switchWidget =new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)).getChild(
						new UiSelector().className("android.widget.LinearLayout").index(1)).getChild(
								new UiSelector().className("android.widget.Switch"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (!switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(2);
			LeUiObject OK = new LeUiObject(new UiSelector().className(
					"android.widget.Button").text("OK").resourceId("android:id/button1"));
			OK.click();
			sleepInt(2);
			verify("Can't disabled data.",!switchWidget.isChecked());
		}
	}

	@CaseName("DataConnection")
	public void testDataConnection() throws UiObjectNotFoundException {
		for (int i = 0;i< getIntParams("Loop");i++){
			addStep("connect data connection");
			connectData();
			sleepInt(2);
			press_back(2);
			addStep("disconncet data connection");
			disconnectData();
			sleepInt(2);
			press_back(2);
		}
			press_back(5);
	}
}

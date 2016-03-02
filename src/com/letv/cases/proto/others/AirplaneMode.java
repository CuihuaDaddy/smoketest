package com.letv.cases.proto.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class AirplaneMode extends LetvTestCase {
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		closeAPM();
		press_back(5);
	}
	
	public void openAPM() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		LeUiObject more = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("More").resourceId("com.android.settings:id/title"));
		verify("Can't find data more button.", more.exists());
		more.click();
		sleepInt(2);
		LeUiObject apm = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Airplane mode").resourceId("android:id/title"));
		verify("Can't find data apm button.", apm.exists());
		sleepInt(2);
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"android.widget.Switch").resourceId(
				"android:id/switchWidget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(5);
			verify("Can't open Airplan mode.",switchWidget.isChecked());
		}
	}

	public void closeAPM() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		LeUiObject more = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("More").resourceId("com.android.settings:id/title"));
		verify("Can't find more  button.", more.exists());
		more.click();
		sleepInt(2);
		LeUiObject apm = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Airplane mode").resourceId("android:id/title"));
		verify("Can't find apm  button.", apm.exists());
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"android.widget.Switch").resourceId(
				"android:id/switchWidget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (!switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(5);
			verify("Can't close Airplan mode.",!switchWidget.isChecked());
		}
	}

	@CaseName("APM")
	public void testAPMConnection() throws UiObjectNotFoundException {
		for (int i = 0;i< getIntParams("Loop");i++){
			addStep("Open APM");
			openAPM();
			sleepInt(5);
			press_back(2);
			addStep("Close APM");
			closeAPM();
			sleepInt(5);
			press_back(2);
		}
			press_back(5);
	}
}

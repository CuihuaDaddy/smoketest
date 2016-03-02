package com.letv.cases.leui.Bluetooth;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Bluetooth extends LetvTestCase {

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		//closeWifi();
		super.tearDown();
	}
	
	@CaseName("打开蓝牙")
	public void testOpenBluetooth() throws UiObjectNotFoundException {
		
		addStep("Step1 : 打开设置");
		launchApp(AppName.SETTING);
		sleepInt(4);
		
		addStep("Step2 : 进入蓝牙");
		UiObject bluetooth = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("蓝牙"));
		verify("Can't find Bluetooth button.", bluetooth.exists());
		bluetooth.click();
		sleepInt(2);
		
		addStep("Step3 : 打开蓝牙");
		UiObject switchWidget = new UiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			for (int i = 1; i <= 60; i++) {
				sleepInt(1);
				if (switchWidget.isChecked()) {
					break;
				}
			}
			verify("Can't open bluetooth.", switchWidget.isChecked());
		}
		
		addStep("Step4 : 验证能否刷新蓝牙列表");
		LeUiObject refresh_icon = new LeUiObject(
			new UiSelector().className("android.widget.LinearLayout").index(1)
			.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
			.childSelector(new UiSelector().className("android.widget.ImageView").index(0)
			.resourceId("android:id/icon"))));
		for (int i = 1; i <= 70; i++) {
			sleepInt(1);
			if (refresh_icon.isEnabled()) {
				break;
			}
		}
		if (refresh_icon.exists())
		{
			verify("Can't refresh bluetooth.", refresh_icon.isEnabled());			
		}
	}
	
	@CaseName("关闭蓝牙")
	public void testCloseBluetooth() throws UiObjectNotFoundException {
		addStep("Step1 : 进入设置");
		launchApp(AppName.SETTING);
		sleepInt(4);
		
		addStep("Step2 : 进入蓝牙");
		UiObject bluetooth = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("蓝牙"));
		verify("Can't find Wi-Fi button.", bluetooth.exists());
		bluetooth.click();
		sleepInt(2);
		
		addStep("Step3 : 关闭蓝牙");
		UiObject switchWidget = new UiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		if (switchWidget.isChecked()) {			
			switchWidget.click();
			sleepInt(10);
			verify("Can't close bluetooth.", !(switchWidget.isChecked()));
		}
	}

}

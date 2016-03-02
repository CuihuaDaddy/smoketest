package com.letv.cases.leui.Settings;

import java.util.ArrayList;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Location extends LetvTestCase {
	
	@CaseName("Mobile-3251:Location Access-Location services.")
	public void testOpenCloseLocation() throws UiObjectNotFoundException {
		addStep("1. Go to settings-Location Access.");
		launchApp(AppName.SETTING);
		LeUiObject locationInfo = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("位置信息"));
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(locationInfo.exists()){
				break;
			}
		}
		verify("位置信息不存在",locationInfo.exists());
		locationInfo.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("2. Enable or unable Access to my location");
		LeUiObject switchWidget = new LeUiObject(
				new UiSelector().className("com.letv.leui.widget.LeSwitch"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(5);
		}
		verify("Can't open Airplan mode.", switchWidget.isChecked());
		addStep("2. Unable Access to my location");
		switchWidget.click();
		sleepInt(5);
		verify("Can't close Airplan mode.", !switchWidget.isChecked());
	}

	@CaseName("Mobile-21879:位置信息界面")
	public void testLoacationPage() throws UiObjectNotFoundException {
		addStep("1.进入系统设置");
		launchApp(AppName.SETTING);
		addStep("2.点击位置信息");
		LeUiObject locationInfo = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("位置信息"));
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(locationInfo.exists()){
				break;
			}
		}
		verify("位置信息不存在",locationInfo.exists());
		locationInfo.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject switchWidget = new LeUiObject(
				new UiSelector().className("com.letv.leui.widget.LeSwitch"));
		LeUiObject mode = new LeUiObject(
				new UiSelector().className("candroid.widget.TextView").text("模式"));
		LeUiObject locationRight = new LeUiObject(
				new UiSelector().className("candroid.widget.TextView").text("定位权限"));
		LeUiObject recentLocation = new LeUiObject(
				new UiSelector().className("candroid.widget.TextView").text("最近的位置信息请求"));
		LeUiObject netLocation = new LeUiObject(
				new UiSelector().className("candroid.widget.TextView").text("网络位置"));
		verify("位置信息验证点不存在",switchWidget.exists()&&mode.exists()&&locationRight.exists()&&recentLocation.exists()&&netLocation.exists());
	}
		
	
	@CaseName("Mobile-21882:定位权限")
	public void testLocateRight() throws UiObjectNotFoundException {
		addStep("1.进入系统设置");
		launchApp(AppName.SETTING);
		addStep("2.点击位置信息");
		LeUiObject locationInfo = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("位置信息"));
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(locationInfo.exists()){
				break;
			}
		}
		verify("位置信息不存在",locationInfo.exists());
		locationInfo.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("3.点击定位权限");
		LeUiObject locateRight = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("定位权限"));
		verify("定位权限不存在",locateRight.exists());
		locateRight.clickAndWaitForNewWindow();
		sleepInt(1);
		ArrayList<String> list = new ArrayList<String>();
		list.add("百度输入法乐视版");
		list.add("我的乐视");
		list.add("WPS Office");
		list.add("乐视视频");
		list.add("微博");
		list.add("地图");
		list.add("乐看搜索");
		list.add("乐视商城");
		
		for(int i=0;i<list.size();i++){
			String checkPointName=list.get(i).toString();
			LeUiObject checkPoint = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text(checkPointName));
			verify(checkPointName+"不存在",checkPoint.exists());
		}
	}
}

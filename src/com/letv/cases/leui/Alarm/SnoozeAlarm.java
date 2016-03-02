package com.letv.cases.leui.Alarm;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class SnoozeAlarm extends LetvTestCase {

	@Override
	public void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	    UiWatcher alarmWatcher = new UiWatcher() {
			public boolean checkForCondition() {
				LeUiObject alarmWindows = new LeUiObject(new UiSelector().resourceId("android:id/button1").text(
						"Ok"));
				if (alarmWindows.exists()) {
					LeUiObject Snooze = new LeUiObject(new UiSelector().resourceId("android:id/button2").text(
							"Snooze"));
					try {
						Snooze.click();
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				} else {
					return false;
				}
			}
		};
		getUiDevice().registerWatcher("alarmWatcher", alarmWatcher);
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		getUiDevice().removeWatcher("alarmWatcher");
	}
	
	@CaseName("延时闹钟,修改系统时间，设置闹钟,退出时钟")
	public void testSnoozeAlarm() throws UiObjectNotFoundException {
		addStep("Step1:延时闹钟");
		addStep("打开闹钟");
		launchApp(AppName.CLOCK);
		LeUiObject alarmList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
					"com.android.deskclock:id/alarm_list"));
		sleepInt(1);
		LeUiObject alarmTag = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Alarm"));
		alarmTag.click();
		sleepInt(1);
		int alarm1 = alarmList.getChildCount();
		addStep("新建闹钟");
		LeUiObject addAlarm = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Add"));
		addAlarm.click();
		sleepInt(1);
		phone.swipe(710, 1237, 710, 1150, 50);
		sleepInt(1);
		LeUiObject saveAlarm = new LeUiObject(new UiSelector().resourceId("com.android.deskclock:id/alarm_setting_menu_ok"));
		saveAlarm.click();
		sleepInt(1);
		verify("新闹钟添加不成功", alarmList.exists()&& alarmList.getChildCount() == (alarm1+1));
		sleepInt(60);
		alarmTag.click();
		sleepInt(305);
		phone.swipe(558, 950, 558, 1850, 100);
		sleepInt(1);
		LeUiObject cameraBtn = new LeUiObject(new UiSelector().resourceId("com.android.systemui:id/camera_btn"));
		verify("延时闹钟失败", cameraBtn.exists());
		addStep("延时闹钟成功");
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

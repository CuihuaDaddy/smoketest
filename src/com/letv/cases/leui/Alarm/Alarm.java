package com.letv.cases.leui.Alarm;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Alarm extends LetvTestCase {

	@Override
	public void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	    
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		
	}
	
	@CaseName("启动时钟应用,关闭闹钟,延时闹钟,修改系统时间，设置闹钟,退出时钟")
	public void testOpenAlarm() throws UiObjectNotFoundException {
		addStep("Step1:启动时钟应用");
		launchApp(AppName.CLOCK);
		sleepInt(2);
		LeUiObject alarmTag = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("闹钟"));
		verify("没有成功进入闹钟",alarmTag.exists());
		sleepInt(2);
		addStep("step2:home键后台图库，再次点击图库");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.CLOCK);
		sleepInt(5);
		verify("没有成功进入闹钟",alarmTag.exists());
		addStep("step3:home键后台图库调用多任务，多任务中点击图库");
		/*press_home(1);
		sleepInt(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));*/
		press_menu(1);
		sleepInt(2);
		LeUiObject clear = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").textContains("立即清理"))));
		if(clear.exists()){
			clear.click();
			sleepInt(3);
			press_menu(1);
		}
		sleepInt(1);
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
		verify("没有成功进入闹钟",alarmTag.exists());
		addStep("退出应用");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	@CaseName("创建闹钟，关闭闹钟,退出时钟")
	public void testCloseAlarm() throws UiObjectNotFoundException {
		UiWatcher alarmWatcher = new UiWatcher() {
			public boolean checkForCondition() {
				LeUiObject alarmWindows1 = new LeUiObject(new UiSelector().resourceId("android:id/button2").text(
						"Snooze"));
				LeUiObject alarmWindows2 = new LeUiObject(new UiSelector().resourceId("android:id/button2").text(
						"稍后提醒"));
				if (alarmWindows1.exists()||alarmWindows2.exists()) {
					LeUiObject ok1 = new LeUiObject(new UiSelector().resourceId("android:id/button1").text(
							"Ok"));
					LeUiObject ok2 = new LeUiObject(new UiSelector().resourceId("android:id/button1").text(
							"好"));
					
					try {
						if(ok1.exists()){
							ok1.click();
						}
						if(ok2.exists()){
							ok2.click();
						}
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
		addStep("Step2:关闭闹钟");
		addStep("打开闹钟");
		launchApp(AppName.CLOCK);
		LeUiObject alarmList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
					"com.android.deskclock:id/alarm_list"));
		sleepInt(1);
		LeUiObject alarmTag1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Alarm"));
		LeUiObject alarmTag2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("闹钟"));
		if(alarmTag1.exists()){
			alarmTag1.click();
		}
		if(alarmTag2.exists()){
			alarmTag2.click();
		}
		sleepInt(1);
		int alarm1 = alarmList.getChildCount();
		addStep("新建闹钟");
		LeUiObject addAlarm = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId("com.android.deskclock:id/alarm_menu_add"));
		addAlarm.click();
		sleepInt(1);
		phone.swipe(710, 1237, 710, 1150, 50);
		sleepInt(1);
		LeUiObject saveAlarm = new LeUiObject(new UiSelector().resourceId("com.android.deskclock:id/alarm_setting_menu_ok"));
		saveAlarm.click();
		sleepInt(1);
		verify("新闹钟添加不成功", alarmList.exists()&& alarmList.getChildCount() == (alarm1+1));
		sleepInt(60);
		if(alarmTag1.exists()){
			alarmTag1.click();
		}
		if(alarmTag2.exists()){
			alarmTag2.click();
		}
		addStep("关闭闹钟成功");
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		getUiDevice().removeWatcher("alarmWatcher");
	}
	
	@CaseName("延时闹钟,关闭闹钟,退出时钟")
	public void testSnoozeAlarm() throws UiObjectNotFoundException, RemoteException {
		UiWatcher alarmWatcher1 = new UiWatcher() {
			public boolean checkForCondition() {
				LeUiObject alarmWindows1 = new LeUiObject(new UiSelector().resourceId("android:id/button2").text(
						"Snooze"));
				LeUiObject alarmWindows2 = new LeUiObject(new UiSelector().resourceId("android:id/button2").text(
						"稍后提醒"));
				if (alarmWindows1.exists()||alarmWindows2.exists()) {
					try {
						if(alarmWindows1.exists()){
							alarmWindows1.click();
						}
						if(alarmWindows2.exists()){
							alarmWindows2.click();
						}
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
		getUiDevice().registerWatcher("alarmWatcher1", alarmWatcher1);
		addStep("Step1:延时闹钟");
		addStep("打开闹钟");
		launchApp(AppName.CLOCK);
		LeUiObject alarmList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
					"com.android.deskclock:id/alarm_list"));
		sleepInt(1);
		LeUiObject alarmTag1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Alarm"));
		LeUiObject alarmTag2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("闹钟"));
		if(alarmTag1.exists()){
			alarmTag1.click();
		}
		if(alarmTag2.exists()){
			alarmTag2.click();
		}
		sleepInt(1);
		int alarm1 = alarmList.getChildCount();
		addStep("新建闹钟");
		LeUiObject addAlarm = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId("com.android.deskclock:id/alarm_menu_add"));
		addAlarm.click();
		sleepInt(1);
		phone.swipe(710, 1237, 710, 1150, 50);
		sleepInt(1);
		LeUiObject saveAlarm = new LeUiObject(new UiSelector().resourceId("com.android.deskclock:id/alarm_setting_menu_ok"));
		saveAlarm.click();
		sleepInt(1);
		verify("新闹钟添加不成功", alarmList.exists()&& alarmList.getChildCount() == (alarm1+1));
		sleepInt(60);
		if(alarmTag1.exists()){
			alarmTag1.click();
		}
		if(alarmTag2.exists()){
			alarmTag2.click();
		}
		sleepInt(305);
		phone.swipe(558, 950, 558, 1850, 100);
		sleepInt(1);
		LeUiObject cameraBtn = new LeUiObject(new UiSelector().resourceId("com.android.systemui:id/camera_btn"));
		verify("延时闹钟失败", cameraBtn.exists());
		addStep("延时闹钟成功");
		addStep("关闭闹钟成功");
		unLockDevice();
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		getUiDevice().removeWatcher("alarmWatcher1");
	}
	
	
	@CaseName("创建闹钟，关闭闹钟,退出时钟")
	public void testLaunchAlarm() throws UiObjectNotFoundException {
		UiWatcher alarmWatcher = new UiWatcher() 
		{
			public boolean checkForCondition() 
			{
				
				LeUiObject alarmWindows = new LeUiObject(
						new UiSelector().text("上滑关闭闹钟"));
				int x = getUiDevice().getDisplayWidth();
				int y = getUiDevice().getDisplayHeight();
				if (alarmWindows.exists()) 
				{
				    getUiDevice().swipe(x/2, 4*y/5, x/2, y/5, 100);
					return true;
				} 
				else 
				{
					return false;
				}
			}
		};
		
		getUiDevice().registerWatcher("alarmWatcher", alarmWatcher);
		
		//addStep("Step2:关闭闹钟");
		addStep("Step1:打开闹钟");
		launchApp(AppName.CLOCK);
		LeUiObject noAlarm = new LeUiObject(new UiSelector().text("无闹钟"));
		LeUiObject alarmList = new LeUiObject(new UiSelector().resourceId("com.android.deskclock:id/alarm_item").index(0));
		
		if (!noAlarm.exists()){
			addStep("已存在闹钟，长按删除已有闹钟");
			int dx = alarmList.getBounds().centerX();
			int dy = alarmList.getBounds().centerY();
			getUiDevice().swipe(dx, dy, dx, dy, 50);
			LeUiObject selectAll = new LeUiObject(new UiSelector().text("全选"));
			if(selectAll.exists()){
				selectAll.click();
			}
			sleepInt(1);
			LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.ImageView").resourceId("android:id/icon"));
			delete.click();
			LeUiObject  deleteConfirm = new LeUiObject(new UiSelector().textContains("删除"));
			deleteConfirm.click();
		}
/*		
		addStep("新增闹钟");
		LeUiObject addClock = new LeUiObject(new UiSelector().resourceId("com.android.deskclock:id/alarm_menu_add"));
		addClock.clickAndWaitForNewWindow();
		
		*/
		
/*		int alarm1 = 0;
		if (alarmList.exists())
		{
			alarm1 = alarmList.getChildCount();
		}
		sleepInt(1);*/
		
		/*
		if(alarmTag1.exists()){
			alarmTag1.click();
		}
		if(alarmTag2.exists()){
			alarmTag2.click();
		}
		sleepInt(1);
		*/
		
		addStep("Step2:新建闹钟");
		LeUiObject addAlarm = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId("com.android.deskclock:id/alarm_menu_add"));
		sleepInt(2);
		addAlarm.click();
		sleepInt(2);
		
		//phone.swipe(710, 1237, 710, 1150, 50);
		LeUiObject minute_bar = new LeUiObject(
				new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.FrameLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.view.View")
				.resourceId("com.android.deskclock:id/minute"))))));
		verify("Minutes bar not exists", minute_bar.exists());
		if (minute_bar.exists())
		{
			int dx = minute_bar.getBounds().centerX();
			int dy = minute_bar.getBounds().centerY();
			phone.swipe(dx, dy, dx, dy-100, 50);			
		}
		
		sleepInt(1);
		LeUiObject saveAlarm = new LeUiObject(new UiSelector().resourceId("com.android.deskclock:id/alarm_setting_menu_ok"));
		saveAlarm.click();
		sleepInt(1);
//		verify("新闹钟添加不成功", alarmList.exists()&& alarmList.getChildCount() == (alarm1+1));
		
		// Wait for alarm events
		//sleepInt(60);
		
	/*	int clock_click_count = 0;
		do 
		{
			sleepInt(1);
			clock_click_count = clock_click_count + 1;
			
			LeUiObject alarmWindows = new LeUiObject(
					new UiSelector().resourceId("com.android.deskclock:id/keyguard_alert_snooze")
					.className("android.widget.TextView"));
			LeUiObject alarmWindows = new LeUiObject(
					new UiSelector().className("android.widget.TextView").textContains("稍后提醒"));
			if (alarmWindows.exists()) 
			{
				int dx = alarmWindows.getBounds().centerX();
				int dy = alarmWindows.getBounds().centerY();
				//phone.swipe(dx, dy, dx, dy-2000, 40);		
				phone.click(dx, dy);
				
				clock_click_count = 62;
				break;
			}
		
		} while (clock_click_count < 61);
		
		addStep("Step3:删除所有闹钟");
		LeUiObject alarmTag1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId("com.android.deskclock:id/alarm_item_label").text("Alarm"));
		LeUiObject alarmTag2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId("com.android.deskclock:id/alarm_item_label").text("闹钟"));
		if(alarmTag1.exists()){
			//alarmTag1.click();
			int dx = alarmTag1.getBounds().centerX();
			int dy = alarmTag1.getBounds().centerY();
			phone.swipe(dx, dy, dx, dy, 100);
		}
		else if(alarmTag2.exists()){
			//alarmTag2.click();
			int dx = alarmTag2.getBounds().centerX();
			int dy = alarmTag2.getBounds().centerY();
			phone.swipe(dx, dy, dx, dy, 100);
		}
				
		//phone.swipe(710, 379, 710, 379, 200);
		//phone.swipe(710, 379, 710, 379, 400);
		//getUiDevice().getInstance().swipe(710, 379, 710, 379, 400);
				
		sleepInt(5);
		LeUiObject select_all_btn = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
					"com.android.deskclock:id/alarm_menu_select").index(0));
		if (select_all_btn.exists())
		{
			select_all_btn.click();
			sleepInt(2);
		}
		
		LeUiObject delete_btn = new LeUiObject(new UiSelector().className(
				"android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.ImageView")
						.resourceId("android:id/icon")));
		
		if (!delete_btn.isEnabled())
		{
			select_all_btn.click();			
		}
		
		if (delete_btn.exists() && delete_btn.isEnabled())
		{
			delete_btn.click();
			sleepInt(2);
		}
		
		LeUiObject confirm_btn = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.Button")
						.resourceId("android:id/le_bottomsheet_default_confirm")));
		if (confirm_btn.exists())
		{
			confirm_btn.click();
			sleepInt(2);
		}*/
		sleepInt(60);
		
		verify("待机60秒等待watcher触发", !noAlarm.exists());
		
		addStep("Step4:退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		getUiDevice().removeWatcher("alarmWatcher");
	}	
}

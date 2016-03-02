package com.letv.cases.leui.Init;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LetvTestCase;

public class Init extends LetvTestCase {
	
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
	
	@CaseName("系统OTA升级")
	public void testOTAupdate() throws UiObjectNotFoundException {
		addStep("进入系统升级");
		launchApp(AppName.UPGRADE);
		sleepInt(10);
		 
		UiObject shareButton = new UiObject(new UiSelector().resourceId("com.letv.android.ota:id/share"));
		shareButton.click();
		
		UiObject localUpdate = new UiObject(new UiSelector().text("本地升级"));
		localUpdate.click();
		
		UiObject checkNo = new UiObject(new UiSelector().text("没有找到升级包，请下载升级包并复制到内部存储的根目录中。"));
		UiObject checkYes = new UiObject(new UiSelector().text("系统发现升级包，手机将重启以完成安装，是否确认升级？"));
		if(checkNo.exists()){
			fail("未找到升级包，升级失败");
		}
		if(checkYes.exists()){
		UiObject ok = new UiObject(new UiSelector().text("确认"));
		ok.click();
		}
		sleepInt(600);
 		
		/*addStep("Step1:初始化手机");
		for (int i = 0; i < 4; i++) {
		getUiDevice().click(720, 2259);
		sleepInt(2);
		}
		addStep("Step2:设置中文");
		addStep("Step2:设置密码安全");
		addStep("Step2:设置usb允许");
		addStep("Step2:设置休眠30分钟");
		launchApp(AppName.SETTING);
		
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
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));*/
	}

}

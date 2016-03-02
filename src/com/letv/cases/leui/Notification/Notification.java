package com.letv.cases.leui.Notification;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Notification extends LetvTestCase {
	@CaseName("手势呼出通知栏")
//	待机界面垂直下滑
	public void testOpenNotification() throws UiObjectNotFoundException {
		addStep("step1:待机界面垂直下滑");
		LeUiObject noticeManage = new LeUiObject(new UiSelector().resourceId("com.android.systemui:id/status_bar_notification_setting_btn"));
		for (int i = 0; i < 5; i++) {
			if(noticeManage.exists()){
				break;
			}
			phone.swipe(564, 2, 564, 1788, 100);
			sleepInt(2);
		}
		verify("Can't open notification.", noticeManage.exists());
		addStep("退出");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	@CaseName("删除通知消息")
//	1、待机界面垂直下滑
//	2、点击“X”
	public void testClearNotification() throws UiObjectNotFoundException {
		addStep("step1:待机界面垂直下滑");
		LeUiObject noticeManage = new LeUiObject(new UiSelector().resourceId("com.android.systemui:id/status_bar_notification_setting_btn"));
		LeUiObject clearAll = new LeUiObject(new UiSelector().resourceId("com.android.systemui:id/clear_all_button"));
		for (int i = 0; i < 5; i++) {
			if(noticeManage.exists()){
				break;
			}
			phone.swipe(564, 2, 564, 1788, 100);
			sleepInt(2);
		}
		
		verify("Can't open notification.", noticeManage.exists());
		sleepInt(3);
		if((!clearAll.exists())&&(noticeManage.exists())){
			System.out.println("没有可清除的消息");
		}else{
			clearAll.click();
			sleepInt(2);
			verify("清除后notification没有弹起来",!clearAll.exists());
		}
		
		
	}
	
}

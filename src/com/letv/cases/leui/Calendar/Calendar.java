package com.letv.cases.leui.Calendar;


import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Calendar extends LetvTestCase {
	

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	
	
	
	@CaseName("启动日历应用")
//	1.启动日历应用
//	2.home键后台日历，再次点击日历
//	3.home键后台日历 调用多任务，多任务
	public void testOpenCalendar() throws UiObjectNotFoundException {
		addStep("Step1:启动日历应用");
		launchApp(AppName.CALENDAR);
		sleepInt(2);
		LeUiObject event = new LeUiObject(new UiSelector()
			.packageName("com.android.calendar"));
		verify("没有进入到日历中.", event.exists());
		sleepInt(2);
		addStep("step2:home键后台日历，再次点击日历");
		press_home(1);
		sleepInt(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.CALENDAR);
		sleepInt(2);
		verify("没有进入到日历中.", event.exists());
		addStep("step3:home键后台日历，再次点击日历");
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
		verify("没有进入到日历中.", event.exists());
		addStep("退出应用");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	
	private void clearAllEvents() throws UiObjectNotFoundException {
		LeUiObject event_item = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/event_item_rl"));
		while (event_item.exists()) {
			event_item.click();
			sleepInt(2);
			LeUiObject delete = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.calendar:id/delete_event"));
			verify("Can't find delete button.", delete.exists());
			delete.click();
			sleepInt(1);
			LeUiObject confirm = new LeUiObject(new UiSelector().resourceId(
					"android:id/button1").text("OK"));
			verify("Can't find confirm button.", confirm.exists());
			confirm.click();
			sleepInt(3);
		}
	}

	private void verifyAdd(int count) throws UiObjectNotFoundException {
		LeUiObject event = new LeUiObject(new UiSelector()
			.className("android.widget.RelativeLayout").index(1));
		verify("Can't find event button.", event.exists());
		event.click();
		LeUiObject event_list = new LeUiObject(
				new UiSelector()
					.resourceId("android:id/text1").text("Event List"));
		LeUiObject spinner = new LeUiObject(
				new UiSelector()
					.className("android.widget.Spinner"));
		verify("can't find Spinner button",spinner.exists());
		if(event_list.exists()){
			return;
		}else{
			spinner.click();
			sleepInt(1);
			verify("can't find event list button",event_list.exists());
			event_list.click();
			sleepInt(1);
		}
		LeUiObject event_item = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/event_item_rl"));
		verify("Can't add event loop " + count, event_item.exists());
	}

	private void verifyDel(int count) throws UiObjectNotFoundException {
		LeUiObject event = new LeUiObject(new UiSelector()
		.className("android.widget.RelativeLayout").index(1));
		verify("Can't find event button.", event.exists());
		event.click();
		LeUiObject event_list = new LeUiObject(
				new UiSelector()
					.resourceId("android:id/text1").text("Event List"));
		LeUiObject spinner = new LeUiObject(
				new UiSelector()
					.className("android.widget.Spinner"));
		verify("can't find Spinner button",spinner.exists());
		if(event_list.exists()){
			return;
		}else{
			spinner.click();
			sleepInt(1);
			verify("can't find event list button",event_list.exists());
			event_list.click();
			sleepInt(1);
		}
		LeUiObject event_item = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/event_item_rl"));
		verify("Can't delete event loop " + count, !event_item.exists());
	}


	@CaseName("事件的添加和删除")
	public void testEventReminder() throws UiObjectNotFoundException {
		addStep("打开日历");
		launchApp(AppName.CALENDAR);
		addStep("编辑一事件");
		LeUiObject add = new LeUiObject(
				new UiSelector().className("android.widget.RelativeLayout").index(4)
				.childSelector(new UiSelector().text("添加")));
		verify("Can't find add button.", add.exists());
		add.click();
		sleepInt(2);
		LeUiObject eventName = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/title"));
		verify("Can't find event name item.", eventName.exists());
		eventName.click();
		sleepInt(1);
		callShell("input text 010");
		sleepInt(2);
		LeUiObject complete = new LeUiObject(new UiSelector().resourceId("com.android.calendar:id/action_done"));
		verify("Can't find complete button.", complete.exists());
		complete.click();
		sleepInt(2);
		addStep("确认事件添加成功");
		LeUiObject eventList = new LeUiObject(new UiSelector().text("列表"));
		verify("关注不存在",eventList.exists());
		eventList.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject check = new LeUiObject(new UiSelector().textContains("010"));
		verify("事件添加不成功",check.exists());
		sleepInt(2);
		addStep("退出日历");
		press_back(3);
	}
	
	@CaseName("查看日历")
	public void testLookCalendar() throws UiObjectNotFoundException {
		addStep("打开日历");
		launchApp(AppName.CALENDAR);
		sleepInt(2);
		LeUiObject agree = new LeUiObject(new UiSelector().textContains("同意"));
		if(agree.exists()){
			agree.click();
			sleepInt(2);
		}
		LeUiObject lookmore = new LeUiObject(new UiSelector().className("android.widget.Button").resourceId("com.android.calendar:id/guide_more_button"));
		if(lookmore.exists()){
			lookmore.click();
			sleepInt(2);
		}
		phone.click(90, 150);
		sleepInt(2);
		LeUiObject event = new LeUiObject(new UiSelector().resourceId("android:id/content")
				.packageName("com.android.calendar"));
		verify("没有进入到日历中.", event.exists());
		LeUiObject date = new LeUiObject(new UiSelector().resourceId("com.android.calendar:id/title")
				.className("android.widget.TextView").index(0));
		
		verify("年月存在",date.exists());
		String changeDate=null;
		for (int i = 0; i < 18; i++) {
			phone.swipe(750, 580, 750, 1200, 20);
			sleepInt(1);
			changeDate = date.getText();
			if(changeDate.contains("2014")){
				break;
			}
		}
		verify("切换年月不成功",changeDate.contains("2014"));
		
		addStep("退出日历");
		press_back(5);
	}
	
	@CaseName("日历视图切换")
	public void testChangeView() throws UiObjectNotFoundException {
		addStep("打开日历");
		launchApp(AppName.CALENDAR);
		sleepInt(2);
		LeUiObject agree = new LeUiObject(new UiSelector().textContains("同意"));
		if(agree.exists()){
			agree.click();
			sleepInt(2);
		}
		LeUiObject event = new LeUiObject(new UiSelector().resourceId("android:id/title")
				.packageName("com.android.calendar"));
		verify("没有进入到日历中.", event.exists());
		LeUiObject date = new LeUiObject(new UiSelector().resourceId("com.android.calendar:id/title")
				.className("android.widget.TextView").index(0));

		
		verify("年月存在",date.exists());
		String changeDate=null;
		for (int i = 0; i < 13; i++) {
			phone.swipe(750, 580, 750, 1200, 20);
			sleepInt(1);
			changeDate = date.getText();
			if(changeDate.contains("2014")){
				break;
			}
		}
		verify("切换年月不成功",changeDate.contains("2014"));
		
		addStep("进入周视图");
		LeUiObject week = new LeUiObject(new UiSelector()
				.className("android.widget.RadioButton").text("周"));
		verify("周视图选项不存在",week.exists());
		week.click();
		sleepInt(3);
		LeUiObject weekVerify = new LeUiObject(new UiSelector()
			.className("android.widget.ViewSwitcher"));
		verify("周视图切换不成功",weekVerify.exists());
		for (int i = 0; i < 13; i++) {
			phone.swipe(250, 1000, 800, 1000, 10);
			sleepInt(1);
		}
		LeUiObject year = new LeUiObject(new UiSelector()
			.className("android.widget.RadioButton").text("年"));
		year.click();
		sleepInt(3);
		LeUiObject yearVerify = new LeUiObject(new UiSelector()
		.resourceId("com.android.calendar:id/year")
		.childSelector(new UiSelector().className("android.widget.ListView")));
		verify("年视图不存在",yearVerify.exists());
		for (int i = 0; i < 13; i++) {
			phone.swipe(750, 580, 750, 1200, 20);
			sleepInt(1);
		}
		press_back(5);
	}
}
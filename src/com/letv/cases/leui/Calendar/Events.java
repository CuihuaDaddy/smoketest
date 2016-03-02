package com.letv.cases.leui.Calendar;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Events extends LetvTestCase {


	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
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
	public void testEventsAddDel() throws UiObjectNotFoundException {
		addStep("打开日历");
		launchApp(AppName.CALENDAR);
		addStep("清除所有事件");
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
		if(!event_list.exists()){
			spinner.click();
			sleepInt(1);
			verify("can't find event list button",event_list.exists());
			event_list.click();
			sleepInt(1);
		}
		clearAllEvents();
		press_back(1);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("编辑一事件");
			LeUiObject add = new LeUiObject(
					new UiSelector()
							.className("android.widget.RelativeLayout").index(4));
			verify("Can't find add button.", add.exists());
			add.click();
			sleepInt(2);
			LeUiObject eventName = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.calendar:id/title").text("Event name"));
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
			verifyAdd(i + 1);
			addStep("删除事件");
			clearAllEvents();
			press_back(1);
			addStep("确认事件删除成功");
			verifyDel(i + 1);
			press_back(1);
			sleepInt(2);
		}
		addStep("退出日历");
		press_back(5);
	}

}

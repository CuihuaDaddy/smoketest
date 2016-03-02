package com.letv.cases.proto.pim;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Events extends LetvTestCase {

	private int loop = 5;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	private void clearAllEvents() throws UiObjectNotFoundException {
		// launchAppByPackage(IntentConstants.calendar);
		// LeUiObject event = new LeUiObject(new UiSelector()
		// .className("android.widget.Button")
		// .resourceId("com.android.calendar:id/ib_agenda").text("事件"));
		// verify("Can't find event button.", event.exists());
		// event.click();
		/*LeUiObject event_item = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/agenda_events_list"));
		while (event_item.exists()) {
			event_item.click();
			sleepInt(2);
			LeUiObject delete = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.calendar:id/delete_event"));
			verify("Can't find delete button.", delete.exists());
			delete.click();
			LeUiObject confirm = new LeUiObject(new UiSelector().resourceId(
					"android:id/button1").text("确定"));
			verify("Can't find confirm button.", confirm.exists());
			confirm.click();
			sleepInt(2);
		
		}*/
		sleepInt(2);
		LeUiObject option= new LeUiObject(
				new UiSelector().className("android.widget.ImageButton").description("More options"));
		verify("Can't find option button",option.exists());
		option.click();
		sleepInt(2);
		LeUiObject clearEvent= new LeUiObject(
				new UiSelector().className("android.widget.TextView").text("Clear events"));
		verify("Can't find clearEvent button",clearEvent.exists());
		clearEvent.click();
		sleepInt(2);
		LeUiObject sync= new LeUiObject(
				new UiSelector().resourceId("com.android.calendar:id/sync").className("android.widget.CheckBox"));
		if(!sync.isChecked()){
			sync.click();
			sleepInt(2);
		}
		LeUiObject ok= new LeUiObject(
				new UiSelector().resourceId("com.android.calendar:id/btn_ok").className("android.widget.Button"));
		ok.click();
		sleepInt(2);
		LeUiObject ok2= new LeUiObject(
				new UiSelector().resourceId("android:id/button1").className("android.widget.Button"));
		ok2.click();
		sleepInt(2);
		
	}

	private void verifyAdd(int count) throws UiObjectNotFoundException {
		LeUiObject spinner= new LeUiObject(new UiSelector().className("android.widget.Spinner"));
		verify("Can't find spinner.", spinner.exists());
		spinner.click();
		sleepInt(1);
		LeUiObject event = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.calendar:id/button_view").text("Agenda"));
		verify("Can't find event button.", event.exists());
		event.click();
		sleepInt(1);
		LeUiObject event_item = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/agenda_events_list"));
		verify("Can't add event loop " + count, event_item.exists());
	}

	private void verifyDel(int count) throws UiObjectNotFoundException {
		LeUiObject noEvent = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/blank_text").text("No event."));
		verify("Can't delete event loop " + count, noEvent.exists());
	}

	@CaseName("事件的添加和删除")
	public void testEventsAddDel() throws UiObjectNotFoundException {
		addStep("打开日历");
		launchAppByPackage(IntentConstants.calendar);
		addStep("清除所有事件");
/*		LeUiObject spinner= new LeUiObject(new UiSelector().className("android.widget.Spinner"));
		verify("Can't find spinner.", spinner.exists());
		spinner.click();
		sleepInt(1);
		LeUiObject event = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.calendar:id/button_view").text("Agenda"));
		verify("Can't find event button.", event.exists());
		event.click();
		sleepInt(1);*/
		clearAllEvents();
		press_back(1);
		for (int i = 0; i < getIntParams("Loop") ; i++) {
			addStep("编辑一事件");
			// LeUiObject title = new LeUiObject(
			// new UiSelector()
			// .resourceId("com.android.calendar:id/title"));
			// verify("Can't find title field.", title.exists());
			// title.setText("010");
			LeUiObject option= new LeUiObject(
					new UiSelector().className("android.widget.ImageButton"));
			verify("Can't find option button",option.exists());
			option.click();
			sleepInt(1);
			LeUiObject add = new LeUiObject(
					new UiSelector()
							.resourceId("android:id/title").text("New event"));
			verify("Can't find add button.", add.exists());
			add.click();
			// callShell("input tap 800 200");
			sleepInt(2);
			callShell("input text 010");
			sleepInt(2);
			callShell("input tap 257 1451");
			sleepInt(2);
			callShell("input text 123");
			sleepInt(2);
			LeUiObject complete = new LeUiObject(new UiSelector().text("Done"));
			verify("Can't find complete button.", complete.exists());
			complete.click();
			addStep("确认事件添加成功");
			verifyAdd(i + 1);
			addStep("删除事件");
			clearAllEvents();
			press_back(1);
			addStep("确认事件删除成功");
			verifyDel(i + 1);
			sleepInt(2);
		}
		addStep("退出日历");
		press_back(5);
	}

}

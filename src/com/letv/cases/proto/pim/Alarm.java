package com.letv.cases.proto.pim;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Alarm extends LetvTestCase {

	@CaseName("闹铃的添加和删除")
	public void testAlarm() throws UiObjectNotFoundException {
		addStep("打开闹钟");
		launchAppByPackage(IntentConstants.proto_clock);
		sleepInt(2);
		LeUiObject alarmTag = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").description("Alarm"));
		alarmTag.click();
		sleepInt(1);

		LeUiObject emptyTag = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("No Alarms"));
		if (!emptyTag.exists()) {
			addStep("清空所有闹钟");
			clearAllAlarms();
		}

		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("新建闹钟");
			LeUiObject addAlarm = new LeUiObject(new UiSelector().resourceId(
					"com.android.deskclock:id/fab").description("Add alarm"));
			addAlarm.click();
			sleepInt(2);
			LeUiObject saveAlarm = new LeUiObject(new UiSelector().className(
					"android.widget.Button").text("OK"));
			saveAlarm.click();
			sleepInt(1);
			LeUiObject alarmList = new LeUiObject(new UiSelector().className(
					"android.widget.ListView").resourceId(
					"com.android.deskclock:id/alarms_list"));
			verify("新闹钟添加不成功", alarmList.exists()
					&& alarmList.getChildCount() == 1);

			addStep("删除闹钟");
			clearAllAlarms();
		}

		addStep("退出应用");
		press_back(5);
	}

	private void clearAllAlarms() throws UiObjectNotFoundException {
		LeUiObject expand = new LeUiObject(new UiSelector().resourceId(
				"com.android.deskclock:id/arrow").description("Expand alarm"));
		LeUiObject delete = new LeUiObject(new UiSelector().resourceId(
				"com.android.deskclock:id/delete").description("Delete alarm"));
		while (expand.exists()) {
			if (delete.exists()) {
				delete.click();
				sleepInt(1);
				continue;
			} else {
				expand.click();
				sleepInt(1);
				verify("Can't find delete alarm button.", delete.exists());
				delete.click();
				sleepInt(1);
			}
		}
	}
}

package com.letv.cases.proto.telephony;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Contacts extends LetvTestCase {	

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		launchAppByPackage(IntentConstants.proto_contacts);
		sleepInt(3);
		super.tearDown();
		clearContacts();
		press_back(5);
	}

	// add contact
	private void addContact() throws UiObjectNotFoundException {
		// find add contact button
		LeUiObject add = new LeUiObject(new UiSelector().resourceId(
				"com.android.contacts:id/floating_action_button").description(
				"add new contact"));
		verify("Can't find add contact button.", add.exists());
		add.click();
		sleepInt(1);
		// LeUiObject confirm = new LeUiObject(new
		// UiSelector().text("确定").resourceId(
		// "com.android.contacts:id/right_button"));
		// if (confirm.exists()) {
		// confirm.click();
		// sleepInt(1);
		// }
		// fill name field
		LeUiObject name = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text("Name"));
		verify("Can't find name field.", name.waitForExists(10000));
		name.setText(getStrParams("contactName"));
//		name.setText("123");
		// fill number field
		LeUiObject number = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text("Phone"));
		verify("Can't find number field.", number.waitForExists(10000));
		number.setText(getStrParams("dialNum"));
//		number.setText("10086");
		// click complete button
		LeUiObject complete = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.contacts:id/save_menu_item"));
		verify("Can't find complete button.", complete.waitForExists(10000));
		complete.click();
		sleepInt(2);
		// confirm add contact success
		addStep("确认联系人新建成功");
		LeUiObject detail = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.contacts:id/photo_touch_intercept_overlay"));
		verify("Add contact failed.", detail.waitForExists(10000));
		// phone.pressBack();
	}

	// delete contact
	private void deleteContact() throws UiObjectNotFoundException {
		LeUiObject edit = new LeUiObject(new UiSelector().resourceId(
				"com.android.contacts:id/menu_edit").description("Edit"));
		verify("Can't find contact edit button.", edit.exists());
		edit.click();
		sleepInt(1);
		LeUiObject more = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").description("More options"));
		verify("Can't find more button.", more.waitForExists(10000));
		more.click();
		sleepInt(1);
		LeUiObject delete = new LeUiObject(new UiSelector().text("Delete")
				.resourceId("android:id/title"));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("OK").resourceId(
				"android:id/button1"));
		verify("Can't find delete confirm button.", confirm.exists());
		confirm.click();
		sleepInt(2);
		// confirm delete success
		addStep("确认联系人删除成功");
		LeUiObject empty = new LeUiObject(new UiSelector().className("android.view.View").index(2));
		verify("delete contact failed.", !empty.waitForExists(5000));
	}

	// clear all the contacts
	private void clearContacts() throws UiObjectNotFoundException {
		LeUiObject list = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId("android:id/list"));
		if (list.waitForExists(5000)) {
			UiObject view = list.getChild(new UiSelector()
					.className("android.view.View"));
			LeUiObject edit = new LeUiObject(new UiSelector().resourceId(
					"com.android.contacts:id/menu_edit").description("Edit"));
			LeUiObject more = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").description("More options"));
			LeUiObject delete = new LeUiObject(new UiSelector().text("Delete")
					.resourceId("android:id/title"));
			LeUiObject confirm = new LeUiObject(new UiSelector().text("OK")
					.resourceId("android:id/button1"));
			for (int i = 0; i < 20; i++) {
				if (view.waitForExists(5000)) {
					view.click();
					sleepInt(1);
					if (edit.waitForExists(5000)) {
						edit.click();
						sleepInt(1);
						if (more.waitForExists(5000)) {
							more.click();
							sleepInt(1);
							if (delete.waitForExists(5000)) {
								delete.click();
								if (confirm.waitForExists(5000)) {
									confirm.click();
									sleepInt(1);
								}
							} else {
								System.out.println("Can't find delete button.");
								continue;
							}
						} else {
							System.out.println("Can't find more button.");
							continue;
						}
					}
				} else {
					break;
				}
			}
		}
	}

	@CaseName("添加删除联系人")
	public void testAddDelContacts() throws UiObjectNotFoundException {
		addStep("打开通讯录");
		launchAppByPackage(IntentConstants.proto_contacts);
		sleepInt(3);
		// find contact image button
		// LeUiObject contact = new LeUiObject(new UiSelector()
		// .packageName("com.android.dialer")
		// .className("android.widget.TabHost")
		// .childSelector(
		// new UiSelector().className("android.widget.ImageView")
		// .resourceId("android:id/icon").index(0)));
		// verify("Can't find contact image button.", contact.exists());
		// contact.click();
		// sleepInt(1);
		addStep("清空联系人");
		clearContacts();
		press_back(5);
		sleepInt(2);
		launchAppByPackage(IntentConstants.proto_contacts);
		sleepInt(3);
		for (int i = 0; i < getIntParams("Loop"); i++) {
//		for (int i = 0; i < 3; i++) {
			addStep("新建联系人");
			addContact();
			addStep("删除联系人");
			deleteContact();
		}
		addStep("退出通讯录");
		press_back(5);
	}
}

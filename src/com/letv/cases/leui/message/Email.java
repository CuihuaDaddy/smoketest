package com.letv.cases.leui.message;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Email extends LetvTestCase {

	private void deleteEmail() throws UiObjectNotFoundException {
		LeUiObject select = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("android:id/right_tv").text("选择"));
		verify("Can't find select button.", select.exists());
		select.click();
		sleepInt(1);
		LeUiObject all = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("android:id/right_tv").text("全选"));
		verify("Can't find all button.", all.exists());
		all.click();
		sleepInt(1);
		LeUiObject delete = new LeUiObject(new UiSelector()
				.resourceId("android:id/icon")
				.className("android.widget.ImageView").index(0).instance(3));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(2);
		LeUiObject empty = new LeUiObject(new UiSelector().resourceId(
				"com.android.email:id/empty_view").text("无邮件。"));
		verify("Can't delete all mail.", empty.exists());
	}

	private void clearAllBox() throws UiObjectNotFoundException {
		launchApp(AppName.EMAIL);
		sleepInt(2);
		LeUiObject email = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("android:id/back_tv").text("邮箱"));
		verify("Can't find email button.", email.exists());
		email.click();
		sleepInt(2);
		LeUiObject inbox = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.email:id/name").text("收件箱"));
		verify("Can't find inbox button.", inbox.exists());
		inbox.click();
		sleepInt(2);
		deleteEmail();
	}

}

package com.letv.cases.proto.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class FileManager extends LetvTestCase {
	
	private final String FOLDER_NAME = "010";

	@CaseName("文件管理器中建立文件夹与删除")
	public void testFolder() throws UiObjectNotFoundException {
		addStep("打开文件管理");
		launchAppByPackage(IntentConstants.filemanager);
		removeFolder();
		LeUiObject phoneStorage = new LeUiObject(new UiSelector().resourceId("com.mediatek.filemanager:id/edit_adapter_name").className(
				"android.widget.TextView").text("Phone storage"));
		verify("Can't find phone storage.", phoneStorage.exists());
		phoneStorage.click();
		sleepInt(2);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("新建文件夹010");
//			phone.pressKeyCode(KEY_MENU);
			sleepInt(2);
			LeUiObject create = new LeUiObject(new UiSelector().resourceId("com.mediatek.filemanager:id/create_folder").className(
					"android.widget.TextView"));
			verify("Can't find create text view.", create.exists());
			create.click();
//			LeUiObject newFolderTag = new LeUiObject(new UiSelector().className(
//					"android.widget.TextView").text("文件夹"));
//			verify("Can't find folder text view.", newFolderTag.exists());
//			newFolderTag.click();
			sleepInt(2);

			LeUiObject folderNameEdit = new LeUiObject(
					new UiSelector().className("android.widget.EditText"));
			verify("Can't find edit text.", folderNameEdit.exists());
			folderNameEdit.setText(FOLDER_NAME);
			sleepInt(2);

			LeUiObject confirmBtn = new LeUiObject(new UiSelector().className(
					"android.widget.Button").text("OK"));
			confirmBtn.click();
			sleepInt(2);

			addStep("确认文件夹建立成功");
			LeUiObject OIOFolder = new LeUiObject(new UiSelector().resourceId(
					"com.mediatek.filemanager:id/edit_adapter_name").text(FOLDER_NAME));
			verify("未能成功建立文件夹", OIOFolder.exists());

			addStep("删除新建的文件夹");
			removeFolder();
		}

		addStep("退出文件管理器");
		press_back(5);
	}

	private void removeFolder() throws UiObjectNotFoundException {
		LeUiObject OIOFolder = new LeUiObject(new UiSelector().resourceId(
				"com.mediatek.filemanager:id/edit_adapter_name").text(FOLDER_NAME));
		if (OIOFolder.exists()) {
			LeUiObject editTag = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").index(2));
			editTag.click();
			sleepInt(2);
			LeUiObject select = new LeUiObject(new UiSelector().resourceId("android:id/title").text("Select file or folder"));
			select.click();
			sleepInt(2);
			OIOFolder.click();
			sleepInt(1);
			LeUiObject delTag = new LeUiObject(new UiSelector().resourceId("com.mediatek.filemanager:id/delete").className(
					"android.widget.TextView"));
			delTag.click();
			sleepInt(2);
			LeUiObject delBtn = new LeUiObject(new UiSelector().resourceId(
					"android:id/button1").text("OK"));
			delBtn.click();
			sleepInt(2);
		}
		verify("删除文件夹失败", !OIOFolder.exists());
	}
}

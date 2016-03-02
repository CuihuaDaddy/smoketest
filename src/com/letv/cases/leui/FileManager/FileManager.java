package com.letv.cases.leui.FileManager;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class FileManager extends LetvTestCase {
	private final String FOLDER_NAME = "010";
	private final String COPY_FOLDER_NAME = "010-copy";
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		
	}
	@CaseName("启动文件管理应用")
	public void testOpenFileManager() throws UiObjectNotFoundException {
		
		addStep("Step1:启动文件管理应用");
		launchApp(AppName.FILEMANAGER);
		sleepInt(3);
		LeUiObject PhoneMemory = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/mobile_storage_name")
				.text("手机存储"));
		verify("没有进入到文件管理器.", PhoneMemory.exists());
		sleepInt(2);
		addStep("step2:home键后台，再次点击");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.FILEMANAGER);
		sleepInt(2);
		verify("没有进入到文件管理器.", PhoneMemory.exists());
		addStep("step3:home键后台调用多任务，多任务中点击文件管理");
		press_home(1);
		sleepInt(2);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
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
		sleepInt(3);
		launchApp(AppName.FILEMANAGER);
		sleepInt(2);
		verify("没有进入到文件管理器.", PhoneMemory.exists());
		addStep("step3:home键后台文件管理调用多任务，多任务中点击文件管理");
		press_home(1);
		sleepInt(2);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		press_menu(1);
		sleepInt(2);
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
		verify("没有进入到文件管理器.", PhoneMemory.exists());
		addStep("退出应用");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	@CaseName("查看、移动、删除、新建文件/文件夹")
	public void testEditFileManager() throws UiObjectNotFoundException {
		addStep("打开文件管理");
		launchApp(AppName.FILEMANAGER);
		sleepInt(5);
		LeUiObject PhoneMemory1 = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/mobile_storage_name")
				.text("Phone memory"));
		LeUiObject PhoneMemory2 = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/mobile_storage_name")
				.text("手机存储"));
		verify("没有进入到文件管理器.", PhoneMemory1.exists()||PhoneMemory2.exists());
		if(PhoneMemory1.exists()){
			PhoneMemory1.click();
		}else{
			PhoneMemory2.click();
		}
		sleepInt(4);
		addStep("新建文件夹010");
		LeUiObject newFolderTag = new LeUiObject(new UiSelector().resourceId("com.letv.android.filemanager:id/category_mkdir_menu").className("android.widget.TextView"));
		newFolderTag.click();
		sleepInt(1);
		LeUiObject folderNameEdit = new LeUiObject(
		new UiSelector().className("android.widget.EditText"));
		folderNameEdit.setText(FOLDER_NAME);
		sleepInt(3);
		LeUiObject confirmBtn1 = new LeUiObject(new UiSelector().resourceId("android:id/button1").text("yes"));
		LeUiObject confirmBtn2 = new LeUiObject(new UiSelector().resourceId("android:id/button1").text("新建"));
		verify("确定按钮不存在",confirmBtn1.exists()||confirmBtn2.exists());
		if(confirmBtn1.exists()){
			confirmBtn1.click();
		}else {
			confirmBtn2.click();
		}
		
		sleepInt(1);
		addStep("确认文件夹建立成功");
		LeUiObject OIOFolder = new LeUiObject(
			new UiSelector()
				.className("android.widget.TextView")
					.resourceId(
						"com.letv.android.filemanager:id/navigation_view_item_name")
						.text(FOLDER_NAME));
		verify("未能成功建立文件夹", OIOFolder.exists());
		
		
		addStep("新建文件夹copy-010");
		newFolderTag.click();
		sleepInt(1);
		folderNameEdit.setText(COPY_FOLDER_NAME);
		sleepInt(3);
		verify("确定按钮不存在",confirmBtn1.exists()||confirmBtn2.exists());
		if(confirmBtn1.exists()){
			confirmBtn1.click();
		}else {
			confirmBtn2.click();
		}
		
		sleepInt(3);
		addStep("确认文件夹建立成功");
		LeUiObject CopyOIOFolder = new LeUiObject(
				new UiSelector()
					.className("android.widget.TextView")
						.resourceId(
							"com.letv.android.filemanager:id/navigation_view_item_name")
							.text(COPY_FOLDER_NAME));
		verify("未能成功copy文件夹", CopyOIOFolder.exists());
		phone.swipe(550, 550, 550, 1300, 100);
		
		addStep("移动010文件夹到copy_010文件夹内");
		int dx = OIOFolder.getBounds().centerX();
		int dy = OIOFolder.getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		LeUiObject copy = new LeUiObject(new UiSelector().className("android.widget.FrameLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
						.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
								.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
										.childSelector(new UiSelector().className("android.widget.ImageView").index(0))))));
		verify("Can't find copy button.", copy.exists());
		sleepInt(2);
		copy.click();
		sleepInt(3);
		verify("010-copy文件夹没有找到",CopyOIOFolder.exists());
		CopyOIOFolder.click();
		sleepInt(2);
		LeUiObject move = new LeUiObject(new UiSelector().resourceId("com.letv.android.filemanager:id/btn_commit")
				.className("android.widget.Button").index(1));
		verify("Can't find move button.", move.exists());
		move.click();
		sleepInt(3);
		verify("未能成功移动文件夹010到copy_010文件夹",!OIOFolder.exists());
		sleepInt(2);
		addStep("删除新建的文件夹");
		removeFolder();
		addStep("退出文件管理器");
		press_back(3);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	private void removeFolder() throws UiObjectNotFoundException {
		LeUiObject CopyOIOFolder = new LeUiObject(
				new UiSelector()
						.className("android.widget.TextView")
						.resourceId(
								"com.letv.android.filemanager:id/navigation_view_item_name")
						.text(COPY_FOLDER_NAME));
		if (CopyOIOFolder.exists()) {
			int dx = CopyOIOFolder.getBounds().centerX();
			int dy = CopyOIOFolder.getBounds().centerY();
			phone.swipe(dx, dy, dx, dy, 100);
			sleepInt(2);
			LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.FrameLayout").index(0)
					.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
							.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
									.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)
											.childSelector(new UiSelector().className("android.widget.ImageView").index(0))))));
			verify("Can't find delete button.", delete.exists());
			delete.click();
			sleepInt(2);
			/*LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/button1").index(1));
			verify("Can't find confirm button.", confirm.exists());
			confirm.click();*/
		}
		verify("删除文件夹失败", !CopyOIOFolder.exists());
	}
}

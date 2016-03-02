package com.letv.cases.leui.multimedia;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Recoder extends LetvTestCase {


	@CaseName("音频文件的录制、打开和删除")
	public void testRecodeAudio() throws UiObjectNotFoundException {
		launchApp(AppName.RECORDER);
		LeUiObject emptyTag = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("No new recordings"));
		LeUiObject audioList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.letv.android.recorder:id/record_list"));
		if (!emptyTag.exists()) {
			addStep("清空所有录音");
			clearAudioFile();
		}
		verify("录音记录不为空", emptyTag.exists());

		addStep("录制5秒音频");
		LeUiObject recordBtn = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.recorder:id/recordBtn").className(
				"android.widget.ImageView"));
		recordBtn.click();
		sleepInt(5);
		LeUiObject stopBtn = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.recorder:id/stopBtn").className(
				"android.widget.ImageView"));
		verify("未能开始录音", stopBtn.exists());

		addStep("保存音频");
		stopBtn.click();
		sleepInt(1);
		verify("未能保存录音", audioList.exists() && audioList.getChildCount() == 1);

		addStep("播放录音");
		audioList.getChild(
				new UiSelector().className("android.widget.LinearLayout")
						.index(0)).click();
		sleepInt(1);
		LeUiObject playSeekBar = new LeUiObject(new UiSelector().className(
				"android.view.View").resourceId(
				"com.letv.android.recorder:id/play_seekbar"));
		verify("未能播放录音", playSeekBar.exists());
		press_back(1);

		addStep("清空录音文件");
//		clearAudioFile();
		
		int dx = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerX();
		int dy = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		sleepInt(2);
		LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/button1").text("Delete"));
		verify("Can't find confirm button.", confirm.exists());
		confirm.click();
		sleepInt(1);
		verify("删除录音失败", emptyTag.exists());
		
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	private void clearAudioFile() throws UiObjectNotFoundException {
		LeUiObject audioList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.letv.android.recorder:id/record_list"));
		int dx = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerX();
		int dy = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		for (int i = 0; i < audioList.getChildCount(); i++) {
			sleepInt(2);			
			LeUiObject itemCheckbox = new LeUiObject(new UiSelector().className(
					"android.widget.ListView").resourceId(
							"com.letv.android.recorder:id/record_list").childSelector(
					new UiSelector().className("android.widget.LinearLayout")
							.index(i).childSelector(
					new UiSelector()
							.className("com.letv.leui.widget.LeCheckBox"))));
			if (!itemCheckbox.isChecked()) {
				itemCheckbox.click();
				sleepInt(1);
			}
		}
		LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/button1").text("Delete"));
		verify("Can't find confirm button.", confirm.exists());
		confirm.click();
		sleepInt(1);
	}

	private void clearDcim() throws UiObjectNotFoundException {
		launchApp(AppName.FILEMANAGER);
		sleepInt(2);
		LeUiObject PhoneMemory = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/mobile_storage_name")
				.text("Phone memory"));
		verify("Can't find Phone Memory.", PhoneMemory.exists());
		PhoneMemory.click();
		sleepInt(2);
		LeUiObject dcim = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/navigation_view_item_name")
				.text("DCIM"));
		verify("Can't find dcim folder.", dcim.exists());
		dcim.click();
		sleepInt(1);
		LeUiObject camera = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/navigation_view_item_name")
				.text("Camera"));
		if(camera.exists()){
			int dx = camera.getBounds().centerX();
			int dy = camera.getBounds().centerY();
			phone.swipe(dx, dy, dx, dy, 100);
			sleepInt(2);
			LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(3));
			verify("Can't find delete button.", delete.exists());
			delete.click();
			sleepInt(1);
			LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/button1").index(2));
			verify("Can't find confirm button.", confirm.exists());
			confirm.click();
			sleepInt(1);
		}else {
			press_back(5);
			return;
		}

/*		verify("Can't find camera folder.", camera.exists());	
		camera.click();
		sleepInt(1);
		LeUiObject listView = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.letv.android.filemanager:id/navigation_view_layout"));
		if (listView.exists()) {
			LeUiObject edit = new LeUiObject(new UiSelector().resourceId(
					"com.letv.android.filemanager:id/actionbar_checkbox").text(
					"编辑"));
			verify("Can't find edit button.", edit.exists());
			edit.click();
			LeUiObject all = new LeUiObject(new UiSelector().resourceId(
					"com.letv.android.filemanager:id/actionbar_se_all").text(
					"全选"));
			verify("Can't find select all button.", all.exists());
			all.click();
			LeUiObject delete = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("删除"));
			verify("Can't find delete button.", delete.exists());
			delete.click();
			LeUiObject delBtn = new LeUiObject(new UiSelector().resourceId(
					"android:id/button1").text("删除"));
			verify("Can't find confirm button.", delBtn.exists());
			delBtn.click();
			press_back(5);
		} else {
			press_back(5);
			return;
		}*/
	}

	@CaseName("录制视频")
	public void testRecordVideo() throws UiObjectNotFoundException {
		addStep("打开相机，切换到录像模式");
		launchApp(AppName.CALENDAR);
		sleepInt(2);
		callShell("input swipe 100 900 700 900");
		sleepInt(2);
		LeUiObject mode_video = new LeUiObject(new UiSelector()
				.resourceId("com.android.camera2:id/mode_video").text("Video")
				.selected(true));
		verify("Can't switch to video mode.", mode_video.exists());
		sleepInt(2);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("点击开始录像60S");
			LeUiObject record = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/stop"));
			verify("Can't find record button.", record.exists());
			record.click();
			sleepInt(60);
			record.click();
			sleepInt(2);
			addStep("播放录像10S");
			LeUiObject thumb = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/preview_thumb_frame"));
			verify("Can't find thumb button.", thumb.exists());
			thumb.click();
			sleepInt(2);
			phone.click(568, 966);
			sleepInt(10);
			press_back(1);
			sleepInt(1);
			press_back(1);
			sleepInt(2);
		}
		addStep("删除录像");
		clearDcim();
		addStep("退出相机");
		press_back(1);
	}

	@CaseName("拍照")
	public void testTakePicture() throws UiObjectNotFoundException {
		addStep("打开相机，切换到照相模式");
		launchApp(AppName.CALENDAR);
		sleepInt(2);
		LeUiObject mode_photo = new LeUiObject(new UiSelector()
				.resourceId("com.android.camera2:id/mode_photo").text("Camera")
				.selected(true));
		verify("Can't switch to photo mode.", mode_photo.exists());
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("点击拍照");
			LeUiObject shutter = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/shutter_button"));
			verify("Can't find shutter button.", shutter.exists());
			shutter.click();
			sleepInt(4);
			addStep("查看照片");
			LeUiObject thumb = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/preview_thumb_frame"));
			verify("Can't find thumb button.", thumb.exists());
			thumb.click();
			sleepInt(2);
			press_back(1);
			sleepInt(2);
		}
		addStep("删除照片");
		clearDcim();
		addStep("退出相机");
		press_back(1);
	}
}

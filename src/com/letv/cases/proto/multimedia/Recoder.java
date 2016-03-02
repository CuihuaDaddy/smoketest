package com.letv.cases.proto.multimedia;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Recoder extends LetvTestCase {

	@CaseName("音频文件的录制、打开和删除")
	public void testRecodeAudio() throws UiObjectNotFoundException {
		LeUiObject recorderApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Sound Recorder").description("Sound Recorder"));
		launchApp("录音机");
		LeUiObject listTag = new LeUiObject(new UiSelector().resourceId("com.android.soundrecorder:id/fileListButton").className("android.widget.ImageButton"));
		LeUiObject audioList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.android.soundrecorder:id/recording_file_list_view"));
				verify("没有录音列表按钮", listTag.exists());
		listTag.click();
		LeUiObject emptyTag = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("(No recording file)").resourceId("com.android.soundrecorder:id/empty_view"));
		if (!emptyTag.exists()) {
			addStep("清空所有录音");
			clearAudioFile();
		}
		verify("录音记录不为空", emptyTag.exists());

		addStep("录制5秒音频");
		LeUiObject recordBtn = new LeUiObject(new UiSelector().resourceId(
				"com.android.soundrecorder:id/recordButton").className(
				"android.widget.ImageButton"));
		recordBtn.click();
//		sleepInt(5);
		LeUiObject stopBtn = new LeUiObject(new UiSelector().resourceId(
				"com.android.soundrecorder:id/stopButton").className(
				"android.widget.ImageButton"));
		verify("未能开始录音", stopBtn.exists());
		addStep("保存音频");
		stopBtn.click();
		sleepInt(1);
		LeUiObject saveBtn = new LeUiObject(new UiSelector().resourceId(
				"com.android.soundrecorder:id/acceptButton").className(
				"android.widget.Button").text("Save"));
		saveBtn.click();
		sleepInt(1);
		listTag.click();
		sleepInt(1);
		verify("未能保存录音", audioList.exists() && audioList.getChildCount() == 1);

		addStep("播放录音");
		audioList.getChild(
				new UiSelector().className("android.widget.LinearLayout")
						.index(0)).click();
		LeUiObject playSeekBar = new LeUiObject(new UiSelector().className(
				"android.widget.ProgressBar").resourceId(
				"com.android.soundrecorder:id/stateProgressBar"));
		verify("未能播放录音", playSeekBar.exists());
		sleepInt(20);
		addStep("清空录音文件");
		listTag.click();
		clearAudioFile();
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	private void clearAudioFile() throws UiObjectNotFoundException {
		LeUiObject audioList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.android.soundrecorder:id/recording_file_list_view"));
		UiObject file = audioList.getChild(new UiSelector().className(
				"android.widget.LinearLayout").index(0));
		int dx = file.getBounds().centerX();
		int dy = file.getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		sleepInt(1);
		for (int i = 1; i < audioList.getChildCount(); i++) {
			UiObject itemCheckbox = audioList.getChild(
					new UiSelector().className("android.widget.LinearLayout")
							.index(i)).getChild(
					new UiSelector()
							.className("android.widget.CheckBox"));
			if (!itemCheckbox.isChecked()) {
				itemCheckbox.click();
				sleepInt(1);
			}
		}
		LeUiObject delTag = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId("com.android.soundrecorder:id/deleteButton"));
		delTag.click();
		sleepInt(1);
		LeUiObject okBtn = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("OK").resourceId("android:id/button1"));
		okBtn.click();
		sleepInt(1);
	}

	private void clearDcim() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.proto_filemanager);
		sleepInt(2);
		LeUiObject dcim = new LeUiObject(new UiSelector().resourceId(
				"com.rhmsoft.fm:id/name").text("DCIM"));
		LeUiObject camera = new LeUiObject(new UiSelector().resourceId(
				"com.rhmsoft.fm:id/name").text("Camera"));
		LeUiObject listView = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.rhmsoft.fm:id/entryList"));
		if(dcim.waitForExists(5000)){
			dcim.click();
			sleepInt(1);
			if(camera.waitForExists(5000)){
				camera.click();
				sleepInt(4);
				if (listView.exists()) {
					LeUiObject edit = new LeUiObject(new UiSelector().className(
							"android.widget.Button").text("Multi"));
					verify("Can't find edit button.", edit.waitForExists(5000));
					edit.click();
					LeUiObject all = new LeUiObject(new UiSelector().className(
							"android.widget.Button").text("Select All"));
					verify("Can't find select all button.", all.waitForExists(5000));
					all.click();
					LeUiObject delete = new LeUiObject(new UiSelector().className(
							"android.widget.Button").text("Delete"));
					verify("Can't find delete button.", delete.waitForExists(5000));
					delete.click();
					LeUiObject confirm = new LeUiObject(new UiSelector().resourceId(
							"com.rhmsoft.fm:id/button1").text("OK"));
					verify("Can't find confirm button.", confirm.waitForExists(5000));
					confirm.click();
					press_back(5);
				} else {
					press_back(5);
					return;
				}
			}else{
				System.out.println("Can't find camera folder.");
			}
		}else{
			System.out.println("Can't find dcim folder.");
		}
	}

	@CaseName("录制视频")
	public void testRecordVideo() throws UiObjectNotFoundException {
		clearDcim();
		addStep("打开相机，切换到录像模式");
		LeUiObject CameraApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Camera").description("Camera"));
		launchApp("相机");
/*	    launchAppByPackage(IntentConstants.proto_camera);
		sleepInt(2);
		LeUiObject RePhoto = new LeUiObject(new UiSelector().resourceId(
				"android:id/alertTitle").className("android.widget.TextView").textContains("Remember"));
		LeUiObject NoThanks = new LeUiObject(new UiSelector().resourceId(
				"android:id/button2").textContains("No"));		
		if(RePhoto.exists()){
			sleep(2000);
			NoThanks.click();
			sleep(2000);
		}
		callShell("input swipe 100 900 600 900");
		LeUiObject mode_video = new LeUiObject(new UiSelector().resourceId(
				"com.android.camera2:id/mode_video").text("Video"));
		verify("Can't switch to video mode.", mode_video.waitForExists(5000));
		// int dx = mode_video.getBounds().centerX();
		// int dy = mode_video.getBounds().centerY();
		// callShell("input tap " + dx + " " + dy);
		 * 
		 */
		sleepInt(2);
		for (int i = 0; i < getIntParams("Loop"); i++) {
//		for (int i = 0; i < 2; i++) {
			addStep("点击开始录像60S");
			LeUiObject record = new LeUiObject(new UiSelector().resourceId(
					"com.android.gallery3d:id/shutter_button_video")
					.description("Video shutter button"));
			verify("Can't find record button.", record.waitForExists(10000));
			record.click();
			sleepInt(60);
			record.click();
			sleepInt(3);
			addStep("播放录像10S");
			// callShell("input swipe 600 900 100 900");
			LeUiObject thumb = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.gallery3d:id/thumbnail"));
			verify("Can't find thumb button.", thumb.exists());
			thumb.click();
			sleepInt(2);
			phone.click(548, 988);
			sleepInt(10);
			press_back(1);
			sleepInt(2);
			press_back(1);
		}
		addStep("删除录像");
		clearDcim();
		addStep("退出相机");
		press_back(1);
	}

	@CaseName("拍照")
	public void testTakePicture() throws UiObjectNotFoundException {
		clearDcim();
		addStep("打开相机，切换到照相模式");
		LeUiObject CameraApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Camera").description("Camera"));
		launchApp("相机");
		sleep(3000);
		LeUiObject RePhoto = new LeUiObject(new UiSelector().resourceId(
				"android:id/alertTitle").className("android.widget.TextView").textContains("Remember"));
		LeUiObject NoThanks = new LeUiObject(new UiSelector().resourceId(
				"android:id/button2").textContains("No"));		
		if(RePhoto.exists()){
			sleep(2000);
			NoThanks.click();
			sleep(2000);
		}
		for (int i = 0; i < getIntParams("Loop"); i++) {
			sleep(3000);
			addStep("点击拍照");
			LeUiObject shutter = new LeUiObject(new UiSelector().resourceId(
					"com.android.gallery3d:id/shutter_button_photo").description(
					"Shutter button"));
			verify("Can't find shutter button.", shutter.exists());
			shutter.click();
			sleepInt(4);
			addStep("查看照片");
			LeUiObject thumb = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.gallery3d:id/thumbnail"));
			verify("Can't find thumb button.", thumb.exists());
			thumb.click();
			sleepInt(3);
			LeUiObject image = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.gallery3d:id/gl_root_view"));
			LeUiObject Gallery = new LeUiObject(
					new UiSelector()
							.resourceId("android:id/text1").className("android.widget.TextView").text("Gallery"));
			LeUiObject OpenWithGallery = new LeUiObject(
					new UiSelector()
							.resourceId("android:id/title").className("android.widget.TextView").text("Open with Gallery"));
			if(Gallery.exists()||OpenWithGallery.exists()){
				if(Gallery.exists()){
					Gallery.click();
				}
				sleep(2000);
				LeUiObject ALWAYS = new LeUiObject(
						new UiSelector()
								.text("Always"));
				ALWAYS.click();
				sleep(1000);
			}
			verify("Can't find photo.", image.exists());
			image.click();
			addStep("删除照片");
			LeUiObject moreBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").description("More options"));
			if(!moreBtn.exists()){
				image.click();
			}
			verify("未找到更多按钮", moreBtn.waitForExists(5000));
			moreBtn.click();
			sleep(1000);
			LeUiObject delete = new LeUiObject(new UiSelector().resourceId(
					"android:id/title")
					.text("Delete"));
			verify("Can't find delete button.", delete.waitForExists(5000));
			delete.click();
			sleepInt(1);			
			LeUiObject done = new LeUiObject(new UiSelector().className(
					"android.widget.Button").text("OK"));
			verify("未找到确认按钮", done.waitForExists(5000));
			done.click();
			sleep(4000);
			press_back(1);
		}
		addStep("删除照片");
		clearDcim();
		addStep("退出相机");
		press_back(1);
	}
}

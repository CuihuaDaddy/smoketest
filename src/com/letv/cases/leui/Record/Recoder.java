package com.letv.cases.leui.Record;

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
		sleepInt(3);
		LeUiObject emptyTag1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("No new recordings"));
		LeUiObject emptyTag2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("暂无录音记录"));
		LeUiObject emptyTag3 = new LeUiObject(new UiSelector().className(
				"android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().className("android.view.View").index(0)));
		
		if (!(emptyTag1.exists()||emptyTag2.exists()||emptyTag3.exists())) {
			addStep("清空所有录音");
			clearAudioFile();
		}
		verify("录音记录不为空", emptyTag1.exists()||emptyTag2.exists()||emptyTag3.exists());

		addStep("录制5秒音频");
		LeUiObject recordBtn = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.recorder:id/recordBtn").className(
				"android.widget.ImageView"));
		recordBtn.click();
		sleepInt(5);
		
		/*
		 * Recording UI is changing, UIAutoMator can't catch moving element
		 */
		phone.click(733, 2333);
		sleepInt(1);
		
		LeUiObject stopBtn = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.recorder:id/stopBtn").className(
				"android.widget.ImageView"));
		verify("未能开始录音", stopBtn.exists());

		addStep("保存音频");
		stopBtn.click();
		sleepInt(1);
		LeUiObject audioList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.letv.android.recorder:id/record_list"));
		verify("未能保存录音", audioList.exists() && audioList.getChildCount() == 1);

		addStep("播放录音");
		LeUiObject play = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.letv.android.recorder:id/record_list").childSelector(
				new UiSelector().className("android.widget.LinearLayout")
						.index(0)));
		verify("play的按钮不存在",play.exists());
		play.click();
		sleepInt(1);
		LeUiObject playSeekBar = new LeUiObject(new UiSelector().className(
				"android.view.View").resourceId(
				"com.letv.android.recorder:id/play_seekbar"));
		verify("未能播放录音", playSeekBar.exists());
		press_back(1);

		addStep("分享录音");
		shareAudioFile();		
		
		addStep("退出应用");
		press_back(3);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	private void shareAudioFile() throws UiObjectNotFoundException {
		LeUiObject audioList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.letv.android.recorder:id/record_list"));
		int dx = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerX();
		int dy = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		sleepInt(2);
		/*LeUiObject share = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
								.childSelector(new UiSelector().className("android.widget.ImageView").index(0)))));*/
		LeUiObject share = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0))));
		verify("Can't find share button.", share.exists());
		share.click();
		sleepInt(1);
		LeUiObject sharetitleMsg1 = new LeUiObject(new UiSelector().resourceId("android:id/share_title").index(1).text("Messaging"));
		LeUiObject sharetitleMsg2 = new LeUiObject(new UiSelector().resourceId("android:id/share_title").index(1).text("信息"));

		verify("Can't find share title Msg", sharetitleMsg1.exists()||sharetitleMsg2.exists());
		LeUiObject sharetitleBT1 = new LeUiObject(new UiSelector().resourceId("android:id/share_title").index(1).text("Bluetooth"));
		LeUiObject sharetitleBT2 = new LeUiObject(new UiSelector().resourceId("android:id/share_title").index(1).text("蓝牙"));
		verify("Can't find share title BT", sharetitleBT1.exists()||sharetitleBT2.exists());
		sleepInt(1);
	
	}
	private void clearAudioFile() throws UiObjectNotFoundException {
		LeUiObject audioList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView"));
		if(!audioList.exists()){
			return ;
		}
		int dx = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerX();
		int dy = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		/*for (int i = 0; i < audioList.getChildCount(); i++) {
			sleepInt(2);			
			LeUiObject itemCheckbox = new LeUiObject(new UiSelector().className(
					"android.widget.ListView").childSelector(
					new UiSelector().className("android.widget.LinearLayout")
							.index(i)).childSelector(
					new UiSelector()
							.className("com.letv.leui.widget.LeCheckBox")));
			if (!itemCheckbox.isChecked()) {
				itemCheckbox.click();
				sleepInt(1);
			}
		}*/
		sleepInt(2);
        //select all or unselect all
		LeUiObject selectAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("全选"));
		LeUiObject unselectAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("全不选"));
		verify("全选或者全不选不存在",selectAll.exists()||unselectAll.exists());
		if(!unselectAll.exists()){
			selectAll.click();
		}
		
		sleepInt(2);
		
		LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(1);
		LeUiObject confirm1 = new LeUiObject(new UiSelector().resourceId("android:id/button1").text("Delete"));
		LeUiObject confirm2 = new LeUiObject(new UiSelector().textContains("删除"));
		verify("Can't find confirm button.", confirm1.exists()||confirm2.exists());
		if(confirm1.exists()){
			confirm1.click();
		}else{
			confirm2.click();
		}
		
		sleepInt(1);
	}
}

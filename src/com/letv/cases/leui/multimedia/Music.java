package com.letv.cases.leui.multimedia;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Music extends LetvTestCase {

	private String music1 = "California Dreaming";
	private String music2 = "Can you feel it";
	private String musicPackage = "com.android.music";

	@CaseName("打开音乐播放器，播放音乐，关闭音乐播放器")
	public void testPlayMusic() throws UiObjectNotFoundException {
		addStep("打开音乐播放器");
		LeUiObject Music = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Music").description("Music"));
		launchApp(AppName.MUSIC);
		sleepInt(2);
		LeUiObject myMusic = new LeUiObject(new UiSelector().className(
				"android.widget.RelativeLayout").index(0).clickable(true));
		verify("Can't find My music.", myMusic.exists());
		myMusic.click();
		sleepInt(1);
		addStep("选择歌曲播放5秒");
		LeUiObject localMusic = new LeUiObject(new UiSelector().resourceId(
		"com.android.music:id/entry_local_music"));
		verify("Can't find local music.", localMusic.exists());
		localMusic.click();
		sleepInt(2);
		LeUiObject SongTag = new LeUiObject(new UiSelector().className(
				"android.widget.RelativeLayout").index(0).clickable(true));
		verify("Can't find Song Tag.", SongTag.exists());
		SongTag.click();
		sleepInt(1);
		LeUiObject scan = new LeUiObject(new UiSelector().resourceId(
		"com.android.music:id/scan").text("扫描添加"));
		if (scan.exists()) {
			scan.click();
			sleepInt(2);
			LeUiObject start_scan = new LeUiObject(new UiSelector().resourceId(
					"com.android.music:id/start_scan").text("一键扫描"));
			verify("Can't find start scan button.", start_scan.exists());
			start_scan.click();
			sleepInt(5);
			LeUiObject add_all = new LeUiObject(new UiSelector().resourceId(
					"com.android.music:id/add_all").text("添加"));
			if (add_all.exists()) {
				add_all.click();
				sleepInt(5);
			} else {
				press_back(1);
			}
		}
		addStep("至少2首音乐被交替循环播放20遍");
		for (int i = 0; i < getIntParams("Loop"); i++) {
			LeUiObject m1 = new LeUiObject(new UiSelector().resourceId(
					"com.android.music:id/track_name").text(music1));
			verify("Can't find music " + music1, m1.exists());
			m1.click();
			sleepInt(5);
			LeUiObject m2 = new LeUiObject(new UiSelector().resourceId(
					"com.android.music:id/track_name").text(music2));
			verify("Can't find music " + music2, m2.exists());
			m2.click();
			sleepInt(5);
		}
		addStep("关闭音乐播放器");
		callShell("am force-stop " + musicPackage);
		press_back(5);
	}

}

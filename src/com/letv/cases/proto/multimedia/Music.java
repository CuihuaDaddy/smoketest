package com.letv.cases.proto.multimedia;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Music extends LetvTestCase {

	private String music1 = "California Dreaming";
	private String music2 = "Can you feel it";
	private String musicPackage = "com.android.music";

	@CaseName("打开音乐播放器，播放音乐，关闭音乐播放器")
	public void testPlayMusic() throws UiObjectNotFoundException {
		addStep("打开音乐播放器");
		launchAppByPackage(IntentConstants.music);
		sleepInt(2);
		addStep("选择歌曲播放5秒");
		LeUiObject localMusic = new LeUiObject(new UiSelector().resourceId(
				"com.android.music:id/songtab").text("Songs"));
		verify("Can't find local music.", localMusic.exists());
		localMusic.click();
		sleepInt(2);
		addStep("至少2首音乐被交替循环播放20遍");
		for (int i = 0; i < getIntParams("Loop"); i++) {
			LeUiObject m1 = new LeUiObject(new UiSelector().resourceId(
					"com.android.music:id/line1").text(music1));
			verify("Can't find music " + music1, m1.exists());
			m1.click();
			sleepInt(5);
			press_back(1);
			LeUiObject m2 = new LeUiObject(new UiSelector().resourceId(
					"com.android.music:id/line1").text(music2));
			verify("Can't find music " + music2, m2.exists());
			m2.click();
			sleepInt(5);
			press_back(1);
		}
		addStep("关闭音乐播放器");
		callShell("am force-stop " + musicPackage);
		press_back(5);
	}

}

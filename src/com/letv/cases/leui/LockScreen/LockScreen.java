package com.letv.cases.leui.LockScreen;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class LockScreen extends LetvTestCase {

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

	@CaseName("lock screen")
	public void testTouchPanel() throws UiObjectNotFoundException, Exception {
		addStep("step1:短按power键锁屏");
		phone.pressKeyCode(KEY_POWER);
		sleepInt(3);
		verify("手机锁屏幕失败",!phone.isScreenOn());
		addStep("step2:滑动解锁");
		if (!phone.isScreenOn()) {
			phone.wakeUp();
			sleepInt(1);
			unLockDevice();
		}
		sleepInt(2);
		verify("手机滑动解锁失败",phone.isScreenOn());
		addStep("step3:滑动进入相机程序");
		phone.pressKeyCode(KEY_POWER);
		sleepInt(3);
		if (phone.isScreenOn()) {
			phone.pressKeyCode(KEY_POWER);
		}
		if (!phone.isScreenOn()) {
			phone.wakeUp();
			sleepInt(1);
			phone.swipe(1378, 2530, 1378, 300, 80);
		}
		sleepInt(5);
		LeUiObject shutter = new LeUiObject(new UiSelector().resourceId(
				"com.android.camera2:id/shutter_button").index(0));
		verify("没有进入到相机界面", shutter.exists());
		
	}
}
	
	
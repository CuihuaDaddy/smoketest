package com.letv.cases.leui.Camera;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class CameraSwitchVideo extends LetvTestCase {
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

	@CaseName("相机和摄像机模式切换")
	public void testCameraSwitchVideo() throws UiObjectNotFoundException {
		addStep("打开相机，切换到录像模式");
		launchApp(AppName.CAMERA);
		sleepInt(4);
		LeUiObject mode_video1 = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").text("Video")
				.selected(true));
		LeUiObject mode_video2 = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").text("视频")
				.selected(true));
		if (!(mode_video1.exists() || mode_video2.exists())) {
			callShell("input swipe 200 900 1000 900");
		}
		
		sleepInt(2);
		verify("Can't switch to video mode.", mode_video1.exists()||mode_video2.exists());
		sleepInt(3);
		addStep("切换到照相模式");
		callShell("input swipe 1000 900 200 900");
		
		LeUiObject mode_camera1 = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").text("Camera")
				.selected(true));
		LeUiObject mode_camera2 = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").text("拍照")
			.selected(true));
		for (int i = 0; i < 15; i++) {
			if((mode_camera1.exists())||(mode_camera2.exists())){
				break;
			}
			sleepInt(1);
		}
		verify("Can't switch to Camrea mode.", mode_camera1.exists()||mode_camera2.exists());
		sleepInt(3);
		addStep("切换到录像模式");
		callShell("input swipe 200 900 1000 900");
		sleepInt(4);
		verify("Can't switch to video mode.", mode_video1.exists()||mode_video2.exists());
		sleepInt(3);
		addStep("切换到照相模式");
		callShell("input swipe 1000 900 200 900");
		sleepInt(4);
		verify("Can't switch to Camrea mode.", mode_camera1.exists()||mode_camera2.exists());
		sleepInt(3);
		addStep("退出相机");
		press_back(4);
		sleepInt(2);
	}
}

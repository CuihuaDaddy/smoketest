package com.letv.cases.leui.Camera;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Camera extends LetvTestCase {

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	@CaseName("多场景拍摄图片")
	public void testSceneMode() throws UiObjectNotFoundException {
		addStep("Step1:打开相机");
		launchApp(AppName.CAMERA);
		sleepInt(3);
		addStep("进入相机设置");
		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/shutter_button"));
		LeUiObject Csetting = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/camera_setting_btn"));
		verify("Can't find camera setting button.", Csetting.exists());
		Csetting.click();
		sleepInt(4);
		addStep("滑动相机设置界面");
		LeUiObject pos = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("夜景"));
		verify("未能进入相机设置",pos.exists());
		phone.swipe(1220, 980, 43, 980, 50);
		sleepInt(2);
		LeUiObject sceneMode2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("场景模式"));
		verify("Can't find scene Mode button.", sceneMode2.exists());
		addStep("进入场景模式选择");
		sceneMode2.click();
		sleepInt(2);
		
		//自动模式拍照
		addStep("点击自动模式");
		LeUiObject auto = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("自动"));
		verify("自动模式没有找到",auto.exists());
		auto.click();
		sleepInt(3);
		addStep("点击拍照");
		verify("Can't find shutter button.", shutter.exists());
		shutter.click();
		sleepInt(6);
		addStep("进入相机设置");
		verify("Can't find camera setting button.", Csetting.exists());
		Csetting.click();
		sleepInt(4);
		addStep("滑动相机设置界面");
		verify("未能进入相机设置",pos.exists());
		phone.swipe(1220, 980, 43, 980, 50);
		sleepInt(2);
		verify("Can't find scene Mode button.", sceneMode2.exists());
		addStep("进入场景模式选择");
		sceneMode2.click();
		sleepInt(2);
		
		//肖像模式拍照
		addStep("点击肖像模式");
		LeUiObject PortraitMode2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("肖像"));
/*		LeUiObject PortraitMode = new LeUiObject(new UiSelector().resourceId(
				"com.android.camera2:id/subSettingGrid").index(0)
				.childSelector(new UiSelector().resourceId("com.android.camera2:id/setting_item").index(2)
						.childSelector(new UiSelector().resourceId("com.android.camera2:id/setting_item_title").index(1))));*/
		verify("肖像模式不存在",(PortraitMode2.exists()));
		PortraitMode2.click();
		sleepInt(3);
		addStep("点击拍照");
		verify("Can't find shutter button.", shutter.exists());
		shutter.click();
		sleepInt(6);
		addStep("进入相机设置");
		verify("Can't find camera setting button.", Csetting.exists());
		Csetting.click();
		sleepInt(4);
		addStep("滑动相机设置界面");
		verify("未能进入相机设置",pos.exists());
		phone.swipe(1220, 980, 43, 980, 50);
		sleepInt(2);
		verify("Can't find scene Mode button.", sceneMode2.exists());
		addStep("进入场景模式选择");
		sceneMode2.click();
		sleepInt(2);
		
		//风景模式拍照
		addStep("点击风景模式");
		LeUiObject LandscapeMode2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("风景"));
		verify("风景模式不存在",LandscapeMode2.exists());
		LandscapeMode2.click();
		sleepInt(3);
		addStep("点击拍照");
		verify("Can't find shutter button.", shutter.exists());
		shutter.click();
		sleepInt(6);
		addStep("进入相机设置");
		verify("Can't find camera setting button.", Csetting.exists());
		Csetting.click();
		sleepInt(4);
		addStep("滑动相机设置界面");
		verify("未能进入相机设置",pos.exists());
		phone.swipe(1220, 980, 43, 980, 50);
		sleepInt(2);
		verify("Can't find scene Mode button.", sceneMode2.exists());
		addStep("进入场景模式选择");
		sceneMode2.click();
		sleepInt(2);
		
		//运动模式拍照
		addStep("点击运动模式");
		LeUiObject SportsMode2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("运动"));
		verify(SportsMode2.exists());
		SportsMode2.click();
		sleepInt(3);
		addStep("点击拍照");
		verify("Can't find shutter button.", shutter.exists());
		shutter.click();
		sleepInt(6);
		addStep("进入相机设置");
		verify("Can't find camera setting button.", Csetting.exists());
		Csetting.click();
		sleepInt(4);
		addStep("滑动相机设置界面");
		verify("未能进入相机设置",pos.exists());
		phone.swipe(1220, 980, 43, 980, 50);
		sleepInt(2);
		verify("Can't find scene Mode button.", sceneMode2.exists());
		addStep("进入场景模式选择");
		sceneMode2.click();
		sleepInt(2);
		
		//自动模式拍照
		addStep("点击自动模式");
		LeUiObject AutoMode2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
						"com.android.camera2:id/setting_item_title").text("自动"));
		verify("自动模式不存在",AutoMode2.exists());
		AutoMode2.click();
		sleepInt(3);
		addStep("点击拍照");
		verify("Can't find shutter button.", shutter.exists());
		shutter.click();
		sleepInt(6);
		
		addStep("查看照片");
		LeUiObject thumb1 = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/preview_thumb_frame").description("Filmstrip view"));
		LeUiObject thumb2 = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/preview_thumb_frame").description("胶卷视图"));
		LeUiObject thumb = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/preview_thumb_frame").className("android.widget.ImageView").index(1));
		verify("Can't find thumb button.", (thumb1.exists()||thumb2.exists())&&thumb.exists());
		thumb.click();
		sleepInt(4);
		LeUiObject agreenAndGo = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().resourceId("android:id/btn_agree").index(1))));
		if(agreenAndGo.exists()){
			agreenAndGo.click();
			sleepInt(3);
		}
		addStep("查看第1张照片");
		LeUiObject photo = new LeUiObject(new UiSelector().className(
				"android.view.View").resourceId(
						"com.android.gallery3d:id/gl_root_view"));
		
		LeUiObject deleteIcon = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)
								.childSelector(new UiSelector().className("android.widget.ImageView").index(0)))));
		LeUiObject delete2 = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("删除 1 张图片"));
		
		verify("Can't find 1th photo.", photo.exists());
		sleepInt(2);
		addStep("删除第1张照片");
		photo.click();
		sleepInt(3);
		deleteIcon.click();
		sleepInt(3);
		verify("删除不存在",delete2.exists());
		delete2.click();
		sleepInt(2);
		addStep("查看第2张照片");
		verify("Can't find 2th photo.", photo.exists());
		sleepInt(2);
		addStep("删除第2张照片");
		photo.click();
		sleepInt(1);
		deleteIcon.click();
		sleepInt(2);
		verify("删除不存在",delete2.exists());
		delete2.click();
		sleepInt(2);
		addStep("查看第3张照片");
		verify("Can't find 3th photo.", photo.exists());
		sleepInt(2);
		addStep("删除第3张照片");
		photo.click();
		sleepInt(1);
		deleteIcon.click();
		sleepInt(2);
		verify("删除不存在",delete2.exists());
		delete2.click();
		sleepInt(2);
		addStep("查看第4张照片");
		verify("Can't find 4th photo.", photo.exists());
		sleepInt(3);
		addStep("删除第4张照片");
		photo.click();
		sleepInt(1);
		deleteIcon.click();
		sleepInt(1);
		verify("删除不存在",delete2.exists());
		delete2.click();
		sleepInt(2);
		
		}
	
		@CaseName("美颜拍照")
		public void testBeautyPhoto() throws UiObjectNotFoundException {
			addStep("Step1:打开相机");
			launchApp(AppName.CAMERA);			
			sleepInt(3);
			LeUiObject beautyPhoto = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
							"com.android.camera2:id/control_button_right"));
			verify("没有美颜相机按钮",beautyPhoto.exists());
			beautyPhoto.click();
			sleepInt(2);
			LeUiObject forthBeauty = new LeUiObject(new UiSelector().className(
					"android.widget.FrameLayout").resourceId(
							"com.android.camera2:id/forth_level"));
			verify("没有进入到美颜相机",forthBeauty.exists());
			int X= forthBeauty.getBounds().centerX();
			int Y= forthBeauty.getBounds().centerY();
			phone.swipe(X, Y, X, Y, 20);

			LeUiObject forth = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
							"com.android.camera2:id/forth_level_txt").text("4"));
			verify("没有成功设置4级美颜",forth.exists());
			sleepInt(4);
			LeUiObject shutter = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
							"com.android.camera2:id/shutter_button"));
			verify("没有拍照按钮",shutter.exists());
			shutter.click();
		}
}
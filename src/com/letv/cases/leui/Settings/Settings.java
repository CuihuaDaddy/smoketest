package com.letv.cases.leui.Settings;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Settings extends LetvTestCase {
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
	
	@CaseName("改变屏幕休眠时间")
	public void testScreenSleepTime() throws UiObjectNotFoundException {
		addStep("进入设置");
		launchApp(AppName.SETTING);
		sleepInt(2);
		LeUiObject show = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("显示"));
		verify("显示不存在",show.exists());
		show.click();
		sleepInt(2);
		LeUiObject sleep = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("休眠"));
		verify("休眠不存在",sleep.exists());
		sleep.click();
		sleepInt(3);
		LeUiObject thSecond = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("30秒"));
		verify("30秒不存在",thSecond.exists());
		thSecond.click();
		sleepInt(3);
		LeUiObject thSecondVerify = new LeUiObject(new UiSelector().resourceId("android:id/message").text("30秒"));
		verify("30秒睡眠时间修改不成功",thSecondVerify.exists());
		verify("休眠不存在",sleep.exists());
		sleep.click();
		sleepInt(3);
		LeUiObject oneMin = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("1分钟"));
		verify("一分钟不存在",oneMin.exists());
		oneMin.click();
		sleepInt(3);
		LeUiObject oneMinVerify = new LeUiObject(new UiSelector().resourceId("android:id/message").text("1分钟"));
		verify("一分钟秒睡眠时间修改不成功",oneMinVerify.exists());
		verify("休眠不存在",sleep.exists());
		sleep.click();
		sleepInt(3);
		LeUiObject twoMin = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("2分钟"));
		verify("两分钟不存在",twoMin.exists());
		twoMin.click();
		sleepInt(3);
		System.out.println("****************"+twoMin.getText());
		LeUiObject twoMinVerify = new LeUiObject(new UiSelector().resourceId("android:id/message").text("2分钟"));
		verify("两分钟秒睡眠时间修改不成功",twoMinVerify.exists());
		press_back(3);
		
	}
	
	@CaseName("设置壁纸-预置壁纸/本机拍摄图片")
	public void testSetWallPaper() throws UiObjectNotFoundException {
		addStep("长按桌面");
		//press_keyevent(1,KEY_MENU );
		phone.click(1350,2150);
		sleepInt(2);
		phone.swipe(1350,2150,1350,2150,100);
		sleepInt(3);
		addStep("进入壁纸");
		LeUiObject wallPaper = new LeUiObject(new UiSelector().resourceId("com.android.launcher3:id/wallpaper_button").className(
				"android.widget.TextView").text("壁纸"));
		verify("壁纸不存在",wallPaper.exists());
		wallPaper.click();
		sleepInt(2);
		addStep("滑动并选择壁纸");
		LeUiObject rightArrow = new LeUiObject(new UiSelector().resourceId("com.android.launcher3:id/arrow_right").className(
				"android.widget.ImageView"));
		phone.swipe(1200, 2400, 300, 2400, 30);
		sleepInt(1);
		phone.swipe(1200, 2400, 300, 2400, 30);
		sleepInt(1);
		for(int i=0;i<10;i++){
			if(!rightArrow.exists()){
				break;
			}
			phone.swipe(1200, 2400, 300, 2400, 30);
			sleepInt(2);
		}
		sleepInt(2);
		LeUiObject paperChoose = new LeUiObject(new UiSelector().resourceId("com.android.launcher3:id/item_thumb"));
		verify("壁纸没有找到",paperChoose.exists());
		paperChoose.click();
		sleepInt(2);
		LeUiObject selectMark = new LeUiObject(new UiSelector().className("android.view.View")
				.resourceId("com.android.launcher3:id/item_select_mark"));
		verify("壁纸选择失败",selectMark.exists());
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		addStep("进入媒体库拍照");
		launchApp(AppName.GALLERY);
		sleepInt(3);
		LeUiObject camera = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/menu_camera")
				.className("android.widget.TextView").index(0));
		verify("未能成功进入图库",camera.exists());
		camera.click();
		sleepInt(4);
		LeUiObject chooseOne = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").text("相机"));
		if(chooseOne.exists()){
			chooseOne.click();
			sleepInt(4);
		}
		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/shutter_button"));
		verify("未能进入到相机", shutter.exists());
		shutter.click();
		sleepInt(8);
		press_back(3);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.GALLERY);
		sleepInt(5);
		LeUiObject myPhoto = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/album_name").className(
				"android.widget.TextView").text("我的相机"));
		verify("未能在图库中发现我的相机",myPhoto.exists());
		myPhoto.click();
		sleepInt(2);
		LeUiObject clickFirst = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/gl_root_view").className(
				"android.view.View"));
		verify("未能进入到我的相机里",clickFirst.exists());
		phone.click(135,363);
		sleepInt(2);
		for (int i = 0; i < 5; i++) {
			phone.click(135,363);
			sleepInt(1);
			LeUiObject shareImg = new LeUiObject(new UiSelector().className(
					"android.widget.RelativeLayout").index(0)
					.childSelector(new UiSelector().resourceId("com.android.gallery3d:id/bottom_widget")
							.className("android.widget.LinearLayout").index(2)
							.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(2)
									.childSelector(new UiSelector().className("android.widget.ImageView")))));
			if(shareImg.exists()){
				shareImg.click();
				break;
			}
		}
		sleepInt(2);
		LeUiObject setWall = new LeUiObject(new UiSelector().resourceId("android:id/share_title").className(
				"android.widget.TextView").text("设为壁纸"));
		verify("设为壁纸不存在",setWall.exists());
		setWall.click();
		sleepInt(5);
		LeUiObject set = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("设为"));
		verify("设为的按钮不存在",set.exists());
		set.click();
		sleepInt(2);
		LeUiObject setAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("同时设置"));
		verify("同时设置不存在",setAll.exists());
		setAll.click();
		sleepInt(4);
		verify("设置壁纸不成功",!setAll.exists());
		
	}

	

}

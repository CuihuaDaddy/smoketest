package com.letv.cases.leui.ControlCenter;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class ControlCenter extends LetvTestCase {
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
	@CaseName("竖屏多任务&控制中心界面显示排版正常")
//	1.调出多任务&控制中心，界面显示正常
//	2.控制中心分四个部分：
//	最上层为快捷应用（快捷应用如下：遥控器、相机、计算器、闹钟、手电筒、日历、便签）和编辑按钮
//	第二层为音乐专区
//	第三层为亮度调节条
//	第四层为快捷开关（快捷开关如下：WIFI、数据、屏幕旋转、震动、飞行模式、GPS、蓝牙、勿扰模式、热点、VPN、NFC）和编辑按钮
//	分别使用快捷应用（如：相机、计算器、闹钟）；播放音乐；调节亮度；使用快捷开关（如：WIFI、GPS、蓝牙）
	public void testControlCenter() throws UiObjectNotFoundException {
		addStep("step1:调出多任务&控制中心，界面显示正常");
		press_home(1);
		sleepInt(1);
		verify("没有进入到Home界面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		press_menu(1);
		sleepInt(1);
		LeUiObject contorlcenter = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").resourceId(
					"com.android.systemui:id/le_cc_control_content_id"));
		verify("没有成功进入到控制中心",contorlcenter.exists());
		
		addStep("step2:控制中心分四个部分是否都存在");
		LeUiObject ccApp = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").resourceId(
					"com.android.systemui:id/le_cc_app_layout_extend_id"));
		verify("最上层的快捷应用不存在",ccApp.exists());
		LeUiObject ccMusic = new LeUiObject(new UiSelector().className(
				"android.widget.RelativeLayout").resourceId(
					"com.android.systemui:id/le_cc_music_control_id"));
		verify("第二层的音乐专区不存在",ccMusic.exists());
		LeUiObject ccBrightness = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
					"com.android.systemui:id/le_cc_brightness_low_indicate_id"));
		verify("第三层的亮度调节条不存在",ccBrightness.exists());
		LeUiObject ccShortcut = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").resourceId(
					"com.android.systemui:id/le_cc_shortcut_layout_extend_id"));
		verify("第四层的快捷开关不存在",ccShortcut.exists());
		
		addStep("step3:分别使用快捷应用");
		addStep("step3.1:使用第一层快捷应用中的遥控");
		LeUiObject yaokong = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
						.childSelector(new UiSelector().className("android.widget.ImageView").index(0))));
		sleepInt(2);
		verify("遥控不存在",yaokong.exists());
		yaokong.click();
		sleepInt(3);
		LeUiObject remoteTag = new LeUiObject(new UiSelector().packageName("com.letv.android.remotecontrol"));
		verify("没有进入到遥控界面", remoteTag.exists());
		sleepInt(1);
		press_back(1);
		press_home(1);
		press_menu(1);
		sleepInt(2);
		
		addStep("step3.1:使用第一层快捷应用中的相机");
		LeUiObject camera = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
						.childSelector(new UiSelector().className("android.widget.ImageView").index(0))));
		sleepInt(2);
		camera.click();
		sleepInt(3);
		LeUiObject cameraTag = new LeUiObject(new UiSelector().packageName("com.android.camera2").index(0));
		verify("没有进入到相机界面", cameraTag.exists());
		sleepInt(1);
		press_back(1);
		press_home(1);
		press_menu(1);
		sleepInt(2);
		
		addStep("step3.3:使用第一层快捷应用中的计算器");
		LeUiObject computer = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().className("android.widget.ImageView").index(0))));
		LeUiObject computerTag = new LeUiObject(new UiSelector().packageName("com.android.calculator2").index(0));
		computer.click();
		sleepInt(2);
		verify("没有进入到计算器界面", computerTag.exists());
		sleepInt(1);
		press_back(1);
		press_home(1);
		press_menu(1);
		sleepInt(2);
		
		addStep("step3.4:使用第一层快捷应用中的手电筒");
		LeUiObject shine = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(3)
						.childSelector(new UiSelector().className("android.widget.ImageView").index(0))));
		verify("没有发现手电筒", shine.exists());
		shine.click();
		sleepInt(2);
		shine.click();
		press_back(1);
		press_home(1);
		press_menu(1);
		sleepInt(1);	
		
		addStep("step3.5:使用第一层快捷应用中的截屏");
		LeUiObject screen = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(4)
						.childSelector(new UiSelector().className("android.widget.ImageView").index(0))));
		verify("没有进入到截屏", screen.exists());
		screen.click();
		sleepInt(15);
		LeUiObject noticeManage = new LeUiObject(new UiSelector().resourceId("com.android.systemui:id/status_bar_notification_setting_btn"));
		for (int i = 0; i < 5; i++) {
			if(noticeManage.exists()){
				break;
			}
			phone.swipe(564, 2, 564, 1788, 100);
			sleepInt(2);
		}
		verify("Can't open notification.", noticeManage.exists());
		LeUiObject screenCut = new LeUiObject(new UiSelector().textContains("已抓取屏幕"));
		LeUiObject delete = new LeUiObject(new UiSelector().textContains("删除"));
		verify("截屏不成功",screenCut.exists());
		if(delete.exists()){
			
			delete.click();
		}
		sleepInt(3);
		press_back(1);
		press_home(1);
		sleepInt(1);
	}
}

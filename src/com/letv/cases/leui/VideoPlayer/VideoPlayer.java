package com.letv.cases.leui.VideoPlayer;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class VideoPlayer extends LetvTestCase {
	
	private String video1 = "testvideoavi.avi";
	private String video2 = "testvideowmv.wmv";
	private String video3 = "testvideo3gp.3gp";
	
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
	@CaseName("启动视频播放器应用")
//	1.启动视频播放器应用
//	2.home键后台视频播放器，再次点击视频播放器
//	3.home键后台视频播放器 调用多任务，多任务中点击视频播放器
	public void testOpenVideoPlayer() throws UiObjectNotFoundException {
		
		addStep("step1:启动视频播放器应用");
		launchApp(AppName.VIDEOPLAYER);
		sleepInt(2);
		LeUiObject videoPlayerIcon = new LeUiObject(new UiSelector().className("android.widget.TextView").text("播放器"));
		verify("没有成功进入视频播放器",videoPlayerIcon.exists());
		sleepInt(2);
		addStep("step2:home键后台播放器，再次点击播放器");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.VIDEOPLAYER);
		sleepInt(5);
		verify("没有成功进入视频播放器",videoPlayerIcon.exists());
		addStep("step3:home键后台播放器调用多任务，多任务中点击播放器");
		press_menu(1);
		sleepInt(2);
		LeUiObject clear = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").textContains("立即清理"))));
		if(clear.exists()){
			clear.click();
			sleepInt(3);
			press_menu(1);
		}
		sleepInt(1);
		LeUiObject recent = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		LeUiObject recent2 = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		verify("最近不存在",recent.exists()||recent2.exists());
		if(recent.exists()){
			recent.click();
		}else{
			recent2.click();
		}
		sleepInt(3);
		verify("没有成功进入视频播放器",videoPlayerIcon.exists());
		addStep("退出应用");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	@CaseName("播放本地多种格式的视频")
//	1.打开媒体库，并且查看本地视频
//	2.播放多种格式的视频例（wmv/avi/3gp)，每个视频时长至少3分钟
	public void testPlayVideo() throws UiObjectNotFoundException {
		
		addStep("step1:启动视频播放器应用");
		launchApp(AppName.VIDEOPLAYER);
		sleepInt(3);
		LeUiObject videoPlayerIcon = new LeUiObject(new UiSelector().className("android.widget.TextView").text("播放器"));
		sleepInt(2);
		verify("没有成功进入视频播放器",videoPlayerIcon.exists());
		LeUiObject resource = new LeUiObject(new UiSelector().className("android.widget.TextView").text("Resource"));
		sleepInt(2);
		verify("没有找到视频项",resource.exists());
		resource.click();
		sleepInt(3);
		LeUiObject v1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text(video1));
		verify("Can't find video1 " + video1, v1.exists());
		v1.click();
		sleepInt(120);
		/*press_back(1);
		sleepInt(3);
		LeUiObject v2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text(video2));
		verify("Can't find video2 " + video2, v2.exists());
		v2.click();
		sleepInt(120);*/
		press_back(1);
		resource.click();
		sleepInt(3);
		LeUiObject v3 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text(video3));
		verify("Can't find video3 " + video3, v3.exists());
		v3.click();
		sleepInt(120);
		press_back(2);
		sleepInt(3);
		addStep("退出应用");
		press_back(4);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

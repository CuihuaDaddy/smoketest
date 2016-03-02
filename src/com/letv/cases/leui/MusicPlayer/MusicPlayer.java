package com.letv.cases.leui.MusicPlayer;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class MusicPlayer extends LetvTestCase {
	
	private String music1 = "wmamusic";
	private String music2 = "mp3music";
	private String music3 = "amrmusic";
	
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
	@CaseName("启动音乐频播放器应用")
//	1.启动视频播放器应用
//	2.home键后台视频播放器，再次点击视频播放器
//	3.home键后台视频播放器 调用多任务，多任务中点击视频播放器
	public void testOpenMusicPlay() throws UiObjectNotFoundException {
		
		addStep("step1:启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(3);
		
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		addStep("step2:home键后台，再次点击");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.MUSIC);
		sleepInt(3);
		verify("没有成功进入音乐频播放器",isEnter.waitForExists(10));
		addStep("step3:home键后台调用多任务，多任务中点击");
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
		verify("没有成功进入音乐频播放器",isEnter.exists());
		addStep("退出应用");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	@CaseName("播放本地多种格式的音频")
//	1.打开媒体库，并且查看本地视频
//	2.播放多种格式的视频例（amr/mp2/wma)，每个视频时长至少3分钟
	public void testPlayMusic() throws UiObjectNotFoundException {
		addStep("启动文件管理器");
		launchApp(AppName.FILEMANAGER);
		sleepInt(2);
		LeUiObject mobileStorage = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").resourceId(
				"com.letv.android.filemanager:id/mobile_storage"));
		verify("Can't find mobile storage.", mobileStorage.exists());
		mobileStorage.click();
		sleepInt(1);
		addStep("退出文件管理器");
		press_back(2);
		press_home(1);
		addStep("step1:启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(3);
		LeUiObject dateConfirm = new LeUiObject(new UiSelector().text("确定"));
		if(dateConfirm.exists()){
			dateConfirm.click();
			sleepInt(2);
		}
		LeUiObject agree = new LeUiObject(new UiSelector().text("同意并继续"));
		if(agree.exists()){
			agree.click();
			sleepInt(2);
		}
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		addStep("选择歌曲播放5秒");
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));
		if(confirm.exists()){
			confirm.click();
			sleepInt(3);
		}
		LeUiObject own = new LeUiObject(new UiSelector().text("我的"));
		verify("Can't find own", own.exists());
		own.click();
		sleepInt(2);
		LeUiObject localMusic = new LeUiObject(new UiSelector().resourceId(
		"com.android.music:id/entry_local_music"));
		verify("Can't find local music.", localMusic.exists());
		localMusic.click();
		sleepInt(2);
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
		
		sleepInt(2);
		
		// AMR format is not supported by MusicPlayer of LeMax
		LeUiObject m1 = new LeUiObject(new UiSelector().className("android.widget.TextView").textContains(music1));
		sleepInt(2);
		//if(!m1.exists()){
		//	phone.swipe(525, 1320, 525, 650, 30);
		//}
		verify("Can't find music " + music1, m1.exists());
		m1.click();
		sleepInt(10);
		
//		press_back(1);
		sleepInt(3);
		LeUiObject m2 = new LeUiObject(new UiSelector().className("android.widget.TextView").textContains(music2));
		sleepInt(4);
		//if(!m2.exists()){
		//	phone.swipe(525, 1320, 525, 650, 30);
		//}
		verify("Can't find music " + music2, m2.exists());
		m2.click();
		System.out.println("播放成功");
		sleepInt(10);
		
//		press_back(1);
		sleepInt(3);
		//bug, amr cannot be found
		/*LeUiObject m3 = new LeUiObject(new UiSelector().className("android.widget.TextView").textContains(music3));
		//press_back(1);
		sleepInt(2);
		//if(!m3.exists()){
		//	phone.swipe(525, 1320, 525, 650, 30);
		//}
		verify("Can't find music " + music3, m3.exists());
		m3.click();
		System.out.println("播放成功");
		sleepInt(3);
		sleepInt(2);*/
		
		addStep("退出应用");
		press_back(4);
		press_home(1);
		press_menu(1);
		sleepInt(3);
		
		//LeUiObject clear = new LeUiObject(new UiSelector()
		//.className("android.widget.LinearLayout").index(0)
		//.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
		//		.childSelector(new UiSelector().className("android.widget.TextView").textContains("立即清理"))));
		
		LeUiObject clear = new LeUiObject(new UiSelector()
			.className("android.widget.RelativeLayout").index(2)
			.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
			.childSelector(new UiSelector().className("android.widget.TextView")
			.resourceId("com.android.systemui:id/leui_recent_clear_all_txtview"))));		
		verify("清除不存在", clear.exists());
		
		clear.click();
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

package com.letv.cases.leui.MusicPlayer;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;
import com.letv.uf.Utf7ImeHelper;

public class Music extends LetvTestCase {
	
	@CaseName("在音乐播放器听音乐的热歌榜里播放音乐")
	public void testPlayMusicInHot() throws UiObjectNotFoundException {
		//connectWifi();
		addStep("启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(5);
		LeUiObject dateConfirm = new LeUiObject(new UiSelector().text("确定"));
		if(dateConfirm.exists()){
			dateConfirm.click();
			sleepInt(2);
		}
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));
		if(confirm.exists()){
			confirm.click();
			sleepInt(3);
		}
		addStep("听音乐的热歌榜里播放音乐");
		LeUiObject listenMusic = new LeUiObject(new UiSelector().resourceId("android:id/title").text("听音乐"));
		verify("未能发现听音乐", listenMusic.exists());
		listenMusic.click();
		sleepInt(2);
		phone.swipe(500, 1700, 500, 1100, 30);
		LeUiObject hotMusic = new LeUiObject(new UiSelector().resourceId("com.android.music:id/rankItemImage2"));
		verify("未能发现热歌榜", hotMusic.exists());
		hotMusic.click();
		sleepInt(5);
		LeUiObject music01 = new LeUiObject(new UiSelector().resourceId("com.android.music:id/textView_song_index").text("01"));
		verify("未能发现热歌榜里的歌曲01", music01.exists());
		music01.click();
	}
	@CaseName("在音乐播放器收藏和取消收藏音乐")
	public void testCollectMusic() throws UiObjectNotFoundException {
		connectWifi();
		addStep("启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(5);
		LeUiObject dateConfirm = new LeUiObject(new UiSelector().text("确定"));
		if(dateConfirm.exists()){
			dateConfirm.click();
			sleepInt(2);
		}
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));
		if(confirm.exists()){
			confirm.click();
			sleepInt(3);
		}
		addStep("到搜索界面搜索歌曲小苹果");
		LeUiObject search = new LeUiObject(new UiSelector().resourceId("android:id/title").text("搜索"));
		verify("未能发现搜索", search.exists());
		search.click();
		sleepInt(2);
		LeUiObject searchEdit = new LeUiObject(new UiSelector().resourceId(
		"android:id/lc_search_src_text"));
		verify("不能够发现编辑框", searchEdit.exists());
		searchEdit.click();
		sleepInt(2);
		searchEdit.setText(Utf7ImeHelper.e("xpg"));
		sleepInt(3);
		LeUiObject searchResult = new LeUiObject(new UiSelector()
			.className("android.widget.ListView")
			.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
					.childSelector(new UiSelector().resourceId("com.android.music:id/hot_word_detail").text("小苹果"))));
		verify("未能都出结果小苹果",searchResult.exists());
		searchResult.click();
		sleepInt(2);
		LeUiObject searchResultItem = new LeUiObject(new UiSelector()
		.resourceId("android:id/list").className("android.widget.ListView")
		.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().resourceId("com.android.music:id/track_name").text("小苹果"))));
		verify("搜索出来结果界面没有找到的小苹果歌曲",searchResultItem.exists());
		searchResultItem.click();
		sleepInt(10);
		addStep("收藏音乐和取消收藏");
		//点击收藏按钮，uiautomator获取不到相关空间
		phone.click(1120, 1820);
		sleepInt(4);
		phone.click(90, 130);
		sleepInt(2);
		press_back(1);
		sleepInt(2);
		LeUiObject own = new LeUiObject(new UiSelector().text("我的"));
		verify("我的不存在", own.exists());
		own.click();
		LeUiObject myCollect = new LeUiObject(new UiSelector().text("我收藏的"));
		verify("我收藏的不存在",myCollect.exists());
		myCollect.click();
		sleepInt(2);
		LeUiObject xpg = new LeUiObject(new UiSelector().text("小苹果"));
		verify("未能收藏成功",xpg.exists());
		xpg.click();
		sleepInt(2);
		//点击收藏按钮，uiautomator获取不到相关空间
		phone.click(1120, 1820);
		sleepInt(4);
		phone.click(90, 130);
		sleepInt(2);
		press_back(1);
		sleepInt(2);
		verify("我的不存在", own.exists());
		own.click();
		verify("我收藏的不存在",myCollect.exists());
		myCollect.click();
		sleepInt(2);
		verify("未能取消收藏",!xpg.exists());
	}
	@CaseName("在音乐播放器里新建播放列表")
	public void testCreatePlayList() throws UiObjectNotFoundException {
		connectWifi();
		addStep("启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(5);
		LeUiObject dateConfirm = new LeUiObject(new UiSelector().text("确定"));
		if(dateConfirm.exists()){
			dateConfirm.click();
			sleepInt(2);
		}
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));
		if(confirm.exists()){
			confirm.click();
			sleepInt(3);
		}
		addStep("新建名字为常听音乐的播放列表");
		LeUiObject own = new LeUiObject(new UiSelector().text("我的"));
		verify("Can't find own", own.exists());
		own.click();
		sleepInt(2);
		LeUiObject creatList = new LeUiObject(new UiSelector().resourceId("com.android.music:id/create_playlist"));
		verify("创建播放列表的按钮不存在", creatList.exists());
		creatList.click();
		sleepInt(2);
		LeUiObject editName = new LeUiObject(new UiSelector().resourceId("com.android.music:id/editTextDialogUserInput"));
		verify("输入播放列表名字的区域不存在", editName.exists());
		editName.setText(Utf7ImeHelper.e("常听音乐"));
		sleepInt(2);
		LeUiObject create = new LeUiObject(new UiSelector().className("android.widget.Button").text("新建"));
		verify("新建的按钮不存在", create.exists());
		create.click();
		sleepInt(3);
		press_back(1);
		sleepInt(2);
		phone.swipe(500, 1200, 500, 500, 30);
		LeUiObject oftenListen = new LeUiObject(new UiSelector().text("常听音乐"));
		verify("新建播放列表失败",oftenListen.exists());
		addStep("删除新建的播放列表");
		LeUiObject moreSet = new LeUiObject(new UiSelector().resourceId("com.android.music:id/action_more").index(2));
		verify("更多设置不存在", moreSet.exists());
		moreSet.click();
		sleepInt(2);
		LeUiObject delete = new LeUiObject(new UiSelector().text("删除"));
		verify("删除不存在", delete.exists());
		delete.click();
		sleepInt(2);
		verify("未能删除成功", !oftenListen.exists());
	}
	@CaseName("在音乐播放器里搜索音乐并播放")
	public void testSearchMusic() throws UiObjectNotFoundException {
		connectWifi();
		addStep("启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(5);
		LeUiObject dateConfirm = new LeUiObject(new UiSelector().text("确定"));
		if(dateConfirm.exists()){
			dateConfirm.click();
			sleepInt(2);
		}
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));
		if(confirm.exists()){
			confirm.click();
			sleepInt(3);
		}
		addStep("到搜索界面搜索歌曲小苹果");
		LeUiObject search = new LeUiObject(new UiSelector().resourceId("android:id/title").text("搜索"));
		verify("未能发现搜索", search.exists());
		search.click();
		sleepInt(2);
		LeUiObject searchEdit = new LeUiObject(new UiSelector().resourceId(
		"android:id/lc_search_src_text"));
		verify("不能够发现编辑框", searchEdit.exists());
		searchEdit.click();
		sleepInt(2);
		searchEdit.setText(Utf7ImeHelper.e("xpg"));
		sleepInt(3);
		LeUiObject searchResult = new LeUiObject(new UiSelector()
			.className("android.widget.ListView")
			.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
					.childSelector(new UiSelector().resourceId("com.android.music:id/hot_word_detail").text("小苹果"))));
		verify("未能都出结果小苹果",searchResult.exists());
		searchResult.click();
		sleepInt(2);
		LeUiObject searchResultItem = new LeUiObject(new UiSelector()
		.resourceId("android:id/list").className("android.widget.ListView")
		.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().resourceId("com.android.music:id/track_name").text("小苹果"))));
		verify("搜索出来结果界面没有找到的小苹果歌曲",searchResultItem.exists());
		searchResultItem.click();
		sleepInt(10);
	}
	@CaseName("在音乐播放器视频墙播放音乐")
	public void testPlayInWall() throws UiObjectNotFoundException {
		connectWifi();
		addStep("启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(5);
		LeUiObject dateConfirm = new LeUiObject(new UiSelector().text("确定"));
		if(dateConfirm.exists()){
			dateConfirm.click();
			sleepInt(2);
		}
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));
		if(confirm.exists()){
			confirm.click();
			sleepInt(3);
		}
		addStep("视频墙播放音乐");
		LeUiObject lookMusic = new LeUiObject(new UiSelector().resourceId("android:id/title").text("看音乐"));
		verify("看音乐不存在", lookMusic.exists());
		lookMusic.click();
		sleepInt(2);
		LeUiObject videoWall = new LeUiObject(new UiSelector().text("视频墙"));
		verify("视频墙不存在",videoWall.exists());
		videoWall.click();
		sleepInt(3);
		phone.swipe(500, 1800, 500, 900, 30);
		sleepInt(3);
		phone.swipe(500, 1800, 500, 900, 30);
		sleepInt(6);
		addStep("视频墙播放");
		LeUiObject mv = new LeUiObject(new UiSelector().resourceId("com.android.music:id/image"));
		verify("视频墙mv不存在",mv.exists());
		mv.click();
		sleepInt(15);
		LeUiObject isplay = new LeUiObject(new UiSelector().className("android.view.View"));
		verify("播放视频页未能打开",isplay.exists());

	}
	@CaseName("在音乐播放器视频墙收藏音乐")
	public void testCollectInWall() throws UiObjectNotFoundException {

		//connectWifi();
		addStep("启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(5);
		LeUiObject dateConfirm = new LeUiObject(new UiSelector().text("确定"));
		if(dateConfirm.exists()){
			dateConfirm.click();
			sleepInt(2);
		}
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));
		if(confirm.exists()){
			confirm.click();
			sleepInt(3);
		}
		addStep("视频墙收藏音乐");
		LeUiObject lookMusic = new LeUiObject(new UiSelector().resourceId("android:id/title").text("看音乐"));
		verify("看音乐不存在", lookMusic.exists());
		lookMusic.click();
		sleepInt(2);
		LeUiObject videoWall = new LeUiObject(new UiSelector().text("视频墙"));
		verify("视频墙不存在",videoWall.exists());
		videoWall.click();
		sleepInt(3);
		phone.swipe(500, 1800, 500, 900, 30);
		sleepInt(3);
		phone.swipe(500, 1800, 500, 900, 30);
		sleepInt(6);
		LeUiObject collect = new LeUiObject(new UiSelector().resourceId("com.android.music:id/action_like"));
		verify("视频墙收藏音乐的按钮不存在",collect.exists());
		collect.click();
		sleepInt(2);
		LeUiObject own = new LeUiObject(new UiSelector().text("我的"));
		verify("我的不存在", own.exists());
		own.click();
		LeUiObject myCollect = new LeUiObject(new UiSelector().text("我收藏的"));
		verify("我收藏的不存在",myCollect.exists());
		myCollect.click();
		sleepInt(2);
		LeUiObject mv = new LeUiObject(new UiSelector().text("MV"));
		verify("未能进入我收藏的界面",mv.exists());
		mv.click();
		sleepInt(3);
		LeUiObject mvList = new LeUiObject(new UiSelector().resourceId("android:id/list"));
		verify("mv列表不存在",mvList.exists());
		verify("收藏不成功",!(mvList.getChildCount()==0));
		
	}
	@CaseName("在音乐播放器听音乐的热歌榜里播放音乐")
	public void testLookShow() throws UiObjectNotFoundException {
		//connectWifi();
		addStep("启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(5);
		LeUiObject dateConfirm = new LeUiObject(new UiSelector().text("确定"));
		if(dateConfirm.exists()){
			dateConfirm.click();
			sleepInt(2);
		}
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));
		if(confirm.exists()){
			confirm.click();
			sleepInt(3);
		}
		addStep("看演出里查看演出详情");
		LeUiObject lookShow = new LeUiObject(new UiSelector().resourceId("android:id/title").text("看演出"));
		verify("未能发现看演出", lookShow.exists());
		lookShow.click();
		sleepInt(2);
		LeUiObject firstShow = new LeUiObject(new UiSelector().resourceId("com.android.music:id/performance_name"));
		verify("未能进入看演出界面找到第一个演出", firstShow.exists());
		firstShow.click();
		sleepInt(5);
		LeUiObject buy = new LeUiObject(new UiSelector().resourceId("com.android.music:id/textView_perform_detail_buy_ticket").text("立即购票"));
		verify("未能进入看演出界面找到第一个演出", buy.exists());
	}
	@CaseName("在精选推荐里随机播放音乐")
	public void testGoodQuality() throws UiObjectNotFoundException {
		//connectWifi();
		addStep("启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(5);
		LeUiObject dateConfirm = new LeUiObject(new UiSelector().text("确定"));
		if(dateConfirm.exists()){
			dateConfirm.click();
			sleepInt(2);
		}
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));
		if(confirm.exists()){
			confirm.click();
			sleepInt(3);
		}
		addStep("在精选推荐里随机播放音乐");
		LeUiObject lookMusic = new LeUiObject(new UiSelector().resourceId("android:id/title").text("看音乐"));
		verify("看音乐不存在", lookMusic.exists());
		lookMusic.click();
		sleepInt(2);
		LeUiObject goodQuality = new LeUiObject(new UiSelector().text("精选推荐"));
		verify("精选推荐不存在",goodQuality.exists());
		goodQuality.click();
		LeUiObject music = new LeUiObject(new UiSelector().resourceId("com.android.music:id/image"));
		verify("精选推荐下没有音乐",music.exists());
		music.click();
		sleepInt(15);
		LeUiObject isPlay = new LeUiObject(new UiSelector().resourceId("com.android.music:id/control_panel_root"));
		verify("精选推荐下音乐没有进入的播放页面",isPlay.exists());
		
	}
	@CaseName("在音乐TV里随机播放音乐")
	public void testMusicTV() throws UiObjectNotFoundException {
		//connectWifi();
		addStep("启动音频播放器应用");
		launchApp(AppName.MUSIC);
		sleepInt(5);
		LeUiObject dateConfirm = new LeUiObject(new UiSelector().text("确定"));
		if(dateConfirm.exists()){
			dateConfirm.click();
			sleepInt(2);
		}
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.music"));
		verify("没有成功进入音乐频播放器",isEnter.exists());
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));
		if(confirm.exists()){
			confirm.click();
			sleepInt(3);
		}
		addStep("在音乐TV里随机播放音乐");
		LeUiObject lookMusic = new LeUiObject(new UiSelector().resourceId("android:id/title").text("看音乐"));
		verify("看音乐不存在", lookMusic.exists());
		lookMusic.click();
		sleepInt(2);
		LeUiObject goodQuality = new LeUiObject(new UiSelector().text("精选推荐"));
		verify("精选推荐不存在",goodQuality.exists());
		goodQuality.click();
		LeUiObject music = new LeUiObject(new UiSelector().resourceId("com.android.music:id/image"));
		verify("精选推荐下没有音乐",music.exists());
		music.click();
		sleepInt(15);
		LeUiObject isPlay = new LeUiObject(new UiSelector().resourceId("com.android.music:id/control_panel_root"));
		verify("精选推荐下音乐没有进入的播放页面",isPlay.exists());
		
	}
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		addStep("退出应用");
		press_back(3);
		press_home(1);
		press_menu(1);
		sleepInt(3);
		LeUiObject clear = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").index(4))));
		if(clear.exists()){
			clear.click();
		}
		super.tearDown();
	}
}

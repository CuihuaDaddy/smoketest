package com.letv.cases.leui.LeJian;


import java.util.ArrayList;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;
import com.letv.uf.Utf7ImeHelper;

public class LeJian extends LetvTestCase {

	@CaseName("进入乐见桌面，随机打开视频")
	public void testPlayVideoInLeJian() throws UiObjectNotFoundException {
		addStep("step1: 连接wifi");
		connectWifi();
		press_back(3);
		press_home(1);
		addStep("step2: 进入乐见桌面并随机打开一个视频");
		phone.swipe(200, 1300, 1200, 1300, 50);
		sleep(2);
		LeUiObject beginUse = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").textContains("开始使用"));
		if(beginUse.exists()){
			beginUse.click();
			sleepInt(3);
		}
		phone.swipe(750, 2200, 750, 850, 50);
		sleepInt(2);
		phone.swipe(750, 2200, 750, 850, 50);
		sleepInt(2);
		phone.swipe(750, 2200, 750, 850, 50);
		sleepInt(2);
		phone.swipe(750, 2200, 750, 850, 50);
		sleepInt(2);
		LeUiObject acceWindows = new LeUiObject(new UiSelector().packageName("android")
				.resourceId("android:id/le_bottomsheet_switchbutton_text").className(
				"android.widget.TextView").index(1));
		if (acceWindows.exists()) {
			new LeUiObject(new UiSelector().className(
					"android.widget.Button").text("允许")).click();
			sleepInt(5);
		} 
		sleepInt(10);
		LeUiObject isOpen = new LeUiObject(new UiSelector()
		.resourceId("com.letv.android.client:id/main_player_video_view_container"));
		verify("随机打开视频失败",isOpen.exists());
	}

	@CaseName("进入乐见桌面，搜索视频")
	public void testSearchVideo() throws UiObjectNotFoundException {
		//connectWifi();
		press_back(3);
		press_home(1);
		addStep("step1: 进入乐见桌面");
		phone.swipe(200, 1300, 1200, 1300, 50);
		sleep(2);
		LeUiObject beginUse = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").textContains("开始使用"));
		if(beginUse.exists()){
			beginUse.click();
			sleepInt(3);
		}
		phone.swipe(750, 2200, 750, 850, 50);
		sleepInt(2);
		phone.swipe(750, 850, 750, 2200, 50);
		sleepInt(2);
		addStep("step 2: 输入片名超能陆战队并播放");
		//LeUiObject leso = new LeUiObject(new UiSelector().textContains("全网视频"));
		LeUiObject leso = new LeUiObject(new UiSelector().resourceId("com.letv.sarrsdesktop:id/search_box").index(1)
				.childSelector(new UiSelector().className("android.widget.TextView")));		
		verify("乐看搜索框不存在",leso.exists());
		leso.click();
		sleepInt(3);
		
		LeUiObject inputVideoName = new LeUiObject(new UiSelector().textContains("输入片名"));
		verify("输入搜索框不存在",inputVideoName.exists());
		//inputVideoName.setText(Utf7ImeHelper.e("超能陆战队"));
		inputVideoName.setText("超能陆战队");
		sleepInt(2);
		LeUiObject search = new LeUiObject(new UiSelector().textContains("搜索"));
		verify("搜索不存在",search.exists());
		search.click();
		sleepInt(2);
		LeUiObject searchResult = new LeUiObject(new UiSelector().resourceId("com.letv.lesophoneclient:id/listv_album")
				.childSelector(new UiSelector().resourceId("com.letv.lesophoneclient:id/search_movie_item")
						.childSelector(new UiSelector().className("android.widget.FrameLayout").index(0))));
		verify("未能搜到结果",searchResult.exists());
		searchResult.click();
		sleepInt(2);
		LeUiObject acceWindows = new LeUiObject(new UiSelector().packageName("android")
				.resourceId("android:id/le_bottomsheet_switchbutton_text").className(
				"android.widget.TextView").index(1));
		if (acceWindows.exists()) {
			new LeUiObject(new UiSelector().className(
					"android.widget.Button").text("允许")).click();
			sleepInt(5);
		} 
		sleepInt(10);
		LeUiObject isOpen = new LeUiObject(new UiSelector()
		.resourceId("com.letv.android.client:id/main_player_video_view_container"));
		verify("随机打开视频失败",isOpen.exists());
	}
	
	@CaseName("乐视桌面设置喜好选择")
	public void testChooseFav() throws UiObjectNotFoundException {
		connectWifi();
		press_back(3);
		press_home(1);
		addStep("进入乐见桌面");
		phone.swipe(200, 1300, 1200, 1300, 50);
		sleep(2);
		LeUiObject beginUse = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").textContains("开始使用"));
		if(beginUse.exists()){
			beginUse.click();
			sleepInt(3);
		}
		phone.swipe(750, 2200, 750, 850, 50);
		sleepInt(2);
		phone.swipe(750, 2200, 750, 850, 50);
		sleepInt(2);
		
		LeUiObject chooseFav = new LeUiObject(new UiSelector()
		.resourceId("com.android.launcher3:id/button_settings"));
		verify("重新选择喜好的按钮不存在",chooseFav.exists());
		chooseFav.click();
		sleepInt(4);
		addStep("进行喜好选择（脱口秀，小鲜肉，体育）并提交");
		LeUiObject Fav1 = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("脱口秀"));
		LeUiObject Fav2 = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("小鲜肉"));
		LeUiObject Fav3 = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("体育"));
		Fav1.click();
		sleepInt(2);
		Fav2.click();
		sleepInt(2);
		Fav3.click();
		sleepInt(2);
		LeUiObject submit = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("提交我的喜好"));
		verify("提交我的喜好不存在",submit.exists());
		submit.click();
		sleepInt(5);
		verify("提交不成功",!submit.exists());
	}
	
	@CaseName("乐见桌面选择分别推荐，电影，排行榜，电视剧，动漫，综艺，记录片等")
	public void testShowMethod() throws UiObjectNotFoundException {
		connectWifi();
		press_back(3);
		press_home(1);
		addStep("进入乐见桌面");
		phone.swipe(200, 1300, 1200, 1300, 50);
		sleep(2);
		LeUiObject beginUse = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").textContains("开始使用"));
		if(beginUse.exists()){
			beginUse.click();
			sleepInt(3);
		}
		phone.swipe(750, 2200, 750, 850, 50);
		sleepInt(2);
		phone.swipe(750, 2200, 750, 850, 50);
		sleepInt(2);
		ArrayList<String> list = new ArrayList<String>();
		list.add("推荐");
		list.add("关注");
		list.add("排行榜");
		list.add("电影");
		list.add("电视剧");
		list.add("动漫");
		list.add("综艺");
		list.add("纪录片");
		list.add("音乐");
		list.add("日韩");
		list.add("内地");
		list.add("欧美");
		list.add("喜剧");
		list.add("爱情");
		addStep("分别推荐，电影，排行榜，电视剧，动漫，综艺，记录片等");
		for(int i = 0;i<14;i++){
			chooseRecommed(list.get(i).toString());
		}
		
	}
	
	@CaseName("进入乐见桌面，搜索视频")
	public void testLaunchLeJian() throws UiObjectNotFoundException {
		//connectWifi();
		//press_back(3);
		//press_home(1);
		addStep("step1: 进入乐见桌面");
		phone.swipe(200, 1300, 1200, 1300, 50);
		sleep(2);
		LeUiObject beginUse = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").textContains("开始使用"));
		if(beginUse.exists()){
			beginUse.click();
			sleepInt(3);
		}
		//phone.swipe(750, 2200, 750, 850, 50);
		//sleepInt(2);
		phone.swipe(750, 850, 750, 2200, 50);
		sleepInt(2);
	/*	addStep("step 2: 验证乐见搜索框");
		LeUiObject leso = new LeUiObject(new UiSelector().resourceId("com.letv.sarrsdesktop:id/search_box").index(1)
				.childSelector(new UiSelector().className("android.widget.TextView")));		
		for(int k=0;k<20;k++){
	        sleepInt(1);
	        if(leso.exists()){
		       break;
	        }
        }
		verify("乐见搜索框不存在",leso.exists());
		*/
	}
	
	public void chooseRecommed(String type) throws UiObjectNotFoundException{
		LeUiObject recommendSet = new LeUiObject(new UiSelector()
		.resourceId("com.android.launcher3:id/button_recommend_text"));
		verify("推荐设置不存在",recommendSet.exists());
		recommendSet.click();
		sleepInt(2);
		LeUiObject movieType = new LeUiObject(new UiSelector()
		.resourceId("com.android.launcher3:id/sarrs_category_text").text(type));
		movieType.click();
		sleepInt(4);
		verify(type+"选择不成功",recommendSet.getText().equals(type));
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		press_back(2);
		//closeWifi();
		super.tearDown();
	}
	
	
}

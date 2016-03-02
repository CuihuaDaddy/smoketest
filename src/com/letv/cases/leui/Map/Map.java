package com.letv.cases.leui.Map;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Map extends LetvTestCase {
	@CaseName("启动地图应用,关闭地图应用")
	public void testOpenMap() throws UiObjectNotFoundException {
		
		launchApp(AppName.MAP);
		sleepInt(3);
		
		LeUiObject confirm= new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("确定"));
		if (confirm.exists()) {
			confirm.click();
		}
		
		LeUiObject prompts = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("Welcome to baidu map"));
		if(prompts.exists()){
			LeUiObject noAsk1 = new LeUiObject(new UiSelector().className(
					"android.widget.CheckBox").textContains("No ask"));
			LeUiObject noAsk2 = new LeUiObject(new UiSelector().className(
					"android.widget.CheckBox").textContains("不再提示"));
			verify("不再提示没有显示",noAsk1.exists()||noAsk2.exists());
			if(noAsk1.exists()){
				if(!(noAsk1.isChecked())){
					noAsk1.click();
					sleepInt(1);
				}
			}else {
				if(!(noAsk2.isChecked())){
					noAsk2.click();
					sleepInt(1);
				}
			}
			
			LeUiObject OK1 = new LeUiObject(new UiSelector().className(
					"android.widget.Button").textContains("Ok"));
			LeUiObject OK2 = new LeUiObject(new UiSelector().className(
					"android.widget.Button").textContains("确定"));
			verify("OK按钮不存在",OK1.exists()||OK2.exists());
			if(OK1.exists()){
				OK1.click();
			}else{
				OK2.click();
			}
			
			sleepInt(3);
		}
		LeUiObject allow= new LeUiObject(new UiSelector().className(
				"android.widget.Button").resourceId("android:id/le_bottomsheet_btn_confirm_5"));
		if (allow.exists()) {
			allow.click();
		}
		
		phone.drag(1000, 500, 40, 500, 8);
		sleepInt(1);
		phone.drag(1000, 500, 40, 500, 8);
		sleepInt(2);
		LeUiObject cancel1= new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("取消"));
		LeUiObject cancel2= new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("取消"));
		if (cancel1.exists()) {
			cancel1.click();
		}
		if (cancel2.exists()) {
			cancel2.click();
		}
		
		LeUiObject nearby = new LeUiObject(new UiSelector().packageName("com.baidu.BaiduMap"));
		
		verify("没有成功进入地图",nearby.exists());
		sleepInt(2);
		addStep("step2:home键后台，再次点击地图");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.MAP);		
		sleepInt(2);
		verify("没有成功进入地图",nearby.exists());
		addStep("step3:home键后台调用多任务，多任务中点击地图");
		
		//press_home(1);
		//sleepInt(3);		
		/*
		 * Need to remove to proceed following steps
		press_menu(1);
		sleepInt(3);
		
		LeUiObject clear = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").textContains("立即清理"))));
		verify("清除不存在",clear.exists());
		
		clear.click();
		sleepInt(3);
		*/
		press_menu(1);
		sleepInt(1);
		
		LeUiObject recent = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		LeUiObject recent2 = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		verify("最近不存在",recent.exists()||recent2.exists());
		if(recent.exists()){
			recent.click();
		}else{
			recent2.click();
		}
		sleepInt(3);
		verify("没有成功进入地图",nearby.exists());
		addStep("退出应用");
		LeUiObject quitNote = new LeUiObject(new UiSelector().textContains("要退出百度地图"));
		for (int i = 0; i < 5; i++) {
			if(quitNote.exists()){
				LeUiObject OK = new LeUiObject(new UiSelector()
				.className("android.widget.Button")
				.resourceId("com.baidu.BaiduMap:id/positiveButton").text("确定"));
				OK.click();
				sleepInt(2);
				break;
			}else{
				press_back(1);
			}
		}
		
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

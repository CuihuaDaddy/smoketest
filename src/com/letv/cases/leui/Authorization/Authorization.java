package com.letv.cases.leui.Authorization;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Authorization extends LetvTestCase {
	
	@CaseName("修改权限管理")
//	1.设置权限管理，例如定位-地图分别设置为允许、拒绝、总是询问
//	2.启动地图
	public void testAuthorization() throws UiObjectNotFoundException {
		LeUiObject map = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("地图").resourceId("android:id/title"));
		LeUiObject alwaysAsk = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("总是询问"));
		LeUiObject reject = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("拒绝"));	
		LeUiObject allow = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("允许"));	
		LeUiObject note = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("地图申请访问位置信息."));
		LeUiObject quit = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("确认要退出百度地图？"));
		LeUiObject sure = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("确定"));
		LeUiObject reject1 = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("拒绝"));	
		LeUiObject allow1 = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("允许"));	
		
		
		
		intoMapAuthorization();
		map.click();
		sleepInt(2);
		/*verify("没有找到总是询问", alwaysAsk.exists());
		alwaysAsk.click();
		addStep("设置定位中的地图的权限为总是询问");
		press_back(4);
		press_home(1);
		intoMap();
		
		verify("地图申请访问位置信息没有弹出",note.exists());*/
		verify("没有允许选项",allow.exists());
		allow.click();
		addStep("设定地图允许访问定位");
		sleepInt(2);
		press_back(1);
		for(int i=0;i<4;i++){
			if(quit.exists()){
				verify("有退出地图提示但是没有确定按钮",sure.exists());
				sure.click();
				sleepInt(1);
				press_home(1);
				break;
			}else{
				press_back(1);
			}
		}
		addStep("退出地图");
		intoMapAuthorization();
		int num = 0;
		
		for(int i=0;i<20;i++){
			LeUiObject aaa = new LeUiObject(new UiSelector().className("android.widget.ListView").resourceId("android:id/list")
					.childSelector(new UiSelector().className("android.widget.LinearLayout").index(i)
							.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
									.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
											.childSelector(new UiSelector().className("android.widget.TextView"))))));
			if(!("地图".equals(aaa.getText()))){
				addStep("--------"+aaa.getText());
			}else{
				num = i;
				break;
			}
		}
		
		
		 LeUiObject bbb = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(num)
				 .childSelector(new UiSelector().className("android.widget.TextView").index(1)));
		
		verify("设定地图允许访问定位失败",(bbb.getText()).equals(allow.getText()));
		addStep("设定地图允许访问定位成功");
//		
		map.click();
		sleepInt(2);
		/*verify("没有找到总是询问", alwaysAsk.exists());
		alwaysAsk.click();
		addStep("设置定位中的地图的权限为总是询问");
		
		press_back(4);
		press_home(1);
		
		intoMap();
		verify("地图申请访问位置信息没有弹出",note.exists());
		addStep("设定总是询问成功");*/
		verify("没有拒绝选项",reject.exists());
		reject.click();
		addStep("设定地图拒绝访问定位");
		sleepInt(2);
		press_back(1);
		for(int i=0;i<4;i++){
			if(quit.exists()){
				verify("有退出地图提示但是没有确定按钮",sure.exists());
				sure.click();
				sleepInt(1);
				press_home(1);
				break;
			}else{
				press_back(1);
			}
		}
		addStep("退出地图");
		intoMapAuthorization();
		for(int i=0;i<20;i++){
			LeUiObject ccc = new LeUiObject(new UiSelector().className("android.widget.ListView").resourceId("android:id/list")
					.childSelector(new UiSelector().className("android.widget.LinearLayout").index(i)
							.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
									.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
											.childSelector(new UiSelector().className("android.widget.TextView"))))));
			if(!("地图".equals(ccc.getText()))){
				addStep("--------"+ccc.getText());
			}else{
				num = i;
				break;
			}
		}
		LeUiObject ddd = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(num)
				.childSelector(new UiSelector().className("android.widget.TextView").index(1)));
		verify("设定地图拒绝访问定位失败",(ddd.getText()).equals(reject.getText()));
		addStep("设定地图拒绝访问定位成功");
		press_back(4);
		press_home(1);
		addStep("退出到主界面");
	}
	
	public void intoMapAuthorization() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().resourceId("android:id/decor_content_parent").packageName("com.android.settings")));	
		LeUiObject Authorization = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("隐私授权").resourceId("android:id/title"));
		verify("没有进入到设置中",settingPanel.exists());
		settingPanel.setAsVerticalList();
		settingPanel.scrollBackward();
		settingPanel.scrollBackward();
		for (int i = 0; i < 5; i++) {
			if (!Authorization.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}
		verify("没有找到隐私授权", Authorization.exists());
		Authorization.click();
		sleepInt(2);
		LeUiObject AuthorizationManager = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("应用权限管理"));
		verify("没有进入到隐私授权",AuthorizationManager.exists());
		AuthorizationManager.click();
		sleepInt(2);
		swipe_screen((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8), 
				(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.5), 30);
		LeUiObject location = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("定位").resourceId("android:id/title"));
		for(int i=0;i<3;i++){
			if (location.exists()) {
				break;
			}
			phone.swipe(550, 1600, 550, 659, 80);
			sleepInt(2);
		}
		
		verify("没有进入到权限管理中",location.exists());
		location.click();
		sleepInt(2);
		LeUiObject map = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("地图").resourceId("android:id/title"));
		verify("没有进入到定位中",map.exists());

	}
	
	public void intoMap() throws UiObjectNotFoundException {
		launchApp(AppName.MAP);
		LeUiObject sure = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("确定"));
		LeUiObject welcomePage = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/guide_view_page"));
		
		if(sure.exists()){
			sure.click();
			sleepInt(2);
		}
		if(welcomePage.exists()){
			phone.swipe(1080, 1150, 280, 1150, 80);
			sleepInt(2);
			phone.swipe(1080, 1150, 280, 1150, 80);
			sleepInt(2);
			phone.swipe(1080, 1150, 280, 1150, 80);
			sleepInt(2);
			phone.click(540, 1745);
			sleepInt(4);
		}
		
	}
	

}

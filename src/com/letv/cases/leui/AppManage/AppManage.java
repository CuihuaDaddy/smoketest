package com.letv.cases.leui.AppManage;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class AppManage extends LetvTestCase {

	@Override
	public void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	    
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	@CaseName("卸载应用程序")
	public void testUninstall() throws UiObjectNotFoundException {
		addStep("1.进入settings");
		launchApp(AppName.SETTING);		
		sleepInt(2);
		LeUiObject show = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("显示"));
		verify("未能进入应用",show.exists());
		LeUiObject appManage = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("应用管理"));
		for (int i = 0; i < 10; i++) {
			if(appManage.exists()){
				break;
			}else{
				phone.swipe(500, 1400, 500, 650, 10);
				sleepInt(2);
			}
		}
		verify("应用管理不存在",appManage.exists());
		appManage.click();
		sleepInt(5);
		LeUiObject installed = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("已安装"));
		verify("已安装不存在",installed.exists());
		installed.click();
		sleepInt(3);
		addStep("2.卸载应用文件管理器");
		LeUiObject filemanager = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("文件管理器"));
		verify("准备卸载的文件管理器不存在",filemanager.exists());
		filemanager.click();
		sleepInt(3);
		LeUiObject uninstall = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("卸载"));
		verify("卸载按钮不存在",uninstall.exists());
		uninstall.click();
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("确定"));
		verify("确定按钮不存在",confirm.exists());
		confirm.click();
		sleepInt(5);
		verify("卸载不成功",!filemanager.exists());
		
	}
	
	
	@CaseName("清除应用数据/缓存")
	public void testClearData() throws UiObjectNotFoundException {
		addStep("1.进入settings");
		launchApp(AppName.SETTING);
		sleepInt(2);
		LeUiObject show = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("显示"));
		verify("未能进入应用",show.exists());
		LeUiObject appManage = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("应用管理"));
		for (int i = 0; i < 10; i++) {
			if(appManage.exists()){
				break;
			}else{
				phone.swipe(500, 1400, 500, 650, 10);
				sleepInt(2);
			}
		}
		verify("应用管理不存在",appManage.exists());
		appManage.click();
		sleepInt(5);
		LeUiObject all = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("全部"));
		verify("全部不存在",all.exists());
		all.click();
		sleepInt(3);
		LeUiObject app = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(6)
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)));
		verify("准备要清楚的应用不存在",app.exists());
		app.click();
		sleepInt(3);
		LeUiObject dataSize = new LeUiObject(new UiSelector().resourceId("com.android.settings:id/data_size_text")
				.className("android.widget.TextView"));
		verify("数据大小字段不存在",dataSize.exists());
		if(dataSize.getText().equals("0.00 B")){
			System.out.println("数据大小为0，用例结束");
		}else{
			LeUiObject clearData = new LeUiObject(new UiSelector().className(
					"android.widget.Button").text("清除数据").enabled(true));
			verify("清楚数据的button不存在",clearData.exists());
			clearData.click();
			LeUiObject confirm = new LeUiObject(new UiSelector().className(
					"android.widget.Button").text("确定"));
			verify("确定按钮不存在",confirm.exists());
			confirm.click();
			sleepInt(2);
			verify("清楚数据失败",dataSize.getText().equals("0.00 B"));
		}
	}
	
	
	@CaseName("停止应用程序")
	public void testStopApp() throws UiObjectNotFoundException {
		addStep("1.进入settings");
		launchApp(AppName.SETTING);
		sleepInt(2);
		LeUiObject show = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("显示"));
		verify("未能进入应用",show.exists());
		LeUiObject appManage = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("应用管理"));
		for (int i = 0; i < 10; i++) {
			if(appManage.exists()){
				break;
			}else{
				phone.swipe(500, 1400, 500, 650, 10);
				sleepInt(2);
			}
		}
		verify("应用管理不存在",appManage.exists());
		appManage.click();
		sleepInt(5);
		LeUiObject all = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("全部"));
		verify("全部不存在",all.exists());
		all.click();
		sleepInt(3);
		LeUiObject app = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(6)
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)));
		verify("准备要清楚的应用不存在",app.exists());
		app.click();
		sleepInt(3);
		LeUiObject stop = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("强行停止"));
		verify("强制停止按钮不存在",stop.exists());
		stop.click();
		sleepInt(3);
		LeUiObject cancel = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("取消"));
		if(cancel.exists()){
			System.out.println("强制停止可能会导致出现异常，因此取消");
			cancel.click();
		}else{
			verify("强制停止不成功",!stop.exists());
		}
	}
}

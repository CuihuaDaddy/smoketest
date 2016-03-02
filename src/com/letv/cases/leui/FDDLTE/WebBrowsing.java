package com.letv.cases.leui.FDDLTE;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class WebBrowsing extends LetvTestCase {
	private String url = "www.sina.cn";
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		press_back(5);
		disconnectData();
	}
	@CaseName("SIM1: WEB Browsing ")
	public void testBrowsingSim1() throws UiObjectNotFoundException {
		connectData();
		switch4G();
		addStep("step1:SIM1切换到4G网络下");
		SIM1dataUse();
		addStep("step2:使用SIM1浏览网页");
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		sleepInt(3);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url_input_view"));
		// baidu home page
		LeUiObject baidu = new LeUiObject(new UiSelector().className(
				"android.webkit.WebView").index(0)
				.childSelector(new UiSelector().className("android.view.View").index(0)));
	
		addStep("打开浏览器并正常显示");
		verify("Can't find url input EditText.", urlEdit.exists());
		addStep("输入特定网址（例如：http://m.baidu.com/）并登陆");
		sleepInt(1);
		urlEdit.setText("http://m.baidu.com/");
		sleepInt(1);
		getUiDevice().pressEnter();
		sleepInt(10);
		LeUiObject adChoose = new LeUiObject(new UiSelector().resourceId(
				"android:id/buttonPanel").index(2)
				.childSelector(new UiSelector().resourceId("android:id/button3").index(0)));
		if(adChoose.exists()){
			adChoose.click();
		}
		addStep("确认登陆网页显示正常");
		verify("Can't open let home page.", baidu.exists());
		addStep("返回到浏览器首页");
		press_back(1);
		addStep("退出浏览器");
		press_back(5);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	@CaseName("SIM2: WEB Browsing")
	public void testBrowsingSim2() throws UiObjectNotFoundException {
		connectData();
		switch4G();
		addStep("step1:SIM2切换到4G网络下");
		SIM2dataUse();
		addStep("step2:使用SIM2浏览网页");
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		sleepInt(8);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url_input_view"));
		// baidu home page
		LeUiObject letweb = new LeUiObject(new UiSelector().className(
				"android.webkit.WebView").index(0)
				.childSelector(new UiSelector().className("android.widget.Button").index(5)));
		addStep("打开浏览器并正常显示");
		verify("Can't find url input EditText.", urlEdit.exists());
		addStep("输入特定网址（例如：http://m.baidu.com/）并登陆");
		sleepInt(1);
		urlEdit.setText("http://m.baidu.com/");
		sleepInt(1);
		getUiDevice().pressEnter();
		sleepInt(10);
		LeUiObject adChoose = new LeUiObject(new UiSelector().resourceId(
				"android:id/buttonPanel").index(2)
				.childSelector(new UiSelector().resourceId("android:id/button3").index(0)));
		if(adChoose.exists()){
			adChoose.click();
		}
		addStep("确认登陆网页显示正常");
		verify("Can't open let home page.", letweb.exists());
		addStep("返回到浏览器首页");
		press_back(1);
		addStep("退出浏览器");
		press_back(5);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

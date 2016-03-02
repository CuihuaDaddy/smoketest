package com.letv.cases.leui.WCDMA;

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
		connectData();
		switch3G();
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		switch4G();
		disconnectData();
		super.tearDown();
	}
	@CaseName("SIM1: WEB Browsing ")
	public void testBrowsingSim1() throws UiObjectNotFoundException {
		addStep("step1:SIM1切换到3G网络下");
		SIM1dataUse();
		addStep("step2:使用SIM1浏览网页");
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url"));
		// sina home page
		LeUiObject sina = new LeUiObject(new UiSelector().resourceId(
				"com.android.browser:id/url").textContains("sina"));
		addStep("打开浏览器并正常显示");
		verify("Can't find url input EditText.", urlEdit.exists());
		addStep("输入特定网址（例如：www.sina.cn）并登陆");
		urlEdit.click();
		callShell("input text " + url);
		sleepInt(1);
		getUiDevice().pressEnter();
		sleepInt(10);
		addStep("确认登陆网页显示正常");
		verify("Can't open sina home page.", sina.exists());
		addStep("返回到浏览器首页");
		press_back(1);
		addStep("退出浏览器");
		press_back(5);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	@CaseName("SIM2: WEB Browsing")
	public void testBrowsingSim2() throws UiObjectNotFoundException {
		addStep("step1:SIM2切换到3G网络下");
		SIM2dataUse();
		addStep("step2:使用SIM2浏览网页");
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url"));
		// sina home page
		LeUiObject sina = new LeUiObject(new UiSelector().resourceId(
				"com.android.browser:id/url").textContains("sina"));
		addStep("打开浏览器并正常显示");
		verify("Can't find url input EditText.", urlEdit.exists());
		addStep("输入特定网址（例如：www.sina.cn）并登陆");
		urlEdit.click();
		callShell("input text " + url);
		sleepInt(1);
		getUiDevice().pressEnter();
		sleepInt(10);
		addStep("确认登陆网页显示正常");
		verify("Can't open sina home page.", sina.exists());
		addStep("返回到浏览器首页");
		press_back(1);
		addStep("退出浏览器");
		press_back(5);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

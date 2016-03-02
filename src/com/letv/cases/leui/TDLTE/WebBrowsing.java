package com.letv.cases.leui.TDLTE;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class WebBrowsing extends LetvTestCase {
	private String url = "http://sina.cn/";
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
	}
	@Override
	protected void tearDown() throws Exception {
		/*LeUiObject webSetting = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_layout")
				.childSelector(new UiSelector().resourceId("com.android.browser:id/menu_bar_more").index(4)));
		if(webSetting.exists()){
			webSetting.click();
			sleepInt(3);
			LeUiObject exit = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(7)
					.childSelector(new UiSelector().resourceId("com.android.browser:id/menu_item_text").index(1)));
			verify("退出按钮不存在",exit.exists());
			exit.click();
			sleepInt(2);
		}*/
		switch4G();
		super.tearDown();
		
		
	}
	@CaseName("SIM1: WEB Browsing ")
	public void testBrowsingSim1() throws UiObjectNotFoundException {
		connectData();
		switch4G();
		addStep("step1:SIM1切换到3G网络下");
		SIM1dataUse();
		sleepInt(5);
		addStep("step2:使用SIM1浏览网页");
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		sleepInt(8);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url_input_view"));
		// baidu home page
		LeUiObject sina = new LeUiObject(new UiSelector().className(
				"android.webkit.WebView"));
	
		addStep("打开浏览器并正常显示");
		verify("Can't find url input EditText.", urlEdit.exists());
		addStep("输入特定网址（http://sina.cn/）并打开网页");
		sleepInt(1);
		urlEdit.setText("http://sina.cn/");
		sleepInt(3);
		getUiDevice().pressEnter();
		sleepInt(10);
		LeUiObject adChoose = new LeUiObject(new UiSelector().resourceId(
				"android:id/buttonPanel").index(2)
				.childSelector(new UiSelector().resourceId("android:id/button3").index(0)));
		if(adChoose.exists()){
			adChoose.click();
		}
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
		connectData();
		switch4G();
		addStep("step1:SIM2切换到3G网络下");
		SIM2dataUse();
		sleepInt(5);
		addStep("step2:使用SIM2浏览网页");
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		sleepInt(8);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url_input_view"));
		// sina home page
		LeUiObject sina = new LeUiObject(new UiSelector().className(
				"android.webkit.WebView"));
		addStep("打开浏览器并正常显示");
		verify("Can't find url input EditText.", urlEdit.exists());
		addStep("输入特定网址（例如：http://sina.cn/）并登陆");
		sleepInt(1);
		urlEdit.setText("http://sina.cn/");
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
		verify("Can't open let home page.", sina.exists());
		addStep("返回到浏览器首页");
		press_back(1);
		addStep("退出浏览器");
		press_back(5);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

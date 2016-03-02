package com.letv.cases.leui.browser;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Browser extends LetvTestCase {

	private String url = "www.sina.cn";	
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		callShell("am force-stop com.android.browser");
	}

	private void clearCache() throws UiObjectNotFoundException {
		LeUiObject history = new LeUiObject(new UiSelector().text("历史"));
		verify("Can't find history button.", history.exists());
		history.click();
	}

	@CaseName("打开特定的web页面")
	public void testOpenPage() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		// url input edit text
		// LeUiObject urlEdit = new LeUiObject(new UiSelector()
		// .className("com.letv.leui.widget.LeEditText").text("搜索或输入网址")
		// .resourceId("com.android.browser:id/url"));
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"com.android.browser:id/title_display"));
		// open page button
		LeUiObject goButton = new LeUiObject(new UiSelector().text("前往"));
		// sina home page
		LeUiObject sina = new LeUiObject(new UiSelector().text("手机新浪网").resourceId(
				"com.android.browser:id/url_input_view"));
		// home page button
		LeUiObject home = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(2));
		// page tab button
		// LeUiObject tab = new LeUiObject(new UiSelector()
		// .resourceId("android:id/icon").index(0).instance(4));
		// close tab button
		verify("Can't find home page button.", home.exists());
		home.click();
		sleepInt(3);
		LeUiObject close = new LeUiObject(
				new UiSelector().resourceId("com.android.browser:id/closetab"));
		for (int i = 0; i < getIntParams("Loop"); i++) {
			// clearCache();
			addStep("打开浏览器并正常显示");
			// getUiDevice().dumpWindowHierarchy("tttt");
			// verify("Can't find url input EditText.",
			// urlEdit.waitForExists(5000));
			for (int k = 0; k < 10; i++) {
				if(urlEdit.exists()){
					break;
				}
				sleepInt(1);
			}
			verify("Can't find url input EditText.", urlEdit.exists());
			addStep("输入特定网址（www.sina.cn）并进入网页");
			urlEdit.click();
			// urlEdit.setText(url);
			callShell("input text " + url);
			sleepInt(1);
			// verify("Can't find open page button.", goButton.exists());
			// goButton.click();
			getUiDevice().pressEnter();
			sleepInt(10);
			addStep("确认登陆网页显示正常");
			verify("Can't open sina home page.", sina.exists());
			addStep("返回到浏览器首页");
			// if (tab.exists()) {
			// tab.click();
			// sleepInt(1);
			// if (close.exists()) {
			// close.click();
			// sleepInt(1);
			// }
			// }
			verify("Can't find home page button.", home.exists());
			home.click();
			sleepInt(1);
			// System.out.println("===================click=================");
		}
		addStep("退出浏览器");
		press_back(5);
	}

	@CaseName("点击网页上的链接")
	public void testClickLink() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		addStep("进入特定网页");
		LeUiObject sina = new LeUiObject(new UiSelector()
				.className("android.widget.ImageView")
				.resourceId("com.android.browser:id/thumb").index(0));
		verify("Can't open home page.", sina.exists());
		sina.click();
		sleepInt(10);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("点击网页上固定链接");
			phone.click(100, 680);
			sleepInt(5);
			addStep("验证链接可进入");
			addStep("返回特定网页页面");
			press_back(1);
			sleepInt(2);
		}
		addStep("返回浏览器主页面");
		// home page button
		LeUiObject home = new LeUiObject(new UiSelector()
				.resourceId("android:id/icon").index(0).instance(2));
		verify("Can't find home page button.", home.exists());
		home.click();
		sleepInt(3);
		addStep("退出浏览器");
		press_back(5);
	}
}

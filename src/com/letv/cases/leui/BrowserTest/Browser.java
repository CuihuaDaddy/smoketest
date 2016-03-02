package com.letv.cases.leui.BrowserTest;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;
import com.letv.uf.Utf7ImeHelper;

public class Browser extends LetvTestCase {
	private String url = "http://sina.cn/";	
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
	
	
	@CaseName("启动浏览器应用,并且退出浏览器")
	public void testOpenBrowser() throws UiObjectNotFoundException {
		addStep("step1:Launch browser");
		launchApp(AppName.BROWSER);
		sleepInt(10);
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url_input_view"));
		verify("does not launch browser successful",urlEdit.exists());
		sleepInt(2);
		addStep("step2:Back to desktop via press home button");
		press_home(1);
		sleepInt(3);
		verify("does not back to desktop", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.BROWSER);
		sleepInt(5);
		verify("doesn't enter browser success",urlEdit.exists());
		addStep("step3:Back to desktop via press home key,then enter again via multi-control center.");
		
		press_menu(1);
		sleepInt(2);
		LeUiObject clear = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").resourceId("com.android.systemui:id/leui_recent_clear_all_txtview"));
		clear.click();
		sleepInt(3);
		LeUiObject recent = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		LeUiObject recent2 = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		verify("Recent history does not exist",recent.exists()||recent2.exists());
		if(recent.exists()){
			recent.click();
		}else{
			recent2.click();
		}
		sleepInt(3);
		verify("Does not enter browser",urlEdit.exists());
		addStep("exit browser");
		press_back(1);
		press_home(1);
		verify("does not back to desktop", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	@CaseName("打开特定的web页面")
	public void testOpenPage() throws UiObjectNotFoundException {
		addStep("Launch browser");
		launchApp(AppName.BROWSER);
		sleepInt(5);
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"com.android.browser:id/title_display"));
		// sina home page
		LeUiObject sina = new LeUiObject(new UiSelector().textContains("手机新浪网").resourceId(
				"com.android.browser:id/title_display"));
		// home page button
		LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
		verify("Can't find home page button.", home.exists());
		home.click();
		sleepInt(3);
		addStep("Verify current page is home page");
		verify("Can't find url input EditText.", urlEdit.exists());
		addStep("input 'www.sina.cn'");
		urlEdit.click();
		sleepInt(5);
		urlEdit.setText(Utf7ImeHelper.e(url));
		sleepInt(2);
		getUiDevice().pressEnter();
		addStep("verify current page is sina.cn");
		verify("Can't open sina home page.", sina.exists());
		addStep("exist browser");
		press_back(3);
	}
	
	@CaseName("浏览器本地书签")
	public void testBookmark() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		sleepInt(5);
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url_input_view"));
		LeUiObject sina = new LeUiObject(new UiSelector().textContains("手机新浪网"));
		LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
		verify("Can't find home page button.", home.exists());
		home.click();
		sleepInt(3);
		addStep("打开浏览器并正常显示");
		verify("Can't find url input EditText.", urlEdit.exists());
		addStep("输入特定网址（例如：www.sina.cn）并登陆");
		urlEdit.click();
		urlEdit.setText(url);
		sleepInt(2);
		getUiDevice().pressEnter();
		addStep("确认登陆网页显示正常");
		for (int i = 0; i < 30; i++) {
			if(sina.exists())
			{
				break;
			}
			sleepInt(1);
		}
		verify("Can't open sina home page.", sina.exists());
		LeUiObject share = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_share").index(1));
		verify("没有分享button",share.exists());
		share.click();
		sleepInt(3);
		LeUiObject addbookmark = new LeUiObject(new UiSelector().text("加入书签"));
		verify("没有增加到收藏按钮",addbookmark.exists());
		addbookmark.click();
		sleepInt(3);
		LeUiObject no = new LeUiObject(new UiSelector().className("android.widget.Button").text("暂不"));
		if(no.exists()){
			no.click();
		}
		sleepInt(3);
		
		LeUiObject save = new LeUiObject(new UiSelector().className("android.widget.TextView").text("保存"));
		verify("没有书签项",save.exists());
		save.click();
		sleepInt(3);
		LeUiObject replace = new LeUiObject(new UiSelector().textContains("覆盖"));
		if(replace.exists()){
			replace.click();
			sleepInt(2);
		}
		addStep("添加新浪网至书签");
		verify("Can't find home page button.", home.exists());
		home.click();
		sleepInt(3);
		addStep("返回到浏览器首页");
		LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
		verify("没有更多项",more.exists());
		more.click();
		sleepInt(3);		
		LeUiObject Favorites = new LeUiObject(new UiSelector().className("android.widget.TextView").text("书签/历史"));
		verify("没有收藏选项",Favorites.exists());
		Favorites.click();
		sleepInt(3);
		LeUiObject SinaBM = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/title").text("手机新浪网"));
		verify("没有新浪网页的书签",SinaBM.exists());
		SinaBM.click();
		for(int i=0;i<30;i++){
			if(sina.exists()){
				break;
			}
		}
		verify("Can't open sina home page.", sina.exists());
		addStep("通过书签进入新浪网");
		phone.swipe(538, 1650, 538, 300, 80);
		sleepInt(1);
		phone.swipe(538, 1650, 538, 300, 80);
		sleepInt(1);
		phone.swipe(538, 300, 538, 1650, 80);
		sleepInt(1);
		phone.swipe(538, 1650, 538, 300, 80);
		sleepInt(1);
		phone.swipe(538, 1650, 538, 300, 80);
		sleepInt(3);
		verify("浏览网页失败", !home.exists());
		phone.swipe(538, 247, 538, 1650, 80);
		phone.swipe(538, 247, 538, 1650, 80);
		sleepInt(3);
		verify("浏览网页失败", home.exists());
		addStep("浏览新浪网");	
		press_back(4);
		sleepInt(1);
		press_home(1);
		addStep("退出浏览器");
	}
	
	@CaseName("浏览器多窗口切换")
	public void testwindowswitch() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		sleepInt(5);
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url_input_view"));
		LeUiObject sina = new LeUiObject(new UiSelector().textContains("手机新浪网"));
		LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
		verify("Can't find home page button.", home.exists());
		home.click();
		sleepInt(3);
		LeUiObject windownum = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_window_number").index(3));
		LeUiObject addwindow = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/multi_window_bottom_bar_add_new_page"));
		addStep("打开浏览器并正常显示");
		for (int i = 0; i < 6; i++) {
			verify("Can't find url input EditText.", urlEdit.exists());
			addStep("输入特定网址（例如：www.sina.cn）并登陆");
			urlEdit.click();
			//urlEdit.setText(url);
			sleepInt(1);
			urlEdit.setText(Utf7ImeHelper.e(url));
			sleepInt(1);
			getUiDevice().pressEnter();
			for (int j = 0; j < 30; j++) {
				if (sina.exists()) {
					break;
				}
				sleepInt(1);
			}
			addStep("确认登陆网页显示正常");
			verify("Can't open sina home page.", sina.exists());
			verify("Can't find window num button.", windownum.exists());
			windownum.click();
			sleepInt(1);
			if(i!=5){
			verify("Can't find add window button.", addwindow.exists());
			addwindow.click();	
			sleepInt(2);
			verify("Can't find url input EditText.", urlEdit.exists());
			}
		}
		press_back(4);
		sleepInt(1);
		press_home(1);
		addStep("退出浏览器");		
	}
}

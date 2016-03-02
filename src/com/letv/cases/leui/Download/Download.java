package com.letv.cases.leui.Download;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Download extends LetvTestCase {

	private String dServer = "http://10.57.9.203/test.apk";

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		// clear the download list
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		//closeWifi();
	}

	public void DownloadAPK() throws UiObjectNotFoundException {
		connectWifi();
		addStep("打开浏览器");
		addStep("清除浏览器缓存，历史记录和cookie数据");
		launchApp(AppName.BROWSER);
		LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
		verify("Can't find home page button.", home.exists());
		home.click();
		sleepInt(3);
		addStep("在超链接栏输入一个有下载资源的超链接地址");
		// url input edit text
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
		"android.widget.EditText").resourceId(
		"com.android.browser:id/url_input_view"));
		urlEdit.click();
		sleepInt(1);
		callShell("input text " + dServer);
		sleepInt(1);
		addStep("下载正常文本文件完成");
		phone.pressEnter();
		sleepInt(5);
		LeUiObject download = new LeUiObject(new UiSelector().className("android.widget.Button").textContains("下载"));
		verify("下载弹窗不存在",download.exists());
		download.click();
		sleepInt(10);
	}

	
	@CaseName("安装下载文件")
	public void testInstallAPK() throws UiObjectNotFoundException {
		DownloadAPK();
		LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
		more.click();
	    LeUiObject download = new LeUiObject(new UiSelector().text("下载"));
	    download.click();
		LeUiObject apk = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/track_item"));
		verify("没有下载成功",apk.exists());
		sleepInt(3);
		apk.click();
		LeUiObject allowInstall = new LeUiObject(new UiSelector().className("android.widget.Button").text("解除禁止"));
		if(allowInstall.exists()){
			allowInstall.click();
		}
		sleepInt(8);
		LeUiObject install = new LeUiObject(new UiSelector().resourceId("com.android.packageinstaller:id/ok_button").text("安装"));
		verify("没有安装按钮",install.exists());
		install.click();
		LeUiObject installDone = new LeUiObject(new UiSelector().resourceId("com.android.packageinstaller:id/done_button"));
		verify("没有安装成功",installDone.waitForExists(20000));
		
	}
	
	@CaseName("分享下载文件")
	public void testShareAPK() throws UiObjectNotFoundException {
		launchApp(AppName.DOWNLOAD);
		sleepInt(2);
		addStep("打开下载管理器");
		LeUiObject testAPK = new LeUiObject(new UiSelector().resourceId("com.android.documentsui:id/complete_filename").textContains(".apk"));
		verify("没有下载成功",testAPK.exists());
		sleepInt(1);
		int x=testAPK.getBounds().centerX();
		int y=testAPK.getBounds().centerY();
		phone.swipe(x, y, x, y, 50);
		sleepInt(10);
		LeUiObject share = new LeUiObject(new UiSelector().resourceId("com.android.packageinstaller:id/install_ok_text").text("应用安装完成。"));
		verify("没有安装成功",share.exists());
		addStep("退出浏览器");
		press_back(5);
		press_home(1);
		
	}
	@CaseName("删除下载文件")
	public void testdeleteAPK() throws UiObjectNotFoundException {
		launchApp(AppName.BROWSER);
		LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
		verify("点击浏览器的更多按钮",more.exists());
		more.click();
		sleepInt(3);
		LeUiObject download = new LeUiObject(new UiSelector().className("android.widget.GridView")
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
						.childSelector(new UiSelector().text("下载"))));
		verify("下载不存在",download.exists());
		download.click();
		sleepInt(2);
		LeUiObject noThings = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/download_no_records"));
		if(noThings.exists()){
			System.out.println("没有下载文档");
		}else{
			sleepInt(4);
			addStep("打开下载");
			LeUiObject testAPK = new LeUiObject(new UiSelector().textContains(".apk"));
			verify("没有下载成功",testAPK.exists());
			sleepInt(1);
			int x=testAPK.getBounds().centerX();
			int y=testAPK.getBounds().centerY();
			phone.swipe(x, y, x, y, 80);
			sleepInt(4);
			LeUiObject delete = new LeUiObject(new UiSelector().textContains("删除"));
			delete.click();
			sleepInt(2);
			verify("没有删除按钮",delete.exists());
			LeUiObject delete1 = new LeUiObject(new UiSelector().text("确认删除1条下载记录"));
			delete1.click();
			sleepInt(2);
			verify("没有删除成功",!testAPK.exists());
			addStep("退出下载管理器");
			press_back(3);
			press_home(1);
		}
		
		
	}
}

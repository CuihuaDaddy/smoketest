package com.letv.cases.leui.multimedia;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class OnlineStream extends LetvTestCase {

	private String link = "http://10.57.9.203/httpvod/video/flv-h264.html";
	
	@CaseName("在线流媒体")
	public void testOnlineVideo() throws UiObjectNotFoundException {
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("打开浏览器并正常显示首页");
			launchApp(AppName.BROWSER);
			addStep("清除浏览器缓存，历史记录和cookie数据");
			LeUiObject home = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(2));
			verify("Can't find home page button.", home.exists());
			home.click();
			sleepInt(1);
			addStep("在地址栏中输入流媒体地址");
			LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.browser:id/url_input_view"));
			verify("Can't find url input EditText.", urlEdit.exists());
			urlEdit.click();
			sleepInt(1);
			callShell("input text " + link);
			// LeUiObject goButton = new LeUiObject(new UiSelector().text("前往"));
			getUiDevice().pressEnter();
			sleepInt(5);			
			addStep("播放节目5秒钟");
			callShell("input tap 461 487");
			sleepInt(8);
			LeUiObject webkit = new LeUiObject(new UiSelector().className(
				"android.webkit.WebView"));
			verify("没在浏览器页面.", webkit.exists());
//			callShell("input tap 25 730");
//			LeUiObject play = new LeUiObject(new UiSelector().className(
//					"android.widget.SeekBar").fromParent(
//					new UiSelector().className("android.widget.Button")));
//			verify("Can't find play button.", play.exists());
//			play.click();			
			addStep("关闭播放");
//			play.click();
//			System.out.println("1111111111111111111");
//			LeUiObject tabs = new LeUiObject(new UiSelector()
//					.resourceId("android:id/icon").index(0).instance(3));
//			System.out.println("2222222222222222222");
//			verify("Can't find tab button.", tabs.exists());
//			System.out.println("333333333333333");
//			tabs.click();
//			System.out.println("44444444444444444");
//			callShell("input tap 700 1850");
			press_back(1);
			sleepInt(1);
//			LeUiObject close = new LeUiObject(
//					new UiSelector()
//							.resourceId("com.android.browser:id/closetab"));
//			verify("Can't find close button.", close.exists());
//			close.click();
//			callShell("input swipe 300 600 300 100");
//			sleepInt(2);
		}
		addStep("退出浏览器");
		press_back(5);
	}
}

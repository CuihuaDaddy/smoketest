package com.letv.cases.leui.Weibo;


import java.io.File;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

import android.util.Log;

public class LetvSinaWeibo extends LetvTestCase {
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		registerWeiboWatcher();
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		unregisterWatcher();
		super.tearDown();
	}
	
	@CaseName("启动新浪微博, 验证微博标题, 退出新浪微博")
	public void testLaunchSinaWeibo() throws UiObjectNotFoundException {
		addStep("step1 : 启动新浪微博");
		//launchApp(AppName.LESPORT);
		launchAppByPackage("com.sina.weibo/com.sina.weibo.SplashActivity");
		
		LeUiObject iterm = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("确定"));
		if(iterm.exists()){
			iterm.click();
		}
		sleepInt(9);
		
		LeUiObject loc_agree = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("确定"));
		if(loc_agree.exists()){
			loc_agree.click();
		}
		sleepInt(5);
		
		addStep("step2 : 验证新浪微博标题");
		LeUiObject weibo_title = new LeUiObject(
				new UiSelector().resourceId("com.sina.weibo:id/video_root_view"));		
		verify("没有成功进入到新浪微博",weibo_title.exists());
		
		addStep("step3 : 退出新浪微博");
		press_back(3);
		press_home(1);
		sleepInt(5);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	
	}
	private void registerWeiboWatcher() {
		UiWatcher weiboWatcher = new UiWatcher() {
			public boolean checkForCondition() {
				UiObject weiboWindows = new UiObject(new UiSelector()
						.className("android.widget.TextView").textContains(
								"读取联系人"));
				if (weiboWindows.exists()) {
					try {
						new UiObject(new UiSelector().className(
								"android.widget.Button").text("允许")).click();
						sleepInt(2);
					} catch (UiObjectNotFoundException e) {
						e.printStackTrace();
					} finally {

					}
					return true;
				} else {
					return false;
				}
			}
		};
	
		
		getUiDevice().registerWatcher("weiboWatcher", weiboWatcher);
	}
	
	private void unregisterWatcher() {
		
		getUiDevice().removeWatcher("weiboWatcher");
	}

}

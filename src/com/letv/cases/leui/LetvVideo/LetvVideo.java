/**
 * 
 */
package com.letv.cases.leui.LetvVideo;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class LetvVideo extends LetvTestCase {
	
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
	
	@CaseName("启动乐视视频, 验证乐视视频标题, 退出乐视视频")
	public void testLaunchLetvVideo() throws UiObjectNotFoundException {
		addStep("step1 : 启动乐视视频");
		launchApp(AppName.LETVVIDEO);
		sleepInt(10);
		//launchAppByPackage("com.sina.weibo/com.sina.weibo.SplashActivity");
		
		LeUiObject update_later_btn = new LeUiObject(new UiSelector().className(
				"android.widget.Button").resourceId(
					"com.letv.android.client:id/update_later_btn").index(5));
		if(update_later_btn.exists()){
			update_later_btn.click();
		}
		
		addStep("step2 : 验证乐视视频首页标题");
		sleepInt(5);
		LeUiObject letvvideo_home_title = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
					"com.letv.android.client:id/title").index(1).text("首页"));
		verify("没有成功进入到乐视视频", letvvideo_home_title.exists());
		
		addStep("step3 : 退出乐视视频");
		press_back(3);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

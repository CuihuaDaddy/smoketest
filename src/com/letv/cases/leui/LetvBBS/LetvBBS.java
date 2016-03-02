package com.letv.cases.leui.LetvBBS;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class LetvBBS extends LetvTestCase {
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
	
	@CaseName("启动乐迷社区， 验证乐迷社区标题, 退出乐迷社区")
	public void testLaunchLetvBBS() throws UiObjectNotFoundException {
		addStep("step1 : 启动乐迷社区");
		//launchApp(AppName.LETVVIDEO);
		launchAppByPackage("com.letv.bbs/com.letv.bbs.LoadingActivity");
		sleepInt(5);
		
		LeUiObject agree_btn = new LeUiObject(new UiSelector().className(
				"android.widget.Button").resourceId(
					"com.letv.bbs:id/btn_agree").index(3));
		if(agree_btn.exists())
		{
			agree_btn.click();
		}
		
		addStep("step2 : 验证乐迷社区底部功能按键");
		sleepInt(5);
		LeUiObject stop_refresh_btn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
					"com.letv.bbs:id/button_stop_refresh").index(0));
		
		LeUiObject push_url_btn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
					"com.letv.bbs:id/button_pushurl").index(0));
		
		if (stop_refresh_btn.exists())
		{
			addStep("step2.1 : 验证乐迷社区底部功能按键stop_refresh");
			verify("没有成功进入到乐迷社区", stop_refresh_btn.exists());
		}
		else if (push_url_btn.exists())
		{
			addStep("step2.2 : 验证乐迷社区底部功能按键push_url");
			verify("没有成功进入到乐迷社区", push_url_btn.exists());
		}
		else
		{
			verify("没有成功进入到乐迷社区", stop_refresh_btn.exists());
			verify("没有成功进入到乐迷社区", push_url_btn.exists());
		}
		
		addStep("step3 : 退出乐迷社区");
		press_back(3);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

}

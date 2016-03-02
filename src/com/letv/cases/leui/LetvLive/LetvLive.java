package com.letv.cases.leui.LetvLive;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class LetvLive extends LetvTestCase {
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
	
	@CaseName("启动乐视Live， 验证乐视Live标题, 退出乐视Live")
	public void testLaunchLetvLive() throws UiObjectNotFoundException {
		addStep("step1 : 启动乐视Live");
		//launchApp(AppName.LETVVIDEO);
		launchAppByPackage("com.letv.android.letvlive/com.letv.android.letvlive.LiveActivity");
		sleepInt(5);
		
		LeUiObject agree_btn = new LeUiObject(new UiSelector().className(
				"android.widget.Button").resourceId(
					"com.letv.android.letvlive:id/btn_agree").index(3));
		if(agree_btn.exists())
		{
			agree_btn.click();
		}
		
		addStep("step2 : 验证乐视Live功能按键");
		sleepInt(5);
		LeUiObject setting_btn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
					"com.letv.android.letvlive:id/btn_setting").index(2));
		
		LeUiObject search_box = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
					"com.letv.android.letvlive:id/mine_pic").index(0));
		
		if (setting_btn.exists())
		{
			addStep("step2.1 : 验证乐视Live功能按键setting_btn");
			verify("没有成功进入到乐视Live", setting_btn.exists());
		}
		else if (search_box.exists())
		{
			addStep("step2.2 : 验证乐视Live搜索框search_box");
			verify("没有成功进入到乐视Live", search_box.exists());
		}
		else
		{
			verify("没有成功进入到乐视Live", setting_btn.exists());
			verify("没有成功进入到乐视Live", search_box.exists());
		}
		
		
		addStep("step3 : 退出乐视Live");
		press_back(3);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		
	}

}

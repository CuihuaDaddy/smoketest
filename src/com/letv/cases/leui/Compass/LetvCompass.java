package com.letv.cases.leui.Compass;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class LetvCompass extends LetvTestCase {
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
	
	@CaseName("启动指南针, 退出指南针")
	public void testLaunchLetvCompass() throws UiObjectNotFoundException 
	{		
		addStep("step1 : 启动指南针");
		//launchApp("指南针");
		launchAppByPackage("com.letv.android.compass/com.letv.android.compass.MainActivity");
		sleepInt(5);
		
		addStep("step1.1 : 点击同意并继续");
		LeUiObject agree_btn = new LeUiObject(new UiSelector().className(
				"android.widget.Button").resourceId(
					"com.letv.bbs:id/btn_agree").index(3));
		if(agree_btn.exists())
		{
			agree_btn.click();
		}
		
		//addStep("step2 : 验证首次使用校准界面");
		//
		//
		
		//addStep("step3 : 验证方向标题资源");
		//LeUiObject title_orientation = new LeUiObject(
		//	new UiSelector().className("android.widget.TextView")
		//	.resourceId("com.letv.android.compass:id/textview_orientation").index(0));
		// verify("未能验证方向标题资源", title_orientation.exists());	
		
		
		addStep("step3 : 退出指南针");
		press_back(2);
		press_back(2);
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));		
	}
}


package com.letv.cases.leui.multitask;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.cases.proto.others.LaunchApps;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class MultiTask extends LetvTestCase {
	@CaseName("滑动查看应用任务")
	public void testSlideLookTask() throws UiObjectNotFoundException {
		addStep("点击多任务键");
		launchApp(AppName.MESSAGE);
		press_back(1);
		launchApp(AppName.PHONE);
		press_back(1);
		launchApp(AppName.BROWSER);
		press_back(1);
		launchApp(AppName.CALCULATOR);
		press_back(1);
		launchApp(AppName.CAMERA);
		press_back(1);
		sleepInt(1);
		press_menu(1);
		sleepInt(1);
		addStep("查看多任务页面显示");
		LeUiObject recent = new LeUiObject(new UiSelector().resourceId("com.android.systemui:id/acsv")
						.childSelector(new UiSelector().className("android.widget.LinearLayout")));
		verify("最近打开的应用不存在",recent.exists());
		System.out.println("*******************"+recent.getChildCount());
		verify("最近打开的应用的数目不正确",recent.getChildCount()>4);
		for(int i=1;i<3;i++){
			LeUiObject recentSmall = new LeUiObject(new UiSelector().resourceId("com.android.systemui:id/acsv")
							.childSelector(new UiSelector().className("android.widget.LinearLayout")
									.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(i)
											.childSelector(new UiSelector().resourceId("com.android.systemui:id/leui_recent_thumbnail")))));
			verify("缩略图"+i+"不存在",recentSmall.exists());
		}
		press_back(2);
	}
	@CaseName("应用图标进入应用")
	public void testEnterAppFromTask() throws UiObjectNotFoundException {
		addStep("点击多任务键");
		launchApp(AppName.MESSAGE);
		press_back(1);
		launchApp(AppName.BROWSER);
		press_menu(1);
		sleepInt(1);
		LeUiObject recentSmall = new LeUiObject(new UiSelector().resourceId("com.android.systemui:id/acsv")
				.childSelector(new UiSelector().className("android.widget.LinearLayout")
						.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
								.childSelector(new UiSelector().resourceId("com.android.systemui:id/leui_recent_thumbnail")))));
		LeUiObject isEnter = new LeUiObject(new UiSelector().packageName("com.android.browser"));
		addStep("点击应用图标");
		verify("最近打开缩略图不存在",recentSmall.exists());
		recentSmall.click();
		sleepInt(3);
		verify("未能进入应用",isEnter.exists());
		press_back(2);
	}
	
	@CaseName("上滑动清除应用任务")
	public void testSwpieUpClear() throws UiObjectNotFoundException {
		addStep("点击多任务键");
		launchApp(AppName.MESSAGE);
		press_back(1);
		launchApp(AppName.PHONE);
		press_back(1);
		launchApp(AppName.BROWSER);
		press_menu(1);
		sleepInt(1);
		LeUiObject recent = new LeUiObject(new UiSelector().resourceId("com.android.systemui:id/acsv")
				.childSelector(new UiSelector().className("android.widget.LinearLayout")));
		verify("最近打开的应用不存在",recent.exists());
		int recentCount = recent.getChildCount();
		verify("最近打开的应用的数目不正确",recentCount>3);
		swipe_screen((int)(phone.getDisplayWidth()*0.45), (int)(phone.getDisplayHeight()*0.8), (int)(phone.getDisplayWidth()*0.45), (int)(phone.getDisplayHeight()*0.2), 30);
		sleepInt(3);
		verify("最近打开的应用的数目不正确",recentCount-1==recent.getChildCount());
		press_back(2);
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		press_menu(1);
		sleepInt(2);
		LeUiObject clear = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").textContains("立即清理"))));
		if(clear.exists()){
			clear.click();
		}
		super.tearDown();
		
	}
}

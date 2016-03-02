package com.letv.cases.leui.Desktop;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Desktop extends LetvTestCase {
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
	@CaseName("长按添加小插件")
	public void testAddWidget() throws UiObjectNotFoundException {
		//phone.drag(10,100,10,100,50);
		press_home(2);
		sleepInt(2);
		phone.swipe(1350,2150,1350,2150,100);
		sleepInt(3);
		LeUiObject Widget = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("小部件"));
		verify("没有找到小部件.", Widget.exists());
		Widget.click();
		sleepInt(3);
		LeUiObject calendar = new LeUiObject(new UiSelector().
				text("日历"));	
		verify("没有找到日历小部件.", calendar.exists());
		calendar.click();
		sleepInt(2);
		press_home(1);
		LeUiObject calendarTag = new LeUiObject(new UiSelector().
				className("android.widget.ListView").
				resourceId("com.android.calendar:id/events_list").
				index(1));
		verify("日历部件没有添加成功", calendarTag.exists());
		press_home(1);
	}
	
	@CaseName("生成文件夹图标")
	public void testCreatFolder() throws UiObjectNotFoundException {
		LeUiObject sport = new LeUiObject(new UiSelector().className("android.widget.TextView").text("应用商店"));	
		LeUiObject lestore = new LeUiObject(new UiSelector().className("android.widget.TextView").text("遥控"));
		verify("应用商店遥控不存在",sport.exists()&&lestore.exists());
		int phoneX = sport.getBounds().centerX();
		int phoneY = sport.getBounds().centerY();
		int messageX = lestore.getBounds().centerX();
		int messageY = lestore.getBounds().centerY();
		phone.drag(phoneX,phoneY,messageX,messageY,20);
		sleepInt(4);
		LeUiObject folder = new LeUiObject(new UiSelector().className(
				"android.widget.FrameLayout").description("文件夹：文件夹"));
		verify("没有找到文件夹.", folder.exists());
		folder.click();
		sleepInt(2);
		verify("phone 和 message 没有合成一个文件夹",sport.exists()&&lestore.exists());
		addStep("将电话和短信合并图标生成文件夹图标");
		press_home(1);
	}
	
	@CaseName("设置主题")
	public void testSetTheme() throws UiObjectNotFoundException {
		addStep("长按桌面");
		phone.swipe(1282, 2171, 1282, 2171, 200);
		sleepInt(2);
		phone.swipe(1282, 2171, 1282, 2171, 200);
		sleepInt(3);
		addStep("进入主题");
		LeUiObject wallPaper = new LeUiObject(new UiSelector().resourceId("com.android.launcher3:id/theme_button")
				.className("android.widget.TextView").index(1));
		verify("主题不存在",wallPaper.exists());
		wallPaper.click();
		sleepInt(2);
		addStep("滑动并选择主题");
		phone.swipe(1060, 2400, 300, 2400, 30);
		sleepInt(1);
		LeUiObject chooseTheme = new LeUiObject(new UiSelector()
			.resourceId("com.android.launcher3:id/item_thumb").className("android.widget.ImageView"));
		verify("主题不存在",chooseTheme.exists());
		addStep("选择主题");
		chooseTheme.click();
		sleepInt(10);
		
	}
	
}

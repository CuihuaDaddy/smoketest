package com.letv.cases.leui.LetvScan;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class LetvScan extends LetvTestCase {
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
	
	@CaseName("启动扫一扫, 退出扫一扫")
	public void testLaunchLetvScan() throws UiObjectNotFoundException 
	{
		addStep("step1 : 点击Menu按键");
		press_menu(1);
		sleepInt(1);
		
		LeUiObject ack_btn = new LeUiObject(
			new UiSelector().className("android.widget.Button")
			.resourceId("com.android.systemui:id/leui_recent_view_guide_btn").index(3));
		if (ack_btn.exists())
		{
			ack_btn.click();			
		}
		
		addStep("step2 : 滑动屏幕，寻找Scan图标");
		phone.swipe(1308, 234, 111, 234, 50);
		sleepInt(2);
		phone.swipe(1308, 234, 111, 234, 50);
		sleepInt(2);
		
		addStep("step3 : 验证并点击Scan图标");		
		LeUiObject scan_icon = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeHorizontalScrollView").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(10)
				.childSelector(new UiSelector().className("android.widget.ImageView").index(0)
				.resourceId("com.android.systemui:id/image")))));
		verify("未找到Scan图标", scan_icon.exists());
		scan_icon.click();
		sleepInt(2);
		
		addStep("step4 : 验证并点击图库图标");		
		LeUiObject gallery_icon = new LeUiObject(
				new UiSelector().className("android.widget.RelativeLayout").index(3)
				.childSelector(new UiSelector().className("android.widget.ImageView").index(1)
				.resourceId("com.android.browser:id/qrcode_photo")));
		verify("未找到图库图标", gallery_icon.exists());
		gallery_icon.click();
		sleepInt(2);
		
		addStep("step5 : 尝试扫描QRCode图标");
		/*
		LeUiObject qrcode_icon = new LeUiObject(
				new UiSelector().className("android.widget.FrameLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.FrameLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.FrameLayout").index(0)		
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.view.View").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)				
				.childSelector(new UiSelector().className("android.widget.FrameLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.FrameLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.GridView").index(0)
				.childSelector(new UiSelector().className("android.widget.FrameLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.ImageView").index(0)
				.resourceId("com.android.documentsui:id/icon_mime")))))))))))));
		*/
		LeUiObject qrcode_icon = new LeUiObject(
				new UiSelector().className("android.widget.GridView").index(0)
				.childSelector(new UiSelector().className("android.widget.FrameLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.ImageView").index(0)
				.resourceId("com.android.documentsui:id/icon_mime")))));
		for (int i = 0; i < 5; i++) {
			if (!qrcode_icon.exists()) {
				phone.swipe(700, 1200, 700, 500, 30);
				sleepInt(2);
			} 
			else {
				break;
			}
		}
		
		verify("未找到QRCode图标", qrcode_icon.exists());
		// qrcode is not clickable
		//qrcode_icon.click();
		int dx = qrcode_icon.getBounds().centerX();
		int dy = qrcode_icon.getBounds().centerY();
		//phone.swipe(dx, dy, dx, dy, 100);
		phone.click(dx, dy);		
		sleepInt(5);
		
		addStep("step6 : 验证QRCode图标扫描后的结果");
		LeUiObject cancel_icon = new LeUiObject(
				new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.Button").index(4)
				.resourceId("android:id/le_bottomsheet_default_cancel"))));
		
		verify("未找到QRCode图标扫描后的结果 ", cancel_icon.exists());
		cancel_icon.click();
		sleepInt(1);
			
		addStep("step7 : 退出扫一扫");
		press_back(2);
		press_back(2);
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));	
	
	}

}
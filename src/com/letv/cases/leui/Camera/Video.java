package com.letv.cases.leui.Camera;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Video extends LetvTestCase {
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}	
	@CaseName("录制视频")
	public void testRecordVideo() throws UiObjectNotFoundException {
		addStep("打开相机，切换到录像模式");
		launchApp(AppName.CAMERA);
		sleepInt(4);
		
		LeUiObject mode_video1 = new LeUiObject(new UiSelector()
				.resourceId("com.android.camera2:id/mode_video").text("Video")
				.selected(true));
		LeUiObject mode_video2 = new LeUiObject(new UiSelector()
			.resourceId("com.android.camera2:id/mode_video").text("视频")
			.selected(true));
		if(!(mode_video1.exists()||mode_video2.exists())){
			callShell("input swipe 200 900 1000 900");
		}
		
		sleepInt(2);
		verify("Can't switch to video mode.", mode_video1.exists()||mode_video2.exists());
		sleepInt(2);
		addStep("点击开始录像30S");
		LeUiObject record = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
					"com.android.camera2:id/shutter_button").index(0));
		verify("Can't find record button.", record.exists());
		record.click();
		sleepInt(30);
		verify("Can't find record button.", record.exists());
		record.click();
		sleepInt(4);
		addStep("播放录像10S");
		LeUiObject thumb0 = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/thumb_bound"));
		LeUiObject thumb1 = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/preview_thumb_frame").description("Filmstrip view"));
		LeUiObject thumb2 = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/preview_thumb_frame").description("胶卷视图"));
		LeUiObject thumb = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/preview_thumb_frame").className("android.widget.ImageView").index(1));
		
		if (thumb0.exists())
		{
			thumb0.click();
		}
		else if (thumb1.exists())
		{
			thumb1.click();			
		}
		else if (thumb2.exists())
		{
			thumb2.click();			
		}
		else if (thumb.exists())
		{
			thumb.click();
		}
		else
		{
			;
		}
					
		//verify("Can't find thumb button.", (thumb0.exists() || thumb1.exists()||thumb2.exists())&&thumb.exists());
		//thumb.click();
		
		LeUiObject photo = new LeUiObject(new UiSelector().className(
				"android.view.View").resourceId("com.android.gallery3d:id/gl_root_view"));
		sleepInt(3);
		LeUiObject agree = new LeUiObject(new UiSelector().textContains("同意并继续"));
		if(agree.exists()){
			try {
				agree.click();
			} catch (UiObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sleepInt(2);
		}
		verify("Can't find video.", photo.exists());
		int dx = photo.getBounds().centerX();
		int dy = photo.getBounds().centerY();
		phone.click(dx, dy);
		//phone.click(735, 1250);
		sleepInt(1);
		phone.click(dx, dy);
		//phone.click(735, 1250);
		sleepInt(10);
		press_back(1);
		sleepInt(5);
		addStep("删除录像");
		LeUiObject deleteIcon = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(0)
						.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
								.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)
										.childSelector(new UiSelector().className("android.widget.ImageView").index(0)))));
		LeUiObject delete = new LeUiObject(new UiSelector().textContains("删除 1 个视频"));
		int dr = photo.getBounds().bottom;
		dr = dr -100;
		//System.out.printf("right:%d\n", dr);
		phone.click(180, dr);
		sleepInt(2);
		verify("删除ICON不存在",deleteIcon.exists());
		deleteIcon.click();
		sleepInt(3);
		verify("删除不存在",delete.exists());
		delete.click();
		sleepInt(2);
		}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	
}

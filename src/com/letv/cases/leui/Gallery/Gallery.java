package com.letv.cases.leui.Gallery;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Gallery extends LetvTestCase {
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
	@CaseName("启动图库应用")
//	1.启动图库应用
//	2.home键后台图库，再次点击图库
//	3.home键后台图库调用多任务，多任务中点击图库
	public void testOpenGallery() throws UiObjectNotFoundException {
		launchApp(AppName.CAMERA);
		sleepInt(4);
		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/shutter_button"));
		verify("拍照的按钮不存在",shutter.exists());
		shutter.click();
		sleepInt(8);
		press_back(2);
		press_home(1);	
		addStep("step1:启动图库应用");
		launchApp(AppName.GALLERY);
		sleepInt(4);
		LeUiObject albums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums"));
		verify("没有成功进入图库",albums.exists());
		sleepInt(2);
		addStep("step2:home键后台图库，再次点击图库");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.GALLERY);
		sleepInt(5);
		verify("没有成功进入图库",albums.exists());
		addStep("step3:home键后台图库调用多任务，多任务中点击图库");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		press_menu(1);
		sleepInt(2);
		LeUiObject clear = new LeUiObject(new UiSelector()
		.className("android.widget.LinearLayout").index(0)
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
				.childSelector(new UiSelector().className("android.widget.TextView").textContains("立即清理"))));
		if(clear.exists()){
			clear.click();
			sleepInt(3);
			press_menu(1);
		}
		launchApp(AppName.GALLERY);
		sleepInt(2);
		verify("没有成功进入图库",albums.exists());
		addStep("step3:home键后台图库调用多任务，多任务中点击图库");
		press_home(1);
		sleepInt(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		sleepInt(3);
		press_menu(1);
		sleepInt(3);
		LeUiObject recent = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		LeUiObject recent2 = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.view.View")
						.resourceId("com.android.systemui:id/leui_recent_thumbnail")));
		verify("最近不存在",recent.exists()||recent2.exists());
		if(recent.exists()){
			recent.click();
		}else{
			recent2.click();
		}
		sleepInt(3);
		verify("没有成功进入图库",albums.exists());
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		}
	
	@CaseName("查看/编辑/删除照片")
//	查看/编辑/删除照片
	public void testEditPic() throws UiObjectNotFoundException {
		launchApp(AppName.CAMERA);
		sleepInt(4);
		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/shutter_button"));
		verify("拍照的按钮不存在",shutter.exists());
		shutter.click();
		sleepInt(4);
		press_back(2);
		press_home(1);
		addStep("step1:启动图库应用");
		launchApp(AppName.GALLERY);
		sleepInt(2);
		LeUiObject albums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums"));
		verify("没有成功进入图库",albums.exists());
		sleepInt(2);
		LeUiObject firstAlbums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums")
				.childSelector(new UiSelector().text("我的相机").index(0)));
		verify("没有相册存在",firstAlbums.exists());
		firstAlbums.click();
		sleepInt(4);
		LeUiObject picView = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/gl_root_view"));
		verify("没有成功进入到第一个相册内",picView.exists());
		phone.click(181, 366);
		sleepInt(4);
		
		
		sleepInt(2);
		LeUiObject editIcon = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
								.childSelector(new UiSelector().className("android.widget.ImageView").index(0)))));
		for (int i = 0; i < 5; i++) {
			if(editIcon.exists()){
				break;
			}
			phone.click(181, 366);
			sleepInt(2);
		}
		verify("编辑的icon没有找到",editIcon.exists());
		editIcon.click();
		sleepInt(3);
		addStep("step2:编辑照片");
		LeUiObject Crop = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)
								.childSelector(new UiSelector().className("android.widget.ImageView").index(0)))));
		verify("没有Crop按钮",Crop.exists());
		Crop.click();
		LeUiObject Crop1 = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/le_aspect1to1_effect").text("1:1"));
		for (int i = 0; i < 5; i++) {
			if(Crop1.exists()){
				break;
			}
			sleepInt(1);
		}
		verify("没有1:1按钮",Crop1.exists());
		Crop1.click();
		sleepInt(1);
		LeUiObject save1 = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/save").text("Save"));
		LeUiObject save2 = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/save").text("保存"));
		LeUiObject save = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().className("android.widget.TextView").index(0)));
		verify("没有save按钮",(save1.exists()||save2.exists())&&save.exists());
		
		save.click();
		for (int i = 0; i < 15; i++) {
			if(picView.exists()){
				break;
			}
			sleepInt(1);
		}
		verify("没有裁剪成功",picView.exists());
		
		sleepInt(1);
		LeUiObject deleteIcon = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)
								.childSelector(new UiSelector().className("android.widget.ImageView").index(0)))));
		LeUiObject delete1 = new LeUiObject(new UiSelector().className(
				"android.widget.Button").resourceId(
						"android:id/button1").text("Delete"));
		LeUiObject delete2 = new LeUiObject(new UiSelector().className(
				"android.widget.Button").textContains("删除"));
		for (int i = 0; i < 5; i++) {
			if(deleteIcon.exists()){
				break;
			}
			phone.click(366, 366);
			sleepInt(2);
		}
		verify("没有delete按钮",deleteIcon.exists());
		deleteIcon.click();
		sleepInt(3);
		verify("删除不存在",delete1.exists()||delete2.exists());
		if(delete1.exists()){
			delete1.click();
		}else{
			delete2.click();
		}
		sleepInt(3);
		verify("没有删除成功",picView.exists());
		addStep("退出应用");
		press_back(4);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	@CaseName("分享照片")
//	点击照片，选择分享
	public void testsharePic() throws UiObjectNotFoundException {
		launchApp(AppName.CAMERA);
		sleepInt(4);
		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
						"com.android.camera2:id/shutter_button"));
		verify("拍照的按钮不存在",shutter.exists());
		shutter.click();
		sleepInt(4);
		press_back(2);
		press_home(1);
		addStep("step1:启动图库应用");
		launchApp(AppName.GALLERY);
		sleepInt(2);
		LeUiObject firstAlbums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums")
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)));
		verify("没有相册存在",firstAlbums.exists());
		firstAlbums.click();
		sleepInt(2);
		LeUiObject picView = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/gl_root_view"));
		verify("没有成功进入到第一个相册内",picView.exists());
		phone.swipe(181, 366, 181, 366, 100);
		sleepInt(3);
		
		LeUiObject share = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
								.childSelector(new UiSelector().className("android.widget.ImageView").index(0)))));
		verify("没有share按钮",share.exists());
		share.click();
		sleepInt(2);
		LeUiObject Sharewith1 = new LeUiObject(new UiSelector().resourceId("android:id/share_title").text("Share with"));
		LeUiObject Sharewith2 = new LeUiObject(new UiSelector().resourceId("android:id/share_title").text("分享方式"));
		verify("没有Share with提示",Sharewith1.exists()||Sharewith2.exists());
		sleepInt(1);
		addStep("退出应用");
		press_back(4);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	@CaseName("云同步照片")
//	登录乐视账户，同步照片
	public void testSYNCPic() throws UiObjectNotFoundException {
		launchApp(AppName.LEACCOUNT2);
		addStep("step1:进入乐视账号");
		LeUiObject LeUI = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.letv.android.account:id/account_img"));
		sleepInt(5);
		verify("没有进入乐视账号登陆界面", LeUI.exists());
		// 手机号/邮箱
			
		LeUiObject account = new LeUiObject(new UiSelector()
				.className("com.letv.leui.widget.LeEditText").text("手机号/邮箱"));
		LeUiObject clear = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"android:id/le_editor_clear_btn").index(1));
		if (clear.exists()) {
			clear.click();
			sleepInt(3);
		}
		verify("输入账号的地方不存在", account.exists());
		account.click();
		account.setText(getStrParams("LetvUser1"));
		sleepInt(2);
		phone.pressEnter();
		LeUiObject pwd = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeTitleEditText").resourceId(
				"com.letv.android.account:id/password"));
		pwd.setText(getStrParams("LetvPWD1"));
		sleepInt(2);
		phone.pressEnter();
		LeUiObject login = new LeUiObject(new UiSelector()
				.className("android.widget.Button")
				.resourceId("com.letv.android.account:id/btn_login")
				.text("登    录"));
		verify("登录按钮不存在",login.exists());
		login.click();
		LeUiObject logout = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.letv.android.account:id/button1").text("退出账号"));
		for (int i = 0; i < 20; i++) {
			if (!logout.exists()) {
				sleepInt(5);
			} else {
				break;
			}
		}
		verify("登陆乐视账号失败", logout.exists());
		LeUiObject cloudserver = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("云服务"));
		verify("没有云服务", cloudserver.exists());
		cloudserver.click();
		addStep("退出乐视账号");
		logout.click();
		sleepInt(2);
		pwd.setText(getStrParams("LetvPWD1"));
		sleepInt(2);
		LeUiObject OK = new LeUiObject(new UiSelector().className(
				"android.widget.Button").resourceId("android:id/button1"));
		verify("OK按钮不存在",OK.exists());
		OK.click();
		sleepInt(3);
		LeUiObject deleteinfo = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("从手机上删除"));
		verify("从手机上删除字段不存在",deleteinfo.exists());
		deleteinfo.click();
		sleepInt(4);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		press_back(3);
		press_home(1);
	}
}

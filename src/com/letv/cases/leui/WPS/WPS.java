package com.letv.cases.leui.WPS;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class WPS extends LetvTestCase {
	private String wpsContent = "aaaaa";
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		press_back(5);
	}
	
	private void addWPS() throws UiObjectNotFoundException {
		LeUiObject addBtn1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("New"));
		LeUiObject addBtn2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("新建"));
		verify("没有添加按钮",addBtn1.exists()||addBtn2.exists());
		sleepInt(2);
		if(addBtn1.exists()){
			addBtn1.click();
		}
		if(addBtn2.exists()){
			addBtn2.click();
		}
		sleepInt(3);
		LeUiObject doc1 = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("cn.wps.moffice_eng:id/new_document_item_textview").text("Document"));
		LeUiObject doc2 = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("cn.wps.moffice_eng:id/new_document_item_textview").text("文档"));
		verify("没有Document选项",doc1.exists()||doc2.exists());
		if(doc1.exists()){
			doc1.click();
		}
		if(doc2.exists()){
			doc2.click();
		}
		sleepInt(10);
		LeUiObject Baidu = new LeUiObject(new UiSelector().className("android.widget.TextView").text("Baidu IME for Letv"));
		if(Baidu.exists()){
			LeUiObject doNotR = new LeUiObject(new UiSelector().className("android.widget.CheckBox").textContains("Don't remind"));
			if(!(doNotR.isChecked())){
				doNotR.click();
				sleepInt(1);
			}
			LeUiObject ok= new LeUiObject(new UiSelector().className("android.widget.Button").text("continue"));
			ok.click();
			sleepInt(1);
		}
		callShell("input text " + wpsContent);
		sleepInt(2);
		LeUiObject Done1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("Done"));
		LeUiObject Done2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("完成"));
		if(Done1.exists()){
			Done1.click();
		}
		if(Done2.exists()){
			Done2.click();
		}
		sleepInt(2);
		LeUiObject save = new LeUiObject(new UiSelector().className("android.widget.ImageView").resourceId("cn.wps.moffice_eng:id/image_save"));
		verify("保存按钮不存在",save.exists());
		save.click();
		sleepInt(2);
		LeUiObject saveToMyDoc1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("My Documents"));
		LeUiObject saveToMyDoc2= new LeUiObject(new UiSelector().className("android.widget.TextView").text("我的文档"));
		if(saveToMyDoc1.exists()){
			saveToMyDoc1.click();
		}
		if(saveToMyDoc2.exists()){
			saveToMyDoc2.click();
		}
		sleepInt(2);
		LeUiObject editTitle1 = new LeUiObject(new UiSelector().className("android.widget.EditText").resourceId("cn.wps.moffice_eng:id/save_new_name"));
		editTitle1.click();
		editTitle1.clearTextField();
		editTitle1.click();
		editTitle1.clearTextField();
		editTitle1.click();
		editTitle1.clearTextField();
		callShell("input text " + wpsContent);
		LeUiObject save2 = new LeUiObject(new UiSelector().className("android.widget.Button").resourceId("cn.wps.moffice_eng:id/btn_save"));
		save2.click();
		sleepInt(5);	
		press_back(1);
		sleepInt(2);
		LeUiObject recent = new LeUiObject(new UiSelector().className("android.widget.TextView").text("最近"));
		recent.click();
		sleepInt(2);
		LeUiObject item = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("cn.wps.moffice_eng:id/history_record_item_name"));
		sleepInt(4);
		verify("文档创建失败",(wpsContent+".doc").equals(item.getText()));
	}
	
	private void checkWPS() throws UiObjectNotFoundException {
		LeUiObject item = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("cn.wps.moffice_eng:id/history_record_item_name").textContains(wpsContent));
		item.click();
		sleepInt(2);
		LeUiObject close = new LeUiObject(new UiSelector().className("android.widget.ImageView").resourceId("cn.wps.moffice_eng:id/image_close"));
		close.click();
		sleepInt(2);
		
	}
	
	private void delWPS() throws UiObjectNotFoundException {
		LeUiObject open1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("open"));
		LeUiObject open2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("打开"));
		if(open1.exists()){
			open1.click();
		}
		if(open2.exists()){
			open2.click();
		}
		sleepInt(2);

		LeUiObject doc1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("DOC"));
		LeUiObject doc2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("DOC"));
		verify("没有Doc选项",doc1.exists()||doc2.exists());
		if(doc1.exists()){
			doc1.click();
		}
		if(doc2.exists()){
			doc2.click();
		}
		sleepInt(2);
		phone.swipe(710, 1237, 710, 1150, 50);
		sleepInt(2);
		LeUiObject item = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("cn.wps.moffice_eng:id/fb_filename_text").textContains(wpsContent));
		verify(item.exists());
		sleepInt(2);
		LeUiObject property = new LeUiObject(new UiSelector().className("android.widget.ImageView").resourceId("cn.wps.moffice_eng:id/fb_item_property_btn"));
		verify(property.exists());
		property.click();
		LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.TextView").text("删除文档"));
		delete.click();
		sleepInt(2);
		verify("文档删除失败",!(item.exists()));
	}
	
	
	@CaseName("启动WPS应用,退出WPS")
	public void testOpenWPS() throws UiObjectNotFoundException {
		
		addStep("step1:启动WPS");
		launchApp(AppName.WPS);
		sleepInt(6);
		LeUiObject Notice2 = new LeUiObject(new UiSelector().className("android.widget.Button").text("同意"));
		if(Notice2.exists()){
			LeUiObject doNotR2 = new LeUiObject(new UiSelector().className("android.widget.CheckBox").textContains("不再提醒"));
			if(!(doNotR2.isChecked())){
				doNotR2.click();
				sleepInt(1);
			}
			Notice2.click();
			sleepInt(1);
			
		}
		
		LeUiObject myOffice1 = new LeUiObject(new UiSelector().packageName("cn.wps.moffice_eng"));
		verify("没有成功进入WPS", myOffice1.exists());
		addStep("step2:home键后台，再次点击WPS");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.WPS);
		sleepInt(5);
		verify("没有成功进入WPS", myOffice1.exists());
		addStep("step3:home键后台调用多任务，多任务中点击wps");
		/*press_home(1);
		sleepInt(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));*/
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
		sleepInt(1);
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
		verify("没有成功进入WPS", myOffice1.exists());
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	@CaseName("查看、编辑、删除、新建文件")
	public void testAddDelWPS() throws UiObjectNotFoundException {
		addStep("step1:启动WPS");
		LeUiObject Notice2 = new LeUiObject(new UiSelector().className("android.widget.Button").text("同意"));
		launchApp(AppName.WPS);
		sleepInt(6);
		if(Notice2.exists()){
			LeUiObject doNotR2 = new LeUiObject(new UiSelector().className("android.widget.CheckBox").textContains("不再提醒"));
			if(!(doNotR2.isChecked())){
				doNotR2.click();
				sleepInt(1);
			}
			Notice2.click();
			sleepInt(1);
			
		}
		LeUiObject myOffice1 = new LeUiObject(new UiSelector().packageName("cn.wps.moffice_eng"));
		verify("没有成功进入WPS", myOffice1.exists());
		addWPS();
		checkWPS();
		delWPS();
		addStep("退出应用");
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

}

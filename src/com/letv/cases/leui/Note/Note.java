package com.letv.cases.leui.Note;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Note extends LetvTestCase {
	private String noteContent = "aaaaa";	
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
	private void addNote() throws UiObjectNotFoundException {
		//LeUiObject addBtn = new LeUiObject(new UiSelector().className("android.widget.ImageView").resourceId("android:id/icon").index(0));
		LeUiObject addBtn = new LeUiObject(new UiSelector().resourceId("com.letv.android.note:id/bottom_widget")
				.childSelector(new UiSelector().resourceId("android:id/icon").index(0)));
		verify("没有添加便签按钮",addBtn.exists());
		sleepInt(2);
		addBtn.click();
		sleepInt(2);
		callShell("input text " + noteContent);
		sleepInt(2);
		press_back(1);
		sleepInt(2);
		LeUiObject notecontent = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("com.letv.android.note:id/note_content"));
		LeUiObject noLogin = new LeUiObject(new UiSelector().className("android.widget.Button").text("暂不"));
		if(noLogin.exists()){
			noLogin.click();
			sleepInt(2);
		}
		verify("没有添加便签成功",noteContent.equals(notecontent.getText()));
	}
	
	private void delNote() throws UiObjectNotFoundException {
		LeUiObject notecontent = new LeUiObject(new UiSelector().resourceId("com.letv.android.note:id/note_content").text(noteContent));
		verify("note content 不存在！",notecontent.exists());
		notecontent.click();
		sleepInt(1);
		LeUiObject delBtn = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(4));
		verify("没有删除便签按钮",delBtn.exists());
		sleepInt(2);
		delBtn.click();
		LeUiObject confirmBtn = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm").textContains("删除"));
		sleepInt(2);
		verify("删除确认按钮不存在",confirmBtn.exists());
		confirmBtn.click();
		sleepInt(2);
		verify("没有删除便签成功",!notecontent.exists());
		
	}
	
	@CaseName("启动便签应用,退出便签应用")
	public void testOpenNote() throws UiObjectNotFoundException {
		addStep("step1:点击进入便签应用");
		launchApp(AppName.NOTE);
		sleepInt(5);
		LeUiObject allNotes1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("All notes"));
		LeUiObject allNotes2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("全部便签"));
		verify("没有成功便签应用",allNotes1.exists()||allNotes2.exists());
		sleepInt(2);
		addStep("step2:home键后台，再次点击便签应用");
		press_home(1);
		sleepInt(3);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		launchApp(AppName.NOTE);
		sleepInt(5);
		verify("没有成功便签应用",allNotes1.exists()||allNotes2.exists());
		addStep("step3:home键后台调用多任务，多任务中点击便签应用");
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
		verify("没有成功便签应用",allNotes1.exists()||allNotes2.exists());
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	@CaseName("创建删除便签")
	public void testAddDelNote() throws UiObjectNotFoundException {
		addStep("step1:点击进入便签应用");
		launchApp(AppName.NOTE);
		sleepInt(5);
		LeUiObject allNotes1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("All notes"));
		LeUiObject allNotes2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("全部便签"));
		verify("没有成功便签应用",allNotes1.exists()||allNotes2.exists());;
		addNote();
		delNote();
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));	
	}
}

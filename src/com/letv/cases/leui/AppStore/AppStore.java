package com.letv.cases.leui.AppStore;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;
import com.letv.uf.Utf7ImeHelper;

public class AppStore extends LetvTestCase {
	@CaseName("启动应用商店应用,应用下载,退出")
	public void testAppStore() throws UiObjectNotFoundException {
		connectWifi();
		launchApp(AppName.APPSTORE);
		sleepInt(5);
		addStep("step1:启动应用商店应用");
		LeUiObject welcome = new LeUiObject(new UiSelector().resourceId("com.letv.app.appstore:id/view_root"));
		LeUiObject into = new LeUiObject(new UiSelector().text("进入商店"));
		LeUiObject sw = new LeUiObject(new UiSelector().className("android.widget.TextView").text("软件"));
		for(int i=0;i<7;i++){
			if(welcome.exists()){
				phone.swipe((int)(phone.getDisplayWidth()*0.8),(int)(phone.getDisplayHeight()*0.8)
						, (int)(phone.getDisplayWidth()*0.2), (int)(phone.getDisplayHeight()*0.8), 10);
				sleepInt(1);
			}
			if(into.exists()){
				into.click();
				sleepInt(2);
			}else if(sw.exists()){
				break;
			}
		}
		sleepInt(4);
		for(int i=0;i<3;i++){
			if(sw.exists()){
				break;
			}
		}
		verify("没有软件项目",sw.exists());
		sw.click();
		sleepInt(2);
		LeUiObject search = new LeUiObject(new UiSelector()
		.resourceId("com.letv.app.appstore:id/ll_title_search"));
		verify("没有search项",search.exists());
		search.click();
		sleepInt(2);
		search.setText(Utf7ImeHelper.e("QQ音乐"));
		sleepInt(2);
		LeUiObject qqMusic = new LeUiObject(new UiSelector().textContains("QQ音乐"));
		verify("没有找到QQ音乐",qqMusic.exists());
		qqMusic.click();
		addStep("step2:下载软件");
		sleepInt(2);
		LeUiObject install = new LeUiObject(new UiSelector()
		.text("安装"));
		verify("没有安装按钮",install.exists());
		install.click();
		sleepInt(20);
		press_back(1);
		LeUiObject management= new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("管理"));
		verify("没有管理项目",management.exists());
		management.click();
		sleepInt(2);
		LeUiObject installMg= new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("安装管理"));
		verify("没有安装管理项目",installMg.exists());
		installMg.click();
		sleepInt(2);
		LeUiObject alreadyInstall= new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("已安装的应用"));
		verify("没有成功进入已安装应用中",alreadyInstall.exists());
		verify("qq音乐安装不成功",qqMusic.exists());
		sleepInt(2);
		addStep("step3:退出应用商城");
		press_back(3);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}

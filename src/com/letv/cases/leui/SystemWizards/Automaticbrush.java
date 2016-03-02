package com.letv.cases.leui.SystemWizards;

import android.R.integer;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Automaticbrush extends LetvTestCase {

	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		
	}
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@CaseName("系统刷机开机后向导设置")
	public void testSystemWizards() throws UiObjectNotFoundException, RemoteException {
		
		addStep("选择系统语言");
		LeUiObject chinese = new LeUiObject(new UiSelector()
		.className("com.letv.leui.widget.LeRadioButton").text("中文简体"));
		LeUiObject skip = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("跳过"));
		verify("未能进入到选择语言项",chinese.exists());
		chinese.click();
		sleepInt(3);
		LeUiObject next = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("继续"));
		verify("未能发现继续按钮",next.exists());
		next.click();
		sleepInt(4);
		
		addStep("选择默认输入法");
		/*LeUiObject InputMethod = new LeUiObject(new UiSelector()
		.className("android.widget.TextView")
		.resourceId("com.letv.android.setupwizard:id/title").text("Select Default InputMethod"));*/
		LeUiObject InputMethod = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("选择默认输入法"));
		verify("未能进入到选择输入法的界面",InputMethod.exists());
		verify("未能发现继续按钮",next.exists());
		next.click();
		sleepInt(4);
		//新改
		addStep("检测SIM卡");
		LeUiObject selectSIM = new LeUiObject(new UiSelector().className("android.widget.TextView")
		.text("检测SIM卡"));
		verify("未能发现检测SIM卡",selectSIM.exists());
		LeUiObject isInsert = new LeUiObject(new UiSelector().className("android.widget.TextView")
				.textContains("请插入您的SIM卡"));
		if(isInsert.exists()){
			skip.click();
		}else{
			verify("SIM卡已经插入，但是下一步不存在",next.exists());
			next.click();
		}
		//新改	
		addStep("选择wifi,跳过此项");
		LeUiObject selectwifi = new LeUiObject(new UiSelector().className("android.widget.TextView")
		.text("选择无线网络"));
		verify("未能发现选择wifi的页面",selectwifi.waitForExists(8000));
		verify("跳过按钮不存在",skip.exists());
		skip.click();
		sleepInt(4);
	
		addStep("使用条款,同意并且继续");
		LeUiObject UsageTerms = new LeUiObject(new UiSelector().textContains("使用条款"));
		verify("未能进入到使用条款界面",UsageTerms.exists());
		LeUiObject agreed = new LeUiObject(new UiSelector().text("同意并继续"));
		verify("未能找到同意并继续的按钮",agreed.exists());
		agreed.click();
		sleepInt(8);
		checkLetAccount();
		
		addStep("其他服务，选择默认");
		LeUiObject otherservices = new LeUiObject(new UiSelector().textContains("其他服务"));
		verify("其他服务页面不存在",otherservices.exists());
		verify("未能发现继续按钮",next.exists());
		next.click();
		checkLetAccount();
		sleepInt(4);
		
		
		addStep("开启精彩");
		LeUiObject start = new LeUiObject(new UiSelector().text("开启精彩"));
		verify("开启精彩不存在",start.exists());
		start.click();
		sleepInt(20);
		checkLetAccount();
		LeUiObject isSuccess = new LeUiObject(new UiSelector().packageName("com.android.launcher3"));
		
		for(int i=0;i<150;i++){
			if(isSuccess.exists()){
				break;
			}else{
				if (!phone.isScreenOn()) {
					phone.wakeUp();
				}
				sleepInt(1);
			}
		}
		verify("引导界面进入不成功",isSuccess.exists());
		sleepInt(5);
		
		addStep("允许手机安装第三方应用");
		launchApp(AppName.SETTING);
		sleepInt(2);
		UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().resourceId("android:id/decor_content_parent").packageName("com.android.settings")));	
		LeUiObject security = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("密码安全"));
		verify("没有进入到设置中",settingPanel.exists());
		settingPanel.setAsVerticalList();
		settingPanel.scrollBackward();
		settingPanel.scrollBackward();
		for (int i = 0; i < 10; i++) {
			if (!security.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}
		verify("没有找到密码安全", security.exists());
		security.click();
		sleepInt(2);
		LeUiObject source = new LeUiObject(new UiSelector().text("未知来源"));
		verify("未找到未知来源",source.exists());
		source.click();
		sleepInt(2);
		LeUiObject sure = new LeUiObject(new UiSelector()
		.className("android.widget.Button").text("确定"));
		verify("未找到确定",sure.exists());
		sure.click();
		sleepInt(2);
		
		addStep("step10:后台安装应用");
		LeUiObject backStage = new LeUiObject(new UiSelector().text("后台安装应用"));
		verify("未找到后台安装应用",backStage.exists());
		backStage.click();
		sleepInt(1);
		LeUiObject allow = new LeUiObject(new UiSelector().text("允许"));
		verify("未找到允许",allow.exists());
		allow.click();
		sleepInt(3);
		
		addStep("step11:辅助功能-锁屏防误触");
		press_back(1);
		verify("没有进入到设置中",settingPanel.exists());
		settingPanel.setAsVerticalList();
		settingPanel.scrollBackward();
		settingPanel.scrollBackward();
		LeUiObject auxiliary = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("辅助功能"));
		for (int i = 0; i < 5; i++) {
			if (!auxiliary.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}
		verify("没有找到辅助功能", auxiliary.exists());
		auxiliary.click();
		sleepInt(2);
		LeUiObject lockscreen = new LeUiObject(new UiSelector().text("锁屏防误触"));
		verify("未找到锁屏防触控",lockscreen.exists());
		lockscreen.click();
		sleepInt(2);
	}
	
	void checkLetAccount() throws UiObjectNotFoundException{
		addStep("跳过输入乐视账号");
		LeUiObject letvAccount = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").textContains("乐视帐号"));
		if(letvAccount.exists()){
			verify("未能到达输入乐视账号界面",letvAccount.exists());
			LeUiObject skipStep = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").textContains("跳过"));
			verify("未能找到跳过此步的按钮",skipStep.exists());
			skipStep.click();
			sleepInt(4);
			LeUiObject confirm = new LeUiObject(new UiSelector()
			.className("android.widget.Button").textContains("跳过"));
			verify("未能发现确定按钮",confirm.exists());
			confirm.click();
			sleep(6);
		}
	}
}

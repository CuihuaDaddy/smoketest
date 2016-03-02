package com.letv.cases.leui.IMEI;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class IMEI extends LetvTestCase {
	@CaseName("查看IMEI")
	public void testIMEI() throws UiObjectNotFoundException {
		
		launchApp(AppName.PHONE);
		sleepInt(1);
		addStep("step1:启动拨号器");
		LeUiObject dialApp = new LeUiObject(new UiSelector().className("android.widget.TabWidget")
				.index(1).childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
						.childSelector(new UiSelector().className("android.widget.TextView").resourceId("android:id/title"))));
		verify("dialApp not exists",dialApp.exists());
		dialApp.click();
		sleepInt(2);
		addStep("step2:输入*#06#");
		LeUiObject starBtn = new LeUiObject(new UiSelector()
		.resourceId("com.android.dialer:id/dialpad_key_star"));
		verify("*不存在",starBtn.exists());
		starBtn.click();
		sleepInt(1);
		LeUiObject poundBtn = new LeUiObject(new UiSelector()
		.resourceId("com.android.dialer:id/dialpad_key_pound"));
		verify("#不存在",poundBtn.exists());
		poundBtn.click();
		sleepInt(1);
		pressDialPad("06");
		poundBtn.click();
		sleepInt(3);
		addStep("step3:查看IMEI");
		LeUiObject IMEI = new LeUiObject(new UiSelector().resourceId(
				"android:id/message").className("android.widget.TextView").index(0));
		verify("没有成功显示IMEI号",IMEI.exists()); 
		addStep("step4:退出拨号盘");
		LeUiObject OK = new LeUiObject(new UiSelector().className("android.widget.Button").text("确定"));
		verify("OK不存在",OK.exists());
		OK.click();
		sleepInt(1);
		press_back(2);
		}
}

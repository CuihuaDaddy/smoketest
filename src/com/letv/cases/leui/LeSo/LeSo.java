package com.letv.cases.leui.LeSo;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class LeSo extends LetvTestCase {
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
	@CaseName("启动乐看搜索应用,搜索,退出")
	public void testLeSo() throws UiObjectNotFoundException {
		LeUiObject leso = new LeUiObject(new UiSelector().resourceId("com.letv.leadlesoclient:id/search_widget"));
		for (int i = 0; i < 20; i++) {
			if (!leso.exists()) {
				phone.swipe(1000, 500, 50, 500, 50);
			} else {
				break;
			}
		}
		verify("没有找到乐搜",leso.exists());
		leso.click();
		sleepInt(2);
		LeUiObject lesoInput = new LeUiObject(new UiSelector().resourceId("com.letv.leadlesoclient:id/input_box"));
		verify("没有进入乐搜应用",lesoInput.exists());
		addStep("启动乐搜应用");
		lesoInput.setText("hero");
		sleepInt(2);
		LeUiObject lesoResult = new LeUiObject(new UiSelector().resourceId("com.letv.leadlesoclient:id/leso_suggest_key_name").textContains("Hero"));
		verify("没有搜到跟hero相关的信息",lesoResult.exists());
		addStep("搜索Hero");
		addStep("退出下载管理器");
		press_back(5);
		press_home(1);
	}
}

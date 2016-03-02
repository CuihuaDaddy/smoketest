package com.letv.cases.leui.Settings;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class InputMethod extends LetvTestCase {
	
	@CaseName("Mobile-40899:输入法列表")
	public void testCheckInputAndLanguage() throws UiObjectNotFoundException {
		addStep("1.进入设置");
		launchApp(AppName.SETTING);
		LeUiObject languageAndInput = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("语言和输入法"));
		addStep("2.进入语言和输入法");
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(languageAndInput.exists()){
				break;
			}
		}
		verify("语言和输入法不存在",languageAndInput.exists());
		languageAndInput.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("3.观察输入法列表");
		LeUiObject baiduInput = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("百度输入法乐视版"));
		verify("百度输入法不存在",baiduInput.exists());
		LeUiObject currentInputMethod = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("当前输入法"));
		verify("当前输入法不存在",currentInputMethod.exists());
		currentInputMethod.click();
		LeUiObject changeInputMethod = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("更改输入法"));
		LeUiObject chooseInputMethod = new LeUiObject(new UiSelector().className(
				"android.widget.Button").textContains("选择输入法"));
		verify("更改输入法和选择输入法不存在",changeInputMethod.exists()&&chooseInputMethod.exists());
	}
	
}

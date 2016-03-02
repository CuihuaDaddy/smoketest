package com.letv.cases.leui.Settings;

import java.util.ArrayList;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Language extends LetvTestCase {
	
	@CaseName("Mobile-40894:语言和输入法界面显示")
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
		addStep("3.观察语言和输入法界面显示");
		ArrayList<String> list = new ArrayList<String>();
		list.add("语言");
		list.add("键盘和输入法");
		list.add("当前输入法");
		list.add("百度输入法乐视版");
		list.add("语音");
		list.add("文字转语音 (TTS) 输出");
		list.add("鼠标/触控板");
		list.add("指针速度");
		for(int i=0;i<list.size();i++){
			String checkPointName=list.get(i).toString();
			LeUiObject checkPoint = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text(checkPointName));
			verify(checkPointName+"不存在",checkPoint.exists());
		}
		LeUiObject seekBar = new LeUiObject(new UiSelector().className(
				"android.widget.SeekBar"));
		verify("指针速度进度条不存在",seekBar.exists());
	}
	
	@CaseName("Mobile-40895:设置语言为简体中文")
	public void testChangeLanguageChinese() throws UiObjectNotFoundException {
		selectLanguage("中文 (简体)");
		addStep("进入系统其他界面查看系统语言");
		LeUiObject checkPoint1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("文字转语音 (TTS) 输出"));
		LeUiObject checkPoint2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("中文 (简体)"));
		verify("未能选择语言成功",checkPoint1.exists()&&checkPoint2.exists());
	}
	@CaseName("Mobile-40896:设置语言为繁体中文")
	public void testChangeOldeChinese() throws UiObjectNotFoundException {
		selectLanguage("中文 (繁體)");
		addStep("进入系统其他界面查看系统语言");
		LeUiObject checkPoint1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("文字轉語音輸出"));
		LeUiObject checkPoint2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("中文 (繁體)"));
		verify("未能选择语言成功",checkPoint1.exists()&&checkPoint2.exists());
	}
	@CaseName("Mobile-40897:设置语言为英文")
	public void testChangeLanguageEnglish() throws UiObjectNotFoundException {
		selectLanguage("English");
		addStep("进入系统其他界面查看系统语言");
		LeUiObject checkPoint1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Text-to-speech output"));
		LeUiObject checkPoint2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("English"));
		verify("未能选择语言成功",checkPoint1.exists()&&checkPoint2.exists());
	}
	@CaseName("Mobile-40898:设置语言为中文（香港）")
	public void testChangeLanguageHongKong() throws UiObjectNotFoundException {
		selectLanguage("中文 (香港)");
		addStep("进入系统其他界面查看系统语言");
		LeUiObject checkPoint1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("文字轉語音輸出"));
		LeUiObject checkPoint2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("中文 (香港)"));
		verify("未能选择语言成功",checkPoint1.exists()&&checkPoint2.exists());
	}
	public void selectLanguage(String lang) throws UiObjectNotFoundException{
		addStep("1.进入设置");
		launchAppByPackage("com.android.settings/.Settings");
		LeUiObject languageAndInput1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("语言和输入法"));
		LeUiObject languageAndInput2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("語言與輸入設定"));
		LeUiObject languageAndInput3 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("語言和輸入設定"));
		LeUiObject languageAndInput4 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("Language & input"));
		addStep("2.进入语言和输入法");
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(languageAndInput1.exists()||languageAndInput2.exists()||languageAndInput3.exists()||
					languageAndInput4.exists()){
				break;
			}
		}
		verify("语言和输入法不存在",languageAndInput1.exists()||languageAndInput2.exists()||languageAndInput3.exists()||languageAndInput4.exists());
		if(languageAndInput1.exists()){
			languageAndInput1.clickAndWaitForNewWindow();
		}else if(languageAndInput2.exists()){
			languageAndInput2.clickAndWaitForNewWindow();
		}else if (languageAndInput3.exists()) {
			languageAndInput3.click();
		}else{
			languageAndInput4.clickAndWaitForNewWindow();
		}
		sleepInt(1);
		addStep("3.点击语言，选择"+lang);
		LeUiObject language = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
						.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0))));
		verify("语言不存在",language.exists());
		language.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject chooseLanguage = new LeUiObject(new UiSelector().resourceId("android:id/locale").text(lang));
		verify("要选的"+lang+"语言不存在",chooseLanguage.exists());
		chooseLanguage.clickAndWaitForNewWindow();
		sleepInt(1);
		
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		
		LeUiObject checkIsSimpleChinese = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("语言"));
		if(!checkIsSimpleChinese.exists()){
			selectLanguage("中文 (简体)");
			LeUiObject checkPoint = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text("中文 (简体)"));
			verify("未能切换中文 (简体)语言成功",checkPoint.exists());
		}
		super.tearDown();
	}
}

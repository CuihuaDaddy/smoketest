package com.letv.cases.leui.Settings;

import java.util.ArrayList;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;
import com.letv.uf.Utf7ImeHelper;

public class AboutPhone extends LetvTestCase {
	
	@CaseName("Mobile-23154:关于手机更改名字")
	public void testAboutPhoneChangeName() throws UiObjectNotFoundException {
		addStep("进入关于手机");
		launchApp(AppName.SETTING);
		LeUiObject aboutPhone = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("关于手机"));
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
		}
		verify("关于手机不存在",aboutPhone.exists());
		aboutPhone.clickAndWaitForNewWindow();
		addStep("2.更改手机名称（名称同时包含数字，英文，符号和中文）");
		LeUiObject phoneName = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("手机名称"));
		verify("手机名称不存在",phoneName.exists());
		phoneName.clickAndWaitForNewWindow();
		LeUiObject inputName = new LeUiObject(new UiSelector().resourceId(
				"com.android.settings:id/phone_name_edt_entry"));
		verify("输入手机名字的区域不存在",inputName.exists());
		inputName.clearTextField();
		inputName.clearTextField();
		inputName.clearTextField();
		inputName.setText(Utf7ImeHelper.e("le_phone123"));
		LeUiObject confirm = new LeUiObject(new UiSelector().resourceId(
				"com.android.settings:id/action_confirm").text("确定"));
		verify("确定按钮不存在",confirm.exists());
		confirm.clickAndWaitForNewWindow();
		LeUiObject nameVerify = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("le_phone123"));
		verify("手机名字修改不成功",nameVerify.exists());
	}
	
	@CaseName("Mobile-19195:关于手机列表界面")
	public void testCheckAboutPhone() throws UiObjectNotFoundException {
		addStep("进入关于手机");
		launchApp(AppName.SETTING);
		LeUiObject aboutPhone = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("关于手机"));
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
		}
		verify("关于手机不存在",aboutPhone.exists());
		aboutPhone.clickAndWaitForNewWindow();
		addStep("2.更改手机名称（名称同时包含数字，英文，符号和中文）");
		ArrayList<String> list = new ArrayList<String>();
		list.add("手机名称");
		list.add("状态信息");
		list.add("型号");
		list.add("Android版本");
		list.add("EUI 版本号");
		list.add("处理器");
		list.add("内核版本");
		list.add("版本号");
		list.add("用户体验计划");
		list.add("法律信息");
		
		for(int i=0;i<list.size();i++){
			String checkPointName=list.get(i).toString();
			LeUiObject checkPoint = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text(checkPointName));
			verify(checkPointName+"不存在",checkPoint.exists());
		}
		
	}
	
	@CaseName("Mobile-19194:About phone-检查法律信息")
	public void testCheckLegalInfo() throws UiObjectNotFoundException {
		addStep("进入关于手机");
		launchApp(AppName.SETTING);
		LeUiObject aboutPhone = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("关于手机"));
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
		}
		verify("关于手机不存在",aboutPhone.exists());
		aboutPhone.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("选择'Legal information''  ");
		LeUiObject legalInfo = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("法律信息"));
		verify("法律信息不存在",legalInfo.exists());
		legalInfo.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("进入Open source licenses");
		LeUiObject openSource = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("开放源代码许可"));
		verify("开放源代码许可不存在",openSource.exists());
		openSource.clickAndWaitForNewWindow();
		LeUiObject openSourceEnter = new LeUiObject(new UiSelector().className(
				"android.webkit.WebView").description("网页视图"));
		for (int i = 0; i < 10; i++) {
			if(openSourceEnter.exists()){
				break;
			}
			sleepInt(1);
		}
		verify("未能进入开放源代码许可",openSourceEnter.exists());
	}
	
	@CaseName("Mobile-19200:IMEI信息")
	public void testCheckSIMInfo() throws UiObjectNotFoundException {
		addStep("进入关于手机");
		launchApp(AppName.SETTING);
		LeUiObject aboutPhone = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("关于手机"));
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
		}
		verify("关于手机不存在",aboutPhone.exists());
		aboutPhone.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("点击状态信息");
		LeUiObject statusInfo = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("状态信息"));
		verify("状态信息不存在",statusInfo.exists());
		statusInfo.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject SIMCardStatus = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("SIM卡状态"));
		verify("SIM卡状态不存在",SIMCardStatus.exists());
		SIMCardStatus.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject CMCC01 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("中国移动 01"));
		LeUiObject CMCC02 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("中国移动 02"));
		verify("两张卡不存在",CMCC01.exists()&&CMCC02.exists());
		ArrayList<String> list = new ArrayList<String>();
		list.add("网络");
		list.add("信号强度");
		list.add("移动网络类型");
		list.add("运营商信息");
		list.add("服务状态");
		list.add("漫游");
		list.add("移动网络状态");
		list.add("本机号码");
		list.add("IMEI");
		list.add("IMEI SV");
		for(int i=0;i<list.size();i++){
			String checkPointName=list.get(i).toString();
			LeUiObject checkPoint = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text(checkPointName));
			verify(checkPointName+"不存在",checkPoint.exists());
		}
		CMCC02.click();
		sleepInt(2);
		for(int i=0;i<10;i++){
			String checkPointName=list.get(i).toString();
			LeUiObject checkPoint = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text(checkPointName));
			verify(checkPointName+"不存在",checkPoint.exists());
		}
	}
	
	@CaseName("Mobile-19193:About phone-检查状态信息")
	public void testCheckPhoneStatus() throws UiObjectNotFoundException {
		addStep("进入关于手机");
		launchApp(AppName.SETTING);
		LeUiObject aboutPhone = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("关于手机"));
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
		}
		verify("关于手机不存在",aboutPhone.exists());
		aboutPhone.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("点击状态信息并检查显示是否正确");
		LeUiObject statusInfo = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("状态信息"));
		verify("状态信息不存在",statusInfo.exists());
		statusInfo.clickAndWaitForNewWindow();
		sleepInt(1);
		ArrayList<String> list = new ArrayList<String>();
		list.add("SIM卡状态");
		list.add("电池状态");
		list.add("电池电量");
		list.add("IP地址");
		list.add("蓝牙地址");
		list.add("WLAN MAC 地址");
		list.add("序列号");
		list.add("已开机时间");
		for(int i=0;i<list.size();i++){
			String checkPointName=list.get(i).toString();
			LeUiObject checkPoint = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text(checkPointName));
			verify(checkPointName+"不存在",checkPoint.exists());
		}
	}
}

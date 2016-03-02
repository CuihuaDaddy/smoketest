package com.letv.cases.leui.Settings;

import java.util.ArrayList;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;
import com.letv.uf.Utf7ImeHelper;

public class DateAndTime extends LetvTestCase {
	
	@CaseName("Mobile-40924:时区搜索")
	public void testTimeAreaSearch() throws UiObjectNotFoundException {
		addStep("1.进入设置");
		launchApp(AppName.SETTING);
		LeUiObject dateAndTime = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("日期和时间"));
		addStep("2.进入日期和时间");
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(dateAndTime.exists()){
				break;
			}
		}
		verify("日期和时间不存在",dateAndTime.exists());
		dateAndTime.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("3.点击设置时区");
		LeUiObject autoConfirm = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
						.childSelector(new UiSelector().className("com.letv.leui.widget.LeSwitch"))));
		verify("自动确定的开关不存在",autoConfirm.exists());
		if(autoConfirm.isChecked()){
			autoConfirm.click();
			sleepInt(2);
			verify("自动确定开关被关掉", !autoConfirm.isChecked());
		}
		addStep("4.搜索框输入文字，数字，字母或字符等");
		LeUiObject setTimeArea = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("设置时区"));
		verify("设置时区不存在",setTimeArea.exists());
		setTimeArea.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject search = new LeUiObject(new UiSelector().description("搜索时区"));
		verify("搜索市区不存在",search.exists());
		search.click();
		sleepInt(2);
		LeUiObject searchInput = new LeUiObject(new UiSelector().text("   搜索时区"));
		verify("搜索时区输入的地方不存在",searchInput.exists());
		searchInput.setText(Utf7ImeHelper.e("中国标准时间(北京)"));
		sleepInt(1);
		LeUiObject beijing = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("中国标准时间(北京)"));
		verify("北京标准时间不存在",beijing.exists());
		beijing.clickAndWaitForNewWindow();
		sleepInt(1);
		press_back(1);
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("GMT+08:00 中国标准时间"));
		verify("北京标准时间选择不成功",confirm.exists());
	}
	
	@CaseName("Mobile-40923:设置时区")
	public void testSetTimeArea() throws UiObjectNotFoundException {
		addStep("1.进入设置");
		launchApp(AppName.SETTING);
		LeUiObject dateAndTime = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("日期和时间"));
		addStep("2.进入日期和时间");
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(dateAndTime.exists()){
				break;
			}
		}
		verify("日期和时间不存在",dateAndTime.exists());
		dateAndTime.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("3.点击设置时区");
		LeUiObject autoConfirm = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
						.childSelector(new UiSelector().className("com.letv.leui.widget.LeSwitch"))));
		verify("自动确定的开关不存在",autoConfirm.exists());
		if(autoConfirm.isChecked()){
			autoConfirm.click();
			sleepInt(2);
			verify("自动确定开关被关掉", !autoConfirm.isChecked());
		}
		addStep("4.选择任意城市");
		LeUiObject setTimeArea = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("设置时区"));
		verify("设置时区不存在",setTimeArea.exists());
		setTimeArea.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject beijing = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("中国标准时间(北京)"));
		verify("北京标准时间不存在",beijing.exists());
		beijing.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("GMT+08:00 中国标准时间"));
		verify("北京标准时间选择不成功",confirm.exists());
	}
	
	@CaseName(" Mobile-40917:日期和时间界面显示")
	public void testOpenClose24Format() throws UiObjectNotFoundException {
		addStep("1.进入设置");
		launchApp(AppName.SETTING);
		LeUiObject dateAndTime = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("日期和时间"));
		addStep("2.进入日期和时间");
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(dateAndTime.exists()){
				break;
			}
		}
		verify("日期和时间不存在",dateAndTime.exists());
		dateAndTime.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("3.开启24小时制");
		LeUiObject time_switch = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
						.childSelector(new UiSelector().className("com.letv.leui.widget.LeSwitch"))));
		verify("24小时制的开关不存在",time_switch.exists());
		LeUiObject time24 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("13:00"));
		LeUiObject time12 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("下午1:00"));
		if(time_switch.isChecked()){
			verify("24小时制不存在", time24.exists());
			time_switch.click();
			sleepInt(2);
			
		}
		verify("12小时制不存在",(!time_switch.isChecked())&&time12.exists());
		time_switch.click();
		sleepInt(2);
		verify("24小时制不存在",time24.exists()&&time_switch.isChecked());
	}
	
	@CaseName("Mobile-40920:自动获取网络时区与时间-数据")
	public void testAutoGetTimeByWifi() throws UiObjectNotFoundException {
		addStep("打开wifi");
		connectWifi();
		autoGetTime();
		addStep("关闭wifi");
		closeWifi();
	}
	
	@CaseName("Mobile-40920:自动获取网络时区与时间-数据")
	public void testAutoGetTimeByUserdate() throws UiObjectNotFoundException {
		addStep("关闭wifi");
		closeWifi();
		autoGetTime();
	}
	
	@CaseName(" Mobile-40917:日期和时间界面显示")
	public void testDateShow() throws UiObjectNotFoundException {
		addStep("1.进入设置");
		launchApp(AppName.SETTING);
		LeUiObject dateAndTime = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("日期和时间"));
		addStep("2.进入日期和时间");
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(dateAndTime.exists()){
				break;
			}
		}
		verify("日期和时间不存在",dateAndTime.exists());
		dateAndTime.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("3.观察日期和时间界面显示");
		ArrayList<String> list = new ArrayList<String>();
		list.add("24 小时制");
		list.add("自动确定");
		list.add("设置时区");
		list.add("设置时间");
		list.add("日期格式");
		list.add("GMT+08:00 中国标准时间");
		list.add("使用网络上的时区和时间");
		for(int i=0;i<list.size();i++){
			String checkPointName=list.get(i).toString();
			LeUiObject checkPoint = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text(checkPointName));
			verify(checkPointName+"不存在",checkPoint.exists());
		}
		LeUiObject time_switch = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
						.childSelector(new UiSelector().className("com.letv.leui.widget.LeSwitch"))));
		LeUiObject auto_switch = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
						.childSelector(new UiSelector().className("com.letv.leui.widget.LeSwitch"))));
		verify("24小时制开关或者自动确定开关不存在",time_switch.exists()&&auto_switch.exists());
		
	}
	
	@CaseName(" Mobile-40933:设置日期格式为周-月-日-年")
	public void testMouthDayYear() throws UiObjectNotFoundException {
		dateFormat("12-31-1970","月-日-年");
	}
	
	@CaseName(" Mobile-40934:设置日期格式为周-日-月-年")
	public void testDayMouthYear() throws UiObjectNotFoundException {
		dateFormat("31-12-1970","日-月-年");
	}
	
	@CaseName("Mobile-40935:设置日期格式为年-月-日-周")
	public void testTearMouthDay() throws UiObjectNotFoundException {
		dateFormat("1970-12-31","年-月-日");
	}
	
	public void dateFormat(String format,String info) throws UiObjectNotFoundException{
		addStep("1.进入设置");
		launchApp(AppName.SETTING);
		LeUiObject dateAndTime = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("日期和时间"));
		addStep("2.进入日期和时间");
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(dateAndTime.exists()){
				break;
			}
		}
		addStep("3.点击日期格式右侧下拉按钮,选择"+info);
		verify("日期和时间不存在",dateAndTime.exists());
		dateAndTime.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject chooseDate = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("日期格式"));
		verify("日期格式不存在",chooseDate.exists());
		chooseDate.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject dayMouthYear = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text(format));
		verify("显示格式"+info+"不存在",dayMouthYear.exists());
		dayMouthYear.clickAndWaitForNewWindow();
		sleepInt(1);
		LeUiObject dateFormat = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("日期格式"));
		verify("显示格式月日年的格式未选择成功",dateFormat.exists());
	}
	
	public void autoGetTime() throws UiObjectNotFoundException{
		addStep("1.进入设置");
		launchApp(AppName.SETTING);
		LeUiObject dateAndTime = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("日期和时间"));
		addStep("2.进入日期和时间");
		for (int i = 0; i < 5; i++) {
			swipe_screen(500, 1500, 500, 1000, 30);
			if(dateAndTime.exists()){
				break;
			}
		}
		verify("日期和时间不存在",dateAndTime.exists());
		dateAndTime.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("3.开启自动确定");
		LeUiObject autoConfirm = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
						.childSelector(new UiSelector().className("com.letv.leui.widget.LeSwitch"))));
		verify("自动确定的开关不存在",autoConfirm.exists());
		if(autoConfirm.isChecked()){
			autoConfirm.click();
			verify("自动确定不存在", !autoConfirm.isChecked());
			sleepInt(2);
			autoConfirm.click();
			sleepInt(2);
			verify("自动确定存在", autoConfirm.isChecked());
		}
	}
}

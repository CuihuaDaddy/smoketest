package com.letv.cases.leui.Calculator;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Calculator extends LetvTestCase {


	@CaseName("启动计算器应用,计算器操作正常,退出计算器")
	public void testCalculator() throws UiObjectNotFoundException {
		addStep("Step1:启动计算器应用");
		launchApp(AppName.CALCULATOR);
		sleepInt(4);
		//加减乘除号
		LeUiObject plus = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/plus"));
		verify("Can't find plus icon.", plus.exists());
		LeUiObject minus = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/minus"));
		verify("Can't find minus icon.", minus.exists());
		LeUiObject multiply  = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/mul"));
		verify("Can't find multiply  icon.", multiply .exists());
		LeUiObject divide = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/div"));
		verify("Can't find divide icon.", divide.exists());
		//9 和1
		LeUiObject digit1 = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/digit1").text("1"));
		verify("Can't find one digit1.", digit1.exists());
		LeUiObject digit9 = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/digit9").text("9"));
		verify("Can't find digit9 icon.", digit9.exists());
		//点号
		LeUiObject dot = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/dot"));
		verify("Can't find dot icon.", dot.exists());
		//等于号
		LeUiObject equal = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/equal"));
		verify("Can't find equal icon.", equal.exists());
		
		//结果
		LeUiObject result = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/formula").className("android.widget.EditText"));
		verify("Can't find result.", result.exists());
		
		//清除
		LeUiObject clear = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/clear"));
		verify("Can't find clear.", clear.exists());
		/*String path = getPath(); 
		path= path + "/"+ Thread.currentThread().getStackTrace()[2].getMethodName()+"/target.png";
        System.out.println("*********************"+Thread.currentThread().getStackTrace()[2].getMethodName());
		System.out.println("******************"+getPath()+"target.png");
		verifyByImg(path, 300, 1500, 1000, 2000, 1);*/
		addStep("Step2:计算器操作正常");
		
		//1.11111+9.99999=11.1111
		clear.click();
		sleepInt(1);
		digit1.click();
		sleepInt(1);
		dot.click();
		sleepInt(1);
		for (int i = 0; i < 6; i++) {
			digit1.click();
			sleepInt(1);
		}
		plus.click();
		sleepInt(1);
		digit9.click();
		sleepInt(1);
		dot.click();
		sleepInt(1);
		for (int i = 0; i < 6; i++) {
			digit9.click();
			sleepInt(1);
		}
		
		equal.click();
		sleepInt(2);
		verify("加法结果计算错误！","11.11111".equals(result.getText()));
		
		//9.99999-1.11111=8.888888
		clear.click();
		sleepInt(1);
		digit9.click();
		sleepInt(1);
		dot.click();
		sleepInt(1);
		for (int i = 0; i < 5; i++) {
			digit9.click();
			sleepInt(1);
		}
		minus.click();
		sleepInt(1);
		digit1.click();
		sleepInt(1);
		dot.click();
		sleepInt(1);
		for (int i = 0; i < 5; i++) {
			digit1.click();
			sleepInt(1);
		}
		equal.click();
		sleepInt(2);
		verify("减法结果计算错误！","8.88888".equals(result.getText()));
		
		//1.11111X9.99999=11.1110888889
		clear.click();
		sleepInt(1);
		digit1.click();
		sleepInt(1);
		dot.click();
		sleepInt(1);
		for (int i = 0; i < 5; i++) {
			digit1.click();
			sleepInt(1);
		}
		multiply.click();
		sleepInt(1);
		digit9.click();
		sleepInt(1);
		dot.click();
		sleepInt(1);
		for (int i = 0; i < 5; i++) {
			digit9.click();
			sleepInt(1);
		}
		equal.click();
		sleepInt(2);
		verify("乘法结果计算错误！","11.111088889".equals(result.getText()));
		
		//9.99999/1.11111=9
		clear.click();
		sleepInt(1);
		digit9.click();
		sleepInt(1);
		dot.click();
		sleepInt(1);
		for (int i = 0; i < 5; i++) {
			digit9.click();
			sleepInt(1);
		}
		divide.click();
		sleepInt(1);
		digit1.click();
		sleepInt(1);
		dot.click();
		sleepInt(1);
		for (int i = 0; i < 5; i++) {
			digit1.click();
			sleepInt(1);
		}
		equal.click();
		sleepInt(2);
		verify("除法结果计算错误！","9".equals(result.getText()));
		
		addStep("Step3:计算器操作正常");
		clear.click();
		sleepInt(1);
		press_back(2);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	public String getPath(){
		String value = this.getClass().getName();
        // 注意要加\\,要不出不来,yeah
        String[] names = value.split("\\.");
        String path = "/sdcard/smoke_pic/";
        for (int i = 0; i < names.length; i++) {
        	if(i==names.length-2){
        		path += names[i];
        	}else if(i==names.length-1){
        		path += "/"+names[i];
        	}
        	else {
        		path += names[i]+".";
			}
        }
        
        return path;
	}
}

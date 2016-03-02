package com.letv.cases.leui.WIFI;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;

public class Wifi extends LetvTestCase {


	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();		
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		//closeWifi();
		super.tearDown();
	}


	public void addAP() throws UiObjectNotFoundException {
		
		LeUiObject add_network = new LeUiObject(new UiSelector()
				.className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.ImageView").index(0))));
		for (int i=0;i<300;i++){
			if (add_network.exists() && add_network.isEnabled())
			{
				try
				{
					if (add_network.isClickable())
					{
						add_network.click();
					}
					else
					{
						int dx = add_network.getBounds().centerX();
						int dy = add_network.getBounds().centerY();
						phone.click(dx, dy);
					}
					sleepInt(3);
				}
				catch(UiObjectNotFoundException e)
				{
					e.printStackTrace();
				}
				
				break;
			}
			sleepInt(1);			
		}
		
		/*
		LeUiObject add1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"android:id/title").text("Add network"));
		LeUiObject add2 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"android:id/title").text("添加网络"));
		LeUiObject add = new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().className("android.widget.RelativeLayout").index(1)));
		for (int i=0;i<300;i++){
			if (add2.exists()){
				break;
			}
			sleepInt(1);			
		}
		verify("添加网络按钮不存在",add1.exists()||add2.exists()||add.exists());	
		add.click();
		sleepInt(3);
		*/
		
		LeUiObject APname2 = new LeUiObject(
				new UiSelector().className("android.widget.EditText")
				.resourceId("android:id/edit")
				.text("网络名称"));
		verify("AP名称不存在",APname2.exists());
		APname2.click();
		sleepInt(2);
		APname2.setText(getStrParams("WifiAP"));
		sleepInt(2);
//		LeUiObject APsecurity1 = new LeUiObject(
//				new UiSelector()
//						.className("android.widget.TextView").resourceId("android:id/title").text("Security"));
//		LeUiObject APsecurity2 = new LeUiObject(
//				new UiSelector()
//						.className("android.widget.TextView").resourceId("android:id/title").text("安全性"));
		LeUiObject APsecurity = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
								.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
										.childSelector(new UiSelector().resourceId("android:id/title").className("android.widget.TextView").index(0))))));
		//verify("安全性不存在",(APsecurity1.exists()||APsecurity2.exists()||APsecurity.exists()));
		//verify("安全性不存在",APsecurity.exists());
		APsecurity.click();
		sleepInt(2);
		LeUiObject WPA2 = new LeUiObject(
				new UiSelector()
						.className("android.widget.TextView").resourceId("android:id/le_bottomsheet_text").text("WPA/WPA2 PSK"));
		verify("WPA/WPA2 PSK不存在",WPA2.exists());
		WPA2.click();
		sleepInt(2);
		LeUiObject WPA = new LeUiObject(
				new UiSelector()
				.className("android.widget.TextView").text("WPA/WPA2 PSK").index(1));
		verify("WPA/WPA2 PSK选择不成功", WPA.exists());
		sleepInt(2);
		LeUiObject APpw= new LeUiObject(
				new UiSelector()
						.className("android.widget.TextView").resourceId("android:id/title").text("密码"));
		verify("密码不存在",APpw.exists());
		APpw.click();
		sleepInt(2);
		APpw.setText(getStrParams("WifiPWD"));
		LeUiObject OK3 = new LeUiObject(
				new UiSelector().className("android.view.View").index(0)
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)));
		OK3.click();
		sleepInt(6);
	}

	@CaseName("wifi")
	public void testWifiConnection() throws UiObjectNotFoundException {
		closeWifi();
		press_back(5);
		addStep("1.Open wifi");
		openWifi();
		addStep("2.Connect wifi");
		sleepInt(5);
		addStep("3.Connect to a AP");
//		addAP();
//		sleepInt(10);
		LeUiObject connectInfo1 = new LeUiObject(new UiSelector().resourceId(
				"android:id/summary").text("Connected"));
		LeUiObject connectInfo2 = new LeUiObject(new UiSelector().resourceId(
				"android:id/summary").text("已连接"));

		verify("Can't connect to wifi.", connectInfo1.exists()||connectInfo2.exists());
		press_back(5);
		//closeWifi();
	}
}

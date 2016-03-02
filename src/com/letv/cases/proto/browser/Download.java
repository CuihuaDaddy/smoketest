package com.letv.cases.proto.browser;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCase;
public class Download extends LetvTestCase {

	private String dServer = "http://10.57.9.203";

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		// clear the download list
		testDeleteAll();
	}
	// get download count
	private int getDownloadCount() throws UiObjectNotFoundException {
		int count = 0;
//		launchAppByPackage(IntentConstants.downloadlist);
		launchApp("下载");
		sleep(2000);
		LeUiObject listView = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.android.documentsui:id/list"));
		verify("Can't find list view.", listView.exists());
		count = listView.getChildCount();
		press_back(5);
		return count;
	}

	// play download audio or video
	private void playFromList() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.proto_filemanager);
		sleep(3000);
		LeUiObject download = new LeUiObject(new UiSelector().resourceId(
				"com.rhmsoft.fm:id/name").text("Download"));
		verify("Can't find download folder.", download.exists());
		download.click();
		LeUiObject file = new LeUiObject(new UiSelector()
				.className("android.widget.ListView")
				.resourceId("com.rhmsoft.fm:id/entryList").childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)));
		verify("Can't find download file.", file.exists());
		file.click();
		sleepInt(10);
		press_back(5);
	}

	@CaseName("下载一个正常文件")
	public void testDownloadTxt() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		addStep("清除浏览器缓存，历史记录和cookie数据");
		for (int i = 0; i < getIntParams("Loop"); i++) {
			launchAppByPackage(IntentConstants.proto_browser);
			addStep("在超链接栏输入一个有下载资源的超链接地址");
			// url input edit text
			LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.browser:id/url"));
			urlEdit.click();
			callShell("input text " + dServer + "/txt.php");
			sleepInt(1);
			addStep("下载正常文本文件完成");
			phone.pressEnter();
			sleepInt(5);
			press_back(4);
			addStep("确认下载文件成功");
			int count = getDownloadCount();
			if (count < i + 1) {
				fail("Can't download txt file, TestCaseLoop===" + i);
			}
		}
		addStep("删除下载文件");
		testDeleteAll();
		addStep("退出浏览器");
		press_back(5);
	}

	@CaseName("下载音频文件并打开")
	public void testDownloadAudio() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		addStep("清除浏览器缓存，历史记录和cookie数据");
		for (int i = 0; i < getIntParams("Loop"); i++) {
			launchAppByPackage(IntentConstants.proto_browser);
			addStep("在超链接栏输入一个有下载资源的超链接地址");
			// url input edit text
			LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.browser:id/url"));
			urlEdit.click();
			callShell("input text " + dServer + "/music.php");
			sleepInt(1);
			addStep("下载音频文件并确认下载完成");
			phone.pressEnter();
			sleepInt(5);
			press_back(4);
			int count = getDownloadCount();
			if (count < i + 1) {
				fail("Can't download muisc, TestCaseLoop===" + i);
			}
			addStep("播放此音频文件，正常播放后停止播放");
//			playFromList();
			launchApp("下载");
			sleepInt(1);
			LeUiObject file = new LeUiObject(new UiSelector().className(
					"android.widget.ListView").childSelector(new UiSelector().className(
						"android.widget.LinearLayout").index(0)));
			int dx = file.getBounds().centerX();
			int dy = file.getBounds().centerY();
			phone.click(dx, dy);
			sleepInt(10);
			press_back(5);
		}
		addStep("删除下载文件");
		testDeleteAll();
		addStep("退出浏览器");
		press_back(5);
	}

	@CaseName("下载图片")
	public void testDownloadPicture() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		addStep("清除浏览器缓存，历史记录和cookie数据");
		for (int i = 0; i < getIntParams("Loop"); i++) {
			launchAppByPackage(IntentConstants.proto_browser);			
			addStep("在超链接栏输入一个有下载资源的超链接地址");
			// url input edit text
			LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.browser:id/url"));
			urlEdit.click();
			callShell("input text " + dServer + "/picture.php");
			sleepInt(1);
			addStep("下载图片文件并确认下载完成");
			phone.pressEnter();
			sleepInt(5);
			press_back(4);
			int count = getDownloadCount();
			if (count < i + 1) {
				fail("Can't download picture, TestCaseLoop===" + i);
			}
		}
		addStep("删除下载文件");
		testDeleteAll();
		addStep("退出浏览器");
		press_back(5);
	}

	@CaseName("下载视频并能正确打开")
	public void testDownloadVideo() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		addStep("清除浏览器缓存，历史记录和cookie数据");
		for (int i = 0; i < getIntParams("Loop"); i++) {
			launchAppByPackage(IntentConstants.proto_browser);			
			addStep("在超链接栏输入一个有下载资源的超链接地址");
			// url input edit text
			LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.browser:id/url"));
			urlEdit.click();
			callShell("input text " + dServer + "/video.php");
			sleepInt(1);
			addStep("下载视频文件并确认下载完成");
			phone.pressEnter();
			sleepInt(8);
			press_back(4);
			int count = getDownloadCount();
			if (count < i + 1) {
				fail("Can't download music, TestCaseLoop===" + i);
			}
			addStep("播放此视频文件，正常播放后停止播放");
//			playFromList();
			launchApp("下载");
			sleepInt(1);
			LeUiObject file = new LeUiObject(new UiSelector().className(
					"android.widget.ListView").childSelector(new UiSelector().className(
						"android.widget.LinearLayout").index(0)));
			int dx = file.getBounds().centerX();
			int dy = file.getBounds().centerY();
			phone.click(dx, dy);
			sleepInt(10);
			press_back(5);
		}
		addStep("删除下载文件");
		testDeleteAll();
		addStep("退出浏览器");
		press_back(5);
	}

	//浏览器的下载与删除音频，图片以及视频
	private void testDeleteAll() throws UiObjectNotFoundException {
		addStep("打开文件管理器");
		launchApp("下载");
		sleepInt(2);
		addStep("删除下载目录的所有文件（TXT文件、视频、音频和图片）");
		LeUiObject downloadList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView"));
		if(downloadList.exists()){
			LeUiObject file = new LeUiObject(new UiSelector().className(
					"android.widget.ListView").childSelector(new UiSelector().className(
					"android.widget.LinearLayout").index(0)));
			LeUiObject deleteBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").description("More options"));
			int count = 0;
			while (file.exists()) {
				if (count > 50)
					break;
				int dx = file.getBounds().centerX();
				int dy = file.getBounds().centerY();
				phone.swipe(dx, dy, dx, dy, 100);
				sleepInt(1);
				verify("Can't find delete button.", deleteBtn.exists());
				deleteBtn.click();
				count++;
			}
			addStep("确认删除成功");
			LeUiObject empty = new LeUiObject(new UiSelector().resourceId(
					"android:id/empty").text("No items"));
			verify("Can't delete files.", empty.exists());
		}
		addStep("退出文件管理器");
		press_back(3);
	}
}

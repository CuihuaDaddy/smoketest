package com.letv.cases.leui.browser;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
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
		launchApp(AppName.DOWNLOAD);
		LeUiObject listView = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.android.documentsui:id/download_list"));
		verify("Cant't find list view.", listView.exists());
		count = listView.getChildCount() - 1;
		press_back(5);
		return count;
	}

	// play download audio or video
	private void playFromList() throws UiObjectNotFoundException {
		launchApp(AppName.FILEMANAGER);
		LeUiObject download = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/navigation_view_item_name")
				.text("Download"));
		verify("Can't find download folder.", download.exists());
		download.click();
		LeUiObject file = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/navigation_view_details_item")
				.index(0));
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
			launchApp(AppName.BROWSER);
			LeUiObject home = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(2));
			verify("Can't find home page button.", home.exists());
			home.click();
			sleepInt(1);
			addStep("在超链接栏输入一个有下载资源的超链接地址");
			// url input edit text
			LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.browser:id/url_input_view"));
			urlEdit.click();
			sleepInt(1);
			callShell("input text " + dServer + "/txt.php");
			sleepInt(1);
			addStep("下载正常文本文件完成");
			phone.pressEnter();
			sleepInt(5);
			addStep("确认下载文件成功，并清空下载历史记录列表");
			int count = getDownloadCount();
			if (count < i + 1) {
				fail("Can't download txt file, loop===" + i);
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
			launchApp(AppName.BROWSER);
			LeUiObject home = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(2));
			verify("Can't find home page button.", home.exists());
			home.click();
			addStep("在超链接栏输入一个有下载资源的超链接地址");
			// url input edit text
			LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.browser:id/url_input_view"));
			urlEdit.click();
			callShell("input text " + dServer + "/music.php");
			sleepInt(1);
			addStep("下载音频文件并确认下载完成");
			phone.pressEnter();
			sleepInt(5);
			int count = getDownloadCount();
			if (count < i + 1) {
				fail("Can't download music, loop===" + i);
			}
			addStep("播放此音频文件，正常播放后停止播放");
//			playFromList();
			LeUiObject Downloads = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text("Downloads").description("Downloads"));
			launchApp(AppName.DOWNLOAD);
			sleepInt(2);
			LeUiObject firstFile = new LeUiObject(new UiSelector().className(
					"android.widget.RelativeLayout").index(1));
			firstFile.click();
			sleepInt(5);
			addStep("停止播放");
			phone.click(548, 1645);
			sleepInt(1);
			press_back(1);
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
			launchApp(AppName.BROWSER);
			LeUiObject home = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(2));
			verify("Can't find home page button.", home.exists());
			home.click();
			addStep("在超链接栏输入一个有下载资源的超链接地址");
			// url input edit text
			LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.browser:id/url_input_view"));
			urlEdit.click();
			callShell("input text " + dServer + "/picture.php");
			sleepInt(1);
			addStep("下载图片文件并确认下载完成");
			phone.pressEnter();
			sleepInt(5);
			int count = getDownloadCount();
			if (count < i + 1) {
				fail("Can't download picture, loop===" + i);
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
			launchApp(AppName.BROWSER);
			LeUiObject home = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(2));
			verify("Can't find home page button.", home.exists());
			home.click();
			addStep("在超链接栏输入一个有下载资源的超链接地址");
			// url input edit text
			LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.browser:id/url_input_view"));
			urlEdit.click();
			callShell("input text " + dServer + "/video.php");
			sleepInt(1);
			addStep("下载视频文件并确认下载完成");
			phone.pressEnter();
			sleepInt(10);
			int count = getDownloadCount();
			if (count < i + 1) {
				fail("Can't download music, loop===" + i);
			}
			addStep("播放此视频文件，正常播放后停止播放");
//			playFromList();
			LeUiObject Downloads = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text("Downloads").description("Downloads"));
			launchApp(AppName.DOWNLOAD);
			sleepInt(2);
			LeUiObject firstFile = new LeUiObject(new UiSelector().className(
					"android.widget.RelativeLayout").index(1));
			firstFile.click();
			LeUiObject videoPlayer = new LeUiObject(new UiSelector().resourceId("com.android.videoplayer:id/info_container").className("android.widget.LinearLayout"));
			verify("Can't open video files", videoPlayer.exists());
			sleepInt(5);
			addStep("停止播放");
			press_back(1);
		}
		addStep("删除下载文件");
		testDeleteAll();
		addStep("退出浏览器");
		press_back(5);
	}

	@CaseName("浏览器的下载与删除（音频，图片以及视频）")
	private void testDeleteAll() throws UiObjectNotFoundException {
		addStep("打开下载管理器");
		launchApp(AppName.DOWNLOAD);
		sleepInt(2);
		addStep("删除下载目录的所有文件（TXT文件、视频、音频和图片）");
		LeUiObject select = new LeUiObject(new UiSelector().resourceId("com.android.documentsui:id/menu_select").text("select"));
		if (select.exists()) { // there are some files
			select.click();
			sleepInt(2);
			LeUiObject all = new LeUiObject(new UiSelector().text("select all").resourceId(
					"com.android.documentsui:id/menu_select_all"));
			verify("Can't find all button.", all.exists());
			all.click();
			sleepInt(2);
/*			LeUiObject delete = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("删除"));
			verify("Can't find delete button.", delete.exists());
			delete.click();*/
			phone.click(730, 1850);
			sleepInt(2);
			LeUiObject confirm = new LeUiObject(new UiSelector().resourceId(
					"android:id/le_bottomsheet_btn_cancel_1").text(
					"Delete"));
			verify("Can't find confirm button.", confirm.exists());
			confirm.click();
		} else { // no files
			System.out.println("There is no file in download list.");
		}
		addStep("确认删除成功");
		verify("Can't delete files.", !select.exists());
		addStep("退出");
		press_back(5);
	}
}

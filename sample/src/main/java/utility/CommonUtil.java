package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonUtil {

	//  takeScreenShot(WebDriver driver,String folderScreenShot, String testCaseId)使い方
	//  テスト結果が”成功”以外の時にスクリーンショット取得
	//	@AfterMethod(alwaysRun = true)
	//	public void afterCheckItemA(ITestResult result) {
	//		if(result.getStatus() != ITestResult.SUCCESS) {
	//			CommonUtil.takeScreenShot(driver, ".\\result", result.getMethod().getMethodName());
	//		}
	//		driver.quit();
	//		System.out.println("site closed1");
	//	}

	/**
	 * take screenshot
	 * @param driver
	 * @param folderScreenShot
	 * @param testCaseId
	 */
	public static void takeScreenShot(WebDriver driver, String folderScreenShot, String testCaseId) {
		takeScreenShot(driver, createScreenShotFileName(folderScreenShot, testCaseId));
	}

	/** 
	 * create Screen Shot FileName
	 * @param folderScreenShot
	 * @param testCaseId
	 * @return
	 */
	public static String createScreenShotFileName(String folderScreenShot, String testCaseId) {
		return folderScreenShot + File.separator + testCaseId + ".png";
	}

	/**
	 * take Screen Shot
	 * @param driver
	 * @param fileName
	 */
	private static void takeScreenShot(WebDriver driver, String fileName) {
		if (null == driver) {
			return;
		}
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException e) {
			System.out.println(e);
		}
		//		System.out.println(scrFile);
		//		System.out.println(fileName);
	}

}

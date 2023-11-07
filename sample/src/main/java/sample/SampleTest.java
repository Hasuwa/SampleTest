package sample;

import static org.testng.Assert.*;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.CommonUtil;

public class SampleTest extends TestBase {

	@BeforeMethod(alwaysRun = true)
	public void before() {
		testNum = 1;
		beforeTheCheck();
	}

	@AfterMethod(alwaysRun = true)
	public void after(ITestResult result) {
		if (result.getStatus() != ITestResult.SUCCESS) {
			CommonUtil.takeScreenShot(driver, ".\\result", result.getMethod().getMethodName());
		}
		afterTheCheck();
	}

	@Test(groups = "answerCheck")
	public void testA() {
		selectYear();
		startQuestion();
		answerQuestion(SEND_ANS_I_PATH);
		assertEquals(sentAnswer, correctAnswer);
	}
}

package sample;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	WebDriver driver;
	int testNum;
	Duration waitTime;
	WebDriverWait wait;
	String sentAnswer;
	String correctAnswer;

	final String FESHIKEN_PATH = "https://www.fe-siken.com/";
	final String FIRST_QUESTION_XPATH = "//*[@id=\"mainCol\"]/div[2]/table/tbody/tr[3]/td[1]/a";
	final String SEND_ANS_CORRECT_PATH = "//*[@id=\"t\"]";
	final String SEND_ANS_A_PATH = "//*[@id=\"mainCol\"]/div[2]/div[2]/ul/li[1]/button";
	final String SEND_ANS_I_PATH = "//*[@id=\"mainCol\"]/div[2]/div[2]/ul/li[2]/button";
	final String SEND_ANS_U_PATH = "//*[@id=\"mainCol\"]/div[2]/div[2]/ul/li[3]/button";
	final String SEND_ANS_E_PATH = "//*[@id=\"mainCol\"]/div[2]/div[2]/ul/li[4]/button";
	final String ANSWER_XPATH = "//*[@id=\"answerChar\"]";

	/**
	 * ChromeDriverのパスを指定
	 */
	public void setPropatyPath() {
		final String CHOROME_DRIVER_PATH = "chromeDriver/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", CHOROME_DRIVER_PATH);
	}

	/**
	 * ①サイトを開く
	 */
	public void siteOpen() {
		driver = new ChromeDriver();
		driver.get(FESHIKEN_PATH);
	}

	/**
	 * ②ウィンドウを最大化
	 */
	public void maximizeWindow() {
		driver.manage().window().maximize();
		System.out.println("site opened " + testNum);
	}

	/**
	 * ③テストする過去問の年度を選択し画面遷移する
	 */
	public void selectYear() {
		waitTime = Duration.ofSeconds(30);
		wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"testMenu\"]/li[" + testNum + "]/a")));
		System.out.println(this.driver.findElement(By.xpath("//*[@id=\"testMenu\"]/li[" + testNum + "]/a")).getText());
		this.driver.findElement(By.xpath("//*[@id=\"testMenu\"]/li[" + testNum + "]/a")).click();
	}

	/**
	 * 選択した年度の「問１」をクリック
	 */
	public void startQuestion() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_QUESTION_XPATH)));
		this.driver.findElement(By.xpath(FIRST_QUESTION_XPATH)).click();
	}

	/**
	 * 回答する
	 * @param answer
	 */
	public void answerQuestion(String answer) {
		// 引数で指定されたXpathを回答し、アイウエを画面から取得
		sentAnswer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answer))).getText();
		this.driver.findElement(By.xpath(answer)).click();
		System.out.println(testNum + " 回答 " + sentAnswer);
		// 正解のアイウエを画面から取得
		correctAnswer = this.driver.findElement(By.xpath(ANSWER_XPATH)).getText();
		System.out.println(testNum + " 正解 " + correctAnswer);
	}

	public void beforeTheCheck() {
		setPropatyPath();
		siteOpen();
		maximizeWindow();
	}

	public void afterTheCheck() {

		driver.quit();
		System.out.println("site closed " + testNum);
	}
}

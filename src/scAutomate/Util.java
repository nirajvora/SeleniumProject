package scAutomate;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class Util {
	public static boolean isPresent(WebDriver driver, String xpath) {
		return driver.findElements(By.xpath(xpath)).size() > 0;
	}

	protected static void switchTabs(WebDriver driver, int tabIndex) {
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabIndex));
	}

	protected static void createBlankTarget(WebDriver driver, WebElement profile) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return arguments[0].target='_blank'";
		Object result = js.executeScript(script, profile);
	}

	protected static void addClass(WebDriver driver, WebElement profile, String count) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return arguments[0].class='" + count + "'";
		Object result = js.executeScript(script, profile);
	}
	
	protected static void sendMessage(WebDriver driver, String message) throws InterruptedException {
		String messageXpath = "//*[@id='content']/div/div[4]/div/div[2]/div/a[last()]";
		String textXpath = "html/body/div[3]/div/div/div/div[2]/div[1]/div/textarea";
		String sendXpath = "html/body/div[3]/div/div/div/div[2]/div[2]/div[2]/button[2]";
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement messageLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(messageXpath)));
		messageLink.click();
		WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(textXpath)));
		textArea.sendKeys(message);
		WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(sendXpath)));
		Thread.sleep(500);
		sendButton.click();
	}

	protected static void scrollInToView(WebDriver driver, WebElement profile) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "arguments[0].scrollIntoView(false);";
		js.executeScript(script, profile);
	}

	protected static void closeCookiePolicy(WebDriver driver) {
		String xpath = "//*[@id='app']/div[1]/div/div/div/a";
		WebDriverWait wait = new WebDriverWait(driver, 10);
		if(Util.isPresent(driver, xpath)) {
			WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			closeButton.click();
		}
	}
	
	protected static void hidePlayer(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "document.getElementsByClassName(\"playControls\")[0].removeAttribute(\"class\");";
		js.executeScript(script);
	}


	protected static int getRandomTime(int count) {
		// TODO Auto-generated method stub
		Random rand = new Random();

		int  n = rand.nextInt(120000) + 60000;
		if((count % 23) == 0) {
			int  m = rand.nextInt(900000) + 900000;
			n += m;
		}
		return n;
	}

}

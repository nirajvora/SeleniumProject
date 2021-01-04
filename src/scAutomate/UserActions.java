package scAutomate;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import scAutomate.Util;

abstract class UserActions {
	protected static void homePageSignIn(WebDriver driver, String email, String password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
//		Click 'Sign In' top right corner
		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/div/div/div[1]/div/div/div[3]/button[1]")));
		loginButton.click();
		Thread.sleep(1000);
		logIn(driver, wait, email, password);
	}
	
	private static void logIn(WebDriver driver, WebDriverWait wait, String email, String password) throws InterruptedException {
//		Fill out email and click continue
		WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[3]/div/div/form/div/div[1]/div/div[2]/div/input")));
		emailInput.sendKeys(email);
		driver.findElement(By.xpath("html/body/div[3]/div/div/form/div/div[1]/div/button")).click();
		Thread.sleep(1000);
//		Fill out password and click Sign In
		WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[3]/div/div/form/div/div[2]/div/div[2]/div/input")));
		passwordInput.sendKeys(password);
		driver.findElement(By.xpath("html/body/div[3]/div/div/form/div/div[2]/div/button")).click();
	}
	
	protected static void messageFollowers(WebDriver driver, String message, int start) throws InterruptedException {
		Actions action = new Actions(driver);
		
		if(!(start > 0)) { start = 1; }
		String followerXpathPre = "//*[@id='content']/div/div/div[2]/div/div/ul/li[";
		String profileXpathPost = "]/div/div[2]/a";
		String followButtonXpathPost = "]/div/div[3]/button";
		int currentFollower = start;
		int waitTime;
		String currentXpath = followerXpathPre + String.valueOf(start) + profileXpathPost;
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(2000);
		
//		loop through followers list and message each one .
		String baseWindow = driver.getWindowHandle();
		while(Util.isPresent(driver, currentXpath)) {
			WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(currentXpath)));
			Util.createBlankTarget(driver, profile);
			if((start - 1) % 12 != 0 || start == 1) {
				profile.click();
			} else {
				Util.scrollInToView(driver, profile);
				profile.click();
			}
			
			Thread.sleep(2000);

			Util.switchTabs(driver, 1);
			String finalMessage = MessageCreator.conformMessage(start, message);
			
			Util.sendMessage(driver, finalMessage);
//			System.out.println(finalMessage);
			System.out.println("Number of users messaged : " + start);
			
			Thread.sleep(2000);
			
			driver.close();
			Util.switchTabs(driver, 0);
			
			waitTime = Util.getRandomTime(start);
			System.out.println("Time Before next message : " + waitTime);
			Thread.sleep(10000);
			
			start ++;
			currentXpath = followerXpathPre + String.valueOf(start) + profileXpathPost;
		}
	}
	
	protected static void goToAccount(WebDriver driver, String username, String userXpath) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(2000);
//		Locate Search Bar and search for 'String username'
		WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app']/header/div/div[2]/div/form/input")));
		searchBar.sendKeys(username);
		searchBar.sendKeys(Keys.RETURN);
		Thread.sleep(2000);
//		Click on intended user profile based on xpath
		WebElement user = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(userXpath)));
		user.click();
	}
	
	protected static void goToFollowers(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(2000);
//		Click on 'Followers' on right side of user profile to get followers list.
		WebElement followersLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/div/div[5]/div[2]/div/article[1]/table/tbody/tr/td[1]/a/div")));
		followersLink.click();
	}

	public static void userPageSignIn(WebDriver driver, String email, String password) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
//		Click 'follow' for first user
		WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/div/div/div[2]/div/div/ul/li[1]/div/div[3]/button")));
		loginButton.click();
		Thread.sleep(1000);
		logIn(driver, wait, email, password);
		Thread.sleep(5000);
	}

	public static void goToReposts(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(2000);
//		Click on 'Followers' on right side of user profile to get followers list.
		WebElement repostsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[1]/div[2]/div/div[5]/div[1]/div/div[1]/div/div/div[2]/div[2]/div/ul/li[3]/a")));
		repostsLink.click();
	}
}

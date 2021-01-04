package scAutomate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxWorker {
	static WebDriver driver;
	static String baseURL = "https://www.soundcloud.com";
	static String message = "Hey, noticed you like Darci's music. We're a new and upcoming curation group that works with artists like him and wanted to share this track with you. \n\nLet us know what you think and give us a follow for more tracks like this! https://soundcloud.com/sui-luj/2012-yaguar-callahan-dilip-chromonicci";
	static String accountPath = "//*[@id='content']/div/div/div[3]/div/div/div/ul/li[2]/div/div/a/div/span";
	static String songPath = "//*[@id='content']/div/div/div[3]/div/div/div/ul/li[2]/div/div/div/div[2]/div[1]/div/div/div[2]/a/span";
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.gecko.driver", "/opt/Selenium/geckodriver");
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", "/opt/Selenium/chromedriver");
//		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
//		Sign in to soundcloud
		driver.get(baseURL);
		scAutomate.UserActions.homePageSignIn(driver, "the_kid01@aol.com", "password01");
		
		scAutomate.UserActions.goToAccount(driver, "darci these nights", songPath);
		
//		go to followers page of desired profile
//		scAutomate.UserActions.goToFollowers(driver);
		
//		or go to reposts page of desired song
		scAutomate.UserActions.goToReposts(driver);
		
		Util.closeCookiePolicy(driver);
		Util.hidePlayer(driver);
		
		scAutomate.UserActions.messageFollowers(driver, message, 1);
	}
	
	

}

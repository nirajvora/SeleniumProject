package scAutomate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tester {
	static WebDriver driver;
	static String baseURL = "https://www.soundcloud.com";
	static String followersURL = "https://soundcloud.com/lexaura/followers";
	static String message = "Hey, noticed you're following lex aura. We're a bay area curation page that works with artists like him and wanted to share this track with you. \n\nLet us know what you think and give us a follow for more ies like this!";

	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "/opt/Selenium/geckodriver");
		driver = new FirefoxDriver();
		
		System.out.println(message);
//		System.setProperty("webdriver.chrome.driver", "/opt/Selenium/chromedriver");
//		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		
//		go to followers page of desired profile
		driver.get(followersURL);
		Util.closeCookiePolicy(driver);

		scAutomate.UserActions.messageFollowers(driver, message, 1);
		
	}

}

package scAutomate;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class repostFollower {
	static WebDriver driver;
	static String baseURL = "https://www.soundcloud.com";
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "/opt/Selenium/geckodriver");
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", "/opt/Selenium/chromedriver");
//		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
//		Sign in to soundcloud
		driver.get("https://soundcloud.com/russ/dangerous-prod-russ/reposts");
		
		scAutomate.UserActions.userPageSignIn(driver, "the_kid8@aol.com", "password01");
		
		Thread.sleep(2000);
		Util.closeCookiePolicy(driver);
//		Util.hidePlayer(driver);
//		
//		scAutomate.UserActions.followFollowers(driver);
	}

}

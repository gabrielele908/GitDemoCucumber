package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class base {

	//static driver run one by one testcases. if we need run all together so remove static and put in testng.xml <suite name="Suite" parallel="test">
	public static WebDriver driver;
	public Properties prop;
    public String currentDirectory = System.getProperty("user.dir");
    
	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(currentDirectory+"\\src\\main\\java\\resources\\data.properties");

		prop.load(fis);
String browserName=prop.getProperty("browser"); //take a value inside in the Properties.

		/* mvn test -Dbrowser=chrome */
//for maven-in real time can be any browser you put.
	//	String browserName = System.getProperty("browser");
		//System.out.println(browserName);
//can use also equals to compare.
		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\gabri\\eclipse-workspace\\E2EProject\\src\\main\\java\\resources\\chromedriver.exe");
			ChromeOptions options= new ChromeOptions();	
			
			//running backend- without open windows driver.
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
		    System.out.println("update git");
		    System.out.println("update git2");
		    System.out.println("update git3");
			driver = new ChromeDriver(options);
			// execute in chrome driver

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\webdrivers\\geckodriver64.exe");
			driver = new FirefoxDriver();
		
		} else if (browserName.equals("IE")) {
			
			System.setProperty("webdriver.ie.driver", "C:\\webdrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public void getScreenshot(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Users\\gabri\\screenshot\\" + result + " screenshot.png"));

	}

}

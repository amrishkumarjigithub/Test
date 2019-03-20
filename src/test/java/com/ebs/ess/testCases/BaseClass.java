package com.ebs.ess.testCases;

import java.io.File;
import java.io.IOException;
// import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
// import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

// import com.aventstack.extentreports.ExtentReports;
// import com.aventstack.extentreports.ExtentTest;
import com.ebs.ess.utilities.ReadConfig;


public class BaseClass {

	// public String baseURL="http://envu.cit.health.nsw.gov.au/";
	// public String username="73000007";
	// public String password="Password123!";

	public static ReadConfig readconfig= new ReadConfig();
	
	public String baseURL=readconfig.getApplURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();

	public static ChromeOptions loptions;
	public static WebDriver gdriver;
	
	public static Logger logger;

	@Parameters("browser")

	@BeforeClass
	public void setUp(String pbr) {
		
		logger=Logger.getLogger("loginEBS");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("BaseClass.Setup - " + pbr +" ....");

		if (pbr.equals("chrome")) {
			// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			loptions = new ChromeOptions();
			loptions.addArguments("--log-level=2");
			loptions.addArguments("test-type");
			loptions.addArguments("--js-flags=--expose-gc");
			loptions.addArguments("--enable-precise-memory-info");
			loptions.addArguments("--disable-popup-blocking");
			loptions.addArguments("--disable-default-apps");
			loptions.addArguments("--enable-automation");
			loptions.addArguments("test-type=browser");
			loptions.addArguments("disable-infobars");
			loptions.setExperimentalOption("useAutomationExtension", false);
			loptions.addArguments("disable-extensions");
	
			gdriver=new ChromeDriver(loptions);
		}
		else if (pbr.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			gdriver=new FirefoxDriver();
		}
		else if (pbr.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			gdriver=new InternetExplorerDriver();
		}
		else if (pbr.equals("edge")) {
			System.setProperty("webdriver.edge.driver", readconfig.getEdgePath());
			gdriver=new EdgeDriver();
		}
		else {
			System.out.println("BaseClass.Setup Not valid Browser....");
		}


		// gdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// gdriver.get(baseURL);
		// logger.info("BaseClass.Setup - URL is opened..");

		logger.info("BaseClass.Setup - End...");

	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		gdriver.quit();
		logger.info("BaseClass.tearDown quit...");
	}



	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		// System.out.println("BaseClass.captureScreen - taken...");
		logger.info("BaseClass.captureScreen ...");
	}


	/*
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	*/

}

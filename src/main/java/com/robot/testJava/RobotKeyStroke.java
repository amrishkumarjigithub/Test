package com.robot.testJava;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RobotKeyStroke {

	public static ChromeOptions loptions;
	public static WebDriver gdriver;

	public static void main(String[] args) throws AWTException, InterruptedException{
		
		// System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
		// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
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

		//gdriver=new ChromeDriver(loptions);
		gdriver=new FirefoxDriver();
		gdriver.get("http://spreadsheetpage.com/index.php/site/file/yearly_calendar_workbook");
		
		gdriver.findElement(By.linkText("yearly-calendar.xls")).click();
		// ro gdriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/ul/li[3]/a"));

		
		Robot robot = new Robot();
		System.out.println("Robot Class....");

		robot.keyPress(KeyEvent.VK_DOWN);
		System.out.println("Robot Key Down....");
		Thread.sleep(3000);
		
		robot.keyPress(KeyEvent.VK_TAB);
		System.out.println("Robot Key Tab....");
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_TAB);
		System.out.println("Robot Key Tab....");
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_TAB);
		System.out.println("Robot Key Tab....");
		Thread.sleep(3000);

		robot.keyPress(KeyEvent.VK_ENTER);
		System.out.println("Robot Key Enter....");
		Thread.sleep(3000);

	}

}

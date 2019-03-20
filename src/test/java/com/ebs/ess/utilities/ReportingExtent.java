package com.ebs.ess.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
// import org.openqa.selenium.OutputType;
// import org.openqa.selenium.TakesScreenshot;
// import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportingExtent extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	// private static ThreadLocal<ExtentTest> test = new ThreadLocal();
	// WebDriver driver = CommonHelper.driver;
		
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location of the report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		
		extent=new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","Testpwd");

		htmlReporter.config().setDocumentTitle("LoginEBSV1 Test Project"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		// htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public void onTestSuccess(ITestResult tr)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
		logger.log(Status.PASS,MarkupHelper.createLabel("PASS",ExtentColor.GREEN));
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";

		// File f = new File(screenshotPath); 

		File sf = new File(screenshotPath); 
		String tscreenshotPath=System.getProperty("user.dir") + "/Screenshots/" +tr.getName() +timeStamp+ ".png";
		//File sourcefile = ts.getScreenshotAs(OutputType.FILE);
		File tf = new File(tscreenshotPath);
		try {
			FileUtils.copyFile(sf, tf);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		if(tf.exists())
		// if(f.exists())
		{
			try {
				logger.pass("Screenshot is below (Pass):" + logger.addScreenCaptureFromPath(tscreenshotPath));
			} catch (Exception e) {
				logger.log(Status.PASS," PASS12 File Exists Screen Capture...");
			}
		}
		else {
			logger.log(Status.PASS," PASS File NOT Exists Screen Capture..."); // send the passed information to the report with GREEN color highlighted
		}

	}
	
	public void onTestFailure(ITestResult tr)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
		logger.log(Status.FAIL,MarkupHelper.createLabel("FAIL",ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
		// logger.log(Status.FAIL,"est FaileTd write..."); // send the passed information to the report with GREEN color highlighted
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";

		// File f = new File(screenshotPath); 

		File sf = new File(screenshotPath); 
		String tscreenshotPath=System.getProperty("user.dir") + "/Screenshots/" +tr.getName() +timeStamp+ ".png";
		//File sourcefile = ts.getScreenshotAs(OutputType.FILE);
		File tf = new File(tscreenshotPath);
		try {
			FileUtils.copyFile(sf, tf);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		if(tf.exists())
		// if(f.exists())
		{
		try {
			logger.fail("Screenshot is below (Fail):" + logger.addScreenCaptureFromPath(tscreenshotPath));
			} 
		catch (IOException e) 
				{
				e.printStackTrace();
				}
		}
		else {
			logger.fail("FAIL File NOT Exists Screen Capture..."); // send the passed information to the report with GREEN color highlighted
		}
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		logger.log(Status.SKIP,MarkupHelper.createLabel("SKIPPED",ExtentColor.ORANGE));
		// logger.log(Status.SKIP,"Test Skipped write...");
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	

}

package com.ebs.ess.testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
// import org.openqa.selenium.NoAlertPresentException;
// import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
// import org.testng.annotations.Test;

import com.ebs.ess.pageObjects.LoginPageEBS;
import com.ebs.ess.utilities.XLUtils;


public class TC_LoginTestDDT extends BaseClass {
	
	public static int itcounter=0;
	public static String lmsg1;
	public String gloc1="1. Start";


	@Test(dataProvider="LoginUserData02")
	public void loginDDT02(String pUserid, String pPassword) throws InterruptedException {

		itcounter++;
		lmsg1=" loginDDT02";
		logger.info(itcounter + lmsg1 + " - Entry Started.... wait ");

		Thread.sleep(3000);

		gdriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		gdriver.get(baseURL);
		
		logger.info(itcounter + lmsg1 + " - URL is opened...");

		if(pUserid.isEmpty() || pPassword.isEmpty()) {

			logger.error(itcounter + lmsg1 + " - Userid AND/OR Password is Empty - Failed.......");
			Assert.assertTrue(false);
			//Assert.
			// Assert.assert
			// logger.warn(itcounter + lmsg1 + " - Assert after AssertTrue Userid OR Password is Empty - Failed.......");
			// Assert.assertFalse(false);
			// logger.warn(itcounter + lmsg1 + " - Assert after AssertFalse Userid OR Password is Empty - Failed.......");
		}

		gdriver.get(baseURL);
		logger.info("BaseClass.Setup - URL is opened..");
		LoginPageEBS lp=new LoginPageEBS(gdriver);
		gdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

		// WebDriverWait wait = new WebDriverWait(gdriver, 10);
		try {
			if (gdriver.findElement(By.xpath("//input[@name='userid' and @type='text' and @title='Enter Username']")).isDisplayed()) {
				// gdriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				logger.info(itcounter + lmsg1 + " - Enter Username Page...");
			} else {
				logger.warn(itcounter + lmsg1 + " - NOT Displayed Enter Username failed...");
				AssertJUnit.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false);
			logger.warn(gloc1+ itcounter + lmsg1 + " - 54 Exception in (Enter Username)...."); // + e.getMessage());
		}


		lp.setUserName(pUserid);
		logger.info(itcounter + lmsg1 + " - Entered Username...");
		gdriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		lp.clickContinue();
		logger.info(itcounter + lmsg1 + " - clickContinue...");
		// gdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		gdriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		try {
			if (gdriver.findElement(By.xpath("//input[@name='Bharosa_Password_PadDataField' and @type='password' and @title='Password']")).isDisplayed()) {
				// gdriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				logger.info(itcounter + lmsg1 + " - Password Page...");
			} else {
				logger.warn(itcounter + lmsg1 + " - NOT Displayed Password failed...");
				AssertJUnit.assertTrue(false);
			}
		} catch (Exception e) {
			AssertJUnit.assertTrue(false);
			logger.warn(gloc1+ itcounter + lmsg1 + " - 54 Exception in (Password)...."); // + e.getMessage());
		}

		lp.setPassword(pPassword);
		logger.info(itcounter + lmsg1 + " - Entered Password..");
		gdriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		// gdriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		lp.pressEnter();
		logger.info(itcounter + lmsg1 + " - pressEnter...");
		// gdriver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		gdriver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);		

		try {
			if (gdriver.findElement(By.xpath(
					"//div[@id='errorMessage' and contains(@title,'Sorry, the identification you entered was not')]"))
					.isDisplayed()
					|| gdriver.getCurrentUrl()
							.contentEquals("https://spcsso.cit.health.nsw.gov.au/oaam_server/loginPage.jsp")) {
				logger.warn(itcounter + lmsg1 + " - Login failed...");

				try {
					gdriver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
					captureScreen(gdriver, "loginDDT02");
					logger.info(itcounter + lmsg1 + " - Screenshot Login failed.......");
					gdriver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
					// lp.clickLogout();
				} catch (Exception e) {
					logger.info(itcounter + lmsg1 + " - Error - Exception - Screenshot Login failed....." + e.getMessage());
				}

				Assert.assertTrue(false);
				gdriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			} else {
				logger.info(itcounter + lmsg1 + " - Login Passed...");
				// AssertJUnit.assertTrue(true);
			}
		} catch (Exception e) {
			logger.info(gloc1+ itcounter + lmsg1 + " - 54 Exception in (if)...."); // + e.getMessage());
		}

		try {
			gdriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			captureScreen(gdriver, "loginDDT02");
			Assert.assertTrue(true);
			logger.info(itcounter + lmsg1 + " - before logout....");
			Thread.sleep(1000);
			lp.clickLogout();
		} catch (Exception e) {
			logger.info(itcounter + lmsg1 + " - Error - Exception" + e.getMessage());
		}
	}


	@DataProvider(name="LoginUserData02")
	String[][] getData01() throws IOException {
		String srcFileAndPath = System.getProperty("user.dir")
						+ "/src/test/java/com/ebs/ess/testData/LoginUserData02.xlsx";
		
		int rownum=XLUtils.getRowCount(srcFileAndPath, "Sheet1");
		int colcount=XLUtils.getCellCount(srcFileAndPath, "Sheet1",1);
		
		logger.info("Row Num: " + rownum + ", Column Count: " + colcount);

		String logindata[][] =  new String[rownum][colcount];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(srcFileAndPath,"Sheet1", i,j);//1 0
				logger.info(" - Row: " + (i-1) + " " + XLUtils.getCellData(srcFileAndPath, "Sheet1", i, j) + ", Column: " + j);
			}
		}
		
		// logger.info("Cell: 0,0 " + logindata[0][0] + ", Cell: 0,1 " + logindata[0][1]);
		// logger.info("Cell: 1,0 " + logindata[1][0] + ", Cell: 1,1 " + logindata[1][1]);

		return logindata;
	}


}

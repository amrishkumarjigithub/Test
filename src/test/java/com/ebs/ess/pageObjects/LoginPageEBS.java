package com.ebs.ess.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageEBS {

	WebDriver ldriver;
	Logger logger1;
	public static int cnt=0;

	public LoginPageEBS(WebDriver rdriver) {

		cnt++;
		logger1=Logger.getLogger("LoginPage");
		logger1.info(cnt + " LoginPage - Constructor..." );

		ldriver=rdriver;
        ldriver.manage().deleteAllCookies();
        ldriver.manage().window().maximize();
		
        logger1.info(cnt + " LoginPage - window..." );

        PageFactory.initElements(rdriver, this);
        logger1.info(cnt + " LoginPage - this..." );
	}
	
	
	@FindBy(name="userid")
	@CacheLookup
	WebElement txtUserName;
		
	@FindBy(className="loginButton")
	@CacheLookup
	WebElement btnContinue;	

	@FindBy(id="Bharosa_Password_PadDataField")
	@CacheLookup
	WebElement txtPassword;	

	// @FindBy(id="Bharosa_Password_PadMap")
	// @FindBy(xpath="area[alt=\"enter\"]")
	@FindBy(id="Bharosa_Password_PadDataField")
	@CacheLookup
	WebElement keyEnter;	

	@FindBy(linkText="Logout")
	@CacheLookup
	WebElement lnkLogout;	


	public void setUserName(String pUserid) {
		txtUserName.sendKeys(pUserid);
		logger1.info("LoginPage - setUserName..." );
	}


	public void clickContinue() {
		btnContinue.click();
	}

	public void setPassword(String pPassword) {
		txtPassword.sendKeys(pPassword);
		logger1.info("LoginPage - setPassword..." );
	}

	public void pressEnter() {
		keyEnter.submit();
		logger1.info("LoginPage - pressEnter..." );
	}
	
	public void clickLogout() {
		lnkLogout.click();
		logger1.info("LoginPage - clickLogout..." );
	}

	// public void navigateToLoginPage() {
		// lnkLogout.click();
	// }

}

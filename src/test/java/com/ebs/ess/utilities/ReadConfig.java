package com.ebs.ess.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;

	public ReadConfig() {
		File srcFile = new File("./Configuration/config.properties");
		
		try {
			FileInputStream srcFis = new FileInputStream(srcFile);
			pro = new Properties();
			pro.load(srcFis);
		} catch (Exception e) {
			System.out.println("Error - Exception : "+e.getMessage());
		}		
	}
	
	public String getApplURL() {
		String applurl=pro.getProperty("ApplURL");
		return applurl;
	}
	
	public String getUsername() {
		String username=pro.getProperty("Username");
		return username;
	}

	public String getPassword() {
		String password=pro.getProperty("Password");
		return password;
	}
	
	public String getChromePath() {
		String chromepath=pro.getProperty("ChromePath");
		return chromepath;
	}

	public String getIEPath() {
		String iepath=pro.getProperty("IEPath");
		return iepath;
	}
	
	public String getEdgePath() {
		String edgepath=pro.getProperty("EdgePath");
		return edgepath;
	}

	public String getFirefoxPath() {
		String firefoxpath=pro.getProperty("FirefoxPath");
		return firefoxpath;
	}

}

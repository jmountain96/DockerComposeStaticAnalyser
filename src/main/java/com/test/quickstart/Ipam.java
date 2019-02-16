package com.test.quickstart;

import com.test.quickstart.Validation.Interfaces.CheckStringListFormat;
import com.test.quickstart.Validation.ValidationEnums;

public class Ipam {
	private String driver;
	@CheckStringListFormat(message = "Subnet in IPAM config is of incorrect format", value = ValidationEnums.CheckStringListType.SUBNET)
	private String[] config;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String[] getConfig() {
		return config;
	}
	public void setConfig(String[] config) {
		this.config = config;
	}
}

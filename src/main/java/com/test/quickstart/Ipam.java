package com.test.quickstart;

import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;

import java.util.Map;

import com.test.quickstart.Validation.ValidationEnums;

public class Ipam {
	private String driver;
	private Map<String,String>[] config;
	@CheckStringFormat(message = "Subnet in IPAM config is of incorrect format", value = ValidationEnums.CheckStringType.SUBNET)
	private String subnet;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public Map<String,String>[] getConfig() {
		return config;
	}
	public void setConfig(Map<String,String>[] config) {
		this.config = config;
		setSubnet(config[0].get("subnet"));
	}
	public String getSubnet() {
		return subnet;
	}
	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}
}

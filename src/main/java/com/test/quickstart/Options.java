package com.test.quickstart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.ValidationEnums;

public class Options {
	@JsonProperty("syslog-address")
	private String syslogAddress;
	@JsonProperty("max-size")
	@CheckStringFormat(message = "Invalid memory size specified for Logging Options maxsize", value = ValidationEnums.CheckStringType.MEMORY)
	private String maxSize;
	@JsonProperty("max-file")
	private int maxFile;
	public String getSyslogAddress() {
		return syslogAddress;
	}
	public void setSyslogAddress(String syslogAddress) {
		this.syslogAddress = syslogAddress;
	}
	public String getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}
	public int getMaxFile() {
		return maxFile;
	}
	public void setMaxFile(int maxFile) {
		this.maxFile = maxFile;
	}
}

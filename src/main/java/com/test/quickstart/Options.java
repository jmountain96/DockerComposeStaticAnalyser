package com.test.quickstart;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Options {
	@JsonProperty("syslog-address")
	private String syslogAddress;
	@JsonProperty("max-size")
	private String maxSize;
	@JsonProperty("max-file")
	private String maxFile;
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
	public String getMaxFile() {
		return maxFile;
	}
	public void setMaxFile(String maxFile) {
		this.maxFile = maxFile;
	}
}

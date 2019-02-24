package com.test.quickstart;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.ContainsString;

public class Logging {
	@ContainsString(message  = "Invalid logging driver specified", value = ValidationEnums.ContainsStringType.LOGGING_DRIVER)
	private String driver;
	private Options options;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public Options getOptions() {
		return options;
	}
	public void setOptions(Options options) {
		this.options = options;
	}
}

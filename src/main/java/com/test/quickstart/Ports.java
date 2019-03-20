package com.test.quickstart;

import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;
import com.test.quickstart.Validation.ValidationEnums;
public class Ports {
	@CheckStringFormat(message = "Invalid port target", value = ValidationEnums.CheckStringType.PORT)
	private String target;
	@CheckStringFormat(message = "Invalid port published", value = ValidationEnums.CheckStringType.PORT)
	private String published;
	@ContainsString(message = "Invalid port protocol", value = ValidationEnums.ContainsStringType.PORT_PROTOCOL)
	private String protocol;
	@ContainsString(message = "Invalid port mode", value = ValidationEnums.ContainsStringType.PORT_MODE)
	private String mode;
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
}

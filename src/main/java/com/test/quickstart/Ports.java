package com.test.quickstart;

import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;
import com.test.quickstart.Validation.ValidationEnums;
public class Ports {
	@CheckStringFormat(message = "Invalid port target", value = ValidationEnums.CheckStringType.PORT)
	private int target;
	@CheckStringFormat(message = "Invalid port published", value = ValidationEnums.CheckStringType.PORT)
	private int published;
	@ContainsString(message = "Invalid port protocol", value = ValidationEnums.ContainsStringType.PORT_PROTOCOL)
	private String protocol;
	@ContainsString(message = "Invalid port mode", value = ValidationEnums.ContainsStringType.PORT_MODE)
	private String mode;
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getPublished() {
		return published;
	}
	public void setPublished(int published) {
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

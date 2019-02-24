package com.test.quickstart;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;

public class RestartPolicy {
	@ContainsString(message = "Invalid restart policy condition", value = ValidationEnums.ContainsStringType.RESTART_POLICY_CONDITION)
	private String condition;
	@CheckStringFormat(message = "Invalid format for restart policy delay", value = ValidationEnums.CheckStringType.TIME)
	private String delay;
	private int max_attempts;
	@CheckStringFormat(message = "Invalid format for restart policy window", value = ValidationEnums.CheckStringType.TIME)
	private String window;
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public int getMax_attempts() {
		return max_attempts;
	}
	public void setMax_attempts(int max_attempts) {
		this.max_attempts = max_attempts;
	}
	public String getWindow() {
		return window;
	}
	public void setWindow(String window) {
		this.window = window;
	}
}

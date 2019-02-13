package com.test.quickstart;

public class RestartPolicy {
	private String condition;
	private String delay;
	private int max_attempts;
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

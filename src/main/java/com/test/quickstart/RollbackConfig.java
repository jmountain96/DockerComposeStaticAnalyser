package com.test.quickstart;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;

public class RollbackConfig {
	private int parallelism;
	@CheckStringFormat(message = "Invalid format for rollback config delay", value = ValidationEnums.CheckStringType.TIME)
	private String delay;
	@ContainsString(message = "Invalid failure action for rollback config", value = ValidationEnums.ContainsStringType.RBFAILUREACTION)
	private String failure_action;
	@CheckStringFormat(message = "Invalid format for rollback config delay", value = ValidationEnums.CheckStringType.TIME)
	private String monitor;
	private String max_failure_ratio;
	@ContainsString(message = "Invalid order for rollback config", value = ValidationEnums.ContainsStringType.CONFIGORDER)
	private String order;
	public int getParallelism() {
		return parallelism;
	}
	public void setParallelism(int parallelism) {
		this.parallelism = parallelism;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public String getFailure_action() {
		return failure_action;
	}
	public void setFailure_action(String failure_action) {
		this.failure_action = failure_action;
	}
	public String getMonitor() {
		return monitor;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	public String getMax_failure_ratio() {
		return max_failure_ratio;
	}
	public void setMax_failure_ratio(String max_failure_ratio) {
		this.max_failure_ratio = max_failure_ratio;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}

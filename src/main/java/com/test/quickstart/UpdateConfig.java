package com.test.quickstart;

public class UpdateConfig {
	private int parallelism;
	private String delay;
	private String failure_action;
	private String monitor;
	private String max_failure_ration;
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
	public String getMax_failure_ration() {
		return max_failure_ration;
	}
	public void setMax_failure_ration(String max_failure_ration) {
		this.max_failure_ration = max_failure_ration;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}

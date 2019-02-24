package com.test.quickstart;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;

public class Limits {
	@CheckStringFormat( message = "Cpus must be a number for deploy memory limits", value = ValidationEnums.CheckStringType.INTEGER)
	private String cpus;
	@CheckStringFormat( message = "Invalid memory format for deploy memory limits", value = ValidationEnums.CheckStringType.MEMORY)
	private String memory;
	public String getCpus() {
		return cpus;
	}
	public void setCpus(String cpus) {
		this.cpus = cpus;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
}

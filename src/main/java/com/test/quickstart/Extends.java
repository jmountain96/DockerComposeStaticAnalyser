package com.test.quickstart;

import com.test.quickstart.Validation.Interfaces.CheckFileExists;

public class Extends {
	@CheckFileExists(message = "Extends file cannot be found", value = "")
	private String file;
	private String service;
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
}

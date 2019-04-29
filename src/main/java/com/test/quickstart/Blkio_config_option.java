package com.test.quickstart;

import com.test.quickstart.Validation.Interfaces.CheckFolderExists;

public class Blkio_config_option {
	@CheckFolderExists(message = "Blkio config path points to a directory that cannot be found")
	private String path;
	private String rate;
	private String weight;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
}

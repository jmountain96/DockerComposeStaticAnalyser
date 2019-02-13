package com.test.quickstart;

import java.util.Map;

public class Ulimits {
	private String nproc;
	private Map<String,String> nofile;
	public String getNproc() {
		return nproc;
	}
	public void setNproc(String nproc) {
		this.nproc = nproc;
	}
	public Map<String, String> getNofile() {
		return nofile;
	}
	public void setNofile(Map<String, String> nofile) {
		this.nofile = nofile;
	}
}

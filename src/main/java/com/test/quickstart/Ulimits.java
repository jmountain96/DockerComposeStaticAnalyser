package com.test.quickstart;

import java.util.Map;

public class Ulimits {
	private String nproc;
	private Map<String,Integer> nofile;
	public String getNproc() {
		return nproc;
	}
	public void setNproc(String nproc) {
		this.nproc = nproc;
	}
	public Map<String, Integer> getNofile() {
		return nofile;
	}
	public void setNofile(Map<String, Integer> nofile) {
		this.nofile = nofile;
	}
}

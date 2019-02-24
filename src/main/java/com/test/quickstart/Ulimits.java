package com.test.quickstart;

import java.util.Map;

public class Ulimits {
	private String nproc;
	private Nofile nofile;
	public String getNproc() {
		return nproc;
	}
	public void setNproc(String nproc) {
		this.nproc = nproc;
	}
	public Nofile getNofile() {
		return nofile;
	}
	public void setNofile(Nofile nofile) {
		this.nofile = nofile;
	}
}

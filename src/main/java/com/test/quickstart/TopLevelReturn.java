package com.test.quickstart;

public class TopLevelReturn {
	private String[] services;
	private String[] configs;
	private String[] networks;
	private String[] secrets;
	private String[] volumes;
	private String[] envList;
	public String[] getServices() {
		return services;
	}
	public void setServices(String[] services) {
		this.services = services;
	}
	public String[] getConfigs() {
		return configs;
	}
	public void setConfigs(String[] configs) {
		this.configs = configs;
	}
	public String[] getNetworks() {
		return networks;
	}
	public void setNetworks(String[] networks) {
		this.networks = networks;
	}
	public String[] getSecrets() {
		return secrets;
	}
	public void setSecrets(String[] secrets) {
		this.secrets = secrets;
	}
	public String[] getVolumes() {
		return volumes;
	}
	public void setVolumes(String[] volumes) {
		this.volumes = volumes;
	}
	public String[] getEnvList() {
		return envList;
	}
	public void setEnvList(String[] envList) {
		this.envList = envList;
	}
}

package com.test.quickstart;

import com.test.quickstart.Validation.Interfaces.CheckFileExists;

public class CredentialSpec {
	@CheckFileExists(message = "Credential_Spec file doesn't exist within the CredentialSpecs subdirectory", value = "\\CredentialsSpecs\\")
	private String file;
	@CheckFileExists(message = "Registry file doesn't exist within the Windows registry on the daemon's host", value = "HKLM\\SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion\\Virtualization\\Containers\\CredentialSpecs\\")
	private String registry;
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getRegistry() {
		return registry;
	}
	public void setRegistry(String registry) {
		this.registry = registry;
	}
}

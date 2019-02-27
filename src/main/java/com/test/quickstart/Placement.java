package com.test.quickstart;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.ListContainsString;

public class Placement {
	@ListContainsString(message = "Invalid constraint specified in deploy constraints", value = ValidationEnums.ListContainsStringType.CONSTRAINTS)
	@CheckDuplication(message = "Duplicate placement constraint detected")
	private String[] constraints;
	@ListContainsString(message = "Invalid preference specified in deploy preferences", value = ValidationEnums.ListContainsStringType.PLACEMENTPREF)
	@CheckDuplication(message = "Duplicate placement preference detected")
	private String[] preferences;
	public String[] getConstraints() {
		return constraints;
	}
	public void setConstraints(String[] constraints) {
		this.constraints = constraints;
	}
	public String[] getPreferences() {
		return preferences;
	}
	public void setPreferences(String[] preferences) {
		this.preferences = preferences;
	}
}

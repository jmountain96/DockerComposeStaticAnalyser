package com.test.quickstart;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.ContainsString;

public class VolumeV {
	@ContainsString(message = "Volume no copy flag must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String nocopy;

	public String getNocopy() {
		return nocopy;
	}

	public void setNocopy(String nocopy) {
		this.nocopy = nocopy;
	}
}

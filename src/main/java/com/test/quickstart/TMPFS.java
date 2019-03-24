package com.test.quickstart;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;

public class TMPFS {
	@CheckStringFormat(message = "Invalid memory size specified for volume tmpfs", value = ValidationEnums.CheckStringType.MEMORY)
	private String size;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}

package com.test.quickstart;

import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;
import com.test.quickstart.TypeEnums.Type;
import com.test.quickstart.Validation.ValidationEnums;
public class Healthcheck {
	private TypeConverter converter = new TypeConverter();
	private TypeResolver resolver = new TypeResolver();
	private Object test;
	@CheckDuplication(message = "Duplicate healthcare test detected")
	private String[] testSL;
	private String testS;
	private Type testType;
	@ContainsString(message = "Healthcheck test type is invalid", value = ValidationEnums.ContainsStringType.HEALTHCHECK)
	private String testFormat;
	@CheckStringFormat(message = "Invalid interval for healthcheck", value = ValidationEnums.CheckStringType.TIME)
	private String interval;
	@CheckStringFormat(message = "Invalid timeout for healthcheck", value = ValidationEnums.CheckStringType.TIME)
	private String timeout;
	private int retries;
	@CheckStringFormat(message = "Invalid start period for healthcheck", value = ValidationEnums.CheckStringType.TIME)
	private String start_period;
	@ContainsString(message = "Healthcheck disable must be a boolean value", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String disable;
	
	
	public TypeConverter getConverter() {
		return converter;
	}


	public void setConverter(TypeConverter converter) {
		this.converter = converter;
	}


	public Object getTest() {
		return test;
	}


	public void setTest(Object test) throws Exception {
		this.test = test;
		convertTest();
	}


	public String[] getTestSL() {
		return testSL;
	}


	public void setTestSL(String[] testSL) {
		this.testSL = testSL;
	}


	public String getTestS() {
		return testS;
	}


	public void setTestS(String testS) {
		this.testS = testS;
	}


	public Type getTestType() {
		return testType;
	}


	public void setTestType(Type testType) {
		this.testType = testType;
	}


	public String getInterval() {
		return interval;
	}


	public void setInterval(String interval) {
		this.interval = interval;
	}


	public String getTimeout() {
		return timeout;
	}


	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}


	public int getRetries() {
		return retries;
	}


	public void setRetries(int retries) {
		this.retries = retries;
	}


	public String getStart_period() {
		return start_period;
	}


	public void setStart_period(String start_period) {
		this.start_period = start_period;
	}


	public String getDisable() {
		return disable;
	}


	public void setDisable(String disable) {
		this.disable = disable;
	}


	private void convertTest() throws Exception
	{
		if(resolver.checkStringList(test))
		{
			testSL  = converter.convertStringList(test );
			testType = Type.STRINGlIST;
			testFormat = testSL[0];
		}
		else if(resolver.checkString(test))
		{
			testS = test.toString() ;
			testType = Type.STRING;
		}
		else 
		{
			throw new Exception("Unknown type for Healthcheck test");
		}
	}
}

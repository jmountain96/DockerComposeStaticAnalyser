package com.test.quickstart;

public class Healthcheck {
	private TypeConverter converter = new TypeConverter();;
	private Object test;
	private String[] testSL;
	private String testS;
	private String testType;
	private String interval;
	private String timeout;
	private String retries;
	private String start_period;
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


	public void setTest(Object test) {
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


	public String getTestType() {
		return testType;
	}


	public void setTestType(String testType) {
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


	public String getRetries() {
		return retries;
	}


	public void setRetries(String retries) {
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


	private void convertTest()
	{
		String tTest = test.toString();
		if(tTest .charAt(0) == '[')
		{
			testSL  = converter.convertStringList(tTest );
			testType = "String[]";
		}
		else 
		{
			testS = tTest ;
			testType = "String";
		}
	}
}

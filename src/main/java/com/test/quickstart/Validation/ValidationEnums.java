package com.test.quickstart.Validation;

public class ValidationEnums {

	public enum ContainsStringType
	{
		VERSION, ENDPOINT_MODE, DEPLOY_MODE, RESTART_POLICY_CONDITION, RBFAILUREACTION, 
		UCFAILUREACTION, CONFIGORDER, BOOLEAN, ISOLATION, HEALTHCHECK, NETWORK_MODE, PID, 
		PORT_PROTOCOL, PORT_MODE, VOLUME_TYPE, VOLUME_CONSISTENCY, LOGGING_DRIVER
	}
	public enum CheckStringType
	{
		IMAGE, MEMORY, INTEGER, TIME, DNS, DOMAIN, IPV6, PORT, MAC, UNIX_PERM,SYSLOG, ENV, SUBNET
	}
	public enum CheckStringListType
	{
		IMAGE, DNS, EXTRAHOST, PORT, ENV
	}
	public enum ListContainsStringType
	{
		CAP, CONSTRAINTS, PLACEMENTPREF
	}
	
}

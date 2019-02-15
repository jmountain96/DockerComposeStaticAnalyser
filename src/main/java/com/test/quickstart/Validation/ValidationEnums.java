package com.test.quickstart.Validation;

public class ValidationEnums {
	public enum capabilities
	{
		ALL, AUDIT_CONTROL,AUDIT_READ,AUDIT_WRITE,BLOCK_SUSPEND,CHOWN,
		DAC_OVERRIDE,DAC_READ_SEARCH,FOWNER,FSETID,IPC_LOCK,IPC_OWNER,
		KILL,LEASE,LINUX_IMMUTABLE,MAC_ADMIN,MKNOD,NET_ADMIN,NET_BIND_SERVICE,
		NET_BROADCAST,NET_RAW,SETGID,SETFCAP,SETPCAP,SETUID,SYS_ADMIN,SYS_BOOT,
		SYS_CHROOT,SYS_MODULE,SYS_NICE,SYS_PACCT,SYS_PTRACE,SYS_RAWIO,SYS_RESOURCE,
		SYS_TIME,SYS_TTY_CONFIG,SYSLOG,WAKE_ALARM
	}
	public enum deployEndpointMode
	{
		vip, dnsrr
	}
	public enum deployMode
	{
		global, replicated
	}
	public enum deployRestartPolicyCondition
	{
		none,failure,any
	}
	public enum deployRollbackConfigFailureAction //lower case this when called
	{
		CONTINUE,PAUSE
	}
	public enum deployRollbackConfigOrder
	{
		stopfirst,startfirst // BOTH DASHED
	}
	public enum healthcheckTest
	{
		NONE, CMD, CMDSHELL //CMD SHELL IS DASHED
	}
	public enum isolation //lower case when called
	{
		DEFAULT,PROCESS,HYPERV
	}
	public enum networkMode
	{
		bridge, host, overlay, macvlan, none
	}
	public enum portProtocol
	{
		tcp, udp
	}
	public enum restart
	{
		no, always, onfailure, unlessstopped //last two dashed
	}
	public enum volumeType
	{
		volume, bin, tmpfs
	}
	public enum volumeConsistency
	{
		consistent, cached, delegated
	}
	public enum ContainsStringType
	{
		VERSION, ENDPOINT_MODE, DEPLOY_MODE, RESTART_POLICY_CONDITION, RBFAILUREACTION, UCFAILUREACTION, CONFIGORDER
	}
	public enum CheckStringType
	{
		IMAGE, MEMORY, INTEGER, TIME, DNS, DOMAIN
	}
	public enum CheckStringListType
	{
		IMAGE, DNS
	}
	public enum ListContainsStringType
	{
		CAP, CONSTRAINTS, PLACEMENTPREF
	}
	
}

package com.test.quickstart;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckFileExists;
import com.test.quickstart.Validation.Interfaces.CheckFileListExists;
import com.test.quickstart.Validation.Interfaces.CheckFolderExists;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.CheckStringListFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;
import com.test.quickstart.Validation.Interfaces.Dependency;
import com.test.quickstart.Validation.Interfaces.ListContainsString;
import com.test.quickstart.Validation.ValidationEnums;
import javax.validation.constraints.Email;

public class TopLevel {
	private TypeConverter converter = new TypeConverter();
	
	private Object build;
	@CheckFolderExists(message = "Directory doesn't exist")
	private String buildS;
	private Build buildB;
	private String buildType;
	@ListContainsString(message = "unknown capability within cap_add", value = ValidationEnums.ListContainsStringType.CAP)
	@CheckDuplication(message = "Duplicate cap_add capability detected")
	private String[] cap_add;
	@ListContainsString(message = "unknown capability within cap_drop", value = ValidationEnums.ListContainsStringType.CAP)
	@CheckDuplication(message = "Duplicate cap_drop capability detected detected")
	private String[] cap_drop;
	private String cgroup_parent;
	private Object command;
	private String commandS;
	@CheckDuplication(message = "Duplicate command detected")
	private String[] commandSL;
	private String commandType;
	private Map<String,Configs> configs;
	private String container_name;
	private CredentialSpec credential_spec;
	@CheckStringListFormat(message = "Invalid format for devices", value = ValidationEnums.CheckStringListType.IMAGE)
	@CheckDuplication(message = "Duplicate device detected")
	private String[] devices;
	@CheckStringFormat(message = "Invalid format for domain name", value = ValidationEnums.CheckStringType.DOMAIN)
	private String domainname;
	private Object dns;
	@CheckStringFormat(message = "Invalid format for dns", value = ValidationEnums.CheckStringType.DNS)
	private String dnsS;
	@CheckStringListFormat(message = "Invalid format for dns", value = ValidationEnums.CheckStringListType.DNS)
	@CheckDuplication(message = "Duplicate dns detected")
	private String[] dnsSL;
	private String dnsType;
	@CheckStringFormat(message = "Invalid format for dns", value = ValidationEnums.CheckStringType.DOMAIN)
	private String dns_search;
	private Object entrypoint;
	@CheckDuplication(message = "Duplicate entrypoint detected")
	private String[] entrypointSL;
	private String entrypointS;
	private String entrypointType;
	private Object env_file;
	@CheckStringListFormat(message = "Invalid format for env file within env file list", value = ValidationEnums.CheckStringListType.ENV)
	@CheckFileListExists(message = "One or more files within the env file list cannot be found")
	@CheckDuplication(message = "Duplicate env_file detected")
	private String[] env_fileSL;
	@CheckStringFormat(message = "Invalid format for env file ", value = ValidationEnums.CheckStringType.ENV)
	@CheckFileExists(message = "Specified enviroment file cannot be found", value = "")
	private String env_fileS;
	private String env_FileType;
	private Object environment;
	private Map<String,String> environmentM;
	@CheckDuplication(message = "Duplicate environment detected")
	private String[] environmentSL;
	private String environmentType;
	@CheckStringListFormat(message = "Invalid port target", value = ValidationEnums.CheckStringListType.PORT)
	@CheckDuplication(message = "Duplicate port detected")
	private String[] expose;
	@CheckDuplication(message = "Duplicate external link detected")
	private String[] external_links;
	@CheckStringListFormat(message = "extra hosts doesn't specify valid host addresses", value = ValidationEnums.CheckStringListType.EXTRAHOST)
	@CheckDuplication(message = "Duplicate extra host detected")
	private String[] extra_hosts;
	private Healthcheck healthcheck;
	private String hostname;
	private String image;
	private String ipc;
	@ContainsString(message = "Isolation must be default, process or hyperv", value = ValidationEnums.ContainsStringType.ISOLATION) 
	private String isolation;
	private Object labels;
	private Map<String,String> labelsM;
	@CheckDuplication(message = "Duplicate label detected")
	private String[] labelsS;
	private String labelType;
	@CheckStringFormat(message = "mac_address must be a valid memory format", value = ValidationEnums.CheckStringType.MAC)
	private String mac_address;
	private Object networks;
	@CheckDuplication(message = "Duplicate network detected")
	private String[] networksSL;
	private Map<String, Network> networksN;
	private String networkType;
	@ContainsString(message = "Invalid network type", value = ValidationEnums.ContainsStringType.NETWORK_MODE)
	private String network_mode;
	@ContainsString(message  = "Invalid PID mode", value = ValidationEnums.ContainsStringType.PID)
	private String pid;
	private Object ports;
	@CheckStringListFormat(message = "Invalid port target", value = ValidationEnums.CheckStringListType.PORT)
	@CheckDuplication(message = "Duplicate port detected")
	private String[] portsSL;
	private Ports[] portsP; 
	private String portsType;
	@ContainsString(message = "Priveleged must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String privileged;
	@ContainsString(message = "Read only must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String read_only;
	private String restart;
	private Object secrets;
	@CheckDuplication(message = "Duplicate secret detected")
	private String[] secretsL;
	private Secrets[] secretsSL;
	private String secretsType;
	private String[] security_opt;
	@CheckStringFormat(message = "shm_size must be a valid memory format", value = ValidationEnums.CheckStringType.MEMORY)
	private String shm_size;
	@ContainsString(message = "stdin_open must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String stdin_open;
	@CheckStringFormat(message = "Invalid time specified for stop grace period", value = ValidationEnums.CheckStringType.TIME)
	private String stop_grace_period;
	private String stop_signal;
	private Object sysctls;
	private Map<String, String> sysctlsM;
	@CheckDuplication(message = "Duplicate sysctls detected")
	private String[] sysctlsSL;
	private String sysctlsType;
	private Object tmpfs;
	@CheckDuplication(message = "Duplicate tmpfs detected")
	private String[] tmpfsSL;
	private String tmpfsS;
	private String tmpfsType;
	@ContainsString(message = "tty must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String tty;
	private Ulimits ulimits;
	private String user;
	private String userns_mode;
	private Map<String, Volume> volumes;
	@CheckFolderExists(message = "Working directory folder doesn't exist")
	private String working_dir;
	@ContainsString(value = ValidationEnums.ContainsStringType.VERSION, message = "Missing Docker Compose Version, compiler will assume version 1 of Compose is being used")
	private String version;
	private Map<String,Service> services;
	@Dependency(message = "Service references a network mode for a service that isn't present")
	private Dependencies NetworkModeDependencies = new Dependencies();
	
	
	
	public TypeConverter getConverter() {
		return converter;
	}
	public void setConverter(TypeConverter converter) {
		this.converter = converter;
	}
	public Object getBuild() {
		return build;
	}
	public void setBuild(Object build) {
		this.build = build;
		if(converter.checkString(build) == true)
		{
			this.buildS = build.toString();
			this.buildType = "String";
		}
		else 
		{
			this.buildB = converter.convertBuild(build);
			this.buildType = "Build";
		}
	}
	public String getBuildS() {
		return buildS;
	}
	public void setBuildS(String buildS) {
		this.buildS = buildS;
	}
	public Build getBuildB() {
		return buildB;
	}
	public void setBuildB(Build buildB) {
		this.buildB = buildB;
	}
	public String getBuildType() {
		return buildType;
	}
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	public String[] getCap_add() {
		return cap_add;
	}
	public void setCap_add(String[] cap_add) {
		this.cap_add = cap_add;
	}
	public String[] getCap_drop() {
		return cap_drop;
	}
	public void setCap_drop(String[] cap_drop) {
		this.cap_drop = cap_drop;
	}
	public String getCgroup_parent() {
		return cgroup_parent;
	}
	public void setCgroup_parent(String cgroup_parent) {
		this.cgroup_parent = cgroup_parent;
	}
	public Object getCommand() {
		return command;
	}
	public void setCommand(Object command) {
		this.command = command;
		convertCommand();
	}
	public String getCommandS() {
		return commandS;
	}
	public void setCommandS(String commandS) {
		this.commandS = commandS;
	}
	public String[] getCommandSL() {
		return commandSL;
	}
	public void setCommandSL(String[] commandSL) {
		this.commandSL = commandSL;
	}
	public String getCommandType() {
		return commandType;
	}
	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}
	public Map<String, Configs> getConfigs() {
		return configs;
	}
	public void setConfigs(Map<String, Configs> configs) {
		this.configs = configs;
	}
	public String getContainer_name() {
		return container_name;
	}
	public void setContainer_name(String container_name) {
		this.container_name = container_name;
	}
	public CredentialSpec getCredential_spec() {
		return credential_spec;
	}
	public void setCredential_spec(CredentialSpec credential_spec) {
		this.credential_spec = credential_spec;
	}
	public String[] getDevices() {
		return devices;
	}
	public void setDevices(String[] devices) {
		this.devices = devices;
	}
	public Object getDns() {
		return dns;
	}
	public void setDns(Object dns) {
		this.dns = dns;
		convertDNS();
	}
	public String getDnsS() {
		return dnsS;
	}
	public void setDnsS(String dnsS) {
		this.dnsS = dnsS;
	}
	public String[] getDnsSL() {
		return dnsSL;
	}
	public void setDnsSL(String[] dnsSL) {
		this.dnsSL = dnsSL;
	}
	public String getDnsType() {
		return dnsType;
	}
	public void setDnsType(String dnsType) {
		this.dnsType = dnsType;
	}
	public String getDns_search() {
		return dns_search;
	}
	public void setDns_search(String dns_search) {
		this.dns_search = dns_search;
	}
	public Healthcheck getHealthcheck() {
		return healthcheck;
	}
	public void setHealthcheck(Healthcheck healthcheck) {
		this.healthcheck = healthcheck;
	}
	public String getDomainname() {
		return dns_search;
	}
	public void setDomainname(String dns_search) {
		this.dns_search = dns_search;
	}
	public Object getEntrypoint() {
		return entrypoint;
	}
	public void setEntrypoint(Object entrypoint) {
		this.entrypoint = entrypoint;
		convertEntrypoint();
	}
	public String[] getEntrypointSL() {
		return entrypointSL;
	}
	public void setEntrypointSL(String[] entrypointSL) {
		this.entrypointSL = entrypointSL;
	}
	public String getEntrypointS() {
		return entrypointS;
	}
	public void setEntrypointS(String entrypointS) {
		this.entrypointS = entrypointS;
	}
	public String getEntrypointType() {
		return entrypointType;
	}
	public void setEntrypointType(String entrypointType) {
		this.entrypointType = entrypointType;
	}
	public Object getEnv_file() {
		return env_file;
	}
	public void setEnv_file(Object env_file) {
		this.env_file = env_file;
		convertEnv_file();
	}
	public String[] getEnv_fileSL() {
		return env_fileSL;
	}
	public void setEnv_fileSL(String[] env_fileSL) {
		this.env_fileSL = env_fileSL;
	}
	public String getEnv_fileS() {
		return env_fileS;
	}
	public void setEnv_fileS(String env_fileS) {
		this.env_fileS = env_fileS;
	}
	public String getEnv_FileType() {
		return env_FileType;
	}
	public void setEnv_FileType(String env_FileType) {
		this.env_FileType = env_FileType;
	}
	public Object getEnvironment() {
		return environment;
	}
	public void setEnvironment(Object environment) {
		this.environment = environment;
		convertEnvironment();
	}
	public Map<String, String> getEnvironmentM() {
		return environmentM;
	}
	public void setEnvironmentM(Map<String, String> environmentM) {
		this.environmentM = environmentM;
	}
	public String[] getEnvironmentSL() {
		return environmentSL;
	}
	public void setEnvironmentSL(String[] environmentSL) {
		this.environmentSL = environmentSL;
	}
	public String getEnvironmentType() {
		return environmentType;
	}
	public void setEnvironmentType(String environmentType) {
		this.environmentType = environmentType;
	}
	public String[] getExpose() {
		return expose;
	}
	public void setExpose(String[] expose) {
		this.expose = expose;
	}
	public String[] getExternal_links() {
		return external_links;
	}
	public void setExternal_links(String[] external_links) {
		this.external_links = external_links;
	}
	public String[] getExtra_hosts() {
		return extra_hosts;
	}
	public void setExtra_hosts(String[] extra_hosts) {
		this.extra_hosts = extra_hosts;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getIpc() {
		return ipc;
	}
	public void setIpc(String ipc) {
		this.ipc = ipc;
	}
	public String getIsolation() {
		return isolation;
	}
	public void setIsolation(String isolation) {
		this.isolation = isolation;
	}
	public Object getLabels() {
		return labels;
	}
	public void setLabels(Object labels) {
		this.labels = labels;
		convertLabels();
	}
	public Map<String, String> getLabelsM() {
		return labelsM;
	}
	public void setLabelsM(Map<String, String> labelsM) {
		this.labelsM = labelsM;
	}
	public String[] getLabelsS() {
		return labelsS;
	}
	public void setLabelsS(String[] labelsS) {
		this.labelsS = labelsS;
	}
	public String getLabelType() {
		return labelType;
	}
	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	public Object getNetworks() {
		return networks;
	}
	public void setNetworks(Object networks) {
		this.networks = networks;
		convertNetworks();
	}
	public String[] getNetworksSL() {
		return networksSL;
	}
	public void setNetworksSL(String[] networksSL) {
		this.networksSL = networksSL;
	}
	public Map<String, Network> getNetworksN() {
		return networksN;
	}
	public void setNetworksN(Map<String, Network> networksN) {
		this.networksN = networksN;
	}
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public String getNetwork_mode() {
		return network_mode;
	}
	public void setNetwork_mode(String network_mode) {
		this.network_mode = network_mode;
		
	}
	public Dependencies getNetworkModeDependencies() {
		return NetworkModeDependencies;
	}
	public void setNetworkModeDependencies() {
		if(this.network_mode.startsWith("service:") == true)
		{
			NetworkModeDependencies.dependents[0] = this.network_mode.substring(this.network_mode.lastIndexOf("="));
		}
		NetworkModeDependencies.target = this.services.keySet().toArray(new String[services.size()] );
		
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Object getPorts() {
		return ports;
	}
	public void setPorts(Object ports) {
		this.ports = ports;
		ArrayList<Map<String, Object>> portsAL = null;
		if(converter.checkStringList(ports) == true)
		{
			this.portsSL = converter.convertStringList(ports);
			this.portsType = "String[]";
		}
		else 
		{
			portsAL = (ArrayList<Map<String, Object>>)ports;
			portsP = converter.convertPorts(portsAL);
			this.portsType = "Ports[]";
		}
	}
	public String[] getPortsSL() {
		return portsSL;
	}
	public void setPortsSL(String[] portsSL) {
		this.portsSL = portsSL;
	}
	public Ports[] getPortsP() {
		return portsP;
	}
	public void setPortsP(Ports[] portsP) {
		this.portsP = portsP;
	}
	public String getPortsType() {
		return portsType;
	}
	public void setPortsType(String portsType) {
		this.portsType = portsType;
	}
	public String getPrivileged() {
		return privileged;
	}
	public void setPrivileged(String privileged) {
		this.privileged = privileged;
	}
	public String getRead_only() {
		return read_only;
	}
	public void setRead_only(String read_only) {
		this.read_only = read_only;
	}
	public String getRestart() {
		return restart;
	}
	public void setRestart(String restart) {
		this.restart = restart;
	}
	public Object getSecrets() {
		return secrets;
	}
	public void setSecrets(Object secrets) {
		this.secrets = secrets;
		Map<String, Map<String,Object>> secretsAL = null;;
		if(converter.checkStringList(secrets) == true)
		{
			this.secretsL = converter.convertStringList(secrets);
			this.secretsType = "String[]";
		}
		else 
		{
			secretsAL = (Map<String, Map<String,Object>>)secrets;
			secretsSL = converter.convertSecrets(secretsAL);
			this.secretsType = "Secrets[]";
		}
	}
	public String[] getSecretsL() {
		return secretsL;
	}
	public void setSecretsL(String[] secretsL) {
		this.secretsL = secretsL;
	}
	public Secrets[] getSecretsSL() {
		return secretsSL;
	}
	public void setSecretsSL(Secrets[] secretsSL) {
		this.secretsSL = secretsSL;
	}
	public String getSecretsType() {
		return secretsType;
	}
	public void setSecretsType(String secretsType) {
		this.secretsType = secretsType;
	}
	public String[] getSecurity_opt() {
		return security_opt;
	}
	public void setSecurity_opt(String[] security_opt) {
		this.security_opt = security_opt;
	}
	public String getShm_size() {
		return shm_size;
	}
	public void setShm_size(String shm_size) {
		this.shm_size = shm_size;
	}
	public String getStdin_open() {
		return stdin_open;
	}
	public void setStdin_open(String stdin_open) {
		this.stdin_open = stdin_open;
	}
	public String getStop_grace_period() {
		return stop_grace_period;
	}
	public void setStop_grace_period(String stop_grace_period) {
		this.stop_grace_period = stop_grace_period;
	}
	public String getStop_signal() {
		return stop_signal;
	}
	public void setStop_signal(String stop_signal) {
		this.stop_signal = stop_signal;
	}
	public Object getSysctls() {
		return sysctls;
	}
	public void setSysctls(Object sysctls) {
		this.sysctls = sysctls;
		convertSysctls();
	}
	public Map<String, String> getSysctlsM() {
		return sysctlsM;
	}
	public void setSysctlsM(Map<String, String> sysctlsM) {
		this.sysctlsM = sysctlsM;
	}
	public String[] getSysctlsSL() {
		return sysctlsSL;
	}
	public void setSysctlsSL(String[] sysctlsSL) {
		this.sysctlsSL = sysctlsSL;
	}
	public String getSysctlsType() {
		return sysctlsType;
	}
	public void setSysctlsType(String sysctlsType) {
		this.sysctlsType = sysctlsType;
	}
	public String[] getTmpfsSL() {
		return tmpfsSL;
	}
	public void setTmpfsSL(String[] tmpfsSL) {
		this.tmpfsSL = tmpfsSL;
	}
	public String getTmpfsS() {
		return tmpfsS;
	}
	public void setTmpfsS(String tmpfsS) {
		this.tmpfsS = tmpfsS;
	}
	public String getTmpfsType() {
		return tmpfsType;
	}
	public void setTmpfsType(String tmpfsType) {
		this.tmpfsType = tmpfsType;
	}
	public void setTmpfs(Object tmpfs) {
		this.tmpfs = tmpfs;
		convertTmpfs();
	}
	public Object getTmpfs() {
		return tmpfs;
	}
	public String getTty() {
		return tty;
	}
	public void setTty(String tty) {
		this.tty = tty;
	}
	public Ulimits getUlimits() {
		return ulimits;
	}
	public void setUlimits(Ulimits ulimits) {
		this.ulimits = ulimits;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserns_mode() {
		return userns_mode;
	}
	public void setUserns_mode(String userns_mode) {
		this.userns_mode = userns_mode;
	}
	public Map<String, Volume> getVolumes() {
		return volumes;
	}
	public void setVolumes(Map<String, Volume> volumes) {
		this.volumes = volumes;
	}
	public String getWorking_dir() {
		return working_dir;
	}
	public void setWorking_dir(String working_dir) {
		this.working_dir = working_dir;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Map<String,Service> getServices() {
		return services;
	}
	public void setServices(Map<String,Service> services) {
		this.services = services;
		for (Entry<String, Service> service : this.services.entrySet())
		{
			service.getValue().setName(service.getKey());
		}
	}
	private void convertCommand() 
	{
		boolean set = false;
		String tCommand = command.toString();
		if(tCommand.charAt(0) == '[')
		{
			commandSL = converter.convertStringList(tCommand);
			commandType = "String[]";
			set = true;
		}
		else 
			
		{
			if(set == false)
			{
				commandS = tCommand;
				commandType = "String";
			}
		}	
	}
	private void convertDNS()
	{
		boolean set = false;
		String tDNS = dns.toString();
		if(tDNS.charAt(0) == '[')
		{
			dnsSL = converter.convertStringList(tDNS);
			dnsType = "String[]";
			set = true;
		}
		else 
			
				
		{
			if(set == false){
			dnsS = tDNS;
			dnsType = "String";
		}
				}
	}
	private void convertEntrypoint()
	{
		boolean set = false;
		String tEntrypoint = entrypoint.toString();
		if(tEntrypoint .charAt(0) == '[')
		{
			entrypointSL  = converter.convertStringList(tEntrypoint );
			entrypointType = "String[]";
			set = true;
		}
		else 
		{
			if(set == false){
			entrypointS = tEntrypoint ;
			entrypointType = "String";
		}
		}
	}
	private void convertEnv_file()
	{
		boolean set = false;
		String tEnv_file = env_file.toString();
		if(tEnv_file .charAt(0) == '[')
		{
			env_fileSL  = converter.convertStringList(tEnv_file );
			env_FileType = "String[]";
			set = true;
		}
		else 
		{
			if(set == false){
			env_fileS = tEnv_file ;
			env_FileType = "String";
		}
		}
	}
	private void convertEnvironment() 
	{
		boolean set = false;
		try {
			environmentM = converter.convertMap(environment);
			environmentType = "Map<String,String>";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				environmentSL = converter.convertStringList(environment);
				environmentType = "String[]";
			}
		}
	}
	private void convertLabels() 
	{
		boolean set = false;
		try {
			labelsM = converter.convertMap(labels);
			labelType = "Map<String,String>";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				labelsS = converter.convertStringList(labels);
				labelType = "String[]";
			}
		}
	}
	private void convertNetworks() 
	{
		Map<String, Map<String,Object>> networksAL = null;;
		if(converter.checkStringList(networks) == true)
		{
			this.networksSL = converter.convertStringList(networks);
			this.secretsType = "String[]";
		}
		else 
		{
			networksAL = (Map<String, Map<String,Object>>)networks;
			networksN = converter.convertNetworks(networksAL);
			this.secretsType = "Map<String,Network>";
		}
	}
	private void convertSysctls()
	{
		boolean set = false;
		try {
			sysctlsM = converter.convertMap(sysctls);
			sysctlsType = "Map<String,String>";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				sysctlsSL = converter.convertStringList(sysctls);
				sysctlsType = "String[]";
			}
		}
	}
	private void convertTmpfs() 
	{
		boolean set = false;
		String tTmpfs = tmpfs.toString();
		if(tTmpfs.charAt(0) == '[')
		{
			tmpfsSL = converter.convertStringList(tmpfs);
			tmpfsType = "String[]";
			set = true;
		}
		else 
			
		{
			if(set == false)
			{
				tmpfsS = tTmpfs;
				tmpfsType = "String";
			}
		}	
	}
	public TopLevelReturn accept(YamlParserVisitor parser)
	{
		return parser.visit(this);
	}
	
}

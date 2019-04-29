package com.test.quickstart;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.quickstart.TypeEnums.Type;
import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckFileExists;
import com.test.quickstart.Validation.Interfaces.CheckFolderExists;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.CheckStringListFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;
import com.test.quickstart.Validation.Interfaces.Dependency;
import com.test.quickstart.Validation.Interfaces.ListContainsString;



public class Service {
	private TypeConverter Converter = new TypeConverter();
	private TypeResolver resolver = new TypeResolver();
	private String Name;
	private Blkio_config blkio_config;
	private Object build;
	@CheckFolderExists(message = "Specified build directory cannot be found")
	private String buildS;
	private Build buildB;
	private Type buildType;
	@ListContainsString(message = "Unknown capability within cap_add", value = ValidationEnums.ListContainsStringType.CAP)
	@CheckDuplication(message = "Duplicate cap_add capability detected")
	private String[] cap_add;
	@ListContainsString(message = "Unknown capability within cap_drop", value = ValidationEnums.ListContainsStringType.CAP)
	@CheckDuplication(message = "Duplicate cap_drop capability detected")
	private String[] cap_drop;
	private String cgroup_parent;
	private Object command;
	private String commandS;
	@CheckDuplication(message = "Duplicate command detected")
	private String[] commandSL;
	private Type commandType;
	private String container_name;
	private Object configs;
	private Configs[] configsC;
	@CheckDuplication(message = "Duplicate service config detected")
	private String[] configsSL;
	private CredentialSpec credential_spec;
	private Type configType;
	private String cpu_count;
	private String cpu_shares;
	private String cpu_quota;
	private String cpuset;
	private String cpus;
	@CheckStringFormat(message = "Invalid time format for cpu rt runtime", value = ValidationEnums.CheckStringType.TIME)
	private String cpu_rt_runtime;
	@CheckStringFormat(message = "Invalid time format for cpu rt period", value = ValidationEnums.CheckStringType.TIME)
	private String cpu_rt_period;
	@CheckStringFormat(message = "Invalid time format for cpu period", value = ValidationEnums.CheckStringType.TIME)
	private String cpu_period;
	private String cpu_percent;
	private String oom_kill_disable;
	private String oom_score_adj;
	private String device_cgroup_rules;
	private String mem_limit;
	@CheckStringFormat(message = "mem reservation isn't a valid memory format", value = ValidationEnums.CheckStringType.MEMORY)
	private String mem_reservation;
	private String memswap_limit;
	private String mem_swappiness;
	private String pids_limit;
	private String platform;
	private Object depends_on;
	private Map<String,Condition> depends_onM;
	private String[] depends_onS;
	private Type depends_onType;
	private Deploy deploy;
	@CheckStringListFormat(message = "Invalid format for devices", value = ValidationEnums.CheckStringListType.IMAGE)
	@CheckDuplication(message = "Duplicate device detected")
	private String[] devices;
	private Object dns;
	@CheckStringFormat(message = "Invalid format of dns field specified within the top level", value = ValidationEnums.CheckStringType.DNS)
	private String dnsS;
	@CheckStringListFormat(message = "Invalid format for dns", value = ValidationEnums.CheckStringListType.DNS)
	@CheckDuplication(message = "Duplicate dns detected")
	private String[] dnsSL;
	private Type dnsType;
	private Object dns_search;
	@CheckStringFormat(message = "Invalid format for dns", value = ValidationEnums.CheckStringType.DOMAIN)
	private String dns_searchS;
	private String[] dns_searchSL;
	private Type dns_searchType;
	@CheckDuplication(message = "Duplicate dns_opt detected")
	private String[] dns_opt;
	@CheckStringFormat(message = "Invalid format for domain name", value = ValidationEnums.CheckStringType.DOMAIN)
	private String domainname;
	private Object entrypoint;
	@CheckDuplication(message = "Duplicate entrypoint detected")
	private String[] entrypointSL;
	private String entrypointS;
	private Type entrypointType;
	private Object env_file;
	@CheckDuplication(message = "Duplicate service env_file detected")
	private String[] env_fileSL;
	private String env_fileType;
	private Object environment;
	private Map<String,String> environmentM;
	@CheckDuplication(message = "Duplicate environment detected")
	private String[] environmentSL;
	private Type environmentType;
	@CheckStringListFormat(message = "Invalid port target", value = ValidationEnums.CheckStringListType.PORT)
	@CheckDuplication(message = "Duplicate port detected")
	private String[] expose;
	@CheckDuplication(message = "Duplicate external link detected")
	private String[] external_links;
	@CheckStringListFormat(message = "extra hosts doesn't specify valid host addresses", value = ValidationEnums.CheckStringListType.EXTRAHOST)
	@CheckDuplication(message = "Duplicate extra host detected")
	private String[] extra_hosts;
	@JsonProperty("extends")
	private Extends Extends;
	private Healthcheck healthcheck;
	private String hostname;
	private Object labels;
	private Map<String,String> labelsM;
	@CheckDuplication(message = "Duplicate service lable detected")
	private String[] labelsS;
	private Type labelType;
	private String image;
	@CheckFileExists(message = "Either the service init isn't the value 'true' or the docker-init file specfied by the service init cannot be found", value = "")
	private String init; 
	private String ipc;
	@ContainsString(message = "Isolation must be default, process or hyperv", value = ValidationEnums.ContainsStringType.ISOLATION) 
	private String isolation;
	@CheckDuplication(message = "Duplicate service link detected")
	private String[] links;
	private Logging logging;
	private String[] group_add;
	@CheckStringFormat(message = "mac_address must be a valid mac address format", value = ValidationEnums.CheckStringType.MAC)
	private String mac_address;
	@ContainsString(message = "Invalid network type ", value = ValidationEnums.ContainsStringType.NETWORK_MODE)
	private String network_mode;
	private Object networks;
	@CheckDuplication(message = "Duplicate service network detected")
	private String[] networksSL;
	private Map<String,Network> networksN;
	private Type networkType;
	@ContainsString(message  = "Invalid PID mode", value = ValidationEnums.ContainsStringType.PID)
	private String pid;
	@ContainsString(message = "Invalid restart condition", value = ValidationEnums.ContainsStringType.RESTART_POLICY_CONDITION)
	private String restart;
	private String runtime;
	private String scale;
	private Object secrets;
	@CheckDuplication(message = "Duplicate secret detected")
	private String[] secretsL;
	private Secrets[] secretsSL;
	private Type secretsType;
	@ContainsString(message = "stdin_open must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String stdin_open;
	@ContainsString(message = "tty must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String tty;
	private Object ports;
	@CheckDuplication(message = "Duplicate service port detected")
	private String[] portsSL;
	private Ports[] portsP; 
	private Type portsType;
	@ContainsString(message = "Priveleged must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String privileged;
	@ContainsString(message = "Read only must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String read_only;
	private String[] security_opt;
	@CheckStringFormat(message = "shm_size must be a valid memory format", value = ValidationEnums.CheckStringType.MEMORY)
	private String shm_size;
	@CheckStringFormat(message = "Invalid time specified for stop grace period", value = ValidationEnums.CheckStringType.TIME)
	private String stop_grace_period;
	private String stop_signal;
	private Object sysctls;
	private Map<String, String> sysctlsM;
	@CheckDuplication(message = "Duplicate sysctls detected")
	private String[] sysctlsSL;
	private Type sysctlsType;
	private Object tmpfs;
	@CheckDuplication(message = "Duplicate tmpfs detected")
	private String[] tmpfsSL;
	private String tmpfsS;
	private Type tmpfsType;
	private Ulimits ulimits;
	private String user;
	private String userns_mode;
	private String volume_driver;
	private Object volumes;
	private Volume[] volumesVL;
	@CheckDuplication(message = "Duplicate service volume detected")
	private String[] volumesSL;
	private Type volumeType;
	private String[] volumes_from;
	@CheckFolderExists(message = "Working directory folder doesn't exist")
	private String working_dir;
	@Dependency(message = "Service depends on config that isn't present")
	private Dependencies ConfigDependencies = new Dependencies();;
	@Dependency(message = "Service depends on another service that isn't present")
	private Dependencies ServiceDependencies = new Dependencies();
	@Dependency(message = "Service links another service that isn't present")
	private Dependencies LinkDependencies = new Dependencies();
	@Dependency(message = "Service references a network that isn't present")
	private Dependencies NetworkDependencies = new Dependencies();
	@Dependency(message = "Service references a sercret that isn't present")
	private Dependencies SecretDependencies = new Dependencies();
	@Dependency(message = "Service references a volume that isn't present")
	private Dependencies VolumeDependencies = new Dependencies();
	@Dependency(message = "Service references a network mode for a service that isn't present")
	private Dependencies NetworkModeDependencies = new Dependencies();
	
	public TypeConverter getConverter() {
		return Converter;
	}
	public void setConverter(TypeConverter converter) {
		Converter = converter;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Blkio_config getBlkio_config() {
		return blkio_config;
	}
	public void setBlkio_config(Blkio_config blkio_config) {
		this.blkio_config = blkio_config;
	}
	public Object getBuild() {
		return build;
	}
	public void setBuild(Object build) {
		this.build = build;
		if(resolver.checkString(build) == true)
		{
			this.buildS = build.toString();
			this.buildType = Type.STRING;
		}
		else 
		{
			this.buildB = Converter.convertBuild(build);
			this.buildType = Type.BUILD;
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
	public Type getBuildType() {
		return buildType;
	}
	public void setBuildType(Type buildType) {
		this.buildType = buildType;
	}
	public Object getCommand() {
		return command;
	}
	public void setCommand(Object command) throws Exception {
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
	public Type getCommandType() {
		return commandType;
	}
	public void setCommandType(Type commandType) {
		this.commandType = commandType;
	}
	public String getContainer_name() {
		return container_name;
	}
	public void setContainer_name(String container_name) {
		this.container_name = container_name;
	}
	public Object getConfigs() {
		return configs;
	}
	@SuppressWarnings("unchecked")
	public void setConfigs(Object configs) throws Exception {
		this.configs = configs;
		ArrayList<Map<String, Object>> configAL = null;
		if(resolver.checkMapList(configs) == true)
		{
			configAL = (ArrayList<Map<String, Object>>)configs;
			configsC = Converter.convertConfigsList(configAL);
			this.configType = Type.CONFIGSLIST;
		}
		else if(resolver.checkStringList(configs) == true)
		{
			this.configsSL = Converter.convertStringList(configs);
			this.configType = Type.STRINGlIST;
		}
		else 
		{
			throw new Exception("Unknown type for service config");
		}
	
	}
	public Configs[] getConfigsC() {
		return configsC;
	}
	public void setConfigsC(Configs[] configsC) {
		this.configsC = configsC;
	}
	public String[] getConfigsSL() {
		return configsSL;
	}
	public void setConfigsSL(String[] configsSL) {
		this.configsSL = configsSL;
	}
	public Type getConfigType() {
		return configType;
	}
	public void setConfigType(Type configType) {
		this.configType = configType;
	}
	public Dependencies getConfigDependencies() {
		return ConfigDependencies;
	}
	public void setConfigDependencies(String[] configList) {
		if(this.configType == Type.STRINGlIST)
		{
			this.ConfigDependencies.dependents = this.configsSL;
		}
		else 
		{
			String[] configSources = new String[configsC.length];
			for(int i = 0 ; i < configsC.length ; i++)
			{
				configSources[i] = configsC[i].getSource();
			}
			this.ConfigDependencies.dependents = configSources;
		}
		this.ConfigDependencies.target = configList;
	}
	public String getCpu_shares() {
		return cpu_shares;
	}
	public void setCpu_shares(String cpu_shares) {
		this.cpu_shares = cpu_shares;
	}
	public String getCpu_quota() {
		return cpu_quota;
	}
	public void setCpu_quota(String cpu_quota) {
		this.cpu_quota = cpu_quota;
	}
	public String getCpuset() {
		return cpuset;
	}
	public void setCpuset(String cpuset) {
		this.cpuset = cpuset;
	}
	public String getCpu_count() {
		return cpu_count;
	}
	public void setCpu_count(String cpu_count) {
		this.cpu_count = cpu_count;
	}
	public String getCpus() {
		return cpus;
	}
	public void setCpus(String cpus) {
		this.cpus = cpus;
	}
	public String getCpu_percent() {
		return cpu_percent;
	}
	public void setCpu_percent(String cpu_percent) {
		this.cpu_percent = cpu_percent;
	}
	public Object getDepends_on() {
		return depends_on;
	}
	public void setDepends_on(Object depends_on) throws Exception {
		this.depends_on = depends_on;
		convertDependsOn();
	}
	public Map<String, Condition> getDepends_onM() {
		return depends_onM;
	}
	public void setDepends_onM(Map<String, Condition> depends_onM) {
		this.depends_onM = depends_onM;
	}
	public String[] getDepends_onS() {
		return depends_onS;
	}
	public void setDepends_onS(String[] depends_onS) {
		this.depends_onS = depends_onS;
	}
	public Type getDepends_onType() {
		return depends_onType;
	}
	public void setDepends_onType(Type depends_onType) {
		this.depends_onType = depends_onType;
	}
	public Deploy getDeploy() {
		return deploy;
	}
	public void setDeploy(Deploy deploy) {
		this.deploy = deploy;
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
	public Type getEntrypointType() {
		return entrypointType;
	}
	public void setEntrypointType(Type entrypointType) {
		this.entrypointType = entrypointType;
	}
	public Object getEnvironment() {
		return environment;
	}
	public void setEnvironment(Object environment) throws Exception {
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
	public Type getEnvironmentType() {
		return environmentType;
	}
	public void setEnvironmentType(Type environmentType) {
		this.environmentType = environmentType;
	}
	public Object getEnv_file() {
		return env_file;
	}
	public void setEnv_file(Object env_file) throws Exception {
		this.env_file = env_file;
		convertEnv_File();
	}
	public String[] getEnv_fileSL() {
		return env_fileSL;
	}
	public void setEnv_fileSL(String[] env_fileSL) {
		this.env_fileSL = env_fileSL;
	}
	public String getEnv_fileType() {
		return env_fileType;
	}
	public void setEnv_fileType(String env_fileType) {
		this.env_fileType = env_fileType;
	}
	public Extends getExtends() {
		return Extends;
	}
	public void setExtends(Extends extends1) {
		Extends = extends1;
	}
	public String[] getExpose() {
		return expose;
	}
	public void setExpose(String[] expose) {
		this.expose = expose;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public Healthcheck getHealthcheck() {
		return healthcheck;
	}
	public void setHealthcheck(Healthcheck healthcheck) {
		this.healthcheck = healthcheck;
	}
	public Object getLabels() {
		return labels;
	}
	public void setLabels(Object labels) throws Exception {
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
	public Type getLabelType() {
		return labelType;
	}
	public void setLabelType(Type labelType) {
		this.labelType = labelType;
	}
	public String[] getGroup_add() {
		return group_add;
	}
	public void setGroup_add(String[] group_add) {
		this.group_add = group_add;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getInit() {
		return init;
	}
	public void setInit(String init) {
		this.init = init;
	}
	public String[] getLinks() {
		return links;
	}
	public void setLinks(String[] links) {
		this.links = links;
	}
	public Dependencies getLinkDependencies() {
		return LinkDependencies;
	}
	public void setLinkDependencies(String[] services) {
		this.LinkDependencies.dependents = this.links;
		this.LinkDependencies.target = services;
	}
	public Logging getLogging() {
		return logging;
	}
	public void setLogging(Logging logging) {
		this.logging = logging;
	}
	public String getMem_limit() {
		return mem_limit;
	}
	public void setMem_limit(String mem_limit) {
		this.mem_limit = mem_limit;
	}
	public String getMemswap_limit() {
		return memswap_limit;
	}
	public void setMemswap_limit(String memswap_limit) {
		this.memswap_limit = memswap_limit;
	}
	public String getMem_swappiness() {
		return mem_swappiness;
	}
	public void setMem_swappiness(String mem_swappiness) {
		this.mem_swappiness = mem_swappiness;
	}
	public String getNetwork_mode() {
		return network_mode;
	}
	public void setNetwork_mode(String network_mode) {
		this.network_mode = network_mode;
	}
	public Object getNetworks() {
		return networks;
	}
	public void setNetworks(Object networks) throws Exception {
		this.networks = networks;
		convertNetworks();
	}
	public String[] getNetworksSL() {
		return networksSL;
	}
	public void setNetworksSL(String[] networksSL) {
		this.networksSL = networksSL;
	}
	public Map<String, Network> getNetworksM() {
		return networksN;
	}
	public void setNetworksM(Map<String, Network> networksN) {
		this.networksN = networksN;
	}
	public Type getNetworkType() {
		return networkType;
	}
	public void setNetworkType(Type networkType) {
		this.networkType = networkType;
	}
	public Dependencies getNetworkModeDependencies() {
		return NetworkModeDependencies;
	}
	public void setNetworkModeDependencies(String[] services) {
		if(this.network_mode.startsWith("service:") == true)
		{
			NetworkModeDependencies.dependents[0] = this.network_mode.substring(this.network_mode.lastIndexOf("="));
		}
		NetworkModeDependencies.target = services;
		
	}
	public Dependencies getNetworkDependencies() {
		return NetworkDependencies;
	}
	public void setNetworkDependencies(String[] networks) {
		this.NetworkDependencies.target = networks;
		if(networkType == Type.STRINGlIST) {
			this.NetworkDependencies.dependents = this.networksSL;
		}
		else {
			this.NetworkDependencies.dependents = this.networksN.keySet().toArray(new String[networksN.size()] );
		}
	}
	
	public String getRestart() {
		return restart;
	}
	public void setRestart(String restart) {
		this.restart = restart;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public Dependencies getServiceDependencies() {
		return ServiceDependencies;
	}
	public void setServiceDependenciesD(String[] services) {
		if(this.depends_on != null)
		{
			if(depends_onType == Type.STRINGlIST)
			{
				ServiceDependencies.dependents = this.depends_onS;
			}
			else
			{
				Set<String> keys = this.depends_onM.keySet();
				String[] array = keys.toArray(new String[keys.size()]);
				ServiceDependencies.dependents = array;
			}
			
		}
		else 
		{
			ServiceDependencies.dependents = null;
		}
		ServiceDependencies.target = services;
	}
	public Object getSecrets() {
		return secrets;
	}
	@SuppressWarnings("unchecked")
	public void setSecrets(Object secrets) {
		this.secrets = secrets;
		
		if(resolver.checkMapList(secrets) == true)
		{
			Map<String,Object>[] secretsSO = Converter.convertMapList((ArrayList<Map<String, Object>>)secrets);
			this.secretsSL = Converter.convertSecretList(secretsSO);
			this.secretsType = Type.SECRETLIST;
		}
		else 
		{
			if(resolver.checkStringList(secrets) == true)
			{
				secretsL = Converter.convertStringList(secrets);
				this.secretsType = Type.STRINGlIST;
			}
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
	public Type getSecretsType() {
		return secretsType;
	}
	public void setSecretsType(Type secretsType) {
		this.secretsType = secretsType;
	}
	public Dependencies getSecretDependencies() {
		return SecretDependencies;
	}
	public void setSecretDependencies(String[] secrets) {
		if(this.secretsType == Type.STRINGlIST)
		{
			this.SecretDependencies.dependents = this.secretsL;
		}
		else 
		{
			String[] secretSources = new String[secretsSL.length];
			for(int i = 0 ; i < secretSources.length ; i++)
			{
				secretSources[i] = secretsSL[i].getSource();
			}
			this.SecretDependencies.dependents = secretSources;
		}
		SecretDependencies.target = secrets;
	}
	public String getStdin_open() {
		return stdin_open;
	}
	public void setStdin_open(String stdin_open) {
		this.stdin_open = stdin_open;
	}
	public String getTty() {
		return tty;
	}
	public void setTty(String tty) {
		this.tty = tty;
	}
	public Object getPorts() {
		return ports;
	}
	@SuppressWarnings("unchecked")
	public void setPorts(Object ports) {
		this.ports = ports;
		ArrayList<Map<String, Object>> portsAL = null;
		if(resolver.checkMapList(ports) == true)
		{
			portsAL = (ArrayList<Map<String, Object>>)ports;
			portsP = Converter.convertPorts(portsAL);
			this.portsType = Type.PORTLIST;
			
		}
		else 
		{
			this.portsSL = Converter.convertStringList(ports);
			this.portsType = Type.STRINGlIST;
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
	public Type getPortsType() {
		return portsType;
	}
	public void setPortsType(Type portsType) {
		this.portsType = portsType;
	}
	public String getPrivileged() {
		return privileged;
	}
	public void setPrivileged(String privileged) {
		this.privileged = privileged;
	}
	public Object getVolumes() {
		return volumes;
	}
	@SuppressWarnings("unchecked")
	public void setVolumes(Object volumes) throws Exception {
		this.volumes = volumes;
		ArrayList<Map<String, Object>> volumeAL = null;
		if(resolver.checkMapList(volumes) == true)
		{
			
			volumeAL = (ArrayList<Map<String, Object>>)volumes;
			volumesVL = Converter.convertVolumes(volumeAL);
			this.volumeType = Type.VOLUMELISTS;
		}
		else if(resolver.checkStringList(volumes))
		{
			setVolumesSL(Converter.convertStringList(volumes));
			this.volumeType = Type.STRINGlIST;
		}
		else 
		{
			throw new Exception("Unkown type for service volume");
		}
	}
	public Volume[] getVolumesVL() {
		return volumesVL;
	}
	public void setVolumesVL(Volume[] volumesVL) {
		this.volumesVL = volumesVL;
	}
	public String[] getVolumesSL() {
		return volumesSL;
	}
	public void setVolumesSL(String[] volumesSL) {
		
		for(int i = 0; i < volumesSL.length; i++)
		{
			if (volumesSL[i].contains(":"))
			{
				String[] x = volumesSL[i].split(":");
				volumesSL[i] = x[0];
			}
		}

	}
	public Type getVolumeType() {
		return volumeType;
	}
	public void setVolumeType(Type volumeType) {
		this.volumeType = volumeType;
	}
	public Dependencies getVolumeDependencies() {
		return VolumeDependencies;
	}
	public void setVolumeDependencies(String[] volumeList) {
		if(this.volumeType == Type.STRINGlIST)
		{
			this.VolumeDependencies.dependents = this.volumesSL;
		}
		else 
		{
			String[] volumeSources = new String[volumesVL.length];
			for(int i = 0 ; i < volumesVL.length ; i++)
			{
				volumeSources[i] = volumesVL[i].getSource(); //Retrives the names of the volume objects
			}
			this.VolumeDependencies.dependents = volumeSources;
		}
		this.VolumeDependencies.target = volumeList;
	}
	public String[] getVolumes_from() {
		return volumes_from;
	}
	public void setVolumes_from(String[] volumes_from) {
		this.volumes_from = volumes_from;
	}
	private void convertCommand() throws Exception 
	{
		String tCommand = command.toString();
		if(resolver.checkStringList(command))
		{
			commandSL = Converter.convertStringList(tCommand);
			commandType = Type.STRINGlIST;
			
		}
		else if(resolver.checkString(command) == true)
			{
				commandS = tCommand;
				commandType = Type.STRING;
			}
		
		else 
		{
			throw new Exception("Unknown type for command");
		}	
	}
	public String getWorking_dir() {
		return working_dir;
	}
	public void setWorking_dir(String working_dir) {
		this.working_dir = working_dir;
	}
	public TypeResolver getResolver() {
		return resolver;
	}
	public void setResolver(TypeResolver resolver) {
		this.resolver = resolver;
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
	public Type getDnsType() {
		return dnsType;
	}
	public String getDns_searchS() {
		return dns_searchS;
	}
	public void setDns_searchS(String dns_searchS) {
		this.dns_searchS = dns_searchS;
	}
	public String[] getDns_searchSL() {
		return dns_searchSL;
	}
	public void setDns_searchSL(String[] dns_searchSL) {
		this.dns_searchSL = dns_searchSL;
	}
	public Type getDns_searchType() {
		return dns_searchType;
	}
	public void setDns_searchType(Type dns_searchType) {
		this.dns_searchType = dns_searchType;
	}
	public void setDns_search(Object dns_search) {
		this.dns_search = dns_search;
		convertDnsSearch();
	}
	public Object getDns_search() {
		return dns_search;
	}
	public void setDnsType(Type dnsType) {
		this.dnsType = dnsType;
	}
	public String getDomainname() {
		return domainname;
	}
	public void setDomainname(String domainname) {
		this.domainname = domainname;
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
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	public Map<String, Network> getNetworksN() {
		return networksN;
	}
	public void setNetworksN(Map<String, Network> networksN) {
		this.networksN = networksN;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getRead_only() {
		return read_only;
	}
	public void setRead_only(String read_only) {
		this.read_only = read_only;
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
	public void setSysctls(Object sysctls) throws Exception {
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
	public Type getSysctlsType() {
		return sysctlsType;
	}
	public void setSysctlsType(Type sysctlsType) {
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
	public Type getTmpfsType() {
		return tmpfsType;
	}
	public void setTmpfsType(Type tmpfsType) {
		this.tmpfsType = tmpfsType;
	}
	public void setTmpfs(Object tmpfs) {
		this.tmpfs = tmpfs;
		convertTmpfs();
	}
	public Object getTmpfs() {
		return tmpfs;
	}
	public Ulimits getUlimits() {
		return ulimits;
	}
	public void setUlimits(Ulimits ulimits) {
		this.ulimits = ulimits;
	}
	public String getVolume_driver() {
		return volume_driver;
	}
	public void setVolume_driver(String volume_driver) {
		this.volume_driver = volume_driver;
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
	private void convertLabels() throws Exception 
	{
		if(resolver.checkMap(labels) == true)
		{
			labelsM = Converter.convertMap(labels);
			labelType = Type.MAP_STRING_STRING;
		}
		else if(resolver.checkStringList(labels) == true)
		{
			labelsS = Converter.convertStringList(labels);
			labelType = Type.STRINGlIST;
		}
		else
		{
			throw new Exception ("Unknown type entered for Top Level Labels");
		}
	}
	@SuppressWarnings("unchecked")
	private void convertNetworks() throws Exception 
	{
		Map<String, Map<String,Object>> networksAL = null;;
		if(resolver.checkStringList(networks) == true)
		{
			this.networksSL = Converter.convertStringList(networks);
			this.networkType = Type.STRINGlIST;
		}
		else if(resolver.checkNestedMap(networks) == true)
		{
			networksAL = (Map<String, Map<String,Object>>)networks;
			networksN = Converter.convertNetworks(networksAL);
			this.networkType = Type.MAP_STRING_NETWORK;
		}
		else
		{
			throw new Exception ("Unknown type entered for Top Level Network");
		}
		
	}
	private void convertEnvironment() throws Exception
	{
		if(resolver.checkMap(environment) == true)
		{
			environmentM = Converter.convertMap(environment);
			environmentType = Type.MAP_STRING_STRING;
		}
		else if(resolver.checkStringList(environment) == true)
		{
			environmentSL = Converter.convertStringList(environment);
			environmentType = Type.STRINGlIST;
		}
		else
		{
			throw new Exception ("Unknown type entered for Environment");
		}
	}
	private void convertEnv_File() throws Exception
	{
	
		if(resolver.checkStringList(env_file))
		{
			env_fileSL  = Converter.convertStringList(env_file );
			env_fileType = "String[]";
		}
		else if(resolver.checkString(env_file))
		{
			String[] list = new String[1];
			list[0] = env_file.toString();
			env_fileSL = list ;
			env_fileType = "String";
		}
		else 
		{
			throw new Exception("Unknown type for Healthcheck test");
		}
		
	}
	private void convertEntrypoint()
	{
		boolean set = false;
		String tEntrypoint = entrypoint.toString();
		if(resolver.checkStringList(entrypoint))
		{
			entrypointSL  = Converter.convertStringList(tEntrypoint );
			entrypointType = Type.STRINGlIST;
			set = true;
		}
		else 
		{
			if(set == false)
			{
			entrypointS = tEntrypoint ;
			entrypointType = Type.STRING;
			}
		}
	}
	private void convertDependsOn() throws Exception
	{
		if(resolver.checkNestedMap(depends_on) == true)
		{
			depends_onM = Converter.convertMapCondition((Map<String, Map<String, Object>>) depends_on);
			depends_onType = Type.MAP_STRING_CONDITION;
		}
		else if(resolver.checkStringList(depends_on))
		{
			depends_onS = Converter.convertStringList(depends_on);
			depends_onType = Type.STRINGlIST;
		}
		else
		{
			throw new Exception("Unknown type for depends_on test");
		}
	}
	private void convertTmpfs() 
	{
		boolean set = false;
		String tTmpfs = tmpfs.toString();
		if(resolver.checkStringList(tmpfs))
		{
			tmpfsSL = Converter.convertStringList(tmpfs);
			tmpfsType = Type.STRINGlIST;
			set = true;
		}
		else 
			
		{
			if(set == false)
			{
				tmpfsS = tTmpfs;
				tmpfsType = Type.STRING;
			}
		}	
	}
	private void convertSysctls() throws Exception
	{
		if(resolver.checkMap(sysctls) == true)
		{
			sysctlsM = Converter.convertMap(sysctls);
			sysctlsType = Type.MAP_STRING_STRING;
		}
		else if(resolver.checkStringList(sysctls) == true)
		{
			sysctlsSL = Converter.convertStringList(sysctls);
			sysctlsType = Type.STRINGlIST;
		}
		else
		{
			throw new Exception ("Unknown type entered for Top Level sysctls");
		}
	}
	private void convertDNS()
	{
		boolean set = false;
		String tDNS = dns.toString();
		if(resolver.checkStringList(dns))
		{
			dnsSL = Converter.convertStringList(tDNS);
			dnsType = Type.STRINGlIST;
			set = true;
		}
		else 
		{
			if(set == false){
			dnsS = tDNS;
			dnsType = Type.STRING;
			}
		}
	}
	private void convertDnsSearch()
	{
		boolean set = false;
		String tDNS = dns_search.toString();
		if(resolver.checkStringList(dns))
		{
			dns_searchSL = Converter.convertStringList(tDNS);
			dns_searchType = Type.STRINGlIST;
			set = true;
		}
		else 
		{
			if(set == false){
				dns_searchS = tDNS;
				dns_searchType = Type.STRING;
			}
		}
	}
	public String getCpu_rt_runtime() {
		return cpu_rt_runtime;
	}
	public void setCpu_rt_runtime(String cpu_rt_runtime) {
		this.cpu_rt_runtime = cpu_rt_runtime;
	}
	public String getCpu_rt_period() {
		return cpu_rt_period;
	}
	public void setCpu_rt_period(String cpu_rt_period) {
		this.cpu_rt_period = cpu_rt_period;
	}
	public String getCpu_period() {
		return cpu_period;
	}
	public void setCpu_period(String cpu_period) {
		this.cpu_period = cpu_period;
	}
	public String getOom_kill_disable() {
		return oom_kill_disable;
	}
	public void setOom_kill_disable(String oom_kill_disable) {
		this.oom_kill_disable = oom_kill_disable;
	}
	public String getOom_score_adj() {
		return oom_score_adj;
	}
	public void setOom_score_adj(String oom_score_adj) {
		this.oom_score_adj = oom_score_adj;
	}
	public String getDevice_cgroup_rules() {
		return device_cgroup_rules;
	}
	public void setDevice_cgroup_rules(String device_cgroup_rules) {
		this.device_cgroup_rules = device_cgroup_rules;
	}
	public String getMem_reservation() {
		return mem_reservation;
	}
	public void setMem_reservation(String mem_reservation) {
		this.mem_reservation = mem_reservation;
	}
	public String getPids_limit() {
		return pids_limit;
	}
	public void setPids_limit(String pids_limit) {
		this.pids_limit = pids_limit;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String[] getDns_opt() {
		return dns_opt;
	}
	public void setDns_opt(String[] dns_opt) 
	{
		this.dns_opt = dns_opt;
	}


	
}

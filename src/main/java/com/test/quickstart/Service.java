package com.test.quickstart;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckFileExists;
import com.test.quickstart.Validation.Interfaces.CheckFolderExists;
import com.test.quickstart.Validation.Interfaces.CheckStringListFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;
import com.test.quickstart.Validation.Interfaces.Dependency;



public class Service {
	private TypeConverter Converter = new TypeConverter();
	private TypeResolver resolver = new TypeResolver();
	private String Name;
	private Blkio_config blkio_config;
	private Object build;
	@CheckFolderExists(message = "Directory doesn't exist")
	private String buildS;
	private Build buildB;
	private String buildType;
	private Object command;
	private String commandS;
	@CheckDuplication(message = "Duplicate command detected")
	private String[] commandSL;
	private String commandType;
	private String container_name;
	private Object configs;
	private Configs[] configsC;
	@CheckDuplication(message = "Duplicate service config detected")
	private String[] configsSL;
	private String configType;
	private String cpu_shares;
	private String cpu_quota;
	private String cpuset;
	private String mem_limit;
	private String memswap_limit;
	private String mem_swappiness;
	private Object depends_on;
	private Map<String,Condition> depends_onM;
	private String[] depends_onS;
	private String depends_onType;
	private Deploy deploy;
	private Object entrypoint;
	@CheckDuplication(message = "Duplicate entrypoint detected")
	private String[] entrypointSL;
	private String entrypointS;
	private String entrypointType;
	private Object env_file;
	@CheckDuplication(message = "Duplicate service env_file detected")
	private String[] env_fileSL;
	private String env_fileType;
	private Object environment;
	private Map<String,String> environmentM;
	@CheckDuplication(message = "Duplicate environment detected")
	private String[] environmentSL;
	private String environmentType;
	@CheckStringListFormat(message = "Invalid port target", value = ValidationEnums.CheckStringListType.PORT)
	@CheckDuplication(message = "Duplicate port detected")
	private String[] expose;
	private Healthcheck healthcheck;
	private String hostname;
	private Object labels;
	private Map<String,String> labelsM;
	@CheckDuplication(message = "Duplicate service lable detected")
	private String[] labelsS;
	private String labelType;
	private String image;
	@CheckFileExists(message = "The docker-init file specfied by the service init cannot be found", value = "")
	private String init;
	@CheckDuplication(message = "Duplicate service link detected")
	private String[] links;
	private Logging logging;
	private String[] group_add;
	@ContainsString(message = "Invalid network type", value = ValidationEnums.ContainsStringType.NETWORK_MODE)
	private String network_mode;
	private Object networks;
	@CheckDuplication(message = "Duplicate service network detected")
	private String[] networksSL;
	private Map<String,Network> networksN;
	private String networkType;
	@ContainsString(message = "Invalid restart condition", value = ValidationEnums.ContainsStringType.RESTART_POLICY_CONDITION)
	private String restart;
	private String runtime;
	private String scale;
	private Object secrets;
	@CheckDuplication(message = "Duplicate secret detected")
	private String[] secretsL;
	private Secrets[] secretsSL;
	private String secretsType;
	@ContainsString(message = "stdin_open must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String stdin_open;
	@ContainsString(message = "tty must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String tty;
	private Object ports;
	@CheckDuplication(message = "Duplicate service port detected")
	private String[] portsSL;
	private Ports[] portsP; 
	private String portsType;
	@ContainsString(message = "Priveleged must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String privileged;
	private Object volumes;
	private Volume[] volumesVL;
	@CheckDuplication(message = "Duplicate service volume detected")
	private String[] volumesSL;
	private String volumeType;
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
	@Dependency(message = "Service references an env_file that isn't present")
	private Dependencies EnvironmentDependencies = new Dependencies();
	
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
			this.buildType = "String";
		}
		else 
		{
			this.buildB = Converter.convertBuild(build);
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
	public String getCommandType() {
		return commandType;
	}
	public void setCommandType(String commandType) {
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
			this.configType = "Configs[]";
		}
		else if(resolver.checkStringList(configs) == true)
		{
			this.configsSL = Converter.convertStringList(configs);
			this.configType = "String[]";
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
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	public Dependencies getConfigDependencies() {
		return ConfigDependencies;
	}
	public void setConfigDependencies(String[] configList) {
		if(this.configType == "String[]")
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
	public String getDepends_onType() {
		return depends_onType;
	}
	public void setDepends_onType(String depends_onType) {
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
	public String getEntrypointType() {
		return entrypointType;
	}
	public void setEntrypointType(String entrypointType) {
		this.entrypointType = entrypointType;
	}
	public Dependencies getEnvironmentDependencies() {
		return EnvironmentDependencies;
	}
	public void setEnvironmentDependencies(String[] environments) {
		
		EnvironmentDependencies.dependents = this.env_fileSL;
		
		
		EnvironmentDependencies.target = environments;
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
	public String getEnvironmentType() {
		return environmentType;
	}
	public void setEnvironmentType(String environmentType) {
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
	public String getLabelType() {
		return labelType;
	}
	public void setLabelType(String labelType) {
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
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public Dependencies getNetworkDependencies() {
		return NetworkDependencies;
	}
	public void setNetworkDependencies(String[] networks) {
		this.NetworkDependencies.target = networks;
		if(networkType == "String[]") {
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
			if(depends_onType == "String[]")
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
			this.secretsType = "Secrets[]";
		}
		else 
		{
			Map<String, Map<String,Object>> secretsSSO = (Map<String, Map<String,Object>>)secrets;
			secretsSL = Converter.convertSecrets(secretsSSO);
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
	public Dependencies getSecretDependencies() {
		return SecretDependencies;
	}
	public void setSecretDependencies(String[] secrets) {
		if(this.secretsType == "String[]")
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
		if(resolver.checkStringList(ports) == true)
		{
			
			this.portsSL = Converter.convertStringList(ports);
			this.portsType = "String[]";
		}
		else 
		{
			portsAL = (ArrayList<Map<String, Object>>)ports;
			portsP = Converter.convertPorts(portsAL);
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
			this.volumeType = "Volume[]";
		}
		else if(resolver.checkStringList(volumes))
		{
			setVolumesSL(Converter.convertStringList(volumes));
			this.volumeType = "String[]";
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
	public String getVolumeType() {
		return volumeType;
	}
	public void setVolumeType(String volumeType) {
		this.volumeType = volumeType;
	}
	public Dependencies getVolumeDependencies() {
		return VolumeDependencies;
	}
	public void setVolumeDependencies(String[] volumeList) {
		if(this.volumeType == "String[]")
		{
			this.VolumeDependencies.dependents = this.volumesSL;
		}
		else 
		{
			String[] volumeSources = new String[volumesVL.length];
			for(int i = 0 ; i < volumesVL.length ; i++)
			{
				volumeSources[i] = volumesVL[i].getSource();
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
			commandType = "String[]";
			
		}
		else if(resolver.checkString(command) == true)
			{
				commandS = tCommand;
				commandType = "String";
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
	private void convertLabels() throws Exception 
	{
		if(resolver.checkMap(labels) == true)
		{
			labelsM = Converter.convertMap(labels);
			labelType = "Map<String,String>";
		}
		else if(resolver.checkStringList(labels) == true)
		{
			labelsS = Converter.convertStringList(labels);
			labelType = "String[]";
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
			this.networkType = "String[]";
		}
		else if(resolver.checkNestedMap(networks) == true)
		{
			networksAL = (Map<String, Map<String,Object>>)networks;
			networksN = Converter.convertNetworks(networksAL);
			this.networkType = "Map<String,Network>";
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
			environmentType = "Map<String,String>";
		}
		else if(resolver.checkStringList(environment) == true)
		{
			environmentSL = Converter.convertStringList(environment);
			environmentType = "String[]";
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
			entrypointType = "String[]";
			set = true;
		}
		else 
		{
			if(set == false)
			{
			entrypointS = tEntrypoint ;
			entrypointType = "String";
			}
		}
	}
	private void convertDependsOn() throws Exception
	{
		if(resolver.checkNestedMap(depends_on) == true)
		{
			depends_onM = Converter.convertMapCondition((Map<String, Map<String, Object>>) depends_on);
			depends_onType = "Map<String,Condition>";
		}
		else if(resolver.checkStringList(depends_on))
		{
			depends_onS = Converter.convertStringList(depends_on);
			depends_onType = "String[]";
		}
		else
		{
			throw new Exception("Unknown type for depends_on test");
		}
	}

	
}

package com.test.quickstart;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import com.test.quickstart.Validation.Interfaces.CheckUsed;
import com.test.quickstart.Validation.Interfaces.ContainsString;
import com.test.quickstart.Validation.Interfaces.Dependency;
import com.test.quickstart.Validation.Interfaces.ListContainsString;
import com.test.quickstart.Validation.ValidationEnums;
import javax.validation.constraints.Email;

public class TopLevel {
	private TypeConverter converter = new TypeConverter();
	private TypeResolver resolver = new TypeResolver();
	private Map<String,Configs> configs;
	
	private String cpu_shares;
	private String cpu_quota;
	private String cpu_rt_runtime;
	@CheckStringFormat(message = "Invalid time format for cpu rt period", value = ValidationEnums.CheckStringType.TIME)
	private String cpu_rt_period;
	private String cpuset;
	@CheckStringFormat(message = "Invalid time format for cpu period", value = ValidationEnums.CheckStringType.TIME)
	private String cpu_period;
	private String oom_kill_disable;
	private String oom_score_adj;
	private String device_cgroup_rules;
	
	private String[] dns_opt;
	
	@JsonProperty("extends")
	private String Extends;
	

	private String image;
	
	
	private String init;
	private String[] group_add;
	private Object labels;
	private Map<String,String> labelsM;
	@CheckDuplication(message = "Duplicate label detected")
	private String[] labelsS;
	private String labelType;
	
	private String mem_limit;
	private String memswap_limit;
	private String mem_swappiness;
	@CheckStringFormat(message = "mem reservation isn't a valid memory format", value = ValidationEnums.CheckStringType.MEMORY)
	private String mem_reservation;
	private Object networks;
	@CheckDuplication(message = "Duplicate network detected")
	private String[] networksSL;
	private Map<String, Network> networksN;
	private String networkType;
	
	private String pids_limit;
	private String platform;
	private String scale;
	private Object ports;
	@CheckStringListFormat(message = "Invalid port target", value = ValidationEnums.CheckStringListType.PORT)
	@CheckDuplication(message = "Duplicate port detected")
	private String[] portsSL;
	private Ports[] portsP; 
	private String portsType;
	
	
	
	private Object secrets;
	@CheckDuplication(message = "Duplicate secret detected")
	private String[] secretsL;
	private Secrets[] secretsSL;
	private String secretsType;
	
	
	
	
	
	
	
	
	private Map<String, Volume> volumes;
	private String volume_driver;
	private String volumes_from;
	
	@ContainsString(value = ValidationEnums.ContainsStringType.VERSION, message = "Missing Docker Compose Version, compiler will assume version 1 of Compose is being used")
	private String version;
	private Map<String,Service> services;
	
	@CheckUsed(message = "Unused Secret")
	private Dependencies SecretCheckUsed = new Dependencies();
	@CheckUsed(message = "Unused Config")
	private Dependencies ConfigCheckUsed = new Dependencies();
	@CheckUsed(message = "Unused Volume")
	private Dependencies VolumeCheckUsed = new Dependencies();
	@CheckUsed(message = "Unused Env")
	private Dependencies EnvCheckUsed = new Dependencies();
	@CheckUsed(message = "Unused Network")
	private Dependencies NetworkCheckUsed = new Dependencies();
	
	
	public TypeConverter getConverter() {
		return converter;
	}
	public void setConverter(TypeConverter converter) {
		this.converter = converter;
	}
	public Map<String, Configs> getConfigs() {
		return configs;
	}
	public void setConfigs(Map<String, Configs> configs) {
		this.configs = configs;
	}
	
	
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	
	
	
	public Object getPorts() {
		return ports;
	}
	public void setPorts(Object ports) {
		this.ports = ports;
		ArrayList<Map<String, Object>> portsAL = null;
		if(resolver.checkStringList(ports) == false)
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
	
	public Object getSecrets() {
		return secrets;
	}
	public void setSecrets(Object secrets) {
		this.secrets = secrets;
		Map<String, Map<String,Object>> secretsAL = null;;
		if(resolver.checkStringList(secrets) == true)
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
	
	
	
	
	
	
	public Map<String, Volume> getVolumes() {
		return volumes;
	}
	public void setVolumes(Map<String, Volume> volumes) {
		this.volumes = volumes;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
	public String getExtends() {
		return Extends;
	}
	public void setExtends(String extends1) {
		Extends = extends1;
	}
	public String[] getGroup_add() {
		return group_add;
	}
	public void setGroup_add(String[] group_add) {
		this.group_add = group_add;
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
	public String getPids_limit() {
		return pids_limit;
	}
	public void setPids_limit(String pids_limit) {
		this.pids_limit = pids_limit;
	}
	public String getVolume_driver() {
		return volume_driver;
	}
	public void setVolume_driver(String volume_driver) {
		this.volume_driver = volume_driver;
	}
	public String getVolumes_from() {
		return volumes_from;
	}
	public void setVolumes_from(String volumes_from) {
		this.volumes_from = volumes_from;
	}
	public Map<String,Service> getServices() {
		return services;
	}
	public void setServices(Map<String,Service> services) {
		this.services = services;
	}
	public Dependencies getSecretCheckUsed() {
		return SecretCheckUsed;
	}
	public void setSecretCheckUsed(String[] secretCheckUsed) {
		SecretCheckUsed.target = secretCheckUsed;
		if(this.secretsType == "String[]")
		{
			SecretCheckUsed.dependents = this.secretsL;
		}
		else 
		{
			String[] secretSources = new String[secretsSL.length];
			for(int i = 0 ; i < secretSources.length ; i++)
			{
				secretSources[i] = secretsSL[i].getSource();
			}
			this.SecretCheckUsed.dependents = secretSources;
		}
	}
	public Dependencies getConfigCheckUsed() {
		return ConfigCheckUsed;
	}
	public void setConfigCheckUsed(String[] configCheckUsed) {
		this.ConfigCheckUsed.target = configCheckUsed;
		this.ConfigCheckUsed.dependents = this.configs.keySet().toArray(new String[this.configs.size()]);
	}
	public Dependencies getVolumeCheckUsed() {
		return VolumeCheckUsed;
	}
	public void setVolumeCheckUsed(String[]  volumeCheckUsed) {
		VolumeCheckUsed.target = volumeCheckUsed;
		this.VolumeCheckUsed.dependents = this.volumes.keySet().toArray(new String[this.volumes.size()]);
	}
	public Dependencies getEnvCheckUsed() {
		return EnvCheckUsed;
	}
	public Dependencies getNetworkCheckUsed() {
		return NetworkCheckUsed;
	}
	public void setNetworkCheckUsed(String[]  networkCheckUsed) {
		NetworkCheckUsed.target = networkCheckUsed;
		String[] networks;
		if(getNetworkType() == "String[]")
		{
			networks = getNetworksSL();
		}
		else
		{
			Map<String,Network> networkList = getNetworksN();
			networks = networkList.keySet().toArray(new String[networkList.size()]);
		}
		NetworkCheckUsed.dependents = networks;
	}
	
	
	
	
	private void convertLabels() throws Exception 
	{
		if(resolver.checkMap(labels) == true)
		{
			labelsM = converter.convertMap(labels);
			labelType = "Map<String,String>";
		}
		else if(resolver.checkStringList(labels) == true)
		{
			labelsS = converter.convertStringList(labels);
			labelType = "String[]";
		}
		else
		{
			throw new Exception ("Unknown type entered for Top Level Labels");
		}
	}
	private void convertNetworks() throws Exception 
	{
		Map<String, Map<String,Object>> networksAL = null;;
		if(resolver.checkStringList(networks) == true)
		{
			this.networksSL = converter.convertStringList(networks);
			this.networkType = "String[]";
		}
		else if(resolver.checkNestedMap(networks) == true)
		{
			networksAL = (Map<String, Map<String,Object>>)networks;
			networksN = converter.convertNetworks(networksAL);
			this.networkType = "Map<String,Network>";
		}
		else if(resolver.checkMap(networks))
		{
			Map<String,String> map = converter.convertMap(networks);
			Set<String> keys = map.keySet();
			String[] netList = keys.toArray(new String[keys.size()]);
			this.networksSL = netList;
			this.networkType = "String[]";
		}
		else
		{
			System.out.println(networks);
			System.out.println("yes");
			throw new Exception ("Unknown type entered for Top Level Network");
		}
	}
	
	
	public TopLevelReturn accept(YamlParserVisitor parser)
	{
		return parser.visit(this);
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
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
	public String getInit() {
		return init;
	}
	public void setInit(String init) {
		this.init = init;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getDevice_cgroup_rules() {
		return device_cgroup_rules;
	}
	public void setDevice_cgroup_rules(String device_cgroup_rules) {
		this.device_cgroup_rules = device_cgroup_rules;
	}
	
}

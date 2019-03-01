package com.test.quickstart;

import java.util.ArrayList;
import java.util.Map;

import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckFileExists;
import com.test.quickstart.Validation.Interfaces.CheckFolderExists;
import com.test.quickstart.Validation.Interfaces.Dependency;



public class Service {
	private TypeConverter Converter = new TypeConverter();
	private String Name;
	private Object build;
	@CheckFolderExists(message = "Directory doesn't exist")
	private String buildS;
	private Build buildB;
	private String buildType;
	private Object configs;
	private Configs[] configsC;
	@CheckDuplication(message = "Duplicate service config detected")
	private String[] configsSL;
	private String configType;
	private String[] depends_on;
	private Deploy deploy;
	@CheckDuplication(message = "Duplicate service env_file detected")
	private String[] env_file;
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
	private Object networks;
	@CheckDuplication(message = "Duplicate service network detected")
	private String[] networksSL;
	private Map<String,Network> networksM;
	private String networkType;
	@CheckDuplication(message = "Duplicate service secret detected")
	private String[] secrets; 
	private Object ports;
	@CheckDuplication(message = "Duplicate service port detected")
	private String[] portsSL;
	private Ports[] portsP; 
	private String portsType;
	private Object volumes;
	private Volume[] volumesVL;
	@CheckDuplication(message = "Duplicate service volume detected")
	private String[] volumesSL;
	private String volumeType;
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
	public Object getBuild() {
		return build;
	}
	public void setBuild(Object build) {
		this.build = build;
		if(Converter.checkString(build) == true)
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
	public Object getConfigs() {
		return configs;
	}
	@SuppressWarnings("unchecked")
	public void setConfigs(Object configs) {
		this.configs = configs;
		ArrayList<Map<String, Object>> configAL = null;
		if(Converter.checkStringList(configs) == true)
		{
			this.configsSL = Converter.convertStringList(configs);
			this.configType = "String[]";
		}
		else 
		{
			configAL = (ArrayList<Map<String, Object>>)configs;
			configsC = Converter.convertConfigsList(configAL);
			this.configType = "Configs[]";
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
	public String[] getDepends_on() {
		return depends_on;
	}
	public void setDepends_on(String[] depends_on) {
		this.depends_on = depends_on;
	}
	public Deploy getDeploy() {
		return deploy;
	}
	public void setDeploy(Deploy deploy) {
		this.deploy = deploy;
	}
	public String[] getEnv_file() {
		return env_file;
	}
	public void setEnv_file(String[] env_file) {
		this.env_file = env_file;
	}
	public Dependencies getEnvironmentDependencies() {
		return EnvironmentDependencies;
	}
	public void setEnvironmentDependencies(String[] environments) {
		
		ServiceDependencies.dependents = this.env_file;
		ServiceDependencies.target = environments;
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
	public Map<String, Network> getNetworksM() {
		return networksM;
	}
	public void setNetworksM(Map<String, Network> networksM) {
		this.networksM = networksM;
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
		if(networkType == "StringList") {
			this.NetworkDependencies.dependents = this.networksSL;
		}
		else {
			this.NetworkDependencies.dependents = this.networksM.keySet().toArray(new String[networksM.size()] );
		}
	}
	
	public Dependencies getServiceDependencies() {
		return ServiceDependencies;
	}
	public void setServiceDependenciesD(String[] services) {
		if(this.depends_on != null)
		{
			ServiceDependencies.dependents = this.depends_on;
		}
		else 
		{
			ServiceDependencies.dependents = null;
		}
		ServiceDependencies.target = services;
	}
	public String[] getSecrets() {
		return secrets;
	}
	public void setSecrets(String[] secrets) {
		this.secrets = secrets;
	}
	public Dependencies getSecretDependencies() {
		return SecretDependencies;
	}
	public void setSecretDependencies(String[] secrets) {
		SecretDependencies.dependents = this.secrets;
		SecretDependencies.target = secrets;
	}
	public Object getPorts() {
		return ports;
	}
	public void setPorts(Object ports) {
		this.ports = ports;
		ArrayList<Map<String, Object>> portsAL = null;
		if(Converter.checkStringList(ports) == true)
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
	public Object getVolumes() {
		return volumes;
	}
	@SuppressWarnings("unchecked")
	public void setVolumes(Object volumes) {
		this.volumes = volumes;
		ArrayList<Map<String, Object>> volumeAL = null;
		if(Converter.checkStringList(volumes) == true)
		{
			this.volumesSL = Converter.convertStringList(volumes);
			this.volumeType = "String[]";
		}
		else 
		{
			volumeAL = (ArrayList<Map<String, Object>>)volumes;
			volumesVL = Converter.convertVolumes(volumeAL);
			this.volumeType = "Volume[]";
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
		this.volumesSL = volumesSL;
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
		this.ConfigDependencies.target = volumeList;
	}
	private void convertLabels() 
	{
		boolean set = false;
		try {
			labelsM = Converter.convertMap(labels);
			labelType = "Map<String,String>";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				labelsS = Converter.convertStringList(labels);
				labelType = "String[]";
			}
		}
	}
	private void convertNetworks() 
	{
		boolean set = false;
		try {
			networksM = Converter.convertMapSN(networks);
			networkType = "Map<String,Network>";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				networksSL = Converter.convertStringList(networks);
				networkType = "String[]";
			}
		}
	}
	

	
}

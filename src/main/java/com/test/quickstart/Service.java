package com.test.quickstart;

import java.util.Map;

import com.test.quickstart.Validation.Interfaces.CheckFileExists;
import com.test.quickstart.Validation.Interfaces.Dependency;



public class Service {
	private TypeConverter Converter = new TypeConverter();
	private String Name;
	private Object build;
	private String buildS;
	private Build buildB;
	private String buildType;
	private String[] configs;
	private String[] depends_on;
	private Deploy deploy;
	private Object labels;
	private Map<String,String> labelsM;
	private String[] labelsS;
	private String labelType;
	private String image;
	@CheckFileExists(message = "The docker-init file specfied by the service init cannot be found", value = "")
	private String init;
	private String[] links;
	private Logging logging;
	private Object networks;
	private String[] networksSL;
	private Map<String,Network> networksM;
	private String networkType;
	private String[] secrets; 
	private Object ports;
	private String[] portsSL;
	private Ports portsP; 
	private String portsType;
	private Object volumes;
	private Volume[] volumesVL;
	private String[] volumesSL;
	private String volumeType;
	@Dependency(message = "Service depends on config that isn't present")
	private Dependencies ConfigDependencies;
	@Dependency(message = "Service depends on another service that isn't present")
	private Dependencies ServiceDependencies;
	@Dependency(message = "Service links another service that isn't present")
	private Dependencies LinkDependencies;
	@Dependency(message = "Service references a network that isn't present")
	private Dependencies NetworkDependencies;
	@Dependency(message = "Service references a sercret that isn't present")
	private Dependencies SecretDependencies;
	
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
		convertBuild();
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
	public String[] getConfigs() {
		return configs;
	}
	public void setConfigs(String[] configs) {
		this.configs = configs;
	}
	public Dependencies getConfigDependencies() {
		return ConfigDependencies;
	}
	public void setConfigDependencies(String[] configList) {
		this.ConfigDependencies.dependendents = this.configs;
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
		this.LinkDependencies.dependendents = this.links;
		this.ConfigDependencies.target = services;
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
			this.NetworkDependencies.dependendents = this.networksSL;
		}
		else {
			this.NetworkDependencies.dependendents = this.networksM.keySet().toArray(new String[networksM.size()] );
		}
	}
	
	public Dependencies getServiceDependencies() {
		return ServiceDependencies;
	}
	public void setServiceDependenciesD(String[] services) {
		ServiceDependencies.dependendents = this.depends_on;
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
		SecretDependencies.dependendents = this.secrets;
		SecretDependencies.target = secrets;
	}
	public Object getPorts() {
		return ports;
	}
	public void setPorts(Object ports) {
		this.ports = ports;
		convertPorts();
	}
	public String[] getPortsSL() {
		return portsSL;
	}
	public void setPortsSL(String[] portsSL) {
		this.portsSL = portsSL;
	}
	public Ports getPortsP() {
		return portsP;
	}
	public void setPortsP(Ports portsP) {
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
	public void setVolumes(Object volumes) {
		this.volumes = volumes;
		convertVolumes();
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
	private void convertBuild() 
	{
		boolean set = false;
		try {
			buildB = (Build)build;
			buildType = "Build";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				buildS = build.toString();
				buildType = "String";
			}
		}
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
	private void convertPorts() 
	{
		boolean set = false;
		try {
			portsP = (Ports)ports;
			portsType = "Ports";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				portsSL = Converter.convertStringList(ports);
				portsType = "String[]";
				
			}
		}
	}
	private void convertVolumes() 
	{
		boolean set = false;
		try {
			volumesVL = (Volume[])volumes;
			volumeType = "Volume[]>";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				volumesSL = Converter.convertStringList(volumes);
				volumeType = "String[]";
			}
		}
	}
	
}

package com.test.quickstart;

import java.util.Map;



public class Service {
	private TypeConverter Converter = new TypeConverter();
	private String Name;
	private Object build;
	private String buildS;
	private Build buildB;
	private String buildType;
	private Object configs;
	private Map<String,Configs> configsMC;
	private String[] configsSL;
	private String configType;
	private String[] depends_on;
	private Deploy deploy;
	private Object labels;
	private Map<String,String> labelsM;
	private String[] labelsS;
	private String labelType;
	private String image;
	private String init;
	private String[] links;
	private String logging;
	private Object networks;
	private String[] networksSL;
	private Map<String,Network> networksM;
	private String networkType; 
	private Object ports;
	private String[] portsSL;
	private Ports portsP; 
	private String portsType;
	private Object volumes;
	private Volume[] volumesVL;
	private String[] volumesSL;
	private String volumeType;
	
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
	public Object getConfigs() {
		return configs;
	}
	public void setConfigs(Object configs) {
		this.configs = configs;
		convertConfigs();
	}
	public Map<String, Configs> getConfigsMC() {
		return configsMC;
	}
	public void setConfigsMC(Map<String, Configs> configsMC) {
		this.configsMC = configsMC;
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
	public String getLogging() {
		return logging;
	}
	public void setLogging(String logging) {
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
	private void convertConfigs() 
	{
		boolean set = false;
		try {
			configsMC = Converter.convertMapSC(configs);
			configType = "Map<String,Configs>";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				configsSL = Converter.convertStringList(configs);
				configType = "String[]";
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

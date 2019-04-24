package com.test.quickstart;


import java.util.Map;

import java.util.Set;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.CheckUsed;
import com.test.quickstart.Validation.Interfaces.ContainsString;
import com.test.quickstart.TypeEnums.Type;
import com.test.quickstart.Validation.ValidationEnums;


public class TopLevel {
	private TypeConverter converter = new TypeConverter();
	private TypeResolver resolver = new TypeResolver();
	private Map<String,Configs> configs;
	private Object networks;
	@CheckDuplication(message = "Duplicate network detected")
	private String[] networksSL;
	private Map<String, Network> networksN;
	private Type networkType;
	private Object secrets;
	@CheckDuplication(message = "Duplicate secret detected")
	private String[] secretsL;
	private Secrets[] secretsSL;
	private Type secretsType;
	private Map<String, Volume> volumes;
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
	public Type getNetworkType() {
		return networkType;
	}
	public void setNetworkType(Type networkType) {
		this.networkType = networkType;
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
			this.secretsType = Type.STRINGlIST;
		}
		else 
		{
			secretsAL = (Map<String, Map<String,Object>>)secrets;
			secretsSL = converter.convertSecrets(secretsAL);
			this.secretsType = Type.SECRETLIST;
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
		if(this.secretsType == Type.STRINGlIST)
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
		if(getNetworkType() == Type.STRINGlIST)
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
	
	
	
	

	private void convertNetworks() throws Exception 
	{
		Map<String, Map<String,Object>> networksAL = null;;
		if(resolver.checkStringList(networks) == true)
		{
			this.networksSL = converter.convertStringList(networks);
			this.networkType = Type.STRINGlIST;
		}
		else if(resolver.checkNestedMap(networks) == true)
		{
			networksAL = (Map<String, Map<String,Object>>)networks;
			networksN = converter.convertNetworks(networksAL);
			this.networkType = Type.MAP_STRING_NETWORK;
		}
		else if(resolver.checkMap(networks))
		{
			Map<String,String> map = converter.convertMap(networks);
			Set<String> keys = map.keySet();
			String[] netList = keys.toArray(new String[keys.size()]);
			this.networksSL = netList;
			this.networkType = Type.STRINGlIST;
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

	
}

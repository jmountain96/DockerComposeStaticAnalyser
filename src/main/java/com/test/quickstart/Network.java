package com.test.quickstart;

import java.util.Map;

import com.test.quickstart.TypeEnums.Type;
import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;

public class Network {
	private TypeConverter converter = new TypeConverter();
	private TypeResolver resolver = new TypeResolver();
	@CheckDuplication(message = "Duplicate network alias detected")
	private String[] aliases;
	@CheckStringFormat(message = "Incorrect format for network ipv4_address", value = ValidationEnums.CheckStringType.DNS)
	private String ipv4_address;
	@CheckStringFormat(message = "Incorrect format for network ipv4_address", value = ValidationEnums.CheckStringType.IPV6)
	private String  ipv6_address;
	@ContainsString(message = "Invalid network driver", value = ValidationEnums.ContainsStringType.NETWORK_MODE)
	private String driver;
	private Map<String,String> driver_opts;
	@ContainsString(message = "Enable ipv6 field of network must be a boolean value", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String enable_ipv6;
	@ContainsString(message = "External of network must be a boolean value", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String external;
	@ContainsString(message = "Attachable field of network must be a boolean value", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String attachable;
	private Ipam ipam;
	@ContainsString(message = "Internal field of network must be a boolean value", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String internal;
	private Object labels;
	private Map<String,String> labelsM;
	@CheckDuplication(message = "Duplicate network lable detected")
	private String[] labelsS;
	private Type labelType;
	private String[] link_local_ips;
	private String priority;
	private String name;
	
	
	public TypeConverter getConverter() {
		return converter;
	}
	public void setConverter(TypeConverter converter) {
		this.converter = converter;
	}
	public String[] getAliases() {
		return aliases;
	}
	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}
	public String getIpv4_address() {
		return ipv4_address;
	}
	public void setIpv4_address(String ipv4_address) {
		this.ipv4_address = ipv4_address;
	}
	public String getIpv6_address() {
		return ipv6_address;
	}
	public void setIpv6_address(String ipv6_address) {
		this.ipv6_address = ipv6_address;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public Map<String, String> getDriver_opts() {
		return driver_opts;
	}
	public void setDriver_opts(Map<String, String> driver_opts) {
		this.driver_opts = driver_opts;
	}
	public String getEnable_ipv6() {
		return enable_ipv6;
	}
	public void setEnable_ipv6(String enable_ipv6) {
		this.enable_ipv6 = enable_ipv6;
	}
	public String getExternal() {
		return external;
	}
	public void setExternal(String external) {
		this.external = external;
	}
	public String getAttachable() {
		return attachable;
	}
	public void setAttachable(String attachable) {
		this.attachable = attachable;
	}
	public Ipam getIpam() {
		return ipam;
	}
	public void setIpam(Ipam ipam) {
		this.ipam = ipam;
	}
	public String getInternal() {
		return internal;
	}
	public void setInternal(String internal) {
		this.internal = internal;
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
	public String[] getLink_local_ips() {
		return link_local_ips;
	}
	public void setLink_local_ips(String[] link_local_ips) {
		this.link_local_ips = link_local_ips;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private void convertLabels() throws Exception 
	{
		
		if(resolver.checkMap(labels) == true)
		{
			labelsM = converter.convertMap(labels);
			labelType = Type.MAP_STRING_STRING;
		}
		
		else if(resolver.checkStringList(labels) == true)
		{
			labelsS = converter.convertStringList(labels);
			labelType = Type.STRINGlIST;
		}
		else 
		{
			throw new Exception("Unknown type for Network Labels");
		}
	}
	
}

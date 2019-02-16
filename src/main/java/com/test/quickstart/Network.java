package com.test.quickstart;

import java.util.Map;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;

public class Network {
	private TypeConverter converter = new TypeConverter();
	private String[] aliases;
	@CheckStringFormat(message = "Incorrect format for network ipv4_address", value = ValidationEnums.CheckStringType.DNS)
	private String ipv4_address;
	@CheckStringFormat(message = "Incorrect format for network ipv4_address", value = ValidationEnums.CheckStringType.IPV6)
	private String  ipv6_address;
	@ContainsString(message = "Invalid network driver", value = ValidationEnums.ContainsStringType.NETWORK_MODE)
	private String driver;
	private Map<String,String> driver_opts;
	@ContainsString(message = "Enable ipv6 field must be a boolean value", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String enable_ipv6;
	@ContainsString(message = "External must be a boolean value", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String external;
	@ContainsString(message = "Attachable field must be a boolean value", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String attachable;
	private Ipam ipam;
	private String internal;
	private Object labels;
	private Map<String,String> labelsM;
	private String[] labelsS;
	private String labelType;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}

package com.test.quickstart;

import java.util.Map;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;

public class Network {
	private TypeConverter converter = new TypeConverter();
	private String[] aliases;
	@CheckStringFormat(message = "Incorrect format for network ipv4_address", value = ValidationEnums.CheckStringType.DNS)
	private String ipv4_address;
	@CheckStringFormat(message = "Incorrect format for network ipv4_address", value = ValidationEnums.CheckStringType.IPV6)
	private String  ipv6_address;
	private String driver;
	private Map<String,String> driver_opts;
	private Object external;
	private Map<String,String> externalM;
	private String externalS;
	private String externalType;
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
	public Object getExternal() {
		return external;
	}
	public void setExternal(Object external) {
		this.external = external;
		convertExternal();
	}
	public Map<String, String> getExternalM() {
		return externalM;
	}
	public void setExternalM(Map<String, String> externalM) {
		this.externalM = externalM;
	}
	public String getExternalS() {
		return externalS;
	}
	public void setExternalS(String externalS) {
		this.externalS = externalS;
	}
	public String getExternalType() {
		return externalType;
	}
	public void setExternalType(String externalType) {
		this.externalType = externalType;
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
	private void convertExternal() 
	{
		boolean set = false;
		String tExternal = external.toString();
		try {
			externalM = converter.convertMap(external);
			externalType = "Map<String,String[]>";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				externalS = tExternal;
				externalType = "String";
			}
		}
	}
}

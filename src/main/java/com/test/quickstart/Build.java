package com.test.quickstart;

import java.util.Map;
import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckFileExists;
import com.test.quickstart.Validation.Interfaces.CheckFolderExists;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.CheckStringListFormat;

public class Build {
	private TypeConverter Converter = new TypeConverter();
	@CheckFolderExists(message = "Directory doesn't exist")
	private String context;
	private String dockerfile;
	@CheckFileExists(message = "Referenced alternate Docker file doesn't exist", value = "")
	private String dockerfileContext;
	@CheckStringListFormat(message = "extra hosts doesn't specify valid host addresses", value = ValidationEnums.CheckStringListType.EXTRAHOST)
	@CheckDuplication(message = "Duplicate extra host detected")
	private String[] extra_hosts;
	private Object args;
	private Map<String, String> argsM;
	@CheckDuplication(message = "Duplicate arg detected")
	private String[] argsS;
	private String argType;
	@CheckStringListFormat(message = "images in cache_from are in the wrong format", value = ValidationEnums.CheckStringListType.IMAGE)
	@CheckDuplication(message = "Duplicate cache_from detected in build")
	private String[] cache_from;
	private Object labels;
	private Map<String,String> labelsM;
	@CheckDuplication(message = "Duplicate label detected in build")
	private String[] labelsS;
	private String labelType;
	@CheckStringFormat(message = "memory in shm_size are in the wrong format", value = ValidationEnums.CheckStringType.MEMORY)
	private String shm_size;
	private String target;
	private String network;
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getDockerfile() {
		return dockerfile;
	}
	public void setDockerfile(String dockerfile) {
		this.dockerfile = dockerfile;
		this.dockerfileContext = this.context + dockerfile;
	}
	public Object getArgs() {
		return args;
	}
	public void setArgs(Object args) {
		this.args = args;
		convertArgs();
	}
	public Map<String, String> getArgsM() {
		return argsM;
	}
	public void setArgsM(Map<String, String> argsM) {
		this.argsM = argsM;
	}
	public String[] getArgsS() {
		return argsS;
	}
	public void setArgsS(String[] argsS) {
		this.argsS = argsS;
	}
	public String getArgType() {
		return argType;
	}
	public void setArgType(String argType) {
		this.argType = argType;
	}
	public String[] getCache_from() {
		return cache_from;
	}
	public void setCache_from(String[] cache_from) {
		this.cache_from = cache_from;
	}
	public String[] getExtra_hosts() {
		return extra_hosts;
	}
	public void setExtra_hosts(String[] extra_hosts) {
		this.extra_hosts = extra_hosts;
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
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getShm_size() {
		return shm_size;
	}
	public void setShm_size(String shm_size) {
		this.shm_size = shm_size;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	private void convertArgs() 
	{
		boolean set = false;
		try {
			argsM = Converter.convertMap(args);
			argType = "Map<String,String>";
			set = true;
		}
		catch(java.lang.ClassCastException e){}
		finally {
			if(set == false) 
				{
				argsS = Converter.convertStringList(args);
				argType = "String[]";
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
}

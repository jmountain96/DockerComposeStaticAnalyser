package com.test.quickstart;

import java.util.Map;
import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckFolderExists;
import com.test.quickstart.Validation.Interfaces.ContainsString;
public class Volume {
	private TypeConverter converter = new TypeConverter();
	@ContainsString(message = "Invalid voume type", value = ValidationEnums.ContainsStringType.VOLUME_TYPE)
	private String type;
	private String source;
	@CheckFolderExists(message = "Volume target folder doesn't exist")
	private String target;
	@ContainsString(message = "Volume read only must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String read_only;
	private Map<String,String> bind;
	private Map<String,String> volume;
	private Map<String,String> tmpfs;
	@ContainsString(message = "Invalid voume type", value = ValidationEnums.ContainsStringType.VOLUME_CONSISTENCY)
	private String consistency;
	private String driver;
	private Map<String,String> driver_opts;
	private Object external;
	private Map<String, String> externalM;
	private String externalS;
	private String externalType;
	private Object labels;
	private Map<String,String> labelsM;
	@CheckDuplication(message = "Duplicate volume label detected")
	private String[] labelsS;
	private String labelType;
	private String name;
	
	
	
	public TypeConverter getConverter() {
		return converter;
	}
	public void setConverter(TypeConverter converter) {
		this.converter = converter;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getRead_only() {
		return read_only;
	}
	public void setRead_only(String read_only) {
		this.read_only = read_only;
	}
	public Map<String, String> getBind() {
		return bind;
	}
	public void setBind(Map<String, String> bind) {
		this.bind = bind;
	}
	public Map<String, String> getVolume() {
		return volume;
	}
	public void setVolume(Map<String, String> volume) {
		this.volume = volume;
	}
	public Map<String, String> getTmpfs() {
		return tmpfs;
	}
	public void setTmpfs(Map<String, String> tmpfs) {
		this.tmpfs = tmpfs;
	}
	public String getConsistency() {
		return consistency;
	}
	public void setConsistency(String consistency) {
		this.consistency = consistency;
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

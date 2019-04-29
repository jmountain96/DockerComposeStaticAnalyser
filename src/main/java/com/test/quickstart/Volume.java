package com.test.quickstart;

import java.util.Map;

import com.test.quickstart.TypeEnums.Type;
import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckDuplication;
import com.test.quickstart.Validation.Interfaces.CheckFolderExists;
import com.test.quickstart.Validation.Interfaces.ContainsString;
public class Volume {
	private TypeConverter converter = new TypeConverter();
	private TypeResolver Resolver = new TypeResolver();
	@ContainsString(message = "Invalid volume type", value = ValidationEnums.ContainsStringType.VOLUME_TYPE)
	private String type;
	private String source;
	@CheckFolderExists(message = "Volume target folder doesn't exist")
	private String target;
	@ContainsString(message = "Volume read only must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String read_only;
	private Bind bind;
	private VolumeV volume;
	private TMPFS tmpfs;
	@ContainsString(message = "Invalid volume consistency", value = ValidationEnums.ContainsStringType.VOLUME_CONSISTENCY)
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
	private Type labelType;
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
	public Bind getBind() {
		return bind;
	}
	public void setBind(Bind bind) {
		this.bind = bind;
	}
	public VolumeV getVolume() {
		return volume;
	}
	public void setVolume(VolumeV volume) {
		this.volume = volume;
	}
	public TMPFS getTmpfs() {
		return tmpfs;
	}
	public void setTmpfs(TMPFS b) {
		this.tmpfs = b;
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
	public void setExternal(Object external) throws Exception {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private void convertExternal() throws Exception 
	{
		if(Resolver.checkMap(external) == true)
		{
			externalM = converter.convertMap(external);
			externalType = "Map<String,String>";
		}
		else if(Resolver.checkString(external) == true)
		{
			externalS = external.toString();
			externalType = "String";
		}
		else 
		{
			throw new Exception("Unknown type for Config External");
		}
	}
	private void convertLabels() throws Exception 
	{
		if(Resolver.checkMap(labels) == true)
		{
			labelsM = converter.convertMap(labels);
			labelType = Type.MAP_STRING_STRING;
		}
		
		else if(Resolver.checkStringList(labels) == true)
		{
			labelsS = converter.convertStringList(labels);
			labelType = Type.STRINGlIST;
		}
		else 
		{
			throw new Exception("Unknown type for Build Labels");
		}
	}
}

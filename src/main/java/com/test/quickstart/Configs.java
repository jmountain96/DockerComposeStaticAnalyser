package com.test.quickstart;

import java.util.Map;

import com.test.quickstart.Validation.Interfaces.CheckFileExists;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.ValidationEnums;
public class Configs {
	private TypeConverter converter = new TypeConverter();
	private TypeResolver resolver = new TypeResolver();
	private String name;
	@CheckFileExists(message = "Cannot find path to config file", value = "")
	private String file;
	private Object external;
	private Map<String, String> externalM;
	private String externalS;
	private String externalType;
	private String source;
	private String target;
	private String uid;
	private String gid;
	@CheckStringFormat(message = "Invalid Config permission mode", value = ValidationEnums.CheckStringType.UNIX_PERM)
	private String mode;
	
	public TypeConverter getConverter() {
		return converter;
	}

	public void setConverter(TypeConverter converter) {
		this.converter = converter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	private void convertExternal() throws Exception 
	{
		if(resolver.checkMap(external) == true)
		{
			externalM = converter.convertMap(external);
			externalType = "Map<String,String>";
		}
		else if(resolver.checkString(external) == true)
		{
			externalS = external.toString();
			externalType = "String";
		}
		else 
		{
			throw new Exception("Unknown type for Config External");
		}
	}
}

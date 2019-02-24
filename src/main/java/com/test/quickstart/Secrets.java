package com.test.quickstart;

import java.util.Map;
import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.CheckFileExists;
import com.test.quickstart.Validation.Interfaces.CheckStringFormat;
import com.test.quickstart.Validation.Interfaces.ContainsString;
public class Secrets {
	private TypeConverter converter = new TypeConverter();
	private String source;
	private String target;
	private int uid;
	private int gid;
	@CheckStringFormat(message = "Invalid Secrets permission mode", value = ValidationEnums.CheckStringType.UNIX_PERM)
	private String mode;
	private String name;
	@CheckFileExists(message = "Secrets file doesn't exist", value = "")
	private String file;
	private Object external;
	private Map<String, String> externalM;
	@ContainsString(message = "External must be a boolean", value = ValidationEnums.ContainsStringType.BOOLEAN)
	private String externalS;
	private String externalType;
	
	public TypeConverter getConverter() {
		return converter;
	}

	public void setConverter(TypeConverter converter) {
		this.converter = converter;
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

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

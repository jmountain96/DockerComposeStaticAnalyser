package com.test.quickstart;

import java.util.Map;

import com.test.quickstart.Validation.ValidationEnums;
import com.test.quickstart.Validation.Interfaces.ContainsString;

public class Deploy {
	private TypeConverter Converter = new TypeConverter();
	private TypeResolver Resolver = new TypeResolver();
	@ContainsString(message = "Endpoint mode must be vip or dnsrr", value = ValidationEnums.ContainsStringType.ENDPOINT_MODE)
	private String endpoint_mode;
	private Object labels;
	private Map<String,String> labelsM;
	private String[] labelsS;
	private String labelType;
	@ContainsString(message = "Deploy mode must be global or replicate", value = ValidationEnums.ContainsStringType.DEPLOY_MODE)
	private String mode;
	private Placement placement;
	private int replicas;
	private Resources resources;
	private RestartPolicy restart_policy;
	private RollbackConfig rollback_config;
	private UpdateConfig update_config;
	
	public TypeConverter getConverter() {
		return Converter;
	}

	public void setConverter(TypeConverter converter) {
		Converter = converter;
	}

	public String getEndpoint_mode() {
		return endpoint_mode;
	}

	public void setEndpoint_mode(String endpoint_mode) {
		this.endpoint_mode = endpoint_mode;
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

	public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Placement getPlacement() {
		return placement;
	}

	public void setPlacement(Placement placement) {
		this.placement = placement;
	}

	public int getReplicas() {
		return replicas;
	}

	public void setReplicas(int replicas) {
		this.replicas = replicas;
	}

	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	public RestartPolicy getRestart_policy() {
		return restart_policy;
	}

	public void setRestart_policy(RestartPolicy restart_policy) {
		this.restart_policy = restart_policy;
	}

	public RollbackConfig getRollback_config() {
		return rollback_config;
	}

	public void setRollback_config(RollbackConfig rollback_config) {
		this.rollback_config = rollback_config;
	}

	public UpdateConfig getUpdate_config() {
		return update_config;
	}

	public void setUpdate_config(UpdateConfig update_config) {
		this.update_config = update_config;
	}

	private void convertLabels() throws Exception 
	{
		if(Resolver.checkMap(labels) == true)
		{
			labelsM = Converter.convertMap(labels);
			labelType = "Map<String,String>";
		}
		
		else if(Resolver.checkStringList(labels) == true)
		{
			labelsS = Converter.convertStringList(labels);
			labelType = "String[]";
		}
		else 
		{
			throw new Exception("Unknown type for Labels Args");
		}
	}
}

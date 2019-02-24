package com.test.quickstart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlParser {
	 public static void main(String[] args) throws YamlException, FileNotFoundException {
	        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	        TopLevel level = new TopLevel();
	        try {
	            level = mapper.readValue(new File("test.yaml"), TopLevel.class);
	            System.out.println(ReflectionToStringBuilder.toString(level,ToStringStyle.MULTI_LINE_STYLE));
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        level = setDependencies(level);
	        Validate(level);
	    }
	 /**
	  * Sets the required values within the input for dependency validation
	  * @param input The input to be validated
	  * @return
	  */
	 public static TopLevel setDependencies(TopLevel input) 
	 {
		 if(input.getNetwork_mode() != null)
		 {
			 input.setNetworkModeDependencies();
		 }
		 if(input.getServices() != null)
	     {
			int index = 0;
			Service[] retServices = new Service[input.getServices().size()];
			for(Service s : input.getServices().values()) 
			{
				Map<String, Service> serviceList = input.getServices();
				String[] services = serviceList.keySet().toArray(new String[serviceList.size()]);
				if(s.getDepends_on() != null)
				{
					s.setServiceDependenciesD(services);
				}
				if(s.getLinks() != null)
				{
					s.setLinkDependencies(services);
				}
				if(input.getConfigs() != null && s.getConfigs() != null) 
				{
					Map<String,Configs> configList = input.getConfigs();
					String[] configs = configList.keySet().toArray(new String[configList.size()]);
					s.setConfigDependencies(configs);
				}
				if(input.getNetworks() != null && s.getNetworks() != null)
				{
					String[] networks;
					if(input.getNetworkType() == "String[]")
					{
						networks = input.getNetworksSL();
					}
					else
					{
						Map<String,Network> networkList = input.getNetworksM();
						networks = networkList.keySet().toArray(new String[networkList.size()]);
					}
					s.setNetworkDependencies(networks);
				}
				if(input.getSecrets() != null && s.getSecrets() != null)
				{
					String[] secrets;
					if(input.getNetworkType() == "String[]")
					{
						secrets = input.getSecretsL();
						s.setSecretDependencies(secrets);
					}
					else
					{
						int index2 = 0;
						Secrets[] secretsList = input.getSecretsSL();
						secrets = new String[secretsList.length];
						for(Secrets c : secretsList)
						{
							secrets[index2] = c.getSource();
							index2++;
						}
						s.setSecretDependencies(secrets);
					}
				}
				if(input.getVolumes() != null && s.getVolumes() != null) 
				{
					Map<String,Volume> volumesList = input.getVolumes();
					String[] volumes = volumesList.keySet().toArray(new String[volumesList.size()]);
					s.setVolumeDependencies(volumes);
				}
				if(input.getEnv_file() != null && s.getEnv_file() != null)
				{
					if(input.getEnv_FileType() == "String") 
					{
						String[] envList = new String[0];
						envList[0] = input.getEnv_fileS();
						s.setEnvironmentDependencies(envList);
					}
					else 
					{
						String[] envList = input.getEnv_fileSL();
						s.setEnvironmentDependencies(envList);
					}
				}
				retServices[index] = s;
				index++;
			}
		}
		 return input;
	 }
	 /**
	  * Validates all objects that exist within the input
	  * @param level
	  */
	 public static void Validate(TopLevel level)
	 {
	     ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	     Validator validator = factory.getValidator();
	     Set<ConstraintViolation<TopLevel>> constraintViolations = validator.validate(level);
	     for (ConstraintViolation<TopLevel> violation : constraintViolations) 
	     {
	          System.out.println(violation.getMessage());
	     }
	     if(level.getBuildB() != null) {
	    	 Set<ConstraintViolation<Build>> constraintViolationsB = validator.validate(level.getBuildB());
	    	 for (ConstraintViolation<Build> violation : constraintViolationsB) 
	    	 {
	    		 System.out.println(violation.getMessage());
	    	 }
	     }
	     if(level.getCredential_spec() != null) {
	    	 Set<ConstraintViolation<CredentialSpec>> constraintViolationsC = validator.validate(level.getCredential_spec());
	    	 for (ConstraintViolation<CredentialSpec> violation : constraintViolationsC) 
	    	 {
	    		 System.out.println(violation.getMessage());
	    	 }
	     }
	     if(level.getHealthcheck()!= null)
	     {
	    	 Set<ConstraintViolation<Healthcheck>> constraintViolationsH = validator.validate(level.getHealthcheck());
	    	 for (ConstraintViolation<Healthcheck> violation : constraintViolationsH) 
	    	 {
	    		 System.out.println(violation.getMessage());
	    	 } 
	     }
	     if(level.getNetworksM() != null)
	     {
	    	 for(Network n : level.getNetworksM().values())
    		 {
    			 Set<ConstraintViolation<Network>> constraintViolationsN = validator.validate(n);
    			 for (ConstraintViolation<Network> violation : constraintViolationsN) 
    			 {
    				 System.out.println(violation.getMessage());
    			 }
    			 if(n.getIpam() != null)
    			 {
    				 Set<ConstraintViolation<Ipam>> constraintViolationsNI = validator.validate(n.getIpam());
        			 for (ConstraintViolation<Ipam> violation : constraintViolationsNI) 
        			 {
        				 System.out.println(violation.getMessage());
        			 }
    			 }
    		 }
	     }
	     if(level.getPortsP() != null)
	     {
	    	 for(Ports p : level.getPortsP()) 
	    	 {
	    		 Set<ConstraintViolation<Ports>> constraintViolationsP = validator.validate(p);
		    	 for (ConstraintViolation<Ports> violation : constraintViolationsP) 
		    	 {
		    		 System.out.println(violation.getMessage());
		    	 }
	    	 }
	     }
	     if(level.getSecretsType() == "Secrets[]")
	     {
	    	 for(Secrets s : level.getSecretsSL()) 
	    	 {
	    		 Set<ConstraintViolation<Secrets>> constraintViolationsS = validator.validate(s);
		    	 for (ConstraintViolation<Secrets> violation : constraintViolationsS) 
		    	 {
		    		 System.out.println(violation.getMessage());
		    	 }
	    	 }
	     }
	     if(level.getUlimits() != null)
	     {
	    	 Set<ConstraintViolation<Ulimits>> constraintViolationsH = validator.validate(level.getUlimits());
	    	 for (ConstraintViolation<Ulimits> violation : constraintViolationsH) 
	    	 {
	    		 System.out.println(violation.getMessage());
	    	 }
	    	 if(level.getUlimits().getNofile() != null)
	    	 {
	    		 Set<ConstraintViolation<Nofile>> constraintViolationsUNF = validator.validate(level.getUlimits().getNofile());
		    	 for (ConstraintViolation<Nofile> violation : constraintViolationsUNF) 
		    	 {
		    		 System.out.println(violation.getMessage());
		    	 } 
	    	 }
	     }
	     if(level.getServices() != null)
	     {
	    	 for(Service s : level.getServices().values()) 
	    	 {
	    		 Set<ConstraintViolation<Service>> constraintViolationsSE = validator.validate(s);
		    	 for (ConstraintViolation<Service> violation : constraintViolationsSE) 
		    	 {
		    		 System.out.println(violation.getMessage());
		    	 }
		    	 if(s.getBuildB() != null)
		    	 {
		    		 Set<ConstraintViolation<Build>> constraintViolationsSB = validator.validate(s.getBuildB());
			    	 for (ConstraintViolation<Build> violation : constraintViolationsSB) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    	 } 
		    	 }
		    	 if(s.getDeploy() != null)
		    	 {
		    		 Set<ConstraintViolation<Deploy>> constraintViolationsSD = validator.validate(s.getDeploy());
			    	 for (ConstraintViolation<Deploy> violation : constraintViolationsSD) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    	 }
			    	 if(s.getDeploy().getPlacement() != null)
			    	 {
			    		 Set<ConstraintViolation<Placement>> constraintViolationsSDP = validator.validate(s.getDeploy().getPlacement());
				    	 for (ConstraintViolation<Placement> violation : constraintViolationsSDP) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    	 } 
			    	 }
			    	 if(s.getDeploy().getResources() != null)
			    	 {
			    		 Set<ConstraintViolation<Resources>> constraintViolationsSDR = validator.validate(s.getDeploy().getResources());
				    	 for (ConstraintViolation<Resources> violation : constraintViolationsSDR) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    	 } 
			    	 }
			    	 if(s.getDeploy().getRestart_policy() != null)
			    	 {
			    		 Set<ConstraintViolation<RestartPolicy>> constraintViolationsSDRP = validator.validate(s.getDeploy().getRestart_policy());
				    	 for (ConstraintViolation<RestartPolicy> violation : constraintViolationsSDRP) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    	 } 
			    	 }
			    	 if(s.getDeploy().getRollback_config() != null)
			    	 {
			    		 Set<ConstraintViolation<RollbackConfig>> constraintViolationsSDRC = validator.validate(s.getDeploy().getRollback_config());
				    	 for (ConstraintViolation<RollbackConfig> violation : constraintViolationsSDRC) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    	 } 
			    	 }
			    	 if(s.getDeploy().getUpdate_config() != null)
			    	 {
			    		 Set<ConstraintViolation<UpdateConfig>> constraintViolationsSDUC = validator.validate(s.getDeploy().getUpdate_config());
				    	 for (ConstraintViolation<UpdateConfig> violation : constraintViolationsSDUC) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    	 } 
			    	 }
			    	 
		    	 }
		    	 if(s.getLogging() != null)
		    	 {
		    		 Set<ConstraintViolation<Logging>> constraintViolationsSL = validator.validate(s.getLogging());
			    	 for (ConstraintViolation<Logging> violation : constraintViolationsSL) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    	 } 
		    	 }
		    	 if(s.getNetworksM() != null)
		    	 {
		    		 for(Network n : s.getNetworksM().values())
		    		 {
		    			 Set<ConstraintViolation<Network>> constraintViolationsSN = validator.validate(n);
		    			 for (ConstraintViolation<Network> violation : constraintViolationsSN) 
		    			 {
		    				 System.out.println(violation.getMessage());
		    			 } 
		    		 }
		    	 }
		    	 if(s.getPortsP() != null)
		    	 {
		    		 for(Ports p : s.getPortsP())
		    		 {
		    			 Set<ConstraintViolation<Ports>> constraintViolationsSP = validator.validate(p);
		    			 for (ConstraintViolation<Ports> violation : constraintViolationsSP) 
		    			 {
		    				 System.out.println(violation.getMessage());
		    			 } 
		    		 }
		    	 }
		    	 if(s.getVolumesVL() != null)
		    	 {
		    		 for(Volume v : s.getVolumesVL())
		    		 {
		    			 Set<ConstraintViolation<Volume>> constraintViolationsSV = validator.validate(v);
		    			 for (ConstraintViolation<Volume> violation : constraintViolationsSV) 
		    			 {
		    				 System.out.println(violation.getMessage());
		    			 } 
		    		 }
		    	 }
	    	 } 
	     }
	 }
	 
	 
}

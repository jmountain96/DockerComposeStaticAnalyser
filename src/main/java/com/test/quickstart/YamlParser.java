package com.test.quickstart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;



import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class YamlParser {
	 public static void main(String[] args) throws IOException {
	        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	     
	        TopLevel level = new TopLevel();
	      
	        try {
	            level = mapper.readValue(new File("test.yaml"), TopLevel.class);
	            System.out.println(ReflectionToStringBuilder.toString(level,ToStringStyle.MULTI_LINE_STYLE));
	        }
	        catch(com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException UPE)
	        {
	        	System.out.println(UPE.getMessage());
	        }
	        catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        checkForDuplicateKeys();
	        level = setDependencies(level);
	        Validate(level);
	        checkUsed(level);
	    
	        
	    }
	 /**
	  * Sets the required values within the input for dependency validation
	  * @param input The input to be validated
	  * @return
	  */
	 public static TopLevel setDependencies(TopLevel input) 
	 {
		 YamlParserVisitor visitor = new YamlParserVisitorImpl();
		 TopLevelReturn r = input.accept(visitor);
		 if(input.getNetwork_mode() != null)
		 {
			 input.setNetworkModeDependencies();
		 }
		 if(input.getServices() != null)
	     {
			
			for(Service s : input.getServices().values()) 
			{
				if(r.getConfigs() != null)
				{
					s.setConfigDependencies(r.getConfigs());
				}
				if(r.getEnvList() != null)
				{
					s.setEnvironmentDependencies(r.getEnvList());
				}
				if(r.getNetworks() != null)
				{
					s.setNetworkDependencies(r.getNetworks());
				}
				if(r.getSecrets() != null)
				{
					s.setSecretDependencies(r.getSecrets());
				}
				if(r.getServices() != null)
				{
					s.setServiceDependenciesD(r.getServices());
				}
				if(r.getVolumes() != null)
				{
					s.setVolumeDependencies(r.getVolumes());
				}
				
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
	 private static void checkForDuplicateKeys()
	 {
		 
     	try {
            YamlConfig yamlConfig = new YamlConfig();
            yamlConfig.setAllowDuplicates(false); // default value is true
            YamlReader reader = new YamlReader(new FileReader("test.yaml"), yamlConfig);
            Object object = reader.read();
        } catch (YamlException ex) {
            System.out.println(ex.getMessage());
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	        
	 }
	 
	 private static void checkUsed(TopLevel input) 
	 {
		boolean found = false; 
		YamlParserVisitor visitor = new YamlParserVisitorImpl();
		TopLevelReturn r = input.accept(visitor);
		if(r.getConfigs() != null) 
		{
			
			String[] configs = r.getConfigs();
			for(String config : configs)
			{
				 found = false;
				 for(Service s : input.getServices().values())
				 {
					 for(String t : s.getConfigDependencies().getDependents())
						 {
							 if(config.equals(t))
							 {
								 found = true;
							 }
						 }
				 }
				 if( found == false)
				 {
					 System.out.println("Config " + config + " is not required by any service");
				 }
			}
		}
		if(r.getNetworks() != null) 
		{
			String[] networks = r.getNetworks();
			
			for(String network : networks)
			{
				 found = false;
				 for(Service s : input.getServices().values())
				 {
					 for(String t : s.getNetworkDependencies().getDependents())
						 {
							 if(network.equals(t))
							 {
								 found = true;
							 }
						 }
				 }
				 if( found == false)
				 {
					 System.out.println("Network " + network + " is not required by any service");
				 }
			}
		}
		if(r.getSecrets() != null)
		{
			String[] secrets = r.getSecrets();
			
			for(String secret : secrets)
			{
				 found = false;
				 for(Service s : input.getServices().values())
				 {
					 
					 for(String t : s.getSecretDependencies().getDependents())
						 {
						 
							 if(secret.equals(t))
							 {
								 found = true;
							 }
						 }
				 }
				 if( found == false)
				 {
					 System.out.println("Secret " + secret + " is not required by any service");
				 }
			}
		}
		if(r.getVolumes() != null)
		{
			
			String[] volumes = r.getVolumes();
			for(String volume : volumes)
			{
				 found = false;
				 for(Service s : input.getServices().values())
				 {
					 for(String t : s.getVolumeDependencies().getDependents())
						 {
							 if(volume.equals(t))
							 {
								 found = true;
							 }
						 }
				 }
				 if( found == false)
				 {
					 System.out.println("Volume " + volume + " is not required by any service");
				 }
			}
		}
		if(r.getEnvList() != null)
		{
			String[] envList = r.getEnvList();
			
			for(String env : envList)
			{
				 found = false;
				 for(Service s : input.getServices().values())
				 {
					 for(String t : s.getEnvironmentDependencies().getDependents())
						 {
							 if(env.equals(t))
							 {
								 found = true;
							 }
						 }
				 }
				 if( found == false)
				 {
					 System.out.println("Enviromental variable " + env + " is not required by any service");
				 }
			} 
		} 
	 } 
}
	 


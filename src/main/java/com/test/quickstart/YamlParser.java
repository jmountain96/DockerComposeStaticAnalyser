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
import com.fasterxml.jackson.databind.JsonMappingException;

public class YamlParser {
	 public static void main(String[] args) throws IOException {
		 TopLevel level = new TopLevel();
		 try 
		 {
	       level = ParseFile("testConfigs/a.docker-compose.yml.txt");
		 }
		 catch(com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException UPE)
	        {
	        	System.out.println(UPE.getMessage());
	        	UPE.printStackTrace();
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
	 public static TopLevel ParseFile(String file) throws JsonParseException, JsonMappingException, IOException
	 {
		 	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	     
	        TopLevel level = new TopLevel();
	      
	        
            level = mapper.readValue(new File(file), TopLevel.class);
            
	        
	
	        return level;
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
				if(r.getConfigs() != null && s.getConfigs() != null)
				{
					s.setConfigDependencies(r.getConfigs());
				}
				if(r.getEnvList() != null && s.getEnv_file() != null)
				{
					s.setEnvironmentDependencies(r.getEnvList());
				}
				if(r.getNetworks() != null && s.getNetworks() != null)
				{
					s.setNetworkDependencies(r.getNetworks());
				}
				if(r.getSecrets() != null && s.getSecrets() != null)
				{
					s.setSecretDependencies(r.getSecrets());
				}
				if(r.getServices() != null)
				{
					s.setServiceDependenciesD(r.getServices());
				}
				if(r.getVolumes() != null && s.getVolumes() != null)
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
	 public static int Validate(TopLevel level)
	 {
		 int totalViolations = 0;
	     ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	     Validator validator = factory.getValidator();
	     Set<ConstraintViolation<TopLevel>> constraintViolations = validator.validate(level);
	     for (ConstraintViolation<TopLevel> violation : constraintViolations) 
	     {
	          System.out.println(violation.getMessage());
	          totalViolations += 1;
	     }
	     if(level.getBuildB() != null) {
	    	 Set<ConstraintViolation<Build>> constraintViolationsB = validator.validate(level.getBuildB());
	    	 for (ConstraintViolation<Build> violation : constraintViolationsB) 
	    	 {
	    		 System.out.println(violation.getMessage());
	    		 totalViolations += 1;
	    	 }
	     }
	     if(level.getCredential_spec() != null) {
	    	 Set<ConstraintViolation<CredentialSpec>> constraintViolationsC = validator.validate(level.getCredential_spec());
	    	 for (ConstraintViolation<CredentialSpec> violation : constraintViolationsC) 
	    	 {
	    		 System.out.println(violation.getMessage());
	    		 totalViolations += 1;
	    	 }
	     }
	     if(level.getHealthcheck()!= null)
	     {
	    	 Set<ConstraintViolation<Healthcheck>> constraintViolationsH = validator.validate(level.getHealthcheck());
	    	 for (ConstraintViolation<Healthcheck> violation : constraintViolationsH) 
	    	 {
	    		 System.out.println(violation.getMessage());
	    		 totalViolations += 1;
	    	 } 
	     }
	     if(level.getNetworksN() != null)
	     {
	    	 for(String net : level.getNetworksN().keySet())
    		 {
	    		 Network n = level.getNetworksN().get(net);
    			 Set<ConstraintViolation<Network>> constraintViolationsN = validator.validate(n);
    			 for (ConstraintViolation<Network> violation : constraintViolationsN) 
    			 {
    				 System.out.println(violation.getMessage());
    				 totalViolations += 1;
    			 }
    			 if(n.getIpam() != null)
    			 {
    				 Set<ConstraintViolation<Ipam>> constraintViolationsNI = validator.validate(n.getIpam());
        			 for (ConstraintViolation<Ipam> violation : constraintViolationsNI) 
        			 {
        				 System.out.println(violation.getMessage());
        				 totalViolations += 1;
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
		    		 totalViolations += 1;
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
		    		 totalViolations += 1;
		    	 }
	    	 }
	     }
	     if(level.getUlimits() != null)
	     {
	    	 Set<ConstraintViolation<Ulimits>> constraintViolationsH = validator.validate(level.getUlimits());
	    	 for (ConstraintViolation<Ulimits> violation : constraintViolationsH) 
	    	 {
	    		 System.out.println(violation.getMessage());
	    		 totalViolations += 1;
	    	 }
	    	 if(level.getUlimits().getNofile() != null)
	    	 {
	    		 Set<ConstraintViolation<Nofile>> constraintViolationsUNF = validator.validate(level.getUlimits().getNofile());
		    	 for (ConstraintViolation<Nofile> violation : constraintViolationsUNF) 
		    	 {
		    		 System.out.println(violation.getMessage());
		    		 totalViolations += 1;
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
		    		 totalViolations += 1;
		    	 }
		    	 if(s.getBuildB() != null)
		    	 {
		    		 Set<ConstraintViolation<Build>> constraintViolationsSB = validator.validate(s.getBuildB());
			    	 for (ConstraintViolation<Build> violation : constraintViolationsSB) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    		 totalViolations += 1;
			    	 } 
		    	 }
		    	 if(s.getDeploy() != null)
		    	 {
		    		 Set<ConstraintViolation<Deploy>> constraintViolationsSD = validator.validate(s.getDeploy());
			    	 for (ConstraintViolation<Deploy> violation : constraintViolationsSD) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    		 totalViolations += 1;
			    	 }
			    	 if(s.getDeploy().getPlacement() != null)
			    	 {
			    		 Set<ConstraintViolation<Placement>> constraintViolationsSDP = validator.validate(s.getDeploy().getPlacement());
				    	 for (ConstraintViolation<Placement> violation : constraintViolationsSDP) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
			    	 }
			    	 if(s.getDeploy().getResources() != null)
			    	 {
			    		 Set<ConstraintViolation<Resources>> constraintViolationsSDR = validator.validate(s.getDeploy().getResources());
				    	 for (ConstraintViolation<Resources> violation : constraintViolationsSDR) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
			    	 }
			    	 if(s.getDeploy().getRestart_policy() != null)
			    	 {
			    		 Set<ConstraintViolation<RestartPolicy>> constraintViolationsSDRP = validator.validate(s.getDeploy().getRestart_policy());
				    	 for (ConstraintViolation<RestartPolicy> violation : constraintViolationsSDRP) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
			    	 }
			    	 if(s.getDeploy().getRollback_config() != null)
			    	 {
			    		 Set<ConstraintViolation<RollbackConfig>> constraintViolationsSDRC = validator.validate(s.getDeploy().getRollback_config());
				    	 for (ConstraintViolation<RollbackConfig> violation : constraintViolationsSDRC) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
			    	 }
			    	 if(s.getDeploy().getUpdate_config() != null)
			    	 {
			    		 Set<ConstraintViolation<UpdateConfig>> constraintViolationsSDUC = validator.validate(s.getDeploy().getUpdate_config());
				    	 for (ConstraintViolation<UpdateConfig> violation : constraintViolationsSDUC) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
			    	 }
			    	 
		    	 }
		    	 if(s.getLogging() != null)
		    	 {
		    		 Set<ConstraintViolation<Logging>> constraintViolationsSL = validator.validate(s.getLogging());
			    	 for (ConstraintViolation<Logging> violation : constraintViolationsSL) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    		 totalViolations += 1;
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
		    				 totalViolations += 1;
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
		    				 totalViolations += 1;
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
		    				 totalViolations += 1;
		    			 } 
		    		 }
		    	 }
	    	 } 
	     }
	     return totalViolations;
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
					 if(s.getConfigDependencies().getDependents() != null)
					 {
						 for(String t : s.getConfigDependencies().getDependents())
							 {
								 if(config.equals(t))
								 {
									 found = true;
								 }
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
					 if(s.getNetworkDependencies().getDependents() != null)
					 {
						 for(String t : s.getNetworkDependencies().getDependents())
							 {
								 if(network.equals(t))
								 {
									 found = true;
								 }
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
					 if(s.getSecretDependencies().getDependents() != null)
					 {
						 for(String t : s.getSecretDependencies().getDependents())
							 {
							 
								 if(secret.equals(t))
								 {
									 found = true;
								 }
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
					 if(s.getVolumeDependencies().getDependents() != null)
					 {
						 for(String t : s.getVolumeDependencies().getDependents())
							 {
								 if(volume.equals(t) || t.startsWith(volume))
								 {
									 found = true;
								 }
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
					 if(s.getEnvironmentDependencies().getDependents() != null)
					 {
						 for(String t : s.getEnvironmentDependencies().getDependents())
							 {
								 if(env.equals(t))
								 {
									 found = true;
								 }
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
	 


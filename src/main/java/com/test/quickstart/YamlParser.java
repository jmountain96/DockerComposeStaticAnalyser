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

import org.apache.commons.lang3.ArrayUtils;
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
		 String File = "testConfigs/testValidationPass.yaml";
		 try 
		 {
	       level = ParseFile(File);
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
	        checkForDuplicateKeys(File);
	        level = setDependencies(level);
	        Validate(level);
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
		 String[] Configs = null;
		 String[] EnvList = null;
		 String[] Networks = null;
		 String[] Secrets = null;
		 String[] Volumes = null;
		 if(input.getServices() != null)
	     {
			
			for(Service s : input.getServices().values()) 
			{
				if(r.getConfigs() != null && s.getConfigs() != null)
				{
					s.setConfigDependencies(r.getConfigs());
					if(s.getConfigType() == "String[]")
					{
						Configs = (String[])ArrayUtils.addAll(Configs, s.getConfigsSL());
					}
					else 
					{
						String[] configSources = new String[s.getConfigsC().length];
						for(int i = 0 ; i < configSources.length ; i++)
						{
							configSources[i] = s.getConfigsC()[i].getSource();
							Configs = (String[])ArrayUtils.addAll(Configs, configSources);
						}
				}
				}
				if(r.getEnvList() != null && s.getEnv_file() != null)
				{
					s.setEnvironmentDependencies(r.getEnvList());
					EnvList = (String[])ArrayUtils.addAll(EnvList, s.getEnv_file());
				}
				if(r.getNetworks() != null && s.getNetworks() != null)
				{
					s.setNetworkDependencies(r.getNetworks());
					if(s.getNetworkType()== "String[]") {
						Networks = (String[])ArrayUtils.addAll(Networks, s.getNetworksSL());
					}
					else {
						Networks = (String[])ArrayUtils.addAll(Networks, s.getNetworksM().keySet().toArray(new String[s.getNetworksM().size()] ));
					}
					
				}
				if(r.getSecrets() != null && s.getSecrets() != null)
				{
					s.setSecretDependencies(r.getSecrets());
					
					if(s.getSecretsType() == "String[]")
					{
						Secrets = (String[])ArrayUtils.addAll(Secrets, s.getSecretsL());
					}
					else 
					{
						String[] secretSources = new String[s.getSecretsSL().length];
						for(int i = 0 ; i < secretSources.length ; i++)
						{
							secretSources[i] = s.getSecretsSL()[i].getSource();
						}
						Secrets = (String[])ArrayUtils.addAll(Secrets, secretSources);
					}
				}
				if(r.getServices() != null)
				{
					s.setServiceDependenciesD(r.getServices());
				}
				if(r.getVolumes() != null && s.getVolumes() != null)
				{
					s.setVolumeDependencies(r.getVolumes());
					
					if(s.getVolumeType()== "String[]")
					{
						Volumes = (String[])ArrayUtils.addAll(Volumes, s.getVolumesSL());
					}
					else 
					{
						String[] volumeSources = new String[s.getVolumesVL().length];
						for(int i = 0 ; i < volumeSources.length ; i++)
						{
							volumeSources[i] = s.getVolumesVL()[i].getSource();
						}
						Volumes = (String[])ArrayUtils.addAll(Volumes, volumeSources);
					}
				}
				
			}
				
		}
		 if(Configs != null)
		 {
			 input.setConfigCheckUsed(Configs);
		 }
		 if(EnvList != null)
		 {
			 input.setEnvCheckUsed(EnvList);
		 }
		 if(Networks != null)
		 {
			 input.setNetworkCheckUsed(Networks);
		 }
		 if(Secrets != null)
		 {
			 input.setSecretCheckUsed(Secrets);
		 }
		 if(Volumes != null)
		 {
			 input.setVolumeCheckUsed(Volumes);
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
	 private static void checkForDuplicateKeys(String File)
	 {
		 
     	try {
            YamlConfig yamlConfig = new YamlConfig();
            yamlConfig.setAllowDuplicates(false); // default value is true
            YamlReader reader = new YamlReader(new FileReader(File), yamlConfig);
            Object object = reader.read();
        } catch (YamlException ex) {
            System.out.println(ex.getMessage());
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	        
	 }
	 
}
	 


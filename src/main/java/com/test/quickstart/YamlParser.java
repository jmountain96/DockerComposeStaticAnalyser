package com.test.quickstart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.ArrayUtils;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.fasterxml.jackson.core.JsonParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.test.quickstart.TypeEnums.Type;
import com.fasterxml.jackson.databind.JsonMappingException;

public class YamlParser {
	 public static void main(String[] string) throws IOException 
	 {
		 File f = new File("testConfigs/testValidationPass.yaml");
		 Start(f);
		
	 }
	 public static void Start(File File)
	 {
		 TopLevel level = new TopLevel();
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
	        VersionValidator v = new VersionValidator(level);
	        try {
				v.Validate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 public static TopLevel ParseFile(File file) throws JsonParseException, JsonMappingException, IOException
	 {
		 	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	        TopLevel level = new TopLevel();
            level = mapper.readValue(file, TopLevel.class);
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
		 String[] Configs = null;
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
					if(s.getConfigType() == Type.STRINGlIST)
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
				if(r.getNetworks() != null && s.getNetworks() != null)
				{
					s.setNetworkDependencies(r.getNetworks());
					if(s.getNetworkType()== Type.STRINGlIST) {
						Networks = (String[])ArrayUtils.addAll(Networks, s.getNetworksSL());
					}
					else {
						Networks = (String[])ArrayUtils.addAll(Networks, s.getNetworksM().keySet().toArray(new String[s.getNetworksM().size()] ));
					}
					
				}
				if(r.getSecrets() != null && s.getSecrets() != null)
				{
					s.setSecretDependencies(r.getSecrets());
					
					if(s.getSecretsType() == Type.STRINGlIST)
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
					if(s.getNetwork_mode() != null)
					{
						s.setNetworkModeDependencies(r.getServices());
					}
				}
				if(r.getVolumes() != null && s.getVolumes() != null)
				{
					s.setVolumeDependencies(r.getVolumes());
					
					if(s.getVolumeType()== Type.STRINGlIST)
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
	     
	     if(level.getNetworksN() != null)
	     {
	    	 for(String net : level.getNetworksN().keySet())
    		 {
	    		 Network n = level.getNetworksN().get(net);
	    		 if(n != null)
	    		 {
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
	     }
	     
	     if(level.getSecretsType() == Type.SECRETLIST)
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
		    	 if(s.getBlkio_config() != null)
		    	 {
		    		 Set<ConstraintViolation<Blkio_config>> constraintViolationsSBB = validator.validate(s.getBlkio_config());
			    	 for (ConstraintViolation<Blkio_config> violation : constraintViolationsSBB) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    		 totalViolations += 1;
			    	 } 
			    	 if(s.getBlkio_config().getDevice_read_bps() != null)
			    	 {
			    		 Set<ConstraintViolation<Blkio_config_option>> constraintViolationsSBBB = validator.validate(s.getBlkio_config().getDevice_read_bps());
				    	 for (ConstraintViolation<Blkio_config_option> violation : constraintViolationsSBBB) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
			    	 }
			    	 if(s.getBlkio_config().getDevice_read_iops() != null)
			    	 {
			    		 Set<ConstraintViolation<Blkio_config_option>> constraintViolationsSBBI = validator.validate(s.getBlkio_config().getDevice_read_iops());
				    	 for (ConstraintViolation<Blkio_config_option> violation : constraintViolationsSBBI) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
			    	 }
			    	 if(s.getBlkio_config().getDevice_write_bps() != null)
			    	 {
			    		 Set<ConstraintViolation<Blkio_config_option>> constraintViolationsSBBW = validator.validate(s.getBlkio_config().getDevice_write_bps());
				    	 for (ConstraintViolation<Blkio_config_option> violation : constraintViolationsSBBW) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
			    	 }
			    	 if(s.getBlkio_config().getDevice_write_iops() != null)
			    	 {
			    		 Set<ConstraintViolation<Blkio_config_option>> constraintViolationsSBBWI = validator.validate(s.getBlkio_config().getDevice_write_iops());
				    	 for (ConstraintViolation<Blkio_config_option> violation : constraintViolationsSBBWI) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
			    	 }
			    	 if(s.getBlkio_config().getWeight_device()!= null)
			    	 {
			    		 Set<ConstraintViolation<Blkio_config_option>> constraintViolationsSBW = validator.validate(s.getBlkio_config().getWeight_device());
				    	 for (ConstraintViolation<Blkio_config_option> violation : constraintViolationsSBW) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
			    	 }
		    	 }
		    	 if(s.getBuildB() != null)
		    	 {
		    		 Set<ConstraintViolation<Build>> constraintViolationsSB = validator.validate(s.getBuildB());
			    	 for (ConstraintViolation<Build> violation : constraintViolationsSB) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    		 totalViolations += 1;
			    	 }
			    	 if(s.getBuildB().getContext() == null && s.getImage() == null)
			    	 {
			    		 System.out.println("A build context or an image must be given for each service");
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
		    	 if(s.getCredential_spec() != null) {
			    	 Set<ConstraintViolation<CredentialSpec>> constraintViolationsC = validator.validate(s.getCredential_spec());
			    	 for (ConstraintViolation<CredentialSpec> violation : constraintViolationsC) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    		 totalViolations += 1;
			    	 }
			     }
		    	 if(s.getHealthcheck()!= null)
			     {
			    	 Set<ConstraintViolation<Healthcheck>> constraintViolationsSH = validator.validate(s.getHealthcheck());
			    	 for (ConstraintViolation<Healthcheck> violation : constraintViolationsSH) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    		 totalViolations += 1;
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
		    	 if(s.getUlimits() != null)
			     {
			    	 Set<ConstraintViolation<Ulimits>> constraintViolationsH = validator.validate(s.getUlimits());
			    	 for (ConstraintViolation<Ulimits> violation : constraintViolationsH) 
			    	 {
			    		 System.out.println(violation.getMessage());
			    		 totalViolations += 1;
			    	 }
			    	 if(s.getUlimits().getNofile() != null)
			    	 {
			    		 Set<ConstraintViolation<Nofile>> constraintViolationsUNF = validator.validate(s.getUlimits().getNofile());
				    	 for (ConstraintViolation<Nofile> violation : constraintViolationsUNF) 
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
		    			 if(v.getBind() != null)
		    			 {
		    				 Set<ConstraintViolation<Bind>> constraintViolationsSVB = validator.validate(v.getBind());
			    			 for (ConstraintViolation<Bind> violation : constraintViolationsSVB) 
			    			 {
			    				 System.out.println(violation.getMessage());
			    				 totalViolations += 1;
			    			 } 
		    			 }
		    			 if(v.getTmpfs() != null)
		    			 {
		    				 Set<ConstraintViolation<TMPFS>> constraintViolationsSVT = validator.validate(v.getTmpfs());
			    			 for (ConstraintViolation<TMPFS> violation : constraintViolationsSVT) 
			    			 {
			    				 System.out.println(violation.getMessage());
			    				 totalViolations += 1;
			    			 } 
		    			 }
		    			 if(v.getVolume() != null)
		    			 {
		    				 Set<ConstraintViolation<VolumeV>> constraintViolationsSVV = validator.validate(v.getVolume());
			    			 for (ConstraintViolation<VolumeV> violation : constraintViolationsSVV) 
			    			 {
			    				 System.out.println(violation.getMessage());
			    				 totalViolations += 1;
			    			 } 
		    			 }
		    		 }
		    	 }
	    	 } 
	     }
	     return totalViolations;
	 }
	 private static void checkForDuplicateKeys(File File)
	 {
		 
     	try {
            YamlConfig yamlConfig = new YamlConfig();
            yamlConfig.setAllowDuplicates(false); // default value is true
            YamlReader reader = new YamlReader(new FileReader(File), yamlConfig);
            reader.read();
        } catch (YamlException ex) {
            System.out.println(ex.getMessage());
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	        
	 }
	 
}
	 


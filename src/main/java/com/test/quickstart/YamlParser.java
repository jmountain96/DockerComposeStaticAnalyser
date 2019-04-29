package com.test.quickstart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
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
		 // Uncomment to use user input mode
		// Scanner myObj = new Scanner(System.in);  
		// System.out.println("Please enter the file path of the Compose configuration YAML file");
		 //String File = myObj.nextLine(); 
		// myObj.close();
		 // Start(File); 
		 Start("test.yaml"); 
	 }
	 /**
	  * Executes the 5 methods that perform validation
	  * @param File - A string representing the destination of the file being parsed
	  */
	 public static void Start(String File)
	 {
		 TopLevel level = new TopLevel(); // Create the top level bean
		 try 
		 {
	       level = ParseFile(File);
		 }
		 catch(com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException UPE)
	        {
	        	System.out.println("An unrecognised key has been entered or the key is at the wrong level"); // Print the unrecognised property to the console
	        	UPE.printStackTrace();
	        }
		 catch(com.fasterxml.jackson.databind.exc.InvalidFormatException IFE)
	        {
	        	System.out.println("Wrong type entered for a value"); // Print the unrecognised property to the console
	        	IFE.printStackTrace();
	        }
		 catch(com.fasterxml.jackson.databind.exc.MismatchedInputException MIE)
	        {
	        	System.out.println("Unknown data entered, all data must be YAML key-value mappings"); // Print the unrecognised property to the console
	        	MIE.printStackTrace();
	        }
		 catch(com.fasterxml.jackson.dataformat.yaml.snakeyaml.error.MarkedYAMLException MYE)
	        {
			 	System.out.println("A formatting error has occured, see below for details");
	        	MYE.printStackTrace();
	        }
         catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {
				checkForDuplicateKeys(File);
			} catch (YamlException ex) {
				System.out.println(ex.getMessage() ); // Print any exceptions found
				//ex.printStackTrace();
			}
	        level = setDependencies(level); // Set dependencies
	        Validate(level); // Validate the parsed file
	        VersionValidator v = new VersionValidator(level); // Perform version validation
	        try {
				v.Validate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 public static TopLevel ParseFile(String file) throws JsonParseException, JsonMappingException, IOException
	 {
		 	File inputFile = new File(file);
		 	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	        TopLevel level = new TopLevel();
            level = mapper.readValue(inputFile, TopLevel.class);
	        return level;
	 }
	 /**
	  * Sets the required values within the input for dependency validation
	  * @param input The input to be validated
	  * @return TopLevel The level object where dependencies have been set
	  */
	 public static TopLevel setDependencies(TopLevel input) 
	 {
		 YamlParserVisitor visitor = new YamlParserVisitorImpl();
		 TopLevelReturn r = input.accept(visitor);
		 String[] Configs = null; // Stores the Configs from every service
		 String[] Networks = null; // Stores the Networks from every service
		 String[] Secrets = null; // Stores the Secrets from every service
		 String[] Volumes = null; // Stores the Volumes from every service
		 if(input.getServices() != null)
	     {
			
			for(Service s : input.getServices().values()) // Loop through the services
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
	     if(level.getConfigs() != null)
	     {
	    	 for( String con  : level.getConfigs().keySet()) 
	    		 
	    	 {
	    		 Configs c = level.getConfigs().get(con);
	    		 Set<ConstraintViolation<Configs>> constraintViolationsC = validator.validate(c);
		    	 for (ConstraintViolation<Configs> violation : constraintViolationsC) 
		    	 {
		    		 System.out.println(violation.getMessage());
		    		 totalViolations += 1;
		    	 }
	    	 }
	     }
	     if(level.getVolumes() != null)
	     {
	    	 for( String vol  : level.getVolumes().keySet()) 
	    		 
	    	 {
	    		 Volume v = level.getVolumes().get(vol);
	    		 if(v != null)
	    		 {
		    		 Set<ConstraintViolation<Volume>> constraintViolationsV = validator.validate(v);
			    	 for (ConstraintViolation<Volume> violation : constraintViolationsV) 
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
			    		 for(Blkio_config_option b : s.getBlkio_config().getDevice_read_bps())
			    		 {
				    		 Set<ConstraintViolation<Blkio_config_option>> constraintViolationsSBBB = validator.validate(b);
					    	 for (ConstraintViolation<Blkio_config_option> violation : constraintViolationsSBBB) 
					    	 {
					    		 System.out.println(violation.getMessage());
					    		 totalViolations += 1;
					    	 } 
			    		 }
			    	 }
			    	 if(s.getBlkio_config().getDevice_read_iops() != null)
			    	 {
			    		 for(Blkio_config_option b : s.getBlkio_config().getDevice_read_iops())
			    		 {
				    		 Set<ConstraintViolation<Blkio_config_option>> constraintViolationsSBBI = validator.validate(b);
					    	 for (ConstraintViolation<Blkio_config_option> violation : constraintViolationsSBBI) 
					    	 {
					    		 System.out.println(violation.getMessage());
					    		 totalViolations += 1;
					    	 } 
			    		 }
			    	 }
			    	 if(s.getBlkio_config().getDevice_write_bps() != null)
			    	 {
			    		 for(Blkio_config_option b : s.getBlkio_config().getDevice_write_bps())
			    		 {
				    		 Set<ConstraintViolation<Blkio_config_option>> constraintViolationsSBBW = validator.validate(b);
					    	 for (ConstraintViolation<Blkio_config_option> violation : constraintViolationsSBBW) 
					    	 {
					    		 System.out.println(violation.getMessage());
					    		 totalViolations += 1;
					    	 } 
			    		 }
			    	 }
			    	 if(s.getBlkio_config().getDevice_write_iops() != null)
			    	 {
			    		 for(Blkio_config_option b : s.getBlkio_config().getDevice_write_iops())
			    		 {
				    		 Set<ConstraintViolation<Blkio_config_option>> constraintViolationsSBBWI = validator.validate(b);
					    	 for (ConstraintViolation<Blkio_config_option> violation : constraintViolationsSBBWI) 
					    	 {
					    		 System.out.println(violation.getMessage());
					    		 totalViolations += 1;
					    	 } 
			    		 }
			    	 }
			    	 if(s.getBlkio_config().getWeight_device()!= null)
			    	 {
			    		 for(Blkio_config_option b : s.getBlkio_config().getWeight_device())
			    		 {
				    		 Set<ConstraintViolation<Blkio_config_option>> constraintViolationsSBW = validator.validate(b);
					    	 for (ConstraintViolation<Blkio_config_option> violation : constraintViolationsSBW) 
					    	 {
					    		 System.out.println(violation.getMessage());
					    		 totalViolations += 1;
					    	 } 
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
		    	 if(s.getConfigsC() != null)
		    	 {
		    		 for(Configs c : s.getConfigsC())
		    		 {
			    		 Set<ConstraintViolation<Configs>> constraintViolationsC = validator.validate(c);
				    	 for (ConstraintViolation<Configs> violation : constraintViolationsC) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 }
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
				    	 if(s.getDeploy().getResources().getLimits() != null)
				    	 {
				    		 Set<ConstraintViolation<Limits>> constraintViolationsSDRL = validator.validate(s.getDeploy().getResources().getLimits());
					    	 for (ConstraintViolation<Limits> violation : constraintViolationsSDRL) 
					    	 {
					    		 System.out.println(violation.getMessage());
					    		 totalViolations += 1;
					    	 } 
				    	 }
				    	 if(s.getDeploy().getResources().getReservations() != null)
				    	 {
				    		 Set<ConstraintViolation<Reservations>> constraintViolationsSDRR = validator.validate(s.getDeploy().getResources().getReservations());
					    	 for (ConstraintViolation<Reservations> violation : constraintViolationsSDRR) 
					    	 {
					    		 System.out.println(violation.getMessage());
					    		 totalViolations += 1;
					    	 } 
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
			    	 if(s.getLogging().getOptions() != null)
			    	 {
			    		 Set<ConstraintViolation<Options>> constraintViolationsSLO = validator.validate(s.getLogging().getOptions());
				    	 for (ConstraintViolation<Options> violation : constraintViolationsSLO) 
				    	 {
				    		 System.out.println(violation.getMessage());
				    		 totalViolations += 1;
				    	 } 
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
	 /**
	  * Checks for duplicate keys within the File by parsing the file with the AllowDuplicates flag set to false
	  * @param File
	  * @throws YamlException
	  */
	 public static void checkForDuplicateKeys(String File) throws YamlException
	 {
		File inputFile = new File(File); 
     	try {
            YamlConfig yamlConfig = new YamlConfig();
            yamlConfig.setAllowDuplicates(false); // default value is true
            YamlReader reader = new YamlReader(new FileReader(inputFile), yamlConfig);
            reader.read();
        } 
         catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	        
	 }
	 
}
	 


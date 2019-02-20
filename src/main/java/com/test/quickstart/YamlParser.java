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
	    	//YamlReader reader = new YamlReader(new FileReader("test.yaml"));
	        //TopLevel input = reader.read(TopLevel.class);
	       // System.out.println(input.getCommand());
	        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	        TopLevel level = new TopLevel();
	        try {
	            level = mapper.readValue(new File("test.yaml"), TopLevel.class);
	            System.out.println(ReflectionToStringBuilder.toString(level,ToStringStyle.MULTI_LINE_STYLE));
	            //System.out.println(level.getServices().get("redis").getConfigsC()[0].getSource());
	            
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        level = setDependencies(level);
	        Validate(level);
	    }
	 
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
				s.setServiceDependenciesD(services);
				s.setLinkDependencies(services);
				if(input.getConfigs() != null) 
				{
					Map<String,Configs> configList = input.getConfigs();
					String[] configs = configList.keySet().toArray(new String[configList.size()]);
					s.setConfigDependencies(configs);
				}
				if(input.getNetworks() != null)
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
				if(input.getSecrets() != null)
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
				retServices[index] = s;
				index++;
			}
		}
			 
	    
		 return input;
	 }
	 
	 public static void Validate(TopLevel level)
	 {
		 level = setDependencies(level);
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
	     if(level.getPortsType() == "Ports[]")
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
	    	 } 
	     }
	 }
	 
	 
}

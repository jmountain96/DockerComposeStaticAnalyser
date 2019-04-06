package com.test.quickstart;

import java.util.Map;

public class YamlParserVisitorImpl  implements YamlParserVisitor{

	@Override
	public TopLevelReturn visit(TopLevel input) {
		
		TopLevelReturn ret = new TopLevelReturn();
		if(input.getServices() != null) 
		{
			Map<String, Service> serviceList = input.getServices();
			String[] services = serviceList.keySet().toArray(new String[serviceList.size()]);
			ret.setServices(services);
		}
		if(input.getConfigs() != null) 
		{
			Map<String,Configs> configList = input.getConfigs();
			String[] configs = configList.keySet().toArray(new String[configList.size()]);
			ret.setConfigs(configs); 
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
				Map<String,Network> networkList = input.getNetworksN();
				networks = networkList.keySet().toArray(new String[networkList.size()]);
			}
			ret.setNetworks(networks);
		}
		if(input.getSecrets() != null )
		{
			String[] secrets;
			if(input.getNetworkType() == "String[]")
			{
				secrets = input.getSecretsL();
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
			}
			ret.setSecrets(secrets);
		}
		if(input.getVolumes() != null) 
		{
			Map<String,Volume> volumesList = input.getVolumes();
			String[] volumes = volumesList.keySet().toArray(new String[volumesList.size()]);
			ret.setVolumes(volumes);
		}
		
		return ret;
		
		
		
	}

}

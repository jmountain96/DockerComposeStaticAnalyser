package com.test.quickstart;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;;

public class TypeConverter {
	
	
	
	
	@SuppressWarnings("unchecked")
	public Map<String,String> convertMap(Object input)
	{
		Map<String,String> _ret;
		_ret = (Map<String,String>)input;
		return _ret;
	}
	@SuppressWarnings("unchecked")
	public Map<String, Object>[] convertMapList(List<Map<String, Object>> input)
	{
		Map<String, Object>[] listOfMaps = (Map<String, Object>[]) new Map[input.size()];
		for(int i = 0; i < input.size(); i++)
		{
			Map<String, Object> map;
			map = (Map<String, Object>)input.get(i);
			listOfMaps[i] = map;
		}
		
		return listOfMaps;
	}
	public String[] convertStringList(Object input)
	{
		String[] _ret;
		String stringLabels = input.toString();
		stringLabels = stringLabels.substring(1, stringLabels.length()-1);
		_ret = Arrays.asList(stringLabels.split("\\s*,\\s*")).toArray(new String[0]);
		return _ret;
	}
	@SuppressWarnings("unchecked")
	public Map<String,String[]> convertMapSS(Object input)
	{
		Map<String,String[]> _ret;
		_ret = (Map<String,String[]>)input;
		return _ret;
	}
	public Map<String, Network> convertNetworks(Map<String, Map<String,Object>> input)
	{
		Map<String,Network> ret = new HashMap<>();
		for(String key : input.keySet()) 
		{
			Map<String,Object> s = input.get(key);
			if( s == null)
			{
				ret.put(key, null);
			}
			else
			{
				Ipam I = null;
				BeanWrapper newWrappedNetwork = new BeanWrapperImpl(new Network());
				for (Map.Entry<String, Object> property : s.entrySet())
				{
					if(property.getKey().equals("ipam")) 
					{
						I = convertIpam(property.getValue());
					}
					else 
					{
						newWrappedNetwork.setPropertyValue(property.getKey(), property.getValue());
					}
				}
				Network newNetwork = (Network)newWrappedNetwork.getWrappedInstance();
				if(I != null)
				{
					newNetwork.setIpam(I);
				}
				ret.put(key, newNetwork);
			}
		}
		return ret;
	}
	@SuppressWarnings("unchecked")
	public Map<String,Configs> convertMapSC(Object input)
	{
		Map<String,Configs> _ret;
		_ret = (Map<String,Configs>)input;
		return _ret;
	}
	@SuppressWarnings("unchecked")
	public Build convertBuild(Object input)
	{
		
		BeanWrapper ret = new BeanWrapperImpl(new Build());
		Map<String, Object> input2 = (Map<String, Object>)input;
		for (Map.Entry<String, Object> property : input2.entrySet())
		{
			ret.setPropertyValue(property.getKey(), property.getValue());
		}
		Build b = (Build) ret.getWrappedInstance();
		
		return b;
			
		
		
	}
	public Configs[] convertConfigsList(List<Map<String, Object>> input)
	{
		Configs[] ret = new Configs[input.size()];
		for(int i = 0 ;  i < input.size(); i++) 
		{
			BeanWrapper newWrappedConfig = new BeanWrapperImpl(new Configs());
			for (Map.Entry<String, Object> property : input.get(i).entrySet())
			{
				newWrappedConfig.setPropertyValue(property.getKey(), property.getValue());
			}
			Configs newConfig = (Configs)newWrappedConfig.getWrappedInstance();
			ret[i] = newConfig;
		}
		return ret;
	}
	public Ports[] convertPorts(List<Map<String, Object>> input)
	{
		Ports[] ret = new Ports[input.size()];
		for(int i = 0 ;  i < input.size(); i++) 
		{
			BeanWrapper newWrappedConfig = new BeanWrapperImpl(new Ports());
			for (Map.Entry<String, Object> property : input.get(i).entrySet())
			{
				newWrappedConfig.setPropertyValue(property.getKey(), property.getValue());
			}
			Ports newConfig = (Ports)newWrappedConfig.getWrappedInstance();
			ret[i] = newConfig;
		}
		return ret;
	}
	
	public Volume[] convertVolumes(List<Map<String, Object>> input)
	{
		Volume[] ret = new Volume[input.size()];
		for(int i = 0 ;  i < input.size(); i++) 
		{
			Bind B = null;
			TMPFS T = null;
			VolumeV V = null;
			BeanWrapper newWrappedConfig = new BeanWrapperImpl(new Volume());
			for (Map.Entry<String, Object> property : input.get(i).entrySet())
			{
				if(property.getKey().equals("bind"))
				{
					B = convertBind(property.getValue());
				}
				else if(property.getKey().equals("tmpfs"))
				{
					T = convertTmpfs(property.getValue());
				}
				else if(property.getKey().equals("volume"))
				{
					V = convertVolumeV(property.getValue());
				}
				else
				{
					newWrappedConfig.setPropertyValue(property.getKey(), property.getValue());
				}
			}
			Volume newConfig = (Volume)newWrappedConfig.getWrappedInstance();
			ret[i] = newConfig;
			if(B != null)
			{
				ret[i].setBind(B);
			}
			if(T != null)
			{
				ret[i].setTmpfs(T);
			}
			if(V != null)
			{
				ret[i].setVolume(V);
			}
		}
		return ret;
	}
	public Secrets[] convertSecrets(Map<String, Map<String,Object>> input)
	{
		Secrets[] ret = new Secrets[input.size()];
		int index = 0;
		for(String key : input.keySet()) 
		{
			Map<String,Object> s = input.get(key);
			BeanWrapper newWrappedSecrets = new BeanWrapperImpl(new Secrets());
			for (Map.Entry<String, Object> property : s.entrySet())
			{
				newWrappedSecrets.setPropertyValue(property.getKey(), property.getValue());
			}
			Secrets newSecrets = (Secrets)newWrappedSecrets.getWrappedInstance();
			newSecrets.setSource(key);
			ret[index] = newSecrets;
			index++;
		}
		return ret;
	}
	public Ipam convertIpam(Object input)
	{
		BeanWrapper ret = new BeanWrapperImpl(new Ipam());
		@SuppressWarnings("unchecked")
		Map<String, Object> input2 = (Map<String, Object>)input;
		for (Map.Entry<String, Object> property : input2.entrySet())
		{
			ret.setPropertyValue(property.getKey(), property.getValue());
		}
		Ipam b = (Ipam) ret.getWrappedInstance();
		
		return b;
	}
	public Secrets[] convertSecretList(Map<String,Object>[] input)
	{
		Secrets[] ret = new Secrets[input.length];
		int index = 0;
		for(Map<String,Object> sec : input)
		{
			BeanWrapper newWrappedSecrets = new BeanWrapperImpl(new Secrets());
			for (Map.Entry<String, Object> property : sec.entrySet())
			{
				newWrappedSecrets.setPropertyValue(property.getKey(), property.getValue());
			}
			Secrets newSecrets = (Secrets)newWrappedSecrets.getWrappedInstance();
			ret[index] = newSecrets;
			index++;
		}
		return ret;
	}
	public Bind convertBind(Object input)
	{
		BeanWrapper ret = new BeanWrapperImpl(new Bind());
		@SuppressWarnings("unchecked")
		Map<String, Object> input2 = (Map<String, Object>)input;
		for (Map.Entry<String, Object> property : input2.entrySet())
		{
			ret.setPropertyValue(property.getKey(), property.getValue());
		}
		Bind b = (Bind) ret.getWrappedInstance();
		
		return b;
	}
	public TMPFS convertTmpfs(Object input)
	{
		BeanWrapper ret = new BeanWrapperImpl(new TMPFS());
		@SuppressWarnings("unchecked")
		Map<String, Object> input2 = (Map<String, Object>)input;
		for (Map.Entry<String, Object> property : input2.entrySet())
		{
			ret.setPropertyValue(property.getKey(), property.getValue());
		}
		TMPFS b = (TMPFS) ret.getWrappedInstance();
		
		return b;
	}
	public VolumeV convertVolumeV(Object input)
	{
		BeanWrapper ret = new BeanWrapperImpl(new VolumeV());
		@SuppressWarnings("unchecked")
		Map<String, Object> input2 = (Map<String, Object>)input;
		for (Map.Entry<String, Object> property : input2.entrySet())
		{
			ret.setPropertyValue(property.getKey(), property.getValue());
		}
		VolumeV b = (VolumeV) ret.getWrappedInstance();
		
		return b;
	}
	public Map<String, Condition> convertMapCondition(Map<String, Map<String,Object>> input)
	{
		Map<String,Condition> ret = new HashMap<>();
		for(String key : input.keySet()) 
		{
			Map<String,Object> s = input.get(key);
			BeanWrapper newWrappedCondition = new BeanWrapperImpl(new Condition());
			for (Map.Entry<String, Object> property : s.entrySet())
			{
				newWrappedCondition.setPropertyValue(property.getKey(), property.getValue());
			}
			Condition newCondition = (Condition)newWrappedCondition.getWrappedInstance();
			ret.put(key, newCondition);
		}
		return ret;
	}
}

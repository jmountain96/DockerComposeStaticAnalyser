package com.test.quickstart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;;

public class TypeConverter {
	
	
	
	public boolean checkString(Object input)
	{
		String value = input.toString();
		if(value.startsWith("{") == false)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean checkStringList(Object input) {
		String value = input.toString();
		if(value.startsWith("[{)") == false) 
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	@SuppressWarnings("unchecked")
	public Map<String,String> convertMap(Object input)
	{
		Map<String,String> _ret;
		_ret = (Map<String,String>)input;
		return _ret;
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
	@SuppressWarnings("unchecked")
	public Map<String,Network> convertMapSN(Object input)
	{
		Map<String,Network> _ret;
		_ret = (Map<String,Network>)input;
		return _ret;
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
	@SuppressWarnings("unchecked")
	public Configs[] convertConfigsList(ArrayList<Map<String, Object>> input)
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
	public Ports[] convertPorts(ArrayList<Map<String, Object>> input)
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
	
	public Volume[] convertVolumes(ArrayList<Map<String, Object>> input)
	{
		Volume[] ret = new Volume[input.size()];
		
		
		for(int i = 0 ;  i < input.size(); i++) 
		{
			BeanWrapper newWrappedConfig = new BeanWrapperImpl(new Volume());
			for (Map.Entry<String, Object> property : input.get(i).entrySet())
			{
				newWrappedConfig.setPropertyValue(property.getKey(), property.getValue());
			}
			Volume newConfig = (Volume)newWrappedConfig.getWrappedInstance();
			ret[i] = newConfig;
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
}

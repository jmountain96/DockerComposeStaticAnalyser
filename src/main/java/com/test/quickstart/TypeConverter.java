package com.test.quickstart;

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
		System.out.println(b.getContext());
		return b;
			
		
		
	}
}

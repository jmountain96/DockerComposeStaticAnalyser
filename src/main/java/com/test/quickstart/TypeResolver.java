package com.test.quickstart;

public class TypeResolver 
{
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
		if(value.startsWith("[") == true) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	public boolean checkMap(Object input)
	{
		String value = input.toString();
		if(value.startsWith("{") == true) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	public boolean checkNestedMap(Object input)
	{
		String value = input.toString();
		if(value.endsWith("}}") == true) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean checkMapList(Object input)
	{
		String value = input.toString();
		if(value.startsWith("[{") == true) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}

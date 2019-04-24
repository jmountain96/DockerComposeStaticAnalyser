package com.test.quickstart;

import static org.junit.Assert.assertEquals;



import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import com.esotericsoftware.yamlbeans.YamlException;

public class DuplicateKeysTest {
	@Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	@Test
	public void DuplicateFalse()
	{
		boolean pass = false;
		try {
		YamlParser.checkForDuplicateKeys("testConfigs/testDuplicateFail.yaml");
		}
		catch(YamlException ex)
		{
			System.out.println(ex.getMessage());
			pass = true;
		}
		assertEquals(true,pass);
	}
	public void DuplicateTrue()
	{
		boolean pass = true;
		try {
		YamlParser.checkForDuplicateKeys("testConfigs/testDuplicateFail.yaml");
		}
		catch(YamlException ex)
		{
			System.out.println(ex.getMessage());
			pass = false;
		}
		assertEquals(true,pass);
	}
}

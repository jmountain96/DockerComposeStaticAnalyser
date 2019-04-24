package com.test.quickstart;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class VersionValidatorTests {
	@Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	@Test
	public void testVersionPass()
	{
		TopLevel level = new TopLevel();
		try {
			level = YamlParser.ParseFile("testConfigs/testVersion2.yaml");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		VersionValidator v = new VersionValidator(level);
		try {
			v.Validate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("", systemOutRule.getLog());
		System.out.println(systemOutRule.getLog());
	}
	@Test
	public void testVersionFail()
	{
		TopLevel level = new TopLevel();
		try {
			level = YamlParser.ParseFile("testConfigs/testVersion2F.yaml");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		VersionValidator v = new VersionValidator(level);
		try {
			v.Validate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(systemOutRule.getLog().length(),2071);
		
	}
}

package com.test.quickstart;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class DependencyTests {

	@Test
	public void DependencyPass()
	{
		TopLevel level = new TopLevel();
		try {
			level = YamlParser.ParseFile("testConfigs/testDependencyPass.yaml");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		YamlParser.setDependencies(level);
		assertEquals(YamlParser.Validate(level), 0);
	}
	@Test
	public void DependencyFail()
	{
		TopLevel level = new TopLevel();
		try {
			level = YamlParser.ParseFile("testConfigs/testDependencyFail.yaml");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		YamlParser.setDependencies(level);
		assertEquals(YamlParser.Validate(level), 9);
	}
}

package com.test.quickstart;

import static org.junit.Assert.assertEquals;


import java.io.IOException;

import org.junit.Test;

public class ValidationTests {
	@Test
	public void testValidationPass()
	{
		TopLevel level = new TopLevel();
		try {
			level = YamlParser.ParseFile("testConfigs/testValidationPass.yaml");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(YamlParser.Validate(level), 1);
		YamlParser.Start("testConfigs/testValidationPass.yaml");
	}
	@Test
	public void testValidationFail()
	{
		TopLevel level = new TopLevel();
		try {
			level = YamlParser.ParseFile("testConfigs/testValidationFail.yaml");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(62, YamlParser.Validate(level));
	}
}

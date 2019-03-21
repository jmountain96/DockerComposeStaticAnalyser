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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(YamlParser.Validate(level), 2);
	}
	@Test
	public void testValidationFail()
	{
		TopLevel level = new TopLevel();
		try {
			level = YamlParser.ParseFile("testConfigs/testValidationFail.yaml");
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(YamlParser.Validate(level), 116);
	}
}

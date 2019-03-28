package com.test.quickstart;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class ValidationTests {
	@Test
	public void testValidationPass()
	{
		TopLevel level = new TopLevel();
		try {
			File f = new File("testConfigs/testValidationPass.yaml");
			level = YamlParser.ParseFile(f);
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
			File f = new File("testConfigs/testValidationFail.yaml");
			level = YamlParser.ParseFile(f);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(YamlParser.Validate(level), 120);
	}
}

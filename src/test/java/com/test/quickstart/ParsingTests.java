package com.test.quickstart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ParsingTests {
	YamlParser y = new YamlParser();
	
	@Test
	public void testFullCompose() 
	{
		TopLevel level = YamlParser.ParseFile("testConfigs/testFullCompose.yaml");
		assertEquals(level.getVersion(), "3.1");
		assertNotNull(level.getServices().get("webapp"));
		assertNotNull(level.getBuildB());
		assertEquals(level.getBuildB().getContext(), "./dir");
		assertEquals(level.getBuildB().getDockerfile(), "Dockerfile-alternate");
		Map<String,String> args = new HashMap<>();;
		args.put("buildno", "1");
		assertEquals(level.getBuildB().getArgsM(), args);
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
		assertEquals(level.getVersion(), "3.1");
	}
}

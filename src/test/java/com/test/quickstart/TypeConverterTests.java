package com.test.quickstart;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TypeConverterTests {
	TypeConverter t = new TypeConverter();
	@Test
	public void convertMapTest()
	{
		Map<String,String> testMap = new HashMap<>();
		testMap.put("foo", "bar");
		assertEquals(t.convertMap((Object)testMap), testMap);
	}
	@Test
	public void convertMapListTest()
	{
		Map<String, Object>[] testMapList = (Map<String, Object>[]) new Map[1];
		testMapList[0] = new HashMap<>();
		testMapList[0].put("foo", "bar");
		List<Map<String, Object>> testMapArrayList = Arrays.asList(testMapList);
		assertArrayEquals(t.convertMapList(testMapArrayList), testMapList);
	}
	@Test
	public void convertStringList()
	{
		String[] testList = {"foo", "bar"};
		assertArrayEquals(t.convertStringList(" foo, bar "), testList);
	}
	@Test
	public void convertMapSSTest()
	{
		Map<String,String[]> testMap = new HashMap<>();
		String[] testList = {"foo", "bar"};
		testMap.put("foo", testList);
		assertEquals(t.convertMap((Object)testMap), testMap);
	}
	@Test 
	public void convertNetworksTest()
	{
		Map<String,Map<String,Object>> testMap = new HashMap<>();
		Map<String,Object> testMap2 = new HashMap<>();
		testMap2.put("name", "bar");
		testMap.put("foo", testMap2);
		Network testNetwork = new Network();
		testNetwork.setName("bar");
		Map<String,Network> testMap3 = new HashMap<>();
		testMap3.put("foo", testNetwork);
		assertEquals(t.convertNetworks(testMap).get("foo").getName(), testMap3.get("foo").getName());
	}
	@Test
	public void convertBuildTest()
	{
		Map<String,Object> testMap = new HashMap<>();
		testMap.put("target", "bar");
		Build testBuild = new Build();
		testBuild.setTarget("bar");
		assertEquals(t.convertBuild(testMap).getTarget(), testBuild.getTarget());
	}
	@Test
	public void convertConfigListTest()
	{
		Map<String, Object>[] testMapList = (Map<String, Object>[]) new Map[1];
		testMapList[0] = new HashMap<>();
		testMapList[0].put("name", "bar");
		Configs[] testConfigList = new Configs[1];
		Configs testConfig = new Configs();
		testConfig.setName("bar");
		testConfigList[0] = testConfig;
		List<Map<String, Object>> testMapArrayList = Arrays.asList(testMapList);
		assertEquals(t.convertConfigsList(testMapArrayList)[0].getName(), testConfigList[0].getName());
	}
	@Test
	public void convertPortsListTest()
	{
		Map<String, Object>[] testMapList = (Map<String, Object>[]) new Map[1];
		testMapList[0] = new HashMap<>();
		testMapList[0].put("mode", "bar");
		Ports[] testPortsList = new Ports[1];
		Ports testPorts = new Ports();
		testPorts.setMode("bar");
		testPortsList[0] = testPorts;
		List<Map<String, Object>> testMapArrayList = Arrays.asList(testMapList);
		assertEquals(t.convertPorts(testMapArrayList)[0].getMode(), testPortsList[0].getMode());
	}
	@Test
	public void convertVolumeListTest()
	{
		Map<String, Object>[] testMapList = (Map<String, Object>[]) new Map[1];
		testMapList[0] = new HashMap<>();
		testMapList[0].put("driver", "bar");
		Volume[] testVolumesList = new Volume[1];
		Volume testVolume = new Volume();
		testVolume.setDriver("bar");
		testVolumesList[0] = testVolume;
		List<Map<String, Object>> testMapArrayList = Arrays.asList(testMapList);
		assertEquals(t.convertVolumes(testMapArrayList)[0].getDriver(), testVolumesList[0].getDriver());
	}
	@Test 
	public void convertSecretsTest()
	{
		Map<String,Map<String,Object>> testMap = new HashMap<>();
		Map<String,Object> testMap2 = new HashMap<>();
		testMap2.put("source", "foo");
		testMap.put("foo", testMap2);
		Secrets testSecrets = new Secrets();
		testSecrets.setSource("foo");
		Map<String,Secrets> testMap3 = new HashMap<>();
		testMap3.put("foo", testSecrets);
		assertEquals(t.convertSecrets(testMap)[0].getSource(), testMap3.get("foo").getSource());
	}
	@Test
	public void convertIpamTest()
	{
		Map<String,Object> testMap = new HashMap<>();
		testMap.put("driver", "bar");
		assertEquals(t.convertIpam(testMap).getDriver(), "bar");
	}
	@Test
	public void convertSecretsListTest()
	{
		Map<String, Object>[] testMapList = (Map<String, Object>[]) new Map[1];
		testMapList[0] = new HashMap<>();
		testMapList[0].put("source", "bar");
		Secrets[] testSecretsList = new Secrets[1];
		Secrets testSecrets = new Secrets();
		testSecrets.setSource("bar");
		testSecretsList[0] = testSecrets;
		assertEquals(t.convertSecretList(testMapList)[0].getSource(), testSecretsList[0].getSource());
	}
	@Test
	public void convertMapSCTest()
	{
		Map<String,Configs> testMap = new HashMap<>();
		Configs testConfig = new Configs();
		testConfig.setName("bar");
		testMap.put("foo", testConfig);
		assertEquals(t.convertMapSC((Object)testMap).get("foo").getName(), "bar" );
	}
}

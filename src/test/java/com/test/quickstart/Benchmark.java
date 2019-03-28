package com.test.quickstart;



import java.io.File;


import org.junit.Test;

public class Benchmark {
	@Test
	public void testBenchMark()
	{
		for(int i = 0; i < 100 ; i++) 
		{
		File f = new File("testConfigs/testValidationPass.yaml");
		YamlParser.Start(f);
		}
	}
}

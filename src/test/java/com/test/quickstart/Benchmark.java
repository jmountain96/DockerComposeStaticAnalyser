package com.test.quickstart;





import org.junit.Test;

public class Benchmark {
	@Test
	public void testBenchMark()
	{
		for(int i = 0; i < 100 ; i++) 
		{
		YamlParser.Start("testConfigs/testValidationPass.yaml");
		}
	}
}

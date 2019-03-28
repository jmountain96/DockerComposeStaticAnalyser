package com.test.quickstart;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHContentSearchBuilder;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedSearchIterable;

public class GHRunner 
{
	YamlParser y = new YamlParser();
	@Test
	public void run() throws IOException
	{
		
		File dir = new File("./ghConfigs");
		File[] dirListing = dir.listFiles();
		
		for( int index = 0; index < dirListing.length; index++)
		{
			if (dirListing[index] != null)
			{
				YamlParser.Start(dirListing[index]);
			}
		}
	}
	
	
	
}
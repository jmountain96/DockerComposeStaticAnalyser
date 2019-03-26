package com.test.quickstart;

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
	@Test
	public void run() throws IOException
	{
		GitHub gh = GitHub.connectUsingOAuth("");
		GHContentSearchBuilder search = gh.searchContent();
		GHContentSearchBuilder s = search.q("in:file+filename:docker-compose.yml+org:mobomo");

		PagedSearchIterable<GHContent> res = s.list();
		
		for (GHContent ghRepository : res) 
		{
			System.out.println(ghRepository.getName());
		}
	}
	
	
	
}
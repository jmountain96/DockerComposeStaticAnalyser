package com.test.quickstart;

public class VersionValidator {
	private TopLevel level;

	public VersionValidator(TopLevel level) {
		super();
		this.level = level;
	}
	public void Validate()
	{
		switch(level.getVersion())
		{
		case "2":
			
		case "2.1":
		
		case "2.2":
			
		case "2.3":
		
		case "2.4":
		
		case "3":
		
		case "3.3":
		
		case "3.4":
		
		case "3.5":
		
		case "3.6":
			
		}
	}
	public void Validate2()
	{
		if(level.getIsolation() != null)
		{
			System.out.println("Isolation is only compatible with version 2.1+");
		}
		if(level.getUserns_mode() != null)
		{
			System.out.println("Userns mode is only compatible with version 2.1+");
		}
		if(level.getHealthcheck() != null)
		{
			System.out.println("Healthcheck is only compatible with version 2.1+");
		}
		if(level.getSysctls()!= null)
		{
			System.out.println("Sysctls is only compatible with version 2.1+");
		}
		if(level.getPids_limit() != null)
		{
			System.out.println("Pids Limit is only compatible with version 2.1+");
		}
		if(level.getOom_kill_disable() != null)
		{
			System.out.println("Oom kill disable is only compatible with version 2.1+");
		}
		if(level.getCpu_period() != null)
		{
			System.out.println("Cpu period is only compatible with version 2.1+");
		}
		for(Service s : level.getServices().values())
		{
			if(s.getNetworksM() != null)
			{
				for(Network n : s.getNetworksM().values())
				{
					if(n.getLink_local_ips() != null)
					{
						System.out.println("Service network link local ips is only compatible with version 2.1+");
					}
					if(n.getLabels() != null)
					{
						System.out.println("Network labels are only compatible with version 2.1+");
					}
				}
				if(s.getVolumesVL() != null)
				{
					for(Volume v: s.getVolumesVL())
					{
						if(v.getLabels() != null)
						{
							System.out.println("Volume labels are only compatible with version 2.1+");
						}
						if(v.getName() != null)
						{
							System.out.println("Volume names are only compatible with version 2.1+");
						}
					}
			}
		}
		if(level.getIsolation() != null)
		{
			System.out.println("Isolation is only compatible with version 2.1+");
		}
		if(level.getIsolation() != null)
		{
			System.out.println("Isolation is only compatible with version 2.1+");
		}
		
	}
	public void Validate21()
	{
		if(level.getInit() != null)
		{
			System.out.println("Init is only compatible with version 2.2+");
		}
		if(level.getScale() != null)
		{
			System.out.println("Scale is only compatible with version 2.2+");
		}
		if(level.getCpu_rt_period() != null)
		{
			System.out.println("Cpu rt period compatible with version 2.2+");
		}
		if(level.getCpu_rt_runtime() != null)
		{
			System.out.println("Cpu rt runtime compatible with version 2.2+");
		}
	
	}
	
	public void Validate22()
	{
		if(level.getBuildB() != null)
		{
			if(level.getBuildB().getTarget() != null)
			{
				System.out.println("Build target are only compatible with version 2.3+");
			}
		}
		if(level.getBuildB() != null)
		{
			if(level.getBuildB().getExtra_hosts() != null)
			{
				System.out.println("Build extra hosts are only compatible with version 2.3+");
			}
		}
		if(level.getBuildB() != null)
		{
			if(level.getBuildB().getShm_size() != null)
			{
				System.out.println("Build shm size are only compatible with version 2.3+");
			}
		}
		if(level.getHealthcheck() != null)
		{
			if(level.getHealthcheck().getStart_period() != null)
			{
				System.out.println("Healthcheck start_period are only compatible with version 2.3+");
			}
		}
		for(Service s : level.getServices().values())
		{
			if(s.getRuntime() != null)
			{
				System.out.println("Service runtime is only compatible with version 2.3+");
			}
			if(s.getVolumesVL() != null)
			{
				System.out.println("Service volume long syntax is only compatible with version 2.3+");
			}
		}
		if(level.getDevice_cgroup_rules() != null)
		{
			System.out.println("Device c group rules are only compatible with version 2.3+");
		}
		
		
	}
	public void Validate23()
	{
		if(level.getPlatform() != null)
		{
			System.out.println("Platform is only compatible with version 2.4+");
		}
		
	}
	public void Validate24()
	{
		if(level.getServices() != null)
		{
			for(Service s : level.getServices().values())
			{
				if(s.getDeploy() != null)
				{
					System.out.println("Deploy is only compatible with version 3+");
				}
			}
		}
	}
	public void Version3Removal()
	{
		if(level.getVolume_driver() != null)
		{
			System.out.println("Volume Driver is only compatible with version 2.x");
		}
		if(level.getVolumes_from() != null)
		{
			System.out.println("Volumes from is only compatible with version 2.x");
		}
		if(level.getCpu_shares() != null)
		{
			System.out.println("Cpu shares is only compatible with version 2.x");
		}
		if(level.getCpu_quota() != null)
		{
			System.out.println("Cpu quota is only compatible with version 2.x");
		}
		if(level.getCpuset() != null)
		{
			System.out.println("Cpu set is only compatible with version 2.x");
		}
		if(level.getMem_limit() != null)
		{
			System.out.println("Mem limit is only compatible with version 2.x");
		}
		if(level.getMemswap_limit() != null)
		{
			System.out.println("Mem swap limit is only compatible with version 2.x");
		}
		if(level.getExtends() != null)
		{
			System.out.println("Extends is only compatible with version 2.x");
		}
		if(level.getGroup_add() != null)
		{
			System.out.println("Group add is only compatible with version 2.x");
		}
		if(level.getPids_limit()!= null)
		{
			System.out.println("Pids limit is only compatible with version 2.x");
		}
		for(Service s : level.getServices().values())
		{
			if(s.getNetworksM() != null)
			{
				for(Network n : s.getNetworksM().values())
				{
					if(n.getLink_local_ips() != null)
					{
						System.out.println("Service network link local ips is only compatible with version 2.x");
					}
				}
			}
		}
	}
	public void Validate3()
	{
		if(level.getBuildB() != null)
		{
			if(level.getBuildB().getLabels() != null)
			{
				System.out.println("Build labels are only compatible with version 3.3+");
			}
		}
		if(level.getCredential_spec() != null)
		{
			System.out.println("Credential Specs are only compatible with version 3.3+");
		}
		if(level.getConfigs() != null)
		{
			System.out.println("Configs are only compatible with version 3.3+");
		}
		if(level.getServices() != null)
		{
			for(Service s : level.getServices().values())
			{
				if(s.getDeploy().getEndpoint_mode()!= null)
				{
					System.out.println("Deploy endpoints are only compatible with version 3.3+");
				}
			}
		}
	}
	public void Validate33()
	{
		if(level.getVolumes() != null)
		{
			for(Volume V : level.getVolumes().values()) 
			{
				if(V.getName() != null)
				{
					System.out.println("Volume name is only compatible with version 3.4+");
				}
			}
		}
		if(level.getBuildB() != null)
		{
			if(level.getBuildB().getTarget() != null)
			{
				System.out.println("Build target is only compatible with version 3.4+");
			}
			if(level.getBuildB().getNetwork() != null)
			{
				System.out.println("Build network is only compatible with version 3.4+");
			}
		}
		if(level.getHealthcheck() != null)
		{
			if(level.getHealthcheck().getStart_period() != null)
			{
				System.out.println("Healthcheck start period is only compatible with version 3.4+");
			}
		}
		if(level.getServices() != null)
		{
			for(Service s : level.getServices().values())
			{
				if(s.getDeploy().getRollback_config()!= null)
				{
					if(s.getDeploy().getRollback_config().getOrder()!= null)
					{
						System.out.println("Deploy rollback config order is only compatible with version 3.4+");
					}
				}
			}
		}
	}
	public void Validate34()
	{
		if(level.getIsolation() != null)
		{
			System.out.println("Isolation is only compatible with version 3.5+");
		}
		if(level.getBuildB() != null)
		{
			if(level.getBuildB().getShm_size() != null)
			{
				System.out.println("Build shm size is only compatible with version 3.5+");
			}
		}
		if(level.getNetworksN() != null)
		{
			for(Network N : level.getNetworksN().values()) 
			{
				if(N.getName() != null)
				{
					System.out.println("Network name is only compatible with version 3.5+");
				}
			}
		}
		if(level.getConfigs() != null)
		{
			for(Configs C : level.getConfigs().values()) 
			{
				if(C.getName() != null)
				{
					System.out.println("Config name is only compatible with version 3.5+");
				}
			}
		}
		if(level.getServices() != null)
		{
			for(Service s : level.getServices().values())
			{
				if(s.getName() != null)
				{
					System.out.println("Service name is only compatible with version 3.5+");
				}
			}
		}
	}
	public void Validate35()
	{
		if(level.getTmpfs() != null)
		{
			System.out.println("Tmpfs is only compatible with version 3.6+");
		}
	}
	public void Validate36()
	{
		if(level.getServices() != null)
		{
			for(Service s : level.getServices().values())
			{
				if(s.getInit() != null)
				{
					System.out.println("Service init is only compatible with version 3.7+");
				}
				if(s.getDeploy().getRollback_config()!= null)
				{
					System.out.println("Deploy rollback config is only compatible with version 3.7+");
				}
			}
		}
	}
	
}

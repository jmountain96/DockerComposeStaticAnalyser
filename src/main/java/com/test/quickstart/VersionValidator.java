package com.test.quickstart;

public class VersionValidator {
	private TopLevel level;

	public VersionValidator(TopLevel level) {
		super();
		this.level = level;
	}
	public void Validate() throws Exception
	{
		switch(level.getVersion())
		{
		case "2":
			Validate2();
			break;
		case "2.1":
			Validate21();
			break;
		case "2.2":
			Validate22();
			break;
		case "2.3":
			Validate23();
			break;
		case "2.4":
			Validate24();
			break;
		case "3":
			Validate3();
			Version3Removal();
			break;
		case "3.1":
			Validate33();
			Version3Removal();
			break;
		case "3.2":
			Validate33();
			Version3Removal();
			break;
		case "3.3":
			Validate33();
			Version3Removal();
			break;
		case "3.4":
			Validate34();
			Version3Removal();
			break;
		case "3.5":
			Validate35();
			Version3Removal();
			break;
		case "3.6":
			Validate36();
			Version3Removal();
			break;
		case "3.7":
			Version3Removal();
			break;
		default:
			throw new Exception("Version not supplied");
			
		}
	}
	public void Validate2()
	{
		
		
		
		for(Service s : level.getServices().values())
		{
			if(s.getOom_kill_disable() != null)
			{
				System.out.println("Oom kill disable is only compatible with version 2.1+");
			}
			if(s.getCpu_period() != null)
			{
				System.out.println("Cpu period is only compatible with version 2.1+");
			}
			if(s.getPids_limit() != null)
			{
				System.out.println("Pids Limit is only compatible with version 2.1+");
			}
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
				if(s.getIsolation() != null)
				{
					System.out.println("Isolation is only compatible with version 2.1+");
				}
				if(s.getIsolation() != null)
				{
					System.out.println("Isolation is only compatible with version 2.1+");
				}
				if(s.getIsolation() != null)
				{
					System.out.println("Isolation is only compatible with version 2.1+");
				}
				if(s.getUserns_mode() != null)
				{
					System.out.println("Userns mode is only compatible with version 2.1+");
				}
				if(s.getHealthcheck() != null)
				{
					System.out.println("Healthcheck is only compatible with version 2.1+");
				}
				if(s.getSysctls()!= null)
				{
					System.out.println("Sysctls is only compatible with version 2.1+");
				}
			}
		}
	}
		
		Validate21();
		
	}
	public void Validate21()
	{
		for(Service s : level.getServices().values())
		{
			if(s.getInit() != null)
			{
				System.out.println("Init is only compatible with version 2.2+");
			}
			if(s.getScale() != null)
			{
				System.out.println("Scale is only compatible with version 2.2+");
			}
			if(s.getCpu_rt_period() != null)
			{
				System.out.println("Cpu rt period compatible with version 2.2+");
			}
			if(s.getCpu_rt_runtime() != null)
			{
				System.out.println("Cpu rt runtime compatible with version 2.2+");
			}
		}
		Validate22();
	
	}
	
	public void Validate22()
	{
		
		
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
			if(s.getHealthcheck() != null)
			{
				if(s.getHealthcheck().getStart_period() != null)
				{
					System.out.println("Healthcheck start_period are only compatible with version 2.3+");
				}
			}
			if(s.getDevice_cgroup_rules() != null)
			{
				System.out.println("Device c group rules are only compatible with version 2.3+");
			}
			if(s.getBuildB() != null)
			{
				if(s.getBuildB().getShm_size() != null)
				{
					System.out.println("Build shm size are only compatible with version 2.3+");
				}
				if(s.getBuildB().getExtra_hosts() != null)
				{
					System.out.println("Build extra hosts are only compatible with version 2.3+");
				}
				if(s.getBuildB().getTarget() != null)
				{
					System.out.println("Build target are only compatible with version 2.3+");
				}
				
			}
		}
		
		Validate23();
		
	}
	public void Validate23()
	{
		for(Service s : level.getServices().values())
		{
			if(s.getPlatform() != null)
			{
				System.out.println("Platform is only compatible with version 2.4+");
			}
		}
		Validate24();
		
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
		Validate3();
	}
	public void Version3Removal()
	{
		
		if(level.getVolumes_from() != null)
		{
			System.out.println("Volumes from is only compatible with version 2.x");
		}
		
		for(Service s : level.getServices().values())
		{
			if(s.getNetworksM() != null)
			{
				if(s.getCpu_shares() != null)
				{
					System.out.println("Cpu shares is only compatible with version 2.x");
				}
				if(s.getVolume_driver() != null)
				{
					System.out.println("Volume Driver is only compatible with version 2.x");
				}
				if(s.getCpu_quota() != null)
				{
					System.out.println("Cpu quota is only compatible with version 2.x");
				}
				if(s.getCpuset() != null)
				{
					System.out.println("Cpu set is only compatible with version 2.x");
				}
				if(s.getMem_limit() != null)
				{
					System.out.println("Mem limit is only compatible with version 2.x");
				}
				if(s.getMemswap_limit() != null)
				{
					System.out.println("Mem swap limit is only compatible with version 2.x");
				}
				if(s.getExtends() != null)
				{
					System.out.println("Extends is only compatible with version 2.x");
				}
				if(s.getGroup_add() != null)
				{
					System.out.println("Group add is only compatible with version 2.x");
				}
				if(s.getPids_limit()!= null)
				{
					System.out.println("Pids limit is only compatible with version 2.x");
				}
				for(Network n : s.getNetworksM().values())
				{
					if(n.getLink_local_ips() != null)
					{
						System.out.println("Service network link local ips is only compatible with version 2.x");
					}
					if(n.getEnable_ipv6() != null)
					{
						System.out.println("Service network enable ipv6 is only compatible with version 2.x");
					}
				}
			}
		}
	}
	public void Validate3()
	{	
		if(level.getConfigs() != null)
		{
			System.out.println("Configs are only compatible with version 3.3+");
		}
		if(level.getServices() != null)
		{
			for(Service s : level.getServices().values())
			{
				if(s.getDeploy() != null)
				{
					if(s.getDeploy().getEndpoint_mode()!= null)
					{
						System.out.println("Deploy endpoints are only compatible with version 3.3+");
					}
				}
				if(s.getBuildB() != null)
				{
					if(s.getBuildB().getLabels() != null)
					{
						System.out.println("Build labels are only compatible with version 3.3+");
					}
				}
				if(s.getCredential_spec() != null)
				{
					System.out.println("Credential Specs are only compatible with version 3.3+");
				}
			}
		}
		Validate33();
	}
	public void Validate33()
	{
		if(level.getVolumes() != null)
		{
			for(Volume V : level.getVolumes().values()) 
			{
				if(V != null)
				{
					if(V.getName() != null)
					{
						System.out.println("Volume name is only compatible with version 3.4+");
					}
				}
			}
		}
		
		
		if(level.getServices() != null)
		{
			for(Service s : level.getServices().values())
			{
				if(s.getDeploy() != null)
				{
					if(s.getDeploy().getRollback_config()!= null)
					{
						if(s.getDeploy().getRollback_config().getOrder()!= null)
						{
							System.out.println("Deploy rollback config order is only compatible with version 3.4+");
						}
					}
					if(s.getBuildB() != null)
					{
						if(s.getBuildB().getTarget() != null)
						{
							System.out.println("Build target is only compatible with version 3.4+");
						}
						if(s.getBuildB().getNetwork() != null)
						{
							System.out.println("Build network is only compatible with version 3.4+");
						}
					}
					if(s.getHealthcheck() != null)
					{
						if(s.getHealthcheck().getStart_period() != null)
						{
							System.out.println("Healthcheck start period is only compatible with version 3.4+");
						}
					}
				}
			}
		}
		Validate34();
	}
	public void Validate34()
	{
		
		
		if(level.getNetworksN() != null)
		{
			for(Network N : level.getNetworksN().values()) 
			{
				if(N != null)
				{
					if(N.getName() != null)
					{
						System.out.println("Network name is only compatible with version 3.5+");
					}
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
				if(s.getBuildB() != null)
				{
					if(s.getBuildB().getShm_size() != null)
					{
						System.out.println("Build shm size is only compatible with version 3.5+");
					}
				}
				if(s.getIsolation() != null)
				{
					System.out.println("Isolation is only compatible with version 3.5+");
				}
			}
		}
		Validate35();
	}
	public void Validate35()
	{if(level.getServices() != null)
	{
		for(Service s : level.getServices().values())
		{
			if(s.getTmpfs() != null)
			{
				System.out.println("Tmpfs is only compatible with version 3.6+");
			}
		}
	}
		Validate36();
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
				if(s.getDeploy() != null)
				{
					if(s.getDeploy().getRollback_config()!= null)
					{
						System.out.println("Deploy rollback config is only compatible with version 3.7+");
					}
				}
			}
		}
	}
	
}

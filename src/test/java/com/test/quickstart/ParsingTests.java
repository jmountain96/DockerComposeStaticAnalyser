package com.test.quickstart;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


import org.junit.Test;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

public class ParsingTests {
	YamlParser y = new YamlParser();
	@Test
	public void testIncorrectKeys() 
	{
		try {
			YamlParser.ParseFile("testConfigs/testIncorrectKeys.yaml");
			fail("Expected an UnrecognizedPropertyException to be thrown");
		} 
		catch(UnrecognizedPropertyException UPE)
		{
			assertTrue(UPE.getMessage().startsWith("Unrecognized field \"foo\""));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testIncorrectValue()
	{
		try {
			YamlParser.ParseFile("testConfigs/testIncorrectValue.yaml");
			fail("Expected an MismatchedInputException to be thrown");
		} 
		catch(MismatchedInputException MIE)
		{
			assertTrue(MIE.getMessage().startsWith("Cannot deserialize instance of `int` out of START_ARRAY token"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFullComposeTrue() 
	{
		TopLevel level = new TopLevel();
		try {
			level = YamlParser.ParseFile("testConfigs/testValidationPass.yaml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(level.getVersion(), "3.7");
		assertNotNull(level.getServices().get("webapp"));
		assertNotNull(level.getServices().get("webapp").getBuildB());
		assertEquals(level.getServices().get("webapp").getBuildB().getContext(), "./testFiles/");
		assertEquals(level.getServices().get("webapp").getBuildB().getDockerfile(), "test.env");
		Map<String,Object> args = new LinkedHashMap<>();;
		args.put("buildno", 1);
		assertEquals(level.getServices().get("webapp").getBuildB().getArgsM().get("buildno"), args.get("buildno"));
		String[] labels = {"com.example.description=Accounting webapp", "com.example.department=Finance","com.example.label-with-empty-value" };
		String[] labelsV = {"com.example.description=Database volume", "com.example.department=IT/Ops","com.example.label-with-empty-value" };
		String[] cache = {"alpine:latest", "corp/web_app:3.14"};
		String[] extra_hosts2 = {"somehost:162.242.195.82"};
		assertArrayEquals(level.getServices().get("webapp").getBuildB().getCache_from(), cache);
		assertArrayEquals(level.getServices().get("webapp").getBuildB().getLabelsS(), labels);
		assertArrayEquals(level.getServices().get("webapp").getBuildB().getExtra_hosts(), extra_hosts2);
		assertEquals(level.getServices().get("webapp").getBuildB().getTarget(), "prod");
		assertEquals(level.getServices().get("webapp").getBuildB().getShm_size(), "2gb");
		assertEquals(level.getServices().get("webapp").getBuildB().getNetwork(), "host");
		assertEquals(level.getServices().get("webapp").getCap_add()[0], "ALL");
		String[] cap_drop = {"NET_ADMIN", "SYS_ADMIN"};
		assertArrayEquals(level.getServices().get("webapp").getCap_drop(), cap_drop);
		assertNotNull(level.getServices().get("webapp").getConfigsC());
		assertEquals(level.getServices().get("webapp").getBlkio_config().getDevice_read_bps()[0].getPath(),"./testFiles/");
		assertEquals(level.getServices().get("webapp").getBlkio_config().getDevice_read_bps()[0].getRate(),"12mb");
		assertEquals(level.getServices().get("webapp").getBlkio_config().getDevice_read_iops()[0].getPath(),"./testFiles/");
		assertEquals(level.getServices().get("webapp").getBlkio_config().getDevice_read_iops()[0].getRate(),"120");
		assertEquals(level.getServices().get("webapp").getBlkio_config().getDevice_write_bps()[0].getPath(),"./testFiles/");
		assertEquals(level.getServices().get("webapp").getBlkio_config().getDevice_write_bps()[0].getRate(),"1024k");
		assertEquals(level.getServices().get("webapp").getBlkio_config().getDevice_write_iops()[0].getPath(),"./testFiles/");
		assertEquals(level.getServices().get("webapp").getBlkio_config().getDevice_write_iops()[0].getRate(),"30");
		assertEquals(level.getServices().get("webapp").getBlkio_config().getWeight_device()[0].getPath(),"./testFiles/");
		assertEquals(level.getServices().get("webapp").getBlkio_config().getWeight_device()[0].getWeight(),"400");
		assertEquals(level.getServices().get("webapp").getBlkio_config().getWeight(),300);
		assertEquals(level.getServices().get("webapp").getConfigsC()[0].getSource(), "my_config");
		assertEquals(level.getServices().get("webapp").getConfigsC()[0].getTarget(), "./testFiles/test.env");
		assertEquals(level.getServices().get("webapp").getConfigsC()[0].getUid(), "103");
		assertEquals(level.getServices().get("webapp").getConfigsC()[0].getGid(), "104");
		assertEquals(level.getServices().get("webapp").getConfigsC()[0].getMode(), "0440");
		assertEquals(level.getServices().get("webapp").getCgroup_parent(), "m-executor-abcd");
		assertEquals(level.getServices().get("webapp").getCommand(), "bundle exec thin -p 3000");
		assertEquals(level.getServices().get("webapp").getCpu_count(), "2");
		assertEquals(level.getServices().get("webapp").getCpu_percent(), "50");
		assertEquals(level.getServices().get("webapp").getCpus(), "0.5");
		assertEquals(level.getServices().get("webapp").getCpu_shares(), "73");
		assertEquals(level.getServices().get("webapp").getCpu_quota(), "50000");
		assertEquals(level.getServices().get("webapp").getCpu_period(), "20ms");
		assertEquals(level.getServices().get("webapp").getCpuset(), "0,1");
		assertEquals(level.getServices().get("webapp").getCpu_rt_runtime(), "400ms");
		assertEquals(level.getServices().get("webapp").getCpu_rt_period(), "1400us");
		assertEquals(level.getServices().get("webapp").getContainer_name(), "my-web-container");
		assertEquals(level.getServices().get("webapp").getCredential_spec().getFile(), "test.env");
		assertEquals(level.getServices().get("webapp").getCredential_spec().getRegistry(), "my-credential-spec");
		String[] devices = {"/dev/ttyUSB0:/dev/ttyUSB0"};
		assertArrayEquals(level.getServices().get("webapp").getDevices(), devices);
		assertEquals(level.getServices().get("webapp").getDnsS(), "8.8.8.8");
		assertEquals(level.getServices().get("webapp").getDns_search(), "example.com");
		String[] dns_opt = {"use-vc","no-tld-query"};
		assertArrayEquals(level.getServices().get("webapp").getDns_opt(), dns_opt);
		assertEquals(level.getServices().get("webapp").getExtends().getFile(), "./testFiles/test.env");
		assertEquals(level.getServices().get("webapp").getExtends().getService(), "webapp");
		assertEquals(level.getServices().get("webapp").getEntrypointS(), "./testFiles/test.env");
		String[] health_test = {"CMD", "curl", "-f", "http://localhost"};
		assertArrayEquals(level.getServices().get("webapp").getHealthcheck().getTestSL(), health_test);
		assertEquals(level.getServices().get("webapp").getHealthcheck().getInterval(), "1m30s");
		assertEquals(level.getServices().get("webapp").getHealthcheck().getTimeout(), "10s");
		assertEquals(level.getServices().get("webapp").getHealthcheck().getRetries(), 3);
		assertEquals(level.getServices().get("webapp").getHealthcheck().getStart_period(), "40s");
		assertEquals(level.getServices().get("webapp").getHealthcheck().getDisable(), "true");
		assertEquals(level.getServices().get("webapp").getIsolation(), "default");
		assertEquals(level.getServices().get("webapp").getImage(), "redis");
		assertEquals(level.getServices().get("webapp").getMem_limit(), "1000000000");
		assertEquals(level.getServices().get("webapp").getMemswap_limit(), "2000000000");
		assertEquals(level.getServices().get("webapp").getMem_reservation(), "512m");
		assertEquals(level.getServices().get("webapp").getMem_swappiness(), "3");
		assertEquals(level.getServices().get("webapp").getNetwork_mode(), "bridge");
		String[] environment = {"RACK_ENV=development", "SHOW=true", "SESSION_SECRET"};
		assertArrayEquals(level.getServices().get("webapp").getEnvironmentSL(), environment);
		String[] expose = {"3000","8000"};
		assertArrayEquals(level.getServices().get("webapp").getExpose(), expose);
		assertArrayEquals(level.getServices().get("webapp").getLabelsS(), labelsV);
		String[] external_links = {"redis_1","project_db_1:mysql", "project_db_1:postgresql"};
		assertArrayEquals(level.getServices().get("webapp").getExternal_links(), external_links);
		String[] extra_hosts = {"somehost:162.242.195.82","otherhost:50.31.209.229"};
		assertArrayEquals(level.getServices().get("webapp").getExtra_hosts(), extra_hosts);
		assertNotNull(level.getServices().get("webapp").getHealthcheck());
		assertEquals(level.getServices().get("webapp").getRestart(), "no");
		assertEquals(level.getServices().get("webapp").getPid(), "host");
		assertEquals(level.getServices().get("webapp").getPids_limit(), "10");
		assertEquals(level.getServices().get("webapp").getPlatform(), "osx");
		assertEquals(level.getServices().get("webapp").getOom_kill_disable(), "true");
		assertEquals(level.getServices().get("webapp").getOom_score_adj(), "500");
		String[] security_opt = {"label:user:USER","label:role:ROLE"};
		assertArrayEquals(level.getServices().get("webapp").getSecurity_opt(), security_opt);
		assertEquals(level.getServices().get("webapp").getStop_grace_period(), "1s");
	    assertEquals(level.getServices().get("webapp").getStop_signal(), "SIGUSR1");
		String[] sysctls = {"net.core.somaxconn=1024","net.ipv4.tcp_syncookies=0"};
		assertArrayEquals(level.getServices().get("webapp").getSysctlsSL(), sysctls);
		assertEquals(level.getServices().get("webapp").getTmpfsS(), "/run");
		assertEquals(level.getServices().get("webapp").getUlimits().getNproc(), "65535");
		assertEquals(level.getServices().get("webapp").getUlimits().getNofile().getSoft(), 20000);
		assertEquals(level.getServices().get("webapp").getUlimits().getNofile().getHard(), 40000);
		assertEquals(level.getServices().get("webapp").getUserns_mode(), "host");
		assertEquals(level.getServices().get("webapp").getUser(), "postgresql");
		assertEquals(level.getServices().get("webapp").getWorking_dir(), "./testFiles");
		assertEquals(level.getServices().get("webapp").getDomainname(), "foo.com");
		assertEquals(level.getServices().get("webapp").getHostname(), "foo");
		assertEquals(level.getServices().get("webapp").getIpc(), "host");
		assertEquals(level.getServices().get("webapp").getMac_address(), "02:42:ac:11:65:43");
		assertEquals(level.getServices().get("webapp").getPrivileged(), "true");
		assertEquals(level.getServices().get("webapp").getRead_only(), "true");
		assertEquals(level.getServices().get("webapp").getCpu_rt_runtime(), "400ms");
		assertEquals(level.getServices().get("webapp").getScale(), "3");
		assertEquals(level.getServices().get("webapp").getShm_size(), "64M");
		assertEquals(level.getServices().get("webapp").getStdin_open(), "true");
		assertEquals(level.getServices().get("webapp").getTty(), "true");
		assertEquals(level.getServices().get("webapp").getVolume_driver(), "mydriver");
		Map<String,Condition> depends = new HashMap<>();
		Condition cond = new Condition();
		cond.setCondition("service_healthy");
		depends.put("db", cond);
		assertEquals(level.getServices().get("webapp").getDepends_onM().get("db").getCondition(), depends.get("db").getCondition());
		assertNotNull(level.getServices().get("webapp").getDeploy());
		assertEquals(level.getServices().get("webapp").getDeploy().getReplicas(), 6);
		assertEquals(level.getServices().get("webapp").getDeploy().getRestart_policy().getCondition(), "on-failure");
		assertEquals(level.getServices().get("webapp").getDeploy().getRestart_policy().getDelay(), "5s");
		assertEquals(level.getServices().get("webapp").getDeploy().getRestart_policy().getMax_attempts(), 3);
		assertEquals(level.getServices().get("webapp").getDeploy().getRestart_policy().getWindow(), "120s");
		assertEquals(level.getServices().get("webapp").getDeploy().getEndpoint_mode(), "vip");
		Map<String,String> dlabels = new HashMap<>();
		dlabels.put("com.example.description", "This label will appear on the web service");
		assertEquals(level.getServices().get("webapp").getDeploy().getLabelsM(), dlabels);
		assertEquals(level.getServices().get("webapp").getDeploy().getMode(), "global");
		String[] constraints = {"node.role == manager", "engine.labels.operatingsystem == ubuntu 14.04"};
		assertArrayEquals(level.getServices().get("webapp").getDeploy().getPlacement().getConstraints(), constraints);
		String[] preferences = {"spread == node.labels.zone"};
		assertArrayEquals(level.getServices().get("webapp").getDeploy().getPlacement().getPreferences(), preferences);
		assertEquals(level.getServices().get("webapp").getDeploy().getResources().getLimits().getCpus(),"0.50");
		assertEquals(level.getServices().get("webapp").getDeploy().getResources().getLimits().getMemory(),"50M");
		assertEquals(level.getServices().get("webapp").getDeploy().getResources().getReservations().getCpus(),"0.25");
		assertEquals(level.getServices().get("webapp").getDeploy().getResources().getReservations().getMemory(),"20M");
		assertEquals(level.getServices().get("webapp").getDeploy().getRollback_config().getParallelism(), 2);
		assertEquals(level.getServices().get("webapp").getDeploy().getRollback_config().getDelay(), "10s");
		assertEquals(level.getServices().get("webapp").getDeploy().getRollback_config().getOrder(), "stop-first");
		assertEquals(level.getServices().get("webapp").getDeploy().getRollback_config().getFailure_action(), "pause");
		assertEquals(level.getServices().get("webapp").getDeploy().getRollback_config().getMonitor(), "2s");
		assertEquals(level.getServices().get("webapp").getDeploy().getRollback_config().getMax_failure_ratio(), "3");
		assertEquals(level.getServices().get("webapp").getDeploy().getUpdate_config().getParallelism(), 1);
		assertEquals(level.getServices().get("webapp").getDeploy().getUpdate_config().getDelay(), "11s");
		assertEquals(level.getServices().get("webapp").getDeploy().getUpdate_config().getOrder(), "start-first");
		assertEquals(level.getServices().get("webapp").getDeploy().getUpdate_config().getFailure_action(), "rollback");
		assertEquals(level.getServices().get("webapp").getDeploy().getUpdate_config().getMonitor(), "5s");
		assertEquals(level.getServices().get("webapp").getDeploy().getUpdate_config().getMax_failure_ratio(), "4");
		assertEquals(level.getServices().get("webapp").getInit(), "true");
		String[] links = {"db", "db:database", "redis"};
		assertArrayEquals(level.getServices().get("webapp").getLinks(), links);
		assertEquals(level.getServices().get("webapp").getLogging().getDriver(), "syslog");
		assertEquals(level.getServices().get("webapp").getLogging().getOptions().getSyslogAddress(), "tcp://192.168.0.42:123");
		assertEquals(level.getServices().get("webapp").getLogging().getOptions().getMaxSize(), "200k");
		assertEquals(level.getServices().get("webapp").getLogging().getOptions().getMaxFile(), 10);
		assertNotNull(level.getServices().get("webapp").getNetworksM());
		String[] alias = {"alias1", "alias3"};
		String[] link_ips = {"57.123.22.11", "57.123.22.13"};
		assertArrayEquals(level.getServices().get("webapp").getNetworksM().get("some-network").getLink_local_ips(), link_ips);
		assertArrayEquals(level.getServices().get("webapp").getNetworksM().get("some-network").getAliases(), alias);
		assertEquals(level.getServices().get("webapp").getNetworksM().get("some-network").getPriority(), "100");
		assertEquals(level.getServices().get("webapp").getNetworksM().get("app_net").getIpv4_address(), "172.16.238.10");
		assertEquals(level.getServices().get("webapp").getNetworksM().get("app_net").getIpv6_address(), "2001:3984:3989::1");
		assertNotNull(level.getServices().get("webapp").getSecretsSL());
		assertEquals(level.getServices().get("webapp").getSecretsSL()[0].getSource(), "my_secret");
		assertEquals(level.getServices().get("webapp").getSecretsSL()[0].getTarget(), "redis_secret");
		assertEquals(level.getServices().get("webapp").getSecretsSL()[0].getUid(), 103);
		assertEquals(level.getServices().get("webapp").getSecretsSL()[0].getGid(), 104);
		assertEquals(level.getServices().get("webapp").getSecretsSL()[0].getMode(), "0440");
		assertEquals(level.getServices().get("webapp").getPortsP()[0].getTarget(), "80");
		assertEquals(level.getServices().get("webapp").getPortsP()[0].getPublished(), "8080");
		assertEquals(level.getServices().get("webapp").getPortsP()[0].getProtocol(), "tcp");
		assertEquals(level.getServices().get("webapp").getPortsP()[0].getMode(), "host");
		assertNotNull(level.getServices().get("webapp").getVolumesVL());
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getType(), "volume");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getSource(), "mydata");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getTarget(), "./testConfigs");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getVolume().getNocopy(), "true");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getRead_only(), "true");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getBind().getPropagation(), "1");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getTmpfs().getSize(), "3gb");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getConsistency(), "cached");
		assertEquals(level.getConfigs().get("my_config").getFile(), "./testFiles/test.env");
		assertEquals(level.getConfigs().get("my_config").getExternalM().get("name"), "redis_config");
		assertNotNull(level.getNetworksN().get("app_net"));
		assertEquals(level.getNetworksN().get("app_net").getExternal(), "true");
		assertEquals(level.getNetworksN().get("app_net").getName(), "host");
		assertEquals(level.getNetworksN().get("app_net").getAttachable(), "true");
		assertEquals(level.getNetworksN().get("app_net").getDriver(), "bridge");
		assertEquals(level.getNetworksN().get("app_net").getIpam().getDriver(), "default");
		String subnet = "172.16.238.0/24";
		assertEquals(level.getNetworksN().get("app_net").getIpam().getSubnet(), subnet);
		Map<String,String> driver_opts2 = new HashMap<>();
		driver_opts2.put("foo", "bar");
		driver_opts2.put("baz", "1");
		assertEquals(level.getNetworksN().get("app_net").getDriver_opts(), driver_opts2);
		assertEquals(level.getNetworksN().get("app_net").getEnable_ipv6(), "true");
		String[] labels2 = {"com.example.description=Financial transaction network" };
		assertArrayEquals(level.getNetworksN().get("app_net").getLabelsS(), labels2);
		assertEquals(level.getSecretsSL()[0].getFile(), "./testFiles/test.env");
		assertEquals(level.getSecretsSL()[0].getSource(), "my_secret");
		assertEquals(level.getSecretsSL()[0].getExternalS(), "true");
		assertEquals(level.getSecretsSL()[0].getName(), "redis_secret");
		assertNotNull(level.getVolumes().get("mydata"));
		Map<String,String> driver_opts = new HashMap<>();
		driver_opts.put("type", "nfs");
		driver_opts.put("o", "addr=10.40.0.199,nolock,soft,rw");
		driver_opts.put("device", ":/docker/example");
		assertEquals(level.getVolumes().get("mydata").getDriver_opts(), driver_opts);
		assertArrayEquals(level.getVolumes().get("mydata").getLabelsS(), labelsV);
		assertEquals(level.getVolumes().get("mydata").getName(), "my-app-data");	
	}
	
	
}

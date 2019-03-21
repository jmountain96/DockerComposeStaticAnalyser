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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testIncorrectValue()
	{
		try {
			YamlParser.ParseFile("testConfigs/testIncorrectValue.yaml");
			fail("Expected an UnrecognizedPropertyException to be thrown");
		} 
		catch(MismatchedInputException MIE)
		{
			assertTrue(MIE.getMessage().startsWith("Cannot deserialize instance of `int` out of START_ARRAY token"));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testFullComposeTrue() 
	{
		TopLevel level = new TopLevel();
		try {
			level = YamlParser.ParseFile("testConfigs/testFullCompose.yaml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(level.getVersion(), "3.1");
		assertNotNull(level.getServices().get("webapp"));
		assertNotNull(level.getServices().get("webapp").getBuildB());
		assertEquals(level.getServices().get("webapp").getBuildB().getContext(), "./dir");
		assertEquals(level.getServices().get("webapp").getBuildB().getDockerfile(), "Dockerfile-alternate");
		Map<String,Object> args = new LinkedHashMap<>();;
		args.put("buildno", 1);
		assertEquals(level.getServices().get("webapp").getBuildB().getArgsM().get("buildno"), args.get("buildno"));
		String[] labels = {"com.example.description=Accounting webapp", "com.example.department=Finance","com.example.label-with-empty-value" };
		System.out.println(ReflectionToStringBuilder.toString(level.getServices().get("webapp"),ToStringStyle.MULTI_LINE_STYLE));
		assertArrayEquals(level.getServices().get("webapp").getBuildB().getLabelsS(), labels);
		assertEquals(level.getServices().get("webapp").getBuildB().getTarget(), "prod");
		assertEquals(level.getServices().get("webapp").getBuildB().getShm_size(), "2gb");
		assertNotNull(level.getServices().get("webapp").getConfigsC());
		assertEquals(level.getServices().get("webapp").getConfigsC()[0].getSource(), "my_config");
		assertEquals(level.getServices().get("webapp").getConfigsC()[0].getTarget(), "/redis_config");
		assertEquals(level.getServices().get("webapp").getConfigsC()[0].getUid(), "103");
		assertEquals(level.getServices().get("webapp").getConfigsC()[0].getGid(), "104");
		assertEquals(level.getServices().get("webapp").getConfigsC()[0].getMode(), "0440");
		String[] depends = {"db"};
		assertArrayEquals(level.getServices().get("webapp").getDepends_on(), depends);
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
		assertArrayEquals(level.getServices().get("webapp").getNetworksM().get("some-network").getAliases(), alias);
		assertEquals(level.getServices().get("webapp").getNetworksM().get("app_net").getIpv4_address(), "172.16.238.10");
		assertEquals(level.getServices().get("webapp").getNetworksM().get("app_net").getIpv6_address(), "2001:3984:3989::1");
		assertNotNull(level.getServices().get("webapp").getSecretsSL());
		assertEquals(level.getServices().get("webapp").getSecretsSL()[0].getSource(), "my_secret");
		assertEquals(level.getServices().get("webapp").getSecretsSL()[0].getTarget(), "redis_secret");
		assertEquals(level.getServices().get("webapp").getSecretsSL()[0].getUid(), 103);
		assertEquals(level.getServices().get("webapp").getSecretsSL()[0].getGid(), 104);
		assertEquals(level.getServices().get("webapp").getSecretsSL()[0].getMode(), "0440");
		assertNotNull(level.getServices().get("webapp").getVolumesVL());
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getType(), "volume");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getSource(), "mydata");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getTarget(), "/data");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getVolume().get("nocopy"), "true");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getRead_only(), "true");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getBind().get("propagation"), "1");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getTmpfs().get("size"), "3gb");
		assertEquals(level.getServices().get("webapp").getVolumesVL()[0].getConsistency(), "cached");
		assertNotNull(level.getBuildB());
		assertEquals(level.getBuildB().getContext(), "./dir");
		assertEquals(level.getBuildB().getDockerfile(), "Dockerfile-alternate");
		Map<String,Object> argsB = new LinkedHashMap<>();;
		argsB.put("buildno", 1);
		argsB.put("gitcommithash", "cdc3b19");
		assertEquals(level.getBuildB().getArgsM().get("buildno"), argsB.get("buildno"));
		assertEquals(level.getBuildB().getArgsM().get("gitcommithash"), argsB.get("gitcommithash"));
		String[] labelsB = {"com.example.description=Accounting webapp", "com.example.department=Finance","com.example.label-with-empty-value" };
		assertArrayEquals(level.getBuildB().getLabelsS(), labelsB);
		assertEquals(level.getBuildB().getTarget(), "prod");
		assertEquals(level.getBuildB().getShm_size(), "2gb");
		assertEquals(level.getCap_add()[0], "ALL");
		String[] cap_drop = {"NET_ADMIN", "SYS_ADMIN"};
		assertArrayEquals(level.getCap_drop(), cap_drop);
		assertEquals(level.getCgroup_parent(), "m-executor-abcd");
		assertEquals(level.getCommand(), "bundle exec thin -p 3000");
		assertEquals(level.getContainer_name(), "my-web-container");
		assertEquals(level.getConfigs().get("my_first_config").getFile(), "./config_data");
		assertEquals(level.getConfigs().get("my_second_config").getExternalM().get("name"), "redis_config");
		assertEquals(level.getCredential_spec().getFile(), "my-credential-spec.json");
		assertEquals(level.getCredential_spec().getRegistry(), "my-credential-spec");
		String[] devices = {"/dev/ttyUSB0:/dev/ttyUSB0"};
		assertArrayEquals(level.getDevices(), devices);
		assertEquals(level.getDnsS(), "8.8.8.8");
		assertEquals(level.getDns_search(), "foo.com");
		assertEquals(level.getEntrypointS(), "/code/entrypoint.sh");
		assertEquals(level.getEnv_fileS(), ".env");
		String[] environment = {"RACK_ENV=development", "SHOW=true", "SESSION_SECRET"};
		assertArrayEquals(level.getEnvironmentSL(), environment);
		String[] expose = {"3000","8000"};
		assertArrayEquals(level.getExpose(), expose);
		String[] external_links = {"redis_1","project_db_1:mysql", "project_db_1:postgresql"};
		assertArrayEquals(level.getExternal_links(), external_links);
		String[] extra_hosts = {"somehost:162.242.195.82","otherhost:50.31.209.229"};
		assertArrayEquals(level.getExtra_hosts(), extra_hosts);
		assertNotNull(level.getHealthcheck());
		String[] health_test = {"CMD", "curl", "-f", "http://localhost"};
		assertArrayEquals(level.getHealthcheck().getTestSL(), health_test);
		assertEquals(level.getHealthcheck().getInterval(), "1m30s");
		assertEquals(level.getHealthcheck().getTimeout(), "10s");
		assertEquals(level.getHealthcheck().getRetries(), 3);
		assertEquals(level.getHealthcheck().getStart_period(), "40s");
		assertEquals(level.getIsolation(), "default");
		assertEquals(level.getNetwork_mode(), "bridge");
		assertNotNull(level.getNetworksN().get("app_net"));
		assertEquals(level.getNetworksN().get("app_net").getExternal(), "true");
		assertEquals(level.getNetworksN().get("app_net").getName(), "host");
		assertEquals(level.getNetworksN().get("app_net").getAttachable(), "true");
		assertEquals(level.getNetworksN().get("app_net").getDriver(), "bridge");
		assertEquals(level.getNetworksN().get("app_net").getEnable_ipv6(), "true");
		assertEquals(level.getNetworksN().get("app_net").getIpam().getDriver(), "default");
		String subnet = "172.16.238.0/24";
		assertEquals(level.getNetworksN().get("app_net").getIpam().getSubnet(), subnet);
		assertEquals(level.getPid(), "host");
		assertEquals(level.getPortsP()[0].getTarget(), "80");
		assertEquals(level.getPortsP()[0].getPublished(), "8080");
		assertEquals(level.getPortsP()[0].getProtocol(), "tcp");
		assertEquals(level.getPortsP()[0].getMode(), "host");
		assertEquals(level.getRestart(), "no");
		assertEquals(level.getSecretsSL()[0].getFile(), "./secret_data");
		assertEquals(level.getSecretsSL()[0].getSource(), "my_first_secret");
		assertEquals(level.getSecretsSL()[1].getExternalS(), "true");
		assertEquals(level.getSecretsSL()[1].getSource(), "my_second_secret");
		assertEquals(level.getSecretsSL()[1].getName(), "redis_secret");
		String[] security_opt = {"label:user:USER","label:role:ROLE"};
		assertArrayEquals(level.getSecurity_opt(), security_opt);
		assertEquals(level.getStop_grace_period(), "1s");
		assertEquals(level.getStop_signal(), "SIGUSR1");
		String[] sysctls = {"net.core.somaxconn=1024","net.ipv4.tcp_syncookies=0"};
		assertArrayEquals(level.getSysctlsSL(), sysctls);
		assertEquals(level.getTmpfsS(), "/run");
		assertEquals(level.getUlimits().getNproc(), "65535");
		assertEquals(level.getUlimits().getNofile().getSoft(), 20000);
		assertEquals(level.getUlimits().getNofile().getHard(), 40000);
		assertEquals(level.getUserns_mode(), "host");
		assertNotNull(level.getVolumes().get("example"));
		Map<String,String> driver_opts = new HashMap<>();
		driver_opts.put("type", "nfs");
		driver_opts.put("o", "addr=10.40.0.199,nolock,soft,rw");
		driver_opts.put("device", ":/docker/example");
		assertEquals(level.getVolumes().get("example").getDriver_opts(), driver_opts);
		String[] labelsV = {"com.example.description=Database volume", "com.example.department=IT/Ops","com.example.label-with-empty-value" };
		assertArrayEquals(level.getVolumes().get("example").getLabelsS(), labelsV);
		assertEquals(level.getVolumes().get("example").getName(), "my-app-data");	
		assertEquals(level.getUser(), "postgresql");
		assertEquals(level.getWorking_dir(), "/code");
		assertEquals(level.getDomainname(), "foo.com");
		assertEquals(level.getHostname(), "foo");
		assertEquals(level.getIpc(), "host");
		assertEquals(level.getMac_address(), "02:42:ac:11:65:43");
		assertEquals(level.getPrivileged(), "true");
		assertEquals(level.getRead_only(), "true");
		assertEquals(level.getShm_size(), "64M");
		assertEquals(level.getStdin_open(), "true");
		assertEquals(level.getTty(), "true");
	}
	
	
}

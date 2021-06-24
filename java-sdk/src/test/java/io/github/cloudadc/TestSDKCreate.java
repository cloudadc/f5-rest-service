package io.github.cloudadc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.cloudadc.iControl.model.Pool;
import io.github.cloudadc.iControl.model.SnatPool;
import io.github.cloudadc.iControl.model.VirtualServer;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class TestSDKCreate extends TestSDK {
	
	@Test
	public void testCreatePool() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.createPool("pool_test_1", "round-robin", "tcp", new String[] {"10.1.20.21:8081", "10.1.20.22:8081", "10.1.20.23:8081"});
		
		Pool p = w.getPoolByNameExpandSubcollections("pool_test_1");
		assertEquals(p.loadBalancingMode, "round-robin");
		assertEquals(p.monitor, "/Common/tcp");
		assertEquals(p.membersReference.items.size(), 3);
		
		w.deletePool("pool_test_1");
		
		w.shutdown();
	}
	
	@Test
	public void testCreatePoolJSON() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.createPool("{\"name\":\"pool_test_1\",\"monitor\": \"http\", \"loadBalancingMode\": \"round-robin\", \"members\":[ {\"name\":\"10.1.20.21:8081\"} ] }");
		
		Pool p = w.getPoolByNameExpandSubcollections("pool_test_1");
		assertEquals(p.loadBalancingMode, "round-robin");
		assertEquals(p.monitor, "/Common/http");
		assertEquals(p.membersReference.items.size(), 1);
		
		w.deletePool("pool_test_1");
		
		w.shutdown();
	}
	
	@Test
	public void testCreatePoolBashScript() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.bashScripts("tmsh create ltm pool pool_test_1 members add { 10.1.20.21:8081 { address 10.1.20.21 } } monitor http");
		
		Pool p = w.getPoolByNameExpandSubcollections("pool_test_1");
		assertEquals(p.loadBalancingMode, "round-robin");
		assertEquals(p.monitor, "/Common/http");
		assertEquals(p.membersReference.items.size(), 1);
		
		w.bashScripts("tmsh delete ltm pool pool_test_1");
	
		w.shutdown();
	}
	
	@Test
	public void testCreateSNATPool() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.createSnatPool("snat_test_1", new String[] {"10.1.10.109", "10.1.10.110"});
		
		SnatPool p = w.getSnatPoolByName("snat_test_1");
		assertTrue(p.members.contains("/Common/10.1.10.109"));
		assertTrue(p.members.contains("/Common/10.1.10.110"));
		
		w.deleteSnatPool("snat_test_1");
		
		w.shutdown();
	}
	
	@Test
	public void testCreateSNATPoolJSON() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.createSnatPool("{\"name\":\"snat_test_1\",\"members\":[\"10.1.10.109\", \"10.1.10.110\"]}");
		
		SnatPool p = w.getSnatPoolByName("snat_test_1");
		assertTrue(p.members.contains("/Common/10.1.10.109"));
		assertTrue(p.members.contains("/Common/10.1.10.110"));
		
		w.deleteSnatPool("snat_test_1");
		
		w.shutdown();
	}
	
	@Test
	public void testCreateSNATPoolBash() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.bashScripts("tmsh create ltm snatpool snat_test_1 { members add { 10.1.10.109} members add { 10.1.10.110} }");
		
		SnatPool p = w.getSnatPoolByName("snat_test_1");
		assertTrue(p.members.contains("/Common/10.1.10.109"));
		assertTrue(p.members.contains("/Common/10.1.10.110"));
		
		w.bashScripts("tmsh delete ltm snatpool snat_test_1");
		
		w.shutdown();
	}
	
	@Test
	public void testCreateVSFastL4() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.createVirtualServer("vs_test_1", "10.1.10.20:80", "pool_1");
		
		VirtualServer vs = w.getVirtualServerByName("vs_test_1");
		assertEquals(vs.destination, "/Common/10.1.10.20:80");
		assertEquals(vs.pool, "/Common/pool_1");
		assertNull(vs.persist);
		
		w.deleteVirtualServer("vs_test_1");
		
		w.shutdown();
		
	}
	
	@Test
	public void testCreateVSFastL4JSON() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.createVirtualServer("{\"name\": \"vs_test_1\", \"destination\": \"10.1.10.20:80\", \"mask\": \"255.255.255.255\", \"pool\": \"pool_1\"}" );
		
		VirtualServer vs = w.getVirtualServerByName("vs_test_1");
		assertEquals(vs.destination, "/Common/10.1.10.20:80");
		assertEquals(vs.pool, "/Common/pool_1");
		assertNull(vs.persist);
		
		w.deleteVirtualServer("vs_test_1");
		
		w.shutdown();
		
	}
	
	@Test
	public void testCreateVSFastL4Perist() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.createVirtualServer("vs_test_1", "10.1.10.20:80", "pool_1", "source_addr");
		
		VirtualServer vs = w.getVirtualServerByName("vs_test_1");
		assertEquals(vs.destination, "/Common/10.1.10.20:80");
		assertEquals(vs.pool, "/Common/pool_1");
		assertTrue(vs.persist != null && vs.persist.size() == 1 && vs.persist.get(0).name.equals("source_addr"));
		
		w.deleteVirtualServer("vs_test_1");
		
		w.shutdown();
		
	}
	
	@Test
	public void testCreateVSFastL4PeristJSON() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.createVirtualServer("{\"name\": \"vs_test_1\", \"destination\": \"10.1.10.20:80\", \"mask\": \"255.255.255.255\", \"pool\": \"pool_1\", \"persist\": [ {\"name\": \"source_addr\"} ] }");
		
		VirtualServer vs = w.getVirtualServerByName("vs_test_1");
		assertEquals(vs.destination, "/Common/10.1.10.20:80");
		assertEquals(vs.pool, "/Common/pool_1");
		assertTrue(vs.persist != null && vs.persist.size() == 1 && vs.persist.get(0).name.equals("source_addr"));
		
		w.deleteVirtualServer("vs_test_1");
		
		w.shutdown();
		
	}
	
	@Test
	public void testCreateVSFastL4PeristBASH() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.bashScripts("tmsh create ltm virtual vs_test_1 destination 10.1.10.20:80 persist replace-all-with { source_addr } pool pool_1");
		
		VirtualServer vs = w.getVirtualServerByName("vs_test_1");
		assertEquals(vs.destination, "/Common/10.1.10.20:80");
		assertEquals(vs.pool, "/Common/pool_1");
		assertTrue(vs.persist != null && vs.persist.size() == 1 && vs.persist.get(0).name.equals("source_addr"));
		
		
		w.bashScripts("tmsh delete ltm virtual vs_test_1");
		
		w.shutdown();
		
	}

}

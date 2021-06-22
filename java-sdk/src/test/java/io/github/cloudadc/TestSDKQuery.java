package io.github.cloudadc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import io.github.cloudadc.iControl.model.Member;
import io.github.cloudadc.iControl.model.MembersReference;
import io.github.cloudadc.iControl.model.Node;
import io.github.cloudadc.iControl.model.NodesReference;
import io.github.cloudadc.iControl.model.Pool;
import io.github.cloudadc.iControl.model.PoolsReference;
import io.github.cloudadc.iControl.model.VirtualServer;
import io.github.cloudadc.iControl.model.VirtualServersReference;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class TestSDKQuery extends TestSDK {
	
	@Test
	public void listAllVirtualServers() {
		VirtualServersReference reference = Wrapper.create(HOST, USER, PASSWORD).listAllVirtualServers();
		Set<String> set = new HashSet<>();
		reference.items.forEach(v -> set.add(v.name));
		assertTrue(set.contains("vs_1"));
		assertTrue(set.contains("vs_2"));
		assertTrue(set.contains("vs_3"));
		assertTrue(set.contains("vs_4"));
		assertTrue(set.contains("vs_5"));
		assertTrue(set.contains("vs_6"));
	}
	
	@Test
	public void listAllVirtualServersExpandSubcollections() {
		VirtualServersReference reference =  Wrapper.create(HOST, USER, PASSWORD).listAllVirtualServersExpandSubcollections();
		Set<String> set = new HashSet<>();
		reference.items.forEach(v -> set.add(v.name));
		assertTrue(set.contains("vs_1"));
		assertTrue(set.contains("vs_2"));
		assertTrue(set.contains("vs_3"));
		assertTrue(set.contains("vs_4"));
		assertTrue(set.contains("vs_5"));
		assertTrue(set.contains("vs_6"));
	}
	
	@Test
	public void getVirtualServerByName () {
		VirtualServer vs = Wrapper.create(HOST, USER, PASSWORD).getVirtualServerByName("vs_1");
		assertEquals(vs.name, "vs_1");
		assertEquals(vs.destination, "/Common/10.1.10.11:80");
		assertEquals(vs.ipProtocol, "tcp");
	}
	
	@Test
	public void getVirtualServerByNameExpandSubcollections() {
		VirtualServer vs = Wrapper.create(HOST, USER, PASSWORD).getVirtualServerByNameExpandSubcollections("vs_2");
		assertEquals(vs.name, "vs_2");
		assertEquals(vs.destination, "/Common/10.1.10.12:80");
		assertEquals(vs.ipProtocol, "tcp");
		assertEquals(vs.sourceAddressTranslation.type, "snat");
	}
	
	@Test
	public void listAllNodes() {
		NodesReference reference = Wrapper.create(HOST, USER, PASSWORD).listAllNodes();
		Set<String> set = new HashSet<>();
		reference.items.forEach(n -> set.add(n.name));
		assertTrue(set.contains("10.1.20.11"));
		assertTrue(set.contains("10.1.20.12"));
		assertTrue(set.contains("10.1.20.13"));
		assertTrue(set.contains("10.1.20.14"));
		assertTrue(set.contains("10.1.20.15"));
		assertTrue(reference.items.size() >= 14);
	}
	
	@Test
	public void getNodeByName () {
		Node n = Wrapper.create(HOST, USER, PASSWORD).getNodeByName("10.1.20.11");
		assertEquals(n.address, "10.1.20.11");
	}
	
	@Test
	public void listAllPools() {
		PoolsReference reference = Wrapper.create(HOST, USER, PASSWORD).listAllPools();
		Set<String> set = new HashSet<>();
		reference.items.forEach(p -> set.add(p.name));
		assertTrue(set.contains("pool_1"));
		assertTrue(set.contains("pool_2"));
		assertTrue(set.contains("pool_3"));
		assertTrue(set.contains("pool_4"));
		assertTrue(set.contains("pool_5"));
		assertTrue(set.contains("pool_6"));
		assertTrue(set.contains("pool_7"));
		assertTrue(set.contains("pool_8"));
	}
	
	@Test
	public void listAllPoolsExpandSubcollections() {
		PoolsReference reference = Wrapper.create(HOST, USER, PASSWORD).listAllPoolsExpandSubcollections();
		Set<String> set = new HashSet<>();
		reference.items.forEach(p -> set.add(p.name));
		assertTrue(set.contains("pool_1"));
		assertTrue(set.contains("pool_2"));
		assertTrue(set.contains("pool_3"));
		assertTrue(set.contains("pool_4"));
		assertTrue(set.contains("pool_5"));
		assertTrue(set.contains("pool_6"));
		assertTrue(set.contains("pool_7"));
		assertTrue(set.contains("pool_8"));
	}
	
	@Test
	public void getPoolByName() {
		Pool p = Wrapper.create(HOST, USER, PASSWORD).getPoolByName("pool_1");
		assertEquals(p.name, "pool_1");
		assertEquals(p.monitor, "/Common/http");
		assertEquals(p.loadBalancingMode, "round-robin");
	}
	
	@Test
	public void getPoolByNameExpandSubcollections() {
		Pool p = Wrapper.create(HOST, USER, PASSWORD).getPoolByNameExpandSubcollections("pool_1");
		assertEquals(p.name, "pool_1");
		assertEquals(p.monitor, "/Common/http");
		assertEquals(p.loadBalancingMode, "round-robin");
	}
	
	@Test
	public void listAllMembers() {
		MembersReference reference = Wrapper.create(HOST, USER, PASSWORD).listAllMembers("pool_1");
		assertEquals(3, reference.items.size());
	}
	
	@Test
	public void getMemberByName() {
		Member m = Wrapper.create(HOST, USER, PASSWORD).getMemberByName("pool_1", "10.1.20.11:8081");
		assertNotNull(m);
	}
	
}

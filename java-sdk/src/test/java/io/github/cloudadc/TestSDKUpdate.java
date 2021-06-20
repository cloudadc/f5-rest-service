package io.github.cloudadc;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.github.cloudadc.iControl.model.Member;
import io.github.cloudadc.iControl.model.Node;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class TestSDKUpdate extends TestSDK {

	@Test
	public void testNodeEnabledDisabledEnabled() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		w.nodeEnable("10.1.20.11");
		Node n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-enabled");
		w.nodeDiable("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-disabled");
		w.nodeEnable("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-enabled");
		w.shutdown();
	}
	
	@Test
	public void testNodeMonitorDisabledMonitorEnabled() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		w.nodeEnable("10.1.20.12");
		Node n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "monitor-enabled");
		w.nodeDiable("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "user-disabled");
		n = w.getNodeByName("10.1.20.12");
		w.nodeEnable("10.1.20.12");
		assertEquals(n.session, "monitor-enabled");
		w.shutdown();
	}
	
	@Test
	public void testNodeUncheckedUserDownUnchecked() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		w.nodeUp("10.1.20.11");
		Node n = w.getNodeByName("10.1.20.11");
		assertEquals(n.state, "unchecked");
		w.nodeOffline("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.state, "user-down");
		w.nodeUp("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.state, "unchecked");
		w.shutdown();
	}
	
	@Test
	public void testNodeUpUserDownDownUp() throws InterruptedException {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		w.nodeUp("10.1.20.12");
		Node n = w.getNodeByName("10.1.20.12");
		assertEquals(n.state, "up");
		w.nodeOffline("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.state, "user-down");
		w.nodeUp("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.state, "down");
		w.shutdown();
		//monitor enable will wait a health check interval
		TimeUnit.MILLISECONDS.sleep(1000 * 10);
		n = Wrapper.create(HOST, USER, PASSWORD).getNodeByName("10.1.20.12");
		assertEquals(n.state, "up");
		w.shutdown();
		
	}
	
	@Test
	public void testNodeWithoutMonitor() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		w.nodeEnable("10.1.20.11");
		w.nodeUp("10.1.20.11");
		Node n = w.getNodeByName("10.1.20.11");
		assertEquals(n.state, "unchecked");
		assertEquals(n.session, "user-enabled");
		w.nodeDiable("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.state, "unchecked");
		assertEquals(n.session, "user-disabled");
		w.nodeOffline("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.state, "user-down");
		assertEquals(n.session, "user-disabled");
		w.nodeEnable("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.state, "user-down");
		assertEquals(n.session, "user-enabled");
		w.nodeUp("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.state, "unchecked");
		assertEquals(n.session, "user-enabled");
		w.shutdown();
	}
	
	@Test
	public void testNodeWithMonitor() throws InterruptedException {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		w.nodeEnable("10.1.20.12");
		w.nodeUp("10.1.20.12");
		TimeUnit.MILLISECONDS.sleep(1000 * 15);
		Node n = w.getNodeByName("10.1.20.12");
		assertEquals(n.state, "up");
		assertEquals(n.session, "monitor-enabled");
		w.nodeDiable("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.state, "up");
		assertEquals(n.session, "user-disabled");
		w.nodeOffline("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.state, "user-down");
		assertEquals(n.session, "user-disabled");
		w.nodeEnable("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.state, "user-down");
		assertEquals(n.session, "user-enabled");
		w.nodeUp("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.state, "down");
		assertEquals(n.session, "user-enabled");
		TimeUnit.MILLISECONDS.sleep(1000 * 15);
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.state, "up");
		assertEquals(n.session, "monitor-enabled");
		w.shutdown();
		
	}
	
	@Test
	public void testMemberDisable() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		w.memberDisable("pool_1", "10.1.20.11:8081");
		Member m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.session, "user-disabled");
		w.memberEnable("pool_1", "10.1.20.11:8081");
		m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.session, "monitor-enabled");
		
	}
	
	@Test
	public void testMemberEnable() {
		Wrapper w1 = Wrapper.create(HOST, USER, PASSWORD);
		Wrapper w2 = Wrapper.create(HOST, USER, PASSWORD);
		w1.memberDisable("pool_1", "10.1.20.11:8081");
		w2.memberDisable("pool_2", "10.1.20.13:8081");
		Member m = w1.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.state, "up");
		assertEquals(m.session, "user-disabled");
		m = w2.getMemberByName("pool_2", "10.1.20.13:8081");
		assertEquals(m.state, "unchecked");
		assertEquals(m.session, "user-disabled");
		w1.memberEnable("pool_1", "10.1.20.11:8081");
		m = w1.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.state, "up");
		assertEquals(m.session, "monitor-enabled");
		w2.memberEnable("pool_2", "10.1.20.13:8081");
		m = w2.getMemberByName("pool_2", "10.1.20.13:8081");
		assertEquals(m.state, "unchecked");
		assertEquals(m.session, "user-enabled");
		w1.shutdown();
		w2.shutdown();
	}
	
	@Test
	public void testMemberOffline() throws InterruptedException {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		w.memberOffline("pool_1", "10.1.20.11:8081");
		Member m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.state, "user-down");
		assertEquals(m.session, "monitor-enabled");
		w.memberUp("pool_1", "10.1.20.11:8081");
		m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.state, "down");
		assertEquals(m.session, "monitor-enabled");
		TimeUnit.MILLISECONDS.sleep(1000 * 15);
		m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.state, "up");
		assertEquals(m.session, "monitor-enabled");
	}
	
	@Test
	public void testMemberUp() throws InterruptedException {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		w.memberOffline("pool_2", "10.1.20.13:8081");
		Member m = w.getMemberByName("pool_2", "10.1.20.13:8081");
		assertEquals(m.state, "user-down");
		assertEquals(m.session, "user-enabled");
		w.memberUp("pool_2", "10.1.20.13:8081");
		m = w.getMemberByName("pool_2", "10.1.20.13:8081");
		assertEquals(m.state, "unchecked");
		assertEquals(m.session, "user-enabled");
	}
	
	@Test
	public void test() {
		Node m = new Node();
		System.out.println(m.state + " " + m.session);
	}
}

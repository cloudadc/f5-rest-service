package io.github.cloudadc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.github.cloudadc.iControl.model.Member;
import io.github.cloudadc.iControl.model.Node;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class TestSDKDisableForceOffline extends TestSDK {

	/**
	 * Node without monitor
	 *  Enable -> Disable -> Enable
	 */
	@Test
	public void testNodeDisabledWithoutMonitor() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.nodeEnable("10.1.20.11");
		Node n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		
		w.nodeDiable("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "unchecked");
		
		w.nodeEnable("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");

		w.shutdown();
	}
	
	/**
	 * Node with monitor
	 *  Enable -> Disable -> Enable
	 */
	@Test
	public void testNodeDisabledWithMonitor() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.nodeEnable("10.1.20.12");
		Node n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "up");
		
		w.nodeDiable("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "up");
		
		w.nodeEnable("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "up");
		w.shutdown();	
	}
	
	/**
	 * Node with monitor
	 *  Enable -> Disable -> Enable
	 */
	@Test
	public void testNodeDisabledWithMonitorDown() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.nodeEnable("10.1.20.21");
		Node n = w.getNodeByName("10.1.20.21");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "down");
		
		w.nodeDiable("10.1.20.21");
		n = w.getNodeByName("10.1.20.21");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "down");
		
		w.nodeEnable("10.1.20.21");
		n = w.getNodeByName("10.1.20.21");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "down");
		w.shutdown();	
	}
	
	/**
	 * Node without monitor
	 *  Up -> Offline -> Up
	 */
	@Test
	public void testNodeOfflineWithoutMonitor() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.nodeUp("10.1.20.11");
		Node n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		
		w.nodeOffline("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "user-down");
		
		w.nodeUp("10.1.20.11");
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		w.shutdown();
	}
	
	/**
	 * Node with monitor
	 *  Up -> Offline -> Up
	 */
	@Test
	public void testNodeOfflineWithMonitor() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.nodeUp("10.1.20.12");
		Node n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "up");
		
		w.nodeOffline("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "user-down");
		
		w.nodeUp("10.1.20.12");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "up");

		w.shutdown();
	}
	
	/**
	 * Node with monitor
	 *  Down -> Offline -> Down
	 */
	@Test
	public void testNodeOfflineWithMonitorDown() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.nodeUp("10.1.20.21");
		Node n = w.getNodeByName("10.1.20.21");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "down");
		
		w.nodeOffline("10.1.20.21");
		n = w.getNodeByName("10.1.20.21");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "user-down");
		
		w.nodeUp("10.1.20.21");
		n = w.getNodeByName("10.1.20.21");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "down");

		w.shutdown();	
	}
	
	/**
	 * Member without monitor
	 */
	@Test
	public void testMemberDisableWithoutMonitor() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.memberEnable("pool_2", "10.1.20.13:8081");
		Member m = w.getMemberByName("pool_2", "10.1.20.13:8081");
		assertEquals(m.session, "user-enabled");
		assertEquals(m.state, "unchecked");
		
		w.memberDisable("pool_2", "10.1.20.13:8081");
		m = w.getMemberByName("pool_2", "10.1.20.13:8081");
		assertEquals(m.session, "user-disabled");
		assertEquals(m.state, "unchecked");
		
		w.memberEnable("pool_2", "10.1.20.13:8081");
		m = w.getMemberByName("pool_2", "10.1.20.13:8081");
		assertEquals(m.session, "user-enabled");
		assertEquals(m.state, "unchecked");
		
		w.shutdown();
	}
	
	/**
	 * Member with Monitor
	 */
	@Test
	public void testMemberDisableWithMonitorUp() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.memberEnable("pool_1", "10.1.20.11:8081");
		Member m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.session, "monitor-enabled");
		assertEquals(m.state, "up");
		
		w.memberDisable("pool_1", "10.1.20.11:8081");
		m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.session, "user-disabled");
		assertEquals(m.state, "up");
		
		w.memberEnable("pool_1", "10.1.20.11:8081");
		m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.session, "monitor-enabled");
		assertEquals(m.state, "up");
		
		w.shutdown();
		
		System.out.println(m.session + " " + m.state);
	}
	
	/**
	 * Member with Monitor
	 */
	@Test
	public void testMemberDisableWithMonitorDown() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.memberEnable("pool_1", "10.1.20.23:8081");
		Member m = w.getMemberByName("pool_1", "10.1.20.23:8081");
		assertEquals(m.session, "monitor-enabled");
		assertEquals(m.state, "down");
		
		w.memberDisable("pool_1", "10.1.20.23:8081");
		m = w.getMemberByName("pool_1", "10.1.20.23:8081");
		assertEquals(m.session, "user-disabled");
		assertEquals(m.state, "down");
		
		w.memberEnable("pool_1", "10.1.20.23:8081");
		m = w.getMemberByName("pool_1", "10.1.20.23:8081");
		assertEquals(m.session, "monitor-enabled");
		assertEquals(m.state, "down");
		
		w.shutdown();
	}
	
	/**
	 * Member with and without monitor comparing
	 */
	@Test
	public void testMemberDisableMultipleConnection() {
		
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
	
	/**
	 * Member without monitor
	 * @throws InterruptedException
	 */
	@Test
	public void testMemberOfflineWithoutMonitor() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.memberUp("pool_2", "10.1.20.13:8081");
		Member m = w.getMemberByName("pool_2", "10.1.20.13:8081");
		assertEquals(m.session, "user-enabled");
		assertEquals(m.state, "unchecked");
		
		w.memberOffline("pool_2", "10.1.20.13:8081");
		m = w.getMemberByName("pool_2", "10.1.20.13:8081");
		assertEquals(m.session, "user-disabled");
		assertEquals(m.state, "user-down");
		
		w.memberUp("pool_2", "10.1.20.13:8081");
		m = w.getMemberByName("pool_2", "10.1.20.13:8081");
		assertEquals(m.session, "user-enabled");
		assertEquals(m.state, "unchecked");
		
		w.shutdown();
	}
	
	/**
	 * Member with monitor, monitor results is up
	 * @throws InterruptedException
	 */
	@Test
	public void testMemberOfflineWithMonitorUp() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.memberUp("pool_1", "10.1.20.11:8081");
		Member m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.session, "monitor-enabled");
		assertEquals(m.state, "up");
		
		w.memberOffline("pool_1", "10.1.20.11:8081");
		m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.session, "user-disabled");
		assertEquals(m.state, "user-down");
		
		w.memberUp("pool_1", "10.1.20.11:8081");
		m = w.getMemberByName("pool_1", "10.1.20.11:8081");
		assertEquals(m.session, "monitor-enabled");
		assertEquals(m.state, "up");
		
		w.shutdown();
	}
	
	/**
	 * Member with monitor, monitor results is down
	 * @throws InterruptedException
	 */
	@Test
	public void testMemberOfflineWithMonitorDown() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.memberUp("pool_1", "10.1.20.23:8081");
		Member m = w.getMemberByName("pool_1", "10.1.20.23:8081");
		assertEquals(m.session, "monitor-enabled");
		assertEquals(m.state, "down");
		
		w.memberOffline("pool_1", "10.1.20.23:8081");
		m = w.getMemberByName("pool_1", "10.1.20.23:8081");
		assertEquals(m.session, "user-disabled");
		assertEquals(m.state, "user-down");
		
		w.memberUp("pool_1", "10.1.20.23:8081");
		m = w.getMemberByName("pool_1", "10.1.20.23:8081");
		assertEquals(m.session, "monitor-enabled");
		assertEquals(m.state, "down");
		
		w.shutdown();
	}
}

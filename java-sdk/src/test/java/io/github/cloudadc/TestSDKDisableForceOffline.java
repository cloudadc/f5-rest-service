package io.github.cloudadc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.github.cloudadc.iControl.model.*;
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
	
	@Test
	public void testNodesDisable() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.nodeEnable(new String[] {"10.1.20.11", "10.1.20.12", "10.1.20.13"});
		
		Node n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "up");
		n = w.getNodeByName("10.1.20.13");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		
		w.nodeDiable(new String[] {"10.1.20.11", "10.1.20.12", "10.1.20.13"});
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "unchecked");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "up");
		n = w.getNodeByName("10.1.20.13");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "unchecked");
		
        w.nodeEnable(new String[] {"10.1.20.11", "10.1.20.12", "10.1.20.13"});
		
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "up");
		n = w.getNodeByName("10.1.20.13");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		
		w.shutdown();
	}
	
	@Test
	public void testNodesDisableBash() {
		
        Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		String bash = "tmsh modify ltm node 10.1.20.11 session user-disabled; tmsh modify ltm node 10.1.20.12 session user-disabled;tmsh modify ltm node 10.1.20.13 session user-disabled";
		BashResponse resp = w.bashScripts(bash);
		assertTrue(resp.utilCmdArgs.contains(bash));
		
		Node n1 = w.getNodeByName("10.1.20.11");
		Node n2 = w.getNodeByName("10.1.20.12");
		Node n3 = w.getNodeByName("10.1.20.13");
		
		assertEquals("user-disabled", n1.session);
		assertEquals("user-disabled", n2.session);
		assertEquals("user-disabled", n3.session);
		assertEquals("unchecked", n1.state);
		assertEquals("up", n2.state);
		assertEquals("unchecked", n3.state);
		
		bash = "tmsh modify ltm node 10.1.20.11 session user-enabled; tmsh modify ltm node 10.1.20.12 session user-enabled;tmsh modify ltm node 10.1.20.13 session user-enabled";
		resp = w.bashScripts(bash);
		assertTrue(resp.utilCmdArgs.contains(bash));
		
		n1 = w.getNodeByName("10.1.20.11");
		n2 = w.getNodeByName("10.1.20.12");
		n3 = w.getNodeByName("10.1.20.13");
		
		assertEquals("user-enabled", n1.session);
		assertEquals("monitor-enabled", n2.session);
		assertEquals("user-enabled", n3.session);
		assertEquals("unchecked", n1.state);
		assertEquals("up", n2.state);
		assertEquals("unchecked", n3.state);
		
		w.shutdown();
	}
	
	@Test
    public void testNodesOffline() {

		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.nodeUp(new String[] {"10.1.20.11", "10.1.20.12", "10.1.20.13"});
		
		Node n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "up");
		n = w.getNodeByName("10.1.20.13");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		
		w.nodeOffline(new String[] {"10.1.20.11", "10.1.20.12", "10.1.20.13"});
		
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "user-down");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "user-down");
		n = w.getNodeByName("10.1.20.13");
		assertEquals(n.session, "user-disabled");
		assertEquals(n.state, "user-down");
		
		w.nodeUp(new String[] {"10.1.20.11", "10.1.20.12", "10.1.20.13"});
		
		n = w.getNodeByName("10.1.20.11");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		n = w.getNodeByName("10.1.20.12");
		assertEquals(n.session, "monitor-enabled");
		assertEquals(n.state, "up");
		n = w.getNodeByName("10.1.20.13");
		assertEquals(n.session, "user-enabled");
		assertEquals(n.state, "unchecked");
		
		w.shutdown();
	}
	
	@Test
	public void testNodesOfflineBash() throws InterruptedException {
		
        Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		String bash = "tmsh modify ltm node 10.1.20.11 session user-disabled; tmsh modify ltm node 10.1.20.12 session user-disabled;tmsh modify ltm node 10.1.20.13 session user-disabled;tmsh modify ltm node 10.1.20.11 state user-down; tmsh modify ltm node 10.1.20.12 state user-down;tmsh modify ltm node 10.1.20.13 state user-down";
		BashResponse resp = w.bashScripts(bash);
		assertTrue(resp.utilCmdArgs.contains(bash));
		
		Node n1 = w.getNodeByName("10.1.20.11");
		Node n2 = w.getNodeByName("10.1.20.12");
		Node n3 = w.getNodeByName("10.1.20.13");
		
		assertEquals("user-disabled", n1.session);
		assertEquals("user-disabled", n2.session);
		assertEquals("user-disabled", n3.session);
		assertEquals("user-down", n1.state);
		assertEquals("user-down", n2.state);
		assertEquals("user-down", n3.state);
		
		bash = "tmsh modify ltm node 10.1.20.11 session user-enabled; tmsh modify ltm node 10.1.20.12 session user-enabled;tmsh modify ltm node 10.1.20.13 session user-enabled;tmsh modify ltm node 10.1.20.11 state user-up; tmsh modify ltm node 10.1.20.12 state user-up;tmsh modify ltm node 10.1.20.13 state user-up";
		resp = w.bashScripts(bash);
		assertTrue(resp.utilCmdArgs.contains(bash));
		
		TimeUnit.SECONDS.sleep(3);
		
		n1 = w.getNodeByName("10.1.20.11");
		n2 = w.getNodeByName("10.1.20.12");
		n3 = w.getNodeByName("10.1.20.13");
		
		assertEquals("user-enabled", n1.session);
		assertEquals("monitor-enabled", n2.session);
		assertEquals("user-enabled", n3.session);
		assertEquals("unchecked", n1.state);
		assertEquals("up", n2.state);
		assertEquals("unchecked", n3.state);
		
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
	
	@Test
	public void testMembersDisable() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.memberEnable("pool_1", new String[] {"10.1.20.11:8081", "10.1.20.12:8081", "10.1.20.23:8081"});
		
		w.getPoolByNameExpandSubcollections("pool_1").membersReference.items.forEach(m -> {
			if(m.name.equals("10.1.20.11:8081") || m.name.equals("10.1.20.12:8081")) {
				assertEquals(m.session, "monitor-enabled");
				assertEquals(m.state, "up");
			} else if(m.name.equals("10.1.20.23:8081")) {
				assertEquals(m.session, "monitor-enabled");
				assertEquals(m.state, "down");
			}
		});
		
		w.memberDisable("pool_1", new String[] {"10.1.20.11:8081", "10.1.20.12:8081", "10.1.20.23:8081"});
		
		w.getPoolByNameExpandSubcollections("pool_1").membersReference.items.forEach(m -> {
			if(m.name.equals("10.1.20.11:8081") || m.name.equals("10.1.20.12:8081")) {
				assertEquals(m.session, "user-disabled");
				assertEquals(m.state, "up");
			} else if(m.name.equals("10.1.20.23:8081")) {
				assertEquals(m.session, "user-disabled");
				assertEquals(m.state, "down");
			}
		});
		
        w.memberEnable("pool_1", new String[] {"10.1.20.11:8081", "10.1.20.12:8081", "10.1.20.23:8081"});
		
		w.getPoolByNameExpandSubcollections("pool_1").membersReference.items.forEach(m -> {
			if(m.name.equals("10.1.20.11:8081") || m.name.equals("10.1.20.12:8081")) {
				assertEquals(m.session, "monitor-enabled");
				assertEquals(m.state, "up");
			} else if(m.name.equals("10.1.20.23:8081")) {
				assertEquals(m.session, "monitor-enabled");
				assertEquals(m.state, "down");
			}
		});
		
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
	
	@Test
	public void testMembersOffline() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
        w.memberUp("pool_1", new String[] {"10.1.20.11:8081", "10.1.20.12:8081", "10.1.20.23:8081"});
		
		w.getPoolByNameExpandSubcollections("pool_1").membersReference.items.forEach(m -> {
			if(m.name.equals("10.1.20.11:8081") || m.name.equals("10.1.20.12:8081")) {
				assertEquals(m.session, "monitor-enabled");
				assertEquals(m.state, "up");
			} else if(m.name.equals("10.1.20.23:8081")) {
				assertEquals(m.session, "monitor-enabled");
				assertEquals(m.state, "down");
			}
		});
		
		w.memberOffline("pool_1", new String[] {"10.1.20.11:8081", "10.1.20.12:8081", "10.1.20.23:8081"});
		
		w.getPoolByNameExpandSubcollections("pool_1").membersReference.items.forEach(m -> {
			assertEquals(m.session, "user-disabled");
			assertEquals(m.state, "user-down");
		});
		
		w.memberUp("pool_1", new String[] {"10.1.20.11:8081", "10.1.20.12:8081", "10.1.20.23:8081"});
		
		w.getPoolByNameExpandSubcollections("pool_1").membersReference.items.forEach(m -> {
			if(m.name.equals("10.1.20.11:8081") || m.name.equals("10.1.20.12:8081")) {
				assertEquals(m.session, "monitor-enabled");
				assertEquals(m.state, "up");
			} else if(m.name.equals("10.1.20.23:8081")) {
				assertEquals(m.session, "monitor-enabled");
				assertEquals(m.state, "down");
			}
		});
		
		w.shutdown();
	}
}

package io.github.cloudadc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.github.cloudadc.iControl.model.Member;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class TestSDKMemberDisableEnableOfflineUpDown extends TestSDK {

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

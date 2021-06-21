package io.github.cloudadc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.cloudadc.iControl.model.BashResponse;
import io.github.cloudadc.iControl.model.Node;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class TestBashScript extends TestSDK {
	
	@Test
	public void testBashScriptBatchDiableNode() {
		
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
		
		bash = "tmsh modify ltm node 10.1.20.11 session user-enabled; tmsh modify ltm node 10.1.20.12 session user-enabled;tmsh modify ltm node 10.1.20.13 session user-enabled";
		resp = w.bashScripts(bash);
		assertTrue(resp.utilCmdArgs.contains(bash));
		
		n1 = w.getNodeByName("10.1.20.11");
		n2 = w.getNodeByName("10.1.20.12");
		n3 = w.getNodeByName("10.1.20.13");
		
		assertEquals("user-enabled", n1.session);
		assertEquals("monitor-enabled", n2.session);
		assertEquals("user-enabled", n3.session);
		
		w.shutdown();
	}
	
	@Test
	public void testBashScriptBatchOfflineNode() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		String bash = "tmsh modify ltm node 10.1.20.11 state user-down; tmsh modify ltm node 10.1.20.12 state user-down;tmsh modify ltm node 10.1.20.13 state user-down";
		BashResponse resp = w.bashScripts(bash);
		assertTrue(resp.utilCmdArgs.contains(bash));
		
		Node n1 = w.getNodeByName("10.1.20.11");
		Node n2 = w.getNodeByName("10.1.20.12");
		Node n3 = w.getNodeByName("10.1.20.13");
		
		assertEquals("user-enabled", n1.session);
		assertEquals("monitor-enabled", n2.session);
		assertEquals("user-enabled", n3.session);
		assertEquals("user-down", n1.state);
		assertEquals("user-down", n2.state);
		assertEquals("user-down", n3.state);
		
		bash = "tmsh modify ltm node 10.1.20.11 state user-up; tmsh modify ltm node 10.1.20.12 state user-up;tmsh modify ltm node 10.1.20.13 state user-up";
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
		
		System.out.println(n1.session + " - " + n1.state);
		System.out.println(n2.session + " - " + n2.state);
		System.out.println(n3.session + " - " + n3.state);
	}

}

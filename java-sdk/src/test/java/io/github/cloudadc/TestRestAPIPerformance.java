package io.github.cloudadc;

import org.junit.Test;

import io.github.cloudadc.iControl.model.NodesReference;
import io.github.cloudadc.iControl.model.Transaction;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class TestRestAPIPerformance {
	
    static String HOST = "192.168.10.44";
	
	static String USER = "admin";
	
	static String PASSWORD = "F5survive@123";

	@Test
	public void testListNode() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
//		NodesReference ref = w.listAllNodes();
//		ref.items.forEach(n -> {
//			System.out.println(n.name);
//		});
		
//		Transaction t = w.transactionStart();
//		w.nodeDiable("10.1.20.1");
//		w.nodeEnable("10.1.20.1");
//		w.transactionCommit(t.transId);
		
		w.listAllMembers("pool_0");
	}
}

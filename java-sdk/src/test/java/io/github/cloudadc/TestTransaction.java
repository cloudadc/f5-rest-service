package io.github.cloudadc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import io.github.cloudadc.iControl.model.Node;
import io.github.cloudadc.iControl.model.Transaction;
import io.github.cloudadc.iControl.model.TransactionReference;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class TestTransaction extends TestSDK {

	@Test
	public void testTransactionAPI() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		Transaction t1 = w.transactionStart();
		Transaction t2 = w.transactionStart();
		Transaction t3 = w.transactionStatus(t1.transId);
		
		assertEquals(t3.transId, t1.transId);
		assertEquals(t3.state, t1.state);
		
		Set<Long> set = new HashSet<>();
		TransactionReference reference = w.listAllTransaction();
		
		assertTrue(reference.items.size() >= 2);
		
		reference.items.forEach(t -> {
			set.add(Long.valueOf(t.transId));
			w.transactionRevoke(t.transId);
		});
		
		assertTrue(set.contains(Long.valueOf(t1.transId)));
		assertTrue(set.contains(Long.valueOf(t2.transId)));
		
		reference = w.listAllTransaction();
		
		assertTrue(reference.items.size() == 0);
	}
	
	@Test
	public void testTransactionOperation() {
		
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		
		w.nodeEnable("10.1.20.13");
		w.nodeEnable("10.1.20.14");
		Node n1 = w.getNodeByName("10.1.20.13");
		Node n2 = w.getNodeByName("10.1.20.14");
		assertEquals(n1.session, "user-enabled");
		assertEquals(n2.session, "user-enabled");
		
		Transaction t = w.transactionStart();
		assertEquals(t.state, "STARTED");
		
		w.nodeDiable("10.1.20.13", t.transId);
		w.nodeDiable("10.1.20.14", t.transId);
		
		n1 = w.getNodeByName("10.1.20.13");
		n2 = w.getNodeByName("10.1.20.14");
		assertEquals(n1.session, "user-enabled");
		assertEquals(n2.session, "user-enabled");
		
		w.transactionCommit(t.transId);
		
		n1 = w.getNodeByName("10.1.20.13");
		n2 = w.getNodeByName("10.1.20.14");
		assertEquals(n1.session, "user-disabled");
		assertEquals(n2.session, "user-disabled");
		
		w.nodeEnable("10.1.20.13");
		w.nodeEnable("10.1.20.14");
		
		t = w.transactionStatus(t.transId);
		assertEquals(t.state, "COMPLETED");
		
		w.transactionRevoke(t.transId);

	}
}

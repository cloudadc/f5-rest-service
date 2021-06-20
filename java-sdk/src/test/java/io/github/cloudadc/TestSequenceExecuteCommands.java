package io.github.cloudadc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.cloudadc.iControl.model.Transaction;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class TestSequenceExecuteCommands extends TestSDK {
	
	@Test
	public void testMultipleConnectionMultipleQuery() {
		
		Wrapper[] w = new Wrapper[50];
		for (int i = 0 ; i < 50 ; i ++) {
			w[i] = Wrapper.create(HOST, USER, PASSWORD);
		}
		
		long start = System.currentTimeMillis();
        w[0].getNodeByName("10.1.20.11");
        w[1].getNodeByName("10.1.20.12");
        w[2].getNodeByName("10.1.20.13");
        w[3].getNodeByName("10.1.20.14");
        w[4].getNodeByName("10.1.20.15");
        w[5].getNodeByName("10.1.20.16");
        w[6].getNodeByName("10.1.20.17");
        w[7].getNodeByName("10.1.20.18");
        w[8].getNodeByName("10.1.20.19");
        w[9].getNodeByName("10.1.20.20");
        long t1 = System.currentTimeMillis();
        w[10].getNodeByName("10.1.20.21");
        w[11].getNodeByName("10.1.20.22");
        w[12].getNodeByName("10.1.20.23");
        w[13].getNodeByName("10.1.20.24");
        w[14].getVirtualServerByName("vs_1");
        w[15].getVirtualServerByName("vs_2");
        w[16].getVirtualServerByName("vs_3");
        w[17].getVirtualServerByName("vs_4");
        w[18].getVirtualServerByName("vs_5");
        w[19].getVirtualServerByName("vs_6");
        long t2 = System.currentTimeMillis();
        w[20].getPoolByName("pool_1");
        w[21].getMemberByName("pool_1", "10.1.20.11:8081");
        w[22].getMemberByName("pool_1", "10.1.20.12:8081");
        w[23].getMemberByName("pool_1", "10.1.20.23:8081");
        w[24].getPoolByName("pool_2");
        w[25].getMemberByName("pool_2", "10.1.20.13:8081");
        w[26].getMemberByName("pool_2", "10.1.20.14:8081");
        w[27].getMemberByName("pool_2", "10.1.20.24:8081");
        w[28].getPoolByName("pool_3");
        w[29].getMemberByName("pool_3", "10.1.20.15:8081");
        long t3 = System.currentTimeMillis();
        w[30].getMemberByName("pool_3", "10.1.20.16:8081");
        w[31].getPoolByName("pool_4");
        w[32].getMemberByName("pool_4", "10.1.20.17:8081");
        w[33].getMemberByName("pool_4", "10.1.20.18:8081");
        w[34].getPoolByName("pool_5");
        w[35].getMemberByName("pool_5", "10.1.20.19:8081");
        w[36].getMemberByName("pool_5", "10.1.20.20:8081");
        w[37].getPoolByName("pool_6");
        w[38].getMemberByName("pool_6", "10.1.20.21:8081");
        w[39].getMemberByName("pool_6", "10.1.20.22:8081");
        long t4 = System.currentTimeMillis();
        w[40].getPoolByName("pool_7");
        w[41].getMemberByName("pool_7", "10.1.20.11:8081");
        w[42].getMemberByName("pool_7", "10.1.20.12:8081");
        w[43].getMemberByName("pool_7", "10.1.20.13:8081");
        w[44].getMemberByName("pool_7", "10.1.20.14:8081");
        w[45].getMemberByName("pool_7", "10.1.20.15:8081");
        w[46].getMemberByName("pool_7", "10.1.20.16:8081");
        w[47].getMemberByName("pool_7", "10.1.20.17:8081");
        w[48].getMemberByName("pool_7", "10.1.20.18:8081");
        w[49].getMemberByName("pool_7", "10.1.20.19:8081");
        long end = System.currentTimeMillis();
        
        assertTrue("multiple connections, 10 times query", (t1 - start) < 1000 * 10);
		assertTrue("multiple connections, 10 times query", (t2 - t1) < 1000 * 10);
		assertTrue("multiple connections, 10 times query", (t3 - t2) < 1000 * 10);
		assertTrue("multiple connections, 10 times query", (t4 - t3) < 1000 * 10);
		assertTrue("multiple connections, 10 times query", (end - t4) < 1000 * 10);
        
		for (int i = 0 ; i < 50 ; i ++) {
			w[i].shutdown();
		}
	}
	
	@Test
	public void testSingleConnectionMultipleQuery() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		long start = System.currentTimeMillis();
		w.getNodeByName("10.1.20.11");
		w.getNodeByName("10.1.20.12");
		w.getNodeByName("10.1.20.13");
		w.getNodeByName("10.1.20.14");
		w.getNodeByName("10.1.20.15");
		w.getNodeByName("10.1.20.16");
		w.getNodeByName("10.1.20.17");
		w.getNodeByName("10.1.20.18");
		w.getNodeByName("10.1.20.19");
		w.getNodeByName("10.1.20.20");
		long t1 = System.currentTimeMillis();
		w.getNodeByName("10.1.20.21");
		w.getNodeByName("10.1.20.22");
		w.getNodeByName("10.1.20.23");
		w.getNodeByName("10.1.20.24");
		w.getVirtualServerByName("vs_1");
		w.getVirtualServerByName("vs_2");
		w.getVirtualServerByName("vs_3");
		w.getVirtualServerByName("vs_4");
		w.getVirtualServerByName("vs_5");
		w.getVirtualServerByName("vs_6");
		long t2 = System.currentTimeMillis();
		w.getPoolByName("pool_1");
		w.getMemberByName("pool_1", "10.1.20.11:8081");
		w.getMemberByName("pool_1", "10.1.20.12:8081");
		w.getMemberByName("pool_1", "10.1.20.23:8081");
		w.getPoolByName("pool_2");
		w.getMemberByName("pool_2", "10.1.20.13:8081");
		w.getMemberByName("pool_2", "10.1.20.14:8081");
		w.getMemberByName("pool_2", "10.1.20.24:8081");
		w.getPoolByName("pool_3");
		w.getMemberByName("pool_3", "10.1.20.15:8081");
		long t3 = System.currentTimeMillis();
		w.getMemberByName("pool_3", "10.1.20.16:8081");
		w.getPoolByName("pool_4");
		w.getMemberByName("pool_4", "10.1.20.17:8081");
		w.getMemberByName("pool_4", "10.1.20.18:8081");
		w.getPoolByName("pool_5");
		w.getMemberByName("pool_5", "10.1.20.19:8081");
		w.getMemberByName("pool_5", "10.1.20.20:8081");
		w.getPoolByName("pool_6");
		w.getMemberByName("pool_6", "10.1.20.21:8081");
		w.getMemberByName("pool_6", "10.1.20.22:8081");
		long t4 = System.currentTimeMillis();
		w.getPoolByName("pool_7");
		w.getMemberByName("pool_7", "10.1.20.11:8081");
		w.getMemberByName("pool_7", "10.1.20.12:8081");
		w.getMemberByName("pool_7", "10.1.20.13:8081");
		w.getMemberByName("pool_7", "10.1.20.14:8081");
		w.getMemberByName("pool_7", "10.1.20.15:8081");
		w.getMemberByName("pool_7", "10.1.20.16:8081");
		w.getMemberByName("pool_7", "10.1.20.17:8081");
		w.getMemberByName("pool_7", "10.1.20.18:8081");
		w.getMemberByName("pool_7", "10.1.20.19:8081");		
		long end = System.currentTimeMillis();
		assertTrue("one connection, 10 times query", (t1 - start) < 1000 * 10);
		assertTrue("one connection, 20 times query", (t2 - start) < 1000 * 10);
		assertTrue("one connection, 30 times query", (t3 - start) < 1000 * 10);
		assertTrue("one connection, 40 times query", (t4 - start) < 1000 * 10);
		assertTrue("one connection, 50 times query", (end - start) < 1000 * 10);
		w.shutdown();
	}
	
	@Test
	public void testMultipleConnectionMultipleModify( ) {
		
		Wrapper[] w = new Wrapper[80];
		for (int i = 0 ; i < 80 ; i ++) {
			w[i] = Wrapper.create(HOST, USER, PASSWORD);
		}
		
		long start = System.currentTimeMillis();
		w[0].memberDisable("pool_7", "10.1.20.11:8081");
		w[1].memberDisable("pool_7", "10.1.20.12:8081");
		w[2].memberDisable("pool_7", "10.1.20.13:8081");
		w[3].memberDisable("pool_7", "10.1.20.14:8081");
		w[4].memberDisable("pool_7", "10.1.20.15:8081");
		w[5].memberDisable("pool_7", "10.1.20.16:8081");
		w[6].memberDisable("pool_7", "10.1.20.17:8081");
		w[7].memberDisable("pool_7", "10.1.20.18:8081");
		w[8].memberDisable("pool_7", "10.1.20.19:8081");
		w[9].memberDisable("pool_7", "10.1.20.20:8081");
		long t1 = System.currentTimeMillis();
		w[10].memberDisable("pool_8", "10.1.20.11:8081");
		w[11].memberDisable("pool_8", "10.1.20.12:8081");
		w[12].memberDisable("pool_8", "10.1.20.13:8081");
		w[13].memberDisable("pool_8", "10.1.20.14:8081");
		w[14].memberDisable("pool_8", "10.1.20.15:8081");
		w[15].memberDisable("pool_8", "10.1.20.16:8081");
		w[16].memberDisable("pool_8", "10.1.20.17:8081");
		w[17].memberDisable("pool_8", "10.1.20.18:8081");
		w[18].memberDisable("pool_8", "10.1.20.19:8081");
		w[19].memberDisable("pool_8", "10.1.20.20:8081");
		long t2 = System.currentTimeMillis();
		w[20].memberEnable("pool_7", "10.1.20.11:8081");
		w[21].memberEnable("pool_7", "10.1.20.12:8081");
		w[22].memberEnable("pool_7", "10.1.20.13:8081");
		w[23].memberEnable("pool_7", "10.1.20.14:8081");
		w[24].memberEnable("pool_7", "10.1.20.15:8081");
		w[25].memberEnable("pool_7", "10.1.20.16:8081");
		w[26].memberEnable("pool_7", "10.1.20.17:8081");
		w[27].memberEnable("pool_7", "10.1.20.18:8081");
		w[28].memberEnable("pool_7", "10.1.20.19:8081");
		w[29].memberEnable("pool_7", "10.1.20.20:8081");
		long t3 = System.currentTimeMillis();
		w[30].memberEnable("pool_8", "10.1.20.11:8081");
		w[31].memberEnable("pool_8", "10.1.20.12:8081");
		w[32].memberEnable("pool_8", "10.1.20.13:8081");
		w[33].memberEnable("pool_8", "10.1.20.14:8081");
		w[34].memberEnable("pool_8", "10.1.20.15:8081");
		w[35].memberEnable("pool_8", "10.1.20.16:8081");
		w[36].memberEnable("pool_8", "10.1.20.17:8081");
		w[37].memberEnable("pool_8", "10.1.20.18:8081");
		w[38].memberEnable("pool_8", "10.1.20.19:8081");
		w[39].memberEnable("pool_8", "10.1.20.20:8081");
		long t4 = System.currentTimeMillis();
		w[40].memberOffline("pool_7", "10.1.20.11:8081");
		w[41].memberOffline("pool_7", "10.1.20.12:8081");
		w[42].memberOffline("pool_7", "10.1.20.13:8081");
		w[43].memberOffline("pool_7", "10.1.20.14:8081");
		w[44].memberOffline("pool_7", "10.1.20.15:8081");
		w[45].memberOffline("pool_7", "10.1.20.16:8081");
		w[46].memberOffline("pool_7", "10.1.20.17:8081");
		w[47].memberOffline("pool_7", "10.1.20.18:8081");
		w[48].memberOffline("pool_7", "10.1.20.19:8081");
		w[49].memberOffline("pool_7", "10.1.20.20:8081");
		long t5 = System.currentTimeMillis();
		w[50].memberOffline("pool_8", "10.1.20.11:8081");
		w[51].memberOffline("pool_8", "10.1.20.12:8081");
		w[52].memberOffline("pool_8", "10.1.20.13:8081");
		w[53].memberOffline("pool_8", "10.1.20.14:8081");
		w[54].memberOffline("pool_8", "10.1.20.15:8081");
		w[55].memberOffline("pool_8", "10.1.20.16:8081");
		w[56].memberOffline("pool_8", "10.1.20.17:8081");
		w[57].memberOffline("pool_8", "10.1.20.18:8081");
		w[58].memberOffline("pool_8", "10.1.20.19:8081");
		w[59].memberOffline("pool_8", "10.1.20.20:8081");
		long t6 = System.currentTimeMillis();
		w[60].memberUp("pool_7", "10.1.20.11:8081");
		w[61].memberUp("pool_7", "10.1.20.12:8081");
		w[62].memberUp("pool_7", "10.1.20.13:8081");
		w[63].memberUp("pool_7", "10.1.20.14:8081");
		w[64].memberUp("pool_7", "10.1.20.15:8081");
		w[65].memberUp("pool_7", "10.1.20.16:8081");
		w[66].memberUp("pool_7", "10.1.20.17:8081");
		w[67].memberUp("pool_7", "10.1.20.18:8081");
		w[68].memberUp("pool_7", "10.1.20.19:8081");
		w[69].memberUp("pool_7", "10.1.20.20:8081");
		long t7 = System.currentTimeMillis();
		w[70].memberUp("pool_8", "10.1.20.11:8081");
		w[71].memberUp("pool_8", "10.1.20.12:8081");
		w[72].memberUp("pool_8", "10.1.20.13:8081");
		w[73].memberUp("pool_8", "10.1.20.14:8081");
		w[74].memberUp("pool_8", "10.1.20.15:8081");
		w[75].memberUp("pool_8", "10.1.20.16:8081");
		w[76].memberUp("pool_8", "10.1.20.17:8081");
		w[77].memberUp("pool_8", "10.1.20.18:8081");
		w[78].memberUp("pool_8", "10.1.20.19:8081");
		w[79].memberUp("pool_8", "10.1.20.20:8081");
		long end = System.currentTimeMillis();
		
		assertTrue("multiple connections, 10 times update", (t1 - start) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t2 - start) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t3 - start) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t4 - start) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t5 - start) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t6 - start) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t7 - start) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (end - start) < 1000 * 10);
		
		for (int i = 0 ; i < 80 ; i ++) {
			w[i].shutdown();
		}
	}
	
	@Test
	public void testMultipleConnectionMultipleModifyWithTransaction() {
		
		Wrapper w0 = Wrapper.create(HOST, USER, PASSWORD);
		Transaction t = w0.transactionStart();
		
		Wrapper[] w = new Wrapper[80];
		for (int i = 0 ; i < 80 ; i ++) {
			w[i] = Wrapper.create(HOST, USER, PASSWORD);
		}
		
		long start = System.currentTimeMillis();
		w[0].memberDisable("pool_7", "10.1.20.11:8081", t.transId);
		w[1].memberDisable("pool_7", "10.1.20.12:8081", t.transId);
		w[2].memberDisable("pool_7", "10.1.20.13:8081", t.transId);
		w[3].memberDisable("pool_7", "10.1.20.14:8081", t.transId);
		w[4].memberDisable("pool_7", "10.1.20.15:8081", t.transId);
		w[5].memberDisable("pool_7", "10.1.20.16:8081", t.transId);
		w[6].memberDisable("pool_7", "10.1.20.17:8081", t.transId);
		w[7].memberDisable("pool_7", "10.1.20.18:8081", t.transId);
		w[8].memberDisable("pool_7", "10.1.20.19:8081", t.transId);
		w[9].memberDisable("pool_7", "10.1.20.20:8081", t.transId);
		long t1 = System.currentTimeMillis();
		w[10].memberDisable("pool_8", "10.1.20.11:8081", t.transId);
		w[11].memberDisable("pool_8", "10.1.20.12:8081", t.transId);
		w[12].memberDisable("pool_8", "10.1.20.13:8081", t.transId);
		w[13].memberDisable("pool_8", "10.1.20.14:8081", t.transId);
		w[14].memberDisable("pool_8", "10.1.20.15:8081", t.transId);
		w[15].memberDisable("pool_8", "10.1.20.16:8081", t.transId);
		w[16].memberDisable("pool_8", "10.1.20.17:8081", t.transId);
		w[17].memberDisable("pool_8", "10.1.20.18:8081", t.transId);
		w[18].memberDisable("pool_8", "10.1.20.19:8081", t.transId);
		w[19].memberDisable("pool_8", "10.1.20.20:8081", t.transId);
		long t2 = System.currentTimeMillis();
		w[20].memberEnable("pool_7", "10.1.20.11:8081", t.transId);
		w[21].memberEnable("pool_7", "10.1.20.12:8081", t.transId);
		w[22].memberEnable("pool_7", "10.1.20.13:8081", t.transId);
		w[23].memberEnable("pool_7", "10.1.20.14:8081", t.transId);
		w[24].memberEnable("pool_7", "10.1.20.15:8081", t.transId);
		w[25].memberEnable("pool_7", "10.1.20.16:8081", t.transId);
		w[26].memberEnable("pool_7", "10.1.20.17:8081", t.transId);
		w[27].memberEnable("pool_7", "10.1.20.18:8081", t.transId);
		w[28].memberEnable("pool_7", "10.1.20.19:8081", t.transId);
		w[29].memberEnable("pool_7", "10.1.20.20:8081", t.transId);
		long t3 = System.currentTimeMillis();
		w[30].memberEnable("pool_8", "10.1.20.11:8081", t.transId);
		w[31].memberEnable("pool_8", "10.1.20.12:8081", t.transId);
		w[32].memberEnable("pool_8", "10.1.20.13:8081", t.transId);
		w[33].memberEnable("pool_8", "10.1.20.14:8081", t.transId);
		w[34].memberEnable("pool_8", "10.1.20.15:8081", t.transId);
		w[35].memberEnable("pool_8", "10.1.20.16:8081", t.transId);
		w[36].memberEnable("pool_8", "10.1.20.17:8081", t.transId);
		w[37].memberEnable("pool_8", "10.1.20.18:8081", t.transId);
		w[38].memberEnable("pool_8", "10.1.20.19:8081", t.transId);
		w[39].memberEnable("pool_8", "10.1.20.20:8081", t.transId);
		long t4 = System.currentTimeMillis();
		w[40].memberOffline("pool_7", "10.1.20.11:8081", t.transId);
		w[41].memberOffline("pool_7", "10.1.20.12:8081", t.transId);
		w[42].memberOffline("pool_7", "10.1.20.13:8081", t.transId);
		w[43].memberOffline("pool_7", "10.1.20.14:8081", t.transId);
		w[44].memberOffline("pool_7", "10.1.20.15:8081", t.transId);
		w[45].memberOffline("pool_7", "10.1.20.16:8081", t.transId);
		w[46].memberOffline("pool_7", "10.1.20.17:8081", t.transId);
		w[47].memberOffline("pool_7", "10.1.20.18:8081", t.transId);
		w[48].memberOffline("pool_7", "10.1.20.19:8081", t.transId);
		w[49].memberOffline("pool_7", "10.1.20.20:8081", t.transId);
		long t5 = System.currentTimeMillis();
		w[50].memberOffline("pool_8", "10.1.20.11:8081", t.transId);
		w[51].memberOffline("pool_8", "10.1.20.12:8081", t.transId);
		w[52].memberOffline("pool_8", "10.1.20.13:8081", t.transId);
		w[53].memberOffline("pool_8", "10.1.20.14:8081", t.transId);
		w[54].memberOffline("pool_8", "10.1.20.15:8081", t.transId);
		w[55].memberOffline("pool_8", "10.1.20.16:8081", t.transId);
		w[56].memberOffline("pool_8", "10.1.20.17:8081", t.transId);
		w[57].memberOffline("pool_8", "10.1.20.18:8081", t.transId);
		w[58].memberOffline("pool_8", "10.1.20.19:8081", t.transId);
		w[59].memberOffline("pool_8", "10.1.20.20:8081", t.transId);
		long t6 = System.currentTimeMillis();
		w[60].memberUp("pool_7", "10.1.20.11:8081", t.transId);
		w[61].memberUp("pool_7", "10.1.20.12:8081", t.transId);
		w[62].memberUp("pool_7", "10.1.20.13:8081", t.transId);
		w[63].memberUp("pool_7", "10.1.20.14:8081", t.transId);
		w[64].memberUp("pool_7", "10.1.20.15:8081", t.transId);
		w[65].memberUp("pool_7", "10.1.20.16:8081", t.transId);
		w[66].memberUp("pool_7", "10.1.20.17:8081", t.transId);
		w[67].memberUp("pool_7", "10.1.20.18:8081", t.transId);
		w[68].memberUp("pool_7", "10.1.20.19:8081", t.transId);
		w[69].memberUp("pool_7", "10.1.20.20:8081", t.transId);
		long t7 = System.currentTimeMillis();
		w[70].memberUp("pool_8", "10.1.20.11:8081", t.transId);
		w[71].memberUp("pool_8", "10.1.20.12:8081", t.transId);
		w[72].memberUp("pool_8", "10.1.20.13:8081", t.transId);
		w[73].memberUp("pool_8", "10.1.20.14:8081", t.transId);
		w[74].memberUp("pool_8", "10.1.20.15:8081", t.transId);
		w[75].memberUp("pool_8", "10.1.20.16:8081", t.transId);
		w[76].memberUp("pool_8", "10.1.20.17:8081", t.transId);
		w[77].memberUp("pool_8", "10.1.20.18:8081", t.transId);
		w[78].memberUp("pool_8", "10.1.20.19:8081", t.transId);
		w[79].memberUp("pool_8", "10.1.20.20:8081", t.transId);
		long end = System.currentTimeMillis();
		
		w0.transactionCommit(t.transId);
		
		assertTrue("multiple connections, 10 times update", (t1 - start) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t2 - t1) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t3 - t2) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t4 - t3) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t5 - t4) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t6 - t5) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (t7 - t6) < 1000 * 10);
		assertTrue("multiple connections, 10 times update", (end - t7) < 1000 * 10);
                
        for (int i = 0 ; i < 80 ; i ++) {
			w[i].shutdown();
		}
        
        w0.shutdown();
	}
	
	@Test
	public void testSingleConnectionMultipleModify( ) {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		long start = System.currentTimeMillis();
		w.memberDisable("pool_7", "10.1.20.11:8081");
		w.memberDisable("pool_7", "10.1.20.12:8081");
		w.memberDisable("pool_7", "10.1.20.13:8081");
		w.memberDisable("pool_7", "10.1.20.14:8081");
		w.memberDisable("pool_7", "10.1.20.15:8081");
		w.memberDisable("pool_7", "10.1.20.16:8081");
		w.memberDisable("pool_7", "10.1.20.17:8081");
		w.memberDisable("pool_7", "10.1.20.18:8081");
		w.memberDisable("pool_7", "10.1.20.19:8081");
		w.memberDisable("pool_7", "10.1.20.20:8081");
		long t1 = System.currentTimeMillis();
		w.memberDisable("pool_8", "10.1.20.11:8081");
		w.memberDisable("pool_8", "10.1.20.12:8081");
		w.memberDisable("pool_8", "10.1.20.13:8081");
		w.memberDisable("pool_8", "10.1.20.14:8081");
		w.memberDisable("pool_8", "10.1.20.15:8081");
		w.memberDisable("pool_8", "10.1.20.16:8081");
		w.memberDisable("pool_8", "10.1.20.17:8081");
		w.memberDisable("pool_8", "10.1.20.18:8081");
		w.memberDisable("pool_8", "10.1.20.19:8081");
		w.memberDisable("pool_8", "10.1.20.20:8081");
		long t2 = System.currentTimeMillis();
		w.memberEnable("pool_7", "10.1.20.11:8081");
		w.memberEnable("pool_7", "10.1.20.12:8081");
		w.memberEnable("pool_7", "10.1.20.13:8081");
		w.memberEnable("pool_7", "10.1.20.14:8081");
		w.memberEnable("pool_7", "10.1.20.15:8081");
		w.memberEnable("pool_7", "10.1.20.16:8081");
		w.memberEnable("pool_7", "10.1.20.17:8081");
		w.memberEnable("pool_7", "10.1.20.18:8081");
		w.memberEnable("pool_7", "10.1.20.19:8081");
		w.memberEnable("pool_7", "10.1.20.20:8081");
		long t3 = System.currentTimeMillis();
		w.memberEnable("pool_8", "10.1.20.11:8081");
		w.memberEnable("pool_8", "10.1.20.12:8081");
		w.memberEnable("pool_8", "10.1.20.13:8081");
		w.memberEnable("pool_8", "10.1.20.14:8081");
		w.memberEnable("pool_8", "10.1.20.15:8081");
		w.memberEnable("pool_8", "10.1.20.16:8081");
		w.memberEnable("pool_8", "10.1.20.17:8081");
		w.memberEnable("pool_8", "10.1.20.18:8081");
		w.memberEnable("pool_8", "10.1.20.19:8081");
		w.memberEnable("pool_8", "10.1.20.20:8081");
		long t4 = System.currentTimeMillis();
		w.memberOffline("pool_7", "10.1.20.11:8081");
		w.memberOffline("pool_7", "10.1.20.12:8081");
		w.memberOffline("pool_7", "10.1.20.13:8081");
		w.memberOffline("pool_7", "10.1.20.14:8081");
		w.memberOffline("pool_7", "10.1.20.15:8081");
		w.memberOffline("pool_7", "10.1.20.16:8081");
		w.memberOffline("pool_7", "10.1.20.17:8081");
		w.memberOffline("pool_7", "10.1.20.18:8081");
		w.memberOffline("pool_7", "10.1.20.19:8081");
		w.memberOffline("pool_7", "10.1.20.20:8081");
		long t5 = System.currentTimeMillis();
		w.memberOffline("pool_8", "10.1.20.11:8081");
		w.memberOffline("pool_8", "10.1.20.12:8081");
		w.memberOffline("pool_8", "10.1.20.13:8081");
		w.memberOffline("pool_8", "10.1.20.14:8081");
		w.memberOffline("pool_8", "10.1.20.15:8081");
		w.memberOffline("pool_8", "10.1.20.16:8081");
		w.memberOffline("pool_8", "10.1.20.17:8081");
		w.memberOffline("pool_8", "10.1.20.18:8081");
		w.memberOffline("pool_8", "10.1.20.19:8081");
		w.memberOffline("pool_8", "10.1.20.20:8081");
		long t6 = System.currentTimeMillis();
		w.memberUp("pool_7", "10.1.20.11:8081");
		w.memberUp("pool_7", "10.1.20.12:8081");
		w.memberUp("pool_7", "10.1.20.13:8081");
		w.memberUp("pool_7", "10.1.20.14:8081");
		w.memberUp("pool_7", "10.1.20.15:8081");
		w.memberUp("pool_7", "10.1.20.16:8081");
		w.memberUp("pool_7", "10.1.20.17:8081");
		w.memberUp("pool_7", "10.1.20.18:8081");
		w.memberUp("pool_7", "10.1.20.19:8081");
		w.memberUp("pool_7", "10.1.20.20:8081");
		long t7 = System.currentTimeMillis();
		w.memberUp("pool_8", "10.1.20.11:8081");
		w.memberUp("pool_8", "10.1.20.12:8081");
		w.memberUp("pool_8", "10.1.20.13:8081");
		w.memberUp("pool_8", "10.1.20.14:8081");
		w.memberUp("pool_8", "10.1.20.15:8081");
		w.memberUp("pool_8", "10.1.20.16:8081");
		w.memberUp("pool_8", "10.1.20.17:8081");
		w.memberUp("pool_8", "10.1.20.18:8081");
		w.memberUp("pool_8", "10.1.20.19:8081");
		w.memberUp("pool_8", "10.1.20.20:8081");
		long end = System.currentTimeMillis();
		assertTrue("one connection, 10 times update", (t1 - start) < 1000 * 10);
		assertTrue("one connection, 20 times update", (t2 - start) < 1000 * 10);
		assertTrue("one connection, 30 times update", (t3 - start) < 1000 * 10);
		assertTrue("one connection, 40 times update", (t4 - start) < 1000 * 10);
		assertTrue("one connection, 50 times update", (t5 - start) < 1000 * 10);
		assertTrue("one connection, 60 times update", (t6 - start) < 1000 * 10);
		assertTrue("one connection, 70 times update", (t7 - start) < 1000 * 10);
		assertTrue("one connection, 80 times update", (end - start) < 1000 * 10);
		w.shutdown();
	}
	
	@Test
	public void testSingleConnectionMultipleModifyWithTransaction( ) {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		Transaction t = w.transactionStart();
		long start = System.currentTimeMillis();
		w.memberDisable("pool_7", "10.1.20.11:8081", t.transId);
		w.memberDisable("pool_7", "10.1.20.12:8081", t.transId);
		w.memberDisable("pool_7", "10.1.20.13:8081", t.transId);
		w.memberDisable("pool_7", "10.1.20.14:8081", t.transId);
		w.memberDisable("pool_7", "10.1.20.15:8081", t.transId);
		w.memberDisable("pool_7", "10.1.20.16:8081", t.transId);
		w.memberDisable("pool_7", "10.1.20.17:8081", t.transId);
		w.memberDisable("pool_7", "10.1.20.18:8081", t.transId);
		w.memberDisable("pool_7", "10.1.20.19:8081", t.transId);
		w.memberDisable("pool_7", "10.1.20.20:8081", t.transId);
		long t1 = System.currentTimeMillis();
		w.memberDisable("pool_8", "10.1.20.11:8081", t.transId);
		w.memberDisable("pool_8", "10.1.20.12:8081", t.transId);
		w.memberDisable("pool_8", "10.1.20.13:8081", t.transId);
		w.memberDisable("pool_8", "10.1.20.14:8081", t.transId);
		w.memberDisable("pool_8", "10.1.20.15:8081", t.transId);
		w.memberDisable("pool_8", "10.1.20.16:8081", t.transId);
		w.memberDisable("pool_8", "10.1.20.17:8081", t.transId);
		w.memberDisable("pool_8", "10.1.20.18:8081", t.transId);
		w.memberDisable("pool_8", "10.1.20.19:8081", t.transId);
		w.memberDisable("pool_8", "10.1.20.20:8081", t.transId);
		long t2 = System.currentTimeMillis();
		w.memberEnable("pool_7", "10.1.20.11:8081", t.transId);
		w.memberEnable("pool_7", "10.1.20.12:8081", t.transId);
		w.memberEnable("pool_7", "10.1.20.13:8081", t.transId);
		w.memberEnable("pool_7", "10.1.20.14:8081", t.transId);
		w.memberEnable("pool_7", "10.1.20.15:8081", t.transId);
		w.memberEnable("pool_7", "10.1.20.16:8081", t.transId);
		w.memberEnable("pool_7", "10.1.20.17:8081", t.transId);
		w.memberEnable("pool_7", "10.1.20.18:8081", t.transId);
		w.memberEnable("pool_7", "10.1.20.19:8081", t.transId);
		w.memberEnable("pool_7", "10.1.20.20:8081", t.transId);
		long t3 = System.currentTimeMillis();
		w.memberEnable("pool_8", "10.1.20.11:8081", t.transId);
		w.memberEnable("pool_8", "10.1.20.12:8081", t.transId);
		w.memberEnable("pool_8", "10.1.20.13:8081", t.transId);
		w.memberEnable("pool_8", "10.1.20.14:8081", t.transId);
		w.memberEnable("pool_8", "10.1.20.15:8081", t.transId);
		w.memberEnable("pool_8", "10.1.20.16:8081", t.transId);
		w.memberEnable("pool_8", "10.1.20.17:8081", t.transId);
		w.memberEnable("pool_8", "10.1.20.18:8081", t.transId);
		w.memberEnable("pool_8", "10.1.20.19:8081", t.transId);
		w.memberEnable("pool_8", "10.1.20.20:8081", t.transId);
		long t4 = System.currentTimeMillis();
		w.memberOffline("pool_7", "10.1.20.11:8081", t.transId);
		w.memberOffline("pool_7", "10.1.20.12:8081", t.transId);
		w.memberOffline("pool_7", "10.1.20.13:8081", t.transId);
		w.memberOffline("pool_7", "10.1.20.14:8081", t.transId);
		w.memberOffline("pool_7", "10.1.20.15:8081", t.transId);
		w.memberOffline("pool_7", "10.1.20.16:8081", t.transId);
		w.memberOffline("pool_7", "10.1.20.17:8081", t.transId);
		w.memberOffline("pool_7", "10.1.20.18:8081", t.transId);
		w.memberOffline("pool_7", "10.1.20.19:8081", t.transId);
		w.memberOffline("pool_7", "10.1.20.20:8081", t.transId);
		long t5 = System.currentTimeMillis();
		w.memberOffline("pool_8", "10.1.20.11:8081", t.transId);
		w.memberOffline("pool_8", "10.1.20.12:8081", t.transId);
		w.memberOffline("pool_8", "10.1.20.13:8081", t.transId);
		w.memberOffline("pool_8", "10.1.20.14:8081", t.transId);
		w.memberOffline("pool_8", "10.1.20.15:8081", t.transId);
		w.memberOffline("pool_8", "10.1.20.16:8081", t.transId);
		w.memberOffline("pool_8", "10.1.20.17:8081", t.transId);
		w.memberOffline("pool_8", "10.1.20.18:8081", t.transId);
		w.memberOffline("pool_8", "10.1.20.19:8081", t.transId);
		w.memberOffline("pool_8", "10.1.20.20:8081", t.transId);
		long t6 = System.currentTimeMillis();
		w.memberUp("pool_7", "10.1.20.11:8081", t.transId);
		w.memberUp("pool_7", "10.1.20.12:8081", t.transId);
		w.memberUp("pool_7", "10.1.20.13:8081", t.transId);
		w.memberUp("pool_7", "10.1.20.14:8081", t.transId);
		w.memberUp("pool_7", "10.1.20.15:8081", t.transId);
		w.memberUp("pool_7", "10.1.20.16:8081", t.transId);
		w.memberUp("pool_7", "10.1.20.17:8081", t.transId);
		w.memberUp("pool_7", "10.1.20.18:8081", t.transId);
		w.memberUp("pool_7", "10.1.20.19:8081", t.transId);
		w.memberUp("pool_7", "10.1.20.20:8081", t.transId);
		long t7 = System.currentTimeMillis();
		w.memberUp("pool_8", "10.1.20.11:8081", t.transId);
		w.memberUp("pool_8", "10.1.20.12:8081", t.transId);
		w.memberUp("pool_8", "10.1.20.13:8081", t.transId);
		w.memberUp("pool_8", "10.1.20.14:8081", t.transId);
		w.memberUp("pool_8", "10.1.20.15:8081", t.transId);
		w.memberUp("pool_8", "10.1.20.16:8081", t.transId);
		w.memberUp("pool_8", "10.1.20.17:8081", t.transId);
		w.memberUp("pool_8", "10.1.20.18:8081", t.transId);
		w.memberUp("pool_8", "10.1.20.19:8081", t.transId);
		w.memberUp("pool_8", "10.1.20.20:8081", t.transId);
		long end = System.currentTimeMillis();
		w.transactionCommit(t.transId);
		assertTrue("one connection, 10 times update", (t1 - start) < 1000 * 10);
		assertTrue("one connection, 20 times update", (t2 - start) < 1000 * 10);
		assertTrue("one connection, 30 times update", (t3 - start) < 1000 * 10);
		assertTrue("one connection, 40 times update", (t4 - start) < 1000 * 10);
		assertTrue("one connection, 50 times update", (t5 - start) < 1000 * 10);
		assertTrue("one connection, 60 times update", (t6 - start) < 1000 * 10);
		assertTrue("one connection, 70 times update", (t7 - start) < 1000 * 10);
		assertTrue("one connection, 80 times update", (end - start) < 1000 * 10);
		w.shutdown();
	}
	
	@Test
	public void test() {
		System.out.println();
	}

}

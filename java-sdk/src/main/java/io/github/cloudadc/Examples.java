package io.github.cloudadc;

import io.github.cloudadc.iControl.model.*;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class Examples {
	
	static String HOST = "10.1.1.133";
	
	static String USER = "admin";
	
	static String PASSWORD = "admin";
	
	static void listAllVirtualServers() {
		VirtualServersReference reference = Wrapper.create(HOST, USER, PASSWORD).listAllVirtualServers();
		assert reference.items.size() == 6;
	}
	
	static void listAllVirtualServersExpandSubcollections () {
		VirtualServersReference reference =  Wrapper.create(HOST, USER, PASSWORD).listAllVirtualServersExpandSubcollections();
		assert reference.items.size() == 6;
	}
	
	static void getVirtualServerByName () {
		VirtualServer vs = Wrapper.create(HOST, USER, PASSWORD).getVirtualServerByName("vs_1");
		assert vs.name.equals("vs_1");
		assert vs.destination.equals("10.1.10.11");
		assert vs.ipProtocol.equals("tcp");
	}
	
	static void getVirtualServerByNameExpandSubcollections() {
		VirtualServer vs = Wrapper.create(HOST, USER, PASSWORD).getVirtualServerByNameExpandSubcollections("vs_2");
		assert vs.name.equals("vs_2");
		assert vs.destination.equals("10.1.10.13");
		assert vs.ipProtocol.equals("tcp");
		assert vs.sourceAddressTranslation.type.equals("automap");
	}
	
	static void listAllNodes() {
		NodesReference reference = Wrapper.create(HOST, USER, PASSWORD).listAllNodes();
		assert reference.items.size() == 12;
	}
	
	static void getNodeByName () {
		Node n = Wrapper.create(HOST, USER, PASSWORD).getNodeByName("10.1.20.11");
		assert n.address.equals("10.1.20.11");
	}
	
	static void listAllPools() {
		PoolsReference reference = Wrapper.create(HOST, USER, PASSWORD).listAllPools();
		assert reference.items.size() == 6;
	}
	
	static void listAllPoolsExpandSubcollections() {
		PoolsReference reference = Wrapper.create(HOST, USER, PASSWORD).listAllPoolsExpandSubcollections();
		assert reference.items.size() == 6;
	}
	
	static void getPoolByName() {
		Pool p = Wrapper.create(HOST, USER, PASSWORD).getPoolByName("pool_1");
		System.out.println(p.name + ", " + p.monitor + ", " + p.loadBalancingMode);
	}
	
	static void getPoolByNameExpandSubcollections() {
		Pool p = Wrapper.create(HOST, USER, PASSWORD).getPoolByNameExpandSubcollections("pool_1");
		assert p.loadBalancingMode.equals("round-robin");
	}
	
	static void listAllMembers() {
		MembersReference reference = Wrapper.create(HOST, USER, PASSWORD).listAllMembers("pool_1");
		assert reference.items.size() == 2;
	}
	
	static void getMemberByName() {
		Member m = Wrapper.create(HOST, USER, PASSWORD).getMemberByName("pool_1", "10.1.20.11:8081");
		assert m != null;
	}
	
	static void nodeEnableFromDiable() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		Node n = w.nodeDiable("10.1.20.11");
		assert n.session.equals("user-enabled");
		assert n.state.equals("user-down");
		n = w.nodeEnable("10.1.20.11");
		assert n.session.equals("user-enabled");
		assert n.state.equals("unchecked");
	}
	
	static void nodeUpFromOffline() {
		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);
		Node n = w.nodeOffline("10.1.20.11");
		
		System.out.println(n.session + " " + n.state);
	}

}

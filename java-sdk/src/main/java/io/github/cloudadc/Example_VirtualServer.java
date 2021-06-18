package io.github.cloudadc;

import io.github.cloudadc.iControl.model.VirtualServer;
import io.github.cloudadc.iControl.wapper.Wrapper;

public class Example_VirtualServer {
	
	static String HOST = "10.1.1.133";
	
	static String USER = "admin";
	
	static String PASSWORD = "admin";
	
	static void listAllVirtualServers() {
		Wrapper.create(HOST, USER, PASSWORD)
		.listAllVirtualServers()
		.items
		.forEach(vs -> System.out.println(vs.name + ", " + vs.destination + ", " + vs.enabled + ", " + vs.ipProtocol));
	}
	
	static void listAllVirtualServersExpandSubcollections () {
		Wrapper.create(HOST, USER, PASSWORD)
		.listAllVirtualServersExpandSubcollections()
		.items
		.forEach(vs -> System.out.println(vs.name + ", " + vs.destination + ", " + vs.enabled + ", " + vs.ipProtocol + ", " + vs.sourceAddressTranslation.type));
	}
	
	static void getVirtualServerByName () {
		VirtualServer vs = Wrapper.create(HOST, USER, PASSWORD).getVirtualServerByName("vs_1");
		System.out.println(vs.name + ", " + vs.destination + ", " + vs.enabled + ", " + vs.ipProtocol);
	}
	
	static void getVirtualServerByNameExpandSubcollections() {
		VirtualServer vs = Wrapper.create(HOST, USER, PASSWORD).getVirtualServerByNameExpandSubcollections("vs_1");
		System.out.println(vs.name + ", " + vs.destination + ", " + vs.enabled + ", " + vs.ipProtocol + ", " + vs.sourceAddressTranslation.type);
	}

}

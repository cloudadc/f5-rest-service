package io.github.cloudadc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class APP implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(APP.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Examples.listAllVirtualServers();
//		Examples.listAllVirtualServersExpandSubcollections();
//		Examples.getVirtualServerByName();
//		Examples.getVirtualServerByNameExpandSubcollections();
//		Examples.listAllNodes();
//		Examples.getNodeByName();		
//		Examples.listAllPools();
//		Examples.listAllPoolsExpandSubcollections();
//		Examples.getPoolByName();
//		Examples.getPoolByNameExpandSubcollections();
//		Examples.listAllMembers();
//		Examples.getMemberByName();
//		Examples.nodeEnableFromDiable();
		Examples.nodeUpFromOffline();
		
	}
	
	
	

	
}

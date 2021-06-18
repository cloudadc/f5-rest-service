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
		
		Example_VirtualServer.listAllVirtualServers();
		Example_VirtualServer.listAllVirtualServersExpandSubcollections();
		Example_VirtualServer.getVirtualServerByName();
		Example_VirtualServer.getVirtualServerByNameExpandSubcollections();
		
	}
	
	
	

	
}

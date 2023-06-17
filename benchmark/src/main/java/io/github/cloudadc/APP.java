package io.github.cloudadc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cloudadc.iControl.model.MembersReference;
import io.github.cloudadc.iControl.wapper.Wrapper;

@SpringBootApplication
public class APP implements CommandLineRunner {

	static String HOST = "192.168.10.44";

	static String USER = "admin";

	static String PASSWORD = "F5survive@123";

	public static void main(String[] args) {
		SpringApplication.run(APP.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Wrapper w = Wrapper.create(HOST, USER, PASSWORD);

		long disable_node_start = System.currentTimeMillis();
		for(int i = 1 ; i <= 117 ; i++) {
			String node = "10.1.20." + i;
			w.nodeDiable(node);
		}
		long disable_node_end = System.currentTimeMillis();

		System.out.println("disable 117 nodes spend " + (disable_node_end - disable_node_start) + " milliseconds");

		List<MembersReference> list_disable = new ArrayList<>();
		long extract_member_start = System.currentTimeMillis();
		for (int i = 0 ; i < 50; i ++) {
			String pool = "pool_" + 20 * i;
			MembersReference ref = w.listAllMembers(pool);
			list_disable.add(ref);
		}
		long extract_member_end = System.currentTimeMillis();
		
		System.out.println("extract 1050 members status spend " + (extract_member_end - extract_member_start) + " milliseconds");
		System.out.println("disable 117 nodes and extract 1050 member status (40% time on Client side IO) spend " + (extract_member_end - disable_node_start) + " milliseconds");
		
		long enable_node_start = System.currentTimeMillis();
		for(int i = 1 ; i <= 117 ; i++) {
			String node = "10.1.20." + i;
			w.nodeEnable(node);
		}
		long enable_node_end = System.currentTimeMillis();

		System.out.println("enable 117 nodes spend " + (enable_node_end - enable_node_start) + " milliseconds");

		List<MembersReference> list_enable = new ArrayList<>();
		long extract_member_enable_start = System.currentTimeMillis();
		for (int i = 0 ; i < 50; i ++) {
			String pool = "pool_" + 20 * i;
			MembersReference ref = w.listAllMembers(pool);
			list_enable.add(ref);
		}
		long extract_member_enable_end = System.currentTimeMillis();
		
		System.out.println("extract 1050 members status spend " + (extract_member_enable_end - extract_member_enable_start) + " milliseconds");
		System.out.println("enable 117 nodes and extract 1050 member status(40% time on Client side IO) spend " + (extract_member_enable_end - enable_node_start) + " milliseconds");

		
	}





}

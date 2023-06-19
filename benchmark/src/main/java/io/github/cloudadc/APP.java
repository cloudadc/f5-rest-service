package io.github.cloudadc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cloudadc.iControl.config.Config;
import io.github.cloudadc.iControl.config.ConfigBuilder;
import io.github.cloudadc.iControl.model.MembersReference;
import io.github.cloudadc.iControl.model.Transaction;
import io.github.cloudadc.iControl.model.TransactionCommandsReference;
import io.github.cloudadc.iControl.wapper.Wrapper;

@SpringBootApplication
public class APP implements CommandLineRunner {


	public static void main(String[] args) {
		//args = new String[] {"debug"};
		SpringApplication.run(APP.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Config config = ConfigBuilder.instance().load(args).build();

		Wrapper w = Wrapper.create(config.getHost(), config.getUser(), config.getPassword());
		
		if(args.length > 0 && args[0].equals("debug") ) {
			transaction_debug(w);
		} else {
                        Thread.sleep(5000);
			operation_without_transaction(w);
			Thread.sleep(5000);
			operation_with_transaction(w);
		}

		
	}

	private void transaction_debug(Wrapper w) {

		Transaction t = w.transactionStart();
		
		System.out.println(t);
		
		w.nodeDiable("10.1.20.1", t.transId);
		w.nodeDiable("10.1.20.2", t.transId);
		
		System.out.println(w.transactionStatus(t.transId));
		System.out.println(w.getNodeByName("10.1.20.1"));
		System.out.println(w.getNodeByName("10.1.20.2"));
		
		TransactionCommandsReference r = w.transactionCommands(t.transId);
		System.out.println(r);
		
		t = w.transactionCommit(t.transId);
		
		System.out.println(t);
		
		System.out.println(w.getNodeByName("10.1.20.1"));
		System.out.println(w.getNodeByName("10.1.20.2"));
		
	}

	void operation_without_transaction(Wrapper w) {
		
		System.out.println("operation_without_transaction() start");

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
		System.out.println("disable 117 nodes and extract 1050 member status (30% time on Client side IO) spend " + (extract_member_end - disable_node_start) + " milliseconds");
		
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
		System.out.println("enable 117 nodes and extract 1050 member status(30% time on Client side IO) spend " + (extract_member_enable_end - enable_node_start) + " milliseconds");
	
		System.out.println("operation_without_transaction() end");
	}

	void operation_with_transaction(Wrapper w) {
		
		System.out.println("operation_with_transaction() start");

		Transaction t = w.transactionStart();
		long disable_node_start = System.currentTimeMillis();
		for(int i = 1 ; i <= 117 ; i++) {
			String node = "10.1.20." + i;
			w.nodeDiable(node, t.transId);
		}
		w.transactionCommit(t.transId);
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
		System.out.println("disable 117 nodes and extract 1050 member status (30% time on Client side IO) spend " + (extract_member_end - disable_node_start) + " milliseconds");
		
		t = w.transactionStart();
		long enable_node_start = System.currentTimeMillis();
		for(int i = 1 ; i <= 117 ; i++) {
			String node = "10.1.20." + i;
			w.nodeEnable(node, t.transId);
		}
		w.transactionCommit(t.transId);
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
		System.out.println("enable 117 nodes and extract 1050 member status(30% time on Client side IO) spend " + (extract_member_enable_end - enable_node_start) + " milliseconds");
	
		System.out.println("operation_with_transaction() end");
	}




}

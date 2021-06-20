package io.github.cloudadc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cloudadc.iControl.model.Transaction;
import io.github.cloudadc.iControl.wapper.Wrapper;

@SpringBootApplication
public class APP implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(APP.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Wrapper w0 = Wrapper.create("10.1.1.133", "admin", "admin");
		Transaction t = w0.transactionStart();
		
		Wrapper[] w = new Wrapper[80];
		for (int i = 0 ; i < 80 ; i ++) {
			w[i] = Wrapper.create("10.1.1.133", "admin", "admin");
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
                
        for (int i = 0 ; i < 80 ; i ++) {
			w[i].shutdown();
		}
        
        System.out.println((t1 - start) + " " + (t2 - start)  + " " + (t3 - start)  + " " + (t4 - start)  + " " + (t5 - start)  + " " + (t6 - start)  + " " + (t7 - start)  + " " + (end - start) );
        
		
	}
	
	
	

	
}

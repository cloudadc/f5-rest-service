package io.github.cloudadc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cloudadc.iControl.wapper.Wrapper;

@SpringBootApplication
public class APP implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(APP.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Wrapper[] w = new Wrapper[80];
		for (int i = 0 ; i < 80 ; i ++) {
			w[i] = Wrapper.create("10.1.1.133", "admin", "admin");
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
                
        for (int i = 0 ; i < 80 ; i ++) {
			w[i].shutdown();
		}
        
        System.out.println((t1 - start) + " " + (t2 - start)  + " " + (t3 - start)  + " " + (t4 - start)  + " " + (t5 - start)  + " " + (t6 - start)  + " " + (t7 - start)  + " " + (end - start) );
        
		
	}
	
	
	

	
}

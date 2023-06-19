package io.github.cloudadc.iControl.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigBuilder {

	private Config config = null;

    public static ConfigBuilder instance() {
    	return new ConfigBuilder();
    }
    
    public Config build() {
        return this.config;
    }
    
    public ConfigBuilder load(String... args) {
		
		if(!Files.exists(Paths.get("config.json")) && args.length == 0) {
			throw new RuntimeException("Either pass 'config.json' via args, or put to app home");
	    }
		
		String conf = null;
		String host = null;
		String user = null;
		String password = null;
		
		for (int i = 0 ; i < args.length ; i ++) {
            if(args[i].equals("-h") || args[i].equals("--host")) {
            	host = args[++i];
            } else if(args[i].equals("-c") || args[i].equals("--config")) {
                conf = args[++i];
            }else if(args[i].equals("-u") || args[i].equals("--user")) {
            	user = args[++i];
            } else if(args[i].equals("-p") || args[i].equals("--password")) {
            	password = args[++i];
            } else if(args[i].equals("--help")) {
            	usage();
            }
        }
		
		if(Files.exists(Paths.get("config.json")) || conf != null) {
			String config;
			try {
	            if(conf != null) {
	                    config = getResourceFileAsString(conf);
	            } else {
	                    config = getResourceFileAsString("config.json");
	            }
	            
	            ObjectMapper mapper = new ObjectMapper();
	    		
	    		try {
	    			this.config = mapper.readValue(config.getBytes(), Config.class);
	    		} catch (IOException e) {
	    			throw new RuntimeException(e);
	    		}
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
		}
		
		if(this.config == null) {
			if(host == null || user == null || password == null) {
				throw new RuntimeException("host/user/password should not be null");
			}
			this.config = new Config(host, user, password);
		}
		
		return this;
	}


	private String getResourceFileAsString(String fileName) throws IOException {
		String content = Files.readString(Paths.get(fileName), StandardCharsets.US_ASCII);
		if(content == null || content.length() == 0) {
			throw new RuntimeException(fileName + " not exist under template");
		}
		return content;
	}


	private void usage() {
		System.out.println("Usage: java -jar f5-rest-benchmark.jar [config.json] [-c config.json] [--config config.json] [-h HOST -u USER -p PASSWORD] [--host HOST --user USER --password PASSWORD] [debug] [--debug]");
		System.exit(0);
	}
}

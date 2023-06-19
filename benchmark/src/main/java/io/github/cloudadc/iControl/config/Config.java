package io.github.cloudadc.iControl.config;

public class Config {
	
	private String host;
	
	private String user;
	
	private String password;
	
	public Config() {
		
	}

	public Config(String host, String user, String password) {
		super();
		this.host = host;
		this.user = user;
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Connection String: [host=" + host + ", user=" + user + ", password=" + password + "]";
	}
	
	
	

}

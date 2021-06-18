package io.github.cloudadc.iControl.wapper;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cloudadc.iControl.model.ApiInvocationException;

public abstract class Wrapper implements iWrapper {
	
	static final String USER_AGENT = "iControl Rest Java JDK"; 
	static final String APPLICATION_JSON = "application/json";

	final private String hostname;
	
	final private String username;
	
	final private String password;
	
	final private String baseURL;
	
	final CloseableHttpClient client;
	
	Wrapper(String hostname, String username, String password) {
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		if(hostname.startsWith("https")) {
			this.baseURL = hostname;
		} else {
			this.baseURL = "https://" + hostname;
		}
		this.client = initClient();
	}

	protected abstract CloseableHttpClient initClient();

	public String getHostname() {
		return hostname;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getBaseURL() {
		return baseURL;
	}

	public static Wrapper create(String hostname, String username, String password) {
		return new iControlRestWrapper(hostname, username, password);
	}
	
	protected <T> T doGet(String url, Class<T> valueType) {
		HttpGet request = new HttpGet(getBaseURL() + url);
		request.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
		request.addHeader(HttpHeaders.USER_AGENT, USER_AGENT);
		
		try {
			try(CloseableHttpResponse response = client.execute(request)) {
				ObjectMapper om = new ObjectMapper();
				return om.readValue(response.getEntity().getContent(), valueType);
			}
		} catch (Exception e) {
			throw new ApiInvocationException(e);
		} 
	}

}

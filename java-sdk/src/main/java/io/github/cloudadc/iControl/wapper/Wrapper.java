package io.github.cloudadc.iControl.wapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cloudadc.iControl.model.ApiInvocationException;

public abstract class Wrapper implements iWrapper {
	
	Logger logger = LoggerFactory.getLogger(Wrapper.class);

	static final String USER_AGENT = "iControl Rest Java JDK"; 
	static final String APPLICATION_JSON = "application/json";
	
	static final String SUCCESS = "{\"success\": 1}";

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

	protected String getHostname() {
		return hostname;
	}

	protected String getUsername() {
		return username;
	}

	protected String getPassword() {
		return password;
	}
	
	protected String getBaseURL() {
		return baseURL;
	}
	
	public CloseableHttpClient getClient() {
		return client;
	}
	
	public void shutdown() {
		try {
			client.close();
		} catch (IOException e) {
			throw new ApiInvocationException(e);
		}
	}

	public static Wrapper create(String hostname, String username, String password) {
		return new iControlRestWrapper(hostname, username, password);
	}
	
	protected Map<String, String> transactionHeaders(long id) {
		Map<String, String> map = new HashMap<>();
		map.put("X-F5-REST-Coordination-Id", String.valueOf(id));
		return map;
	}
	
	protected <T> T doGet(String url, Class<T> valueType) {
		
		HttpGet request = new HttpGet(getBaseURL() + url);
		request.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
		request.addHeader(HttpHeaders.USER_AGENT, USER_AGENT);
		
		logger.info(request.toString());		
		
		try {
			try(CloseableHttpResponse response = client.execute(request)) {
				ObjectMapper om = new ObjectMapper();
				return om.readValue(response.getEntity().getContent(), valueType);
			}
		} catch (Exception e) {
			throw new ApiInvocationException(e);
		} 
	}
	
	protected <T> T doPatch(String url, String payload, Class<T> valueType) {	
		return doPatch(url, payload, valueType, Collections.<String, String>emptyMap());
	}
	
    protected <T> T doPatch(String url, String payload, Class<T> valueType, Map<String, String> headers) {
		
		HttpPatch request = new HttpPatch(getBaseURL() + url);
		request.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
		request.addHeader(HttpHeaders.USER_AGENT, USER_AGENT);
		request.addHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);
		headers.keySet().forEach(key -> {
			request.addHeader(key, headers.get(key));
		});
		request.setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON));
		
		logger.info(request.toString());
		
		try {
			try(CloseableHttpResponse response = client.execute(request)) {
				ObjectMapper om = new ObjectMapper();
				return om.readValue(response.getEntity().getContent(), valueType);
			}
		} catch (Exception e) {
			throw new ApiInvocationException(e);
		} 
	}
    
    protected <T> T doPost(String url, String payload, Class<T> valueType) {
    	return doPost(url, payload, valueType, Collections.<String, String>emptyMap());
    }
	
	protected <T> T doPost(String url, String payload, Class<T> valueType, Map<String, String> headers) {
		
		HttpPost request = new HttpPost(getBaseURL() + url);
		request.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
		request.addHeader(HttpHeaders.USER_AGENT, USER_AGENT);
		request.addHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON);
		headers.keySet().forEach(key -> {
			request.addHeader(key, headers.get(key));
		});
		request.setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON));
		
		try {
			try(CloseableHttpResponse response = client.execute(request)) {
				ObjectMapper om = new ObjectMapper();
				return om.readValue(response.getEntity().getContent(), valueType);
			}
		} catch (Exception e) {
			throw new ApiInvocationException(e);
		} 
		
	}
	
	protected <T> T doDelete(String url, Class<T> valueType) {
		
		HttpDelete request = new HttpDelete(getBaseURL() + url);
		request.setHeader(HttpHeaders.ACCEPT, APPLICATION_JSON);
		request.addHeader(HttpHeaders.USER_AGENT, USER_AGENT);
		
		logger.info(request.toString());		
		
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

package io.github.cloudadc.iControl.wapper;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;

import io.github.cloudadc.iControl.model.VirtualServer;
import io.github.cloudadc.iControl.model.VirtualServers;

public class iControlRestWrapper extends Wrapper{
	
	iControlRestWrapper(String hostname, String username, String password) {
		super(hostname, username, password);
	}
	
	@Override
	protected CloseableHttpClient initClient() {
		
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(getUsername(), getPassword());
		provider.setCredentials(AuthScope.ANY, credentials);
		
		try {
			return HttpClientBuilder.create()
					.setDefaultCredentialsProvider(provider)
					.setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
					.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

	@Override
	public VirtualServers listAllVirtualServers()  {
		return doGet("/mgmt/tm/ltm/virtual", VirtualServers.class);
	}
	
	@Override
	public VirtualServers listAllVirtualServersExpandSubcollections() {
		return doGet("/mgmt/tm/ltm/virtual?expandSubcollections=true", VirtualServers.class);
	}

	@Override
	public VirtualServer getVirtualServerByName(String vs) {
		return doGet("/mgmt/tm/ltm/virtual/" + vs, VirtualServer.class);		
	}

	@Override
	public VirtualServer getVirtualServerByNameExpandSubcollections(String vs) {
		return doGet("/mgmt/tm/ltm/virtual/" + vs + "?expandSubcollections=true", VirtualServer.class);
	}

	

	

}

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

import io.github.cloudadc.iControl.model.Member;
import io.github.cloudadc.iControl.model.MembersReference;
import io.github.cloudadc.iControl.model.Node;
import io.github.cloudadc.iControl.model.NodesReference;
import io.github.cloudadc.iControl.model.Pool;
import io.github.cloudadc.iControl.model.PoolsReference;
import io.github.cloudadc.iControl.model.VirtualServer;
import io.github.cloudadc.iControl.model.VirtualServersReference;

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
	public VirtualServersReference listAllVirtualServers()  {
		return doGet("/mgmt/tm/ltm/virtual", VirtualServersReference.class);
	}
	
	@Override
	public VirtualServersReference listAllVirtualServersExpandSubcollections() {
		return doGet("/mgmt/tm/ltm/virtual?expandSubcollections=true", VirtualServersReference.class);
	}

	@Override
	public VirtualServer getVirtualServerByName(String vs) {
		return doGet("/mgmt/tm/ltm/virtual/" + vs, VirtualServer.class);		
	}

	@Override
	public VirtualServer getVirtualServerByNameExpandSubcollections(String vs) {
		return doGet("/mgmt/tm/ltm/virtual/" + vs + "?expandSubcollections=true", VirtualServer.class);
	}

	@Override
	public NodesReference listAllNodes() {
		return doGet("/mgmt/tm/ltm/node", NodesReference.class);
	}

	@Override
	public Node getNodeByName(String name) {
		return doGet("/mgmt/tm/ltm/node/" + name, Node.class);
	}

	@Override
	public PoolsReference listAllPools() {
		return doGet("/mgmt/tm/ltm/pool", PoolsReference.class);
	}

	@Override
	public PoolsReference listAllPoolsExpandSubcollections() {
		return doGet("/mgmt/tm/ltm/pool?expandSubcollections=true", PoolsReference.class);
	}

	@Override
	public Pool getPoolByName(String name) {
		return doGet("/mgmt/tm/ltm/pool/" + name, Pool.class);
	}

	@Override
	public Pool getPoolByNameExpandSubcollections(String name) {
		return doGet("/mgmt/tm/ltm/pool/" + name + "?expandSubcollections=true", Pool.class);
	}

	@Override
	public MembersReference listAllMembers(String poolName) {
		return doGet("/mgmt/tm/ltm/pool/" + poolName + "/members", MembersReference.class);
	}

	@Override
	public Member getMemberByName(String poolName, String memberName) {
		return doGet("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, Member.class);
	}

	@Override
	public Node nodeDiable(String nodeName) {
		return doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"state\": \"user-down\"}", Node.class);
	}

	@Override
	public Node nodeEnable(String nodeName) {
		return doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"state\": \"user-up\"}", Node.class);
	}

	@Override
	public Node nodeOffline(String nodeName) {
		return doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"session\": \"user-disabled\"}", Node.class);
	}

	@Override
	public Node nodeUp(String nodeName) {
		return doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"session\": \"user-enabled\"}", Node.class);
	}

	

	

}

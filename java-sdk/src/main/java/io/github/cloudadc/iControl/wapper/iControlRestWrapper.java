package io.github.cloudadc.iControl.wapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;

import io.github.cloudadc.iControl.model.BashResponse;
import io.github.cloudadc.iControl.model.Member;
import io.github.cloudadc.iControl.model.MembersReference;
import io.github.cloudadc.iControl.model.Node;
import io.github.cloudadc.iControl.model.NodesReference;
import io.github.cloudadc.iControl.model.Pool;
import io.github.cloudadc.iControl.model.PoolsReference;
import io.github.cloudadc.iControl.model.Transaction;
import io.github.cloudadc.iControl.model.TransactionReference;
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
	public Object nodeDiable(String nodeName) {
		return doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"session\": \"user-disabled\"}", Object.class);
	}
	
	@Override
	public Object nodeDiable(String nodeName, long transId) {
		return doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"session\": \"user-disabled\"}", Object.class, transactionHeaders(transId));
	}

	@Override
	public Object nodeEnable(String nodeName) {
		return doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"session\": \"user-enabled\"}", Object.class);
	}
	
	@Override
	public Object nodeEnable(String nodeName, long transId) {
		return doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"session\": \"user-enabled\"}", Object.class, transactionHeaders(transId));
	}

	@Override
	public Object nodeOffline(String nodeName) {
		Transaction t = this.transactionStart();
		doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"state\": \"user-down\"}", Object.class, transactionHeaders(t.transId));
		doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"session\": \"user-disabled\"}", Object.class, transactionHeaders(t.transId));
		this.transactionCommit(t.transId);
		return SUCCESS;
	}
	
	@Override
	public Object nodeOffline(String nodeName, long transId) {
		doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"state\": \"user-down\"}", Object.class, transactionHeaders(transId));
		doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"session\": \"user-disabled\"}", Object.class, transactionHeaders(transId));
		return SUCCESS;
	}

	@Override
	public Object nodeUp(String nodeName) {
		Transaction t = this.transactionStart();
		doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"state\": \"user-up\"}", Object.class, transactionHeaders(t.transId));
		doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"session\": \"user-enabled\"}", Object.class, transactionHeaders(t.transId));
		this.transactionCommit(t.transId);
		return SUCCESS;
	}
	
	@Override
	public Object nodeUp(String nodeName, long transId) {
		doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"state\": \"user-up\"}", Object.class, transactionHeaders(transId));
		doPatch("/mgmt/tm/ltm/node/" + nodeName, "{\"session\": \"user-enabled\"}", Object.class, transactionHeaders(transId));
		return SUCCESS;
	}

	@Override
	public Object memberDisable(String poolName, String memberName) {
		return doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"session\": \"user-disabled\"}", Object.class);
	}
	
	@Override
	public Object memberDisable(String poolName, String memberName, long transId) {
		return doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"session\": \"user-disabled\"}", Object.class, transactionHeaders(transId));
	}

	@Override
	public Object memberEnable(String poolName, String memberName) {
		return doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"session\": \"user-enabled\"}", Object.class);
	}
	
	@Override
	public Object memberEnable(String poolName, String memberName, long transId) {
		return doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"session\": \"user-enabled\"}", Object.class, transactionHeaders(transId));
	}

	@Override
	public Object memberOffline(String poolName, String memberName) {
		Transaction t = this.transactionStart();
		doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"state\": \"user-down\"}", Object.class, transactionHeaders(t.transId));
		doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"session\": \"user-disabled\"}", Object.class, transactionHeaders(t.transId));
		this.transactionCommit(t.transId);
		return SUCCESS;
	}
	
	@Override
	public Object memberOffline(String poolName, String memberName, long transId) {
		doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"state\": \"user-down\"}", Object.class, transactionHeaders(transId));
		doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"session\": \"user-disabled\"}", Object.class, transactionHeaders(transId));
		return SUCCESS;
	}

	@Override
	public Object memberUp(String poolName, String memberName) {
		Transaction t = this.transactionStart();
		doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"state\": \"user-up\"}", Object.class, transactionHeaders(t.transId));
		doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"session\": \"user-enabled\"}", Object.class, transactionHeaders(t.transId));
		this.transactionCommit(t.transId);
		return SUCCESS;
	}
	
	@Override
	public Object memberUp(String poolName, String memberName, long transId) {
		doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"state\": \"user-up\"}", Object.class, transactionHeaders(transId));
		doPatch("/mgmt/tm/ltm/pool/" + poolName + "/members/~Common~" + memberName, "{\"session\": \"user-enabled\"}", Object.class, transactionHeaders(transId));
		return SUCCESS;
	}

	@Override
	public Transaction transactionStart() {
		return doPost("/mgmt/tm/transaction", "{}", Transaction.class);
	}

	@Override
	public Transaction transactionStatus(long id) {
		return doGet("/mgmt/tm/transaction/" + id, Transaction.class);
	}

	@Override
	public Object transactionRevoke(long id) {
		return doDelete("/mgmt/tm/transaction/" + id, Object.class);
	}

	@Override
	public Object transactionCommit(long id) {
		return doPatch("/mgmt/tm/transaction/" + id, "{\"state\":\"VALIDATING\"}", Object.class);
	}

	@Override
	public TransactionReference listAllTransaction() {
		return doGet("/mgmt/tm/transaction", TransactionReference.class);
	}

	@Override
	public BashResponse bashScripts(String bash) {
		return doPost("/mgmt/tm/util/bash", "{\"command\":\"run\", \"utilCmdArgs\": \"-c '" + bash + "'\"}", BashResponse.class);
	}

	@Override
	public Object nodeDiable(String[] nodeNames) {
		List<Object> respList = new ArrayList<>();
		Transaction t = this.transactionStart();
		Arrays.asList(nodeNames).forEach(n -> {
			respList.add(this.nodeDiable(n, t.transId));
		});
		this.transactionCommit(t.transId);
		return respList;
	}

	@Override
	public Object nodeEnable(String[] nodeNames) {
		List<Object> respList = new ArrayList<>();
		Transaction t = this.transactionStart();
        Arrays.asList(nodeNames).forEach(n -> {
        	respList.add(this.nodeEnable(n, t.transId));
		});
		this.transactionCommit(t.transId);
		return respList;
	}

	@Override
	public Object nodeOffline(String[] nodeNames) {
		List<Object> respList = new ArrayList<>();
		Transaction t = this.transactionStart();
        Arrays.asList(nodeNames).forEach(n -> {
        	respList.add(this.nodeOffline(n, t.transId));
		});
		this.transactionCommit(t.transId);
		return respList;
	}

	@Override
	public Object nodeUp(String[] nodeNames) {
		List<Object> respList = new ArrayList<>();
		Transaction t = this.transactionStart();
        Arrays.asList(nodeNames).forEach(n -> {
        	respList.add(this.nodeUp(n, t.transId));
		});
		this.transactionCommit(t.transId);
		return respList;
	}

	@Override
	public Object memberDisable(String poolName, String[] memberNames) {
		List<Object> respList = new ArrayList<>();
		Transaction t = this.transactionStart();
        Arrays.asList(memberNames).forEach(n -> {
        	respList.add(this.memberDisable(poolName, n, t.transId));
		});
		this.transactionCommit(t.transId);
		return respList;
	}

	@Override
	public Object memberEnable(String poolName, String[] memberNames) {
		List<Object> respList = new ArrayList<>();
		Transaction t = this.transactionStart();
        Arrays.asList(memberNames).forEach(n -> {
        	respList.add(this.memberEnable(poolName, n, t.transId));
		});
		this.transactionCommit(t.transId);
		return respList;
	}

	@Override
	public Object memberOffline(String poolName, String[] memberNames) {
		List<Object> respList = new ArrayList<>();
		Transaction t = this.transactionStart();
        Arrays.asList(memberNames).forEach(n -> {
        	respList.add(this.memberOffline(poolName, n, t.transId));
		});
		this.transactionCommit(t.transId);
		return respList;
	}

	@Override
	public Object memberUp(String poolName, String[] memberNames) {
		List<Object> respList = new ArrayList<>();
		Transaction t = this.transactionStart();
        Arrays.asList(memberNames).forEach(n -> {
        	respList.add(this.memberUp(poolName, n, t.transId));
		});
		this.transactionCommit(t.transId);
		return respList;
	}

	@Override
	public Pool createPool(String name, String loadBalancingMode, String monitor, String[] members) {
		return createPool(name, loadBalancingMode, monitor, members, -1);
	}
	
	@Override
	public Pool createPool(String name, String loadBalancingMode, String monitor, String[] members, long transId) {
		
		boolean first = true;
		StringBuffer sb = new StringBuffer();
		for (String member : members) {
			
			if(first) {
				first = false;
			} else {
				sb.append(COMMA).append(SPACE);
			}

			sb.append(PARENTHESIS_LEFT).append(QUOTE).append("name").append(QUOTE).append(COLON).append(SPACE).append(QUOTE).append(member).append(QUOTE).append(PARENTHESIS_RIGHT);
		}
		
		String json = POOL_TEMPLATE.replaceAll(REPLACEMENT_POOL, name).replaceAll(REPLACEMENT_MONITOR, monitor).replaceAll(REPLACEMENT_LOADBALANCEINGNODE, loadBalancingMode).replaceAll(REPLACEMENT_MEMBERS, sb.toString());
		
		if(transId > 0) {
			return doPost("/mgmt/tm/ltm/pool", json, Pool.class, transactionHeaders(transId));
		} else {
			return doPost("/mgmt/tm/ltm/pool", json, Pool.class);
		}
	}

	@Override
	public Pool createPool(String json) {
		return doPost("/mgmt/tm/ltm/pool", json, Pool.class);
	}
	
	@Override
	public Pool createPool(String json, long transId) {
		return doPost("/mgmt/tm/ltm/pool", json, Pool.class, transactionHeaders(transId));
	}

	@Override
	public Object deletePool(String name) {
		return doDelete("/mgmt/tm/ltm/pool/" + name, Object.class);
	}

	@Override
	public Object deletePool(String name, long transId) {
		return doDelete("/mgmt/tm/ltm/pool/" + name, Object.class, transactionHeaders(transId));
	}

	@Override
	public VirtualServer createVirtualServer(String name, String destination, String pool) {
		return createVirtualServer(name, destination, pool, -1);
	}

	@Override
	public VirtualServer createVirtualServer(String name, String destination, String pool, long transId) {
		
		String json = VS_TEMPLATE_FASTL4.replaceAll(REPLACEMENT_VS_NAME, name).replaceAll(REPLACEMENT_DESTINATION, destination).replaceAll(REPLACEMENT_VS_POOL, pool);
		if(transId > 0) {
			return doPost("/mgmt/tm/ltm/virtual", json, VirtualServer.class, transactionHeaders(transId));
		} else {
			return doPost("/mgmt/tm/ltm/virtual", json, VirtualServer.class);
		}
	}

	@Override
	public VirtualServer createVirtualServer(String name, String destination, String pool, String persist) {
		return createVirtualServer(name, destination, pool, persist, -1);
	}

	@Override
	public VirtualServer createVirtualServer(String name, String destination, String pool, String persist, long transId) {
		
		String json = VS_TEMPLATE_FASTL4_PERSIST.replaceAll(REPLACEMENT_VS_NAME, name).replaceAll(REPLACEMENT_DESTINATION, destination).replaceAll(REPLACEMENT_VS_POOL, pool).replaceAll(REPLACEMENT_VS_PERSIST, persist);
		if(transId > 0) {
			return doPost("/mgmt/tm/ltm/virtual", json, VirtualServer.class, transactionHeaders(transId));
		} else {
			return doPost("/mgmt/tm/ltm/virtual", json, VirtualServer.class);
		}
	}

	@Override
	public VirtualServer createVirtualServer(String json) {
		return doPost("/mgmt/tm/ltm/virtual", json, VirtualServer.class);
	}

	@Override
	public VirtualServer createVirtualServer(String json, long transId) {
		return doPost("/mgmt/tm/ltm/virtual", json, VirtualServer.class, transactionHeaders(transId));
	}

	@Override
	public Object deleteVirtualServer(String name) {
		return doDelete("/mgmt/tm/ltm/virtual/" + name, Object.class);
	}

	@Override
	public Object deleteVirtualServer(String name, long transId) {
		return doDelete("/mgmt/tm/ltm/virtual/" + name, Object.class, transactionHeaders(transId));
	}

	

	

}

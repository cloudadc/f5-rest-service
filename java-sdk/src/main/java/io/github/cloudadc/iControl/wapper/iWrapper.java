package io.github.cloudadc.iControl.wapper;

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

public interface iWrapper {
	
	/**
	 * List All Virtual Servers
	 * @return A warper object of all Virtual Servers, which contains a items represent all Virtual Servers
	 */
	public VirtualServersReference listAllVirtualServers() ;
	
	public VirtualServersReference listAllVirtualServersExpandSubcollections();
	
	/**
	 * Get Virtual Server by name
	 * @param vs - the Virtual Server name
	 * @return A warper object of Virtual Server
	 */
	public VirtualServer getVirtualServerByName(String vsName);
	
	public VirtualServer getVirtualServerByNameExpandSubcollections(String vsName);
	
	public NodesReference listAllNodes();
	
	public Node getNodeByName(String nodeName);
	
	public Object nodeDiable(String nodeName);
	
	public Object nodeEnable(String nodeName);
	
	/**
	 * Force Offline = user-disabled + user-down
	 * @param nodeName
	 * @return
	 */
	public Object nodeOffline(String nodeName);
	
	public Object nodeUp(String nodeName);
	
    public Object nodeDiable(String nodeName, long transId);
	
	public Object nodeEnable(String nodeName, long transId);
	
	public Object nodeOffline(String nodeName, long transId);
	
	public Object nodeUp(String nodeName, long transId);
	
	public PoolsReference listAllPools();
	
	public PoolsReference listAllPoolsExpandSubcollections();
	
	public Pool getPoolByName(String poolName);
	
	public Pool getPoolByNameExpandSubcollections(String poolName);
	
	public MembersReference listAllMembers(String poolName);
	
	public Member getMemberByName(String poolName, String memberName);
	
	public Object memberDisable(String poolName, String memberName);
	
	public Object memberEnable(String poolName, String memberName);
	
    public Object memberOffline(String poolName, String memberName);
	
	public Object memberUp(String poolName, String memberName);
	
    public Object memberDisable(String poolName, String memberName, long transId);
	
	public Object memberEnable(String poolName, String memberName, long transId);
	
    public Object memberOffline(String poolName, String memberName, long transId);
	
	public Object memberUp(String poolName, String memberName, long transId);
	
	public Transaction transactionStart();
	
	public Transaction transactionStatus(long transId);
	
	public Object transactionRevoke(long transId);
	
	public Object transactionCommit(long transId);
	
	public TransactionReference listAllTransaction();
	
	public BashResponse bashScripts(String bash);
	

}

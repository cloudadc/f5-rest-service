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

import static io.github.cloudadc.iControl.wapper.Category.*;
import static io.github.cloudadc.iControl.wapper.Type.*;

public interface iWrapper {
	
	/**
	 * List All Virtual Servers
	 * @return A warper object of all Virtual Servers, which contains a items represent all Virtual Servers
	 */
	@iType(category = LTM, type = GET, description = "List All Virtual Servers")
	public VirtualServersReference listAllVirtualServers() ;
	
	@iType(category = LTM, type = GET)
	public VirtualServersReference listAllVirtualServersExpandSubcollections();
	
	/**
	 * Get Virtual Server by name
	 * @param vs - the Virtual Server name
	 * @return A warper object of Virtual Server
	 */
	@iType(category = LTM, type = GET)
	public VirtualServer getVirtualServerByName(String vsName);
	
	@iType(category = LTM, type = GET)
	public VirtualServer getVirtualServerByNameExpandSubcollections(String vsName);
	
	@iType(category = LTM, type = GET)
	public NodesReference listAllNodes();
	
	@iType(category = LTM, type = GET)
	public Node getNodeByName(String nodeName);
	
	@iType(category = LTM, type = PATCH)
	public Object nodeDiable(String nodeName);
	
	@iType(category = LTM, type = PATCH)
	public Object nodeEnable(String nodeName);
	
	@iType(category = LTM, type = PATCH)
	public Object nodeOffline(String nodeName);
	
	@iType(category = LTM, type = PATCH)
	public Object nodeUp(String nodeName);
	
	@iType(category = LTM, type = PATCH)
    public Object nodeDiable(String[] nodeNames);
	
	@iType(category = LTM, type = PATCH)
	public Object nodeEnable(String[] nodeNames);
	
	@iType(category = LTM, type = PATCH)
	public Object nodeOffline(String[] nodeNames);
	
	@iType(category = LTM, type = PATCH)
	public Object nodeUp(String[] nodeNames);
	
	@iType(category = LTM, type = PATCH)
    public Object nodeDiable(String nodeName, long transId);
	
	@iType(category = LTM, type = PATCH)
	public Object nodeEnable(String nodeName, long transId);
	
	@iType(category = LTM, type = PATCH)
	public Object nodeOffline(String nodeName, long transId);
	
	@iType(category = LTM, type = PATCH)
	public Object nodeUp(String nodeName, long transId);
	
	@iType(category = LTM, type = GET)
	public PoolsReference listAllPools();
	
	@iType(category = LTM, type = GET)
	public PoolsReference listAllPoolsExpandSubcollections();
	
	@iType(category = LTM, type = GET)
	public Pool getPoolByName(String poolName);
	
	@iType(category = LTM, type = GET)
	public Pool getPoolByNameExpandSubcollections(String poolName);
	
	@iType(category = LTM, type = GET)
	public MembersReference listAllMembers(String poolName);
	
	@iType(category = LTM, type = GET)
	public Member getMemberByName(String poolName, String memberName);
	
	@iType(category = LTM, type = PATCH)
	public Object memberDisable(String poolName, String memberName);
	
	@iType(category = LTM, type = PATCH)
	public Object memberEnable(String poolName, String memberName);
	
	@iType(category = LTM, type = PATCH)
    public Object memberOffline(String poolName, String memberName);
	
	@iType(category = LTM, type = PATCH)
	public Object memberUp(String poolName, String memberName);
	
	@iType(category = LTM, type = PATCH)
    public Object memberDisable(String poolName, String[] memberNames);
	
	@iType(category = LTM, type = PATCH)
	public Object memberEnable(String poolName, String[] memberNames);
	
	@iType(category = LTM, type = PATCH)
    public Object memberOffline(String poolName, String[] memberNames);
	
	@iType(category = LTM, type = PATCH)
	public Object memberUp(String poolName, String[] memberNames);
	
	@iType(category = LTM, type = PATCH)
    public Object memberDisable(String poolName, String memberName, long transId);
	
	@iType(category = LTM, type = PATCH)
	public Object memberEnable(String poolName, String memberName, long transId);
	
	@iType(category = LTM, type = PATCH)
    public Object memberOffline(String poolName, String memberName, long transId);
	
	@iType(category = LTM, type = PATCH)
	public Object memberUp(String poolName, String memberName, long transId);
	
	@iType(category = LTM, type = POST)
	public Transaction transactionStart();
	
	@iType(category = LTM, type = GET)
	public Transaction transactionStatus(long transId);
	
	@iType(category = LTM, type = PATCH)
	public Object transactionRevoke(long transId);
	
	@iType(category = LTM, type = PATCH)
	public Object transactionCommit(long transId);
	
	@iType(category = LTM, type = GET)
	public TransactionReference listAllTransaction();
	
	@iType(category = LTM, type = POST)
	public BashResponse bashScripts(String bash);
	

}

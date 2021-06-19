package io.github.cloudadc.iControl.wapper;

import io.github.cloudadc.iControl.model.Member;
import io.github.cloudadc.iControl.model.MembersReference;
import io.github.cloudadc.iControl.model.Node;
import io.github.cloudadc.iControl.model.NodesReference;
import io.github.cloudadc.iControl.model.Pool;
import io.github.cloudadc.iControl.model.PoolsReference;
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
	
	public Node nodeDiable(String nodeName);
	
	public Node nodeEnable(String nodeName);
	
	public Node nodeOffline(String nodeName);
	
	public Node nodeUp(String nodeName);
	
	public PoolsReference listAllPools();
	
	public PoolsReference listAllPoolsExpandSubcollections();
	
	public Pool getPoolByName(String poolName);
	
	public Pool getPoolByNameExpandSubcollections(String poolName);
	
	public MembersReference listAllMembers(String poolName);
	
	public Member getMemberByName(String poolName, String memberName);
	
	public Member memberDisable(String poolName, String memberName);
	
	public Member memberEnable(String poolName, String memberName);
	
    public Member memberOffline(String poolName, String memberName);
	
	public Member memberUp(String poolName, String memberName);
	

}

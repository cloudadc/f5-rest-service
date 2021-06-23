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
	
	public static String PARENTHESIS_LEFT = "{";
	
	public static String PARENTHESIS_RIGHT = "}";
	
	public static String QUOTE = "\"";
		
	public static String COMMA = ",";
	
	public static String COLON = ":";
	
	public static String SPACE = " ";
	
	public static String REPLACEMENT_POOL = "REPLACEMENT_POOL";
	
	public static String REPLACEMENT_MONITOR = "REPLACEMENT_MONITOR";
	
	public static String REPLACEMENT_LOADBALANCEINGNODE = "REPLACEMENT_LOADBALANCEINGNODE";
	
	public static String REPLACEMENT_MEMBERS = "REPLACEMENT_MEMBERS";
	
	public static String POOL_TEMPLATE = "{\"name\":\"REPLACEMENT_POOL\",\"monitor\": \"REPLACEMENT_MONITOR\", \"loadBalancingMode\": \"REPLACEMENT_LOADBALANCEINGNODE\", \"members\":[ REPLACEMENT_MEMBERS ] }";
	
	public static String REPLACEMENT_VS_NAME = "REPLACEMENT_VS_NAME";
	
	public static String REPLACEMENT_DESTINATION = "REPLACEMENT_DESTINATION";
	
	public static String REPLACEMENT_VS_POOL = "REPLACEMENT_VS_POOL";
	
	public static String REPLACEMENT_VS_PERSIST = "REPLACEMENT_VS_PERSIST";
	
	public static String VS_TEMPLATE_FASTL4 = "{\"name\": \"REPLACEMENT_VS_NAME\", \"destination\": \"REPLACEMENT_DESTINATION\", \"mask\": \"255.255.255.255\", \"pool\": \"REPLACEMENT_VS_POOL\"}";
	
	public static String VS_TEMPLATE_FASTL4_PERSIST = "{\"name\": \"REPLACEMENT_VS_NAME\", \"destination\": \"REPLACEMENT_DESTINATION\", \"mask\": \"255.255.255.255\", \"pool\": \"REPLACEMENT_VS_POOL\", \"persist\": [ {\"name\": \"REPLACEMENT_VS_PERSIST\"} ] }";
	
	@iType(category = LTM, type = POST)
	public Pool createPool(String name, String loadBalancingMode, String monitor, String[] members);
	
	@iType(category = LTM, type = POST)
	public Pool createPool(String name, String loadBalancingMode, String monitor, String[] members, long transId);
	
	@iType(category = LTM, type = POST)
	public Pool createPool(String payload);
	
	@iType(category = LTM, type = POST)
	public Pool createPool(String payload, long transId);
	
	@iType(category = LTM, type = DELETE)
	public Object deletePool(String name);
	
	@iType(category = LTM, type = DELETE)
	public Object deletePool(String name, long transId);
	
	@iType(category = LTM, type = POST, description = "fastl4 vs, without persist")
	public VirtualServer createVirtualServer(String name, String destination, String pool);
	
	@iType(category = LTM, type = POST, description = "fastl4 vs, without persist")
	public VirtualServer createVirtualServer(String name, String destination, String pool, long transId);
	
	@iType(category = LTM, type = POST, description = "fastl4 vs, with persist")
	public VirtualServer createVirtualServer(String name, String destination, String pool, String persist);
	
	@iType(category = LTM, type = POST, description = "fastl4 vs, with persist")
	public VirtualServer createVirtualServer(String name, String destination, String pool, String persist, long transId);
	
	@iType(category = LTM, type = POST, description = "create vs via post body")
	public VirtualServer createVirtualServer(String payload);
	
	@iType(category = LTM, type = POST, description = "create vs via post body")
	public VirtualServer createVirtualServer(String payload, long transId);
	
	@iType(category = LTM, type = DELETE)
	public Object deleteVirtualServer(String name);
	
	@iType(category = LTM, type = DELETE)
	public Object deleteVirtualServer(String name, long transId);
	
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

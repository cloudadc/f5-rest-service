package io.github.cloudadc.iControl.wapper;

import io.github.cloudadc.iControl.model.VirtualServer;
import io.github.cloudadc.iControl.model.VirtualServers;

public interface iWrapper {
	
	/**
	 * List All Virtual Servers
	 * @return A warper object of all Virtual Servers, which contains a items represent all Virtual Servers
	 */
	public VirtualServers listAllVirtualServers() ;
	
	public VirtualServers listAllVirtualServersExpandSubcollections();
	
	/**
	 * Get Virtual Server by name
	 * @param vs - the Virtual Server name
	 * @return A warper object of Virtual Server
	 */
	public VirtualServer getVirtualServerByName(String vs);
	
	public VirtualServer getVirtualServerByNameExpandSubcollections(String vs);

}

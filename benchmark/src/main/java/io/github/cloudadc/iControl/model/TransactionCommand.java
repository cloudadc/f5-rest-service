package io.github.cloudadc.iControl.model;

import java.util.Map;

public class TransactionCommand {
	
	public String method;
	public String uri;
	public Map<String, Object> body;
	public int evalOrder;
	public int commandId;
	public String kind;
    public String selfLink;
    
	@Override
	public String toString() {
		return "[method=" + method + ", uri=" + uri + ", body=" + body + ", commandId=" + commandId + "]";
	}

}

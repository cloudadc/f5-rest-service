package io.github.cloudadc.iControl.model;

import java.util.List;

public class TransactionCommandsReference {
	
	public List<TransactionCommand> items;
    public String kind;
    public String selfLink;
	@Override
	public String toString() {
		return items.toString() ;
	}

}

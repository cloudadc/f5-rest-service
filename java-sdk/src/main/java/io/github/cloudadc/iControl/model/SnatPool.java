package io.github.cloudadc.iControl.model;

import java.util.List;

public class SnatPool {
	
	public String kind;
    public String name;
    public String partition;
    public String fullPath;
    public int generation;
    public String selfLink;
    public List<String> members;
    public List<MembersReference> membersReference;

}

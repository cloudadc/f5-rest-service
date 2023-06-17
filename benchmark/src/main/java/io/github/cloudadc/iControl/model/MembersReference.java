package io.github.cloudadc.iControl.model;

import java.util.List;

public class MembersReference {

	public String kind;
	public String link;
	public String selfLink;
    public boolean isSubcollection;
    public List<Member> items;
}

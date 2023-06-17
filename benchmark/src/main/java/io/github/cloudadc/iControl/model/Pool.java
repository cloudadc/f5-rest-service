package io.github.cloudadc.iControl.model;

public class Pool {

	public String kind;
    public String name;
    public String partition;
    public String fullPath;
    public int generation;
    public String selfLink;
    public String address;
    public int connectionLimit;
    public int dynamicRatio;
    public String ephemeral;
    public Fqdn fqdn;
    public String inheritProfile;
    public String logging;
    public String monitor;
    public int priorityGroup;
    public String rateLimit;
    public int ratio;
    public String session;
    public String state;
    public NameReference nameReference;
    public String allowNat;
    public String allowSnat;
    public String ignorePersistedWeight;
    public String ipTosToClient;
    public String ipTosToServer;
    public String linkQosToClient;
    public String linkQosToServer;
    public String loadBalancingMode;
    public int minActiveMembers;
    public int minUpMembers;
    public String minUpMembersAction;
    public String minUpMembersChecking;
    public int queueDepthLimit;
    public String queueOnConnectionLimit;
    public int queueTimeLimit;
    public int reselectTries;
    public String serviceDownAction;
    public int slowRampTime;
    public MembersReference membersReference;

}

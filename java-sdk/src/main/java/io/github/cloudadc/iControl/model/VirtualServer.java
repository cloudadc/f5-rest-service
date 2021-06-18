package io.github.cloudadc.iControl.model;

public class VirtualServer {
	
	public String kind;
    public String name;
    public String partition;
    public String fullPath;
    public int generation;
    public String selfLink;
    public String addressStatus;
    public String autoLasthop;
    public String cmpEnabled;
    public int connectionLimit;
    public String destination;
    public boolean enabled;
    public int gtmScore;
    public String ipProtocol;
    public String mask;
    public String mirror;
    public String mobileAppTunnel;
    public String nat64;
    public String pool;
    public PoolReference poolReference;
    public String rateLimit;
    public int rateLimitDstMask;
    public String rateLimitMode;
    public int rateLimitSrcMask;
    public String serviceDownImmediateAction;
    public String source;
    public SourceAddressTranslation sourceAddressTranslation;
    public String sourcePort;
    public String synCookieStatus;
    public String translateAddress;
    public String translatePort;
    public boolean vlansDisabled;
    public int vsIndex;
    public PoliciesReference policiesReference;
    public ProfilesReference profilesReference;

}

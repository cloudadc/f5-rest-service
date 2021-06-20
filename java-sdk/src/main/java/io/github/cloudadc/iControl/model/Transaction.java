package io.github.cloudadc.iControl.model;

public class Transaction {

	public long transId;
    public String state;
    public int timeoutSeconds;
    public boolean asyncExecution;
    public boolean validateOnly;
    public int executionTimeout;
    public int executionTime;
    public String failureReason;
    public String kind;
    public String selfLink;
}

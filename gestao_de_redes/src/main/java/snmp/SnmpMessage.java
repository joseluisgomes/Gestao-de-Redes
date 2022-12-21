package snmp;

public enum SnmpMessage {
    GetRequest(0), GetNextRequest(1),
    GetBulkRequest(2), SetRequest(3),
    Response(4), Trap(5),
    InformRequest(6);

    private final int operationType;

    SnmpMessage(int operationType) {
        this.operationType = operationType;
    }

    public int getOperationType() {
        return operationType;
    }
}

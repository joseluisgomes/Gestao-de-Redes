package snmp;

public enum SnmpOID {
    operTable("1.3.6.1.4.1.1.1.1"), idOper("1.3.6.1.4.1.1.1.1.1"),
    typeOper("1.3.6.1.4.1.1.1.1.2"), operArg1("1.3.6.1.4.1.1.1.1.3"),
    operArg2("1.3.6.1.4.1.1.1.1.4"), idSource("1.3.6.1.4.1.1.1.1.5"),
    idDestination("1.3.6.1.4.1.1.1.1.6"), oidArg("1.3.6.1.4.1.1.1.1.7"),
    valueArg("1.3.6.1.4.1.1.1.1.8"), typeArg("1.3.6.1.4.1.1.1.1.9"),
    sizeArg("1.3.6.1.4.1.1.1.1.10"), ttl("1.3.6.1.4.1.1.1.1.11"),
    status("1.3.6.1.4.1.1.1.1.12");

    private final String OID;

    SnmpOID(String oid) {
        OID = oid;
    }

    public String getOID() {
        return OID;
    }
}

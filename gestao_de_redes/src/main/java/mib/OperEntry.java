package mib;

import snmp.SnmpObjectType;

public class OperEntry {
    private SnmpObjectType idOper = new SnmpObjectType();;
    private SnmpObjectType typeOper = new SnmpObjectType();
    private SnmpObjectType operArg1 = new SnmpObjectType();
    private SnmpObjectType operArg2 = new SnmpObjectType();
    private SnmpObjectType idSource = new SnmpObjectType();
    private SnmpObjectType idDestination = new SnmpObjectType();
    private SnmpObjectType oidArg = new SnmpObjectType();
    private SnmpObjectType valueArg = new SnmpObjectType();
    private SnmpObjectType typeArg = new SnmpObjectType();
    private SnmpObjectType sizeArg = new SnmpObjectType();
    private SnmpObjectType ttl = new SnmpObjectType();
    private SnmpObjectType status = new SnmpObjectType();

    public OperEntry() { }

    public OperEntry(SnmpObjectType idOper,
                     SnmpObjectType typeOper,
                     SnmpObjectType operArg1,
                     SnmpObjectType operArg2,
                     SnmpObjectType idSource,
                     SnmpObjectType idDestination,
                     SnmpObjectType oidArg,
                     SnmpObjectType valueArg,
                     SnmpObjectType typeArg,
                     SnmpObjectType sizeArg,
                     SnmpObjectType ttl,
                     SnmpObjectType status) {
        this.idOper = idOper;
        this.typeOper = typeOper;
        this.operArg1 = operArg1;
        this.operArg2 = operArg2;
        this.idSource = idSource;
        this.idDestination = idDestination;
        this.oidArg = oidArg;
        this.valueArg = valueArg;
        this.typeArg = typeArg;
        this.sizeArg = sizeArg;
        this.ttl = ttl;
        this.status = status;
    }

    public SnmpObjectType getIdOper() {
        return idOper;
    }

    public SnmpObjectType getTypeOper() {
        return typeOper;
    }

    public SnmpObjectType getOperArg1() {
        return operArg1;
    }

    public SnmpObjectType getOperArg2() {
        return operArg2;
    }

    public SnmpObjectType getIdSource() {
        return idSource;
    }

    public SnmpObjectType getIdDestination() {
        return idDestination;
    }

    public SnmpObjectType getOidArg() {
        return oidArg;
    }

    public SnmpObjectType getValueArg() {
        return valueArg;
    }

    public SnmpObjectType getTypeArg() {
        return typeArg;
    }

    public SnmpObjectType getSizeArg() {
        return sizeArg;
    }

    public SnmpObjectType getTtl() {
        return ttl;
    }

    public SnmpObjectType getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperEntry operEntry))
            return false;
        return idOper.equals(operEntry.idOper);
    }

    @Override
    public int hashCode() {
        return idOper.hashCode();
    }

    @Override
    public String toString() {
        return "OperEntry{" +
                "\nidOper=" + idOper +
                ", \ntypeOper=" + typeOper +
                ", \noperArg1=" + operArg1 +
                ", \noperArg2=" + operArg2 +
                ", \nidSource=" + idSource +
                ", \nidDestination=" + idDestination +
                ", \noidArg=" + oidArg +
                ", \nvalueArg=" + valueArg +
                ", \ntypeArg=" + typeArg +
                ", \nsizeArg=" + sizeArg +
                ", \nttl=" + ttl +
                ", \nstatus=" + status +
                '}';
    }
}

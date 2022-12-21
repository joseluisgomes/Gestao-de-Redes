package mib;

import snmp.SnmpOID;

import java.util.*;

public class MIBProxy {
    private final String operTableOID = SnmpOID.operTable.getOID();
    private List<OperEntry> operTable = new ArrayList<>();

    public String getOperTableOID() {
        return operTableOID;
    }

    public List<OperEntry> getOperTable() {
        return operTable;
    }

    public void setOperTable(List<OperEntry> operTable) {
        this.operTable = Objects.requireNonNull(operTable);
    }

    public void addEntryToOperTable(OperEntry operEntry) {
        if (operTable.contains(Objects.requireNonNull(operEntry)))
            return;
        operTable.add(Objects.requireNonNull(operEntry));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ((!(o instanceof MIBProxy mibProxy)))
            return false;
        return operTable.equals(mibProxy.operTable);
    }

    @Override
    public int hashCode() {
        return operTable.hashCode();
    }

    @Override
    public String toString() {
        return "MIBProxy{" +
                "operTableOID='" + operTableOID + '\'' +
                ", operTable=" + operTable +
                '}';
    }
}
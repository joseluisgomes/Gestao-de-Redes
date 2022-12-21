package mib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MIBProxy {
    // Key = OID & Value = OBJECT-TYPE
    private Map<String, List<OperEntry>> operTable = new HashMap<>();

    public Map<String, List<OperEntry>> getOperTable() {
        return operTable;
    }

    public void setOperTable(Map<String, List<OperEntry>> operTable) {
        this.operTable = Objects.requireNonNull(operTable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MIBProxy mibProxy))
            return false;
        return operTable.equals(mibProxy.operTable);
    }

    @Override
    public int hashCode() {
        return operTable.hashCode();
    }

    @Override
    public String toString() {
        return "mib.MIBProxy{" +
                "operTable=" + operTable +
                '}';
    }
}
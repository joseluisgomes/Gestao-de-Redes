package mib;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class OperEntry {
    // Key = OID & Value = OBJECT-TYPE
    private Map<String,Integer> idOper = new HashMap<>();
    private Map<String,Integer> typeOper = new HashMap<>();
    private Map<String,Integer> operArg1 = new HashMap<>();
    private Map<String,Integer> operArg2 = new HashMap<>();
    private Map<String,String> idSource = new HashMap<>();
    private Map<String,String> idDestination = new HashMap<>();
    private Map<String,String> oidArg = new HashMap<>();
    private Map<String,Object> valueArg = new HashMap<>();
    private Map<String,Integer> typeArg = new HashMap<>();
    private Map<String,Integer> sizeArg = new HashMap<>();
    private Map<String,Integer> ttl = new HashMap<>();
    private Map<String,Integer> status = new HashMap<>();

    public OperEntry() { }

    public Map<String, Integer> getIdOper() {
        return idOper;
    }

    public void setIdOper(Map<String, Integer> idOper) {
        this.idOper = Objects.requireNonNull(idOper);
    }

    public Map<String, Integer> getTypeOper() {
        return typeOper;
    }

    public void setTypeOper(Map<String, Integer> typeOper) {
        this.typeOper = Objects.requireNonNull(typeOper);
    }

    public Map<String, Integer> getOperArg1() {
        return operArg1;
    }

    public void setOperArg1(Map<String, Integer> operArg1) {
        this.operArg1 = Objects.requireNonNull(operArg1);
    }

    public Map<String, Integer> getOperArg2() {
        return operArg2;
    }

    public void setOperArg2(Map<String, Integer> operArg2) {
        this.operArg2 = Objects.requireNonNull(operArg2);
    }

    public Map<String, String> getIdSource() {
        return idSource;
    }

    public void setIdSource(Map<String, String> idSource) {
        this.idSource = Objects.requireNonNull(idSource);
    }

    public Map<String, String> getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Map<String, String> idDestination) {
        this.idDestination = Objects.requireNonNull(idDestination);
    }

    public Map<String, String> getOidArg() {
        return oidArg;
    }

    public void setOidArg(Map<String, String> oidArg) {
        this.oidArg = Objects.requireNonNull(oidArg);
    }

    public Map<String, Object> getValueArg() {
        return valueArg;
    }

    public void setValueArg(Map<String, Object> valueArg) {
        this.valueArg = Objects.requireNonNull(valueArg);
    }

    public Map<String, Integer> getTypeArg() {
        return typeArg;
    }

    public void setTypeArg(Map<String, Integer> typeArg) {
        this.typeArg = Objects.requireNonNull(typeArg);
    }

    public Map<String, Integer> getSizeArg() {
        return sizeArg;
    }

    public void setSizeArg(Map<String, Integer> sizeArg) {
        this.sizeArg = Objects.requireNonNull(sizeArg);
    }

    public Map<String, Integer> getTTL() {
        return ttl;
    }

    public void setTtl(Map<String, Integer> ttl) {
        this.ttl = Objects.requireNonNull(ttl);
    }

    public Map<String, Integer> getStatus() {
        return status;
    }

    public void setStatus(Map<String, Integer> status) {
        this.status = Objects.requireNonNull(status);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof OperEntry operEntry))
            return false;
        return idOper.equals(operEntry.idOper);
    }

    @Override
    public int hashCode() {
        return idSource.hashCode();
    }

    @Override
    public String toString() {
        return "MIBEntry{" +
                "idOper=" + idOper +
                ", typeOper=" + typeOper +
                ", operArg1=" + operArg1 +
                ", operArg2=" + operArg2 +
                ", idSource=" + idSource +
                ", idDestination=" + idDestination +
                ", oidArg=" + oidArg +
                ", valueArg=" + valueArg +
                ", typeArg=" + typeArg +
                ", sizeArg=" + sizeArg +
                ", ttl=" + ttl +
                ", status=" + status +
                '}';
    }
}

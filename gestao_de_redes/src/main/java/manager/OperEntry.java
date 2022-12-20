package manager;

import java.util.Objects;

public class OperEntry {
    private Integer idOper;
    private Integer typeOper;
    private Integer operArg1;
    private Integer operArg2;
    private String idSource;
    private String idDestination;
    private String oidARg;
    private Object valueArg;
    private Integer typeArg;
    private Integer sizeArg;
    private Integer ttl;
    private Integer status;

    public OperEntry() {
    }

    public OperEntry(OperEntry entry) {
        this.idOper = entry.getIdOper();
        this.typeOper = entry.getTypeOper();
        this.operArg1 = entry.getOperArg1();
        this.operArg2 = entry.getOperArg2();
        this.idSource = entry.getIdSource();
        this.idDestination = entry.getIdDestination();
        this.oidARg = entry.getOidARg();
        this.valueArg = entry.getValueArg();
        this.typeArg = entry.getTypeArg();
        this.sizeArg = entry.getSizeArg();
        this.ttl = entry.getTTL();
        this.status = entry.getStatus();

    }

    public OperEntry(Integer idOper,
                     Integer typeOper,
                     Integer operArg1,
                     Integer operArg2,
                     String idSource,
                     String idDestination,
                     String oidARg,
                     Object valueArg,
                     Integer typeArg,
                     Integer sizeArg,
                     Integer ttl,
                     Integer status) {
        this.idOper = idOper;
        this.typeOper = typeOper;
        this.operArg1 = operArg1;
        this.operArg2 = operArg2;
        this.idSource = idSource;
        this.idDestination = idDestination;
        this.oidARg = oidARg;
        this.valueArg = valueArg;
        this.typeArg = typeArg;
        this.sizeArg = sizeArg;
        this.ttl = ttl;
        this.status = status;
    }

    public Integer getIdOper() {
        return idOper;
    }

    public void setIdOper(Integer idOper) {
        this.idOper = idOper;
    }

    public Integer getTypeOper() {
        return typeOper;
    }

    public void setTypeOper(Integer typeOper) {
        this.typeOper = typeOper;
    }

    public Integer getOperArg1() {
        return operArg1;
    }

    public void setOperArg1(Integer operArg1) {
        this.operArg1 = operArg1;
    }

    public Integer getOperArg2() {
        return operArg2;
    }

    public void setOperArg2(Integer operArg2) {
        this.operArg2 = operArg2;
    }

    public String getIdSource() {
        return idSource;
    }

    public void setIdSource(String idSource) {
        this.idSource = idSource;
    }

    public String getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(String idDestination) {
        this.idDestination = idDestination;
    }

    public String getOidARg() {
        return oidARg;
    }

    public void setOidARg(String oidARg) {
        this.oidARg = oidARg;
    }

    public Object getValueArg() {
        return valueArg;
    }

    public void setValueArg(Object valueArg) {
        this.valueArg = valueArg;
    }

    public Integer getTypeArg() {
        return typeArg;
    }

    public void setTypeArg(Integer typeArg) {
        this.typeArg = typeArg;
    }

    public Integer getSizeArg() {
        return sizeArg;
    }

    public void setSizeArg(Integer sizeArg) {
        this.sizeArg = sizeArg;
    }

    public Integer getTTL() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void decreaseTTL() {
        ttl--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperEntry operEntry = (OperEntry) o;
        return idOper.equals(operEntry.idOper) && typeOper.equals(operEntry.typeOper)
                && operArg1.equals(operEntry.operArg1) &&
                operArg2.equals(operEntry.operArg2) &&
                idSource.equals(operEntry.idSource) &&
                idDestination.equals(operEntry.idDestination) &&
                oidARg.equals(operEntry.oidARg) &&
                valueArg.equals(operEntry.valueArg) &&
                typeArg.equals(operEntry.typeArg) &&
                sizeArg.equals(operEntry.sizeArg) &&
                ttl.equals(operEntry.ttl) &&
                status.equals(operEntry.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOper,
                typeOper,
                operArg1,
                operArg2,
                idSource,
                idDestination,
                oidARg,
                valueArg,
                typeArg,
                sizeArg,
                ttl,
                status);
    }

    @Override
    public String toString() {
        return "manager.OperEntry{" +
                "idOper=" + idOper +
                ", typeOper=" + typeOper +
                ", operArg1=" + operArg1 +
                ", operArg2=" + operArg2 +
                ", idSource='" + idSource + '\'' +
                ", idDestination='" + idDestination + '\'' +
                ", oidARg='" + oidARg + '\'' +
                ", valueArg=" + valueArg +
                ", typeArg=" + typeArg +
                ", sizeArg=" + sizeArg +
                ", ttl=" + ttl +
                ", status=" + status +
                '}';
    }
}

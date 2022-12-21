package snmp;

public class SnmpObjectType {
    private String objectOID;
    private Object objectValue;

    public SnmpObjectType() {
    }

    public SnmpObjectType(String oid, Object objectValue) {
        objectOID = oid;
        this.objectValue = objectValue;
    }

    public String getObjectOID() {
        return objectOID;
    }

    public Object getObjectValue() {
        return objectValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof SnmpObjectType that))
            return false;
        return objectOID.equals(that.objectOID);
    }

    @Override
    public int hashCode() {
        return objectOID.hashCode();
    }

    @Override
    public String toString() {
        return "SnmpObjectType{" +
                "objectOID='" + objectOID + '\'' +
                ", objectValue=" + objectValue +
                '}';
    }
}

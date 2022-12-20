package mib;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MIBProxy {
    private List<OperEntry> operTable;
    private static final String MIBPATH = "/home/luis/GitHub/UM/Gestao-de-Redes/gestao_de_redes/";

    public MIBProxy() {
        this.operTable = new ArrayList<>();
    }

    public MIBProxy(List<OperEntry> operEntries) {
        this.operTable = operEntries;
    }

    public void setOperEntry(OperEntry operEntry, Integer entryId) {
        operTable.set(entryId, Objects.requireNonNull(operEntry));
    }

    public OperEntry getOperEntry(int index) {
        return operTable.get(index);
    }

    public List<OperEntry> getOperTable() {
        return operTable;
    }

    public void removeOperEntry(Integer id) {
        operTable.remove(id-1);
    }

    public void removeOperTable(OperEntry apagarOperEntry){
        operTable.remove(apagarOperEntry.getIdOper()-1);

        for(int i = 0; i < operTable.size(); i++)
            operTable.get(i).setIdOper(i+1);
    }

    public int search(OperEntry a){
        return operTable.indexOf(a);
    }

    public void saveOperTable(String mib) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File json = new File(MIBPATH + mib);
        MIBProxy mibProxy = new MIBProxy(operTable);

        mapper.defaultPrettyPrintingWriter().writeValue(json,mibProxy);
    }

    public void loadOperTable(String mibProxyJson) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        File json = new File(MIBPATH + mibProxyJson) ;

        MIBProxy mibProxy = mapper.readValue(json, MIBProxy.class);
        operTable = new ArrayList<OperEntry>(mibProxy.getOperTable());
    }

    public static void searchEntryByOperType(Integer entryOperType, ArrayList<OperEntry> operTable){
        OperEntry aux = new OperEntry(); // Auxiliary entry

        for(OperEntry entry : operTable)
            if(entry.getTypeOper().equals(entryOperType))
                aux = new OperEntry(Objects.requireNonNull(entry));

        if(aux.getTypeOper() != null)
            System.out.println("Entry pretended : " + aux);
        else
            System.out.println("There's no entries with the given operation type!");
    }
}

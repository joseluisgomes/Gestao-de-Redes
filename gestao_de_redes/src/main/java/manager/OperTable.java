package manager;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OperTable {
    private List<OperEntry> operTable;
    private static final String MIB_PATH = "/home/luis/GitHub/UM/Gestao-de-Redes/gestao_de_redes/";

    public OperTable() {
        this.operTable = new ArrayList<>();
    }

    public OperTable(List<OperEntry> operEntries) {
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
        File json = new File(MIB_PATH + mib);

        OperTable operTable = new OperTable(this.operTable);
        mapper.defaultPrettyPrintingWriter().writeValue(json, operTable);
    }

    public void loadOperTable(String mibProxyJson) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        File json = new File(MIB_PATH + mibProxyJson) ;

        OperTable operTable = mapper.readValue(json, OperTable.class);
        this.operTable = new ArrayList<>(operTable.getOperTable());
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

package manager;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class SnmpManager { // Snmp Client
    public OID[] operTableOID = { new OID(new int[] {1,3,6,1,4,1,1,1,1}), }; // operTable's OID
    public OID[] operEntryOID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,1}), }; // idOper's OID
    OID[] typeOperOID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,2}), }; // typeOper's OID
    OID[] operArg1OID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,3}), }; // operArg1's OID
    OID[] operArg2OID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,4}), }; // operArg2's OID
    OID[] idSourceOID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,5}), }; // idSource's OID
    OID[] idDestinationOID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,6}), }; // idDestination's OID
    OID[] oidArgOID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,7}), }; // oidArgOID's OID
    OID[] valueArgOID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,8}), }; // valueArg's OID
    OID[] typeArgOID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,9}), }; // typeArg's OID
    OID[] sizeArgOID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,10}), }; // sizeArg's OID
    OID[] ttlOID = { new OID(new int[] {1,3,6,1,4,1,1,1,1,11}), }; // ttl's OID
    OID[] status = { new OID(new int[] {1,3,6,1,4,1,1,1,1,12}), }; // status's OID


    public List<VariableBinding[]> snmpGetBulk(OID[] OIDsProcesses) throws IOException { // snmpgetbulk
        DefaultPDUFactory pduFactory = new DefaultPDUFactory(PDU.GETBULK);

        Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
        snmp.listen(); // open port to receive response

        TreeUtils tree = new TreeUtils(snmp, pduFactory);
        tree.setMaxRepetitions(100); // 100 is the max

        List<TreeEvent> listWalk = tree.walk(getTargetForWrite(), OIDsProcesses);
        List<VariableBinding[]> variableBindings = new ArrayList<>(listWalk.size());

        int errorStatus;
        for (TreeEvent treeEvent : listWalk) {
            errorStatus = treeEvent.getStatus();
            if (errorStatus == PDU.noError)  // check for errors
                variableBindings.add(treeEvent.getVariableBindings()); // copying the results to a data collection that can be manipulated later
            else
                System.out.println("error: " +errorStatus +"\n");
        }
        snmp.close();
        return variableBindings;
    }

    public TreeMap<Integer,String> variableBindingsToTreeMap(List<VariableBinding[]> vbs) { //transforma o list de variableBinding  num treemap
        TreeMap <Integer,String> array = new TreeMap<>();

        for (VariableBinding[] vba : vbs)
            for (VariableBinding vb : vba) {
                Integer pid = vb.getOid().last();
                array.put(pid,vb.toValueString());
            }
        return array;
    }


    public void searchEvent(String nome) throws IOException {
        TreeMap<Integer,String> nomes = new TreeMap<>(variableBindingsToTreeMap(snmpGetBulk(typeOperOID)));
        TreeMap<Integer,String> duracao = new TreeMap<>(variableBindingsToTreeMap(snmpGetBulk(operArg1OID)));
        TreeMap<Integer,String> deltaT = new TreeMap<>(variableBindingsToTreeMap(snmpGetBulk(operArg2OID)));
        TreeMap<Integer,String> dataLimite = new TreeMap<>(variableBindingsToTreeMap(snmpGetBulk(idSourceOID)));

        int key;

        for(Integer i : nomes.keySet()){
            String aux = nomes.get(i);
            if(aux.equals(nome)){
                key = i;
                System.out.println("ID: "+key
                        + "\nNome: " + nomes.get(key)+
                        "\nDuração: " + duracao.get(key)
                        + "\nIntervalo de tempo que falta/já passou para a data de um evento: "+ deltaT.get(key)
                        + "\nData limite da duração de um evento: "+ dataLimite.get(key));
                break;
            }
            else{
                System.out.println("O evento não existe");
            }
        }
    }

    private Target getTargetForWrite() {
        CommunityTarget target = new CommunityTarget();

        target.setCommunity(new OctetString("public")); //verificar se tem de ser public ou private
        target.setVersion(SnmpConstants.version2c);
        target.setAddress(GenericAddress.parse("127.0.0.1/3003"));
        target.setRetries(2);
        target.setTimeout(1500);
        return target;
    }
}

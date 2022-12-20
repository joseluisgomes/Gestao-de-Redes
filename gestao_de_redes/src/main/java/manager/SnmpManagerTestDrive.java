package manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class SnmpManagerTestDrive {
    public static void main(String[] args) throws IOException {
        SnmpManager snmpManager = new SnmpManager();
        List<OperEntry> evs = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        TreeMap<Integer,String> operEntries =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.operEntryOID)));
        TreeMap<Integer,String> operationsType =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.typeOperOID)));
        TreeMap<Integer,String> firstOperArguments =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.operArg1OID)));
        TreeMap<Integer,String> secondOperArguments =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.operArg2OID)));
        TreeMap<Integer,String> sourceIDs =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.idSourceOID)));
        TreeMap<Integer,String> destinationIDs =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.idDestinationOID)));
        TreeMap<Integer,String> objectOIDs =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.oidArgOID)));
        TreeMap<Integer,Object> valueOIDs =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.valueArgOID)));
        TreeMap<Integer,String> argumentTypesOIDs =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.typeArgOID)));
        TreeMap<Integer,String> argumentSizesOIDs =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.sizeArgOID)));
        TreeMap<Integer,String> ttlOIDs =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.ttlOID)));
        TreeMap<Integer,String> statusOIDs =
                new TreeMap<>(snmpManager.variableBindingsToTreeMap(snmpManager.snmpGetBulk(snmpManager.status)));


        operEntries.forEach((key, value) -> {
            OperEntry openEntry = new OperEntry();

            openEntry.setIdOper(Integer.parseInt(value));
            openEntry.setTypeOper(Integer.valueOf(operationsType.get(key)));
            openEntry.setOperArg1((Integer.parseInt(firstOperArguments.get(key))));
            openEntry.setOperArg1((Integer.parseInt(secondOperArguments.get(key))));

            openEntry.setIdSource(sourceIDs.get(key));
            openEntry.setIdDestination(destinationIDs.get(key));
            openEntry.setOidARg(objectOIDs.get(key));
            openEntry.setValueArg(valueOIDs.get(key));

            openEntry.setTypeArg(Integer.parseInt(argumentTypesOIDs.get(key)));
            openEntry.setSizeArg(Integer.parseInt(argumentSizesOIDs.get(key)));
            openEntry.setTTL(Integer.parseInt(ttlOIDs.get(key)));
            openEntry.setStatus(Integer.parseInt(statusOIDs.get(key)));

            evs.add(openEntry);
        });

        System.out.println("Bem vindo ao Gestor de OperEntrys!");
        boolean exit = false;
        while(exit == false){
            System.out.println("O que deseja fazer?");
            System.out.println("1 -> Procurar OperEntrys por nome.");
            System.out.println("2 -> Listar OperEntrys já terminados.");
            System.out.println("3 -> Listar OperEntrys em curso.");
            System.out.println("4 -> Listar OperEntrys ainda por vir.");
            System.out.println("5 -> Sair;");
            int opt = sc.nextInt();
            switch(opt){
                case 1:
                    System.out.println("Qual o nome do OperEntry desejado?");
                    Scanner scanner = new Scanner(System.in);
                    String name = scanner.nextLine();
                    System.out.println("Nome do OperEntry a procurar: " + name);
             //       OperEntrys.searchByName(name, evs);
                    break;
                case 2:
                    System.out.println("Lista de OperEntrys terminados:");
            //        OperEntrys.searchPastEvents(evs);
                    break;
                case 3:
                    System.out.println("Lista de OperEntrys em curso:");
            //        OperEntrys.searchCurrentEvents(evs);
                    break;
                case 4:
                    System.out.println("Lista de OperEntrys ainda por vir:");
            //        OperEntrys.searchFutureEvents(evs);
                    break;
                default:
                    break;
            }
        }
    }
}

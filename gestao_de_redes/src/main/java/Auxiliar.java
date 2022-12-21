import mib.MIBProxy;
import mib.MIBProxy;
import mib.OperEntry;
import snmp.SnmpMessage;
import snmp.SnmpOID;
import snmp.SnmpObjectType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Objects;
import mib.MIBProxy;
import mib.OperEntry;
import snmp.SnmpMessage;
import snmp.SnmpOID;
import snmp.SnmpObjectType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Objects;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Auxiliar extends Thread {

    int clientNo;
    Socket serverClient;
    int x = 0;

    Auxiliar(Socket inSocket, int counter) {
        serverClient = inSocket;
        clientNo = counter;
    }

    public void run() {
        try {
            final MIBProxy mibProxy = new MIBProxy();
            final BufferedReader serverReader = new BufferedReader(new InputStreamReader(serverClient.getInputStream()));

            String snmpCommand;
            while (!(snmpCommand = serverReader.readLine()).equals("exit")) {
                // Executes the given (by the manager) command
                final Process process = Runtime.getRuntime().exec(snmpCommand);
                final BufferedReader processReader = // Read the command's output
                        new BufferedReader(new InputStreamReader(process.getInputStream()));

                // Print the command's output
                String snmpCommandOutput;
                StringBuilder output = new StringBuilder();
                while ((snmpCommandOutput = processReader.readLine()) != null) {
                    System.out.println(snmpCommandOutput);
                    output.append(snmpCommandOutput);
                }
                processReader.close();

                // Update MIB Proxy
                mibProxy.addEntryToOperTable(parseSnmpCommand(snmpCommand, output.toString()));
                System.out.println(mibProxy);
            }

            // close connection
            serverReader.close();


            System.exit(0); // terminate application

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static OperEntry parseSnmpCommand(String snmpCommand, String snmpCommandOutput) {
        final String[] snmpCommandSplitted = Objects.requireNonNull(snmpCommand)
                .split(" ");

        // example: snmpget -v2c -c gr2020 localhost system.sysName.0
        return new OperEntry(
                new SnmpObjectType(SnmpOID.idOper.getOID(), 1),
                new SnmpObjectType(SnmpOID.typeOper.getOID(), SnmpMessage.GetRequest.getOperationType()),
                new SnmpObjectType(SnmpOID.operArg1.getOID(), "-v2c"),
                new SnmpObjectType(SnmpOID.operArg2.getOID(), "-c gr2020"),
                new SnmpObjectType(SnmpOID.idSource.getOID(), "localhost"),
                new SnmpObjectType(SnmpOID.idDestination.getOID(), "system.sysName.0"),
                new SnmpObjectType(SnmpOID.oidArg.getOID(), SnmpOID.idDestination.getOID()),
                new SnmpObjectType(SnmpOID.valueArg.getOID(), snmpCommandOutput),
                new SnmpObjectType(SnmpOID.typeArg.getOID(), SnmpObjectType.class),
                new SnmpObjectType(SnmpOID.sizeArg.getOID(), 32),
                new SnmpObjectType(SnmpOID.ttl.getOID(), LocalDateTime.now()),
                new SnmpObjectType(SnmpOID.status.getOID(), "OK")
        );
    }
}

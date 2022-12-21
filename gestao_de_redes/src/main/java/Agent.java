import mib.MIBProxy;
import mib.OperEntry;
import snmp.SnmpMessage;
import snmp.SnmpOID;
import snmp.SnmpObjectType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Random;

class Agent { // Snmp Server

    public static void main(String[] args) throws Exception {
        final MIBProxy mibProxy = new MIBProxy();

        final ServerSocket server = new ServerSocket(5000);
        final Socket request = server.accept();
        System.out.println("Connection established");

        final BufferedReader serverReader =  // To read data from the manager
                new BufferedReader(new InputStreamReader(request.getInputStream()));

        while (true) {
            String snmpCommand;
            while (!(snmpCommand = serverReader.readLine()).equals("exit")) {
                // Executes the given (by the manager) command
                final Process process = Runtime.getRuntime().exec(snmpCommand);
                final BufferedReader processReader = // Read the command's output
                        new BufferedReader(new InputStreamReader(process.getInputStream()));

                // Update MIB PROXY


                // Print the command's output
                String snmpCommandOutput;
                while ((snmpCommandOutput = processReader.readLine()) != null)
                    System.out.println(snmpCommandOutput);
                processReader.close();
            }

            // close connection
            serverReader.close();
            server.close();
            request.close();

            System.exit(0); // terminate application
        }
    }

    private OperEntry parseSnmpCommand(String snmpCommand, String snmpCommandOutput) {
        final String[] snmpCommandSplitted = Objects.requireNonNull(snmpCommand)
                .split(" ");

        // example: snmpget -v2c -c gr2020 localhost system.sysName.0
        return new OperEntry(
                new SnmpObjectType(SnmpOID.idOper.getOID(), 1),
                new SnmpObjectType(SnmpOID.typeOper.getOID(),SnmpMessage.GetRequest.getOperationType()),
                new SnmpObjectType(SnmpOID.operArg1.getOID(), "-v2c"),
                new SnmpObjectType(SnmpOID.operArg2.getOID(), "-c gr2020"),
                new SnmpObjectType(SnmpOID.idSource.getOID(), "localhost"),
                new SnmpObjectType(SnmpOID.idDestination.getOID(), "system.sysName.0"),
                new SnmpObjectType(SnmpOID.oidArg.getOID(), SnmpOID.idDestination.getOID()),
                new SnmpObjectType(SnmpOID.valueArg.getOID(), snmpCommandOutput),

        )
    }
}

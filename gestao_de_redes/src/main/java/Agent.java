import cryptography.CipherAES;
import mib.MIBProxy;
import mib.OperEntry;
import snmp.SnmpMessage;
import snmp.SnmpOID;
import snmp.SnmpObjectType;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

class Agent { // Snmp Server
    private static final int PORT = 5000;

    public static void main(String[] args) throws Exception {
        final MIBProxy mibProxy = new MIBProxy();

        final ServerSocket server = new ServerSocket(PORT);
        final Socket requestSocket = server.accept();
        System.out.println("Connection established");

        final DataInputStream reqSocketInputStream =  // Request Socket's input stream
                new DataInputStream(requestSocket.getInputStream());

        while (true) {
            final int requestLength = reqSocketInputStream.readInt();
            final byte[] encRequest = new byte[requestLength]; // Encrypted Request

            System.out
                    .println("Received " + reqSocketInputStream.read(encRequest) + " bytes from Manager");

            final String request = CipherAES.decrypt(encRequest); // Decrypt the request
            if (!request.equals("exit")) {
                // Perform the request
                final Process process = Runtime.getRuntime().exec(request);
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
                mibProxy.addEntryToOperTable(parseSnmpCommand(request, output.toString()));
                System.out.println(mibProxy);
            } else {
                // close connection
                reqSocketInputStream.close();
                server.close();
                requestSocket.close();

                System.exit(0); // terminate application
            }
        }
    }

    private static OperEntry parseSnmpCommand(String snmpCommand, String snmpCommandOutput) {
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
                new SnmpObjectType(SnmpOID.typeArg.getOID(), SnmpObjectType.class),
                new SnmpObjectType(SnmpOID.sizeArg.getOID(), 32),
                new SnmpObjectType(SnmpOID.ttl.getOID(), LocalDateTime.now()),
                new SnmpObjectType(SnmpOID.status.getOID(), "OK")
        );
    }
}

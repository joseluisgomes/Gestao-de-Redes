import cryptography.CipherAES;
import mib.MIBProxy;
import mib.OperEntry;
import mib.StatusCodes;
import snmp.SnmpMessage;
import snmp.SnmpObjectType;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Objects;

import static cryptography.CipherAES.encrypt;
import static java.time.LocalTime.now;
import static mib.StatusCodes.Accepted;
import static mib.StatusCodes.OK;
import static snmp.SnmpMessage.*;
import static snmp.SnmpOID.*;

class Agent { // Snmp Server
    private static final int PORT = 5000;

    public static void main(String[] args) throws Exception {
        final MIBProxy mibProxy = new MIBProxy();

        final ServerSocket server = new ServerSocket(PORT);
        final Socket requestSocket = server.accept();
        System.out.println("Connection established");

        final DataInputStream reqSocketInputStream =  // Request Socket's input stream
                new DataInputStream(requestSocket.getInputStream());

        int operEntryID = 0;
        while (true) {
            final int requestLength = reqSocketInputStream.readInt();
            final byte[] encRequest = new byte[requestLength]; // Encrypted Request

            System.out
                    .println("Received " + reqSocketInputStream.read(encRequest) + " bytes from Manager\n");

            final String request = CipherAES.decrypt(encRequest); // Decrypt the request
            if (!request.equals("exit")) {
                // Perform the request
                final Process process = Runtime.getRuntime().exec(request);
                final BufferedReader processReader = // Read the command's output
                        new BufferedReader(new InputStreamReader(process.getInputStream()));

                // Print the command's output
                System.out.println("Request's response:");

                String snmpCommandOutput;
                StringBuilder output = new StringBuilder();
                while ((snmpCommandOutput = processReader.readLine()) != null) {
                    System.out.println(snmpCommandOutput);
                    output.append(snmpCommandOutput);
                }
                processReader.close();

                // Update MIB Proxy
                mibProxy.addEntryToOperTable(
                        parseSnmpCommand(request, output.toString(), operEntryID)
                );

                // Print the MIB's content
                System.out.println("\n-------------------------" + " MIB's Content " + "-----------------------------");
                System.out.println(mibProxy);
                System.out.println("-------------------------------------------------------------------------");

                ++operEntryID;
            } else {
                // close connection
                reqSocketInputStream.close();
                server.close();
                requestSocket.close();

                System.exit(0); // terminate application
            }
        }
    }

    private static OperEntry parseSnmpCommand(String snmpCommand,
                                              String snmpCommandOutput,
                                              int counter) throws Exception {
        final String[] snmpCommandSplitted = Objects.requireNonNull(snmpCommand)
                .split(" ");

        // Determine the #Operations
        final var lastIndexOf = snmpCommand.lastIndexOf('-') + 3;

        final String[] arguments = snmpCommand
                .substring(lastIndexOf)
                .split(" ");

        final String firstArgument = arguments[1];
        final StringBuilder secondArgument = new StringBuilder();

        for (int i = 2; i < arguments.length; i++)
            secondArgument.append(arguments[i]).append(" ");

        // 1st string is the snmp message
        final var snmpMessage = snmpCommandSplitted[0];
        switch (snmpMessage) {
            case "snmpget" -> { // GetRequest
                return new OperEntry(
                        new SnmpObjectType(idOper.getOID(), counter),
                        new SnmpObjectType(typeOper.getOID(), GetRequest.getOperationType()),
                        new SnmpObjectType(operArg1.getOID(), encrypt(firstArgument)),
                        new SnmpObjectType(operArg2.getOID(), encrypt(secondArgument.toString())),
                        new SnmpObjectType(idSource.getOID(), firstArgument),
                        new SnmpObjectType(idDestination.getOID(), secondArgument.toString()),
                        new SnmpObjectType(oidArg.getOID(), idDestination.getOID()),
                        new SnmpObjectType(valueArg.getOID(), snmpCommandOutput),
                        new SnmpObjectType(typeArg.getOID(), String.class),
                        new SnmpObjectType(sizeArg.getOID(), snmpCommand.getBytes().length),
                        new SnmpObjectType(ttl.getOID(), now()),
                        new SnmpObjectType(status.getOID(), OK.getCode())
                );
            }
            case "snmpgetnext" -> { // GetNextRequest
                return new OperEntry(
                        new SnmpObjectType(idOper.getOID(), counter),
                        new SnmpObjectType(typeOper.getOID(), GetNextRequest.getOperationType()),
                        new SnmpObjectType(operArg1.getOID(), encrypt(firstArgument)),
                        new SnmpObjectType(operArg2.getOID(), encrypt(secondArgument.toString())),
                        new SnmpObjectType(idSource.getOID(), firstArgument),
                        new SnmpObjectType(idDestination.getOID(), secondArgument.toString()),
                        new SnmpObjectType(oidArg.getOID(), idDestination.getOID()),
                        new SnmpObjectType(valueArg.getOID(), snmpCommandOutput),
                        new SnmpObjectType(typeArg.getOID(), String.class),
                        new SnmpObjectType(sizeArg.getOID(), snmpCommand.getBytes().length),
                        new SnmpObjectType(ttl.getOID(), LocalDateTime.now()),
                        new SnmpObjectType(status.getOID(), OK.getCode())
                );
            }
            case "snmpbulkget" -> { // GetBulkRequest
                return new OperEntry(
                        new SnmpObjectType(idOper.getOID(), counter),
                        new SnmpObjectType(typeOper.getOID(), GetBulkRequest.getOperationType()),
                        new SnmpObjectType(operArg1.getOID(), encrypt(firstArgument)),
                        new SnmpObjectType(operArg2.getOID(), encrypt(secondArgument.toString())),
                        new SnmpObjectType(idSource.getOID(), firstArgument),
                        new SnmpObjectType(idDestination.getOID(), secondArgument.toString()),
                        new SnmpObjectType(oidArg.getOID(), idDestination.getOID()),
                        new SnmpObjectType(valueArg.getOID(), snmpCommandOutput),
                        new SnmpObjectType(typeArg.getOID(), String.class),
                        new SnmpObjectType(sizeArg.getOID(), snmpCommand.getBytes().length),
                        new SnmpObjectType(ttl.getOID(), LocalDateTime.now()),
                        new SnmpObjectType(status.getOID(), OK.getCode())
                );
            }
            case "snmpset" -> { // SetRequest
                return new OperEntry(
                        new SnmpObjectType(idOper.getOID(), counter),
                        new SnmpObjectType(typeOper.getOID(), SetRequest.getOperationType()),
                        new SnmpObjectType(operArg1.getOID(), encrypt(firstArgument)),
                        new SnmpObjectType(operArg2.getOID(), encrypt(secondArgument.toString())),
                        new SnmpObjectType(idSource.getOID(), firstArgument),
                        new SnmpObjectType(idDestination.getOID(), secondArgument.toString()),
                        new SnmpObjectType(oidArg.getOID(), idDestination.getOID()),
                        new SnmpObjectType(valueArg.getOID(), snmpCommandOutput),
                        new SnmpObjectType(typeArg.getOID(), String.class),
                        new SnmpObjectType(sizeArg.getOID(), snmpCommand.getBytes().length),
                        new SnmpObjectType(ttl.getOID(), LocalDateTime.now()),
                        new SnmpObjectType(status.getOID(), Accepted.getCode())
                );
            }
            case "snmpwalk" -> { // Snmpwalk
                return new OperEntry(
                        new SnmpObjectType(idOper.getOID(), counter),
                        new SnmpObjectType(typeOper.getOID(), GetNextRequest.getOperationType()),
                        new SnmpObjectType(operArg1.getOID(), encrypt(firstArgument)),
                        new SnmpObjectType(operArg2.getOID(), encrypt(secondArgument.toString())),
                        new SnmpObjectType(idSource.getOID(), firstArgument),
                        new SnmpObjectType(idDestination.getOID(), secondArgument.toString()),
                        new SnmpObjectType(oidArg.getOID(), idDestination.getOID()),
                        new SnmpObjectType(valueArg.getOID(), snmpCommandOutput),
                        new SnmpObjectType(typeArg.getOID(), String.class),
                        new SnmpObjectType(sizeArg.getOID(), snmpCommand.getBytes().length),
                        new SnmpObjectType(ttl.getOID(), now()),
                        new SnmpObjectType(status.getOID(), OK.getCode())
                );
            }
            default -> { return null; }
        }
    }
}

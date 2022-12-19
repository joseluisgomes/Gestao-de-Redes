// Server2 class that
// receives data and sends data

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Agent { // Snmp Server

    public static void main(String args[]) throws Exception {
        // Create server Socket
        ServerSocket ss = new ServerSocket(5000);

        // connect it to client socket
        Socket s = ss.accept();
        System.out.println("Connection established");

        // to send data to the client
        PrintStream ps = new PrintStream(s.getOutputStream());

        // to read data coming from the client
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        List<String> snmpCommands = new ArrayList<>();

        // server executes continuously
        while (true) {
            String snmpCommand;

            // repeat as long as the client
            // does not send a null string

            // read from client
            while ((snmpCommand = br.readLine()) != null) {
                System.out.println(snmpCommand);
                snmpCommands.add(snmpCommand);
            }

            final String[] snmpCommandsArray = Arrays
                    .copyOf(snmpCommands.toArray(), snmpCommands.size(),String[].class);
            Runtime.getRuntime().exec(snmpCommandsArray);

            // close connection
            ps.close();
            br.close();
            ss.close();
            s.close();

            // terminate application
            System.exit(0);

        } // end of while
    }
}

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Agent { // Snmp Server

    public static void main(String[] args) throws Exception {
        final ServerSocket ss = new ServerSocket(5000);
        final Socket s = ss.accept();

        System.out.println("Connection established");

        // To read data from the manager
        final BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        final List<String> snmpCommands = new ArrayList<>();

        while (true) {
            String snmpCommand;
            while ((snmpCommand = br.readLine()) != null) {
                snmpCommands.add(snmpCommand);
                System.out.println(snmpCommand);
            }

            final String[] commands = Arrays // SnmpCommands list parsed to an array
                    .copyOf(snmpCommands.toArray(), snmpCommands.size(),String[].class);

            // Runtime.getRuntime().exec(commands); TODO: For the virtual machine

            // close connection
            br.close();
            ss.close();
            s.close();

            System.exit(0); // terminate application
        }
    }
}

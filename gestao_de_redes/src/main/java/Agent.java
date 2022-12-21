import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Agent { // Snmp Server

    public static void main(String[] args) throws Exception {
        final ServerSocket ss = new ServerSocket(5000);
        final Socket s = ss.accept();

        System.out.println("Connection established");

        // To read data from the manager
        final BufferedReader serverReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        while (true) {
            String snmpCommand;
            while (!(snmpCommand = serverReader.readLine()).equals("exit")) {
                final Process process = Runtime.getRuntime().exec(snmpCommand); // Validates the command
                final BufferedReader processReader = // Read the command's output
                        new BufferedReader(new InputStreamReader(process.getInputStream()));

                String snmpCommandOutput;
                while ((snmpCommandOutput = processReader.readLine()) != null)
                    System.out.println(snmpCommandOutput);
                processReader.close();
            }

            // close connection
            serverReader.close();
            ss.close();
            s.close();

            System.exit(0); // terminate application
        }
    }
}

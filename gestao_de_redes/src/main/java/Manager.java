import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Manager { // Snmp Client
    public static void main(String[] args) {
        try (final var client = new Socket("localhost", 5000);
             final var stdinReader =
                     new BufferedReader(new InputStreamReader(System.in));
             final var socketReader =
                     new BufferedReader(new InputStreamReader(client.getInputStream()));
             final var socketWriter = new PrintWriter(client.getOutputStream(), true)
        ) {
            String snmpCommand;
            while ((snmpCommand = stdinReader.readLine()) != null) {
                System.out.println("Command: " + snmpCommand);
                socketWriter.println(snmpCommand);

                String commandOutput = socketReader.readLine();
                System.out.println("Command output: " + commandOutput);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
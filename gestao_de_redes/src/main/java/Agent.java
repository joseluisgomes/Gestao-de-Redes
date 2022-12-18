import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Agent { // Snmp Server
    public static void main(String[] args) {
        int numOfManagers = 0;

        try (final var server = new ServerSocket(5000)) {
            while (true) {
                final var requestSocket = server.accept();
                numOfManagers++;

                final var requestThread = new Thread(new SnmpRunnable(requestSocket));
                requestThread.setName("Thread " + numOfManagers);
                requestThread.start();

                final var reader = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
                String snmpCommand;
                while ((snmpCommand = reader.readLine()) != null)
                    System.out.println(snmpCommand);

        
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

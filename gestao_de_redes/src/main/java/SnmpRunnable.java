import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SnmpRunnable implements Runnable {
    private final Socket socket;
    private final List<String> snmpCommands = new ArrayList<>();

    public SnmpRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter out =
                     new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in =
                     new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String managerInput;

            while ((managerInput = in.readLine()) != null)
                snmpCommands.add(managerInput);

            /*
                Write on the socket's output stream each command
                the manager sent to the agent.
             */
            snmpCommands.forEach(out::println);
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public List<String> getSnmpCommands() {
        return snmpCommands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SnmpRunnable that)) return false;
        return socket.equals(that.socket) && snmpCommands.equals(that.snmpCommands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket, snmpCommands);
    }

    @Override
    public String toString() {
        return "SnmpRunnable{" +
                "socket=" + socket +
                ", snmpCommands=" + snmpCommands +
                '}';
    }
}

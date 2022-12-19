// Client2 class that
// sends data and receives also

import java.io.*;
import java.net.*;

class Manager { // Snmp Client

    public static void main(String args[]) throws Exception {

        // Create client socket
        Socket s = new Socket("localhost", 5000);

        // to send data to the server
        DataOutputStream send = new DataOutputStream(s.getOutputStream());

        // to read data from the keyboard
        BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in));

        String string, string1;

        // repeat as long as exit
        // is not typed at client
        while (!(string = readInput.readLine()).equals("exit"))
            // send to the server
            send.writeBytes(string + "\n");

        // close connection.
        send.close();
        readInput.close();
        s.close();
    }
}

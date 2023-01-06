import cryptography.CipherAES;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class Manager { // Snmp Client
    private static final int PORT = 5000;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws Exception {
        final Socket s = new Socket(HOST, PORT); // Create client socket

        final DataOutputStream send = new DataOutputStream(s.getOutputStream()); // to send data to the server
        final BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); // to read data from the keyboard

        System.out.println("Welcome! You can perform any snmp command.");

        String userInput = "";
        while (!userInput.equals("exit")) {
            System.out.print("Command: ");
            userInput = readInput.readLine();

            final byte[] request = CipherAES.encrypt(userInput);

            // Cryptogram sent = text's size + textEncrypted
            send.writeInt(request.length);
            send.write(request); // Send the request to Agent Proxy
            send.flush();
        }

        // close connection.
        send.close();
        readInput.close();
        s.close();
    }
}

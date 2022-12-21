import java.io.*;
import java.net.*;

class Manager { // Snmp Client


    public static void main(String[] args) throws Exception {
        final Socket s = new Socket("localhost", 5000); // Create client socket

        final DataOutputStream send = new DataOutputStream(s.getOutputStream()); // to send data to the server
        final BufferedReader readInput = new BufferedReader(new InputStreamReader(System.in)); // to read data from the keyboard

        String userInput = "";
        while (!userInput.equals("exit")) {
            System.out.print("Command: ");
            userInput = readInput.readLine();

            send.writeBytes(userInput + "\n"); // send to the server
        }

        // close connection.
        send.close();
        readInput.close();
        s.close();
    }
}

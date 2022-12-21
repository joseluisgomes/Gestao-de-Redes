import mib.MIBProxy;
import mib.OperEntry;
import snmp.SnmpMessage;
import snmp.SnmpOID;
import snmp.SnmpObjectType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Objects;
import mib.MIBProxy;
import mib.OperEntry;
import snmp.SnmpMessage;
import snmp.SnmpOID;
import snmp.SnmpObjectType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Objects;

public class AgentApagar {


        public static void main(String[] args) throws Exception {

            int counter = 0;


            final ServerSocket server = new ServerSocket(5000);
            System.out.println("Listening for connections on port 8080...");



            while (true) {

                System.out.println("Ready");
                Socket c = server.accept();
                counter++;


                System.out.println("Client accepted");
                Auxiliar sct = new Auxiliar(c,counter); //send  the request to a separate thread
                sct.start();

            }
        }


    }



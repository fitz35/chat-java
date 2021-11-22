package server;

import server.config.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ServerThread extends Thread
{

    private Socket clientSocket;
   // private Date date;
    private Room room;
    private ClientBack client;



    public ServerThread(Socket clientSocket )
    {
        this.clientSocket=clientSocket;
    }



    public void run()
    {
        try {
            PrintStream socOut = null;
           BufferedReader socIn = null;

            socIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            socOut= new PrintStream(clientSocket.getOutputStream());
            String line= socIn.readLine();
            String[] details= line.split("/");
            String roomName= details[1];
             room=Server.IdentifyRoom(roomName);
            boolean clientDoesNotExist= room.identifyClient(details[0]);
            if(!clientDoesNotExist)
            {
                System.out.println("Client exists already in this room");
                socOut.println(Config.clientAlreadyExist);
            }
            else
            {
                client= new ClientBack(details[0], clientSocket);
                room.addClient(client);
                System.out.println("Connexion from: " + clientSocket.getInetAddress()+ " called "+details[0]);
                socOut.println(Config.connectionOk);
                socOut.println(room.getAddrIp());
                ArrayList<Message> listeMessage= room.getListeMessages();
                System.out.println("sending " + listeMessage.size() + " old messages...");
                for(Message m: listeMessage)
                {
                    String formattedMessage=m.getFormattedMessage();
                    socOut.println(formattedMessage);
                }
                socOut.println(Config.finishToSendOldMessage);
                line= socIn.readLine();
                while(line. compareTo("exit")!=0)
                {
                    Date date = new Date();
                    Message message= new Message(line, client.getName(),date);
                    String formattedMessage= message.getFormattedMessage();
                    room.sendMessageToRoom(formattedMessage);
                    room.writeInFile(formattedMessage);
                    room.getListeMessages().add(message);
                    line=socIn.readLine();
                }

            }
            socIn.close();

        }
        catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
        room.getListeClients().remove(client);
    }

}



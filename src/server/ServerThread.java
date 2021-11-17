package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class ServerThread extends Thread
{
    private ClientBack clientBack;
    private Room room;
    private BufferedReader stdIn;
    private BufferedReader socIn;
    private Socket clientSocket;



    public ServerThread(Socket clientSocket )
    {
        this.clientSocket=clientSocket;
    }



    public void run()
    {
        try {
            BufferedReader socIn = null;
            socIn = new BufferedReader(
                    new InputStreamReader(clientBack.getClientSocket().getInputStream()));
            String line= socIn.readLine();
            String[] details= line.split("/");
            String roomName= details[1];
            Room room=Server.IdentifyRoom(roomName);
            boolean clientDoesNotExist= room.identifyClient(details[0]);
            while(!clientDoesNotExist)
            {
                System.out.println("Client exists already in this room");
                 line= socIn.readLine();
                details= line.split("/");
                roomName= details[1];
                room=Server.IdentifyRoom(roomName);
                clientDoesNotExist= room.identifyClient(details[0]);
            }

                ClientBack client= new ClientBack(details[0], clientSocket);
                room.addClient(client);
            System.out.println("Connexion from:" + clientSocket.getInetAddress()+ "called"+details[0]);

            while(line!= "exit")
            {
                room.sendMessageToRoom(line, client);
                line=socIn.readLine();
            }
            stdIn.close();
            socIn.close();
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }

}



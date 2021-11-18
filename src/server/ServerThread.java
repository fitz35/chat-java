package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class ServerThread extends Thread
{

    private Socket clientSocket;



    public ServerThread(Socket clientSocket )
    {
        this.clientSocket=clientSocket;
    }



    public void run()
    {
        try {
            BufferedReader socIn = null;
            PrintStream socOut = null;
            socIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            socOut= new PrintStream(clientSocket.getOutputStream());
            String line= socIn.readLine();
            String[] details= line.split("/");
            String roomName= details[1];
            Room room=Server.IdentifyRoom(roomName);
            boolean clientDoesNotExist= room.identifyClient(details[0]);
            if(!clientDoesNotExist)
            {
                System.out.println("Client exists already in this room");
                socOut.println("/Client exists already in this room/");

            }
            else
            {
                ClientBack client= new ClientBack(details[0], clientSocket);
                room.addClient(client);
                System.out.println("Connexion from: " + clientSocket.getInetAddress()+ " called "+details[0]);
                socOut.println(" /Connexion /");
                line= socIn.readLine();
                while(line. compareTo("exit")!=0)
                {
                    room.sendMessageToRoom(line, client);
                    line=socIn.readLine();
                }


            }
            socIn.close();

        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
    }

}



package server;

import server.config.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ArrayList<Room> listRooms;
    private static ServerSocket listenSocket;


    public static Room IdentifyRoom(String roomName)
    {
        for (Room r: listRooms)
        {
            if(r.getName().compareTo(roomName)==0)
            {
                return r;
            }
        }
        Room r= new Room(roomName);
        listRooms.add(r);
        return r;

    }


    public static void main (String args[])
    {

            try {
                listenSocket = new ServerSocket(Config.port); //port
                System.out.println("Server ready...");
                while (true) {

                    Socket clientSocket = listenSocket.accept();
                    System.out.println("before Server Thread");
                    ServerThread serverThread= new ServerThread(clientSocket);
                    System.out.println("before Server Thread start");
                    serverThread.start();

                }
            } catch (Exception e) {
                System.err.println("Error in EchoServer:" + e);
            }
        }



}

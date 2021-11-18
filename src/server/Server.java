package server;

import server.config.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
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
        try {
            String filename= roomName+".txt";
            File myFile = new File(filename);
            if (myFile.createNewFile()) {

                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file");
            e.printStackTrace();
        }
        return r;

    }


    public static void main (String args[])
    {

            try {
                listenSocket = new ServerSocket(Config.port); //port
                System.out.println("Server ready...");
                Server.listRooms=new ArrayList<>();

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

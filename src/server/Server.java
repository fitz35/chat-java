package server;

import server.config.Config;

import java.io.*;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Server {
    private static ArrayList<Room> listRooms;
    private static ServerSocket listenSocket;
    private static int ipCounter=1;
    private  static InetAddress addr;

    /**
     * get the room with the name. if it doesn't already exist, create one
     * @param roomName the room name
     * @return the room
     */
    public static Room IdentifyRoom(String roomName)
    {
        try
        {
            for (Room r: listRooms)
            {
                if(r.getName().compareTo(roomName)==0)
                {
                    return r;
                }
            }

            //We can have only 255 rooms
            String addressIp = "224.0.0." +ipCounter;
            ipCounter++;
            MulticastSocket multicastSocket=null;
            try
            {
                multicastSocket= new MulticastSocket();
                addr = InetAddress.getByName(addressIp);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Room r= new Room(roomName, addressIp, multicastSocket, addr);
            r.joinRoom();
            listRooms.add(r);
            try {
                String filename= Config.dataPath + roomName+".txt";
                try {
                    BufferedReader br = new BufferedReader(new FileReader(filename));
                    String line;

                    while ((line = br.readLine()) != null)
                    {
                        String[]lineComponents= line.split("/");
                        Date dateTime=new SimpleDateFormat(Config.pattern).parse(lineComponents[2]);
                        Message m= new Message(lineComponents[1], lineComponents[0], dateTime);
                        r.getListeMessages().add(m);
                    }
                    br.close();
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                    File myFile = new File(filename);
                    if (myFile.createNewFile())
                    {
                        System.out.println("File created: " + myFile.getName());
                    }
                    else
                    {
                        System.out.println("File already exists.");
                    }
                }
                catch (IOException e)
                {
                    System.out.println("An error occurred while creating the file");
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return r;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
    public static void main (String[] args)
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

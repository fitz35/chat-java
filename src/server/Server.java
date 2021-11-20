package server;

import server.config.Config;

import java.io.*;
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
    private static int ipCounter=0;


    public static Room IdentifyRoom(String roomName)
    {
        for (Room r: listRooms)
        {
            if(r.getName().compareTo(roomName)==0)
            {
                return r;
            }
        }

        //We can have only 255 rooms
        String addressIp = "255.255.255." +ipCounter;
        ipCounter++;
        Room r= new Room(roomName, addressIp);
        listRooms.add(r);
        try {
            String filename= roomName+".txt";
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
            /*File myFile = new File(filename);
            if (myFile.createNewFile()) {

                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file");
            e.printStackTrace();
        }*/

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

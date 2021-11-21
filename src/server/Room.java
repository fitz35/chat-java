package server;

import server.config.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Date;

public class Room
{
    private ArrayList<ClientBack> listeClients;
    private ArrayList< Message> listeMessages;
    private String name;
    private String addrIp;
    private MulticastSocket multicastSocket;
    private InetAddress group;

    public Room(String name,String addrIp,MulticastSocket multicastSocket, InetAddress group)
    {
        this.listeClients= new ArrayList<>();
        this.listeMessages=new ArrayList<>();
        this.name=name;
        this.addrIp=addrIp;
        this.multicastSocket=multicastSocket;
        this.group=group;

    }

    public void joinRoom()
    {
        try
        {
            multicastSocket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAddrIp() {
        return addrIp;
    }

    public InetAddress getGroup() {
        return group;
    }

    public ArrayList<Message> getListeMessages() {
        return listeMessages;
    }

    public MulticastSocket getMulticastSocket() {
        return multicastSocket;
    }

    /*public void sendMessageToRoom(String formattedMessage)
    {
        try
        {
            for(ClientBack c: listeClients)
            {
                PrintStream socOut = new PrintStream(c.getClientSocket().getOutputStream());
                socOut.println(formattedMessage);
            }
        }
        catch(Exception e)
        {
            System.err.println("Error in Sending message to room:" + e);
        }

    }*/


    public void writeInFile(String formattedMessage)
    {
        try {
            String filename= Config.dataPath + this.name+ ".txt";
            FileWriter myWriter = new FileWriter(filename, true);
            String toWrite= formattedMessage+ Config.toWrite;
            myWriter.write(toWrite);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }

    public ArrayList<ClientBack> getListeClients() {
        return listeClients;
    }

    public boolean identifyClient(String inputDetails)
    {

        for(ClientBack c: listeClients)
        {
            if(c.getName().compareTo(inputDetails) == 0)
            {
                return false;
            }
        }
        return true;

    }

    public void addClient(ClientBack c)
    {
        listeClients.add(c);
    }


}

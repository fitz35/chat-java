package server;

import server.config.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Date;

/**
 * room data
 */
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

    /**
     * make the room join the group (listen message)
     */
    public void joinRoom()
    {
        try
        {
            multicastSocket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the address ip
     * @return the address ip
     */
    public String getAddrIp() {
        return addrIp;
    }

    /**
     * get the group
     * @return the group
     */
    public InetAddress getGroup() {
        return group;
    }

    /**
     * get the list of the message
     * @return the list of the message
     */
    public ArrayList<Message> getListeMessages() {
        return listeMessages;
    }

    /**
     * get the multicast socket
     * @return the multicast socket
     */
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

    /**
     * write a message in a file
     * @param formattedMessage the formatted message to write
     */
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

    /**
     * get the name of the room
     * @return the name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * get the list of connected client
     * @return the list of connected client
     */
    public ArrayList<ClientBack> getListeClients() {
        return listeClients;
    }

    /**
     * test if a client isn't connected
     * @param inputDetails the name of the client
     * @return true if the client isn't connected
     */
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

    /**
     * add a client
     * @param c the client to add
     */
    public void addClient(ClientBack c)
    {
        listeClients.add(c);
    }
}

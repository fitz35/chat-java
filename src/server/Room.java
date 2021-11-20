package server;

import server.config.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

public class Room
{
    private ArrayList<ClientBack> listeClients;
    private ArrayList< Message> listeMessages;
    private String name;

    public Room(String name)
    {
        this.listeClients= new ArrayList<>();
        this.listeMessages=new ArrayList<>();
        this.name=name;
    }

    public ArrayList<Message> getListeMessages() {
        return listeMessages;
    }

    public void sendMessageToRoom(String formattedMessage)
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

    }


    public void writeInFile(String formattedMessage)
    {
        try {
            String filename= this.name+ ".txt";
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

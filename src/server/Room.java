package server;

import java.io.PrintStream;
import java.util.ArrayList;

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

    public void sendMessageToRoom(String message)
    {
        try
        {
            for(ClientBack c: listeClients)
            {
                PrintStream socOut = new PrintStream(c.getClientSocket().getOutputStream());
                socOut.println(message);
            }
        }
        catch(Exception e)
        {
            System.err.println("Error in Sending message to room:" + e);
        }

    }

    public String getName() {
        return name;
    }

    public boolean identifyClient(String inputDetails)
    {

        for(ClientBack c: listeClients)
        {
            if(c.getName()==inputDetails)
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

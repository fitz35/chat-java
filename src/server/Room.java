package server;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
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

    public void sendMessageToRoom(String messageNotFormatted, ClientBack client)
    {
        try
        {
            for(ClientBack c: listeClients)
            {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                Message message= new Message(messageNotFormatted, client,date);
                String formattedMessage= message.getFormattedMessage();
                PrintStream socOut = new PrintStream(c.getClientSocket().getOutputStream());
                socOut.println(formattedMessage);
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

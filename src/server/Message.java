package server;

import java.net.Socket;
import java.util.Date;

public class Message {
    private String contents;
    private ClientBack client;
    private Date date;

    public Message(String contents, ClientBack client, Date date) {
        this.contents = contents;
        this.client = client;
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public ClientBack getClient() {
        return client;
    }

    public Date getDate() {
        return date;
    }
    public String getFormattedMessage()
    {
        return (client.getName()+"/"+contents+ "/"+date);
    }
}

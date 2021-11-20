package server;

import server.config.Config;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String contents;
    private String client;
    private Date date;

    public Message(String contents, String client, Date date) {
        this.contents = contents;
        this.client = client;
        this.date = date;
    }

    public String getContents() {
        return contents;
    }


    public Date getDate() {
        return date;
    }
    public String getFormattedMessage()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Config.pattern);
        String formattedDate= simpleDateFormat.format(date);
        return (client+"/"+contents+ "/"+formattedDate);
    }


}

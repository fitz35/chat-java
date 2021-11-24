package server;

import server.config.Config;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * content of a message
 */
public class Message {
    private String contents;
    private String client;
    private Date date;

    public Message(String contents, String client, Date date) {
        this.contents = contents;
        this.client = client;
        this.date = date;
    }

    /**
     * get the content of the message
     * @return the content of the message
     */
    public String getContents() {
        return contents;
    }

    /**
     * get the date of the message
     * @return the date of the message
     */
    public Date getDate() {
        return date;
    }

    /**
     * get a formatted message to be send
     * @return a formatted message to be send
     */
    public String getFormattedMessage()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Config.pattern);
        String formattedDate= simpleDateFormat.format(date);
        return (client+"/"+contents+ "/"+formattedDate);
    }


}

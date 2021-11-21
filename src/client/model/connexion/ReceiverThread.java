package client.model.connexion;

import client.config.Config;
import client.controller.ConnectedState;
import client.controller.Controller;
import client.model.data.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.IOException;
import java.util.ArrayList;

/**
 * thread handle the receive message
 */
public class ReceiverThread extends Thread {
    public static final String LAST_MESSAGE = "lastMessages";

    private final ManageConnection con;
    private final Controller controller;

    private final ArrayList<Message> messages;
    private Message lastMessage;

    /**
     *
     * @param con the connection with the remote server
     */
    public ReceiverThread(ManageConnection con, Controller controller){
        this.con = con;
        this.controller = controller;
        this.messages = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            String line = this.con.getMessage();// first line = if the connection is ok
            if(line.compareTo(Config.clientAlreadyExist) == 0){
                System.out.println("client already exist !");
                this.controller.getState().disconnection();
            }else if(line.compareTo(Config.connectionOk) == 0){
                System.out.println("connection ok !");
                line = this.con.getMessage();// second line = ip
                System.out.println("ip of the room : " + line);
                this.controller.getState().confirmConnection(line);
            }
            if(this.controller.getState() instanceof ConnectedState){

                line = this.con.getMessage();
                while(line.compareTo(Config.finishToSendOldMessage) != 0){// old message
                    System.out.println("Receive old message : " + line);
                    this.addMessage(Message.getMessageFromFormatted(line));
                    line = this.con.getMessage();
                }

                HeartBeatThread checkConnection = new HeartBeatThread(this.con, this.controller);
                checkConnection.start();

                while(this.controller.getState() instanceof ConnectedState){ // new one
                    line = this.controller.getState().getMulticast().receive();
                    System.out.println("Receive : " + line);
                    this.addMessage(Message.getMessageFromFormatted(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.controller.getState().disconnection();// error occurred
    }

    ////////////////////////////////////
    // design pattern observer/observable
    ////////////////////////////////////
    /**
     * listeners
     */
    private final ArrayList<PropertyChangeListener> listener = new ArrayList<>();

    /**
     * add a message to the list
     * @param message the message
     */
    private void addMessage(Message message) {
        notifyListeners(
                LAST_MESSAGE,
                this.lastMessage,
                message);
        this.lastMessage = message;
        this.messages.add(message);
    }

    /**
     * notify the listener
     * @param property the properties changed
     * @param oldValue the old value
     * @param newValue the new value
     */
    private void notifyListeners(String property, Message oldValue, Message newValue) {
        for (PropertyChangeListener name : this.listener) {
            name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }

    /**
     * add a listener
     * @param newListener the new listener
     */
    public void addChangeListener(PropertyChangeListener newListener) {
        listener.add(newListener);
    }
}

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
            String line = con.getMessage();// first line = if the connection is ok
            if(line.compareTo(Config.clientAlreadyExist) == 0){
                System.out.println("client already exist !");
                controller.getState().disconnection();
            }else if(line.compareTo(Config.connectionOk) == 0){
                System.out.println("connection ok !");
                line = con.getMessage();// second line = ip
                System.out.println("ip of the room : " + line);
                controller.getState().confirmConnection(line);
            }

            while(controller.getState() instanceof ConnectedState){
                line = controller.getState().getMulticast().receive();
                this.addMessage(Message.getMessageFromFormatted(line));
                System.out.println("Receive : " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.getState().disconnection();// error occurred
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

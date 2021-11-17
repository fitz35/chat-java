package client.connexion;

import client.controller.ConnectedState;
import client.controller.Controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.IOException;
import java.util.ArrayList;

/**
 * thread handle the receive message
 */
public class ReceiverThread extends Thread {
    public static final String MESSAGES = "messages";

    private final ManageConnection con;
    private final Controller controller;

    private final ArrayList<String> messages;

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
            while(con.isConnected() && controller.getState() instanceof ConnectedState){
                String line = con.getMessage();
                this.addMessage(line);
                System.out.println("Receive : " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void addMessage(String message) {
        ArrayList<String> old = new ArrayList<>(this.messages);
        this.messages.add(message);
        notifyListeners(
                MESSAGES,
                old,
                this.messages);

    }

    /**
     * notify the listener
     * @param property the properties changed
     * @param oldValue the old value
     * @param newValue the new value
     */
    private void notifyListeners(String property, ArrayList<String> oldValue, ArrayList<String> newValue) {
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

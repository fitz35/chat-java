package client.view.connectedWindow;

import client.controller.Controller;

import javax.swing.*;
import java.util.ArrayList;

/**
 * display all the messages
 */
public class DisplayMessagePanel extends JPanel {

    private ArrayList<String> listMessages;
    private final Controller controller;

    // component
    private ArrayList<DisplayOneMessage> listMessagesDisplay;


    public DisplayMessagePanel(ArrayList<String> listMessages, Controller controller){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.controller = controller;

        this.listMessages = listMessages;
        this.listMessagesDisplay = new ArrayList<>();

        for(String message : this.listMessages){
            DisplayOneMessage messageDisplay = new DisplayOneMessage(message, this.controller);
            this.listMessagesDisplay.add(messageDisplay);
            this.add(messageDisplay);
        }
    }

    /**
     * add a message to display
     * @param message the message to add
     */
    public void addMessage(String message){
        this.listMessages.add(message);
        DisplayOneMessage messageDisplay = new DisplayOneMessage(message, this.controller);
        this.listMessagesDisplay.add(messageDisplay);
        this.add(messageDisplay);
    }
}

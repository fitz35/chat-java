package client.view.connectedWindow;

import client.config.Config;
import client.controller.Controller;
import client.model.data.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * display all the messages
 */
public class DisplayMessagePanel extends JPanel {

    private ArrayList<Message> listMessages;
    private final Controller controller;

    // component
    private final ArrayList<DisplayOneMessage> listMessagesDisplay;


    public DisplayMessagePanel(ArrayList<Message> listMessages, Controller controller){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.controller = controller;

        this.listMessages = listMessages;
        this.listMessagesDisplay = new ArrayList<>();

        for(Message message : this.listMessages){
            DisplayOneMessage messageDisplay = new DisplayOneMessage(message, this.controller);
            this.listMessagesDisplay.add(messageDisplay);
            this.add(messageDisplay);
        }
        this.validate();
        this.repaint();
    }

    /**
     * add a message to display
     * @param message the message to add
     */
    public void addMessage(Message message){
        this.listMessages.add(message);
        DisplayOneMessage messageDisplay = new DisplayOneMessage(message, this.controller);
        this.listMessagesDisplay.add(messageDisplay);
        this.add(messageDisplay);

        this.validate();
        this.repaint();
    }
}

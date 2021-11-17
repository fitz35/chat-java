package client.view.connectedWindow;

import client.controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * display one message
 */
public class DisplayOneMessage extends JPanel {

    private final String message;
    private final Controller controller;

    // component
    private final JTextArea messageDisplay;

    public DisplayOneMessage(String message, Controller controller){
        super();
        this.setLayout(new BorderLayout());
        this.message = message;
        this.controller = controller;
        this.messageDisplay = new JTextArea(message);
        this.messageDisplay.setLineWrap(true);
        this.messageDisplay.setEditable(false);
        this.messageDisplay.setVisible(true);
        this.add(messageDisplay, BorderLayout.CENTER);
    }

}

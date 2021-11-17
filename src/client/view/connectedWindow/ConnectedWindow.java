package client.view.connectedWindow;

import client.connexion.ReceiverThread;
import client.controller.Controller;
import client.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * display the message and the possibilities to send
 */
public class ConnectedWindow extends Frame implements PropertyChangeListener {
    // component
    private final DisplayMessagePanel listMessagesPanel;

    private final JLabel titleLabel;

    public ConnectedWindow(Controller controller) {
        super(controller);
        this.setLayout(new BorderLayout());

        this.listMessagesPanel = new DisplayMessagePanel(new ArrayList<>(), controller);
        JScrollPane scrollMessages = new JScrollPane(this.listMessagesPanel);
        scrollMessages.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollMessages.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.titleLabel = new JLabel("Connection to the server !", SwingConstants.CENTER);
        this.titleLabel.setFont(new Font("Serif", Font.BOLD, 20));


        this.add(this.titleLabel, BorderLayout.NORTH);
        this.add(scrollMessages, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().compareTo(ReceiverThread.LAST_MESSAGE) == 0){
            this.listMessagesPanel.addMessage((String) evt.getNewValue());
        }
    }
}

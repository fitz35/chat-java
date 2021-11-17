package client.view.connectedWindow;

import client.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * panel which handle the send form
 */
public class SendFormPanel extends JPanel {

    private final Controller controller;

    // component
    private final JTextField sendField;
    private final JButton sendButton;

    public SendFormPanel(Controller controller){
        super();
        this.setLayout(new FlowLayout());
        this.controller = controller;

        this.sendField = new JTextField();
        this.sendButton = new JButton("send");
        this.sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sendField.getText().compareTo("") != 0){
                    controller.getConnection().sendMessage(sendField.getText());
                }
            }
        });


        this.add(this.sendField);
        this.add(this.sendButton);
    }
}

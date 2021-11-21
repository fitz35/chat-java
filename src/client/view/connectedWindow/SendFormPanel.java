package client.view.connectedWindow;

import client.config.Config;
import client.controller.Controller;
import client.model.data.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

        this.sendButton = new JButton("send");
        this.sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Util.isInString(sendField.getText(), Config.forbiddenStringInMessage)){
                    System.out.println("forbidden string encountered");
                }
                else if(sendField.getText().compareTo("") != 0){
                    System.out.println("send (ihm) : " + sendField.getText());
                    try {
                        controller.getState().getMulticast().send(sendField.getText());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        this.sendField = new JTextField();
        Dimension jtextFieldDimension = new Dimension(Config.minimumSize.width - this.sendButton.getPreferredSize().width - 30,
                20);
        this.sendField.setPreferredSize(jtextFieldDimension);

        this.add(this.sendField);
        this.add(this.sendButton);
    }
}

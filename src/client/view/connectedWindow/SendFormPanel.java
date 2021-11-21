package client.view.connectedWindow;

import client.config.ColorPalette;
import client.config.Config;
import client.controller.Controller;
import client.model.data.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * panel which handle the send form
 */
public class SendFormPanel extends JPanel {

    private final Controller controller;

    // component
    private final JTextField sendField;
    private final JButton sendButton;

    private final JLabel errorMessage;

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
                    StringBuilder toDisplay = new StringBuilder("The following string are forbidden in the name : ");
                    for(String forbidden : Config.forbiddenStringInMessage){
                        toDisplay.append("\"").append(forbidden).append("\", ");
                    }
                    errorMessage.setText(toDisplay.toString());
                    errorMessage.setVisible(true);
                }
                else if(sendField.getText().compareTo("") != 0){
                    System.out.println("send (ihm) : " + sendField.getText());
                    controller.getConnection().sendMessage(sendField.getText());
                }
            }
        });

        this.sendField = new JTextField();
        this.sendField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                errorMessage.setVisible(false);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                errorMessage.setVisible(false);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                errorMessage.setVisible(false);
            }
        });
        Dimension jtextFieldDimension = new Dimension(Config.minimumSize.width - this.sendButton.getPreferredSize().width - 30,
                20);
        this.sendField.setPreferredSize(jtextFieldDimension);

        this.errorMessage = new JLabel("");
        this.errorMessage.setVisible(false);
        this.errorMessage.setForeground(ColorPalette.errorMessage);

        this.add(this.errorMessage);
        this.add(this.sendField);
        this.add(this.sendButton);
    }


}

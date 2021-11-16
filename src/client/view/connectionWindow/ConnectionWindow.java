package client.view.connectionWindow;

import client.config.Color;
import client.config.Config;
import client.controller.Controller;
import client.view.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * window to connect to the server
 */
public class ConnectionWindow extends Frame {
    // component
    private final JLabel titleLabel;
    private final JLabel roomNameLabel, nameLabel;
    private final JLabel errorLabel;
    private final JTextField roomNameField, nameField;
    private final JButton validate;

    private final JPanel askPanel;



    public ConnectionWindow(Controller controller){
        super(controller);

        this.setLayout(new BorderLayout());

        // component
        this.roomNameField = new JTextField();
        this.roomNameField.setMaximumSize(new Dimension(Config.minimumSize.width, 20));
        this.nameField = new JTextField();
        this.nameField.setMaximumSize(new Dimension(Config.minimumSize.width, 20));


        this.roomNameLabel = new JLabel("Enter the room name :");
        this.nameLabel = new JLabel("Enter your name :");

        this.errorLabel = new JLabel("Please fill all the form !");
        this.errorLabel.setForeground(Color.errorMessage);
        this.errorLabel.setVisible(false);

        this.validate = new JButton("Connection");
        this.validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String roomName = roomNameField.getText();
                if(name.compareTo("") != 0 && roomName.compareTo("") != 0){
                    System.out.println("form ok");
                    controller.connection(name, roomName);
                }else{
                    System.out.println("form empty");
                    errorLabel.setVisible(true);
                }
            }
        });

        this.askPanel = new JPanel();
        this.askPanel.setLayout(new BoxLayout(this.askPanel, BoxLayout.Y_AXIS));
        this.askPanel.setMinimumSize(new Dimension(Config.minimumSize.width, 100));
        this.askPanel.setPreferredSize(new Dimension(Config.minimumSize.width, 100));
        this.askPanel.setMaximumSize(new Dimension(Config.minimumSize.width, 100));

        // add on the panel
        Dimension fillerTitle = new Dimension(Config.minimumSize.width, 30);
        this.askPanel.add(new Box.Filler(fillerTitle, fillerTitle, fillerTitle));
        this.askPanel.add(this.nameLabel);
        this.askPanel.add(this.nameField);
        this.askPanel.add(this.roomNameLabel);
        this.askPanel.add(this.roomNameField);
        Dimension fillerValidate = new Dimension(Config.minimumSize.width, 20);
        this.askPanel.add(new Box.Filler(fillerValidate, fillerValidate, fillerValidate));
        this.askPanel.add(this.validate);
        this.askPanel.add(this.errorLabel);

        //title label
        this.titleLabel = new JLabel("Connection to the server !", SwingConstants.CENTER);
        this.titleLabel.setFont(new Font("Serif", Font.BOLD, 20));

        // add on the frame
        this.add(this.titleLabel, BorderLayout.NORTH);
        this.add(this.askPanel, BorderLayout.CENTER);
        this.pack();
    }
}

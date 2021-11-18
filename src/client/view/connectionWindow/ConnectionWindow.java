package client.view.connectionWindow;

import client.config.ColorPalette;
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

        this.errorLabel = new JLabel();
        this.errorLabel.setForeground(ColorPalette.errorMessage);
        this.errorLabel.setVisible(false);

        this.validate = new JButton("Connection");
        this.validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String roomName = roomNameField.getText();
                if(name.compareTo("") != 0 && roomName.compareTo("") != 0){
                    System.out.println("form ok");
                    controller.getState().connection(name, roomName, controller);
                }else{
                    System.out.println("form empty");
                    formError();
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

    ////////////////////////////////////////
    // manage error
    ////////////////////////////////////////

    /**
     * clear the error
     */
    private void clearError(){
        this.errorLabel.setVisible(false);
    }

    /**
     * manage the error from the user
     */
    private void formError(){
        this.errorLabel.setText("Please fill all the form !");
        this.errorLabel.setVisible(true);
    }

    /**
     * manage the error from the user
     */
    public void connectionError(){
        this.errorLabel.setText("An error occur with the connection !");
        this.errorLabel.setVisible(true);
    }


}

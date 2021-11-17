package client.view;

import client.config.Config;
import client.controller.Controller;

import javax.swing.*;

/**
 * base of the frames
 */
public class Frame extends JFrame {

    protected Controller controller;

    public Frame(Controller controller){
        this.controller = controller;
        this.setPreferredSize(Config.preferredSize);
        this.setMinimumSize(Config.minimumSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

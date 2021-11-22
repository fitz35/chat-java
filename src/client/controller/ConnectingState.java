package client.controller;

import client.config.Config;
import client.model.connexion.ManageMulticast;

import java.io.IOException;

/**
 * state connecting to the server
 */
public class ConnectingState extends StateController{
    private final String name;
    private final String room;

    public ConnectingState(String name, String room, Controller controller){
        super(controller);
        this.name = name;
        this.room = room;
    }

    @Override
    public void confirmConnection(String ip){
        this.controller.setState(new ConnectedState(this.name, this.room, this.controller));
    }

    @Override
    public void cancelConnection(){
        this.controller.getConnection().disconnection();
        this.controller.stopThread();
        this.controller.setState(new NotConnectedState(this.controller));
    }

    @Override
    public String getRoom() {
        return room;
    }

    @Override
    public String getName() {
        return name;
    }
}

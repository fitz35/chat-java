package client.controller;

import client.model.connexion.ManageMulticast;

import java.io.IOException;

/**
 * state when we are connected
 */
public class ConnectedState extends StateController{
    private final String name;
    private final String room;
    private final ManageMulticast multicast;

    public ConnectedState(String name, String room, ManageMulticast multicast, Controller controller){
        super(controller);
        this.name = name;
        this.room = room;
        this.multicast = multicast;
    }

    @Override
    public void disconnection(){
        try {
            this.multicast.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.disconnection();
    }

    @Override
    public ManageMulticast getMulticast(){return this.multicast;}

    @Override
    public String getRoom() {
        return room;
    }

    @Override
    public String getName() {
        return name;
    }
}

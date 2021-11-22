package client.controller;

import client.model.connexion.ManageMulticast;

import java.io.IOException;

/**
 * state when we are connected
 */
public class ConnectedState extends StateController{
    private final String name;
    private final String room;

    public ConnectedState(String name, String room, Controller controller){
        super(controller);
        this.name = name;
        this.room = room;
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

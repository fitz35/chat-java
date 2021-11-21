package client.model.connexion;

import client.controller.Controller;

import java.io.IOException;

/**
 * only check if the connection is alive
 */
public class HeartBeatThread extends Thread {
    private final ManageConnection connection;
    private final Controller controller;

    public HeartBeatThread(ManageConnection connection, Controller controller){
        this.connection = connection;
        this.controller = controller;
    }

    @Override
    public void run() {
        try{
            this.connection.getMessage();
        } catch (IOException e) {// connection not alive
            e.printStackTrace();
            this.controller.getState().disconnection();
        }
    }
}

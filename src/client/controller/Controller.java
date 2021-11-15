package client.controller;

import client.connexion.ManageConnection;

/**
 * the controller of the application
 */
public class Controller {
    private StateController state;
    private final ManageConnection connection;

    public Controller(){
        this.setState(new NotConnectedState());
        this.connection = new ManageConnection();
    }

    /**
     * connect to the room with the name
     * @param name the name
     * @param room the room
     */
    public void connection(String name, String room){
        this.state.connection(name, room, this);
    }

    /**
     * disconnect of the server
     */
    public void disconnection(){
        this.state.disconnection(this);
    }

    ////////////////////////////////////////////////////////

    /**
     * get the state of the controller
     * @return the state
     */
    public StateController getState() {
        return state;
    }

    /**
     * set the state of the controller
     * @param state the new state
     */
    public void setState(StateController state) {
        this.state = state;
    }

    /**
     * get the connection
     * @return the connection
     */
    public ManageConnection getConnection() {
        return connection;
    }
}

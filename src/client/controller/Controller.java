package client.controller;

import client.connexion.ManageConnection;
import client.connexion.ReceiverThread;
import client.view.connectedWindow.ConnectedWindow;
import client.view.connectionWindow.ConnectionWindow;

/**
 * the controller of the application
 */
public class Controller {
    private StateController state;
    private final ManageConnection connection;

    private final ConnectionWindow connectionWindow;
    private final ConnectedWindow connectedWindow;

    public Controller(){

        this.connection = new ManageConnection();

        this.connectionWindow = new ConnectionWindow(this);
        this.connectedWindow = new ConnectedWindow(this);

        this.setState(new NotConnectedState());
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
        if(this.state instanceof NotConnectedState){
            this.connectionWindow.setVisible(true);
            this.connectedWindow.setVisible(false);
        }else if(this.state instanceof ConnectedState){
            this.connectionWindow.setVisible(false);
            this.connectedWindow.setVisible(true);
        }
    }

    /**
     * get the connection
     * @return the connection
     */
    public ManageConnection getConnection() {
        return connection;
    }

    /**
     * get the connected window
     * @return the connected window
     */
    public ConnectedWindow getConnectedWindow(){
        return this.connectedWindow;
    }
}

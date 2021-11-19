package client.controller;

import client.model.connexion.ManageConnection;
import client.model.connexion.ReceiverThread;
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

    private ReceiverThread receiverThread;

    public Controller(){

        this.connection = new ManageConnection();

        this.connectionWindow = new ConnectionWindow(this);
        this.connectedWindow = new ConnectedWindow(this);

        this.setState(new NotConnectedState(this));
    }

    ////////////////////////////////////////////////////////

    /**
     * launch the receiver thread
     */
    public void launchThread(){
        this.receiverThread = new ReceiverThread(this.connection, this);
        this.receiverThread.addChangeListener(this.getConnectedWindow());
        this.receiverThread.start();
    }

    /**
     * stop the receiver thread if it exists
     */
    public void stopThread(){
        if(this.receiverThread != null){
            this.receiverThread.interrupt();
            this.receiverThread = null;
        }
    }
    ///////////////////////////////////////////////////////
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
            this.connectionWindow.displayButton();
            this.connectionWindow.setVisible(true);
            this.connectedWindow.setVisible(false);
        }else if(this.state instanceof ConnectingState){
            this.connectionWindow.displayButton();
        }else if(this.state instanceof ConnectedState){
            this.connectionWindow.setVisible(false);
            this.connectedWindow.setVisible(true);
            this.connectedWindow.updateTitle();
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

    /**
     * get the connection window
     * @return the connection window
     */
    public ConnectionWindow getConnectionWindow(){
        return this.connectionWindow;
    }
}

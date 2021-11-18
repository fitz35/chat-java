package client.controller;

/**
 * the state of a controller
 */
public abstract class StateController {
    /**
     * controller
     */
    protected Controller controller;

    public StateController(Controller controller){
        this.controller = controller;
    }
    /**
     * connection to a room with a name
     * @param name the name
     * @param room the room
     */
    public void connection(String name, String room){}

    /**
     * disconnection
     */
    public void disconnection(){}

    /**
     * confirm a connection
     */
    public void confirmConnection(){}

    /**
     * cancel a connection if we attempt to do one
     */
    public void cancelConnection(){}
    ////////////////////////////////////////////////////////////////////////////

    /**
     * get the room name (null if not connected)
     * @return the room name
     */
    public String getRoom() {return null;}

    /**
     * get the name of the user (null if not connected)
     * @return the name of the user
     */
    public String getName() {return null;}
}

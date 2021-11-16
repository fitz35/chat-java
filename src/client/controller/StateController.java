package client.controller;

/**
 * the state of a controller
 */
public abstract class StateController {
    /**
     * connection to a room with a name
     * @param name the name
     * @param room the room
     * @param controller the controller
     */
    public void connection(String name, String room, Controller controller){}

    /**
     * disconnection
     * @param controller the controller
     */
    public void disconnection(Controller controller){}

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

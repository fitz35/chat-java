package client.controller;

/**
 * state when we are connected
 */
public class ConnectedState extends StateController{
    private final String name;
    private final String room;

    public ConnectedState(String name, String room){
        this.name = name;
        this.room = room;
    }

    @Override
    public void disconnection(Controller controller){
        controller.disconnection();
        controller.setState(new NotConnectedState());
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

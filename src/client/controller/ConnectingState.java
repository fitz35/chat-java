package client.controller;

/**
 * state connecting to the server
 */
public class ConnectingState extends StateController{
    private final String name;
    private final String room;

    public ConnectingState(String name, String room){
        this.name = name;
        this.room = room;
    }

    @Override
    public void confirmConnection(Controller controller){
        controller.setState(new ConnectedState(this.name, this.room));
    }

    @Override
    public void disconnection(Controller controller){
        controller.getConnection().disconnection();
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

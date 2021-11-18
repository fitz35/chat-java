package client.controller;

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
    public void disconnection(){
        controller.getConnection().disconnection();
        this.controller.getConnectionWindow().connectionError();
        controller.setState(new NotConnectedState(this.controller));
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

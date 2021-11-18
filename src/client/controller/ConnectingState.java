package client.controller;

/**
 * state connecting to the server
 */
public class ConnectingState extends StateController{
    private final String name;
    private final String room;

    public ConnectingState(String name, String room, Controller controller){
        super(controller);
        this.name = name;
        this.room = room;
    }

    @Override
    public void confirmConnection(){
        controller.setState(new ConnectedState(this.name, this.room, this.controller));
    }

    @Override
    public void disconnection(){
        controller.getConnection().disconnection();
        this.controller.getConnectionWindow().connectionError();
        controller.setState(new NotConnectedState(this.controller));
    }

    @Override
    public void cancelConnection(){
        controller.getConnection().disconnection();
        controller.stopThread();
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

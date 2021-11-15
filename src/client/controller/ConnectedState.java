package client.controller;

/**
 * state when we are connected
 */
public class ConnectedState extends StateController{

    public ConnectedState(){

    }

    @Override
    public void disconnection(Controller controller){
        controller.disconnection();
        controller.setState(new NotConnectedState());
    }
}

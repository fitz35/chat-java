package client.controller;

import client.config.Config;
import client.connexion.ManageConnection;
import client.connexion.ReceiverThread;

/**
 * state when we aren't connected
 */
public class NotConnectedState extends StateController {

    public NotConnectedState(){

    }

    @Override
    public void connection(String name, String room, Controller controller){
        ManageConnection con = controller.getConnection();
        if(con.connection(Config.host, Config.port, name, room)){
            System.out.println("connected to " + Config.host + ":" + Config.port +
                    ", to the room : " + room + " with the name : " + name + "!");
            ReceiverThread ct = new ReceiverThread(con, controller);
            ct.addChangeListener(controller.getConnectedWindow());
            ct.start();
            controller.setState(new ConnectingState(name, room));
        }else{
            controller.getConnectionWindow().connectionError();
        }
    }
}

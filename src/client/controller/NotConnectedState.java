package client.controller;

import client.config.Config;
import client.model.connexion.ManageConnection;

/**
 * state when we aren't connected
 */
public class NotConnectedState extends StateController {

    public NotConnectedState(Controller controller){
        super(controller);
    }

    @Override
    public void connection(String name, String room){
        ManageConnection con = this.controller.getConnection();
        if(con.connection(Config.host, Config.connectionPort, name, room)){
            System.out.println("connection to " + Config.host + ":" + Config.connectionPort +
                    ", to the room : " + room + " with the name : " + name + "!");
            this.controller.launchThread();
            this.controller.setState(new ConnectingState(name, room, this.controller));
        }else{
            this.controller.getConnectionWindow().connectionError();
        }
    }
}

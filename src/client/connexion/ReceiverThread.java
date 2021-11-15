package client.connexion;

import client.controller.ConnectedState;
import client.controller.Controller;

import java.io.IOException;

/**
 * thread handle the receive message
 */
public class ReceiverThread extends Thread{
    private ManageConnection con;
    private Controller controller;

    /**
     *
     * @param con the connection with the remote server
     */
    public ReceiverThread(ManageConnection con, Controller controller){
        this.con = con;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            while(con.isConnected() && controller.getState() instanceof ConnectedState){
                String line = con.getMessage();
                System.out.println("Receive : " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

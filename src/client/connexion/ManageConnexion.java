package client.connexion;

import java.io.IOException;
import java.net.Socket;

/**
 * manage the connexion
 */
public class ManageConnexion {
    private Socket echoSocket = null;


    public ManageConnexion(){

    }

    /**
     * test if the connection is alive
     * @return if the connection is alive
     */
    public boolean isConnected(){
        if(this.echoSocket != null){
            return this.echoSocket.isConnected();
        }else{
            return false;
        }
    }

    /**
     * connexion to a server
     * @param host the server
     * @param port the port of the server
     * @return if the connexion is up
     */
    public boolean connection(String host, int port){
        try {
            this.echoSocket = new Socket(host, port);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * if a connection is alive, disconnect the connection
     */
    public void disconnection(){
        if(this.isConnected()){
            try {
                this.echoSocket.close();
                this.echoSocket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

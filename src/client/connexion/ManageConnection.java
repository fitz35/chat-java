package client.connexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * manage the connexion
 */
public class ManageConnection {
    private Socket echoSocket = null;
    PrintStream socOut = null;
    BufferedReader socIn = null;

    public ManageConnection(){

    }

    /**
     * wait a message if connected (! block the programme)
     * @return the message or null if not connected
     * @exception IOException if the read fails
     */
    public String getMessage() throws IOException {
        if(this.isConnected()){
            return socIn.readLine();
        }else{
            return null;
        }
    }

    /**
     * send a message to the server if connected
     * @param message the message
     */
    public void sendMessage(String message){
        if(this.isConnected()){
            socOut.println(message);
            System.out.println("\"" + message + "\" sent !");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////

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
     * @param name the name of the client
     * @param room the room where the client connect
     * @return if the connexion is up
     */
    public boolean connection(String host, int port, String name, String room){
        try {
            this.echoSocket = new Socket(host, port);
            this.socIn = new BufferedReader(new InputStreamReader(this.echoSocket.getInputStream()));
            this.socOut= new PrintStream(this.echoSocket.getOutputStream());

            // test the credentials :TODO : test this method
            socOut.println(name + "/" + room);
            if(this.echoSocket.isConnected()){
                return true;
            }else{
                this.echoSocket = null;
                this.socIn = null;
                this.socOut = null;
                return false;
            }
        } catch (IOException e) {
            this.echoSocket = null;
            this.socIn = null;
            this.socOut = null;
            return false;
        }
    }

    /**
     * if a connection is alive, disconnect the connection,
     */
    public void disconnection(){
        if(this.isConnected()){
            try {
                this.echoSocket.close();
                this.socOut.close();
                this.socIn.close();
                this.socIn = null;
                this.socOut = null;
                this.echoSocket = null;
            } catch (IOException e) {
                this.socIn = null;
                this.socOut = null;
                this.echoSocket = null;
                e.printStackTrace();
            }
        }else{
            this.socIn = null;
            this.socOut = null;
            this.echoSocket = null;
        }
    }
}

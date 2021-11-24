package server;

import java.net.MulticastSocket;
import java.net.Socket;

/**
 * information's of the client
 */
public class ClientBack {
    private String name;
    private Socket clientSocket;

    public ClientBack(String name, Socket clientSocket)
    {
        this.clientSocket=clientSocket;
        this.name=name;
    }

    /**
     * get the socket of the client
     * @return the socket of the client
     */
    public Socket getClientSocket() {
        return clientSocket;
    }

    /**
     * get the name of the client
     * @return the name of the client
     */
    public String getName() {
        return name;
    }
}

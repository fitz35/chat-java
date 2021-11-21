package server;

import java.net.MulticastSocket;
import java.net.Socket;

public class ClientBack {
    private String name;
    private Socket clientSocket;

    public ClientBack(String name, Socket clientSocket)
    {
        this.clientSocket=clientSocket;
        this.name=name;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public String getName() {
        return name;
    }

    public void launchServerThread()
    {
        ServerThread ct = new ServerThread(this.clientSocket);
        ct.start();
    }

}

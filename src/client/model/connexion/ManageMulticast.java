package client.model.connexion;

import client.config.Config;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;

/**
 * manage the multicast socket
 */
public class ManageMulticast {
    private final String ip;
    private final int port;
    private final MulticastSocket socket;
    private final InetAddress group;

    public ManageMulticast(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        this.group = InetAddress.getByName(this.ip);
        this.socket = new MulticastSocket(this.port);
        this.socket.joinGroup(group);
    }

    /**
     * disconnect of this room
     * @throws IOException if the room doesn't exist
     */
    public void disconnect() throws IOException {
        this.socket.leaveGroup(this.group);
        this.socket.disconnect();
    }

    /**
     * receive data from the socket (! call stop the execution)
     * @return the string received
     * @throws IOException if an error occurred
     */
    public String receive() throws IOException {
        byte[] buf = new byte[Config.maximalLengthOfMessage];
        DatagramPacket recv = new DatagramPacket(buf, buf.length);
        this.socket.receive(recv);

        return new String(recv.getData(), recv.getOffset(), recv.getLength());
    }

    /**
     * send a message to the socket
     * @param message the message to send
     * @throws IOException if a problem occurred to the connection
     * @throws IndexOutOfBoundsException if the message is to length to be send
     */
    public void send(String message) throws IOException, IndexOutOfBoundsException {
        if(message.getBytes().length > Config.maximalLengthOfMessage){
            throw new IndexOutOfBoundsException("Message must be under " + Config.maximalLengthOfMessage + " byte length !");
        }else{
            DatagramPacket data = new DatagramPacket(message.getBytes(), message.length(),
                    this.group, this.port);
            this.socket.send(data);
        }
    }
}

package server.config;

/**
 * manage client.config
 */
public class Config {
    /**
     * port of the server
     */
    public final static int port = 85;

    ////////////////////////////////////////////////////////////////
    // connection message
    ////////////////////////////////////////////////////////////////
    /**
     * message occur when a client already exist in the room
     */
    public final static String clientAlreadyExist = "/Client exists already in this room/";

    /**
     * message send if the connection are ok
     */
    public final static String connectionOk = " /Connexion /";

    public final static String pattern = "yyyy-MM-dd HH:mm:ssZ";

    public final static String toWrite= "\n";

}

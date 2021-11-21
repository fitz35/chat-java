package server.config;

/**
 * manage client.config
 */
public class Config {
    /**
     * port of the server
     */
    public final static int port = 85;

    /**
     * multicast port of the server
     */
    public final static int multiCastPort= 86;

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

    /**
     * message send if their is no more old message
     */
    public final static String finishToSendOldMessage = "End of old messages";

    /**
     * pattern of a date
     */
    public final static String pattern = "yyyy-MM-dd HH:mm:ssZ";

    /**
     * separator of the message
     */
    public final static String toWrite= "\n";

    /**
     * path to the files of data
     */
    public final static String dataPath = "./data/";

}

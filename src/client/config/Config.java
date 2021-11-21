package client.config;

import java.awt.*;
import java.util.ArrayList;

/**
 * manage client.config
 */
public class Config {
    /**
     * port of the server
     */
    public final static int connectionPort = 85;

    /**
     * the multicast port
     */
    public final static int multicastPort = 86;

    /**
     * the host of the server
     */
    public final static String host = "localhost";

    /**
     * maximal length of a message
     */
    public final static int maximalLengthOfMessage = 242000;
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
    public final static String finishToSendOldMessage = "";
    //////////////////////////////////////////////////////////////
    // IHM
    //////////////////////////////////////////////////////////////
    /**
     * the forbidden string in the message
     */
    public final static String[] forbiddenStringInMessage = {"+=", "/"};
    /**
     * preferred size of the window
     */
    public final static Dimension preferredSize = new Dimension(500, 500);

    /**
     * minimum size of the window
     */
    public final static Dimension minimumSize = new Dimension(300, 200);

    /**
     * number of Characters max in one line for the message.
     */
    public final static int nbCharactersMaxOneLine = 40;

    /**
     * position in the window of self message (true = right, false = left).
     */
    public final static boolean selfPositionMessageDisplay = false;
}

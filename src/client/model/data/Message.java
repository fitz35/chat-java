package client.model.data;

/**
 * a message
 */
public class Message {
    private final String clientName;
    private final String message;
    private final String date;

    /**
     * return a message from a formatted one (clientName/message/date)
     * @param formattedMessage the formatted message
     * @return the message
     */
    public static Message getMessageFromFormatted(String formattedMessage){
        String[] split = formattedMessage.split("/");
        return new Message(split[0], split[1], split[2]);
    }

    public Message(String clientName, String message, String date) {
        this.clientName = clientName;
        this.message = message;
        this.date = date;
    }

    /**
     * return the date
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * return the message
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * return the client name
     * @return the client's name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * return if the message is from name client
     * @param name the name of the client
     * @return if the message is from name client
     */
    public boolean isClientNamed(String name){
        return this.getClientName().compareTo(name) == 0;
    }
}

package client.view.connectedWindow;

import client.config.ColorPalette;
import client.config.Config;
import client.controller.Controller;
import client.model.data.Message;

import javax.swing.*;
import javax.swing.text.*;

/**
 * display one message
 */
public class DisplayOneMessage extends JTextPane {

    private final Message message;
    private final Controller controller;

    public DisplayOneMessage(Message message, Controller controller){
        super();
        this.message = message;
        this.controller = controller;

        this.formatMessage(message);
        if(message.isClientNamed(this.controller.getState().getName())){
            this.setBorder(BorderFactory.createLineBorder(ColorPalette.oneMessageSelfColor));
        }else{
            this.setBorder(BorderFactory.createLineBorder(ColorPalette.oneMessageOthersColor));
        }

        this.validate();
        this.repaint();
    }

    /**
     * format a message to display it
     * @param message the message to display
     */
    private void formatMessage(Message message){
        StringBuilder sb = new StringBuilder(message.getMessage());
        for(int i = 1 ; i * Config.nbCharactersMaxOneLine < message.getMessage().length(); i++){
            sb.insert(i * Config.nbCharactersMaxOneLine, "\n");
        }
        String formattedMessage = sb.toString();

        // right if its not me
        if(!message.isClientNamed(this.controller.getState().getName())){
            SimpleAttributeSet attribs = new SimpleAttributeSet();
            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
            this.setParagraphAttributes(attribs, true);
        }

        Style defaultStyle = this.getStyle("default");
        // my default style
        Style customDefaultStyle = this.addStyle("customDefaultStyle", defaultStyle);
        StyleConstants.setFontFamily(customDefaultStyle, "Comic sans MS");
        // the style of a name
        Style nameStyle = this.addStyle("nameStyle", customDefaultStyle);
        StyleConstants.setForeground(nameStyle, ColorPalette.nameColor);
        StyleConstants.setFontSize(nameStyle, 25);

        // the message style
        Style messageStyle = this.addStyle("messageStyle", customDefaultStyle);

        StyledDocument sDoc = (StyledDocument)this.getDocument();
        String messageToDisplay = message.getClientName();
        try {
            int pos = 0;
            messageToDisplay += "\n";
            sDoc.insertString(pos, messageToDisplay, nameStyle);
            pos+=messageToDisplay.length();

            sDoc.insertString(pos, formattedMessage, messageStyle);
            pos+=formattedMessage.length();
        } catch (BadLocationException ignored) {

        }
    }

}

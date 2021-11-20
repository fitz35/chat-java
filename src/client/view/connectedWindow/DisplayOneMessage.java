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

        this.setEditable(false);

        this.formatMessage();
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
     */
    private void formatMessage(){
        StringBuilder sb = new StringBuilder(message.getMessage());
        for(int i = 1 ; i * Config.nbCharactersMaxOneLine < message.getMessage().length(); i++){
            sb.insert(i * Config.nbCharactersMaxOneLine, "\n");
        }
        String formattedMessage = sb.toString();

        // right if true
        if(positionInWindow()){
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

        // the style of a date
        Style dateStyle = this.addStyle("dateStyle", customDefaultStyle);
        StyleConstants.setForeground(dateStyle, ColorPalette.dateColor);
        StyleConstants.setFontSize(dateStyle, 16);

        StyledDocument sDoc = (StyledDocument)this.getDocument();

        try {
            int pos = 0;

            //date if right
            if(this.positionInWindow()){
                String dateToDisplay = message.getDate() + " ";
                sDoc.insertString(pos, dateToDisplay, dateStyle);
                pos+=dateToDisplay.length();
            }

            // name
            String nameToDisplay = message.getClientName();
            if(this.positionInWindow()) {// if there is no date after
                nameToDisplay += "\n";
            }
            sDoc.insertString(pos, nameToDisplay, nameStyle);
            pos+=nameToDisplay.length();

            // date if left
            if(!this.positionInWindow()){
                String dateToDisplay = " " + message.getDate() + "\n";
                sDoc.insertString(pos, dateToDisplay, dateStyle);
                pos+=dateToDisplay.length();
            }

            // message
            sDoc.insertString(pos, formattedMessage, messageStyle);
            pos+=formattedMessage.length();
        } catch (BadLocationException ignored) {

        }
    }

    /**
     * return the position of the message in the window (true = right, false = left)
     * @return the position of the message in the window (true = right, false = left)
     */
    private boolean positionInWindow(){
        if(this.message.isClientNamed(this.controller.getState().getName())){
            return Config.selfPositionMessageDisplay;
        }else{
            return !Config.selfPositionMessageDisplay;
        }
    }

}

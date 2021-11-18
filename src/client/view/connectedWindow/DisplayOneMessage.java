package client.view.connectedWindow;

import client.config.ColorPalette;
import client.config.Config;
import client.controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.*;

/**
 * display one message
 */
public class DisplayOneMessage extends JTextPane {

    private final String message;
    private final Controller controller;

    public DisplayOneMessage(String message, Controller controller){
        super();
        this.message = message;
        this.controller = controller;
        // name/content/date
        String[] split = this.message.split("/");

        String name = split[0];
        this.formatMessage(name, split[1]);
        if(name.compareTo(this.controller.getState().getName()) == 0){
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
    private void formatMessage(String name, String message){
        StringBuilder sb = new StringBuilder(message);
        for(int i = 1 ; i * Config.nbCharactersMaxOneLine < message.length(); i++){
            sb.insert(i * Config.nbCharactersMaxOneLine, "\n");
        }
        String formattedMessage = sb.toString();

        // right if its not me
        if(name.compareTo(this.controller.getState().getName()) != 0){
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
        try {
            int pos = 0;
            name += "\n";
            sDoc.insertString(pos, name, nameStyle);
            pos+=name.length();

            sDoc.insertString(pos, formattedMessage, messageStyle);
            pos+=formattedMessage.length();
        } catch (BadLocationException ignored) {

        }
    }

}

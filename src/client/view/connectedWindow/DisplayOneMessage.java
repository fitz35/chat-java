package client.view.connectedWindow;

import client.config.ColorPalette;
import client.controller.Controller;

import javax.swing.*;
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
        this.formatMessage();
        this.validate();
        this.repaint();
    }

    /**
     * format a message to display it
     */
    private void formatMessage(){
        // name/content/date
        String[] split = this.message.split("/");

        String name = split[0];
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

            sDoc.insertString(pos, split[1], messageStyle);
            pos+=split[1].length();
        } catch (BadLocationException ignored) {

        }
    }

}

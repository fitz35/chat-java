package client.view.connectedWindow;

import client.controller.Controller;
import client.view.Frame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * display the message and the possibilities to send
 */
public class ConnectedWindow extends Frame implements PropertyChangeListener {


    public ConnectedWindow(Controller controller) {
        super(controller);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

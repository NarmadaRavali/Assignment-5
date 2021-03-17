package model;

import controller.CommonConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ravikanth
 * @since 03-04-2021
 */
public class SymbolIO extends JButton {
    private final CommonConstants.Type type;
    private Boolean connected;

    public SymbolIO(CommonConstants.Type type) {
    	super();
        this.type = type;
        connected = false;
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    public CommonConstants.Type getType() {
        return type;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }
}

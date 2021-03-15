package model;

import controller.CommonConstants;

import javax.swing.*;
import java.awt.*;

public class SymbolIO extends JButton {
    private CommonConstants.Type type;
    private Boolean connected;

    public SymbolIO(CommonConstants.Type type) {
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

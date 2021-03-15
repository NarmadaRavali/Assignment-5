package model;

import controller.CommonConstants;

import javax.swing.*;

public class SymbolIO extends JButton {
    private CommonConstants.Type type;
    public SymbolIO(CommonConstants.Type type) {
        this.type = type;
    }

    public CommonConstants.Type getType() {
        return type;
    }
}

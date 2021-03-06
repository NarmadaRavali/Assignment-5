package controller;

import model.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikhil Hiremath
 * @Description: Class for storing all the constant values used throughout the
 *               codebase
 * @since 03-06-2021
 */
public class CommonConstants {

    public static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 800;
    public static final int SYMBOL_WIDTH = 180;
    public static final int SYMBOL_HEIGHT = 80;
    public static final String LEFT_PANEL_NAME = "Left Panel",
            RIGHT_PANEL_NAME = "Right Panel";
    public static final String SAVE = "Save", LOAD = "Load", SPACE = "New " +
            "Space";
    public static final Color LIGHT_GREY = new Color(235, 235, 235);
    public static final String[] symbolNames = {"(", ")", "<", ">", "@", "||"
            , "-"};
    public static final Map<String, Class<?>> symbolClasses;

    static {
        symbolClasses = new HashMap<>();
        symbolClasses.put("@", AtTheRateSymbol.class);
        symbolClasses.put(">", GreaterThanSymbol.class);
        symbolClasses.put("<", LessThanSymbol.class);
        symbolClasses.put("(", OpenParanthesisSymbol.class);
        symbolClasses.put(")", CloseParenthesisSymbol.class);
        symbolClasses.put("-", MinusSymbol.class);
        symbolClasses.put("||", PipeSymbol.class);
    }
}

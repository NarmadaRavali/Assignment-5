package controller;

import model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nikhil Hiremath
 * @since 03-06-2021
 */
public class CommonConstants {
    public enum Type {
        INPUT, OUTPUT
    }

    public static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 800;
    public static final String LEFT_PANEL_NAME = "Left Panel",
            RIGHT_PANEL_NAME = "Right Panel";
    public static final Color LIGHT_GREY = new Color(235, 235, 235);
    public static final String[] symbolNames = { "(", ")", "<", ">", "@", "|-","-|",
            "-" };
    public static final ArrayList<Integer> inputs = new ArrayList<Integer>(Arrays.asList(0,1,1,2,2,Integer.MAX_VALUE,1,1));
    public static final ArrayList<Integer> outputs = new ArrayList<Integer>(Arrays.asList(1,0,2,1,2,1,Integer.MAX_VALUE,1));
    public static final Map<String, Class<?>> symbolClasses;

    static {
        symbolClasses = new HashMap<>();
        symbolClasses.put("(", OpenParanthesisSymbol.class);
        symbolClasses.put(")", CloseParenthesisSymbol.class);
        symbolClasses.put(">", GreaterThanSymbol.class);
        symbolClasses.put("<", LessThanSymbol.class);
        symbolClasses.put("@", AtTheRateSymbol.class);
        symbolClasses.put("|-", OpenPipeSymbol.class);
        symbolClasses.put("-|", ClosePipeSymbol.class);
        symbolClasses.put("-", MinusSymbol.class);
        
    }
}

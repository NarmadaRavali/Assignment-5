package model;

import java.util.HashMap;

/**
 * @author Ravikanth
 * @Description: Class for mapping symbol values to symbol classes
 * @since 03-06-2021
 */
public class SymbolMap {
    public static final HashMap<String, Class<?>> symbolClasses;

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

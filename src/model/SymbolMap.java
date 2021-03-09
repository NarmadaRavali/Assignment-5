package model;

import java.util.HashMap;

/**
 *
 * @author Ravikanth
 * @since 03-06-2021
 * @Description: Class for mapping symbol values to symbol classes
 */
public class SymbolMap {
    public static HashMap<String, Class<?>> symbolClasses;
    static {
        symbolClasses = new HashMap<>();
        symbolClasses.put("@", AtTheRateSymbol.class);
        symbolClasses.put(">", GreaterthanSymbol.class);
        symbolClasses.put("<", LessthanSymbol.class);
    }
}

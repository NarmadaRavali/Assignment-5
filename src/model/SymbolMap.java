package model;

import java.util.HashMap;

public class SymbolMap {
    public static HashMap<String, Class<?>> symbolClasses;
    static {
        symbolClasses = new HashMap<>();
        symbolClasses.put("@", AtTheRateSymbol.class);
        symbolClasses.put(">", GreaterthanSymbol.class);
        symbolClasses.put("<", LessthanSymbol.class);
    }

}

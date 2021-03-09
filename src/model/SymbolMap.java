package model;

import java.util.HashMap;

/**
 * @author Ravikanth
 * @Description: Class for mapping symbol values to symbol classes
 * @since 03-06-2021
 */
public class SymbolMap {
    private HashMap<String, Class<?>> symbolClasses;

    public SymbolMap() {
        symbolClasses = new HashMap<>();
        symbolClasses.put("@", AtTheRateSymbol.class);
        symbolClasses.put(">", GreaterThanSymbol.class);
        symbolClasses.put("<", LessThanSymbol.class);
        symbolClasses.put("(", OpenParanthesisSymbol.class);
        symbolClasses.put(")", CloseParenthesisSymbol.class);
        symbolClasses.put("-", MinusSymbol.class);
        symbolClasses.put("||", PipeSymbol.class);
    }

    /**
     * @param value - name of the symbol
     * @return returns a subclass of symbol
     */
    public Class<?> getClass(String value) {
        return symbolClasses.get(value);
    }

}

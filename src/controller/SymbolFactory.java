package controller;

import model.Symbol;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
/**
 * @author Ravikanth
 * @Description: Factory class for creating Symbol objects
 * @since 03-12-2021
 */
public class SymbolFactory {
    private static Symbol symbol;

    public static Symbol createSymbol(JPanel panel,String symbolName, int x,
                                 int y){
        try {
            symbol = (Symbol) CommonConstants.symbolClasses
                    .get(symbolName)
                    .getDeclaredConstructor(int.class, int.class)
                    .newInstance(x, y);
            panel.add(symbol);
            if (panel.getName().equals("Left Panel")) {
                new DragEventListener(symbol);
            } else if (panel.getName().equals("Right Panel")) {
                new SymbolMouseHandler(panel, symbol);
            }

        }
        catch ( IllegalAccessException
                | NoSuchMethodException | InvocationTargetException
                | InstantiationException e) {
            e.printStackTrace();
        }
        return symbol;
    }
}

package controller;

import model.SymbolMap;
import view.RightPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Ravikanth
 * @Description: This class handles mouse drop listeners and adds a new symbol
 *               to the right panel.
 * @since 03-07-2021
 */
public class DropEventListener {

    public DropEventListener(RightPanel panel) {
        panel.setTransferHandler(new SymbolImportTransferHandler());
    }

    /**
     * This class adds a new Symbol instance in the Right Panel.
     */
    public static class SymbolImportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
        private static final long serialVersionUID = 1L;

        public SymbolImportTransferHandler() {
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                    if (value instanceof String) {
                        Component component = support.getComponent();
                        RightPanel panel = (RightPanel) component;
                        Point mousePosition = MouseInfo.getPointerInfo()
                                .getLocation();
                        Point panelPosition = panel.getLocationOnScreen();
                        int x = mousePosition.x - panelPosition.x;
                        int y = mousePosition.y - panelPosition.y;
                        JButton symbol = (JButton) SymbolMap.symbolClasses
                                .get(value.toString())
                                .getDeclaredConstructor(JComponent.class,
                                        int.class, int.class)
                                .newInstance(panel, x, y);
                        panel.repaint();

                    }
                } catch (IOException | IllegalAccessException
                        | NoSuchMethodException | InvocationTargetException
                        | InstantiationException
                        | UnsupportedFlavorException e) {
                    e.printStackTrace();
                }
            }

            return false;
        }
    }
}

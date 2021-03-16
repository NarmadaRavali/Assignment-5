package controller;

import view.RightPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

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
     * This class adds a new Symbol instance in the Right Panel whenever a
     * symbol from left panel is dragged and dropped on Right Panel
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
                        boolean canAddSymbol = true;
                        if (value.toString().equals("(")){
                            canAddSymbol = !panel.isOpenParen();
                            panel.setOpenParen(true);
                        }
                        else if (value.toString().equals(")")){
                            canAddSymbol = !panel.isCloseParen();
                            panel.setCloseParen(true);
                        }
                        if (canAddSymbol) {
                            Point mousePosition = MouseInfo.getPointerInfo()
                                    .getLocation();
                            Point panelPosition = panel.getLocationOnScreen();
                            int x = mousePosition.x - panelPosition.x;
                            int y = mousePosition.y - panelPosition.y;
                            SymbolFactory.createSymbol(panel, value.toString(), x, y);
                            panel.repaint();
                        }
                    }
                } catch (IOException | UnsupportedFlavorException e) {
                    e.printStackTrace();
                }
            }

            return false;
        }
    }
}

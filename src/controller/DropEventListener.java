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
 *
 * @author Ravikanth
 * @since 03-07-2021
 * @Description:
 */
public class DropEventListener {

    public DropEventListener(RightPanel panel) {
        panel.setTransferHandler(new SymbolImportTransferHandler());
    }

    public static class SymbolImportTransferHandler extends TransferHandler {


		private static final long serialVersionUID = 1L;
		public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;

        public SymbolImportTransferHandler() {
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                    if (value instanceof String) {
                        Component component = support.getComponent();
                        RightPanel panel = (RightPanel) component;
                        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
                        Point panelPosition = panel.getLocationOnScreen();
                        int x = mousePosition.x - panelPosition.x;
                        int y = mousePosition.y - panelPosition.y;
                        JButton symbol = (JButton) SymbolMap.symbolClasses.get(value.toString()).getDeclaredConstructor(JComponent.class,
                                int.class, int.class).newInstance(panel, x, y);
                        panel.repaint();

                        accept = true;

                    }
                } catch (IOException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException | UnsupportedFlavorException e) {
                    e.printStackTrace();
                }
            }

                return accept;
        }
    }
}

package controller;

import model.Symbol;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Ravikanth
 * @Description: This Class handles mouse drag listener and sends the value of
 *               the dragged symbol
 * @since 03-06-2021
 */
public class DragEventListener {
    public DragEventListener(Symbol symbol) {
        symbol.setTransferHandler(
                new SymbolExportTransferHandler(symbol.getText()));

        symbol.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                TransferHandler handle = button.getTransferHandler();
                handle.exportAsDrag(button, e, TransferHandler.COPY);
            }
        });
    }

    /**
     * This class stores and sends the value of the Symbol instance which is
     * being dragged.
     */
    public static class SymbolExportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
        private static final long serialVersionUID = 1L;
        private final String value;

        public SymbolExportTransferHandler(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new StringSelection(getValue());
        }

        @Override
        protected void exportDone(JComponent source, Transferable data,
                int action) {
            super.exportDone(source, data, action);
        }

    }

}

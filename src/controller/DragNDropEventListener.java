package controller;

import model.Symbol;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DragNDropEventListener {
    public DragNDropEventListener(Symbol symbol) {
        symbol.setTransferHandler(new ValueExportTransferHandler(symbol.getText()));

        symbol.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                TransferHandler handle = button.getTransferHandler();
                handle.exportAsDrag(button, e, TransferHandler.COPY);
            }
        });
    }

        public static class ValueExportTransferHandler extends TransferHandler {

            public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
            private final String value;

            public ValueExportTransferHandler(String value) {
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
                Transferable t = new StringSelection(getValue());
                return t;
            }

            @Override
            protected void exportDone(JComponent source, Transferable data, int action) {
                super.exportDone(source, data, action);
            }

        }

}

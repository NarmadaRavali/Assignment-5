package controller;

import model.Symbol;
import view.RightPanel;
import view.RightSpace;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Translate {

    private StringBuilder stringBuilder;
    private ArrayList<String> openParens,closeParens;

    public Translate() {
        stringBuilder = new StringBuilder();
        stringBuilder.append("digraph G {\n\t");
        openParens = new ArrayList<>();
        closeParens = new ArrayList<>();
    }

    public void writeToFile(String data){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save translation");
        int choice = fileChooser.showSaveDialog(null);

        if (choice == JFileChooser.APPROVE_OPTION) {
            try  {
                FileWriter writer = new FileWriter(
                        fileChooser.getSelectedFile() + ".txt");
                writer.write(data);
                writer.flush();
                writer.close();
            }
             catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void convert(Map<RightSpace, ConnectionGraph> graphMap){
        for (RightSpace space : graphMap.keySet()) {
            ConnectionGraph graph = graphMap.get(space);
            RightPanel panel =
                    RightPanel.getInstance();
            String name =
                    panel.getRightPanel().getTitleAt(
                            panel.getRightPanel().indexOfComponent(space));
            stringBuilder.append("subgraph ").append(name).append(
                    " {\n");
            stringBuilder.append(getLines(graph, space));
            stringBuilder.append("\t}\n");
        }
        openParens.forEach(text -> stringBuilder.append("\tstart -> ")
                .append(text).append(";\n"));
        closeParens.forEach(text -> stringBuilder.append("\t").append(text)
                .append(" -> end")
                .append(";\n"));
        stringBuilder.append("}");
        writeToFile(stringBuilder.toString());

    }

    private String getLines(ConnectionGraph edgeGraph, RightSpace tab) {
        StringBuilder lines = new StringBuilder();
        Map<Symbol, ArrayList<Symbol>> edges = edgeGraph.getEdges();
        Set<Symbol> outputs = edges.keySet();
        int panelIndex = RightPanel.getInstance().getRightPanel()
                .indexOfComponent(tab);
        String panelTag = String.valueOf((char)(panelIndex + 97));
        for (Symbol output : outputs) {
                int symbol1Index = getSymbolIndex(output,
                        tab);

                String tmp =  panelTag + symbol1Index ;

                if (output.getText().equals("("))
                    openParens.add(tmp);

                for (Symbol input : edges.get(output)) {
                    int symbol2Index = getSymbolIndex( input, tab);
                    if (input.getText().equals(")"))
                        closeParens.add(panelTag +  symbol2Index);

                    lines.append("\t\t").append(tmp).append(" -> ")
                            .append(panelTag)
                            .append(symbol2Index).append(";")
                            .append(System.lineSeparator());
                }
            }
        return lines.toString();
    }

    private int getSymbolIndex(Symbol symbol, RightSpace rightSpace) {
        int i = 0;
        for (Component component : rightSpace.getComponents()) {
            if (symbol == component) {
                return i;
            }
            i++;
        }
        return -1;
    }
}

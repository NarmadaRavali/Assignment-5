/**
 * 
 */
package controller;

import model.Symbol;
import view.RightPanel;
import view.RightSpace;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Nikhil Hiremath
 * @since 03-12-2021
 * @Description: Stores all the data in the space tabs including symbol's
 *               properties in a .txt file.
 *
 */

public class SaveApplication {

	public SaveApplication(JFrame mainFrame) {
		super();
		JLabel fileName = new JLabel("no file selected");
		JFileChooser fileChooser = new JFileChooser();
		int choice = fileChooser.showSaveDialog(null);

		if (choice == JFileChooser.APPROVE_OPTION) {
			try (FileWriter fw = new FileWriter(fileChooser.getSelectedFile() + ".txt")) {
				JTabbedPane rightPannelTab = RightSpace.getInstance()
						.getRightPanel();
				int tabIndex = 0;
				for (Component i : rightPannelTab.getComponents()) {
					String tabTitle = rightPannelTab.getTitleAt(tabIndex);
					fw.write("Tab" + ";" + tabIndex + ";" + tabTitle
							+ System.lineSeparator());
					tabIndex++;
				}
				tabIndex = 0;
				for (Component i : rightPannelTab.getComponents()) {
					RightPanel tab = (RightPanel) i;

					for (Component j : tab.getComponents()) {
						Symbol symbol = (Symbol) j;

						String name = "d";
						String x = Integer.toString(symbol.getX());
						String y = Integer.toString(symbol.getY());
						String userInput = symbol.getUserInput();

						fw.write("shape" + ";" + name + ";" + x + ";" + y + ";"
								+ userInput + ";" + tabIndex
								+ System.lineSeparator());
					}
					tabIndex++;
				}
				// String lines = getLines();
				// fw.write(lines);

				fw.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
		} else
			fileName.setText("the user cancelled the operation");
	}

}

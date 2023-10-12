package Functions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.util.Arrays;

public class MergeWindow {
    static String[] filePaths = {};
    public static void mergePDFSWindow() {

        JFrame mainFrame = new JFrame("PDF Merger");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500,300);

        JPanel buttonPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        JPanel fileLabel = new JPanel();

        JLabel listOfFiles = new JLabel("");

        JButton addFileButton = new JButton("Add PDF File");
        addFileButton.setMnemonic(KeyEvent.VK_A);
        addFileButton.setActionCommand("addFile");
        addFileButton.addActionListener(e -> {
            filePaths = PDFHarborFunctions.appendToArray(filePaths, PDFHarborFunctions.getFilePath());
            StringBuilder outputForLabel = new StringBuilder();
            outputForLabel.append("<html>");
            for (String filePath : filePaths) {
                String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
                outputForLabel.append("<br>").append(fileName);
            }

            listOfFiles.setText(outputForLabel.toString());
        });

        JTextField inputFileName = new JTextField();
        inputFileName.setText("PDFHarbor-" + LocalDateTime.now());
        inputFileName.setColumns(25);

        JButton enterButton = new JButton("Merge PDFs");
        addFileButton.setMnemonic(KeyEvent.VK_E);
        enterButton.setActionCommand("enter");
        enterButton.addActionListener(e -> MergeFunction.mergePDF(filePaths, inputFileName.getText() + ".pdf"));

        JButton setSaveLocationButton = new JButton("Set Save Location");
        setSaveLocationButton.setMnemonic(KeyEvent.VK_S);
        setSaveLocationButton.setActionCommand("changeSaveLocation");
        setSaveLocationButton.addActionListener(e -> PDFHarborFunctions.setSavePath());


        buttonPanel.add(addFileButton);
        buttonPanel.add(enterButton);
        buttonPanel.add(setSaveLocationButton);

        inputPanel.add(inputFileName);

        fileLabel.add(listOfFiles);

        mainFrame.getContentPane().add(fileLabel, BorderLayout.NORTH);
        mainFrame.getContentPane().add(inputPanel, BorderLayout.CENTER);
        mainFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }
}

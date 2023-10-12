package Functions;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class PDFHarborFunctions {
    /**
     * This code snippet defines a method called appendToArray that takes in an array of strings (inputArray) and a
     * string (inputString). It creates a new array called newArray with a length that is one element longer than
     * inputArray. It then copies all the elements from inputArray to newArray using the System.arraycopy method.
     * Finally, it adds inputString to the last index of newArray and returns it.
     * Appends a given string to the end of a given string array and returns the new array.
     *<p></p>
     * @param  inputArray  the original string array
     * @param  inputString the string to be appended
     * @return             the new string array with the appended string
     */
    public static String[] appendToArray(String[] inputArray, String inputString){

        String[] newArray = new String[inputArray.length + 1];

        System.arraycopy(inputArray, 0, newArray, 0, inputArray.length);
        newArray[inputArray.length] = inputString;

        return newArray;
    }

    /**
     * This code defines a method called getSettingInfo that takes settingName as input and returns a savePath as
     * output. It reads the contents of a file called "src/SystemSettings.txt" and searches for a line that starts with
     * the settingName. If found, it extracts the save path from that line and assigns it to savePath. If the file is
     * not found, it prints an error message.
     *<p></p>
     * @param  settingName   the name of the setting to retrieve information for
     * @return               the information related to the specified setting, or null if not found
     */
    public static String getSettingInfo(String settingName){
        File systemSettingsFile = new File("src/SystemSettings.txt");
        String[] systemSettings = {};
        String settingInfo = null;

        try {
            Scanner fileReader = new Scanner(systemSettingsFile);
            while (fileReader.hasNextLine()) {
                systemSettings = appendToArray(systemSettings, fileReader.nextLine());
            }

            for (String str: systemSettings){
                if (str.length() > settingName.length() && str.startsWith(settingName + ": ")){
                    settingInfo = str.substring(settingName.length() + 2);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Method to handle FileNotFoundException in PDFHarborFunctions.getSavePath() not coded");
        }
        
        return settingInfo;
    }
    /**
     * This code snippet defines a static method named getOutputDirectory that returns a string. It checks if a certain
     * setting called "savePath" is null. If it is null, it prints a default output directory and returns it. Otherwise,
     * it retrieves the value of the "savePath" setting and returns it.
     * <p></p>
     * @return  the output directory path as a string
     */
    public static String getOutputDirectory(){
        if (Objects.equals(getSettingInfo("savePath"), null)){
            System.out.println((System.getProperty("user.home") + "/Documents/FlowHarbor/PDFHarbor"));
            return (System.getProperty("user.home") + "/Documents/FlowHarbor/PDFHarbor");
        }
        else {
            return getSettingInfo("savePath");
        }

    }
    /**
     * This code snippet creates a new directory using the specified save path. If the directory creation is
     */
    public static void createPDFHarborDirectory() throws RuntimeException{
        File newFile = new File(getOutputDirectory());
        System.out.println(getOutputDirectory());
        boolean createDirectory = newFile.mkdirs();
        System.out.println(createDirectory);
    }
    /**
     * Modifies the system settings file with the given setting name and value.
     *
     * @param  settingName   the name of the setting to be modified
     * @param  settingValue  the new value for the setting
     */
    public static void modifySettings(String settingName, String settingValue){
        File systemSettingsFile = new File("src/SystemSettings.txt");
        String[] systemSettings = {};

        try {
            Scanner fileReader = new Scanner(systemSettingsFile);

            while (fileReader.hasNextLine()) {
                systemSettings = appendToArray(systemSettings, fileReader.nextLine());
            }

            for (int x = 0; x < systemSettings.length; x++){
                if (systemSettings[x].startsWith(settingName + ":")){
                    systemSettings[x] = settingName + ":" + settingValue;
                }
            }

            System.out.println(Arrays.toString(systemSettings));

            if(systemSettingsFile.delete()){
                systemSettingsFile.createNewFile();
            } else {
                System.out.println("Method to handle error in PDFHarborFunctions.modifySettings() not coded");
            }

            try (FileWriter fileWriter = new FileWriter(systemSettingsFile)){
                for (String str: systemSettings){
                    fileWriter.write(str + "\n");
                }

            } catch (Exception e){
                System.out.println("Method to handle error in PDFHarborFunctions.modifySettings() not coded");
            }

        } catch (Exception e){
            System.out.println("Method to handle error in PDFHarborFunctions.modifySettings() not coded");
        }
    }

    /**
     * Sets the save path for files through the default file chooser program
     */
    public static void setSavePath(){
        final JFileChooser newSavePath = new JFileChooser();
        newSavePath.setDialogTitle("Directory To Save Files");
        newSavePath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = newSavePath.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION){
            String selectedDirectory = newSavePath.getSelectedFile().getAbsolutePath();
            modifySettings("savePath", selectedDirectory);
        } else {
            System.out.println("Method to handle error in PDFHarborFunctions.setSavePath() not coded");
        }
    }

    /**
     * Retrieves the file path of a selected PDF file.
     *
     * @return the absolute path of the selected PDF file
     */
    public static String getFilePath(){
        final JFileChooser newFilePath = new JFileChooser();
        newFilePath.setDialogTitle("Select PDF File");
        newFilePath.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
        newFilePath.setFileFilter(filter);

        newFilePath.showOpenDialog(null);

        return newFilePath.getSelectedFile().getAbsolutePath();
    }
}

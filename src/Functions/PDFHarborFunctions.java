package Functions;

import java.io.*;
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
        String savePath = null;

        try {
            Scanner fileReader = new Scanner(systemSettingsFile);
            while (fileReader.hasNextLine()) {
                systemSettings = appendToArray(systemSettings, fileReader.nextLine());
            }

            for (String str: systemSettings){
                if (str.length() > settingName.length() && str.startsWith(settingName)){
                    savePath = str.substring(settingName.length() + 2);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Method to handle FileNotFoundException in PDFHarborFunctions.getSavePath() not coded");
        }
        
        return savePath;
    }
    /**
     * This code snippet creates a new directory using the specified save path. If the directory creation is
     * unsuccessful, it throws a runtime exception with an error message.
     *<p></p>
     * @throws RuntimeException   if the directory creation fails
     */
    public static void createPDFHarborDirectory() throws RuntimeException{
        File newFile = new File(PDFHarborFunctions.getSettingInfo("savePath"));
        boolean success = newFile.mkdir();

        if (!success) {
            throw new RuntimeException("Failed to create directory");
        }
    }
}

package Functions;

import java.io.*;
import java.util.Scanner;

public class PDFHarborFunctions {
    public static String[] appendToArray(String[] inputArray, String inputString){
        String[] newArray = new String[inputArray.length + 1];

        System.arraycopy(inputArray, 0, newArray, 0, inputArray.length);
        newArray[inputArray.length] = inputString;

        return newArray;
    }

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

    public static void createPDFHarborDirectory() throws RuntimeException{
        File newFile = new File(PDFHarborFunctions.getSettingInfo("savePath"));
        boolean success = newFile.mkdir();

        if (!success) {
            throw new RuntimeException("Failed to create directory");
        }
    }
}

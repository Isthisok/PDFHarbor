package Functions;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MergeFunction {
    /**
     * This code defines a method called mergePDF that takes in an array of file directories and a file name as
     * parameters. It merges multiple PDF files into a single PDF file. It first determines the output path for the
     * merged PDF file by appending the file name to a save path retrieved from a function call. Then, it checks if a
     * file already exists at the output path and appends a count to the file name if necessary to avoid overwriting
     * existing files.
     * <p></p>
     * Next, it creates a PDFMergerUtility object and adds each file in the file directories to the merger utility. It
     * also sets the destination file name to the output path and merges the documents using a specific memory usage
     setting.
     * <p></p>
     * If an IOException is caught, it checks if the error message indicates that the file or directory does not
     * exist. If that's the case, it creates the necessary directory using a function call and recursively calls the
     * mergePDF method again with the same parameters. If any other type of RuntimeException is caught, it prints a
     * message indicating that the method to handle the exception is not coded.
     *<p></p>
     * @param  fileDirectories   an array of file directories containing the PDF files to be merged
     * @param  fileName          the desired name of the merged PDF file
     */
    public static void mergePDF(String[] fileDirectories, String fileName) {
        String outputPath = PDFHarborFunctions.getSettingInfo("savePath") + "/" + fileName;

        int count = 1;
        while ((new File(outputPath)).isFile()){
            outputPath = PDFHarborFunctions.getSettingInfo("savePath") + "/" + count + "-" + fileName;
            count++;
        }

        try{
            PDFMergerUtility newPDF = new PDFMergerUtility();

            for (String fileDir : fileDirectories){
                File currFile = new File(fileDir);
                newPDF.addSource(currFile);
            }

            newPDF.setDestinationFileName(outputPath);
            newPDF.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly().streamCache);

        } catch(IOException IOe){

            if (Objects.equals(IOe.getMessage(), outputPath + " (No such file or directory)")){
                try {
                    PDFHarborFunctions.createPDFHarborDirectory();
                    mergePDF(fileDirectories, fileName);
                } catch (RuntimeException Re) {
                    System.out.println("Method to handle RuntimeException in MergeFunction.mergePDF() not coded");
                }
            }
        }
    }
}
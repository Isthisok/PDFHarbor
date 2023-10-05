package Functions;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MergeFunction {
    /* -------------------------------------------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------------------------------------------- */
    public static void mergePDF(String[] fileDirectories, String fileName) {
        String outputPath = PDFHarborFunctions.getSettingInfo("savePath") + "/" + fileName;
        System.out.println(outputPath);

        int count = 1;
        while ((new File(outputPath)).isFile()){
            System.out.println(count);
            outputPath = PDFHarborFunctions.getSettingInfo("savePath") + "/" + count + "-" + fileName;
            System.out.println(outputPath);
            count++;
        }

        System.out.println(count);
        System.out.println(outputPath);

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
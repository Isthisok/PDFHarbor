import Functions.MergeFunction;

/**
 * <h1>PDFHarbor</h1>
 * A free, open-source, PDF editor part of the line of applications under FlowHarbor.
 * <h2>Features:</h2>
 * - PDF Merger
 * <p></p>
 * @author Aditya Braganza
 * @version 0.1
 * @since 2023-10-05
 */
public class test {
    public static void main(String[] args) {
        String[] toMerge = {"/Users/adi/Documents/SA_1_-_Mini_IO_-_Task_Guidelines.pdf", "/Users/adi/Documents/_DP_1_AI_HL_FA_2__QP___MS_.pdf"};
        MergeFunction.mergePDF(toMerge, "test.pdf");
    }
}

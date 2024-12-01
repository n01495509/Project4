import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type File handler.
 */
public class FileHandler {
    private static String surveyFile;
    private static FileWriter fileOutput;
    private static PrintWriter printWriter;
    private static final String HEADER = "DateTime,FirstName,LastName,PhoneNumber,Email,Sex,Water,Meals,Wheat,Sugar,Dairy,Miles,Weight";

    /**
     * Instantiates a new File handler.
     */
    public FileHandler() {
        surveyFile = "survey_results.csv";
        String fileHeader = HEADER;
        try {
            fileOutput = new FileWriter(surveyFile, false);
            printWriter = new PrintWriter(fileOutput);
            printWriter.println(fileHeader);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write results.
     *
     * @param surveyData the survey data
     */
    public void writeResults(String surveyData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String createdAt = dateFormat.format(date);
        try {
            fileOutput = new FileWriter(surveyFile, true);
            printWriter = new PrintWriter(fileOutput);
            printWriter.println(createdAt + "," + surveyData);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}






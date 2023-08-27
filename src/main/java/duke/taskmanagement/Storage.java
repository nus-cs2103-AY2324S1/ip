package duke.taskmanagement;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public void writeToFile_exceptionThrown(String filePath, String textToAdd) {
        try {
            writeToFile(filePath, textToAdd);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

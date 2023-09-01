import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

public class Save {
    TaskList taskList;
    private String saveFilePath;
    private static final String tempFilePath = "temp.txt";

    public Save(TaskList taskList, String saveFilePath) throws IOException {
        this.taskList = taskList;
        this.saveFilePath = saveFilePath;
        saveFile();
        };

    public void saveFile() throws IOException {

        File f = new File(saveFilePath);
        if (!f.exists()) {
            FileWriter fw = new FileWriter(saveFilePath, true);
            fw.write("");
            fw.close();
        }

        for (int i=0; i<taskList.size(); i++) {
            String message = String.format("%s", taskList.get(i).getTaskAsString());
            try {
                addFileContents(tempFilePath, message);
                // System.out.println(message + " added ");  // For debugging
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
        };

        Files.copy( Paths.get(tempFilePath), Paths.get(saveFilePath), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(tempFilePath));

    }

    public static void addFileContents(String filePath, String contents) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(contents + "\n");
        fw.close();
    }
}

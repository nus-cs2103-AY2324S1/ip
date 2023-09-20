package functions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * A utility class for saving a file.
 */
public class Save {
    private TaskList taskList;
    private String saveFilePath;
    private final String TEMP_FILE_PATH = "temp.txt";

    /**
     * A public constructor to initialize Save
     *
     * @param taskList  a task list to store tasks
     * @param saveFilePath file path of saved file
     */
    public Save(TaskList taskList, String saveFilePath) {
        this.taskList = taskList;
        this.saveFilePath = saveFilePath;
        saveFile();
    };

    /**
     * Writes the task list into a .txt file specified by user
     */
    public void saveFile() {

        try {
            File f = new File(saveFilePath);
            if (!f.exists()) {
                FileWriter fw = new FileWriter(saveFilePath, true);
                fw.write("");
                fw.close();
            }

            if (taskList.size() == 0) {
                FileWriter fw = new FileWriter(TEMP_FILE_PATH, true);
                fw.write("");
                fw.close();
            }


            for (int i = 0; i < taskList.size(); i++) {
                String message = String.format("%s", taskList.get(i).getTaskAsString());
                addFileContents(TEMP_FILE_PATH, message);
            };

            Files.copy(Paths.get(TEMP_FILE_PATH), Paths.get(saveFilePath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(TEMP_FILE_PATH));

        } catch (IOException e) {
            System.out.println("Error in saving");
        }
    }

    /**
     * Adds contents into save file
     */
    public static void addFileContents(String filePath, String contents) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(contents + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to create file. Please try again later.");
        }
    }
}

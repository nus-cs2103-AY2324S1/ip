package functions;

import functions.TaskList;

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

    /**
     * A public constructor to initialize functions.Save
     *
     * @param taskList  a task list to store tasks
     * @param saveFilePath file path of saved file
     */
    public Save(TaskList taskList, String saveFilePath) {
        this.taskList = taskList;
        this.saveFilePath = saveFilePath;
        saveFile();
        };

    public void saveFile() {

        try {

            File f = new File(saveFilePath);
            if (!f.exists()) {
                FileWriter fw = new FileWriter(saveFilePath, true);
                fw.write("");
                fw.close();
            }

            if (taskList.size() == 0) {
                FileWriter fw = new FileWriter(tempFilePath, true);
                fw.write("");
                fw.close();
            }


            for (int i = 0; i < taskList.size(); i++) {
                String message = String.format("%s", taskList.get(i).getTaskAsString());
                addFileContents(tempFilePath, message);
            }
            ;

            Files.copy(Paths.get(tempFilePath), Paths.get(saveFilePath), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(tempFilePath));
        } catch (IOException e) {
            System.out.println("Error in saving");
        }
    }

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

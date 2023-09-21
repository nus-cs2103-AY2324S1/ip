package phi.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents all storage-related methods (reading from file, checking file paths etc.)
 */
public class Storage {
    private File txtFile;
    private Path filePath;
    private String msg;

    /**
     * Constructor for a new Storage instance.
     * If file / file path does not exist, the .txt file and corresponding directories will be created.
     *
     * @param pathString File path for the saved tasklist.txt file (to be read and written to)
     */
    public Storage(String pathString) {
        assert pathString.equals("./data/tasklist.txt") : "Wrong path for this project";
        this.txtFile = new File(pathString);
        this.filePath = Paths.get(pathString);

        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                msg = "No file found, new .txt file created!";
            } catch (IOException e) {
                msg = e.getMessage();
            }
        }
    }

    /**
     * Reads the input of the stored tasklist .txt file and returns the saved information
     *
     * @return TaskList object containing the tasks stored in tasklist.txt
     */
    public TaskList readFromFile() {
        TaskList tasks = new TaskList();
        try {
            Scanner txtScanner = new Scanner(txtFile);
            while (txtScanner.hasNextLine()) {
                tasks.addFromTxt(txtScanner.nextLine());
            }
            txtScanner.close();
            msg = String.format("There's %d items in the list\nSay \"list\" to see them", tasks.getSize());
        } catch (FileNotFoundException e) {
            msg = "Something went wrong... There's still no file to be found, creating a new one now";
            return new TaskList();
        }
        return tasks;
    }

    /**
     * Writes the output of PHI's current tasklist to the .txt file in storage.
     *
     * @param taskInput PHI's current TaskList object
     */
    public void writeToFile(TaskList taskInput) {
        try {
            FileWriter output = new FileWriter(txtFile);
            output.write(taskInput.outputList());
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public String getMsg() {
        return this.msg;
    }

}

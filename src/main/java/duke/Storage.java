package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasks.TaskList;
import duke.tasks.Task;

/**
 * Implements a storage class to allow the bot to be able to save tasks
 */

public class Storage {
    private String filePath;
    private File file;
    private FileReader fr;
    private Scanner sc;

    /**
     * Constructor of the storage class
     * 
     * @param filePath
     * @throws IOException if file is not found or created
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (file.createNewFile()) {
            System.out.println("Creation of File in process, please be patient");
        }
        this.fr = new FileReader(file);
    }

    /**
     * Describes the process of saving the taskList into a file
     * 
     * @param taskList Takes in the original taskList
     * @throws IOException if there is no file for the contents in the taskList
     *                     to be recorded
     */
    // solution for save adapted from asdfghjkxd
    // Storage.java write method
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }

    /**
     * Allows the contents from the file to be loaded
     * 
     * @return a scanner to read the contents form the file
     */
    public Scanner load() {
        if (sc != null) {
            return sc;
        } else {
            sc = new Scanner(fr);
            return sc;
        }
    }
}

package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Storage.
 */
public class Storage {
    private String filePath;
    private File directory;
    private File file;

    /**
     * Constructs a Storage object.
     * 
     * @param filePath Path to the file to be loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file in the file path.
     * 
     * @return ArrayList of type Task loaded from the file.
     * @throws DukeException If there is an error loading the file.
     */
    public ArrayList<Task> load() throws DukeException {
        // load tasks from storage using filePath given
        ArrayList<Task> taskList = new ArrayList<Task>();
        // check whether directory exists from filePath given
        // check the directory until the file name from the file path
        // if directory doesn't exist, create directory 
        this.directory = new File(filePath.substring(0, filePath.lastIndexOf("/")));
        this.file = new File(filePath);
        
        if (directory.exists() && file.exists()) {
            // load the data into taskList
            try {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    // save to taskList
                    taskList.add(Task.parseTask(s.nextLine()));
                }
                s.close();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        return taskList;
    }

    /**
     * Saves tasks to the file in the file path.
     * @param tasks TaskList to be saved.
     * @throws DukeException If there is an error saving the file.
     */
    public void save(TaskList tasks) throws DukeException {
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }

        // save tasks to storage using filePath given
        // Guard clause: if directory doesn't exist, create directory
        // Guard clause: if file doesn't exist, create file
        try {
            FileWriter fWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                fWriter.write(tasks.getTaskItemSaveString(i) + "\n");
            }
            fWriter.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }

}

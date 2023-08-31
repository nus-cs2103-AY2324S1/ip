package duke;

import duke.tasklist.TaskList;

import duke.tasks.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Storage class to handle file storage, creation and reading of saved file.
 */
public class Storage {

    static final String FILE_PATH = "./data/dataFile";

    TaskList taskList;

    /**
     * Constructor of the duke.Storage class.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     */
    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }


    /**
     * Saves the current history array to the file
     */
    public void saveFile() {
        try {
            FileOutputStream dataFileStream = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objectStream = new ObjectOutputStream(dataFileStream);

            objectStream.writeObject(taskList.get());

            objectStream.close();
        } catch (Exception e) {
            System.out.println("File is not found! But this shouldn't happen LOL");
        }
    }

    /**
     * Reads the file to populate the history array
     */
    public void readFile() {
        try {
            File dataFile = new File(FILE_PATH);
            if (dataFile.exists() && (!dataFile.isDirectory())){
                FileInputStream dataFileStream = new FileInputStream(dataFile);
                ObjectInputStream objectStream = new ObjectInputStream(dataFileStream);
                this.taskList.set((ArrayList<Task>) objectStream.readObject());
                objectStream.close();
            } else {
                Path parentDir = Paths.get(FILE_PATH).getParent();
                Files.createDirectories(parentDir);
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("File is not found! But this shouldn't happen LOL");
        } catch (Exception e) {
            System.out.println("There is an error occurring, " + e.getMessage());
        }
    }

}

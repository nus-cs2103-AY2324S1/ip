package duke;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * duke.Storage class to handle file storage, creation and reading of saved file.
 */
public class Storage {

    static final String FILE_PATH = "./data/dataFile";

    TaskList taskList;

    /**
     * Contructor of the duke.Storage class.
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
            try {
                boolean hasFile = dataFile.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            FileInputStream dataFileStream = new FileInputStream(dataFile);
            if (dataFileStream.available() > 0) {
                ObjectInputStream objectStream = new ObjectInputStream(dataFileStream);
                this.taskList.set((ArrayList<Task>) objectStream.readObject());
                objectStream.close();
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("File is not found! But this shouldn't happen LOL");
        } catch (Exception e) {
            System.out.println("There is an error occurring, " + e.getMessage());
        }
    }

}

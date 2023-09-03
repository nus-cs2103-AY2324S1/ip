package duke;

import duke.exceptions.IncompleteDescriptionException;
import duke.exceptions.InvalidTaskIndexException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Contains methods to access data memory from the hard drive.
 */
public class Storage {
    private File file;

    /**
     * Constructor for Storage class.
     *
     * @param filePath The filepath of the data to be accessed.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads a taskList with data from a task file.
     *
     * @param taskList The taskList to be loaded with data.
     */
    public void loadTaskFile(TaskList taskList) {
        try {
            if (!new File("data").isFile()) {
                new File("data").mkdir();
            }
            Scanner fileReader = new Scanner(this.file);
            while (fileReader.hasNext()) {
                taskList.addTask(Parser.dataToTask(fileReader.nextLine()));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No previous task list found");
        } catch (IncompleteDescriptionException ex) {
            System.out.println("Something went wrong!");
        }
    }

    /**
     * Writes data from the task list to the task file storage.
     *
     * @param taskList The taskList containing data to be written into the task file.
     */
    public void writeTaskFile(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(this.file.getAbsoluteFile());
            String newTaskData = "";
            for (int i = 0; i < taskList.size(); i++) {
                newTaskData += taskList.getTask(i).compressData() + "\n";
            }
            writer.write(newTaskData);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Cannot update task file");
        } catch (InvalidTaskIndexException ex) {
            System.out.println("OOPS! Something went wrong!");
        }
    }

}

package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class represents a storage system for task data.
 * It provides methods to handle loading and saving task data to/from a file.
 */
public class Storage {
    private final File tasks;
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file used for storing task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new File(filePath);
        this.tasks.deleteOnExit();
    }

    /**
     * Prints the contents of the task list.
     */
    public String printFileContents() {
        try {
            Scanner s = new Scanner(tasks);
            StringBuilder stringBuilder = new StringBuilder();

            if (!s.hasNext()) {
                return "Error: There are no items in the list!";
            }

            while (s.hasNext()) {
                stringBuilder.append(s.nextLine()).append(System.lineSeparator());
            }

            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            return "Error: There are no items in the list!";
        }
    }

    /**
     * Writes the task list contents to the file.
     */
    public void writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.displayList());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads task data from the file.
     *
     */
    public void loadTasks() throws DukeException {
        File file = new File(this.filePath);
        File rootDirectory = file.getParent() == null ? new File("parent") : new File(file.getParent());

        if (!rootDirectory.exists()) {
            if (!rootDirectory.mkdirs()) {
                throw new DukeException("Unable to create the directory: " + rootDirectory.getAbsolutePath());
            }
        }

        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new DukeException("Unable to create the file: " + file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error while creating the file: " + e.getMessage());
        }
    }

}

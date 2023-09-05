package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to handle storage of tasks
 */
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tasks from a file and processes them based on their types.
     * The tasks are parsed and passed to respective handler methods.
     * Handles Todo, duke.Deadline, and duke.Event tasks stored in the file.
     */
    public String handleReadAllTasksFromFile() throws FileNotFoundException {

        try {
            File obj = new File(filePath);
        } catch (Exception e) {
            System.out.println("Please create a folder at the specified folder : src/data/duke.txt");
        }

        String tasks = "";
        try {
            File obj = new File(filePath);
            Scanner reader = new Scanner(obj);
            while (reader.hasNextLine()) {
                tasks += reader.nextLine() + "\n";
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Please create a folder at the specified folder : src/data/duke.txt");
        }

        return tasks;
    }

    /**
     * Writes changes in the task list to the file.
     * Each task is written as a formatted string in the file.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void handleChangesInFile(ArrayList<Task> list) throws IOException {
        try {
            Writer writer = new FileWriter(filePath, false);

            for (int i = 0; i < list.size(); i++) {
                writer.append("" + (i + 1)).append(".").append(list.get(i).toString()).append("\n");
            }

            writer.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}

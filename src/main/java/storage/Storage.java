package storage;

import tasks.Task;

import java.io.*;
import java.util.ArrayList;

/**
 * This class handles loading and saving user inputs for
 * continuity between sessions. Inputs are formatted correctly before
 * saved into the file path specified.
 */
public class Storage {

    /**
     * The file to load from and save into.
     */
    private static final String FILE_PATH = "data/duke.txt";

    /**
     * Constructs a Storage object.
     * Creates the directory and file of the specified file path if it does not already exist.
     */
    public Storage() {
        File directory = new File("data");
        File file = new File(FILE_PATH);

        try {
            if (!directory.exists()) {
                directory.mkdir();
                file.createNewFile();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the save data from the previous session.
     *
     * @return String representation of the Task objects created in the previous session.
     */
    public String load() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            reader.close();

            return content.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Saves the current task list into the specified file path.
     *
     * @param taskList A TaskList object that stores all current tasks.
     */
    public void save(ArrayList<Task> taskList) {
        File file = new File(FILE_PATH);

        try {
            FileWriter writer = new FileWriter(file, false);

            for (int i = 0; i < taskList.size(); i++) {
                String content = taskList.get(i).toText();
                writer.write(content);
                writer.write("\n");
            }

            writer.close();
            System.out.println("Updated task list saved under data/duke.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

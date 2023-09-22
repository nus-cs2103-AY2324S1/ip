package storage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import tasks.Task;

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

        createFileIfDoesNotExist(directory, file);
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

    /**
     * Creates the text file to save to if it does not exist.
     *
     * @param directory The directory containing the file.
     * @param file The file.
     */
    public void createFileIfDoesNotExist(File directory, File file) {
        try {
            if (!directory.exists()) {
                directory.mkdir();
                boolean var = file.createNewFile();
                assert var == true : "var should be true";
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package cheems;

import cheems.exceptions.InputOutputException;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * Represents the text database that stores the data.
 * Contains methods that interacts with the database and current task list during program run.
 */
public class Storage {

    /**
     * Each Storage object has a file field storing the file to read and write from.
     */
    private final File file;
    /**
     * This is the instance of Storage singleton object that is always used.
     */
    private static Storage instance;

    /**
     * This private constructor is called only by getInstance(String filepath).
     * Creates the file should it not exist.
     * Stores the file in this.file field of the class, for easy reference by other methods.
     *
     * @param filePath The filepath used to find the text file for storing task data.
     * @throws InputOutputException If fails to create the new file.
     */
    private Storage(String filePath) throws InputOutputException {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            this.file = f;
        } catch (IOException e) {
            throw new InputOutputException("Failed to create a new data file!");
        }
    }

    /**
     * Acts as an overloaded constructor of the Storage class, only called upon startup of the chatbot.
     * Returns a new Storage object based on the filepath given as the database text location.
     * Stores this object in the static singleton field for global access.
     *
     * @param filepath Text database location.
     * @return A Storage object.
     */
    public static Storage getInstance(String filepath) {
        instance = new Storage(filepath);
        return instance;
    }

    /**
     * Acts as an overloaded constructor of the Storage class, called by all methods except Cheems.main().
     * Retrieves the singleton Storage object from static field instance.
     *
     * @return A Storage object.
     */
    public static Storage getInstance() {
        return instance;
    }

    /**
     * Reads tasks from database file.
     * Calls Tasklist.addTask() to add these into the tasklist maintained during chatbot run.
     *
     * @throws InputOutputException If fails to find the file.
     */
    public void loadData() throws InputOutputException {
        try (Scanner s = new Scanner(this.file)) {
            String input;
            while (s.hasNextLine()) {
                input = s.nextLine();
                String[] taskLine = input.split("\\|");
                Tasklist.loadTaskFromDatabase(taskLine);
            }
        } catch (FileNotFoundException e) {
            String msg = String.format("I cannot find %s! May be accidental deletion, " +
                    "try restart cheems.Cheems!", file.getName());
            throw new InputOutputException(msg);
        }
    }

    /**
     * Saves a new task to the database text.
     *
     * @param taskLine The line to be written to the database, formatted based on database specification.
     * @throws InputOutputException If fails to write to file.
     */
    public void saveNewTask(String taskLine) throws InputOutputException {
        try {
            FileWriter fw = new FileWriter(this.file, true);
            fw.write(taskLine + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new InputOutputException("I cannot open the file for writing!");
        }
    }

    /**
     * Updates the specific task in the database with either of the following actions: delete, mark, unmark.
     * Side effect: Overwrites the whole file.
     * @throws InputOutputException If fails to read or write to file.
     */
    public void updateWholeDatabase() throws InputOutputException {
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(Tasklist.taskListToStorage());
            fw.close();
        } catch (IOException e) {
            throw new InputOutputException("I cannot open the file for writing!");
        }
    }
}

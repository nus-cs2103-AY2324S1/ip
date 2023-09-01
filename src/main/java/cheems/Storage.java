package cheems;

import cheems.exceptions.InputOutputException;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the text database that stores the data.
 * Contains methods that interacts with the database and current task list during program run.
 */
public class Storage implements Storable {

    /**
     * Each Storage object has a file field storing the file to read and write from.
     */
    private final File file;

    /**
     * This private constructor is called only by getInstance(String filepath).
     * Creates the file should it not exist.
     * Stores the file in this.file field of the class, for easy reference by other methods.
     *
     * @param filePath The filepath used to find the text file for storing task data.
     * @throws InputOutputException If fails to create the new file.
     */
    public Storage(String filePath) throws InputOutputException {
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
     * Reads tasks from database file.
     * Calls Tasklist.addTask() to add these into the tasklist maintained during chatbot run.
     * @return An Arraylist containing tasks.
     * @throws InputOutputException If fails to find the file.
     */
    public ArrayList<String[]> loadData() throws InputOutputException {
        try (Scanner s = new Scanner(this.file)) {
            String input;
            ArrayList<String[]> output = new ArrayList<>();
            while (s.hasNextLine()) {
                input = s.nextLine();
                String[] taskLine = input.split("\\|");
                output.add(taskLine);
            }
            return output;
        } catch (FileNotFoundException e) {
            String msg = String.format("I cannot find %s! May be accidental deletion, " +
                    "try restart cheems.Cheems!", file.getName());
            throw new InputOutputException(msg);
        }
    }

    /**
     * Saves a new task to the database text.
     * @param taskLine The line to be written to the database, formatted based on database specification.
     * @throws InputOutputException If fails to write to file.
     */
    @Override
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
     * @param content The content to write to database.
     * @throws InputOutputException If fails to read or write to file.
     */
    public void updateWholeDatabase(String content) throws InputOutputException {
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            throw new InputOutputException("I cannot open the file for writing!");
        }
    }
}

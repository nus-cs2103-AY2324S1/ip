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
     * @param filePath The filepath used to find the text file for storing task data.
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
     * @return A Storage object.
     */
    public static Storage getInstance() {
        return instance;
    }

    /**
     * Reads tasks from database file.
     * Calls Tasklist.addTask() to add these into the tasklist maintained during chatbot run.
     */
    public void loadData() throws InputOutputException {
        try (Scanner s = new Scanner(this.file)){
            String input;
            while (s.hasNextLine()) {
                input = s.nextLine();
                String[] taskLine = input.split("\\|");
                Tasklist.addTask(taskLine);
            }
        } catch (FileNotFoundException e) {
            String msg = String.format("I cannot find %s! May be accidental deletion, " +
                    "try restart cheems.Cheems!", file.getName());
            throw new InputOutputException(msg);
        }
    }

    /**
     * Saves a new task to the database text.
     * @param taskLine The line to be written to the database, formatted based on database specification.
     */
    public void saveData(String taskLine) throws InputOutputException {
        try {
            FileWriter fw = new FileWriter(this.file.getPath(), true);
            fw.write(taskLine + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new InputOutputException("I cannot open the file for writing!");
        }
    }

    /**
     * Deletes a task from the database.
     * @param lineToDelete The index of the line that should be deleted.
     */
    public void delete(int lineToDelete) throws InputOutputException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String content = "";
            int currentLine = 1;
            String line;

            while ((line = reader.readLine()) != null) {
                if (currentLine != lineToDelete + 1) {
                    content += line + System.lineSeparator();
                }
                currentLine++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            writer.write(content);
            writer.close();

            System.out.println("Task deleted successfully from storage.");
        } catch (IOException e) {
            throw new InputOutputException("Sorry, I cannot update the text file!");
        }
    }

    /**
     * Marks a task as done or undone, based on the parameter done.
     * @param lineToModify The index of the line that should be updated.
     * @param isDone A true value indicates marking the task done, otherwise undone.
     */
    public void mark(int lineToModify, boolean isDone) throws InputOutputException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String content = "";
            int currentLine = 1;
            String line;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineToModify + 1) {
                    if (!line.isEmpty()) {
                        line = (isDone ? "1" : "0")
                                        + line.substring(1);
                    }
                }
                content += line + System.lineSeparator();
                currentLine++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            writer.write(content);
            writer.close();

            System.out.println("Task udpated successfully from storage.");
        } catch (IOException e) {
            throw new InputOutputException("Sorry, I cannot update the text file!");
        }
    }
}
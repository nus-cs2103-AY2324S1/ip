package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to store the list in text file and retrieve tasks from the same file.
 */
public class Storage {

    /** Store filepath to text file used to store the tasks. */
    private final String filepath;

    /** Takes in filepath to store tasklist. */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks stored in the file to recreate the tasklist,
     * else it will return an empty new TaskList.
     *
     * @return Original tasklist.
     * @throws FileNotFoundException When file is missing.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> dukeList = new ArrayList<>();
        File file = new File(filepath);
        if (file.exists()) {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskString = fileScanner.nextLine();
                Task task = TaskParser.parseTask(taskString);
                dukeList.add(task);
            }
        }
        return dukeList;
    }

    /**
     * Save the tasklist into the file after exiting chatbot.
     *
     * @param list TaskList to be saved.
     */
    public void save(TaskList list) {
        try (PrintWriter out = new PrintWriter(filepath)) {
            for (int i = 0; i < list.count; i++) {
                String taskString = TaskParser.taskToString(list.getTask(i)); //convert task to string
                out.println(taskString);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}

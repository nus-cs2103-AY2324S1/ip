package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import task.Task;

/**
 * Storage class will load and save the tasks to the given file.
 */
public class Storage {

    private final File filepath;

    /**
     * Instantiates a constructor for the Storage class which takes in filepath of the input/output file.
     *
     * @param filepath the filepath
     */
    public Storage(File filepath) {
        this.filepath = filepath;
    }

    /**
     * Saves changes to the file when the chatbot ends the conversation.
     *
     * @param tasklist the tasklist
     */
    public void save(ArrayList<Task> tasklist) {
        try {
            FileWriter writer = new FileWriter(filepath);
            for (Task task : tasklist) {
                // Convert task to string format and write to the file
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all the tasks from the input file and generates the list of tasks and
     * then chatbot will edit the taskList according to user commands.
     *
     * @return the array list
     */
    public ArrayList<Task> load() {
        try {
            //@@author erohsikivar-reused
            //Reused from ChatGpt
            //with minor modifications
            ArrayList<Task> taskList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            while ((line = reader.readLine()) != null) {
                // Parse the line and create tasks, then add to the list
                Task task = Task.parseFromString(line);
                taskList.add(task);
            }
            reader.close();
            return taskList;
            //@@author
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



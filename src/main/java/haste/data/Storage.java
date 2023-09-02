package haste.data;

import haste.commands.Parser;
import haste.tasks.Deadline;
import haste.tasks.Event;
import haste.tasks.Task;
import haste.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a storage for data to be stored.
 */
public class Storage {
    // create data file
    private final String filePath;
    private File file;

    /**
     * Creates a Storage and a file for storing data if not existing.
     *
     * @param filePath Location of file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        create();
    }

    /**
     * Creates a file if not existing.
     */
    public void create() {
        this.file = new File(this.filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("error creating file");
            }
        }
    }

    // read data file

    /**
     * Reads the data of tasks stored in file and adds the corresponding tasks into a TaskList.
     *
     * @param tasks TaskList where tasks are added.
     */
    public void read(TaskList tasks) {
        try {
            Scanner scanner = new Scanner(this.file);
            if (scanner.hasNextLine()) {
               System.out.println("loading tasks from saved files...");
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task currTask = readTask(line);
                tasks.addTask(currTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // add one line to text file

    // Solution inspired by LAM JIN HENG BRAYDON

    /**
     * Returns Task object from a String representation of task.
     *
     * @param line String representation of task from data file.
     * @return Task object.
     */
    public Task readTask(String line) {
        String[] words = line.split("[|]");
        String command = words[0];
        boolean isComplete = Boolean.parseBoolean(words[1]);
        String description = words[2];
        Task newTask = null; // task will never be null as tasks are correctly saved

        switch (command.toLowerCase()) {
        case "t":
            newTask = new ToDo(description, isComplete);
            break;
        case "d":
            newTask = new Deadline(description, Parser.parseTime(words[3]), isComplete);
            break;
        case "e":
            newTask = new Event(description, Parser.parseTime(words[3]), Parser.parseTime(words[4]), isComplete);
            break;
        }

        return newTask;
    }
    /**
     * Writes task data onto data file.
     *
     * @param line String representation of task.
     */
    public void write(String line) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves tasks data onto data file.
     *
     * @param tasks TaskList containing the tasks.
     */
    public void save(TaskList tasks) {
        create();
        for (Task task: tasks.taskList) {
            write(task.save() + "\n");
        }
    }

    /**
     * Deletes the file.
     */
    public void delete() {
        file.delete();
    }

}

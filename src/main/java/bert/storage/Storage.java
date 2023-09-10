package bert.storage;

import bert.tasks.Deadline;
import bert.tasks.Event;
import bert.tasks.Task;
import bert.tasks.TaskList;
import bert.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list into data/tasks.txt.
     *
     * @param tasks The task list to be saved
     */
    public void save(TaskList tasks) {
        try {
            ensureTaskFileExists();
        } catch (IOException e) {
            System.out.println(
                    "____________________________________________________________\n" +
                    "OOPS!!! An error occurred while creating the task file.\n" +
                    "____________________________________________________________\n"
            );
            return;
        }
        try {
            writeToFile(filePath, tasks.toSaveFormat());
        } catch (IOException e) {
            System.out.println(
                    "____________________________________________________________\n" +
                    "OOPS!!! An error occurred while saving tasks.\n" +
                    "____________________________________________________________\n"
            );
        }
    }

    /**
     * Checks if data/tasks.txt exists. If the directory or the file does not exist,
     * creates the directory and the file.
     *
     * @throws IOException This exception is thrown when an error occurs while creating
     *          the file.
     */
    private void ensureTaskFileExists() throws IOException {
        File file = new File(this.filePath);
        if (!file.getParentFile().isDirectory()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Writes the textToAdd input into a file specified by filePath.
     *
     * @param filePath The path to the file to be written
     * @param textToAdd The String of text to be written in the file
     * @throws IOException If an error occurs while opening the file.
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Loads tasks from the save data into the chatbot.
     *
     * @return A list of tasks read from the save data
     * @throws FileNotFoundException If the save data file is not found
     */
    public List<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        List<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            String task = sc.nextLine();
            switch (task.charAt(0)) {
                case 'T':
                    ToDo t = ToDo.createFromSaveFormat(task);
                    tasks.add(t);
                    break;
                case 'D':
                    Deadline d = Deadline.createFromSaveFormat(task);
                    tasks.add(d);
                    break;
                case 'E':
                    Event e = Event.createFromSaveFormat(task);
                    tasks.add(e);
                    break;
            }
        }
        sc.close();
        return tasks;
    }
}

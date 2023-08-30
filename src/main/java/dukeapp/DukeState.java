package dukeapp;

import dukeapp.exceptions.InsufficientArgumentsException;
import dukeapp.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Contains functionality to read and interact with the application state.
 */
public class DukeState {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "duke.txt";
    private final List<Task> tasks = new ArrayList<>();

    public DukeState() {
        this.loadTasks();
    }

    /**
     * Gets the number of tasks.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Lists out all the tasks.
     */
    public void listTasks() {
        System.out.println(DukeConstants.LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("\t %d.%s\n", i + 1, task);
        }
        System.out.println("\t" + DukeConstants.HORIZONTAL_LINE);
    }

    /**
     * Inserts a task into the list of items.
     *
     * @param task The item to be added.
     */
    public void insertTask(Task task) {
        this.tasks.add(task);
        this.saveTasks();
    }

    /**
     * Marks task based on index.
     */
    public void markTask(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        this.saveTasks();
        System.out.printf((DukeConstants.MARKED_MESSAGE) + "%n", task);
    }

    /**
     * Unmarks task based on index.
     */
    public void unmarkTask(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        this.saveTasks();
        System.out.printf((DukeConstants.UNMARKED_MESSAGE) + "%n", task);
    }

    /**
     * Deletes task based on index.
     *
     * @param index The index of the item to be deleted.
     */
    public void deleteTask(int index) {
        Task task = this.tasks.remove(index);
        System.out.printf((DukeConstants.DELETE_MESSAGE) + "%n",
                task, this.tasks.size());
        this.saveTasks();
    }

    /**
     * Loads all tasks from the data/duke.txt file.
     * If the file does not exist, it will be created.
     */
    public void loadTasks() {
        initialiseStorage();
        List<Task> tasks = new ArrayList<>();
        String storagePath = String.format("./%s/%s", DIRECTORY_PATH,
                FILE_PATH);
        File file = new File(storagePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.printf((DukeConstants.ERROR_MESSAGE) + "%n",
                    DukeConstants.FILE_NOT_FOUND_ERROR_MESSAGE);
        }
        while (Objects.requireNonNull(scanner).hasNext()) {
            String input = scanner.nextLine();
            String taskCode = input.substring(0, 1);
            String taskInput = input.length() > 4
                    ? input.substring(4)
                    : "";
            try {
                Task task = Task.parse(taskCode, taskInput);
                tasks.add(task);
            } catch (InsufficientArgumentsException | DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }

    /**
     * Saves current list of tasks into data/duke.txt.
     */
    public void saveTasks() {
        String storagePath = String.format("./%s/%s", DIRECTORY_PATH,
                FILE_PATH);
        FileWriter fw;
        try {
            fw = new FileWriter(storagePath);
            for (Task task : this.tasks) {
                fw.write(task.encode() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.printf((DukeConstants.ERROR_MESSAGE) + "%n",
                    DukeConstants.FAILED_TO_WRITE_FILE_ERROR_MESSAGE);
        }
    }

    private void initialiseStorage() {
        String directoryPath = String.format("./%s", DIRECTORY_PATH);
        String storagePath = String.format("./%s/%s", DIRECTORY_PATH,
                FILE_PATH);
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                System.out.printf((DukeConstants.ERROR_MESSAGE) + "%n",
                        DukeConstants.FAILED_TO_CREATE_FOLDER_ERROR_MESSAGE);
            }
        }
        File file = new File(storagePath);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.printf((DukeConstants.ERROR_MESSAGE) + "%n",
                            DukeConstants.FAILED_TO_CREATE_FILE_ERROR_MESSAGE);
                }
            } catch (IOException e) {
                System.out.printf((DukeConstants.ERROR_MESSAGE) + "%n",
                        DukeConstants.FAILED_TO_CREATE_FILE_ERROR_MESSAGE);
            }
        }
    }
}

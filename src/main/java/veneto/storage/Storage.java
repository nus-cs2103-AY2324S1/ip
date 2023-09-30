package veneto.storage;

import veneto.exceptions.VenetoException;
import veneto.exceptions.VenetoStorageException;
import veneto.task.*;

import java.io.*;
import java.util.Scanner;

public class Storage {
    /* Fields */
    private Writer writer;
    private TaskList tasks;

    private static final String STORAGE_DIR = "./src/main/data/veneto.txt";
    private static final String STORAGE_PATH = "./src/main/data/veneto.txt";

    /* Constructors */

    /* Methods */
    /**
     * initialize the storage environment
     * @param tasks
     * @throws VenetoStorageException when it fails to create the directories
     */
    public void init(TaskList tasks) {
        this.tasks = tasks;
        new File(STORAGE_DIR).mkdir();
        try {
            new File(STORAGE_PATH).createNewFile();
        } catch (IOException e) {
            // but may not happen
            throw new VenetoStorageException("make path fail");
        }
    }

    /**
     * load the data in storage file if exist
     * @return TaskList containing the data stored
     * @throws VenetoException if the path is invalid
     */
    public TaskList load() throws VenetoException {
        tasks = new TaskList(100);
        File f = new File(STORAGE_PATH);
        if (!f.exists()) {
            throw new VenetoStorageException("No Storage Found");
        }
        Scanner sc = null;
        try {
            sc = new Scanner(f);
            String text;
            while (sc.hasNext()) {
                text = sc.nextLine();
                addTask(text);
            }
            return tasks;
        } catch (IOException e) {
            throw new VenetoStorageException("May not happen");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * translate the text into a Task and add it to the TaskList
     * @param text a task stored in the file
     * @throws VenetoStorageException if the storage file is destroyed
     */
    public void addTask(String text) throws VenetoException {
        try {
            String[] task = text.split(",");
            Task t = null;
            int isDone = Integer.parseInt(task[2]);
            switch (task[0]) {
                case "toDo":
                    t = new ToDo(task[1], isDone);
                    break;
                case "deadline":
                    t = new Deadline(task[1], isDone, task[3]);
                    break;
                case "event":
                    t = new Event(task[1], isDone, task[3], task[4]);
                    break;
                default:
                    assert false : "invalid task type";
            }
            tasks.add(t);
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new VenetoStorageException("Storage File Destroyed");
        }
    }

    /**
     * save the data if there's a change
     * @param tasks the TaskList that may be modified
     */
    public void checkChange(TaskList tasks) {
        if (tasks.storageChanged == 1) {
            save();
            tasks.storageChanged = 0;
        }
    }

    /**
     * save the data
     * @throws VenetoException when write fails or close fails
     */
    private void save() {
        try {
            new File(STORAGE_PATH).delete();
            new File(STORAGE_PATH).createNewFile();
            writer = new BufferedWriter(new FileWriter(STORAGE_PATH));
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).saveToString() + "\n");
            }
        } catch (IOException e) {
            throw new VenetoException("write fails");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new VenetoException("close fails");
            }
        }
    }
}

package veneto.storage;

import veneto.exceptions.VenetoException;
import veneto.exceptions.VenetoStorageException;
import veneto.task.*;

import java.io.*;
import java.util.Scanner;

public class Storage {
    /* Fields */
    private String savePath;
    private Writer writer;
    private TaskList tasks;

    /* Constructors */
    /**
     * create a storage for Veneto
     * @param savePath path where the data stored
     */
    public Storage(String savePath) {
        this.savePath = savePath;
    }

    /* Methods */
    /**
     * initialize the storage environment
     * @param tasks
     * @throws VenetoStorageException when it fails to create the directories
     */
    public void init(TaskList tasks) {
        this.tasks = tasks;
        new File("./src/main/data").mkdir();
        try {
            new File(savePath).createNewFile();
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
        File f = new File(savePath);
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
            new File(savePath).delete();
            new File(savePath).createNewFile();
            writer = new BufferedWriter(new FileWriter(savePath));
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).saveToString() + "\n");
            }
        } catch (IOException e) {
            // may not happen
            throw new VenetoException("write fails");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                // may not happen
                throw new VenetoException("close fails");
            }
        }
    }
}

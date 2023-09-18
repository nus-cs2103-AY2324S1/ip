package veneto.storage;

import veneto.exceptions.VenetoException;
import veneto.exceptions.VenetoStorageException;
import veneto.task.*;

import java.io.*;
import java.util.Scanner;

public class Storage {
    /** Fields */
    private String savePath;
    private Writer writer;
    private TaskList tasks;

    /** Constructors */
    public Storage(String savePath) {
        this.savePath = savePath;
    }

    /** Methods */
    public void init(TaskList tasks) {
        this.tasks = tasks;
        new File("./data").mkdir();
        try {
            new File(savePath).createNewFile();
        } catch (IOException e) {
            throw new VenetoException("一般来讲这个不可能发生的");
        }
    }
    public TaskList load() throws VenetoException {
        tasks = new TaskList(100);
        File f = new File(savePath);
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
            throw new VenetoStorageException("No Storage Found");
        } finally {
            sc.close();
        }
    }

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

    public void checkChange(TaskList tasks) {
        if (tasks.storageChanged == 1) {
            save();
            tasks.storageChanged = 0;
        }
    }

    public void save() {
        try {
            new File(savePath).delete();
            new File(savePath).createNewFile();
            writer = new BufferedWriter(new FileWriter(savePath));
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).saveToString() + "\n");
            }
        } catch (IOException e) {
            throw new VenetoException("一般来讲不会发生的");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new VenetoException("一般来讲也不会发生的，但是万一发生了也保不准");
            }
        }
    }
}

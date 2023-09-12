import dan.exceptions.DanException;
import dan.task.*;

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
            throw new DanException("");
        }
    }
    public TaskList load() {
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
            throw new DanException("No Storage Found");
        } finally {
            sc.close();
        }
    }

    public void addTask(String text)
            throws IndexOutOfBoundsException, NumberFormatException, NullPointerException {
        String[] task = text.split(",");
        Task t = null;
        int isDone = Integer.parseInt(task[2]);
        switch (task[0]) {
            case "toDo":
                t = new ToDo(task[1], isDone); break;
            case "deadline":
                t = new Deadline(task[1], isDone, task[3]); break;
            case "event":
                t = new Event(task[1], isDone, task[3], task[4]); break;
        }
        tasks.add(t);
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
            throw new DanException("");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new DanException("");
            }
        }
    }
}

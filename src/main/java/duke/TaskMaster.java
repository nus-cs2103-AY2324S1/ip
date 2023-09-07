package duke;

import java.util.Scanner;

public abstract class TaskMaster {
    protected static TaskList tasks = new TaskList();
    protected static Storage storage;

    public static void masterTasks(String input) {
        String[] splt = input.split(" ");
        String keyword = splt[0];
        Parser.parseInput(splt,keyword);
    }

    public static void initialStorage(String filePath) {
        storage = new Storage(filePath);
    }

    public static String mark(int i) {
        return tasks.mark(i);
    }

    public static String unmark(int i) {
        return tasks.unmark(i);
    }

    public static String list() {
        return tasks.list();
    }

    public static String addTask(Task t) {
        return tasks.addTask(t);
    }

    public static String removeTask(int index) {
        return tasks.removeTask(index);
    }

    public static String readTask(String key) {
        return tasks.readTask(key);
    }

    public static String findTask(String key) {
        return tasks.findTask(key);
    }
}

package duke;

import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;

import java.util.Scanner;

public abstract class Taskmanager {
    protected static TaskList tasks = new TaskList();
    protected static Storage storage;

    public static void manageTasks(String filePath) {
        storage = new Storage(filePath);
        Scanner sn = new Scanner(System.in);
        String input = sn.nextLine();
        String[] splt = input.split(" ");
        String keyword = splt[0];
        while (!keyword.equals("bye")) {
            Parser.parseInput(splt,keyword);
            input = sn.nextLine();
            splt = input.split(" ");
            keyword = splt[0];
        }
    }

    public static void mark(int i) {
        tasks.mark(i);
    }

    public static void unmark(int i) {
        tasks.unmark(i);
    }

    public static void list() {
        tasks.list();
    }

    public static void addTask(Task t) {
        tasks.addTask(t);
    }

    public static void removeTask(int index) {
        tasks.removeTask(index);
    }

    public static void readTask(String key) {
        tasks.readTask(key);
    }

    public static void findTask(String key) {
        tasks.findTask(key);
    }
}

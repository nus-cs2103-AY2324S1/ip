package duke;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private final Storage storage;

    public TaskList(String filePath) {
        this.list = new ArrayList<>();
        this.storage = new Storage(filePath);
        fetchList();
    }

    public void fetchList() {
        try {
            this.list = this.storage.load();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error when fetching to-do list: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try {
            storage.saveToFile(list);
        } catch (IOException e) {
            System.out.println("Error while saving to-do list: " + e.getMessage());
        }
    }
    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
        saveToFile();
    }

    public void markAsDone(int taskNum) {
        Task task = this.list.get(taskNum - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        saveToFile();
    }

    public void delete(int taskNum) {
        Task task = this.list.get(taskNum - 1);
        this.list.remove(taskNum - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
        saveToFile();
    }

    public int size() {
        return this.list.size();
    }

    public Task get(int index) {
        return this.list.get(index);
    }
}

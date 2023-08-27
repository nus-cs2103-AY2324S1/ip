import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list;
    private Storage storage;

    public ToDoList(String filePath) {
        this.list = new ArrayList<>();
        this.storage = new Storage(filePath);
        fetchList();
    }

    public void fetchList() {
        try {
            this.list = this.storage.fetchTasks();
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
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        saveToFile();
    }

    public void listTasks() {
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(i + 1 + ". " + this.list.get(i));
        }
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
    }

    public void markAsDone(int taskNum) {
        Task task = this.list.get(taskNum - 1);
        task.markAsDone();
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        saveToFile();
    }

    public void delete(int taskNum) {
        Task task = this.list.get(taskNum - 1);
        this.list.remove(taskNum - 1);
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        saveToFile();
    }
}

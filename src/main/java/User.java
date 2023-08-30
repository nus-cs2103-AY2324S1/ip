import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class User {
    private int taskNum = 0;
    private ArrayList<Task> tasks = new ArrayList<>();

    public User() {
        try {
            this.loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("No existing tasks, creating new task list...");
            System.out.println("---------------------------------------------------------------");
        }
        this.taskNum = this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.taskNum += 1;
        this.saveTasks();
    }

    public void listTasks() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + this.tasks.get(i).toString());
        }
    }

    public void saveTasks() {
        try {
            FileOutputStream file = new FileOutputStream("savedTasks.ser");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(this.tasks);
            output.close();
            file.close();
        } catch (IOException e) {
            System.out.println("file error occurred when saving");
        }
    }

    private void loadTasks() throws FileNotFoundException {
        try {
            FileInputStream file = new FileInputStream("savedTasks.ser");
            @SuppressWarnings("unchecked")
            ObjectInputStream output = new ObjectInputStream(file);
            this.tasks = (ArrayList<Task>) output.readObject();
            output.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new FileNotFoundException();
        }
    }

    public int taskCount() {
        return this.taskNum;
    }

    public void taskToPrint(int index) {
        System.out.println("   " + this.tasks.get(index).toString());
    }

    public void markTask(int index) {
        this.tasks.get(index).markAsDone();
        System.out.println(" Nice! I've marked this task as done:");
        this.saveTasks();
    }

    public void unMarkTask(int index) {
        this.tasks.get(index).markAsNotDone();
        System.out.println(" OK, I've marked this task as not done yet:");
        this.saveTasks();
    }

    public void deleteTask(int index) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + this.tasks.get(index).toString());
        this.tasks.remove(index);
        this.taskNum -= 1;
        System.out.println(" Now you have " + this.tasks.size() + " tasks in the list.");
        this.saveTasks();
    }
}

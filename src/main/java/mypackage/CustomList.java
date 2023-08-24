package mypackage;
import java.util.ArrayList;

public class CustomList extends ArrayList<Task> {
    public CustomList() {
        super();
    }

    public void printList() {
        System.out.println("________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + ". " + this.get(i));
        }
        System.out.println("________________________________");
    }

    public void markAsDone(int index) {
        System.out.println("Nice! I've marked this task as done:");
        this.get(index - 1).markAsDone();
        System.out.println(" " + this.get(index - 1));
        System.out.println("________________________________");
    }

    public void markAsUndone(int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        this.get(index - 1).markAsUndone();
        System.out.println(" " + this.get(index - 1));
        System.out.println("________________________________");
    }

    public void addTask(Task task) {
        System.out.println("________________________________");
        System.out.println("Got it. I've added this task:");
        this.add(task);
        System.out.println(" " + task);
        System.out.println("Now you have " + Integer.toString(this.size()) + " " + (this.size() == 1 ? "task" : "tasks") + " in the list.");
        System.out.println("________________________________");
    }
}

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public void printContents() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + "." + this.tasks.get(i));
        }
    }

    public void printTasksOn(LocalDate date) {
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).isOnDate(date)) {
                System.out.println((i + 1) + "." + this.tasks.get(i));
            }
        }
    }

    public void mark(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void unmark(int index) {
        Task task = this.tasks.get(index);
        task.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
}

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public void addTask(Task task) {
        this.add(task);
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int length = 1; length < this.size() + 1; length += 1) {
            System.out.println(length + "." + this.get(length - 1));
        }
    }
}

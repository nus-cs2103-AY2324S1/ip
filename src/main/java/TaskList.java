import java.util.ArrayList;
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addToList(Task task) {
        this.tasks.add(task);
    }

    public void markDone(int taskNum) {
        this.tasks.get(taskNum).markDone();
    }

    public void markUndone(int taskNum) {
        this.tasks.get(taskNum).markUndone();
    }

    public String printTask(int taskNum) {
        return this.tasks.get(taskNum).toString();
    }

    public void printList() {
        for(int i = 0; i < tasks.size(); i++) {
            int x = i + 1;
            System.out.println(x + ". " + this.printTask(i));
        }
    }
}

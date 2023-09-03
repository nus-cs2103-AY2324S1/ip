import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import tasks.Task;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
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

    public String printName(int taskNum) {
        return this.tasks.get(taskNum).getName();
    }
    public void numOfTask() {
        System.out.println("N... Now you have... " + this.tasks.size() + " tasks in the list. 0w0");
    }
    public void deleteTask(int taskNum) {
        System.out.println(this.printTask(taskNum));
        this.tasks.remove(taskNum);
    }

    public void printList() {
        for(int i = 0; i < tasks.size(); i++) {
            int x = i + 1;
            System.out.println(x + ". " + this.printTask(i));
        }
    }
}

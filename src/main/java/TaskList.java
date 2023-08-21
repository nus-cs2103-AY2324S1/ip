import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " task(s) in the list.");
    }

    public void setTaskComplete(int i) {
        Task task = this.list.get(i);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void setTaskIncomplete(int i) {
        Task task = this.list.get(i);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public Task getTask(int i) {
        return this.list.get(i);
    }

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }
}

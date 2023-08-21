import java.util.ArrayList;

public class TaskList {

    private static final String DIVIDER = "____________________________________________________________";
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(String taskDescription) {
        System.out.println(DIVIDER);
        this.list.add(new Task(taskDescription));
        System.out.println("added: " + taskDescription);
        System.out.println(DIVIDER);
    }

    public void markTaskDone(int index) {
        this.list.get(index - 1).markDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.list.get(index - 1));
        System.out.println(DIVIDER);
    }

    public void unmarkTaskDone(int index) {
        this.list.get(index - 1).unmarkDone();
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.list.get(index - 1));
        System.out.println(DIVIDER);
    }

    public void listTasks() {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println((i + 1) + ". " + this.list.get(i));
        }
        System.out.println(DIVIDER);
    }
}

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list;

    public ToDoList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
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
    }
}

import java.lang.reflect.Array;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void quietlyAddTask(Task task) {
        this.tasks.add(task);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task: ~Bzzz~");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list. ~Bzzz~");
    }

    public void listAllTasks() {
        System.out.println("Here are the tasks in your list: ~Bzzz~");
        for (int i = 0; i < this.tasks.size() ; i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i).toString());
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTaskDone(int taskIndex) throws BeeException {
        if (taskIndex > this.tasks.size() || taskIndex < 1) {
            throw new BeeException("OOPS!! Please enter a valid task number.");
        }
        this.tasks.get(taskIndex - 1).setDone();
        System.out.println("Nice! I've marked task " + taskIndex +  " as done: ~Bzzz~");
        System.out.println(this.tasks.get(taskIndex - 1).toString());
    }

    public void setTaskNotDone(int taskIndex) throws BeeException {
        if (taskIndex > this.tasks.size() || taskIndex < 1) {
            throw new BeeException("OOPS!! Please enter a valid task number.");
        }
        this.tasks.get(taskIndex - 1).setNotDone();
        System.out.println("OK, I've marked task " + taskIndex +  " as not done yet: ~Bzzzz");
        System.out.println(this.tasks.get(taskIndex - 1).toString());
    }

    public void deleteTask(int taskIndex) throws BeeException {
        if (taskIndex > tasks.size() || taskIndex < 1) {
            throw new BeeException("OOPS!! Please enter a valid task number.");
        }
        Task deletedTask = this.tasks.get(taskIndex - 1);
        this.tasks.remove(taskIndex - 1);
        System.out.println("Okies~. I've removed this task:");
        System.out.println(deletedTask.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

}

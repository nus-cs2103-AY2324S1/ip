import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("You added '" + tasks.get(tasks.size() - 1) + "' to the list!"
                + "\nNow you have " + tasks.size() + " task(s) in the list!");
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }
        Task removedTask = tasks.remove(index - 1);
        System.out.println("Yes Sir. I've removed the following task\n" + removedTask + "\nNow you" +
                " have " + tasks.size() + " task(s) in the list!");
    }

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }
        return tasks.get(index);
    }

    public int getTotalTasks() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void listTask() throws DukeException {
        if (this.getTotalTasks() == 0) {
            System.out.println("List is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int item = 0; item < this.getTotalTasks(); item++)  {
                System.out.println(item + 1 + ". " + this.getTask(item));
            }
        }
    }

}

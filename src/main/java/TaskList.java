import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds Task into TaskList.
     *
     * @param the description of the task
     */
    public void addTask(String taskDesc) {
        this.tasks.add(new Task(taskDesc));
    }

    /**
     * Returns task that was toggled from done to undone and vice versa
     *
     * @param String array of the command split by whitespace
     * @return the toggled task
     */
    public Task toggleTask(String[] commandArr) {
        Task task = this.tasks.get(Integer.parseInt(commandArr[1]) - 1);
        task.toggleTask();
        return task;
    }

    /**
     * Lists out the string representation of Tasks in the order they were added
     * into the TaskList. List is 1-indexed
     */
    public void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf("%d %s\n%n", i + 1, tasks.get(i));
        }
    }
}

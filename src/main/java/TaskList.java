import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void printTasks() {
        System.out.println("Here are your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s%n", i + 1, task);
        }
        System.out.println(Duke.LINE);
    }

    public void printScheduledTasks(LocalDateTime datetime) {
        System.out.println("Here are your list of tasks:");
        int index = 1;
        for (Task task : tasks) {
            if (task.isWithinDateRange(datetime)) {
                System.out.printf("%d. %s%n", index++, task);
            }
        }
        System.out.println(Duke.LINE);
    }

    public String retrieveForStorage() {

        StringBuilder textForStorage = new StringBuilder();
        for (Task task : tasks) {
            textForStorage.append(task.formatForStorage()).append("\n");
        }
        return textForStorage.toString();
    }

}

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private static final String INDENTATION = "     ";
    private static final String EMPTY_TASK_LIST_RESPONSE = "The list is empty, you donkey!";
    private static final String NON_EMPTY_TASK_LIST_RESPONSE = "Here's your list, you donkey!";
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public int size() {
        return tasks.size();
    }
    public Task getTask(int index) {
        return tasks.get(index);
    }
    public void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        }
    }
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }
    public void markTaskAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.tasks.size() == 0) {
            sb.append(INDENTATION).append(EMPTY_TASK_LIST_RESPONSE).append("\n");
        } else {
            sb.append(INDENTATION).append(NON_EMPTY_TASK_LIST_RESPONSE).append("\n");
            for (int i = 0; i < this.tasks.size(); i++) {
                sb.append(INDENTATION)
                        .append(i + 1)
                        .append(".")
                        .append(tasks.get(i).toString())
                        .append("\n");
            }
        }

        return sb.toString();
    }
}

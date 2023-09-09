package friday;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Friday application.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        taskList.add(task);
    }

    public String mark(int taskNumber) {
        Task task = taskList.get(taskNumber);
        task.mark();
        return task.toString();
    }

    public String unmark(int taskNumber) {
        Task task = taskList.get(taskNumber);
        task.unmark();
        return task.toString();
    }

    public String delete(int taskNumber) {
        Task task = taskList.get(taskNumber);
        taskList.remove(taskNumber);
        return "I've removed this task:\n" + task + "\n" + message();
    }

    public TaskList findTasks(String keyWord) {
        TaskList result = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task item = taskList.get(i);
            if (item.containsKeyWord(keyWord)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        return result.toString();
    }

    public String message() {
        return "Now you have " + taskList.size() + " tasks in the list.";
    }
}

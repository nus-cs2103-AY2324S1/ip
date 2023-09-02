package duke.task;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void mark(Integer index) {
        tasks.get(index).mark();
    }
    public void unmark(Integer index) {
        tasks.get(index).unmark();
    }
    public Task getTask(Integer index) {
        return tasks.get(index);
    }
    public String getTaskInput(Integer index) {
        return tasks.get(index).getInput();
    }
    public int length() {
        return tasks.size();
    }
    public void add(Task task) {
        tasks.add(task);
    }
    public void delete(Integer taskIndex) {
        tasks.remove(taskIndex);
    }
    @Override
    public String toString() {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            taskList += (i + 1) + "." + tasks.get(i) + "\n";
        }
        return taskList;
    }
}

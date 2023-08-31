package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private final Ui ui;

    public TaskList(List<Task> savedTasks, Ui ui) {
        this.tasks = savedTasks;
        this.ui = ui;
    }

    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public void deleteTask(int idx) throws IndexOutOfBoundsException{
        Task task = tasks.get(idx);
        tasks.remove(task);
        ui.showDeleteTaskMessage(task, tasks.size());
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        ui.showAddTaskMessage(task, tasks.size());
    }

    public void markTask(int idx) throws IndexOutOfBoundsException {
        Task task = tasks.get(idx);
        task.setIsCompleted(true);
        ui.showMarkedTask(task);
    }

    public void unmarkTask(int idx) throws IndexOutOfBoundsException {
        Task task = tasks.get(idx);
        task.setIsCompleted(false);
        ui.showUnmarkedTask(task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public TaskList find(String keyword) {
        TaskList ret = new TaskList(ui);
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                ret.addTask(task);
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(i);
            stringBuilder.append(".");
            stringBuilder.append(tasks.get(i));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

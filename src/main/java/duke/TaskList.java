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

    public void listTasks() {
        int tempCounter = 0;
        for (Task task : tasks) {
            // Don't print nulls
            if (task == null) { break; }
            if (task instanceof Todo) {
                Todo t = (Todo) task;
                System.out.println(tempCounter + "." + t);
            } else if (task instanceof Deadline) {
                Deadline t = (Deadline) task;
                System.out.println(tempCounter + "." + t);
            } else if (task instanceof Event) {
                Event t = (Event) task;
                System.out.println(tempCounter + "." + t);
            }
            tempCounter++;
        }
        Ui.showLine();
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
}

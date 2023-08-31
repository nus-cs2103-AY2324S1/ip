package data;

import java.util.ArrayList;
import data.exception.DukeException;
import data.tasks.Task;
import ui.Ui;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        if (tasks != null) {
            this.tasks = tasks;
        } else {
            this.tasks = new ArrayList<Task>();
        }
    }

    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task mark(int taskIndex, boolean isDone) throws DukeException {
        // User tries to mark/unmark a task that is out of bounds.
        if (taskIndex < 0 || taskIndex > getSize()) {
            throw new DukeException(String.format(
                "Unable to %s task %d :( You have %d task(s) stored.",
                isDone ? "mark" : "unmark", taskIndex, getSize()
            ));
        }

        Task task = getTask(taskIndex - 1);
        if (isDone) task.mark(); 
        else      task.unmark();
        return task;
    }

    public Task delete(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex > getSize()) {
            throw new DukeException(String.format(
                "Unable to " 
                    + Ui.cTxt("delete", Ui.COLOR.PURPLE) 
                    + " task %d :( You have %d task(s) stored.",
                taskIndex + 1, getSize()
            ));
        }

        Task removedTask = this.tasks.remove(taskIndex);
        return removedTask;
    }

    @Override
    public String toString() {
        String taskListString = "";
        for (Task task : this.tasks) {
            taskListString += task.toFileFormatString() + "\n";
        }
        return taskListString.strip();
    }
}

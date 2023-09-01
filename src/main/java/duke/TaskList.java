package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task remove(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException(Ui.LINE + Messages.INVALID_INDEX_MESSAGE + Ui.LINE);
        }

        return this.tasks.remove(index - 1);
    }

    public int size() {
        return this.tasks.size();
    }

    public String getSaveString() {
        StringBuilder tempString = new StringBuilder();
        for (Task task : this.tasks) {
            tempString.append(task.getSaveString()).append(System.lineSeparator());
        }

        return tempString.toString();
    }

    public void printTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }
    }

    public Task markAsDone(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException(Ui.LINE + Messages.INVALID_INDEX_MESSAGE + Ui.LINE);
        }

        Task currentTask = this.tasks.get(index - 1);
        currentTask.markAsDone();
        return currentTask;
    }

    public Task markAsUndone(int index) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException(Ui.LINE + Messages.INVALID_INDEX_MESSAGE + Ui.LINE);
        }

        Task currentTask = this.tasks.get(index - 1);
        currentTask.markAsUndone();
        return currentTask;
    }
}

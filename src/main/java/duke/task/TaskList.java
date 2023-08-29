package duke.task;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

public class TaskList {
    // change implementation to collections
    private ArrayList<Task> tasks;
    private Storage db;

    public TaskList() {
        db = new Storage("data/duke.ser");
        this.tasks = db.loadTask();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        db.saveTask(tasks);
        Ui.addTask(task, this.tasks.size());
    }

    public void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new DukeException("Please enter a valid task number.");
        }

        Ui.deleteTask(this.tasks.get(taskNumber - 1), this.tasks.size());
        this.tasks.remove(taskNumber - 1);
        db.saveTask(tasks);
    }

    public void listAllTasks() {
        Ui.lsitAllTasks(tasks);
    }

    public void markTaskAsDone(int taskNumber) throws DukeException{
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            throw new DukeException("Please enter a valid task number.");
        }

        Task tsk = this.tasks.get(taskNumber - 1);
        tsk.markAsDone();
        db.saveTask(tasks);
        Ui.markAsDone(tsk);
    }

    public void markTaskAsUndone(int taskNumber) throws DukeException{
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            throw new DukeException("Please enter a valid task number.");
        }
        Task tsk = this.tasks.get(taskNumber - 1);
        tsk.markAsUndone();
        db.saveTask(tasks);
        Ui.markAsUndone(tsk);
    }
}

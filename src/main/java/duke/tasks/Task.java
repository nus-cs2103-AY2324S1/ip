package duke.tasks;

import duke.Ui;

abstract public class Task {
    private int status; //0 for uncompleted, 1 (or any other number) for completed
    private String task;

    public Task(int status, String task) {
        this.status = status;
        this.task = task;
    }

    abstract public String convertTask();

    public void mark(Ui ui) {
        if (this.status == 0) {
            this.status = 1;
            ui.showMarkTask(false, this);
        } else {
            ui.showMarkTask(true, this);
        }
    }

    public void unmark(Ui ui) {
        if (this.status != 0) {
            this.status = 0;
            ui.showUnmarkTask(true, this);
        } else {
           ui.showUnmarkTask(false, this);
        }
    }

    public int getStatus() {
        return this.status;
    }

    public String getTask() {
        return this.task;
    }
    @Override
    public String toString() {
        if (status == 0) {
            return "[ ] " + task;
        } else {
            return "[X] " + task;
        }
    }
}
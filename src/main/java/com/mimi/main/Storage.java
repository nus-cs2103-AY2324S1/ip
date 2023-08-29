package com.mimi.main;

import com.mimi.tasks.Task;

import java.util.ArrayList;

public class Storage {
    Ui ui;

    public Storage(Ui ui) {
        this.ui = ui;
    }
    private ArrayList<Task> previous_Commands= new ArrayList<>();

    public void add(Task task) {
        this.previous_Commands.add(task);

        ui.addTaskMessage(task, previous_Commands.size());
    }

    public void addWithoutPrinting(Task task) {
        this.previous_Commands.add(task);
    }

    public void listItems() {
        System.out.println("Here are the tasks in your list:\n");

        for (int i = 0; i < this.previous_Commands.size(); ++i) {
            Task task = this.previous_Commands.get(i);

            ui.listTask(i+1, task);
        }
    }

    public void mark(int taskNumber) {
        if (taskNumber > previous_Commands.size() || taskNumber <= 0) {
            ui.markUnmarkDeleteWrongTask();
            return;
        }

        Task task = previous_Commands.get(taskNumber - 1);

        if (task.isDone()) {
            ui.taskAlreadyMarkedAsDone();
            return;
        }

        task.toggleDone();
        ui.markedDone(task);
    }

    public void unmark(int taskNumber) {

        if (taskNumber > previous_Commands.size() || taskNumber <= 0) {
            ui.markUnmarkDeleteWrongTask();
            return;
        }

        Task task = previous_Commands.get(taskNumber - 1);

        if (!task.isDone()) {
            ui.taskAlreadyUnmarked();
            return;
        }

        task.toggleDone();
        ui.unmarkedDone(task);

    }

    public void delete(int taskNumber) {
        if (taskNumber > previous_Commands.size() || taskNumber <= 0) {
            ui.markUnmarkDeleteWrongTask();
            return;
        }

        Task task = previous_Commands.get(taskNumber -1);
        previous_Commands.remove(taskNumber -1);

        ui.deletedTask(task);
    }

    public void updateAll(ReadWriteData writer) {
        for (Task task: this.previous_Commands) {
            writer.write(task);
        }
    }


}

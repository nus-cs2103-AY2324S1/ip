package com.mimi.main;

import java.util.ArrayList;

import com.mimi.tasks.Task;

/**
 * A class which represents the tasks previously executed and their status.
 * @author Yuheng
 */
public class Storage {
    private Ui ui;

    private ArrayList<Task> previousCommands = new ArrayList<>();

    /**
     * Creates an instance of the Storage
     * @param ui the Ui needed for the ui responses
     */
    public Storage(Ui ui) {
        this.ui = ui;
    }

    /**
     * Adds a task to the storage.
     * @param task the given Task to be added to the storage.
     */
    public void add(Task task) {
        this.previousCommands.add(task);

        ui.addTaskMessage(task, previousCommands.size());
    }

    /**
     * Adds a task without any ui response. This is used during initialisation
     * when data is read from the hard disk.
     * @param task the Task to be added to the storage.
     */
    public void addWithoutPrinting(Task task) {
        this.previousCommands.add(task);
    }

    /**
     * Lists out all the items in the storage in the order that they were added.
     */
    public void listItems() {
        System.out.println("Here are the tasks in your list:\n");

        for (int i = 0; i < this.previousCommands.size(); ++i) {
            Task task = this.previousCommands.get(i);

            ui.listTask(i + 1, task);
        }
    }

    /**
     * Marks the task represented with the given number.
     * @param taskNumber an integer that denotes the position of the task to be marked.
     */
    public void mark(int taskNumber) {
        if (taskNumber > previousCommands.size() || taskNumber <= 0) {
            ui.markUnmarkDeleteWrongTask();
            return;
        }

        Task task = previousCommands.get(taskNumber - 1);

        if (task.isDone()) {
            ui.taskAlreadyMarkedAsDone();
            return;
        }

        task.toggleDone();
        ui.markTask(task);
    }

    /**
     * Un-marks the task represented with the given number.
     * @param taskNumber an integer that denotes the position of the task to be un-marked.
     */
    public void unmark(int taskNumber) {

        if (taskNumber > previousCommands.size() || taskNumber <= 0) {
            ui.markUnmarkDeleteWrongTask();
            return;
        }

        Task task = previousCommands.get(taskNumber - 1);

        if (!task.isDone()) {
            ui.taskAlreadyUnmarked();
            return;
        }

        task.toggleDone();
        ui.unmarkTask(task);

    }

    /**
     * Deletes the task represented with the given number from the storage.
     * @param taskNumber an integer that denotes the position of the task to be deleted.
     */
    public void delete(int taskNumber) {
        if (taskNumber > previousCommands.size() || taskNumber <= 0) {
            ui.markUnmarkDeleteWrongTask();
            return;
        }

        Task task = previousCommands.get(taskNumber - 1);
        previousCommands.remove(taskNumber - 1);

        ui.deleteTask(task);
    }

    /**
     * Updates the hard disk with all the current tasks in the storage along with their status.
     * @param writer an instance of ReadWriteData to write the information to the hard disk.
     */
    public void updateAll(ReadWriteData writer) {
        for (Task task: this.previousCommands) {
            writer.write(task);
        }
    }

    /**
     * Searches the previous tasks with the given string.
     * @param searchTerm The string that should match descriptions from previous tasks.
     */
    public void search(String searchTerm) {
        int index = 1;
        for (Task task: this.previousCommands) {
            //convert both strings to lower case to ignore casing
            if (task.toString().toLowerCase()
                .contains(searchTerm.toLowerCase())) {
                ui.returnSearchTerm(task, index);
                index++;
            }
        }

        if (index == 1) {
            ui.noSearchResults();
        }
    }


}

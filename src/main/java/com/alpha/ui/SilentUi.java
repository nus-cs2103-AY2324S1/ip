package com.alpha.ui;

import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

/**
 * The type Silent ui.
 */
public class SilentUi extends Ui {

    /**
     * Instantiates a new Silent ui.
     */
    public SilentUi() {
        super();
    }

    @Override
    public void addTask(Task task, TaskList taskList) {
    }

    @Override
    public void markTask(Task task) {
    }

    @Override
    public void unmarkTask(Task task) {
    }

    @Override
    public void deleteTask(Task task, TaskList taskList) {
    }

}

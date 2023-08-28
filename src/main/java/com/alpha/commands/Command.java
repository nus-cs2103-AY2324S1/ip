package com.alpha.commands;

import com.alpha.storage.Storage;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;

public abstract class Command {

    private String args;

    public Command() {
    }

    public Command(String args) {
        this.args = args;
    }

    public String getArgs() {
        return args;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}

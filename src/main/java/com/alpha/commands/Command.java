package com.alpha.commands;

import com.alpha.storage.Storage;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;

/**
 * The type Command representing commands from user input.
 */
public abstract class Command {

    private String args;

    /**
     * Instantiates a new Command.
     */
    public Command() {
    }

    /**
     * Instantiates a new Command with arguments.
     *
     * @param args The arguments of the Command.
     */
    public Command(String args) {
        this.args = args;
    }

    /**
     * Returns the arguments of the Command.
     *
     * @return the args
     */
    public String getArgs() {
        return args;
    }

    /**
     * Executes the commands.
     *
     * @param taskList   Task list of the application.
     * @param ui      Ui of the application.
     * @param storage Storage functionality of the application.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns whether the Command is an Exit Command. False by default.
     *
     * @return boolean of whether the Command is an Exit Command.
     */
    public boolean isExit() {
        return false;
    }
}

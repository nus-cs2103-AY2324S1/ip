package com.alpha.commands;

import java.io.IOException;

import com.alpha.storage.Storage;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;

/**
 * The type Bye command.
 */
public class ByeCommand extends Command {

    /**
     * Instantiates a new Bye command.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Executes the commands.
     *
     * @param taskList   Task list of the application.
     * @param ui      Ui of the application.
     * @param storage Storage functionality of the application.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.save(taskList);
            return ui.goodbye();
        } catch (IOException e) {
            return ui.loadingError();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

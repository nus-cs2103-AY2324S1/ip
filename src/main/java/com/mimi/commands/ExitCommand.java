package com.mimi.commands;

import com.mimi.main.Ui;

/**
 * Representation of the Exit Command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the given command.
     */
    @Override
    public void execute() { }

    /**
     * Calls the ui to give the appropriate response based on the type of command.
     * @param ui Ui class instance
     */
    @Override
    public void uiResponse(Ui ui) {
        ui.exitMessage();
    }

    /**
     * Checks if the command given is the command to exit.
     * @return returns true if the command is the exit command and false otherwise
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

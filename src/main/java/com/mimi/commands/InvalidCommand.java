package com.mimi.commands;

import com.mimi.main.Ui;

/**
 * Representation of Invalid Commands.
 * @author Yuheng
 */
public class InvalidCommand extends Command {
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
        ui.invalidTaskMessage();
    }
}

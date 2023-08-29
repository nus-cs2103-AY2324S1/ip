package com.mimi.commands;

import com.mimi.main.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute() {
    }

    @Override
    public void uiResponse(Ui ui) {
        ui.invalidTaskMessage();
    }
}

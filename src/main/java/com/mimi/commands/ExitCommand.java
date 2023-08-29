package com.mimi.commands;

import com.mimi.main.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(){}
    @Override
    public void uiResponse(Ui ui) {
        ui.exitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

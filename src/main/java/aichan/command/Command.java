package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;

public class Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AiChanException {
        // control the main logic
        // add/delete task + ui shows words
    }

    public boolean isExit() {
        return false;
    }
}

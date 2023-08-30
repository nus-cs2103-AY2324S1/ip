package aichan.command;

import aichan.AiChanException;
import aichan.TaskList;
import aichan.Ui;

public class Command {
    public void execute(TaskList tasks, Ui ui) throws AiChanException {
        // control the main logic
        // add/delete task + ui shows words
    }

    public boolean isExit(){
        return false;
    }
}

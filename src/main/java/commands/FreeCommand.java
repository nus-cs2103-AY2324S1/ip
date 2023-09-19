package commands;

import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;

public class FreeCommand implements Command {

    public static final String COMMAND_WORD = "free";

    @Override
    public String execute(TaskList tasks) {
        return "gay";
    }
}

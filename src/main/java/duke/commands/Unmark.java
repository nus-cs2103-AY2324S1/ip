package duke.commands;

import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.TaskList;

public class Unmark extends Command{

    public Unmark(Storage storage, Parser parser, TaskList tasks) {
        super(storage, parser, tasks);
    }

    @Override
    public String execute() {
        String message;
        try {
            int i = Integer.parseInt(parser.getTaskName()) - 1;
            message = tasks.unmarkTask(i);
            store();
        } catch (NumberFormatException e) {
            message = "need to provide an integer index of task.";
        }

        return message;
    }
}
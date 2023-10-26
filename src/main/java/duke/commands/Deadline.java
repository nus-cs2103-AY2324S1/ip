package duke.commands;

import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.Task;
import duke.helper.TaskList;

public class Deadline extends Command{

    public Deadline(Storage storage, Parser parser, TaskList tasks) {
        super(storage, parser, tasks);
    }

    @Override
    public String execute() {
        String message;
        if (parser.getTaskName().isEmpty()) {
            return  "Task name cannot be empty";
        }
        if (isValidDateFormat(parser.getFirstEnteredTime())) {
            message = tasks.addTask(new Task(parser.getTaskName(),
                    2, "Null", parser.getFirstEnteredTime(), false));
            store();
        } else {
            message = "Invalid date format. Please enter as /by yyyy-mm-dd.";
        }

        return message;
    }
}
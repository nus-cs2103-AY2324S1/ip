package duke.commands;

import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.Task;
import duke.helper.TaskList;

public class Event extends Command{

    public Event(Storage storage, Parser parser, TaskList tasks) {
        super(storage, parser, tasks);
    }

    @Override
    public String execute() {
        String message;
        if (parser.getTaskName().isEmpty()) {
            return "Task name cannot be empty";
        }
        if (isValidDateFormat(parser.getFirstEnteredTime()) &&
                isValidDateFormat(parser.getSecondEnteredTime())) {
            message = tasks.addTask(new Task(parser.getTaskName(),
                    3, parser.getFirstEnteredTime(), parser.getSecondEnteredTime(), false));
            store();
        } else {
            message = "Invalid date format. Please enter as /from yyyy-mm-dd /to yyyy-mm-dd.";
        }

        return message;
    }
}
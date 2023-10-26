package duke.commands;

import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.Task;
import duke.helper.TaskList;

public class Todo extends Command{

    public Todo(Storage storage, Parser parser, TaskList tasks) {
        super(storage, parser, tasks);
    }

    @Override
    public String execute() {
        String message;
        if (parser.getTaskName().isEmpty()) {
            return "Task name cannot be empty";
        }
        message = tasks.addTask(new Task(parser.getTaskName(),
                1, "Null", "Null", false));
        store();


        return message;
    }
}

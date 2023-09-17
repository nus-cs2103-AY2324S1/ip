package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder();
        message.append("Here are the available commands:\n");
        message.append("- `list`: List all tasks\n");
        message.append("- `bye`: Exit the program\n");
        message.append("- `hi`: Display a welcome message\n");
        message.append("- `mark <index>`: Mark a task as done\n");
        message.append("- `unmark <index>`: Mark a task as not done\n");
        message.append("- `delete <index>`: Delete a task\n");
        message.append("- `find <keyword>`: Find tasks by keyword\n");
        message.append("- `help`: Display this help message\n");
        message.append("- `todo <description> /from <fromDate> /to <toDate>`: Add a Todo task\n");
        message.append("- `deadline <description> /by <byDate>`: Add a Deadline task\n");
        message.append("- `event <description> /at <atDate>`: Add an Event task\n");
        return message.toString();
    }
}

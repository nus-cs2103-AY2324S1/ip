package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return "I'm Chatty, here to help you with your ever-growing list of things to do. "
                + "I have a list of pretty handy commands:\n"
                + "help: displays the list of commands available, as well as how to use them\n"
                + "list: lists all the tasks you currently have recorded here\n"
                + "todo <description>: create a new Todo task\n"
                + "event <description> /from <yyyy-mm-dd HHMM> /to <yyyy-mm-dd HHMM>: "
                + "create a new Event task from <time> to <time>\n"
                + "deadline <description> /by <yyyy-mm-dd HHMM>: create a new Deadline task to do by <time>\n"
                + "mark <number>: mark the task <number> as done\n"
                + "unmark <number>: mark the task <number> as incomplete\n"
                + "delete <number>: delete task <number> on your list\n"
                + "find <description>: fetches all tasks that match the specified <description> given. "
                + "It's just a search function.\n"
                + "You'll forget this, so just type 'help' whenever you need it.";
    }
}

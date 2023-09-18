package command;

import duke.DukeException;
import duke.Storage;
import task.TaskList;

/**
 * Prints out a help message for users
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    protected String command;

    public HelpCommand() {
        this.command = COMMAND_WORD;
    }

    public HelpCommand(String command) {
        this.command = command;
    }

    /**
     * Displays a help message which shows the command and instructions to user
     *
     * @param task TaskList which contains an ArrayList of tasks
     * @param storage File path where the tasks are stored
     * @return help message with command and instructions
     */
    @Override
    public String execute(TaskList task, Storage storage) throws DukeException {
        switch (this.command) {
        case HelpCommand.COMMAND_WORD:
            return "Type 'help <command>' for instructions on how to use a particular command\n"
                    + "Available commands: deadline, event, todo, find, mark, unmark, delete, list, bye";
        case DeadlineCommand.COMMAND_WORD:
            return "Deadline: adds a deadline to the task list\n"
                    + "Format: deadline <task description> /by <date/time to do by>\n"
                    + "All dates/times should be in the format dd/mm/yyyy hhmm, eg: 01/01/2024 2359";
        case EventCommand.COMMAND_WORD:
            return "Event: adds an event to the task list\n"
                    + "Format: event <task description> /from <starting date/time> /to <ending date/time>\n"
                    + "All dates/times should be in the format dd/mm/yyyy hhmm, eg: 01/01/2024 2359";
        case TodoCommand.COMMAND_WORD:
            return "Todo: adds a todo to the task list\n"
                    + "Format: todo <task description>";
        case MarkCommand.COMMAND_WORD:
            return "Mark: mark a task as done\n"
                    + "Format: mark <index of task as shown in list>";
        case UnmarkCommand.COMMAND_WORD:
            return "Unmark: mark a task as undone\n"
                    + "Format: unmark <index of task as shown in list>";
        case DeleteCommand.COMMAND_WORD:
            return "Delete: delete a task from the task list\n"
                    + "Format: delete <index of task as shown in list>";
        case FindCommand.COMMAND_WORD:
            return "Find: search for tasks in the task list\n"
                    + "Format: find <keyword to search by>";
        case ListCommand.COMMAND_WORD:
            return "List: displays the current task list\n"
                    + "Format: list";
        case "bye":
            return "Bye: exits the application\n"
                    + "Format: bye";
        default:
            throw new DukeException("Sorry, I do not recognise this command!"
                    + "Type 'help' to see the list of commands");
        }
    }
}

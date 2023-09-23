package avalon.utility;

import avalon.AvalonException;
import avalon.command.DeadlineCommand;
import avalon.command.DeleteCommand;
import avalon.command.EventCommand;
import avalon.command.ExitCommand;
import avalon.command.FindCommand;
import avalon.command.ListCommand;
import avalon.command.MarkCommand;
import avalon.command.PriorityCommand;
import avalon.command.TodoCommand;
import avalon.command.UnmarkCommand;
import avalon.task.TaskList;

/**
 * The `Parser` class is responsible for processing user commands and executing the corresponding actions.
 * It converts user input into executable commands and handles exceptions when necessary.
 */
public class Parser {
    /**
     * Processes the user's input, determines the command type, and executes the corresponding action.
     *
     * @param input    The user's input command.
     * @param taskList The TaskList to which tasks are added or manipulated.
     * @param storage  The storage handler for loading and saving tasks.
     * @param ui       The user interface for displaying information to the user.
     * @return A response message generated as a result of executing the command.
     */
    public static String processCommand(String input, TaskList taskList, Storage storage, Ui ui) {

        String[] inputStr = input.split(" ");
        String command = inputStr[0];

        try {
            switch (command) {
            case "bye":
                ExitCommand exit = new ExitCommand();
                return exit.execute(taskList, storage, ui);
            case "list":
                ListCommand list = new ListCommand();
                return list.execute(taskList, storage, ui);
            case "mark":
                MarkCommand mark = new MarkCommand(input);
                return mark.execute(taskList, storage, ui);
            case "unmark":
                UnmarkCommand unmark = new UnmarkCommand(input);
                return unmark.execute(taskList, storage, ui);
            case "todo":
                TodoCommand todo = new TodoCommand(input);
                return todo.execute(taskList, storage, ui);
            case "deadline":
                DeadlineCommand deadline = new DeadlineCommand(input);
                return deadline.execute(taskList, storage, ui);
            case "event":
                EventCommand event = new EventCommand(input);
                return event.execute(taskList, storage, ui);
            case "delete":
                DeleteCommand delete = new DeleteCommand(input);
                return delete.execute(taskList, storage, ui);
            case "find":
                FindCommand find = new FindCommand(input);
                return find.execute(taskList, storage, ui);
            case "priority":
                PriorityCommand priority = new PriorityCommand(input);
                return priority.execute(taskList, storage, ui);
            default:
                throw new AvalonException("I humbly apologize, but thy words remain a mystery to me...");
            }
        } catch (AvalonException e){
            return "OOPS!!! " + e.getMessage();
        }
    }
}

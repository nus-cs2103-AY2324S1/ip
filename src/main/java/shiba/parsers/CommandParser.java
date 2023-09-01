package shiba.parsers;

import java.util.Scanner;

import shiba.commands.DeadlineCommand;
import shiba.commands.DeleteCommand;
import shiba.commands.EventCommand;
import shiba.commands.FindCommand;
import shiba.commands.ListCommand;
import shiba.commands.MarkCommand;
import shiba.commands.ShibaCommand;
import shiba.commands.TodoCommand;
import shiba.commands.UnmarkCommand;
import shiba.exceptions.ShibaException;
import shiba.tasks.FilePersistentTaskList;
import shiba.ui.Replier;

/**
 * Parses user input and executes the corresponding commands.
 */
public class CommandParser {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final FilePersistentTaskList tasks;

    /**
     * Creates a new CommandParser object.
     *
     * @param tasks TaskList object to be used by the parser.
     */
    public CommandParser(FilePersistentTaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Continually processes user input until bye command is issued
     */
    public void processUserInputs() {
        while (true) {
            String input = SCANNER.nextLine().strip();

            try {
                ShibaCommand.CommandType command = ShibaCommand.CommandType.valueOf(
                        input.split(" ")[0].toUpperCase());
                ShibaCommand shibaCommand;
                switch (command) {
                case LIST:
                    shibaCommand = new ListCommand(tasks);
                    break;
                case MARK:
                    shibaCommand = new MarkCommand(tasks, input);
                    break;
                case UNMARK:
                    shibaCommand = new UnmarkCommand(tasks, input);
                    break;
                case TODO:
                    shibaCommand = new TodoCommand(tasks, input);
                    break;
                case DEADLINE:
                    shibaCommand = new DeadlineCommand(tasks, input);
                    break;
                case EVENT:
                    shibaCommand = new EventCommand(tasks, input);
                    break;
                case DELETE:
                    shibaCommand = new DeleteCommand(tasks, input);
                    break;
                case FIND:
                    shibaCommand = new FindCommand(tasks, input);
                    break;
                case BYE:
                    return;
                default:
                    throw new IllegalArgumentException();
                }
                shibaCommand.execute();
            } catch (ShibaException e) {
                Replier.printException(e);
            } catch (IllegalArgumentException e) {
                Replier.printUnknownCommand();
            }
        }
    }
}

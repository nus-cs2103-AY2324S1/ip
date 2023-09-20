package linus;

import linus.command.ByeCommand;
import linus.command.Command;
import linus.command.DeadlineCommand;
import linus.command.DeleteCommand;
import linus.command.EventCommand;
import linus.command.FindCommand;
import linus.command.HelpCommand;
import linus.command.ListCommand;
import linus.command.MarkCommand;
import linus.command.StatsCommand;
import linus.command.ToDoCommand;
import linus.command.UnmarkCommand;
import linus.exception.LinusException;
import linus.storage.Storage;
import linus.task.TaskList;
import linus.util.Parser;
import linus.util.Ui;

/**
 * Represents a Linus Chatbot.
 */
public class Linus {
    private static final String FILE_PATH = "data/linus.txt";
    private static final String BYE_FLAG = "bye";
    private static final String HELP_FLAG = "help";
    private static final String LIST_FLAG = "list";
    private static final String TODO_FLAG = "todo";
    private static final String DEADLINE_FLAG = "deadline";
    private static final String EVENT_FLAG = "event";
    private static final String MARK_FLAG = "mark";
    private static final String UNMARK_FLAG = "unmark";
    private static final String DELETE_FLAG = "delete";
    private static final String FIND_FLAG = "find";
    private static final String STATS_FLAG = "stats";


    private static final String INVALID_COMMAND_MESSAGE =
            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "Please start your inputs with a valid command.";
    private Storage storage = null;
    private TaskList tasks = null;

    private Ui ui = null;

    /**
     * Constructs a Linus Chatbot with the default file path.
     */
    public Linus() {
        this(FILE_PATH);
    }

    /**
     * Constructs a Linus Chatbot with the specified file path.
     *
     * @param filePath The file path.
     */
    public Linus(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (LinusException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    @Override
    public String toString() {
        return ui.printWelcomeMessage();
    }

    public String getResponse(String input) {
        ui.resetOutput();
        Command command = null;
        try {
            String[] commandAndData = Parser.parse(input);
            String commandString = commandAndData[0];
            String data = commandAndData[1];

            switch (commandString) {
            case BYE_FLAG:
                command = new ByeCommand(ui);
                command.execute();
                break;
            case HELP_FLAG:
                command = new HelpCommand(ui);
                command.execute();
                break;
            case LIST_FLAG:
                command = new ListCommand(tasks, ui);
                command.execute();
                break;
            case MARK_FLAG:
                command = new MarkCommand(tasks, data, ui);
                command.execute();
                break;
            case UNMARK_FLAG:
                command = new UnmarkCommand(tasks, data, ui);
                command.execute();
                break;
            case DELETE_FLAG:
                command = new DeleteCommand(tasks, data, ui);
                command.execute();
                break;
            case FIND_FLAG:
                command = new FindCommand(tasks, data, ui);
                command.execute();
                break;
            case TODO_FLAG:
                command = new ToDoCommand(tasks, data, ui);
                command.execute();
                break;
            case DEADLINE_FLAG:
                command = new DeadlineCommand(tasks, data, ui);
                command.execute();
                break;
            case EVENT_FLAG:
                command = new EventCommand(tasks, data, ui);
                command.execute();
                break;
            case STATS_FLAG:
                command = new StatsCommand(tasks, data, ui);
                command.execute();
                break;
            default:
                throw new IllegalArgumentException();
            }
            storage.store(tasks.getList());
        } catch (IllegalArgumentException e) {
            ui.print(INVALID_COMMAND_MESSAGE);
        } catch (LinusException e) {
            ui.print(e.getMessage());
        }
        return ui.getOutput();
    }

}

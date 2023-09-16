package linus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import linus.exception.LinusException;
import linus.storage.Storage;
import linus.task.Deadline;
import linus.task.Event;
import linus.task.TaskList;
import linus.task.ToDo;
import linus.util.Parser;
import linus.util.Ui;

/**
 * Represents a Linus Chatbot.
 */
public class Linus {
    private static final String FILE_PATH = "data/linus.txt";

    private static final String STATS_COMMAND_REGEX =
            "/duration (\\d+)"
                    + "( /task (todo|deadline|event))?"
                    + "( /done)?";

    private static enum Command {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        TODO,
        DEADLINE,
        EVENT,
        STATS,
        HELP,
        BYE
    }

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
        try {
            String[] items = null;
            String description = "";
            int index = 0;

            String[] commandAndData = Parser.parse(input);
            String command = commandAndData[0];
            String data = commandAndData[1];

            switch (Command.valueOf(command.toUpperCase())) {
            case BYE:
                ui.printExitMessage();
                break;
            case HELP:
                ui.printHelpMessage();
                break;
            case LIST:
                ui.printList(tasks.getList(), "Here are the tasks in your list:\n");
                break;
            case MARK:
                index = Integer.parseInt(data) - 1;
                tasks.mark(index);
                ui.printMarkSuccessMessage(tasks.get(index), tasks.getList().size());
                break;
            case UNMARK:
                index = Integer.parseInt(data) - 1;
                tasks.unmark(index);
                ui.printUnmarkSuccessMessage(tasks.get(index), tasks.getList().size());
                break;
            case DELETE:
                index = Integer.parseInt(data) - 1;
                tasks.delete(index);
                ui.printDeleteSuccessMessage(tasks.get(index), tasks.getList().size());
                break;
            case FIND:
                ui.printFindSuccessMessage(tasks.find(data));
                break;
            case TODO:
                if (data == "") {
                    throw new LinusException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                description = data;
                tasks.add(new ToDo(description));
                ui.printAddSuccessMessage(tasks.get(tasks.getList().size() - 1), tasks.getList().size());
                break;
            case DEADLINE:
                items = data.split(" /by ");
                if (items.length != 2) {
                    throw new LinusException(
                            "☹ OOPS!!! Please specify the deadline in the correct format: "
                                    + "deadline <description> /by <date>"
                    );
                }

                description = items[0];
                String by = items[1];

                tasks.add(new Deadline(description, by));
                ui.printAddSuccessMessage(tasks.get(tasks.getList().size() - 1), tasks.getList().size());
                break;
            case EVENT:
                items = data.split(" /from | /to ");
                if (items.length != 3) {
                    throw new LinusException(
                            "☹ OOPS!!! Please specify the event in the correct format: \n"
                                    + "event <description> /from <date> /to <date>"
                    );
                }
                description = items[0];
                String from = items[1];
                String to = items[2];

                tasks.add(new Event(description, from, to));
                ui.printAddSuccessMessage(tasks.get(tasks.getList().size() - 1), tasks.getList().size());
                break;
            case STATS:
                Pattern pattern = Pattern.compile(STATS_COMMAND_REGEX);
                Matcher matcher = pattern.matcher(data);
                if (matcher.matches()) {
                    int duration = Integer.parseInt(matcher.group(1));
                    String taskType = null;
                    if (matcher.group(2) != null) {
                        taskType = matcher.group(3);
                    }
                    boolean isFilterByDone = matcher.group(4) != null;
                    ui.printStats(tasks.showStats(duration, taskType, isFilterByDone));
                } else {
                    throw new LinusException(
                            "☹ OOPS!!! Please specify the stats in the correct format: \n"
                                    + "stats /duration <number of days> /task <taskType> /done \n"
                                    + "where /task <taskType> and /done are optional"
                    );
                }

                break;
            default:
                throw new IllegalArgumentException();
            }
            storage.store(tasks.getList());
        } catch (IllegalArgumentException e) {
            ui.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "Please start your inputs with a valid command.");
        } catch (LinusException e) {
            ui.print(e.getMessage());
        }
        return ui.getOutput();
    }

}

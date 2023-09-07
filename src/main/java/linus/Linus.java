package linus;

import linus.exception.LinusException;
import linus.task.Deadline;
import linus.task.Event;
import linus.task.TaskList;
import linus.task.ToDo;
import linus.util.Parser;
import linus.util.Storage;
import linus.util.Ui;

/**
 * Represents a Linus Chatbot.
 */
public class Linus {
    private static final String FILE_PATH = "data/linus.txt";

    private static enum Command {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        TODO,
        DEADLINE,
        EVENT,
        HELP,
        BYE
    }

    private Storage storage = null;
    private TaskList tasks = null;

    private Ui ui = null;

    /**
     * Constructs a Linus Chatbot with the specified file path.
     *
     * @param filePath The file path.
     */
    public Linus(String filePath) {
        storage = new Storage("data/linus.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (LinusException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    @Override public String toString() {
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
                index = Integer.parseInt(data);
                tasks.mark(index);
                ui.printMarkSuccessMessage(tasks.get(index), tasks.getList().size());
                break;
            case UNMARK:
                index = Integer.parseInt(data);
                tasks.unmark(index);
                ui.printUnmarkSuccessMessage(tasks.get(index), tasks.getList().size());
                break;
            case DELETE:
                index = Integer.parseInt(data);
                tasks.delete(index);
                ui.printDeleteSuccessMessage(tasks.get(index), tasks.getList().size());
                break;
            case FIND:
                tasks.find(data, ui);
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
                if (data == "") {
                    throw new LinusException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }

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
                if (data == "") {
                    throw new LinusException("☹ OOPS!!! The description of a todo cannot be empty.");
                }

                items = data.split(" /from | /to ");
                if (items.length != 3) {
                    throw new LinusException(
                            "☹ OOPS!!! Please specify the event in the correct format: "
                                    + "event <description> /from <date> /to <date>"
                    );
                }
                description = items[0];
                String from = items[1];
                String to = items[2];

                tasks.add(new Event(description, from, to));
                ui.printAddSuccessMessage(tasks.get(tasks.getList().size() - 1), tasks.getList().size());
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

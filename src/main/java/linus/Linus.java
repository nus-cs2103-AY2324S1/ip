package linus;

import linus.exception.LinusException;
import linus.task.Deadline;
import linus.task.Event;
import linus.task.TaskList;
import linus.task.ToDo;
import linus.util.Parser;
import linus.util.Storage;
import linus.util.Ui;

public class Linus {
    private static final String FILE_PATH = "data/linus.txt";

    private static enum Command {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        BYE
    }

    private Storage storage = null;
    private TaskList tasks = null;

    /**
     * Constructs a Linus Chatbot with the specified file path.
     * @param filePath The file path.
     */
    public Linus(String filePath) {
        storage = new Storage("data/linus.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (LinusException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        Ui.showWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String[] items = null;
                String description = "";
                int index = 0;

                String input = Ui.readInput();
                String[] commandAndData = Parser.parse(input);
                String command = commandAndData[0];
                String data = commandAndData[1];

                switch (Command.valueOf(command.toUpperCase())) {
                case BYE:
                    isExit = true;
                    break;
                case LIST:
                    tasks.list();
                    break;
                case MARK:
                    index = Integer.parseInt(data);
                    tasks.mark(index);
                    break;
                case UNMARK:
                    index = Integer.parseInt(data);
                    tasks.unmark(index);
                    break;
                case DELETE:
                    index = Integer.parseInt(data);
                    tasks.delete(index);
                    break;
                case TODO:
                    if (data == "") {
                        throw new LinusException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    description = data;
                    tasks.add(new ToDo(description));
                    break;
                case DEADLINE:
                    if (data == "") {
                        throw new LinusException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                    items = data.split(" /by ");
                    if (items.length != 2) {
                        throw new LinusException("☹ OOPS!!! Please specify the deadline in the correct format: deadline <description> /by <date>");
                    }

                    description = items[0];
                    String by = items[1];

                    tasks.add(new Deadline(description, by));
                    break;
                case EVENT:
                    if (data == "") {
                        throw new LinusException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    items = data.split(" /from | /to ");
                    if (items.length != 3) {
                        throw new LinusException("☹ OOPS!!! Please specify the event in the correct format: event <description> /from <date> /to <date>");
                    }
                    description = items[0];
                    String from = items[1];
                    String to = items[2];

                    tasks.add(new Event(description, from, to));
                    break;
                }
            } catch (IllegalArgumentException e) {
                Ui.print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "Please start your inputs with a valid command.");
            } catch (LinusException e) {
                Ui.print(e.getMessage());
            }
        }
        storage.store(tasks.getList());
        Ui.showExitMessage();
    }

    public static void main(String[] args) {
        new Linus(FILE_PATH).run();
    }
}
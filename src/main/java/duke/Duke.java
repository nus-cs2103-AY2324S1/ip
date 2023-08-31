package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Duke is a Chatbot that helps you keep track of your tasks.
 * In this version, Duke is renamed to Nano.
 */
public class Duke {

    /** The Indentation Level to format text. */
    public static final String INDENTATION = "    ";

    /** The storage that is used to save and load the user's tasks. */
    private static Storage storage;

    /** The task list that is used to store the user's tasks. */
    private TaskList tasks;

    /** The UI to interact with the User. */
    private Ui ui;

    /** The file path to store the User's Task Data. */
    private static final String FILEPATH = "./data/duke.txt";

    /**
     * Handles the Chatbot functionality.
     */
    private void handleCommand() {
        String commandString;
        Command command;

        while (true) {
            commandString = ui.getInput();
            Parser parseLine = new Parser(commandString);
            command = parseLine.getCommand();

            if (command == null) {
                ui.printInvalidCommandError();
                continue;
            }

            try {
                switch (command) {
                    case BYE:
                        ui.printBye();
                        return;
                    case LIST:
                        ui.printOutput(tasks.formatList());
                        break;
                    case MARK:
                    case UNMARK:
                        ui.printOutput(tasks.handleMarking(parseLine.getArguments(), command.getCommandName()));
                        break;
                    case DELETE:
                        ui.printOutput(tasks.handleDelete(parseLine.getArguments()));
                        break;
                    case TODO:
                        String todoData = parseLine.parseToDoArguments();
                        ui.printOutput(tasks.handleToDo(todoData));
                        break;
                    case DEADLINE:
                        String[] deadlineData = parseLine.parseDeadlineArguments();
                        ui.printOutput(tasks.handleDeadline(deadlineData[0], deadlineData[1]));
                        break;
                    case EVENT:
                        String[] eventData = parseLine.parseEventArguments();
                        ui.printOutput(tasks.handleEvent(eventData[0], eventData[1], eventData[2]));
                        break;
                    default:
                        ui.printOutput("I don't understand what you're saying.");
                        break;
                }
            } catch (DukeException e) {
                ui.printOutput(e.getMessage());
            }
        }

    }

    /**
     * Runs the Chatbot program.
     */
    public void run() {
        storage = new Storage(FILEPATH);
        ui = new Ui();
        ui.printGreeting();

        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            System.out.println("--- No Data Stored ---");
            tasks = new TaskList(new ArrayList<>(), storage);
        }

        handleCommand();
    }

    /**
     * The main method is used to run the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}

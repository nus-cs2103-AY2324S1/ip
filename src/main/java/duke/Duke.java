package duke;

import duke.commands.Command;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.InvalidTaskIndexException;
import duke.exceptions.MissingTaskIndexException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * The main class for the Ding Chatbot application.
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs a new instance of the duke.Duke class.
     * Initializes the storage and task list.
     */
    public Duke() {
        storage = new Storage("./data", "./data/ding.txt");
        tasks = storage.getTasks();
    }

    /**
     * Runs the main loop of the chatbot application.
     * Handles user input, executes commands, and interacts with the user.
     */
    public void run() {
        Ui.introReply();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            try {
                Command command = Parser.getCommand(str);
                switch (command) {
                case LIST:
                    Ui.printListReply(tasks);
                    break;
                case MARK:
                case UNMARK:
                    Ui.markTaskReply(Parser.taskToMark(str, tasks), tasks, command);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    tasks.addTask(Parser.parseStringToTask(str));
                    Ui.addTaskReply(str, tasks);
                    break;
                case DELETE:
                    Ui.deleteTaskReply(Parser.taskToDelete(str, tasks), tasks);
                    break;
                case BYE:
                    isRunning = false;
                    Ui.exitReply();
                    return;
                default:
                    Ui.illegalArgumentExceptionReply();
                }
            } catch (InvalidTaskIndexException e) {
                Ui.invalidTaskIndexExceptionReply(tasks);
            } catch (MissingTaskIndexException e) {
                Ui.missingTaskIndexExceptionReply();
            } catch (InvalidDescriptionException e) {
                Ui.invalidDescriptionExceptionReply();
            } catch (InvalidDateTimeException e) {
                Ui.invalidDateTimeExceptionReply();
            } finally {
                storage.updateStorage(tasks);
                if (isRunning) {
                    Ui.horizontalLineReply();
                    str = sc.nextLine();
                }
            }
        }
    }

    /**
     * Main function for Ding Chatbot
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}

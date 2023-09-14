package duke;

import java.time.format.DateTimeParseException;
import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.utils.Commands;
import duke.utils.Parser;
import duke.utils.Ui;

/**
 * Chatbot that takes in commands.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for creating a Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.readFile());
    }

    /**
     * Returns the response to the user's input
     * @param input The user's input
     * @return Toothless' response
     */
    public String getResponse(String input) {

        String output;
        Commands command = Parser.determineCommand(input);

        try {

            switch (command) {
            case TODO:
            case DEADLINE:
            case EVENT:
                Task t = TaskList.createTask(input, command, 0);
                tasks.addTask(t);
                output = ui.showTaskAdded(t.getTask());
                break;

            case LIST:
                if (tasks.isEmpty()) {
                    output = ui.showNoTasks();
                } else {
                    output = ui.showTasks(tasks.getTasksDes(1), 0);
                }
                break;


            case UNMARK:
            case MARK:
                String completionStatus = tasks.changeTaskCompletion(input, command);
                output = completionStatus;
                break;

            case FIND:
                List<String> matchingTasks = tasks.findTask(input);
                output = ui.showTasks(matchingTasks, 1);
                break;

            case DELETE:
                String deleteStatus = tasks.deleteTask(input);
                output = deleteStatus;
                break;

            case BYE:
                String savedStatus = storage.saveToDisk(tasks.getTasksDes(0));
                output = savedStatus + "\n" + ui.farewell();
                break;

            case UNKNOWN:
            default:
                throw new InvalidInputException("Invalid input");
            }
        } catch (DukeException e) {
            output = ui.showDukeError(e);
        } catch (DateTimeParseException e) {
            output = ui.showDateError();
        } catch (Exception e) {
            output = ui.showGeneralError();
        }

        return output;
    }
}

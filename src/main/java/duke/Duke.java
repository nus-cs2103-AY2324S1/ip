package duke;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

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
     * @param path Location of the list of tasks.
     */
    public Duke(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        this.tasks = new TaskList(storage.readFile());
    }

    /**
     * Starts the chatbot.
     */
    public void run() {

        boolean end = false;

        Scanner scanner = new Scanner(System.in);

        ui.greet();

        while (!end) {
            try {
                String nextInput = scanner.nextLine().trim();
                Commands command = Parser.determineCommand(nextInput);

                switch (command) {
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        Task t = TaskList.createTask(nextInput, command, 0);
                        tasks.addTask(t);
                        ui.showTaskAdded(t.getTask());
                        break;

                    case LIST:
                        if (tasks.isEmpty()) {
                            ui.showNoTasks();
                        } else {
                            ui.showTasks(tasks.getTasksDes(1), 1);
                        }
                        break;

                    case UNMARK:
                    case MARK:
                        String completionStatus = tasks.changeTaskCompletion(nextInput, command);
                        ui.showStatusChanged(completionStatus);
                        break;

                    case FIND:
                        List<String> matchingTasks = tasks.findTask(nextInput);
                        ui.showTasks(matchingTasks, 1);
                        break;

                    case DELETE:
                        String deleteStatus = tasks.deleteTask(nextInput);
                        ui.showStatusChanged(deleteStatus);
                        break;

                    case BYE:
                        end = true;
                        String savedStatus = storage.saveToDisk(tasks.getTasksDes(0));
                        ui.showStatusChanged(savedStatus);
                        break;

                    case UNKNOWN:
                    default:
                        throw new InvalidInputException("Invalid input");
                }

            } catch (DukeException e) {
                ui.showDukeError(e);
            } catch (DateTimeParseException e) {
                ui.showDateError();
            } catch (Exception e) {
                ui.showGeneralError();
            }
            ui.separator();
        }
        ui.farewell();
    }

    public static void main(String[] args) {
        new Duke("toothless.txt").run();
    }
}
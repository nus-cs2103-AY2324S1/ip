import Exceptions.DukeException;
import Exceptions.InvalidInputException;
import Tasks.Task;
import Tasks.TaskList;
import Utils.Commands;
import Storage.Storage;
import Utils.Parser;
import Utils.Ui;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Chatbot that takes in commands.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

        while(!end) {
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
                            ui.showAllTasks(tasks.getTasksDes(1));
                        }
                        break;

                    case UNMARK:
                    case MARK:
                        String completionStatus = tasks.changeTaskCompletion(nextInput, command);
                        ui.showStatusChanged(completionStatus);
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
        new Duke("src/main/java/Storage/toothless.txt").run();
    }
}

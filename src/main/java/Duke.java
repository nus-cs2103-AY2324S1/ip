import Exceptions.DukeException;
import java.util.Scanner;
public class Duke {
    private final Ui _ui;
    private TaskList _taskList;
    private final Storage _storage;
    public static final String DATA_FILE_PATH = "data/duke.txt";

    public Duke(String filepath) {
        _ui = new Ui();
        _storage = new Storage(filepath);
        try {
            _taskList = new TaskList(_storage.load());
        } catch (DukeException e) {
            _ui.showDukeError(e);
            _taskList = new TaskList();
        }
    }

    public void run() {
        _ui.showWelcome();
        // Create a scanner object to read commands entered by the user
        Scanner scanner = new Scanner(System.in);

        // Start command loop
        while (true) {
            // Read the next line of input
            String userInput = scanner.nextLine();

            try {
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equals("help")) {
                    _ui.displayHelpMessage();
                } else if (userInput.equals("list")) {
                    _taskList.listTasks();
                } else if (userInput.startsWith("mark")) {
                    _taskList.markTaskAsDone(userInput);
                    _taskList.saveTask(_storage.filepath);
                } else if (userInput.startsWith("unmark")) {
                    _taskList.unmarkTaskAsDone(userInput);
                    _taskList.saveTask(_storage.filepath);
                } else if (userInput.startsWith("delete")) {
                    _taskList.deleteTask(userInput);
                    _taskList.saveTask(_storage.filepath);
                } else if (userInput.startsWith("find")) {
                    _taskList.findTasks(userInput);
                } else {
                    _taskList.addTask(userInput);
                    _taskList.saveTask(_storage.filepath);
                }
            } catch (DukeException e) {
                _ui.showDukeError(e);
            } catch (Exception e) {
                _ui.showException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(DATA_FILE_PATH).run();
    }
}
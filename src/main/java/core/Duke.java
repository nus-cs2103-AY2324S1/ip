package core;
import Duke.storage.Storage;
import Duke.parser.Parser;
import Duke.tasks.TaskList;
import Duke.tasks.TaskManager;
import Duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private TaskManager tm;


    public enum CommandType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        MARK,
        UNMARK,
        BYE,
        FIND,
        UNKNOWN
    }

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
    }
    

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);  
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        tm = new TaskManager(tasks);
    }

    public Duke() {

    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            String userCommand = ui.readUserCommand();
            Parser p = new Parser();
            CommandType commandType = p.getCommandType(userCommand);
            switch (commandType) {
                case LIST:
                    ui.showTasks(tasks);
                    break;
                case BYE:
                    ui.showGoodbye();
                    storage.updateData(tasks);
                    isRunning = false;
                    break;
                case DELETE:
                    ui.showMessage(tm.handleDelete(userCommand));
                    break;
                case MARK:
                    ui.showMessage(tm.handleMark(userCommand));
                    break;
                case UNMARK:
                    ui.showMessage(tm.handleUnmark(userCommand));
                    break;
                case EVENT:
                case DEADLINE:
                    case TODO:
                    ui.showMessage(tm.addTask(userCommand));
                    break;
                case FIND:
                    ui.showMessage(tm.findTasks(userCommand));
                    break;
                case UNKNOWN:
                    ui.showError("unknown command.");
                    break;    
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello my friends!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
package duke;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskArray;
import duke.ui.DialogBox;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;


/**
 * The main class for the Duke chatbot application.
 */
public class Duke {
    private Ui ui;
    private TaskArray taskArrayList;
    private Storage storage;
    public final static String HORIZONTAL_LINE = "____________________________________________________________";
    public final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private boolean end = false;

    private static Duke globalDuke = new Duke();
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DukeA.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DukeB.jpeg"));


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {
        this("./data/tasks.txt");
    }

    public static Duke getGlobalDuke() {
        return globalDuke;
    }


    public static void main(String[] args) {
        Launcher.main(args);
    }
    /**
     * Constructs a Duke object with the given file path for storage.
     *
     * @param filePath The file path to use for storing tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskArrayList = storage.load();

        } catch (Exception e) {
            //ui.showLoadingError();
            this.taskArrayList = new TaskArray();
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        Ui.greetFunction("KimDuke");
        ui.helpFunction();

        taskArrayList = ui.runTask(taskArrayList);
        storage.upload(taskArrayList);
    }

    /**
     * Return response from Duke after processing the input and also modify if user input is bye.
     *
     * @param input The input from user.
     */
    public String getResponse(String input) {
        String output = ui.runGUITask(taskArrayList,input);

        if (output.equals(ui.byeFunction())) {
            end = true;
        }

        if (output.equals(ui.byeFunction())) {
            end = true;
        }

        return "KimDuke : " + output;
    }

    public static String saveData(){
        globalDuke.storage.upload(globalDuke.taskArrayList);
        return "Task Data is successfully saved into File Database";
    }
    public static String clearData(){
        globalDuke.taskArrayList = new TaskArray();
        return "All Tasks is successfully cleared, use command \"save\" to update changes in File Database\n";
    }

    public static TaskArray getTaskArrayList() {
        return globalDuke.taskArrayList;
    }

    public boolean isEnd(){
        return end;
    }

}

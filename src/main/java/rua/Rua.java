package rua;

import javafx.application.Application;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import rua.command.Command;
import rua.common.Parser;
import rua.common.Storage;
import rua.common.StringLogger;
import rua.common.Ui;
import rua.task.TaskList;

public class Rua {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaRua.png"));
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Rua() {
        ui = new Ui();
        storage = new Storage("src/main/data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
            ui.showMessage("Load successfully. Now you have " + tasks.getTasks().size()
                    + " tasks in the list.\n");
            ui.showMessage(tasks.toString());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a chatbot Rua object.
     *
     * @param filePath The path to the file which stores the tasks.
     */
    public Rua(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showMessage("Load successfully. Now you have " + tasks.getTasks().size()
                    + " tasks in the list.\n");
            ui.showMessage(tasks.toString());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Rua("src/main/data/tasks.txt").run();
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                ui.showLine(); // show the divider line ("_______")
                tasks = c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        StringLogger.clear();
        try {
            Command c = Parser.parse(input);
            tasks = c.execute(tasks, ui, storage);
        } catch (Exception e) {
            ui.showError(e.toString());
        }
        return StringLogger.getLog();
    }

}

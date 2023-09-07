package adam;

import adam.command.Command;
import adam.exception.AdamException;
import adam.tasks.Task;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * This is the main java class that contains instructions to create the chatbot Tasks.Task manager Adam.Adam.
 */
public class Adam extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpeg"));
    private Image adam = new Image(this.getClass().getResourceAsStream("/images/DaAdam.jpeg"));
    private Scene scene;
    private Storage storage;
    private TaskList list;
    private Ui ui;

    /**
     * Creates an instance of an Adam Object that is used to initialize the other objects.
     */
    public Adam() {
        ui = new Ui();
        storage = new Storage();
        try {
            list = new TaskList(storage.read());
        } catch (AdamException e) {
            list = new TaskList(new ArrayList<Task>());
        }

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(list, storage, ui);
        } catch (AdamException e) {
            return ui.displayError(e.getInfo());
        }
    }

    /**
     * Starts the program and this method will only end when the program stops running.
     */
    @Override
    public void start(Stage stage) {
        boolean isRunning =  true;
        ui.welcome();
            while (isRunning) {
                try {
                    String li = ui.readInput();
                    Command command = Parser.parse(li);
                    command.execute(list, storage, ui);
                    isRunning = list.isRunning();
                    }
                catch (AdamException e) {
                    ui.displayError(e.getInfo());
                }
            }
    }

}

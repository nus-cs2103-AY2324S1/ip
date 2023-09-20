package duke;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {
    private Storage storage = new Storage("data/list.txt");
    private Tasklist tasks = new Tasklist(storage.getTasks());

    public Duke() {
    }

    @Override
    public void start(Stage stage) {
    }

    /**
     * Generates a response based on user input.
     *
     * @param input User input string.
     * @return Duke's response string.
     */
    public String getResponse(String input) {
        return Parser.parseResponse(input, tasks, storage);
    }

}

package seedu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;


/**
 * The main class
 */
public class Duke {

    private ScrollPane scrollPane;
    private Image user = new Image(this.getClass().getResourceAsStream("/user.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/ans.png"));
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(null);
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private String handleUserInput() {
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().addAll(
                custom.DialogBox.getUserDialog( userText.toString(), user)
        );
        userInput.clear();
        return userText.toString();
    }


    /**
     * Shows the response from the Duke after handling the user input.
     */
    public String[] getResponse(String input) {
        try {
            Command c = Parser.parse(input, this.ui, this.storage, this.tasks);
            ArrayList<String> s = new ArrayList<>();
            s = c.execute();
            return s.toArray(new String[0]);
        } catch (Exception e) {
            String[] s = new String[1];

            s[0] = this.ui.showError("Something wrong! " + e.getMessage());
            return s;
        }
    }



    public void run() {
        this.ui.showWelcome(dialogContainer);
    }

    public static void main(String[] args) {
        String filename = "src/save.txt";
    }
}

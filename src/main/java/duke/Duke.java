package duke;

import duke.storage.Storage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;



/**
 * Duke is entry point of our program
 */
public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    public static final String LISTPATH = "./data/list.txt";

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;

    private UIResponse uiResponse;

    public Duke() {
        this.storage = new Storage();
        this.uiResponse = new UIResponse(this.storage);
    }


    /**
     * Start duke program
     */
    public void run() {
        Greeting.greet();
        Commands.run(this.storage);
    }


    public static void main(String[] arg) {
        new Duke().run();
    }



    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return this.uiResponse.getResponseString(input);
    }


}

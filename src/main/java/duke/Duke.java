package duke;

import duke.storage.Storage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    

    private final Storage storage;

    private final UIResponse uiResponse;

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return this.uiResponse.getResponseString(input);
    }


}

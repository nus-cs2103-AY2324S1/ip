package Duke;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Main class from which Duke program is run.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;


    public Duke(){
        ui=new Ui();
        storage=new Storage("file.txt");
        tasks= new TaskList(storage.load());
        parser=new Parser();
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert !input.isEmpty();
        parser.parse(input, tasks, storage, ui);
        if (parser.isExit()) {
            ui.showExit();
        }
        return ui.emptyBuffer();
    }

    public boolean hasEnded() {
        return parser.isExit();
    }

}

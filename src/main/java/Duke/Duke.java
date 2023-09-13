package Duke;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main class from which Duke program is run.
 */
public class Duke {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;

    /**
     * Main method for Duke.
     * @param
     * @throws DukeException
     */

    /*public static void main(String[] args) throws DukeException {
        new Duke("file.txt").run();
    }*/

    public Duke(){
        ui=new Ui();
        storage=new Storage("file.txt");
        tasks= new TaskList(storage.load());
        parser=new Parser();
    }

    /**
     * Public constructor for Duke
     * @param filename for the txt file to be referenced.
     */

    public Duke (String filename){
        ui=new Ui();
        storage=new Storage(filename);
        tasks= new TaskList(storage.load());
        parser=new Parser();
    }

    /**
     * Contains the logic essential to the running of Duke.
     */
    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String cmd = ui.readCommand();
            parser.parse(cmd, tasks, storage, ui);
            isExit = parser.isExit();
        }
        ui.showExit();
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

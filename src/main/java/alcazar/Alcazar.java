package alcazar;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * The class with the main method where all the functionality begins.
 */
public class Alcazar  {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, user),
                    DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String filePath = "./src/main/java/data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        Parser p = new Parser();
        String parseResult = "";
        try {
            parseResult = p.parse(input, tasks, ui, storage);
        } catch (InvalidTaskException e) {

            return e.getMessage();
        } catch (InvalidArgumentException e) {
            return e.getMessage();
        } catch (InvalidSerialException e) {
            ui.showLine();
            return
                e.getMessage();
        }
        return "" + parseResult;
    }

    /**
     * Method to run all the functionality to drive this project.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Parser p = new Parser();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                isExit = p.isExit(fullCommand);
                if (isExit) {
                    continue;
                }
                ui.showLine(); // show the divider line ("_______")
                p.parse(fullCommand, tasks, ui, storage);
            } catch (InvalidTaskException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } catch (InvalidArgumentException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } catch (InvalidSerialException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } finally {
                ui.showLine();
            }
        }
        ui.showExitMsg();
    }
}

package duke;

import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;



/**
 * Duke is a chatbot that helps the user to keep track of Tasks. Users
 * can add, delete, mark tasks as done.
 */
public class Duke extends Application {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a Duke Object. It will attempt to load saved data
     * and continue to read/write tasks to the saved data.
     * Ui, Storage and the TaskLists are initialized here.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            this.tasks = new TaskList(this.storage.readTasks());
        } catch (DukeException e) {
            this.ui.loadingErrorMessage();
            this.tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks);
    }

    /**
     * Starts the Duke interaction with the User, allowing users to continuously input
     * and modify the TaskList until they type bye.
     */
    public void run() {
        try {
            this.ui.introMessage();
            while (true) {
                String command = ui.getInput();
                if (command.equalsIgnoreCase("bye")) {
                    break;
                }
                this.parser.parse(command);
            }
            this.ui.closeScanner();
            this.storage.writeTasks(this.tasks.getTasks());
            this.ui.byeMessage();
        } catch (DukeException e) {
            System.out.println("OOPS!" + e.toString().split("DukeException:")[1]);
        }
    }
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later
    }

    /**
     * Acts as the main entry Point for the Duke Application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args){
        Duke duke = new Duke();
        duke.run();
    }
}

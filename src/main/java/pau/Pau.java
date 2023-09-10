package pau;

import pau.util.Parser;
import pau.util.Storage;
import pau.task.TaskList;
import pau.util.Ui;

import java.util.Scanner;

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
 * Represents the Pau chatbot.
 */
public class Pau extends Application{

    /**
     * The list of tasks.
     */
    private TaskList list;
    /**
     * The storage that handles loading and saving of tasks.
     */
    private Storage storage;
    /**
     * The ui that handles interaction with users.
     */
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a new Pau chatbot.
     *
     * @param filePath The path to the file containing tasks to be loaded.
     */
    public Pau(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public Pau(){
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        this.ui.introduction();
        Scanner scan = new Scanner(System.in);
        this.list = this.storage.loadTasks();
        list.checkList();
        String input = scan.nextLine();

        while (!(input.equals("bye"))) {
            Parser.parseCommand(input, list);
            this.storage.saveTasksToFile(list);
            input = scan.nextLine();
        }
        this.ui.exit();
    }

    public static void main(String[] args) {
        new Pau("./data/paulist.txt").run();
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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("PAU");
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
    }

}

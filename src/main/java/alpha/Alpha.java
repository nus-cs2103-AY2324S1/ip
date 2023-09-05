package alpha;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * The Chatbot Alpha is capable of tracking, marking, listing, and deleting events, to-dos, and deadlines.
 * It responds to commands like "event," "meeting," "deadline," "list," "mark," "unmark," and "delete."
 * To end the Chatbot, type "bye."
 *
 * @author Wong Joon Hung
 */
public class Alpha extends Application{

    private UI ui;
    private FileHandler fileHandler;
    private TaskList taskList;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for the Alpha class. It reads existing tasks from "data/alpha.txt" and adds them to the taskList.
     */
    public Alpha() {
        ui = new UI();
        fileHandler = new FileHandler();
        taskList = fileHandler.readFromFile();
        parser = new Parser(fileHandler, taskList, ui);
        fileHandler.checkAndCreate();
    }

    /**
     * Starts the Alpha ChatBot. Ends when "bye" is input.
     */
    /*
    public void run() {
        ui.introduce();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.read();
            Command command = parser.parse(input);
            command.execute();
            isExit = command.isExit();
        }
        ui.goodbye();
    }
     */

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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

        stage.setTitle("Alpha");
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



        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    public String getResponse(String input) {
        Command command = parser.parse(input);
        if (command.isExit()) {
            Platform.exit();
            return ui.goodbye();
        } else {
            return command.execute();
        }
    }

    /**
     * This is the main method that calls the run() function and starts Chatbot Alpha.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {

    }
}

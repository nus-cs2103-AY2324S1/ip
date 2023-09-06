package jeoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import jeoe.Commands.Command;
import jeoe.Commands.CommandParser;
import jeoe.Exceptions.InitializationException;
import jeoe.Exceptions.InvalidCommandException;
import jeoe.Exceptions.NoCommandException;
import jeoe.Exceptions.RunException;
import jeoe.Others.StorageManager;
import jeoe.Others.Ui;
import jeoe.Tasks.TaskManager;

/**
 * This class encapsulates my version of Duke, called Jeoe.
 * Jeoe is a chatbot that helps the user keep track of their tasks.
 * Java coding standard was adhered to in this program.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class Jeoe {

    /** Manager to contain task list and handle operations on tasks. */
    private static TaskManager taskManager;

    /** Manager to format & print statements to the ui. */
    private static Ui ui;

    /** Manager to load & save task from local storage. */
    private static StorageManager storageManager;

    /** Contains file path to local storage containing saved tasks. */
    private static String filePath = System.getProperty("user.dir") + "/storage/taskListData.txt"; // dir is till ip

    /**
     * Constructor for a Jeoe object.
     *
     * The Jeoe object is to be passed to the Main class which runs the entire application.
     * The Jeoe object process the input using all its other components and passes back the
     * result to the Main class for the JavaFX components to render out the output on a GUI.
     */
    public Jeoe() {
        try {
            Jeoe.initialize();
        } catch (InitializationException e) {
            // exception to do with initialization => scanner fails (cannot be file issue)
            System.out.println(e.getMessage()); // can change to ui printing this out in the future
        }
    }

    /**
     * Initialises the Jeoe program.
     *
     * Instantiates a TaskManager object, Ui object, and a StorageManager Object.
     */
    private static void initialize() throws InitializationException {
        try {
            ui = new Ui();
            storageManager = new StorageManager(filePath); // loading tasks & saving tasks
            // contains list of task, and has operations to add/delete tasks
            taskManager = new TaskManager(storageManager.load()); // load passes the string for TM to add task to arr

            ui.showOpenString();
        } catch (Exception e) {
            System.out.println("Jeoe initialization failed");
        }
    }

    // ----------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------
    // GUI versions
    // ----------------------------------------------------------------------------------------------------------------
    // GUI version 1
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaJeoe.png"));
//
//    /**
//     * Method to initialize then render out GUI components of Jeoe program.
//     * Initializes the Jeoe program, then renden our al the GUI components
//     *
//     * @param stage The stage that the scene will be placed on.
//     */
//    @Override
//    public void start(Stage stage) {
//        try {
//            Jeoe.initialize();
//        } catch (InitializationException e) {
//            // exception to do with initialization => scanner fails (cannot be file issue)
//            System.out.println(e.getMessage()); // can change to ui printing this out in the future
//            return;
//        }
//
//        //Step 1. Setting up required components
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("jeoe");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        userInput.setPrefWidth(325.0);
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//    }
//
//    /**
//     * Handles the user input through a GUI.
//     *
//     * Creates two dialog boxes, one echoing user input and the other containing Jeoe's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label("You :\n" + userInput.getText());
//        String jeoeReply = "";
//        Label jeoeText = new Label(jeoeReply);
//
//        try {
//            String fullCommand = userInput.getText();
//            Command c = CommandParser.parse(fullCommand);
//            jeoeReply = c.executeAndReply(taskManager, ui, storageManager); // already has ui.getReply executed
//            jeoeText = new Label(jeoeReply);
//        } catch (InvalidCommandException e) {
//            // print exception, they will handle their formatting themselves
//            jeoeText = new Label(ui.getReply(e.getMessage()));
//        } catch (NoCommandException e) { // can think of if tried 3 empty commands, terminate program
//            jeoeText = new Label(ui.getReply(e.getMessage()));
//        } catch (RunException e) {
//            jeoeText = new Label(ui.getReply(e.getMessage()));
//        }
//
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(jeoeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }

    // ----------------------------------------------------------------------------------------------------------------
    // GUI version 2

    /**
     * Gets the response from the Jeoe program.
     *
     * Gets the response from the Jeoe program to be displayed on the GUI.
     * @param input The input from the user using the GUI.
     * @return Response string to be displayed on the GUI.
     */
    public String getResponse(String input) {
        String jeoeReply;

        try {
            Command c = CommandParser.parse(input);
            jeoeReply = c.executeAndReply(taskManager, ui, storageManager); // already has ui.getReply executed
        } catch (InvalidCommandException e) {
            // print exception, they will handle their formatting themselves
            jeoeReply = ui.getReply(e.getMessage());
        } catch (NoCommandException e) { // can think of if tried 3 empty commands, terminate program
            jeoeReply = ui.getReply(e.getMessage());
        } catch (RunException e) {
            jeoeReply = ui.getReply(e.getMessage());
        }

        return jeoeReply;
    }

    // ----------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------
    // CLI version
    /**
     * Runs the Jeoe program in the CLI.
     *
     * Sets the loop for the 3 components and executes the commands till a bye command is reached.
     */
    public static void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.nextLine();
                Command c = CommandParser.parse(fullCommand);
                c.execute(taskManager, ui, storageManager);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                // print exception, they will handle their formatting themselves
                System.out.println(e.getMessage());
            } catch (NoCommandException e) { // can think of if tried 3 empty commands, terminate program
                System.out.println(e.getMessage());
            } catch (RunException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main method of Jeoe.
     * The block of code is to initialize & run the Jeoe program (in the CLI).
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            Jeoe.initialize();
            Jeoe.run();
        } catch (InitializationException e) {
            // exception to do with initialization => scanner fails (cannot be file issue)
            System.out.println(e.getMessage()); // can change to ui printing this out in the future
        }
    }
}

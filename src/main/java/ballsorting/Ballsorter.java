package ballsorting;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.application.Platform;
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

/**
 * Main class of the chatbot.
 */
public class Ballsorter extends Application {
    //CHECKSTYLE.OFF: VisibilityModifier
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, HH:mm");
    //CHECKSTYLE.ON: VisibilityModifier
    private TaskList taskList = new TaskList();
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private File tmpDir;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a new instance of the chatbot.
     */
    public Ballsorter() {

        try {
            final Path p = Paths.get("data/Ballsorter.txt");
            final String directory = p.getParent().toString();
            final String filename = p.getFileName().toString();

            File file = new File("data/Ballsorter.txt");
            File dir = new File("data");

            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            tmpDir = file;
            storage = new Storage(tmpDir);
            storage.loadFile(taskList);

        } catch (IOException e) {
            System.out.println(e);
        }

//        //check for storage
//        storage = new Storage(tmpDir);
//        try {
//            if (tmpDir.createNewFile()) {
//                taskList = new TaskList();
//            } else {
//                storage.loadFile(taskList);
//            }
//        } catch (IOException e) {
//            System.out.println(e);
//        }
    }

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        parser = new Parser();

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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Label startUp = new Label("Welcome to Ballsorter! Enter help for the list of possible commands :)");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(startUp, new ImageView(duke))
        );

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Generates response according to the user's command.
     */
    protected String getResponse(String input) {
        return parser.parseUserInput(input, taskList);
    }


    /**
     * Displays the bot and user dialog boxes.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        if (userText.getText().equals("bye")) {
            storage.storeList(taskList);
            Label quitText = new Label("Bye. Hope to see you again soon!");
            dialogContainer.getChildren().
                    add(DialogBox.getDukeDialog(quitText, new ImageView(duke)));
            Platform.exit();
        }
        Label dukeText = new Label(parser.parseUserInput(userText.getText(), taskList));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

}

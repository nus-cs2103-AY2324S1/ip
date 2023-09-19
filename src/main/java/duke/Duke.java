package duke;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * How do I describe this class? Perhaps the bridge between JavaFX and the rest of the code
 */
public class Duke extends Application implements Serializable {
    protected static final Path DUKE_FILE_PATH = Paths.get("./data/duke.txt");
    /*
         Text file that contains the *current state* of the TaskList in a human-readable format
         This file is read in the printFileContents method in Ui.java
     */
    protected static final Path CURRENT_TASKLIST_FILE_PATH = Paths.get("./data/temp.txt");
    /*
         Text file that contains the *current state* of the TaskList in binary format
         This file is used to store TaskList contents that will persist with consecutive runs of the application
     */
    protected static final Path OLD_TASKLIST_FILE_PATH = Paths.get("./data/undo.txt");
    /*
         Text file that contains the *old state* of the OLD TaskList in binary format
         Contents of this file is read when 'undo' command is called, so as to to obtain the 'old' TaskList
    */
    private static Storage storage;
    private static TaskList tasks;
    /**
     * Creates the relevant database text files
     */
    public Duke() {
        storage = new Storage();
        try {
            createTxtFile(DUKE_FILE_PATH);
            createTxtFile(CURRENT_TASKLIST_FILE_PATH);
            createTxtFile(OLD_TASKLIST_FILE_PATH);
            tasks = new TaskList(storage.load());
        } catch (IOException | ClassNotFoundException e) {
            tasks = new TaskList();
        }
    }
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

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
    public TaskList getTasks() {
        return tasks;
    }
    public Storage getStorage() {
        return storage;
    }
    private static void createTxtFile(Path filePath) {
        try {
            File txtFile = new File(filePath.toString());
            File parentDirectory = txtFile.getParentFile();
            if (!parentDirectory.exists()) {
                parentDirectory.mkdirs(); // Create parent directories if they don't exist
            }
            txtFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

package seedu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * The main class
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private Image user = new Image(this.getClass().getResourceAsStream("/user.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/ans.png"));
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("");
        try {
            this.tasks = storage.load();
        } catch (Exception e) {
            tasks = new TaskList(null);
        }
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(null);
        }
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private String handleUserInput() {
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().addAll(
                custom.DialogBox.getUserDialog( userText.toString(), user)
        );
        userInput.clear();
        return userText.toString();
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String[] getResponse(String input) {
        try {
            Command c = Parser.parse(input, this.ui, this.storage, this.tasks);
            ArrayList<String> s = new ArrayList<>();
            s = c.execute();
            return s.toArray(new String[0]);
        } catch (Exception e) {
            String[] s = new String[1];
            s[0] = this.ui.showError("Something wrong!" + e.getMessage());
            return s;
        }
    }

    @Override
    public void start(Stage stage) {

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

        // more code to be added here later
        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke Chatbot Pro");
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
        // more code to be added here later

        String filename = "../src/save.txt";
        new Duke(filename).run();
        boolean isExit = false;

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {

        });

        userInput.setOnAction((event) -> {

        });
    }




    public void run() {
        this.ui.showWelcome(dialogContainer);
    }

    public static void main(String[] args) {
        String filename = "src/save.txt";
    }
}

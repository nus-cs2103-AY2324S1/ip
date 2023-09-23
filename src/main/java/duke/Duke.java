package duke;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Main entry point of the bot.
 */
public class Duke extends Application {
    private final Ui ui = new Ui();
    private final TaskList tasks = new TaskList();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Start the program with GUI.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        // Read tasks from file and save it to tasks.
        Storage.readTask(tasks, ui);

        // Start GUI
        startGui(stage);
    }

    /**
     * Start graphical user interface.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     */
    public void startGui(Stage stage) {
        guiSetRequirement(stage);
        guiFormatWindow(stage);
        guiAddFunctionality(stage);
    }

    private void guiSetRequirement(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setSpacing(10); // Add padding
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
    }

    private void guiFormatWindow(Stage stage) {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Set padding
        scrollPane.setPadding(new Insets(10));

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("KEN");
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        mainLayout.setStyle("-fx-background-color: #121212;");
        scrollPane.setStyle("-fx-background: #121212; -fx-border-color: #444;");
        userInput.setStyle("-fx-background-color: #222; -fx-text-fill: #FFF;");
        sendButton.setStyle("-fx-background-color: #333; -fx-text-fill: #FFF;");
        dialogContainer.setStyle("-fx-background-color: #121212;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Remove Scroll bar.
        AnchorPane.setRightAnchor(scrollPane, 1.0);
        AnchorPane.setLeftAnchor(scrollPane, 1.0);
        userInput.setStyle("-fx-background-color: #222; -fx-text-fill: #FFF; "
                + "-fx-border-color: #444; -fx-border-width: 1; -fx-border-radius: 5;");
        sendButton.setStyle("-fx-background-color: #333; -fx-text-fill: #FFF; "
                + "-fx-border-color: #444; -fx-border-width: 1; -fx-border-radius: 5;");
    }

    private void guiAddFunctionality(Stage stage) {
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Show welcome message
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ui.showWelcome()), new ImageView(duke))
        );
    }


    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        ui.readInput(userInput.getText()); // Save to history
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            boolean isExit = command.execute(this.tasks, ui);
            Storage.writeTask(tasks, ui);
            if (isExit) {
                Platform.exit();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return ui.getLastMsg();
    }

    private static class DialogBox extends HBox {

        private Label text;
        private ImageView displayPicture;

        /**
         * Constructor.
         *
         * @param l  Label.
         * @param iv ImageView.
         */
        public DialogBox(Label l, ImageView iv) {
            text = l;
            displayPicture = iv;

            text.setWrapText(true);
            displayPicture.setFitWidth(100.0);
            displayPicture.setFitHeight(100.0);

            // Add padding to the HBox
            this.setSpacing(10.0);

            // Clip the ImageView into a circle
            Circle clip = new Circle(50.0, 50.0, 50.0);
            displayPicture.setClip(clip);

            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        }

        /**
         * getUserDialog.
         *
         * @param l
         * @param iv ImageView.
         * @return DialogBox.
         */
        public static DialogBox getUserDialog(Label l, ImageView iv) {
            return new DialogBox(l, iv);
        }

        /**
         * GetDukeDialog.
         *
         * @param l  Label.
         * @param iv ImageView.
         * @return DialogBox.
         */
        public static DialogBox getDukeDialog(Label l, ImageView iv) {
            var db = new DialogBox(l, iv);
            db.flip();
            return db;
        }

        /**
         * Flips the dialog box such that the ImageView is on the left and text on the right.
         */
        private void flip() {
            this.setAlignment(Pos.TOP_LEFT);
            ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
            FXCollections.reverse(tmp);
            this.getChildren().setAll(tmp);
        }
    }
}

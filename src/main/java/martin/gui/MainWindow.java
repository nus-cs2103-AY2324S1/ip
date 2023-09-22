package martin.gui;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import martin.Martin;

public class MainWindow extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Martin martin = new Martin("data/martin.txt");

    Image martinImage = new Image(getClass().getResourceAsStream("/images/martin.png"), 50, 50, true, true);
    Image hikaruImage = new Image(getClass().getResourceAsStream("/images/hikaru.png"), 50, 50, true, true);

    /*
     * Launch the GUI.
     */
    public static void main(String[] args) {
        launch(args);
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

        assert scrollPane != null : "ScrollPane should be initialized.";
        assert dialogContainer != null : "DialogContainer should be initialized.";
        assert userInput != null : "UserInput TextField should be initialized.";
        assert sendButton != null : "SendButton should be initialized.";

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, 40.0); // reserve space for userInput and sendButton
        AnchorPane.setLeftAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setRightAnchor(userInput, 60.0); // reserve space for the button

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0); 

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setPadding(new Insets(10));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                showErrorDialog("An error occurred while processing your input. Please try again.");
            }
        });
        
        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                showErrorDialog("An error occurred while processing your input. Please try again.");
            }
        });

        // Display a welcome message when the app starts
        dialogContainer.getChildren().addAll(
                DialogBox.getMartinDialog("Hello! I'm Martin. How can I assist you today?", martinImage)
        );
    }

    /**
     * Processes user input and displays both user's text and Martin's reply.
     * 
     * @throws IOException If there is an error during input/output operation.
     */
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        assert input != null : "User input should not be null.";

        if (input.trim().isEmpty()) {
            showErrorDialog("Input cannot be empty.");
            return;
        }
        
        String response = getResponse(input);
        assert response != null : "Response from Martin should not be null.";
        
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, hikaruImage),
                DialogBox.getMartinDialog(response, martinImage)
        );

        // Introduce a small delay using PauseTransition
        PauseTransition pause = new PauseTransition(Duration.millis(100)); // 100ms delay
        pause.setOnFinished(event -> scrollPane.setVvalue(1.0));
        pause.play();
        userInput.clear();
    }

    /**
     * Executes the getResponse method in Martin using the provided input.
     * 
     * @param input The input string to get a response for.
     * @return The response from Martin for the given input.
     * @throws IOException If there is an error during input/output operation.
     */
    private String getResponse(String input) throws IOException {
        return martin.getResponse(input);
    }

    /**
    * Displays an error dialog with a specified error message.
    *
    * @param errorMessage The specific error message to be shown in the dialog.
    */
    private void showErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Something went wrong!");
        alert.setContentText(errorMessage);

        // Modify the padding of the DialogPane's content
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setContentText(errorMessage);  // Set content text again to refresh the layout

        // Retrieve the content and modify its appearance
        if (dialogPane.getContent() instanceof Label) {
            Label contentLabel = (Label) dialogPane.getContent();

            // Adjust padding
            contentLabel.setPadding(new Insets(15));

            // Change background color to light red
            contentLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        alert.showAndWait();
    }
}

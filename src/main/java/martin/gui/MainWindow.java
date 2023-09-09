package martin.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

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
     * Input processing of user, and displaying of the user's text and Martin's reply.
     * @throws IOException
     */
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = getResponse(input);
        
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, hikaruImage),
                DialogBox.getMartinDialog(response, martinImage)
        );

        scrollPane.setVvalue(1.0);
        userInput.clear();
    }

    /**
     * Execute getResponse in Martin based on the given input.
     * 
     * @throws IOException
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

        alert.showAndWait();
    }
}

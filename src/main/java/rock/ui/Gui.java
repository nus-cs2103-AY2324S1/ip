package rock.ui;

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
import rock.client.Response;
import rock.client.Rock;

/**
 * Represents the graphical user interface
 * for the bot to interact with user
 *
 * @author Alvis Ng (supermii2)
 */
public class Gui extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Rock.png"));
    private Rock rock;
    @Override
    public void start(Stage stage) {
        this.rock = new Rock();

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

        stage.setTitle("Rock");
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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Response response = getResponse(userInput.getText());
        Label responseText = new Label(response.getMessage());
        if (response.isError()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    WarningBox.getWarning(responseText)
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(responseText, new ImageView(duke))
            );
        }
        userInput.clear();
        if (rock.isTerminated()) {
            onTerminate();
        }
    }
    private void onTerminate() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }
    private Response getResponse(String input) {
        return rock.getResponse(input);
    }

    /**
     * Sends a warning to the GUI
     * when there is a problem with saving
     * @param response Message containing error
     */
    public void sendWarning(Response response) {
        assert response.isError() : "Warnings cannot be normal messages";
        Label responseText = new Label(response.getMessage());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(responseText, new ImageView(duke))
        );
    }
}

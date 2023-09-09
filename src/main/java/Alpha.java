import com.alpha.ui.DialogBox;

import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

/**
 * The Alpha class.
 */
public class Alpha extends Application {

    private final Image userPicture = new Image(this.getClass().getResourceAsStream("redbloon.jpg"));
    private final Image alphaPicture = new Image(this.getClass().getResourceAsStream("bluebloon.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Processor processor;

    private Stage stage;

    @Override
    public void start(Stage stage) {
        initializeObjects(stage);
        configureStage();
        configureScrollPane();
        configureDialogContainer();
        configureAnchorPane();
        configureSendButton();
        configureUserInput();
        welcome();
    }

    private void initializeObjects(Stage stage) {
        this.stage = stage;
        processor = new Processor("./data/save.txt");
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        sendButton = new Button("Send");
        userInput = new TextField();
    }

    private void configureStage() {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.setTitle("Alpha");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.show();
    }

    private void configureScrollPane() {
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void configureDialogContainer() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void configureAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void configureSendButton() {

        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    }

    private void configureUserInput() {

        userInput.setPrefWidth(325.0);
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        String alphaResponse = processor.processInput(userInput.getText());
        if (alphaResponse.equals("bye")) {
            goodbye();
            saveTaskList();
            exit();
        } else {
            updateUi(userInput.getText(), alphaResponse);
        }
    }

    private void welcome() {
        Label welcomeText = new Label("Hello! I'm Alpha.\n" + "What can I do for you?\n");
        dialogContainer.getChildren().add(DialogBox.getAlphaDialog(welcomeText, new ImageView(alphaPicture)));
    }

    private void goodbye() {
        Label goodbyeText = new Label("Bye. Hope to see you again soon!\n");
        dialogContainer.getChildren().add(DialogBox.getAlphaDialog(goodbyeText, new ImageView(alphaPicture)));
        userInput.clear();
    }

    private void saveTaskList() {
        processor.saveTaskList();
    }

    private void exit() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            Platform.exit();
        });
        pause.play();
    }

    private void updateUi(String userText, String alphaText) {
        Label user = new Label(userText);
        Label alpha = new Label(alphaText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(user, new ImageView(userPicture)),
                DialogBox.getAlphaDialog(alpha, new ImageView(alphaPicture))
        );
        userInput.clear();
    }
}


package boti.gui;

import boti.Boti;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class for the GUI
 */
public class Main extends Application {

    private static final String INTRO = "    Hello! I'm Boti\n"
                                        + "    What can I do for you?\n";
    private final Image chatbot = new Image(this.getClass().getResourceAsStream("/images/Chatbot.png"));
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Boti duke = new Boti("./data", "storage.txt");

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private AnchorPane mainLayout;
    private Scene scene;
    private UserInputPane userInputPane;

    @Override
    public void start(Stage stage) {
        initialize();
        addContent();
        format(stage);
    }

    private void initialize() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInputPane = new UserInputPane(this);
        mainLayout = new AnchorPane();
        scene = new Scene(mainLayout);
    }

    private void addContent() {
        scrollPane.setContent(dialogContainer);
        mainLayout.getChildren().addAll(scrollPane, userInputPane);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(new Label(INTRO), new ImageView(chatbot)));
    }

    private void format(Stage stage) {
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Boti");
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

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(userInputPane, 1.0);
        AnchorPane.setLeftAnchor(userInputPane, 1.0);
    }

    /**
     * Adds both user's and bot's dialog box based on the text sent by the user
     *
     * @param userText the text sent by the user
     */
    public void addDialog(String userText) {
        Label userLabel = new Label(userText);
        Label responseLabel = new Label(duke.getResponse(userText));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userLabel, new ImageView(user)),
                DialogBox.getDukeDialog(responseLabel, new ImageView(chatbot))
        );
    }
}

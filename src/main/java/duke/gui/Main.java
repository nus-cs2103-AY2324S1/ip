package duke.gui;

import duke.Duke;
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

    private Image chatbot = new Image(this.getClass().getResourceAsStream("/images/Chatbot.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Duke duke = new Duke("./data", "storage.txt");

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private Scene scene;
    private UserInputPane userInputPane;

    @Override
    public void start(Stage stage) {
        //Step 1. Formatting the window to look as expected.
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInputPane = new UserInputPane(this);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInputPane);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Not a Chatbot");
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

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(new Label("    Hello! I'm Not A ChatBot\n"
                + "    What can I do for you?\n"), new ImageView(chatbot)));
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

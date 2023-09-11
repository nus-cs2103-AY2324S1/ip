package duke;

import dukeexception.CorruptedFileException;
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

/**
 * Represents the user interface of an application.
 */
public class UserInterface {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private final Duke duke;
    private Image userImg;
    private Image dukeImg;
    public UserInterface(Duke duke) {
        this.duke = duke;
    }

    /**
     * Initializes the images into the interface.
     * @param userImagePath relative path of the user image.
     * @param dukeImagePath relative path of the duke image.
     * @throws CorruptedFileException if the files do not exist.
     */
    public void init(String userImagePath, String dukeImagePath) throws CorruptedFileException {
        try {
            this.userImg = new Image(this.getClass().getResourceAsStream(userImagePath));
            this.dukeImg = new Image(this.getClass().getResourceAsStream(dukeImagePath));
        } catch (NullPointerException e) {
            throw new CorruptedFileException();
        }
    }

    /**
     * Creates the scene that we want to use to represent the chatbot.
     * @return the chatbot's scene.
     */
    public Scene sceneMaker() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> input());

        userInput.setOnAction((event) -> input());
        return scene;
    }

    /**
     * Sends the input to duke, for it to handle and reply (or not.)
     */
    public void input() {
        String input = userInput.getText();
        Label inputLabel = new Label(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(inputLabel, new ImageView(userImg)));
        duke.handle(input);
        userInput.clear();
    }

    /**
     * Takes a string and prints it out to the system, while formatting it with line dividers.
     * @param output the string to be printed.
     */
    public void output(String output) {
        Label dukeLabel = new Label(output);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeLabel, new ImageView(dukeImg)));
    }

}

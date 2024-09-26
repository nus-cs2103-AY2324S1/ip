package components;

import commands.Command;
import tasks.TaskList;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;

public class DialogManager {
    private final ScrollPane scrollPane;
    private final VBox dialogContainer;
    private final TextField userInput;
    private final Button sendButton;
    private final Ui ui;
    private final Parser parser;
    private final TaskList list;
    private final Storage storage;
    private final Image user;
    private final Image duke;

    public DialogManager(ScrollPane scrollPane, TextField userInput, Button sendButton,
                         Ui ui, Parser parser, TaskList list, Storage storage) {
        this.scrollPane = scrollPane;
        this.dialogContainer = new VBox();
        dialogContainer.setMaxWidth(380);  // Or whatever value you choose
        this.userInput = userInput;
        this.sendButton = sendButton;
        this.ui = ui;
        this.parser = parser;
        this.list = list;
        this.storage = storage;
        this.user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        this.duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    public void initializeDialog() {
        scrollPane.setContent(dialogContainer);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Disable horizontal scrolling

        Label welcomeLabel = new Label(ui.showWelcome("CHAD CCP"));
        welcomeLabel.setWrapText(true);
        welcomeLabel.setMaxWidth(380);  // Set a max width for the label to wrap

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeLabel, roundImage(duke)));

        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        userText.setWrapText(true);
        userText.setMaxWidth(380);  // Set a max width for the label to wrap

        Label dukeText = new Label(getResponse(userInput.getText()));
        dukeText.setWrapText(true);
        dukeText.setMaxWidth(380);  // Set a max width for the label to wrap

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, roundImage(user)),
                DialogBox.getDukeDialog(dukeText, roundImage(duke))
        );
        userInput.clear();
    }


    private ImageView roundImage(Image object) {
        ImageView imageView = new ImageView(object);
        imageView.setFitWidth(90);
        imageView.setFitHeight(90);
        Circle clip = new Circle(
                imageView.getFitWidth() / 2,
                imageView.getFitHeight() / 2,
                imageView.getFitWidth() / 2
        );
        imageView.setClip(clip);
        return imageView;
    }

    private String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            String responseString = command.execute(list, ui, storage);
            if (command.isExit()) {
                System.exit(0);
            }
            return responseString;
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}


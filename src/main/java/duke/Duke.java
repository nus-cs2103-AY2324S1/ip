package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.InputStream;

/**
 * Duke is a task management chatbot GUI that allows users to manage their tasks
 * including todos, deadlines, and events.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private String outputText;

    @Override
    public void start(Stage stage) {
        // Define the file path where duke.txt should be stored
        String filePath = "data/duke.txt";

        // Check if the directory exists, and if not, create it
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }

        // Check if the file exists, and if not, create it
        if (!Files.exists(Paths.get(filePath))) {
            try {
                Files.createFile(Paths.get(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Storage storage = new Storage(filePath);

        TaskList taskList = new TaskList();

        // Assertion: The storage and taskList objects should not be null.
        assert storage != null : "Storage is null.";
        assert taskList != null : "TaskList is null.";

        Ui.getGreeting();

        ArrayList<Task> loadedTasks = storage.loadTasks();
        taskList.setTasks(loadedTasks);

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        final Image logoImage = new Image(getClass().getResourceAsStream("/images/picture.png"));

        ImageView botGreetingLogoImageView = new ImageView(logoImage);
        botGreetingLogoImageView.setFitWidth(40);
        botGreetingLogoImageView.setFitHeight(40);

        Label greetingLabel = new Label(Ui.getGreeting());
        HBox botGreetingMessage = new HBox(botGreetingLogoImageView, greetingLabel);
        botGreetingMessage.setAlignment(Pos.CENTER_LEFT);

        dialogContainer.getChildren().addAll(botGreetingMessage);

        sendButton.setOnAction(event -> {

            String inputText = userInput.getText();
            if (inputText.equalsIgnoreCase("bye")) {
                storage.saveTasks(taskList.getTasks());
                outputText = Ui.getByeMessage();
                Platform.exit();
            } else if (inputText.equalsIgnoreCase("list")) {
                outputText = Ui.getList(taskList);
            } else if (inputText.startsWith("find")) {
                String keyword = inputText.substring("find".length()).trim();
                outputText = taskList.findTasksByKeyword(keyword);
            } else {
                outputText = Parser.parseAndAddTask(inputText, taskList);
            }
            if (!inputText.isEmpty()) {

                ImageView botLogoImageView = new ImageView(logoImage);
                botLogoImageView.setFitWidth(40);
                botLogoImageView.setFitHeight(40);

                ImageView userLogoImageView = new ImageView(logoImage);
                userLogoImageView.setFitWidth(40);
                userLogoImageView.setFitHeight(40);

                Label userLabel = new Label(inputText);
                userLabel.setStyle("-fx-background-color: lightblue; -fx-border-radius: 5; -fx-padding: 5px;");

                Label botLabel = new Label(outputText);
                botLabel.setStyle("-fx-background-color: lightgray; -fx-border-radius: 5; -fx-padding: 5px;");

                HBox userMessage = new HBox(userLogoImageView, userLabel);
                HBox botMessage = new HBox(botLabel, botLogoImageView);

                // Assertion: The userMessage and botMessage should not be null.
                assert userMessage != null : "User message is null.";
                assert botMessage != null : "Bot message is null.";

                userMessage.setAlignment(Pos.CENTER_RIGHT);
                botMessage.setAlignment(Pos.CENTER_LEFT);

                dialogContainer.getChildren().addAll(userMessage, botMessage);

                userInput.clear();
            }
        });

        userInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendButton.fire();
            }
        });

        stage.setScene(scene);
        stage.show();

        stage.setTitle("RemindMe");
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}

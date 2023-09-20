package brotherman;

import brotherman.commands.Command;
import brotherman.exceptions.BrothermanException;
import brotherman.parser.Parser;
import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * Represents a Brotherman chatbot
 */
public class Brotherman extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/riversprite.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/ufosprite.jpg"));

    public Brotherman(String filepath) {
        initialize(filepath);
    }

    public Brotherman() {
        initialize("./data/brotherman.txt");
    }

    private void initialize(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        taskList = storage.readFromFile();
    }

    @Override
    public void start(Stage stage) {
        initializeStage(stage);
        initializeLayout();
        initializeEventHandlers();
    }

    private void initializeStage(Stage stage) {
        stage.setTitle("Brotherman");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.setOnCloseRequest((event) -> closeProgram());

        scene = new Scene(createMainLayout(), 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    private BorderPane createMainLayout() {
        scrollPane = createScrollPane();
        userInput = createTextField();
        sendButton = createSendButton();

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(scrollPane);
        mainLayout.setBottom(createInputLayout());

        return mainLayout;
    }

    private ScrollPane createScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        return scrollPane;
    }

    private ImageView createRoundedImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);

        imageView.setClip(clip);

        return imageView;
    }

    private HBox createInputLayout() {
        HBox inputLayout = new HBox();
        inputLayout.getChildren().addAll(userInput, sendButton);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        return inputLayout;
    }

    private TextField createTextField() {
        TextField textField = new TextField();
        textField.setOnAction((event) -> handleUserInput());
        return textField;
    }

    private Button createSendButton() {
        Button button = new Button("Send");
        button.setOnMouseClicked((event) -> handleUserInput());
        return button;
    }

    private void initializeLayout() {
    }

    private void initializeEventHandlers() {
    }

    private void handleUserInput() {
        String inputText = userInput.getText();

        StackPane userMessagePane = createUserMessagePane(inputText);
        StackPane dukeMessagePane = createDukeMessagePane(getResponse(inputText));

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userMessagePane, createRoundedImageView(user)),
                DialogBox.getDukeDialog(dukeMessagePane, createRoundedImageView(duke))
        );

        userInput.clear();
    }

    private StackPane createUserMessagePane(String text) {
        Label userText = createDialogLabel(text);
        userText.setStyle("-fx-background-color: lightblue; -fx-padding: 10px;");
        StackPane userMessagePane = new StackPane(userText);
        userMessagePane.setStyle("-fx-background-color: transparent;");
        return userMessagePane;
    }

    private StackPane createDukeMessagePane(String text) {
        Label dukeText = createDialogLabel(text);
        dukeText.setStyle("-fx-background-color: lightgreen; -fx-padding: 10px;");
        StackPane dukeMessagePane = new StackPane(dukeText);
        dukeMessagePane.setStyle("-fx-background-color: transparent;");
        return dukeMessagePane;
    }

    private Label createDialogLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        return label;
    }

    private String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (BrothermanException e) {
            return ui.showError(e.getMessage());
        }
    }

    private void closeProgram() {
        storage.saveToFile(taskList.list());
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

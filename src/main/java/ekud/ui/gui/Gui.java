package ekud.ui.gui;

import java.util.List;

import ekud.command.ByeCommand;
import ekud.command.Command;
import ekud.command.Parser;
import ekud.error.EkudException;
import ekud.state.Task;
import ekud.state.TaskList;
import ekud.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Ui {
    private static Image BOT_IMAGE = new Image(Gui.class.getResourceAsStream("/images/bot.png"));
    private static Image USER_IMAGE = new Image(Gui.class.getResourceAsStream("/images/human.png"));

    private Stage stage;
    private Scene scene;

    private VBox mainContainer;

    private ScrollPane historyScrollContainer;
    private VBox historyContainer;

    private HBox inputContainer;
    private TextField inputTextField;
    private Button sendButton;

    public Gui(Stage stage) {
        this.stage = stage;
    }

    public void run() {
        historyContainer = new VBox();
        historyContainer.setFillWidth(true);

        historyScrollContainer = new ScrollPane();
        historyScrollContainer.setContent(historyContainer);
        historyScrollContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        historyScrollContainer.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        historyScrollContainer.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        historyScrollContainer.setFitToWidth(true);
        historyScrollContainer.vvalueProperty().bind(historyContainer.heightProperty());
        VBox.setVgrow(historyScrollContainer, Priority.ALWAYS);

        inputTextField = new TextField();
        inputTextField.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                onSendMessage();
            }
        });
        HBox.setHgrow(inputTextField, Priority.ALWAYS);

        sendButton = new Button("Send");
        sendButton.setOnMouseClicked((event) -> {
            onSendMessage();
        });

        inputContainer = new HBox(inputTextField, sendButton);

        mainContainer = new VBox(historyScrollContainer, inputContainer);
        mainContainer.setFillWidth(true);

        scene = new Scene(mainContainer);

        stage.setScene(scene);
        stage.setTitle("Ekud");
        stage.setResizable(false);
        stage.setMinWidth(400);
        stage.setMinHeight(600);
        stage.show();
    }

    private void onSendMessage() {
        try {
            String line = inputTextField.getText().trim();
            if (line.isEmpty()) {
                return;
            }
            addUserMessage(line);
            Command command = Parser.parseCommand(line);
            if (command instanceof ByeCommand) {
                System.exit(0);
            }
            handle(command);
        } catch (EkudException error) {
            showError(error);
        }
        inputTextField.setText("");
    }

    @Override
    public void showTaskList(TaskList taskList) {
        if (!taskList.hasTasks()) {
            addBotMessage("No tasks yet. Add one!");
            return;
        }

        addBotMessage("Here are the tasks in your list:");
        List<Task> tasks = taskList.asList();
        for (int taskId = 1; taskId <= tasks.size(); taskId++) {
            String message = "";
            // Add padding to align single-digit numbers if we'll render two-digit numbers
            // later on.
            if (tasks.size() > 9 && taskId < 10) {
                message += " ";
            }
            message += taskId;
            Task task = taskList.getTask(taskId);
            message += ". " + task.toString();
            addBotMessage(message);
        }
    }

    @Override
    public void showTaskCount(TaskList taskList) {
        addBotMessage("Now you have " + taskList.asList().size() + " tasks in the list.");
    }

    @Override
    public void showTaskAdded(Task task) {
        addBotMessage("Got it. I've added this task:");
        addBotMessage("   " + task);
    }

    @Override
    public void showTaskMarked(Task task) {
        addBotMessage("Nice! I've marked this task as done:");
        addBotMessage("   " + task);
    }

    @Override
    public void showTaskUnmarked(Task task) {
        addBotMessage("OK, I've marked this task as not done yet:");
        addBotMessage("   " + task);
    }

    @Override
    public void showTaskRemoved(Task task) {
        addBotMessage("Noted. I've removed this task:");
        addBotMessage("   " + task);
    }

    @Override
    public void showFoundTasks(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            addBotMessage("No tasks found.");
            return;
        }

        addBotMessage("Here are the matching tasks in your list:");
        for (int taskId = 1; taskId <= foundTasks.size(); taskId++) {
            String message = "";
            // Add padding to align single-digit numbers if we'll render two-digit numbers
            // later on.
            if (foundTasks.size() > 9 && taskId < 10) {
                message += " ";
            }
            message += taskId;
            Task task = foundTasks.get(taskId - 1);
            message += ". " + task.toString();
            addBotMessage(message);
        }
    }

    @Override
    public void showError(EkudException error) {
        addBotMessage("☹ OOPS!!! " + error.getMessage());
    }

    private void addBotMessage(String message) {
        ImageView botImageView = new ImageView(BOT_IMAGE);
        botImageView.setFitWidth(50);
        botImageView.setFitHeight(50);

        Label messageText = new Label(message);
        messageText.setWrapText(true);
        HBox.setHgrow(messageText, Priority.ALWAYS);

        HBox container = new HBox(botImageView, messageText);

        historyContainer.getChildren().add(container);
    }

    private void addUserMessage(String message) {
        ImageView userImageView = new ImageView(USER_IMAGE);
        userImageView.setFitWidth(50);
        userImageView.setFitHeight(50);

        Label messageText = new Label(message);
        messageText.setWrapText(true);
        HBox.setHgrow(messageText, Priority.ALWAYS);

        HBox container = new HBox(userImageView, messageText);

        historyContainer.getChildren().add(container);
    }
}
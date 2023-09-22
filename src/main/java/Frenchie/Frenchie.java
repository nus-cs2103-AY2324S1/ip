package Frenchie;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.util.Duration;


public class Frenchie extends Application {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/FrenchieUser.jpg"));
    private Image Frenchie = new Image(this.getClass().getResourceAsStream("/images/FrenchieBot.jpeg"));
    public Frenchie() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.tasks = new TaskList();
    }
    public Frenchie(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();
        try {
            this.tasks = storage.load();
        } catch (FrenchieException e) {
            tasks = new TaskList();
        }
    }
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
        stage.setTitle("Frenchie");
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
            //handleUserInput();
        });
        userInput.setOnAction((event) -> {
            //handleUserInput();
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    public String getResponse(String input) {
        StringBuilder output = new StringBuilder();
        Command command = Parser.parseCommand(input);
        String details = Parser.parseDetails(input);
        switch (command) {
        case bye:
            storage.save(tasks);
            ui.closeScanner();
            output.append(this.exit());
            Timeline exitDelay = new Timeline(new KeyFrame(Duration.seconds(2), event -> Platform.exit()));
            exitDelay.setCycleCount(1);
            exitDelay.play();
            break;
        case list:
            output.append(this.listTasks());
            return output.toString();
        case mark:
            int index = Integer.parseInt(details) - 1;
            tasks.markTaskAsCompleted(index);
            output.append(ui.markTaskAsComplete(tasks.get(index)));
            return output.toString();
        case unmark:
            index = Integer.parseInt(details) - 1;
            tasks.markTaskAsIncomplete(index);
            output.append(ui.markTaskAsIncompelte(tasks.get(index)));
            return output.toString();
        case delete:
            index = Integer.parseInt(details) - 1;
            Task task = tasks.get(index);
            tasks.deleteTask(index);
            output.append(ui.deleteTask(task, tasks));
            return output.toString();
        case todo:
            String taskName = details;
            ToDo todo = new ToDo(taskName);
            tasks.addTask(todo);
            output.append(ui.addTask(todo, tasks));
            return output.toString();
        case deadline:
            taskName = details.split("/by")[0].trim();
            String deadlineDate = details.split("/by")[1].trim();
            Deadline deadline = new Deadline(taskName, deadlineDate);
            tasks.addTask(deadline);
            output.append(ui.addTask(deadline, tasks));
            return output.toString();
        case event:
            taskName = details.split("/")[0].trim();
            String startTime = details.split("/from")[1].split("/to")[0].trim();
            String endTime = input.split("/from")[1].split("/to")[1].trim();
            Event event = new Event(taskName, startTime, endTime);
            tasks.addTask(event);
            output.append(ui.addTask(event, tasks));
            return output.toString();
        case find:
            String keyword = details;
            output.append(ui.findMessage());
            output.append(tasks.returnMatchTasks(keyword));
            return output.toString();
        default:
            return "OOPS!!! That is an invalid command!\n NOTE: Dates have to be entered in the dd/MM/yyyy HH:mm format!";
        }
        return output.toString();
    }

    public String exit() {
        return ui.exitMessage();
    }

    public String listTasks() {
        return ui.listTasks(tasks);
    }

    /*public static void main(String[] args) {
        new Frenchie("frenchie.txt").run();
    }*/
}

package Eddie;

import Eddie.GUI.DialogBox;
import Eddie.GUI.Ui;
import Eddie.Tasks.Task;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * The central control point for the chatbot Eddie.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Eddie.png"));


    @Override
    public void start(Stage stage) {
        TaskList.clear();
        Storage.readFile();
//
//        Ui.welcome();
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
        stage.setTitle("Duke");
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
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }


    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        //return "hello";

        return Parser.parse(input);
    }

    /**
     * Exits the chatbot.
     */
    public static String exit() {
        System.exit(0);
        return Ui.exit();

    }


    /**
     * Adds a task to the Tasklist.
     * @param t the task to be added to Tasklist.
     */
    public static String add(Task t){
        String taskName = t.getName();
        TaskList.add(t);


        Storage.write();
        return Ui.addTask(t.toString(), TaskList.size());


    }

    /**
     * Clears the Tasklist.
     */
    public static String clear() {
        TaskList.clear();


        Storage.write();
        return Ui.clear();
    }


    /**
     * Prints out a list of current Tasks stored in the Tasklist.
     */
    public static String list() {
        int listSize = TaskList.size();
        String s = "";
        for (int i = 0; i < listSize ; i++) {
            int num = i + 1;
            Task taskToList = TaskList.get(i);
            s = s + Ui.listTask(num, taskToList.toString()) + "\n";
        }

        return s;
    }

    /**
     * Deletes a task stored in the Tasklist.
     * @param num the Task number which you want to delete.
     */
    public static String delete(int num) {
        Task t = TaskList.get(num - 1);
        TaskList.delete(num - 1);


        Storage.write();
        return Ui.removeTask(t.toString(), TaskList.size());
    }

    public static String find(String s) {

        String output = "Search for tasks with:<" + s + ">\n";
        int size = TaskList.size();
        for (int i = 0; i < size; i++) {
            Task t = TaskList.get(i);
            String taskName = t.getName();
            int inName = taskName.indexOf(s);
            if (inName >= 0) {
                int num = i + 1;
                output = output + num + ". " + t.toString() + "\n";
            }
        }
        return output;


    }


    /**
     * Main class to be run
     * @param args NA
     */
    public static void main(String[] args) {



        Ui.welcome();
    }
}

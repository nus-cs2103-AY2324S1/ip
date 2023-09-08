package duke;

import duke.task.Task;
import duke.task.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.util.List;
import java.util.Scanner;

/**
 * Handles the input and output of the program
 *
 * @author Lian Zhi Xuan
 */
public class Ui {

    //singleton
    public static Ui ui = new Ui();

    private String currInput = "";

    // object / node in Scene graph
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // profile picture of chat
    private Image user = new Image(this.getClass().getResourceAsStream("/images/sleeping_beauty.jpg"));
    private Image chewie = new Image(this.getClass().getResourceAsStream("/images/Chewbacca.png"));

    /**
     * Draws a line
     *
     * @return a line
     */
    public String drawLine() {
        char horizontal_line = '\u2500';
        String line = "";

        for (int i = 0; i < 50; i++) {
            line += horizontal_line;
        }
        return line;
    }

    /**
     * Displays message of creating Task
     *
     * @param task
     */
    public String createTaskPrompt (Task task) {

        String result = "Chewie gotcha, task added:\n" + task.status() + task.taskName();
        result += "\nChewie now find " + Duke.listSize() + " tasks in the list";
        return result;
    }

    /**
     * Display the message of marking a task
     *
     * @param task task created
     */
    public String markPrompt(Task task) {
        String result = "Rrrruuuurrr, Chewie has marked the task.\n";
        result += task.status() + task.taskName();

        return result;
    }

    /**
     * Displays the message of unmarking a task.
     *
     * @param task task created
     */
    public String unmarkPrompt(Task task) {
        String result = "Rrrruuuurrr, Chewie has unmarked the task.\n";
        result += task.status() + task.taskName();
        return result;
    }

    /**
     * Display the message of deleting a task
     *
     * @param task task created
     */
    public String deletePrompt(Task task) {
        String result = "Chewie gotcha, task removed:\n" + task.status() + task.taskName();
        result += "\nChewie now find " + (Duke.listSize() - 1) + " tasks in the list" + "\n";
        return result;
    }

    /**
     * Display list of task
     *
     * @param taskList user's task list
     */
    public String listPrompt(TaskList taskList) {
        List<Task> list = taskList.list();


        String result = "Chewie found your task list:\n";

        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            Task task = list.get(i);

            result += index + "." + task.status() + task.taskName() + "\n";
        }
        return result;
    }

    public String findPrompt(Task[] list) {

       String result = "Chewie found these task:\n";

        for(int i = 0; i < list.length; i++) {
            int index = i + 1;
            Task task = list[i];

           result += index + "." + task.status() + task.taskName() + "\n";
        }

        return result;
    }

    /**
     * Display the starting message of the program
     *
     */
    public String startPrompt() {
        String result = "Rrrruuuurrr, I am Chewbacca, son of Attichitcuk \nHow can Chewie help?";
        return result;
    }

    /**
     * Display ending message of program
     *
     */
    public String endPrompt() {
        return "Chewie is going home now.\nBye bye.\n";
    }

    /**
     * Display error message to user
     *
     * @param e error encountered
     */
    public String errorPrompt(Exception e) {
        return e.getMessage();
    }

    /**
     * Display wrong date format message
     *
     */
    public String wrongDateFormatPrompt() {
        return "The date format is incorrect, please use yyyy-mm-dd format";
    }

    /**
     * Read input from user
     *
     * @return input as String
     */
//    public String readInput() {
//        return scanner.nextLine();
//    }


    // GUI of application
    public void GuiSetup(Stage stage) {
        stage.setTitle("Chewie");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        // init nodes needed
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        // configure scroll pane
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane,userInput,sendButton);

        // set size of application window
        mainLayout.setPrefSize(400.0, 600.0);

        // set dimension of dialog box & user input
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // starting prompt
        DialogBox.getDukeDialog(new Label(startPrompt()), new ImageView(chewie));
    }
    private void setCurrInput(String text) {
        this.currInput = text;
    }

    public String readInput() {

        sendButton.setOnMouseClicked((event) -> {
            String input = userInput.getText();
            setCurrInput(input);

            String output = Duke.run();

            if (!input.isBlank()) {
                dialogContainer.getChildren().addAll(DialogBox.getUserDialog(new Label(input),new ImageView(user)));
                dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(new Label(output),new ImageView(chewie)));
            }
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            String input = userInput.getText();
            setCurrInput(input);

            String output = Duke.run();

            if (!input.isBlank()) {
                dialogContainer.getChildren().addAll(DialogBox.getUserDialog(new Label(input),new ImageView(user)));
                dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(new Label(output),new ImageView(chewie)));
            }
            userInput.clear();
        });

        return currInput;
    }

    private Label getDialogLabel(String text) {

        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}

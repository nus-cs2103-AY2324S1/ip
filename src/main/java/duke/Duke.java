package duke;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.OutOfRangeException;
import exceptions.UnknownCommandException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

/**
 * Starts the chatbot
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private final TaskList list;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage("duke.txt");
//        try {
        list = new TaskList(storage.load());
//        } catch (Exceptions.DukeException e) {
//            ui.showLoadingError();
//            tasks = new duke.TaskList();
//        }
    }

    /**
     * ALlows the chatbbot to run
     */
    public String run(String input, TaskList list) { //returns string cus DialogBox returns string
        String message = "";
        try {
            if (input.equals("list")) {
                 message = list.printList();
            } else if (input.startsWith("mark ")) {
                int num = input.charAt(5) - '0' - 1;
                if (num >= 0 && num < list.count) {
                    list.getTask(num).markAsDone();
                    message = "Nice! I've marked this task done:" + list.getTask(num);
                } else {
                    System.out.println("Invalid");
                }
            } else if (input.startsWith("unmark ")) {
                int num = input.charAt(7) - '0' - 1;
                if (num >= 0 && num < list.count) {
                    list.getTask(num).markAsUndone();
                    message = "OK, I've marked this task as not done yet:" + list.getTask(num);
                } else {
                    message = "Invalid";
                }
            } else if (input.startsWith("todo ")) {
                String des = input.substring(5);
                if (des.isBlank()) {
                    throw new EmptyDescriptionException();
                }
                message = list.addTask(new Todo(des));
            } else if (input.startsWith("deadline ")) {
                if (input.substring(9).isBlank()) {
                    throw new EmptyDescriptionException();
                }
                String[] split = input.substring(9).split(" /by ");
                String des = split[0];
                LocalDate date = LocalDate.parse(split[1]);
                String by = date.format(DateTimeFormatter.ofPattern("MMM d yyy"));
                message = list.addTask(new Deadline(des, by));

            } else if (input.startsWith("event ")) {
                if (input.substring(6).isBlank()) {
                    throw new EmptyDescriptionException();
                }
                String[] split = input.substring(6).split(" /from ");
                String des = split[0];
                String[] fromto = split[1].split(" /to ");
                String from = fromto[0];
                String to = fromto[1];
                message = list.addTask(new Event(des, from, to));
            } else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index <= list.count) {
                    message = list.deleteTask(index);
                } else {
                    throw new OutOfRangeException();
                }
            } else if (input.equals("bye")) {
                message = "slay";
            } else if (input.startsWith("find ")) {
                String description = input.substring(5);
                message = list.findTask(description, list);
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            message = e.getMessage();
        } catch (DateTimeParseException e) {
            message = "OOps invalid time input";
        }
//        ui.showWelcome();
//        boolean isExit = false;
//        Parser parser = new Parser();
//        while (!isExit) {
//            String input = ui.getUserCommand();
//            parser.parse(input, list);
//            isExit = ui.isExit(input);
//        }
        storage.save(list);
        return message;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    String getResponse(String input){
        return run(input, list);
    }
//    /**
//     * Executes Duke
//     *
//     * @param args
//     * @throws FileNotFoundException
//     */
//    public static void main(String[] args) throws FileNotFoundException {
//        new Duke().run();
//    }

//    @Override
//    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//
//        //The container for the content of the chat to scroll.
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput, 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        //Part 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//    }
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }

//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }


}



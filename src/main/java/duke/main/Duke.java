package duke.main;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.StorageException;
import duke.exceptions.UnknownCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;




    public class Duke{
        private static final String FILE_PATH = "./data/duke.txt";
        private static final String chatBotName = "Cristiano";
        private Storage storage;
        private TaskManager taskManager;
        private UI ui;
        private Parser parser;
        private ScrollPane scrollPane;
        private VBox dialogContainer;
        private TextField userInput;
        private Button sendButton;
        private Scene scene;
        private Image user = new Image(this.getClass().getResourceAsStream("/images/speed.png"));
        private Image duke = new Image(this.getClass().getResourceAsStream("/images/ronaldo.png"));

        public Duke() {
            try {
                this.parser = new Parser();
                storage = new Storage(FILE_PATH);
                this.ui = new UI();
                ui.greetUser(chatBotName);
                taskManager = storage.loadData();
            } catch (DukeException e) {
                ui.displayError(e);
            }
        }

//        @Override
//        public void start(Stage stage) {
//            scrollPane = new ScrollPane();
//            dialogContainer = new VBox();
//            scrollPane.setContent(dialogContainer);
//
//            userInput = new TextField();
//            sendButton = new Button("Send");
//
//            AnchorPane mainLayout = new AnchorPane();
//            mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//            scene = new Scene(mainLayout);
//
//            stage.setTitle("Duke");
//            stage.setResizable(false);
//            stage.setMinHeight(600.0);
//            stage.setMinWidth(400.0);
//
//            mainLayout.setPrefSize(400.0, 600.0);
//
//            scrollPane.setPrefSize(385, 535);
//            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//            scrollPane.setVvalue(1.0); //This sets the vertical scroll value of the scrollPane to 1.0. This means the content inside the scrollPane will be scrolled to the very bottom.
//            scrollPane.setFitToWidth(true); //This ensures that the content inside the scrollPane will always be resized to fit the width of the scrollPane.
//
//            // You will need to import `javafx.scene.layout.Region` for this.
//            dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//            userInput.setPrefWidth(325.0);
//
//            sendButton.setPrefWidth(55.0);
//
//            AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//            AnchorPane.setBottomAnchor(sendButton, 1.0);
//            AnchorPane.setRightAnchor(sendButton, 1.0);
//
//            AnchorPane.setLeftAnchor(userInput , 1.0);
//            AnchorPane.setBottomAnchor(userInput, 1.0);
//
//            stage.setScene(scene);
//            stage.show();
//
//            sendButton.setOnMouseClicked((event) -> {
//                handleUserInput();
//            });
//
//            userInput.setOnAction((event) -> {
//                handleUserInput();
//            });
//
//            dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//        }
//
//        private void handleUserInput() {
//            Label userText = new Label(userInput.getText());
//            Label dukeText = new Label(getResponse(userInput.getText()));
//            dialogContainer.getChildren().addAll(
//                    getUserDialog(userText, new ImageView(user)),
//                    getDukeDialog(dukeText, new ImageView(duke))
//            );
//            userInput.clear();
//        }
//
        protected String getResponse(String input) {
            return "Duke heard: " + input;
        }
//
//        private Label getDialogLabel(String text) {
//            Label textToAdd = new Label(text);
//            textToAdd.setWrapText(true);
//
//            return textToAdd;
//        }
//
//        public class DialogBox extends HBox {
//
//            private Label text;
//            private ImageView displayPicture;
//
//            public DialogBox(Label l, ImageView iv) {
//                text = l;
//                displayPicture = iv;
//
//                text.setWrapText(true);
//                displayPicture.setFitWidth(100.0);
//                displayPicture.setFitHeight(100.0);
//
//                this.setAlignment(Pos.TOP_RIGHT);
//                this.getChildren().addAll(text, displayPicture);
//            }
//
//            protected void flip() {
//                this.setAlignment(Pos.TOP_LEFT);
//                ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//                FXCollections.reverse(tmp);
//                this.getChildren().setAll(tmp);
//            }
//
//        }
//
//        public DialogBox getUserDialog(Label l, ImageView iv) {
//            return new DialogBox(l, iv);
//        }
//
//        public DialogBox getDukeDialog(Label l, ImageView iv) {
//            DialogBox db = new DialogBox(l, iv);
//            db.flip();
//            return db;
//        }


        public static void main(String[] args) {
            Duke duke = new Duke();
            duke.run();
        }

        /**
         * this is the function that runs while the user is using the application.
         * It takes in users input, calls getCommand to decide which function it should to call to handle the input.
         * It also handles duke.exceptions and waits for user to say bye.
         */
        protected void run() {

            Scanner scanner = new Scanner(System.in);
            String input = "";
            while (!input.equals("bye")) {
                try {
                    input = scanner.nextLine();
                    String command = this.parser.getCommand(input);
                    switch (command) {
                        case "list":
                            taskManager.list();
                            break;
                        case "mark":
                        case "unmark":
                            handleMarking(input, taskManager);
                            break;
                        case "todo":
                            handleTodo(input, taskManager);
                            break;
                        case "deadline":
                            handleDeadline(input, taskManager);
                            break;
                        case "event":
                            handleEvent(input, taskManager);
                            break;
                        case "delete":
                            handleDelete(input, taskManager);
                            break;
                        case "find":
                            handleFind(input, taskManager);
                        case "bye":
                            break;
                        default:
                            throw new UnknownCommandException("I may be the GOAT but I don't know what that means.");
                    }
                } catch (DukeException e) {
                    ui.displayError(e);
                }
            }
            ui.exit();
        }

        private void handleFind(String input, TaskManager taskManager) throws InvalidArgumentException {
            String keyword = parser.parseFind(input);
            ArrayList<Task> filteredList = taskManager.filterList(keyword);
            ui.displayFilteredList(filteredList, filteredList.size());
        }


        /**
         * returns the first word of the input string. Helps to determine which function to call.
         * @param input
         * @return command string
         */

        /**
         * This function handles the logic for when a user wants to mark a task as done or undone.
         * @param input
         * @param taskManager
         * @throws InvalidArgumentException
         */
        private void handleMarking(String input, TaskManager taskManager) throws DukeException {
            String[] words = input.split(" ");
            try {
                int index = Integer.parseInt(words[1]);
                if (words[0].equals("mark")) {
                    taskManager.mark(index);
                } else if (words[0].equals("unmark")) {
                    taskManager.unmark(index);
                }
                updateStorage();
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Please enter a numerical index!");
            } catch (StorageException e) {
                throw new StorageException("duke.main.Storage Error");
            }
        }

        private void updateStorage() throws StorageException {
            this.storage.saveData(taskManager);
        }

        /**
         * if the user wants to add a to do-task, this function will handle the logic.
         * @param input
         * @param taskManager
         * @throws InvalidArgumentException
         */
        private void handleTodo(String input, TaskManager taskManager) throws InvalidArgumentException, StorageException {
            String taskName = this.parser.parseToDo(input);
            Task task = new ToDo(taskName);
            taskManager.add(task);
            updateStorage();
        }

        /**
         * similar to to-do, this function handles logic for handling adding a deadline to the task manager.
         * @param input
         * @param taskManager
         * @throws InvalidArgumentException
         */
        private void handleDeadline(String input, TaskManager taskManager) throws InvalidArgumentException, StorageException {
            String[] parsedInput = this.parser.parseDeadline(input);
            String taskName = parsedInput[0];
            String dueDate = parsedInput[1];

            LocalDateTime time;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                time = LocalDateTime.parse(dueDate, formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidArgumentException("Please use the format dd/MM/yyyy HH:mm");
            }

            Task task = new Deadline(taskName, time);
            taskManager.add(task);
            updateStorage();
        }

        /**
         * similar to to-do and deadline, this function handles the logic for adding an event to the task manager.
         * @param input
         * @param taskManager
         * @throws InvalidArgumentException
         */
        private void handleEvent(String input, TaskManager taskManager) throws InvalidArgumentException, StorageException {
            String[] parsedInput = this.parser.parseEvent(input);
            String taskName = parsedInput[0];
            String from = parsedInput[1];
            String to = parsedInput[2];

            LocalDateTime fromTime;
            LocalDateTime toTime;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                fromTime = LocalDateTime.parse(from, formatter);
                toTime = LocalDateTime.parse(to, formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidArgumentException("Please use the format dd/MM/yyyy HH:mm");
            }

            Task task = new Event(taskName, fromTime, toTime);
            taskManager.add(task);
            updateStorage();
        }

        /**
         * this function allows the user to delete a task by identifying its index.
         * @param input
         * @param taskManager
         * @throws InvalidArgumentException
         */
        private void handleDelete(String input, TaskManager taskManager) throws InvalidArgumentException {
            String[] words = input.split(" ");
            try {
                int index = Integer.parseInt(words[1]);
                taskManager.delete(index);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Please enter a numerical index!");
            }
        }
    }
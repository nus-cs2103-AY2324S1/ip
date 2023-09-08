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


//        protected String getResponse(String input) {
//            return "Duke heard: " + input;
//        }


        public static void main(String[] args) {
            Duke duke = new Duke();

        }

        /**
         * this is the function that runs while the user is using the application.
         * It takes in users input, calls getCommand to decide which function it should to call to handle the input.
         * It also handles duke.exceptions and waits for user to say bye.
         */
        protected String getResponse(String input) {

            //Scanner scanner = new Scanner(System.in);
            //String input = "";
            while (!input.equals("bye")) {
                try {
                    //input = scanner.nextLine();
                    String command = this.parser.getCommand(input);
                    switch (command) {
                        case "list":
                            return taskManager.list();
                        case "mark":
                        case "unmark":
                            return handleMarking(input, taskManager);
                        case "todo":
                            return handleTodo(input, taskManager);
                        case "deadline":
                            return handleDeadline(input, taskManager);
                        case "event":
                            return handleEvent(input, taskManager);
                        case "delete":
                            return handleDelete(input, taskManager);
                        case "find":
                            handleFind(input, taskManager);
                        case "bye":
                            break;
                        default:
                            throw new UnknownCommandException("I may be the GOAT but I don't know what that means.");
                    }
                } catch (DukeException e) {
                    return ui.displayError(e);
                }
            }
            return ui.exit();
        }

        private String handleFind(String input, TaskManager taskManager) throws InvalidArgumentException {
            String keyword = parser.parseFind(input);
            ArrayList<Task> filteredList = taskManager.filterList(keyword);
            return ui.displayFilteredList(filteredList, filteredList.size());
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
        private String handleMarking(String input, TaskManager taskManager) throws DukeException {
            String[] words = input.split(" ");
            try {
                int index = Integer.parseInt(words[1]);
                String response = "";
                if (words[0].equals("mark")) {
                    response = taskManager.mark(index);
                } else if (words[0].equals("unmark")) {
                    response = taskManager.unmark(index);
                }
                updateStorage();
                return response;
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
        private String handleTodo(String input, TaskManager taskManager) throws InvalidArgumentException, StorageException {
            String taskName = this.parser.parseToDo(input);
            Task task = new ToDo(taskName);
            String response = taskManager.add(task);
            updateStorage();
            return response;
        }

        /**
         * similar to to-do, this function handles logic for handling adding a deadline to the task manager.
         * @param input
         * @param taskManager
         * @throws InvalidArgumentException
         */
        private String handleDeadline(String input, TaskManager taskManager) throws InvalidArgumentException, StorageException {
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
            String response = taskManager.add(task);
            updateStorage();
            return response;
        }

        /**
         * similar to to-do and deadline, this function handles the logic for adding an event to the task manager.
         * @param input
         * @param taskManager
         * @throws InvalidArgumentException
         */
        private String handleEvent(String input, TaskManager taskManager) throws InvalidArgumentException, StorageException {
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
            String response = taskManager.add(task);
            updateStorage();
            return response;
        }

        /**
         * this function allows the user to delete a task by identifying its index.
         * @param input
         * @param taskManager
         * @throws InvalidArgumentException
         */
        private String handleDelete(String input, TaskManager taskManager) throws InvalidArgumentException {
            String[] words = input.split(" ");
            try {
                int index = Integer.parseInt(words[1]);
                String response = taskManager.delete(index);
                return response;
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Please enter a numerical index!");
            }
        }
    }
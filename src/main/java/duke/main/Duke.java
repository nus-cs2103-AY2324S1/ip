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

    public class Duke {
        private static final String FILE_PATH = "./data/duke.txt";
        private static final String chatBotName = "Cristiano";
        private Storage storage;
        private TaskManager taskManager;
        private UI ui;
        private Parser parser;

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
                        default:
                            throw new UnknownCommandException("I may be the GOAT but I don't know what that means.");
                    }
                } catch (DukeException e) {
                    ui.displayError(e);
                }
            }
            ui.exit();
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
import exceptions.InvalidArgumentException;
import exceptions.StorageException;
import exceptions.UnknownCommandException;
import exceptions.DukeException;
import tasks.Task;
import tasks.ToDo;
import tasks.Event;
import tasks.Deadline;

import java.io.IOException;
import java.util.Scanner;

    public class Duke {
        private static final String FILE_PATH = "./data/duke.txt";
        private static final String chatBotName = "Cristiano";
        private Storage storage;
        private TaskManager taskManager;

        public Duke() {
            try {
                storage = new Storage(FILE_PATH);
                taskManager = storage.loadData();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void main(String[] args) {
            String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            Duke duke = new Duke();
            duke.greetUser();
            duke.run();
        }

        protected void greetUser() {
            UI.printLine();
            System.out.println("Hello! I'm " + chatBotName + "! SUIIII!!!");
            System.out.println("What can I do for you?");
        }

        protected static void exit() {
            UI.printLine();
            System.out.println("Bye. Hope to see you again soon!");
            UI.printLine();
        }

        /**
         * this is the function that runs while the user is using the application.
         * It takes in users input, calls getCommand to decide which function it should to call to handle the input.
         * It also handles exceptions and waits for user to say bye.
         */
        protected void run() {
            Scanner scanner = new Scanner(System.in);
            String input = "";
            while (!input.equals("bye")) {
                try {
                    input = scanner.nextLine();
                    String command = getCommand(input);
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
                    UI.printLine();
                    System.out.println(e.getMessage());
                    UI.printLine();
                }
            }
            exit();
        }


        /**
         * returns the first word of the input string. Helps to determine which function to call.
         * @param input
         * @return command string
         */
        private String getCommand(String input) {
            return input.split(" ")[0];
        }

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
                throw new StorageException("Storage Error");
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

            int indexOfSpace = input.indexOf(" ");
            if (indexOfSpace == -1 || indexOfSpace == input.length() - 1) {
                throw new InvalidArgumentException("Please enter a task description.");
            }
            String taskName = input.substring(input.indexOf(" ") + 1).trim();
            if(taskName.isEmpty()) {
                throw new InvalidArgumentException("Please enter a task description.");
            }
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
            String suffix = input.substring(input.indexOf(" ") + 1);
            String[] parts = suffix.split(" /due ");
            if (parts.length != 2) {
                throw new InvalidArgumentException("Invalid format for deadline. " +
                        "Please use: deadline task name /due due Date");
            }
            String taskName = parts[0].trim();
            String dueDate = parts[1].trim();
            Task task = new Deadline(taskName, dueDate);
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
            String suffix = input.substring(input.indexOf(" ") + 1);
            String[] parts = suffix.split(" /from ");
            if (parts.length != 2) {
                throw new InvalidArgumentException("Invalid format for event. " +
                        "Please use: event task_name /from start /to end");
            }
            String taskName = parts[0].trim();
            String[] timeParts = parts[1].split(" /to ");
            if (timeParts.length != 2) {
                throw new InvalidArgumentException("Invalid format for event. " +
                        "Please use: event task_name /from start /to end");
            }
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            Task task = new Event(taskName, from, to);
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
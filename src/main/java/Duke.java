import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String DATAPATH = "./data/duke.txt";

    private TaskList taskList;
    private Ui ui;

    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, CHECK, TODAY,
        TODO, DEADLINE, EVENT,
    }

    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    private void loadData() {
        try {
            Storage storage = new Storage(DATAPATH);
            storage.loadTasks(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("No data file found.");
        } catch (IOException e) {
            System.out.println("Error loading tasks from the data file.");
        }
    }

    private void run() {
        this.ui.showGreet();
        this.loadData();

        while (true) {
            try {
                String userInput = this.ui.readInput();

                if (userInput.trim().isEmpty()) {
                    throw new EmptyCommandException();
                }

                String[] parts = userInput.split(" ", 2);
                CommandType command = this.getCommand(parts[0]);

                switch (command) {
                case BYE:
                    this.exit();
                    return;
                case LIST:
                    this.ui.showList(this.taskList);
                    break;
                case MARK:
                    this.markTaskAsDone(parts);
                    break;
                case UNMARK:
                    this.markTaskAsNotDone(parts);
                    break;
                case TODO:
                    this.addTodo(parts);
                    break;
                case DEADLINE:
                    this.addDeadline(parts);
                    break;
                case EVENT:
                    this.addEvent(parts);
                    break;
                case DELETE:
                    this.deleteTask(parts);
                    break;
                case CHECK:
                    this.checkTasksOnDate(parts);
                    break;
                case TODAY:
                    this.checkTasksForToday();
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                this.ui.showDukeException(e);
            }
        }
    }

    private CommandType getCommand(String command) throws UnknownCommandException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new UnknownCommandException();
        }
    }

    private void exit() {
        this.saveTask();
        this.ui.closeInput();
    }

    private void markTaskAsDone(String[] userCommandParts) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommandParts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = taskList.get(taskIndex);
            task.markAsDone();
            this.ui.showDone(task);
            saveTask();
        } catch (InvalidTaskIndexException e) {
            this.ui.showDukeException(e);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            this.ui.showArrayIndexOutOfBoundsException();
        }
    }

    private void markTaskAsNotDone(String[] userCommandParts) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommandParts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = taskList.get(taskIndex);
            task.markAsNotDone();
            this.ui.showNotDone(task);
            saveTask();
        } catch (InvalidTaskIndexException e) {
            this.ui.showDukeException(e);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            this.ui.showArrayIndexOutOfBoundsException();
        }

    }

    private void addTodo(String[] userCommandParts) {
        try {
            if (userCommandParts.length < 2 || userCommandParts[1].trim().isEmpty()) {
                throw new EmptyDescriptionException("todo");
            }

            String description = userCommandParts[1].trim();

            Todo newTodo = new Todo(description);
            taskList.add(newTodo);
            this.ui.showAdd(newTodo, this.taskList.getLength());
            saveTask();
        } catch (EmptyDescriptionException e) {
            this.ui.showDukeException(e);
        }
    }

    private void addDeadline(String[] userCommandParts) {
        try {
            if (userCommandParts.length < 2) {
                throw new EmptyDescriptionException("Deadline");
            }

            String[] deadlineParts = userCommandParts[1].split("/by");
            if (deadlineParts.length < 2) {
                throw new InvalidFormatException("Please use the format: deadline <description> /by <d/M/yyyy HHmm>.");
            }

            String description = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();
            LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

            Deadline newDeadline = new Deadline(description, dateTime);
            taskList.add(newDeadline);
            this.ui.showAdd(newDeadline, this.taskList.getLength());
            saveTask();

        } catch (EmptyDescriptionException | InvalidFormatException e) {
            this.ui.showDukeException(e);
        } catch (DateTimeParseException e) {
            this.ui.showInvalidDateTimeFormat();
        }
    }

    private void addEvent(String[] userCommandParts) throws EmptyDescriptionException {
        try {
            if (userCommandParts.length < 2) {
                throw new EmptyDescriptionException("event");
            }

            String[] eventParts = userCommandParts[1].split("/at");
            if (eventParts.length < 2) {
                throw new InvalidFormatException("Please use the format: event <description> /at <d/M/yyyy HHmm>");
            }

            String description = eventParts[0].trim();

            LocalDateTime dateTime = LocalDateTime.parse(eventParts[1].trim(),
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            Event newEvent = new Event(description, dateTime);

            taskList.add(newEvent);
            this.ui.showAdd(newEvent, this.taskList.getLength());
            saveTask();

        } catch (EmptyDescriptionException | InvalidFormatException e) {
            this.ui.showDukeException(e);
        } catch (DateTimeParseException e) {
            this.ui.showInvalidDateTimeFormat();
        }
    }

    private void deleteTask(String[] userCommandParts) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommandParts[1]) - 1;

            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task removedTask = taskList.get(taskIndex);
            taskList.delete(taskIndex);
            this.ui.showDelete(removedTask, this.taskList.getLength());
            saveTask();
        } catch (InvalidTaskIndexException e) {
            this.ui.showDukeException(e);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            this.ui.showArrayIndexOutOfBoundsException();
        }
    }

    private void saveTask() {
        try {
            Storage storage = new Storage(DATAPATH);
            storage.saveTasks(taskList);
        } catch (IOException e) {
            this.ui.showSavingError();
        }
    }

    private void checkTasksOnDate(String[] userCommandParts) {
        try {
            if (userCommandParts.length < 2) {
                throw new EmptyDescriptionException("date");
            }

            String dateString = userCommandParts[1].trim();
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
            this.ui.showTasksOnDate(date, taskList);

        } catch (EmptyDescriptionException e) {
            this.ui.showDukeException(e);
        } catch (DateTimeParseException e) {
            this.ui.showInvalidDateFormat();
        }
    }

    private void checkTasksForToday() {
        LocalDate today = LocalDate.now();
        this.ui.showTasksForToday(today, taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
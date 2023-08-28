import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    private static String DATAPATH = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storage.loadData());
        } catch (DukeDatabaseException e) {
            this.ui.showDukeException(e);
        }
    }

    private void run() {
        this.ui.showGreet();

        while (true) {
            try {
                String userInput = this.ui.readInput();

                if (userInput.trim().isEmpty()) {
                    throw new EmptyCommandException();
                }

                CommandType command = Parser.parseCommand(userInput);
                String args = Parser.parseArgument(userInput);

                switch (command) {
                case BYE:
                    this.exit();
                    return;
                case LIST:
                    this.ui.showList(this.taskList);
                    break;
                case MARK:
                    this.markTaskAsDone(args);
                    break;
                case UNMARK:
                    this.markTaskAsNotDone(args);
                    break;
                case TODO:
                    this.addTodo(args);
                    break;
                case DEADLINE:
                    this.addDeadline(args);
                    break;
                case EVENT:
                    this.addEvent(args);
                    break;
                case DELETE:
                    this.deleteTask(args);
                    break;
                case CHECK:
                    this.checkTasksOnDate(args);
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

    private void exit() {
        try {
            this.storage.saveData(this.taskList);
            this.ui.showExit();
        } catch (IOException e) {
            this.ui.showSavingError();
        }
    }

    private void markTaskAsDone(String userCommand) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommand) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = taskList.get(taskIndex);
            task.markAsDone();
            this.ui.showDone(task);
        } catch (InvalidTaskIndexException e) {
            this.ui.showDukeException(e);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            this.ui.showArrayIndexOutOfBoundsException();
        }
    }

    private void markTaskAsNotDone(String userCommand) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommand) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = taskList.get(taskIndex);
            task.markAsNotDone();
            this.ui.showNotDone(task);
        } catch (InvalidTaskIndexException e) {
            this.ui.showDukeException(e);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            this.ui.showArrayIndexOutOfBoundsException();
        }

    }

    private void addTodo(String userCommand) {
        try {
            if (userCommand.trim().isEmpty()) {
                throw new EmptyDescriptionException("todo");
            }

            String description = userCommand.trim();

            Todo newTodo = new Todo(description);
            taskList.add(newTodo);
            this.ui.showAdd(newTodo, this.taskList.getLength());
        } catch (EmptyDescriptionException e) {
            this.ui.showDukeException(e);
        }
    }

    private void addDeadline(String userCommand) {
        try {
            if (userCommand.trim().isEmpty()) {
                throw new EmptyDescriptionException("Deadline");
            }

            String[] deadlineParts = userCommand.split("/by");
            if (deadlineParts.length < 2) {
                throw new InvalidFormatException("Please use the format: deadline <description> /by <d/M/yyyy HHmm>.");
            }

            String description = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();
            LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

            Deadline newDeadline = new Deadline(description, dateTime);
            taskList.add(newDeadline);
            this.ui.showAdd(newDeadline, this.taskList.getLength());

        } catch (EmptyDescriptionException | InvalidFormatException e) {
            this.ui.showDukeException(e);
        } catch (DateTimeParseException e) {
            this.ui.showInvalidDateTimeFormat();
        }
    }

    private void addEvent(String userCommand) throws EmptyDescriptionException {
        try {
            if (userCommand.trim().isEmpty()) {
                throw new EmptyDescriptionException("event");
            }

            String[] eventParts = userCommand.split("/at");
            if (eventParts.length < 2) {
                throw new InvalidFormatException("Please use the format: event <description> /at <d/M/yyyy HHmm>");
            }

            String description = eventParts[0].trim();

            LocalDateTime dateTime = LocalDateTime.parse(eventParts[1].trim(),
                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            Event newEvent = new Event(description, dateTime);

            taskList.add(newEvent);
            this.ui.showAdd(newEvent, this.taskList.getLength());

        } catch (EmptyDescriptionException | InvalidFormatException e) {
            this.ui.showDukeException(e);
        } catch (DateTimeParseException e) {
            this.ui.showInvalidDateTimeFormat();
        }
    }

    private void deleteTask(String userCommand) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommand) - 1;

            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task removedTask = taskList.get(taskIndex);
            taskList.delete(taskIndex);
            this.ui.showDelete(removedTask, this.taskList.getLength());
        } catch (InvalidTaskIndexException e) {
            this.ui.showDukeException(e);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            this.ui.showArrayIndexOutOfBoundsException();
        }
    }

    private void checkTasksOnDate(String userCommand) {
        try {
            if (userCommand.trim().isEmpty()) {
                throw new EmptyDescriptionException("date");
            }

            String dateString = userCommand.trim();
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
        new Duke(DATAPATH).run();
    }
}
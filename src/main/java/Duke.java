import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "\t____________________________________________________________";
    private static String DATAPATH = "./data/duke.txt";

    Scanner scanner = new Scanner(System.in);
    private TaskList taskList = new TaskList();

    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, CHECK, TODAY,
        TODO, DEADLINE, EVENT,

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

    private void greet() {
        System.out.println(LINE);
        System.out.println("\t Hello! I'm Bard.");
        System.out.println("\t What can I do for you?");
        System.out.println(LINE);
        System.out.println();
    }

    private void getUserInput() {
        while (true) {
            try {
                String userInput = scanner.nextLine();

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
                    this.showList();
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
                    this.printTasksForToday();
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println(LINE);
                System.out.println("\t" + e.getMessage());
                System.out.println(LINE);
                System.out.println();
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
        System.out.println(LINE);
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private void showList() {
        if (taskList.isEmpty()) {
            System.out.println(LINE);
            System.out.println("\t There are no tasks in your list.");
            System.out.println(LINE);
            System.out.println();
            return;
        }

        System.out.println(LINE);
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskList.getLength(); i++) {
            System.out.println("\t " + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void markTaskAsDone(String[] userCommandParts) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommandParts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = taskList.get(taskIndex);
            if (task.isDone) {
                System.out.println(LINE);
                System.out.println("\t ☹ OOPS!!! This task is already marked as done:\n" +
                        "\t\t" + taskList.get(taskIndex));
                System.out.println(LINE);
                System.out.println();
            } else {
                task.markAsDone();
                System.out.println(LINE);
                System.out.println("\t Nice! I've marked this task as done:\n" +
                        "\t\t" + taskList.get(taskIndex));
                System.out.println(LINE);
                System.out.println();
                saveTask();
            }
        } catch (InvalidTaskIndexException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE);
            System.out.println("\t ☹ OOPS!!! Please provide a valid task number to mark as done.");
            System.out.println(LINE);
            System.out.println();
        }
    }

    private void markTaskAsNotDone(String[] userCommandParts) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommandParts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = taskList.get(taskIndex);
            if (!task.isDone) {
                System.out.println(LINE);
                System.out.println("\t OOPS!!! This task is already marked as not done:\n" +
                        "\t\t" + taskList.get(taskIndex));
                System.out.println(LINE);
                System.out.println();
            } else {
                task.markAsNotDone();
                System.out.println(LINE);
                System.out.println("\t OK, I've marked this task as NOT done yet:\n" +
                        "\t\t" + taskList.get(taskIndex));
                System.out.println(LINE);
                System.out.println();
                saveTask();
            }
        } catch (InvalidTaskIndexException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE);
            System.out.println("\t ☹ OOPS!!! Please provide a valid task number to mark as not done.");
            System.out.println(LINE);
            System.out.println();
        }

    }

    private void addTodo(String[] userCommandParts) {
        try {
            if (userCommandParts.length < 2 || userCommandParts[1].trim().isEmpty()) {
                throw new EmptyDescriptionException("todo");
            }

            String description = userCommandParts[1].trim();

            Todo newTask = new Todo(description);
            taskList.add(newTask);

            System.out.println(LINE);
            System.out.println("\t Got it. I've added this task:\n" +
                    "\t\t" + newTask + "\n\t Now you have " + taskList.getLength() + " tasks in the list.");
            System.out.println(LINE);
            System.out.println();
            saveTask();
        } catch (EmptyDescriptionException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
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

            System.out.println(LINE);
            System.out.println("\t Got it. I've added this task:\n" +
                    "\t\t" + newDeadline + "\n\t Now you have " + taskList.getLength() + " tasks in the list.");
            System.out.println(LINE);
            System.out.println();
            saveTask();

        } catch (EmptyDescriptionException | InvalidFormatException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease enter the time in the format of <d/M/yyyy HHmm>!\n");
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
            System.out.println(LINE);
            System.out.println("\t Got it. I've added this task:\n" +
                    "\t\t" + newEvent + "\n\t Now you have " + taskList.getLength() + " tasks in the list.");
            System.out.println(LINE);
            System.out.println();
            saveTask();

        } catch (EmptyDescriptionException | InvalidFormatException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease enter the time in the format of <d/M/yyyy HHmm>!\n");
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

            System.out.println(LINE);
            System.out.println("\t Noted. I've removed this task:\n" +
                    "\t\t" + removedTask +
                    "\n\t Now you have " + taskList.getLength() + " tasks in the list.");
            System.out.println(LINE);
            System.out.println();
            saveTask();
        } catch (InvalidTaskIndexException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("\t ☹ OOPS!!! Please provide a valid task number to delete.");
            System.out.println(LINE);
            System.out.println();
        }
    }

    private void saveTask() {
        try {
            Storage storage = new Storage(DATAPATH);
            storage.saveTasks(taskList);
        } catch (IOException e) {
            System.out.println("Error saving tasks to the data file:" + e.getMessage());
        }
    }

    private void checkTasksOnDate(String[] userCommandParts) {
        try {
            if (userCommandParts.length < 2) {
                throw new EmptyDescriptionException("date");
            }

            String dateString = userCommandParts[1].trim();
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));

            System.out.println(LINE);
            System.out.println("\t Tasks on " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ":");

            boolean foundTasks = false;

            for (int i = 0; i < taskList.getLength(); i++) {
                Task task = taskList.get(i);
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.time.toLocalDate().equals(date)) {
                        System.out.println("\t\t " + task);
                        foundTasks = true;
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if (event.time.toLocalDate().equals(date)) {
                        System.out.println("\t\t " + task);
                        foundTasks = true;
                    }
                }
            }

            if (!foundTasks) {
                System.out.println("\t\t Yay! You have no tasks on "
                        + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " :D");
            }

            System.out.println(LINE);
            System.out.println();

        } catch (EmptyDescriptionException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        } catch (DateTimeParseException e) {
            System.out.println(LINE);
            System.out.println("\t ☹ OOPS!!! Please provide a valid date in the format: d/M/yyyy.");
            System.out.println(LINE);
            System.out.println();
        }
    }

    private void printTasksForToday() {
        LocalDate today = LocalDate.now();

        System.out.println(LINE);
        System.out.println("\t Tasks for today (" + today.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + "):");

        boolean foundTasks = false;

        for (int i = 0; i < taskList.getLength(); i++) {
            Task task = taskList.get(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.time.toLocalDate().equals(today)) {
                    System.out.println("\t\t " + task);
                    foundTasks = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.time.toLocalDate().equals(today)) {
                    System.out.println("\t\t " + task);
                    foundTasks = true;
                }
            }
        }

        if (!foundTasks) {
            System.out.println("\t\t Yay! You have no tasks today :D");
        }

        System.out.println(LINE);
        System.out.println();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.loadData();
        duke.greet();
        duke.getUserInput();
    }
}
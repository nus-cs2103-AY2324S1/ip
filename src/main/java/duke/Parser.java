package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The {@code Parser} class. Deals with making sense of the user input.
 */
public class Parser {

    private final Ui ui;
    private final Duke duke;
    private final TaskList tasks;

    /**
     * Enumeration of all possible user commands.
     */
    public enum Command {
        TASK, TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, COMMANDS, BYE, FIND
    }

    /**
     * Constructs a new {@code Parser} object.
     * Can only be instantiated with a {@code Duke} object.
     *
     * @param duke {@code Duke} object that called the constructor.
     * @param tasks {@code TaskList} object instantiated by the same {@code Duke} object.
     * @param ui {@code Ui} object instantiated by the same {@code Duke} object.
     */
    Parser(Duke duke, duke.TaskList tasks, duke.Ui ui) { // Can only be instantiated with a Duke object
        this.duke = duke;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses the user input from the {@code Scanner}.
     * If the input corresponds to one of the valid commands, the {@code executeCommand} method
     * will execute the command. Otherwise, an error message will be printed. (This will be
     * treated as an invalid input.)
     *
     * @param message Input from the user.
     */
    public void readInput(String message) {
        try {
            executeCommand(Command.valueOf(message.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.printf("I'm just a robot!%n"
                    + "I don't understand what %s is!%n", message);
            ui.incrementInvalidInputs();
            ui.printHorizontalLine();
        } catch (IOException e) {
            System.out.printf("Something went wrong: " + e);
        }
    }

    /**
     * Executes the command generated from the {@code readInput} method.
     *
     * @param command Command to be executed.
     */
    public void executeCommand(Command command) throws IOException {
        switch (command) {
        case TASK:
            duke.createTask();
            break;
        case TODO:
            duke.createToDo();
            break;
        case DEADLINE:
            duke.createDeadline();
            break;
        case EVENT:
            duke.createEvent();
            break;
        case LIST:
            duke.list();
            break;
        case MARK:
            duke.markAsComplete();
            break;
        case UNMARK:
            duke.markAsIncomplete();
            break;
        case DELETE:
            duke.deleteTask();
            break;
        case COMMANDS:
            ui.printCommands();
            break;
        case BYE:
            duke.exit(0);
            break;
        case FIND:
            duke.findTask();
            break;
        default:
            // Shouldn't reach here
            // Input errors should already be caught in the readInput() method.
            break;
        }
    }

    /**
     * Instantiates a {@code Scanner} object that reads the user's input when attempting
     * to instantiate a {@code Task}. Then, checks if the details are empty, or if there
     * already exists a {@code Task} of the same name.
     *
     * @param taskType Type of the {@code Task} being instantiated.
     * @return Details of the {@code Task} for valid inputs; {@code null} otherwise.
     */
    public String checkTaskInput(String taskType) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Input %s details.%n", taskType);
        String message = sc.nextLine();
        if (message.isBlank()) {
            System.out.printf("The %s details cannot be empty.%n", taskType);
            ui.printEndOfOperation();
            return null;
        } else if (tasks.checkDuplicates(message)) {
            System.out.printf("Task %s already exists.%n", message);
            ui.printEndOfOperation();
            return null;
        } else {
            return message;
        }
    }

    /**
     * Instantiates a {@code Scanner} object that reads the user's input when inputting a
     * date. Then, checks if the details are empty, and if the format of the date is valid.
     *
     * @param taskType Type of the {@code Task} being instantiated.
     * @param input Description of the date input, to be printed in the UI.
     * @return {@code LocalDate} object corresponding to the user input if valid;
     *     null otherwise.
     */
    public LocalDate checkDateInput(String taskType, String input) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Input %s %s date. (Required format: YYYY-MM-DD)%n",
                taskType, input);
        String message = sc.nextLine();
        try {
            if (message.isBlank()) {
                System.out.printf("The %s %s cannot be empty.%n", taskType, input);
                ui.printEndOfOperation();
                return null;
            } else {
                return LocalDate.parse(message);
            }
        } catch (DateTimeParseException e) {
            System.out.printf("Invalid date format. Please try again.%n");
            ui.printEndOfOperation();
            return null;
        }
    }

    /**
     * Instantiates a {@code Scanner} object that reads the user's input when inputting a
     * time. Then, checks if the details are empty, and if the format of the time is valid.
     * If the task is a {@code Deadline}, and there is no input from the user, {@code checkTimeInput}
     * will return {@code LocalTime.of(23, 59)}.
     *
     * @param taskType Type of the {@code Task} being instantiated.
     * @param input Description of the time input, to be printed in the UI.
     * @return {@code LocalTime} object corresponding to the user input if valid;
     *     null otherwise.
     */
    public LocalTime checkTimeInput(String taskType, String input) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Input %s %s time. (Required format: HH:MM)%n",
                taskType, input);
        String message = sc.nextLine();
        try {
            if (message.isBlank()) {
                if (taskType.equals("deadline") && input.equals("due")) {
                    return LocalTime.of(23, 59);
                } else {
                    System.out.printf("The %s %s cannot be empty.%n", taskType, input);
                    ui.printEndOfOperation();
                    return null;
                }
            } else {
                return LocalTime.parse(message);
            }
        } catch (DateTimeParseException e) {
            System.out.printf("Invalid time format. Please try again.%n");
            ui.printEndOfOperation();
            return null;
        }
    }

    /**
     * Checks if the input {@code LocalDateTime} is at or after the current system time.
     *
     * @param taskType Type of the {@code Task} being instantiated.
     * @param dateTime {@code LocalDateTime} of the {@code Task}.
     * @return {@code true} if the {@code LocalDateTime} is at or after the current system time;
     *     {@code false} otherwise.
     */
    public boolean checkStartDateTime(String taskType, LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            System.out.printf("Cannot create %s before the current time. "
                    + "Please try again.%n", taskType);
            ui.printEndOfOperation();
            return false;
        }
        return true;
    }

    /**
     * Checks if the end time of a {@code Task} is at or after the start time.
     *
     * @param taskType Type of the {@code Task} being instantiated. Currently, only {@code Event}s
     *                 require this method.
     * @param start Start time of the {@code Task}.
     * @param end End time of the {@code Task}.
     * @return {@code true} if the end time is at or after the start time; {@code false} otherwise.
     */
    public boolean checkTimeInterval(String taskType, LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            System.out.printf("Invalid %s time interval. Please try again.%n", taskType);
            ui.printEndOfOperation();
            return false;
        }
        return true;
    }

    /**
     * Gets all tasks in the {@code TaskList} containing a keyword input by the user.
     *
     * @return {@code ArrayList} of tasks matching the keyword; {@code null} if the user input is blank.
     */
    public ArrayList<Task> getMatchingTasks() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please input search keyword.%n");
        String keyword = sc.nextLine();
        if (keyword.isBlank()) {
            return null;
        } else {
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (int i = 0; i < tasks.getNumOfTasks(); i++) {
                Task t = tasks.get(i);
                String details = t.getDetails();
                if (details.contains(keyword)) {
                    matchingTasks.add(t);
                }
            }
            return matchingTasks;
        }
    }

    /**
     * Instantiates a new {@code Scanner} object that reads the user's input when attempting
     * to mark, unmark, or delete a {@code Task}. Only positive integer inputs less than or
     * equal to the total number of tasks are accepted.
     *
     * @param message Description of the command, to be printed in the UI.
     * @return {@code taskNumber} that corresponds to the index of the {@code Task} in the
     *     {@code TaskList} if input is valid; {@code null} otherwise.
     */
    public Integer launchConfirmationScreen(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please input the task number you wish to %s.%n",
                message);
        ui.printHorizontalLine();
        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            System.out.printf("%d. " + tasks.get(i).toString()
                    + "%n", i + 1);
        }
        try {
            int taskNumber = sc.nextInt();
            if (taskNumber > tasks.getNumOfTasks() || taskNumber < 1) {
                System.out.println("Request unsuccessful. (reason: invalid task number)");
                return null;
            } else {
                return taskNumber;
            }
        } catch (InputMismatchException e) {
            System.out.println("Request unsuccessful. (reason: invalid input)");
            return null;
        }
    }

}

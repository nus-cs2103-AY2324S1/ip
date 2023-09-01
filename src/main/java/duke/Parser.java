package duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Parser {

    private final Ui ui;
    private final Duke duke;
    private final TaskList tasks;

    public enum Command {
        TASK, TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, COMMANDS, BYE
    }
    Parser(Duke duke, duke.TaskList tasks, duke.Ui ui) { // Can only be instantiated with a Duke object
        this.duke = duke;
        this.tasks = tasks;
        this.ui = ui;
    }

    public void readInput(String message) {
        try {
            executeCommand(Command.valueOf(message.toUpperCase()));
        }
        catch (IllegalArgumentException e) {
            System.out.printf("I'm just a robot!%n" +
                    "I don't understand what %s is!%n", message);
            ui.incrementInvalidInputs();
            ui.printHorizontalLine();
        }
    }

    public void executeCommand(Command command) {
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
                duke.isRunning = false;
                break;
            default:
                // Shouldn't reach here
                // Input errors should already be caught in the readInput() method.
                break;
        }
    }

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
        }
        catch (InputMismatchException e) {
            System.out.println("Request unsuccessful. (reason: invalid input)");
            return null;
        }
    }

}

import java.util.Scanner;

/**
 * Handles user interaction
 */
public class Ui {

    /** Variable to detect user input */
    Scanner sc = new Scanner(System.in);

    /** Variable to show horizontal lines */
    public static final String LINE_BREAK = ("--------------------------------------------------"
            + "---------------------------------");

    public void greetings() {
        System.out.println("\n");
        System.out.println(LINE_BREAK);
        System.out.println("Welcome. My name is Duke");
        System.out.println("What will you do today?");
    }

    public String inputSession() {
        System.out.println(LINE_BREAK);
        System.out.println("\n");
        String input = sc.nextLine().trim();
        System.out.println(LINE_BREAK);
        return input;
    }

    public Boolean handleInput(TaskList tasks, Input input) throws DukeException {
        Boolean endSession = true;
        String command = input.getCommand();
        String fullInput = input.getFullInput();
        int inputLength = input.getLength();
        if (command.equals("list")) {
            if (inputLength > 1) {
                throw new InvalidCommandSyntaxException("'list' command must not be followed by anything");
            }
            if (tasks.getSize() == 0) {
                System.out.println("List is empty");
            } else {
                for (int i = 1; i < tasks.getSize() + 1; i++) {
                    Task current = tasks.getTask(i - 1);
                    System.out.println(i + ". " + current.convertToString());
                }
            }
        } else if (command.equals("mark")
                || command.equals("unmark")
                || command.equals("delete")) {
            if (inputLength == 1) {
                throw new MissingTaskNumberException("Task number cannot be empty");
            } else if (tasks.getSize() < 1) {
                throw new EmptyListException("List is currently empty");
            }
            switch (command) {
                case "mark":
                    tasks.manipulateTasks(fullInput, "mark", 5);
                    break;
                case "unmark":
                    tasks.manipulateTasks(fullInput, "unmark", 7);
                    break;
                case "delete":
                    tasks.manipulateTasks(fullInput, "delete", 7);
                    break;
            }
        } else if (command.equals("todo")) {
            if (inputLength <= 1) {
                throw new MissingTaskDescriptionException("Todo task description cannot be empty");
            }
            
            String taskName = fullInput.substring(5);
            tasks.addToDo(taskName);
        } else if (command.equals("deadline")) {
            if (inputLength <= 1) {
                throw new MissingTaskDescriptionException("Deadline task description cannot be empty");
            }
            String[] taskDesc = fullInput.substring(9).split("/by");
            if (taskDesc.length < 2) {
                throw new InvalidTaskTimeException("Deadline task must have exactly one /by deadline");
            }
            String taskName = taskDesc[0].trim();
            if (taskName.length() == 0) {
                throw new MissingTaskNameException("Deadline task name cannot be empty");
            }
            String strDeadline = taskDesc[1].trim();
            String deadline = tasks.formatDate(strDeadline);
            tasks.addDeadline(taskName, deadline);
        } else if (command.equals("event")) {
            if (inputLength <= 1) {
                throw new MissingTaskDescriptionException("Event task description cannot be empty");
            }
            String[] taskDesc = fullInput.substring(6).split("/from");
            if (taskDesc.length < 2 || taskDesc.length > 2) {
                throw new InvalidTaskTimeException(
                        "Event task must have exactly one /from and one /to times, in that order");
            }
            String taskName = taskDesc[0].trim();
            String[] fromAndTo = taskDesc[1].split("/to");
            if (fromAndTo.length < 2 || fromAndTo.length > 2) {
                throw new InvalidTaskTimeException(
                        "Event task must have exactly one /from and one /to times, in that order");
            }
            if (taskName.length() == 0) {
                throw new MissingTaskNameException("Event task name cannot be empty");
            }
            String strStart = fromAndTo[0].trim();
            String strEnd = fromAndTo[1].trim();
            String start = tasks.formatDate(strStart);
            String end = tasks.formatDate(strEnd);
            tasks.addEvent(taskName, start, end);
        } else if (command.equals("bye")) {
            if (inputLength > 1) {
                throw new InvalidCommandSyntaxException("'bye' command must not be followed by anything");
            }
            System.out.println("I hope you enjoy my service. Thank you and goodbye");
            System.out.println(LINE_BREAK);
            endSession = false;
        } else {
            throw new InvalidCommandException("No such command exists, please try again");
        }

        return endSession;
    }
    
}

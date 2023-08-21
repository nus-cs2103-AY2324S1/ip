import java.util.Scanner;

public class Duke {
    private static final int LINE_LENGTH = 100;
    private boolean exit = false;
    private final Task[] tasks;
    private int taskCount;

    public Duke() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public static void main(String[] args) {
        Duke program = new Duke();
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        program.printMessage(
            "Hello! I'm Skye, your personal task assistant.\n" +
            "What can I do for you?"
        );

        // Program only exits when user enters "bye" command
        do {
            try {
                // System.out.print("> ");
                String userInput = scanner.nextLine();
                System.out.println();
                String[] tokens = userInput.split(" ", 2);
                String command = !userInput.isEmpty() ? tokens[0] : "";

                // Switch statement to check for command keywords in the first word
                switch (command) {
                    case "bye":
                        scanner.close();
                        program.exit();
                        break;

                    case "list":
                        program.listTasks();
                        break;

                    case "mark":
                        int completedTaskNumber = tokens.length > 1 ? Integer.parseInt(tokens[1]) : 0;
                        program.markTask(completedTaskNumber);
                        break;

                    case "unmark":
                        int incompleteTaskNumber = tokens.length > 1 ? Integer.parseInt(tokens[1]) : 0;
                        program.unmarkTask(incompleteTaskNumber);
                        break;

                    case "deadline":
                        program.addDeadline(tokens);
                        break;

                    case "event":
                        program.addEvent(tokens);
                        break;

                    case "todo":
                        program.addToDo(tokens);
                        break;

                    default:
                        throw new DukeException("I'm sorry, I don't know what that means :-(");
                }
            } catch (DukeException exception) {
                program.printMessage(exception.getMessage());
            } catch (NumberFormatException exception) {
                program.printMessage("Error: Task number must be an integer.\n(example: mark 1)");
            }
        } while (!program.exit);
    }

    private void setExit() {
        this.exit = true;
    }

    private void renderLine() {
        System.out.println("_".repeat(LINE_LENGTH));
    }

    private void printMessage(String message) {
        renderLine();
        System.out.println(message);
        renderLine();
    }

    private void addTask(Task task) {
        this.tasks[taskCount] = task;
        taskCount++;
        printMessage(
            String.format(
                "Got it. I've added this task:\n %s\nNow you have %d task(s) in the list.",
                task.toString(),
                taskCount
            )
        );
    }

    private void listTasks() {
        renderLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("%d.%s%n", i + 1, tasks[i].toString());
        }
        renderLine();
    }

    private void exit() {
        printMessage("Bye. Hope to see you again soon!");
        setExit();
        System.exit(0);
    }

    private boolean isInvalidTaskNumber(int taskNumber) {
        return (taskNumber <= 0) || (taskNumber > taskCount);
    }

    private void markTask(int taskNumber) throws DukeException {
        if (isInvalidTaskNumber(taskNumber)) {
            throw new DukeException("The task number you've entered is out of the valid range.");
        } else if (tasks[taskNumber - 1].isDone()) {
            throw new DukeException("The task number you've entered is already marked as complete.");
        } else {
            tasks[taskNumber - 1].markAsDone();
            printMessage(
                String.format("Nice! I've marked this task as done:\n %s", tasks[taskNumber - 1].toString())
            );
        }
    }

    private void unmarkTask(int taskNumber) throws DukeException {
        if (isInvalidTaskNumber(taskNumber)) {
            throw new DukeException("The task number you've entered is out of the valid range.");
        } else if (!tasks[taskNumber - 1].isDone()) {
            throw new DukeException("The task number you've entered is already unmarked.");
        } else {
            tasks[taskNumber - 1].markAsNotDone();
            printMessage(
                String.format("OK, I've marked this task as not done yet:\n %s", tasks[taskNumber - 1].toString())
            );
        }
    }

    private String[] getEventParams(String[] tokens) throws DukeException {
        String commands = tokens[1];

        // Ensure that "/from" comes before "/to"
        if (
            !commands.contains("/from") || !commands.contains("/to")
            || commands.indexOf("/from") > commands.indexOf("/to")
        ) {
            throw new DukeException(
                "Invalid event format!\n" +
                "Correct usage: event <description> /from <start> /to <end>"
            );
        }

        // Split by /from or /to
        return commands.split("\\s+/\\w+");
    }

    private void addDeadline(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException(
                "The description of a deadline cannot be empty.\n" +
                "Correct usage: deadline <description> /by <date>"
            );
        }

        // Ensure that "/by" exists in the command
        if (!tokens[1].contains("/by")) {
            throw new DukeException(
                "Invalid deadline format!\n" +
                "Correct usage: deadline <description> /by <date>"
            );
        }

        // Split by "by"
        String[] deadlineParams = tokens[1].split("/by");

        // Deadline attributes
        String deadlineDescription = deadlineParams[0].trim();
        String by = deadlineParams[1].trim();

        // Add deadline to tasks
        addTask(new Deadline(deadlineDescription, by));
    }

    private void addEvent(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException(
                "The description of an event cannot be empty.\n" +
                "Correct usage: event <description> /from <start> /to <end>"
            );
        }

        String[] eventParams = getEventParams(tokens);

        // Event attributes
        String eventDescription = eventParams[0].trim();
        String from = eventParams[1].trim();
        String to = eventParams[2].trim();

        // Add event to tasks
        addTask(new Event(eventDescription, from, to));
    }

    private void addToDo(String[] tokens) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException(
                "The description of a todo cannot be empty.\n" +
                "Correct usage: todo <description>"
            );
        } else {
            addTask(new ToDo(tokens[1]));
        }
    }
}
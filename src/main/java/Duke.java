import java.util.Scanner;

public class Duke {
    private static final int LINE_LENGTH = 100;
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
        program.printMessage("Hello! I'm Skye, your personal task assistant.\nWhat can I do for you?");

        // Program only exits when user enters "bye" command
        while (true) {
            // System.out.print("> ");
            String userInput = scanner.nextLine();
            System.out.println();
            String[] tokens = userInput.split(" ", 2);
            String command = !userInput.isEmpty() ? tokens[0] : "";

            // Switch statement to check for command keywords in the first word
            switch (command) {
                case "bye":
                    program.printMessage("Bye. Hope to see you again soon!");
                    scanner.close();
                    System.exit(0);
                    break;

                case "list":
                    program.listTasks();
                    break;

                case "mark":
                    int completedTaskNumber = tokens.length > 1 ? Integer.parseInt(tokens[1]) : 0;
                    if (program.isInvalidTaskNumber(completedTaskNumber)) {
                        program.printMessage("The task number you've entered is out of the valid range.");
                    } else if (program.tasks[completedTaskNumber - 1].isDone()) {
                        program.printMessage("The task number you've entered is already marked as complete.");
                    } else {
                        program.tasks[completedTaskNumber - 1].markAsDone();
                        program.printMessage(
                                String.format(
                                        "Nice! I've marked this task as done:\n %s",
                                        program.tasks[completedTaskNumber - 1].toString()
                                )
                        );
                    }
                    break;

                case "unmark":
                    int incompleteTaskNumber = tokens.length > 1 ? Integer.parseInt(tokens[1]) : 0;
                    if (program.isInvalidTaskNumber(incompleteTaskNumber)) {
                        program.printMessage("The task number you've entered is out of the valid range.");
                    } else if (!program.tasks[incompleteTaskNumber - 1].isDone()) {
                        program.printMessage("The task number you've entered is already unmarked.");
                    } else {
                        program.tasks[incompleteTaskNumber - 1].markAsNotDone();
                        program.printMessage(
                                String.format(
                                        "OK, I've marked this task as not done yet:\n %s",
                                        program.tasks[incompleteTaskNumber - 1].toString()
                                )
                        );
                    }
                    break;

                case "deadline":
                    // Ensure that "/by" exists in the command
                    if (!tokens[1].contains("/by")) {
                        program.printMessage(
                            "Invalid deadline format! It should be: deadline <description> /by <date>"
                        );
                        break;
                    }

                    // Split by "by"
                    String[] deadlineParams = tokens[1].split("/by");

                    // Deadline attributes
                    String deadlineDescription = deadlineParams[0].trim();
                    String by = deadlineParams[1].trim();

                    // Add deadline to tasks
                    program.addTask(new Deadline(deadlineDescription, by));
                    break;

                case "event":
                    // Ensure that "/from" comes before "/to"
                    if (
                        !tokens[1].contains("/from") || !tokens[1].contains("/to")
                        || tokens[1].indexOf("/from") > tokens[1].indexOf("/to")
                    ) {
                        program.printMessage(
                            "Invalid event format! It should be: event <description> /from <start> /to <end>"
                        );
                        break;
                    }

                    // Split by /from or /to
                    String[] eventParams = tokens[1].split("\\s+/\\w+");

                    // Event attributes
                    String eventDescription = eventParams[0].trim();
                    String from = eventParams[1].trim();
                    String to = eventParams[2].trim();

                    // Add event to tasks
                    program.addTask(new Event(eventDescription, from, to));
                    break;

                case "todo":
                    program.addTask(new ToDo(tokens[1]));
                    break;
            }
        }
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

    private boolean isInvalidTaskNumber(int taskNumber) {
        return (taskNumber <= 0) || (taskNumber > taskCount);
    }
}
import java.util.Scanner;

public class Duke {
    private static final int LINE_LENGTH = 100;
    private Task[] tasks;
    private int taskCount;

    public Duke() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public static void main(String[] args) {
        Duke program = new Duke();
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        program.printMessage("Hello! I'm Skye, your personal task assistant. \nWhat can I do for you?");

        // Program only exits when user enters "bye" command
        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine();
            String[] words = userInput.split(" ");
            String firstWord = !userInput.isEmpty() ? words[0] : "";

            // Switch statement to check for command keywords in the first word
            switch (firstWord) {
                case "bye":
                    program.printMessage("Bye. Hope to see you again soon!");
                    scanner.close();
                    System.exit(0);
                    break;

                case "list":
                    program.listTasks();
                    break;

                case "mark":
                    int completedTaskNumber = words.length > 1 ? Integer.parseInt(words[1]) : 0;
                    if (!program.isValidTaskNumber(completedTaskNumber)) {
                        program.printMessage("The task number you've entered is out of the valid range.");
                    } else if (program.tasks[completedTaskNumber - 1].isDone()) {
                        program.printMessage("The task number you've entered is already marked as complete.");
                    } else {
                        program.tasks[completedTaskNumber - 1].markAsDone();
                        program.printMessage(
                                String.format(
                                        "Nice! I've marked this task as done:\n%s",
                                        program.tasks[completedTaskNumber - 1].toString()
                                )
                        );
                    }
                    break;

                case "unmark":
                    int incompleteTaskNumber = words.length > 1 ? Integer.parseInt(words[1]) : 0;
                    if (!program.isValidTaskNumber(incompleteTaskNumber)) {
                        program.printMessage("The task number you've entered is out of the valid range.");
                    } else if (!program.tasks[incompleteTaskNumber - 1].isDone()) {
                        program.printMessage("The task number you've entered is already unmarked.");
                    } else {
                        program.tasks[incompleteTaskNumber - 1].markAsNotDone();
                        program.printMessage(
                                String.format(
                                        "OK, I've marked this task as not done yet:\n%s",
                                        program.tasks[incompleteTaskNumber - 1].toString()
                                )
                        );
                    }
                    break;

                default:
                    program.addTask(userInput);
                    program.printMessage("added: " + userInput);
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

    private void addTask(String taskDescription) {
        this.tasks[taskCount] = new Task(taskDescription);
        taskCount++;
    }

    private void listTasks() {
        renderLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("%d.[%s] %s%n", i + 1, tasks[i].getStatusIcon(), tasks[i].getDescription());
        }
        renderLine();
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber > 0) && (taskNumber <= taskCount);
    }
}
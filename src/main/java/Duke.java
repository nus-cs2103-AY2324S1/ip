import java.util.Scanner;

public class Duke {
    private static final int LINE_LENGTH = 100;
    private String[] tasks;
    private int taskCount;

    public Duke() {
        this.tasks = new String[100];
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

            // Switch statement to check for command keywords
            switch (userInput) {
                case "bye":
                    program.printMessage("Bye. Hope to see you again soon!");
                    scanner.close();
                    System.exit(0);
                    break;

                case "list":
                    program.listTasks();
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

    private void addTask(String task) {
        this.tasks[taskCount] = task;
        taskCount++;
    }

    private void listTasks() {
        renderLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.printf("%d. %s%n", i + 1, tasks[i]);
        }
        renderLine();
    }
}
import java.util.Scanner;
public class Duke {
    static Scanner scanner = new Scanner(System.in);
    private String name;
    private Storage storage;

    public Duke(String name) {
        this.name = name;
        this.storage = new Storage();
    }

    public void echo(String line) {
        formatPrintMessage(line);
    }

    public void greet() {
        System.out.println();
        formatPrintMessage("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }
    public void exit() {
        System.out.println();
        formatPrintMessage("Bye. Hope to see you again soon!");
    }

    public void formatPrintMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public void handleInput(String input) {
        if (input.isEmpty()) {
            formatPrintMessage("Task cannot be empty");
            return;
        }

        String[] inputArr = input.split(" ");
        String command = inputArr[0];

        switch (command) {
            case "list":
                this.storage.showAllTasks();
                break;
            case "mark":
                int taskNumber = Integer.parseInt(inputArr[1]);
                this.storage.markTaskAsDone(taskNumber);
                break;
            case "unmark":
                int taskNumber2 = Integer.parseInt(inputArr[1]);
                this.storage.unmarkTaskAsDone(taskNumber2);
                break;
            case "todo":
                String description = input.replace("todo", "");
                this.storage.addTask(description);
                break;
            case "deadline":
                String description2 = input.replace("deadline", "");
                this.storage.addTask(description2);
                break;
            case "event":
                String description3 = input.replace("event", "");
                this.storage.addTask(description3);
                break;
            default:
                formatPrintMessage("Invalid command");
                break;
        }
    }

    public void run() {
        greet();
        String line = scanner.nextLine();
        while (!line.equals("bye")) {
            handleInput(line);
            line = scanner.nextLine();
        }
        exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("Duke");
        duke.run();
    }

}
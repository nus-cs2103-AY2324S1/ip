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
        switch (input) {
            case "list":
                this.storage.showAllTasks();
                break;
            default:
                this.storage.addTask(input);
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
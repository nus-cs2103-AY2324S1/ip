import java.util.Scanner;
public class Duke {
    static Scanner scanner = new Scanner(System.in);
    private String name;

    public Duke(String name) {
        this.name = name;
    }

    public void echo(String line) {
        formatPrintMessage(line);
    }

    public void greet() {
        formatPrintMessage("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }
    public void exit() {
        formatPrintMessage("Bye. Hope to see you again soon!");
    }

    public void formatPrintMessage(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public void run() {
        greet();
        String line = scanner.nextLine();
        while (!line.equals("bye")) {
            echo(line);
            line = scanner.nextLine();
        }
        exit();
    }


    public static void main(String[] args) {
        Duke duke = new Duke("Duke");
        duke.run();
    }

}
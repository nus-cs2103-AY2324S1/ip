public class Duke {
    // Constructor for Duke
    public Duke() {

    }

    // Constants
    private static final String name = "Beep Boop";
    private static final String line = "─".repeat(100);

    public void printMessage(String message) {
        System.out.printf("\t%s\n", message);
        System.out.println(line);
    }

    public void greet() {
        String greeting = String.format("Hello! I'm %s\n\tWhat can I do for you?\n", name);
        System.out.println(line);
        printMessage(greeting);
    }

    public void exit() {
        String exiting = "Bye. Hope to see you again soon!\n";
        printMessage(exiting);
    }

    public void runDuke() {
        greet();
        exit();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}

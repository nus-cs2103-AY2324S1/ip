public class Duke {
    private String name;

    public Duke(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println();
        formatPrintMessage("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }
    public void exit() {
        formatPrintMessage("Bye. Hope to see you again soon!");
    }

    public void formatPrintMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public void run() {
        greet();
        exit();
    }


    public static void main(String[] args) {
        Duke duke = new Duke("Duke");
        duke.run();
    }

}


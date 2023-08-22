public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String NAME = "Duke";

    public static void main(String[] args) {
        hello();
        bye();
    }

    public static void hello() {
        printLine();
        System.out.println(LOGO);
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void bye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}

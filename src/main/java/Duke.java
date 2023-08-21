public class Duke {
    private static final String NAME = "Duke Prince";
    private static final String SPACER = "----------------------------------------------------";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(SPACER);
        greet();
        System.out.println(SPACER);
        bye();
        System.out.println(SPACER);
    }

    private static void greet() {
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}

public class Duke {
    private static final String MyName = "Rio";
    public static void Greet() {
        System.out.println("Hello! I'm " + MyName);
        System.out.println("What can I do for you?");
    }

    public  static void Exit() {
        System.out.println(" Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}

public class Duke {
    public static String greetUser() {
        String greeting = "――――――――――――――――――――――――――――――――――――――――――\n"
                + "Hello! I'm CringeBot\n"
                + "What can I do for you?\n"
                + "――――――――――――――――――――――――――――――――――――――――――\n"
                + "Bye. Hope to see you again soon!";
        return greeting;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greetUser());
    }
}

public class Duke {
    private static String Greet() {
        return "_______________________________________________\n"
                + "Hi there! I'm Bob.\nHow can I help?\n"
                + "_______________________________________________";
    }

    private static String Exit() {
        return "_______________________________________________\n"
                + "See you soon!\n"
                + "_______________________________________________";
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Greet());
        System.out.println(Exit());
    }
}

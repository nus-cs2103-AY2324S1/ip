public class Duke {
    private static final String LINEBREAK = "____________________________________________________________________";
    public static void main(String[] args) {
        String logo = " _           _        \n"
                    + "| |    _   _| | _____ \n"
                    + "| |   | | | | |/ / _ \\\n"
                    + "| |__ | |_| |   <  __/\n"
                    + "|____| \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greetingMsg = "Hello! I'm Luke \n"
                        + "What can I do for you?";
        System.out.println(LINEBREAK);
        System.out.println(greetingMsg);

        String byeMsg = "Bye. Hope to see you again soon!";
        System.out.println(LINEBREAK);
        System.out.println(byeMsg);
        System.out.println(LINEBREAK);




    }
}

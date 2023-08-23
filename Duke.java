public class Duke {
    private String name = "Lakinta";

    public String greeting() {
        return "Hello! I'm " + name +
                "\nWhat can I do for you?";
    }

    public String exit() {
        return "Bye. Hope to see you again soon!";
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Duke myBot = new Duke();
        System.out.println(myBot.greeting());
        System.out.println(myBot.exit());
    }
}

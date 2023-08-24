public class Duke {
    public static final String greeting = "Hello! I'm Davidson \nWhat can I do for you?\n";

    public static final String exit = "Bye. Hope to see you again soon!\n";

    public static void printHorizontalLine() {
        for (int i = 0; i < 64; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Duke.printHorizontalLine();
        System.out.println(greeting);
        Duke.printHorizontalLine();
        System.out.println(exit);
        Duke.printHorizontalLine();
    }
}

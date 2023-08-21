public class Duke {
    private static final String NAME = "404";

    public static void main(String[] args) {
        printLine();
        System.out.println(" Hello! I'm " + NAME);
        System.out.println(" What can I do for you?");
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    private static void printLine() {
        String line =  "____________________________________________________________";
        System.out.println(line);
    }
}

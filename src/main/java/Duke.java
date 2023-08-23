public class Duke {
    public static void main(String[] args) {
        String name = "Beary";
        System.out.println(String.format("Hello! I'm %s\nWhat can I do for you?", name));
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}


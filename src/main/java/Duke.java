public class Duke {
    private static final String NAME = "Jimmy";

    private static void greet() {
        System.out.printf("Hello! I'm %s\nWhat can I do for you?%n", NAME);
    }

    private static void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();

        farewell();
    }
}

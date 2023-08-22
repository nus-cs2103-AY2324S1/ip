public class Duke {
    private static final String NAME = "Jimmy";

    private static void print(String... strings) {
        for (String s : strings) {
            System.out.print('\t');
            System.out.println(s);
        }
    }

    private static void greet() {
        print(String.format("Hello! I'm %s", NAME), "What can I do for you?");
    }

    private static void farewell() {
        print("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();

        farewell();
    }
}

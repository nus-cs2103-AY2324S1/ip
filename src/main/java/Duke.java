public class Duke {
    private static final String botName = "cc";

    private static void greet() {
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke.greet();
        Duke.bye();
    }
}

public class Duke {
    public static final String NAME = "Buzu";
    public static final String DIVIDER = "__________________________________________________";
    public static void say(String text) {
        System.out.println(DIVIDER);
        System.out.println(text);
        System.out.println(DIVIDER);
        System.out.println();
    }
    public static void main(String[] args) {
        say("Hello! I'm " + NAME + ".\nWhat can I do for you?");
        say("Bye! Hope to see you again soon!");
    }
}

public class Duke {
    private static final int LINE_LENGTH = 100;
    public static void main(String[] args) {
        renderLine();
        System.out.println("Hello! I'm Skye.");
        System.out.println("What can I do for you?");
        renderLine();
        System.out.println("Bye. Hope to see you again soon!");
        renderLine();
    }

    public static void renderLine() {
        System.out.println("_".repeat(LINE_LENGTH));
    }
}
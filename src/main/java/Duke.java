public class Duke {
    private static String name = "Alfred";

    public static void println() {
        System.out.println("____________________________________________________________");
    }

    public static String  getName() {
        return Duke.name;
    }

    public static void main(String[] args) {
        println();
        System.out.println("Hello I'm " + getName());
        System.out.println("What can I do for you?");
        println();
        System.out.println("Bye. Hope to see you again soon!");
        println();
    }
}

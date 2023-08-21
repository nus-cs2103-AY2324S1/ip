public class Duke {
    public static void main(String[] args) {
        String name = "Dude";
        greet(name);
        bye();
    }
    public static void greet(String name) {
        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?\n");
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}

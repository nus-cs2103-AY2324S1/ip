public class Duck {
    public static void main(String[] args) {
        Duck.greet();
        Duck.bye();
    }

    private static void line() {
        System.out.println("____________________________________________________________");
    }

    private static void greet() {
        String greeting = "Hello! I'm Duck\n" + "What can I do for you?";

        line();
        System.out.println(greeting);
        line();
    }

    private static void bye() {
        String bye = "Bye. Hope to see you again soon!";

        line();
        System.out.println(bye);
        line();
    }

}

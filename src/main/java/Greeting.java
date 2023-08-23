public class Greeting {
    static final String linebreak = "____________________________________________________________";
    public static void greet() {
        System.out.println(linebreak);
        String greeting = "Hello! I'm chatbot\nWhat can I do for you?";

        System.out.println(greeting);
        System.out.println(linebreak);
    }

    public static void bye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(Greeting.linebreak);
        System.out.println(bye);
        System.out.println(Greeting.linebreak);
    }
}

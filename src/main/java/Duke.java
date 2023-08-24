public class Duke {
    final private static String name = "Iris";

    public static void main(String[] args) {
        PrintGreetMessage();
        PrintExitMessage();
    }

    private static void PrintGreetMessage()
    {
        System.out.println("Hello, I'm " + name + ".");
        System.out.println("What can I do for you?");
    }

    private static void PrintExitMessage()
    {
        System.out.println("Bye. Hope to see you again soon!");
    }
}

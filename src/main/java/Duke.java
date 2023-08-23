public class Duke {
    public static String byeGreetings = "Bye. Hope to see you again soon!";
    public static String name = " ____    __        __       \n"
            + "|  _ \\   | |  ____ | | __\n"
            + "| | | |  | | |     | |/ /\n"
            + "| |_| |  | | |     |   < \n"
            + "|____/   ___  ____ |_|\\_\\\n";
    public static void main(String[] args) {
        System.out.println(greet());

        System.out.println(Duke.byeGreetings);
    }

    public static String greet() {
        return "Hello from \n " + Duke.name + "What can I do for you? \n";
    }
}

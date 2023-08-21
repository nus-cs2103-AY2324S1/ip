public class Duke {

    private static String name = "SoCrates";
    private static String line =
        "\t____________________________________________________________";


    private static void printMessage(String message) {
        System.out.println(line);
        System.out.println("\t" + message);
        System.out.println("\n" + line);
    }

    private static void printMessage(String[] message) {
        System.out.println(line);
        for (String messageLine : message) {
            System.out.println("\t" + messageLine);
        }
        System.out.println("\n" + line);
    }

    public static void printWelcome() {
        String[] message = {
            "Hello! I'm " + name,
            "What can I do for you?"
        };

        printMessage(message);
    }

    public static void printExit() {
        printMessage("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        printWelcome();

        printExit();
    }
}

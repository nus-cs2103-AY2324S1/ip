import java.util.Scanner;

public class Duke {
    private static String LINE_SEPARATOR = "    ------------------------------------------------------------";

    private static void respond(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("     " + message);
        System.out.println(LINE_SEPARATOR);
        System.out.print(">> ");
    }

    private static void respond(String[] messages) {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < messages.length; i++) {
                System.out.println("     " + messages[i]);
        }
        System.out.println(LINE_SEPARATOR);
        System.out.print(">> ");
    }

    
    private static void greet() {
        String[] messages = new String[] {"Hello! I'm A-CAT (Automated Chatbot Assistant for Tasks)", "What do you want to do today?"};
        Duke.respond(messages);
    }

    private static void exit() {
        Duke.respond("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);

        Boolean programRunning = true;

        while (programRunning) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                programRunning = false;
            } else {
                Duke.respond(input);
            }
        }

        scanner.close();
        Duke.exit();
    }
}

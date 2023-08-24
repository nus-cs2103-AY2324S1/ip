import java.util.Scanner;

public class ChatBot {
    static final String name = "4F5DA2";
    static final String line = "\t____________________________________________________________";
    static final Scanner scanner = new Scanner(System.in);

    public static void greet() {
        System.out.println(line);
        System.out.println("\tWelcome back, human!");
        System.out.println("\tI'm your personal ChatBot, " + name + ".");
        System.out.println("\tWhat can I do for you today?");
        System.out.println(line);
    }

    public static void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void output(String output) {
        System.out.println(line);
        System.out.println(output);
        System.out.println(line);
    }

    public static void nextCommand() {
        String command = scanner.nextLine();
        if (command.equals("bye")) {
            exit();
        } else {
            output("\t" + command);
            nextCommand();
        }
    }

    public static void main(String[] args) {
        greet();
        nextCommand();

    }
}

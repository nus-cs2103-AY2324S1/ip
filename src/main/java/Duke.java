import java.util.*;

public class Duke {
    static String greeting = "______________________________________\n"
            + "Hi, I'm Chatty\n"
            + "What do you need to do today?\n"
            + "______________________________________";
    static String goodbye = "______________________________________\n"
            + "Bye. Don't come back!\n"
            + "______________________________________";

    public static String echo(String str) {
        String returnLine = "______________________________________\n"
                + str
                + "\n______________________________________\n";
        return returnLine;
    }
    static void awaitCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        if (command.equals("bye")) {
            System.out.println(goodbye);
        } else {
            System.out.println(echo(command));
            awaitCommand();
        }
    }
    public static void main(String[] args) {
        System.out.println(greeting);
        awaitCommand();
    }
}

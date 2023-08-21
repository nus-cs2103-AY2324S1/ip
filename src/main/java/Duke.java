import java.util.Scanner;

public class Duke {

    public static void echo(String input) {
        System.out.println("    " + input);
        System.out.println("--------------------------------");
    }
    public static boolean continueOrNot(String input) {
        if (input.equals("bye")) {
            return false;
        }
        return true;
    }
    public static String input() {
        Scanner myInput = new Scanner(System.in);
        String reply = myInput.nextLine();
        System.out.println("--------------------------------");
        return reply;
    }
    public static void greeting() {
        System.out.println("Hello.. I'm ekuD..");
        System.out.println("I probably won't be much of a help.. But ask me something..");
        System.out.println("--------------------------------");
        String input = input();
        while (continueOrNot(input)) {
            echo(input);
            input = input();
        }
        System.out.println("    bye...");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
    }
}

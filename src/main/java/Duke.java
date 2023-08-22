import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String welcome = " ──────────────────────────────────────── \n"
                + "  Hello! I'm Handsome \n"
                + "  What can I do for you? \n"
                + " ──────────────────────────────────────── ";
        String goodbye = " ──────────────────────────────────────── \n" +
                "  Bye. Hope to see you again soon! \n" +
                " ──────────────────────────────────────── \n";
        System.out.println(welcome);
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                break;
            }
            String echo = " ──────────────────────────────────────── \n" +
                    "  " + command + " \n" +
                    " ──────────────────────────────────────── ";
            System.out.println(echo);
        }
        System.out.println(goodbye);
    }
}

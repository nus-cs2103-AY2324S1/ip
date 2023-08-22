import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        String welcome = " ──────────────────────────────────────── \n"
                + "  Hello! I'm Handsome \n"
                + "  What can I do for you? \n"
                + " ──────────────────────────────────────── ";
        String goodbye = " ──────────────────────────────────────── \n"
                + "  Bye. Hope to see you again soon! \n"
                + " ──────────────────────────────────────── \n";
        System.out.println(welcome);
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                break;
            }
            if (command.equals("list")) {
                int index = 1;
                System.out.println(" ──────────────────────────────────────── ");
                for (String task : tasks) {
                    System.out.println(String.format("  %d. %s ", index, task));
                    index++;
                }
                System.out.println(" ──────────────────────────────────────── ");
            } else {
                tasks.add(command);
                String echo = " ──────────────────────────────────────── \n"
                        + "  added: " + command + " \n"
                        + " ──────────────────────────────────────── ";
                System.out.println(echo);
            }
        }
        System.out.println(goodbye);
    }
}

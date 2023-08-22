import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "\t____________________________________________________";
        System.out.println(horizontalLine);
        System.out.println("\tHello! I'm Ari.");
        System.out.println("\tWhat can I do for you?");
        System.out.println(horizontalLine);
        echo();
        System.out.println(horizontalLine);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    public static void echo() {
        String horizontalLine = "\t____________________________________________________";
        ArrayList<String> lst = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        String commands = input.nextLine();

        if (commands.equals("bye")) {
            return;
        }

        while (true) {
            if (commands.equals("bye")) {
                return;
            } else if (commands.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + lst.get(i));
                }
                System.out.println(horizontalLine);
            } else {
                lst.add(commands);
                System.out.println(horizontalLine);
                System.out.println("\t added: " + commands);
                System.out.println(horizontalLine);
            }

            commands = input.nextLine();
        }
    }

}

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

        Scanner input = new Scanner(System.in);
        String commands = input.nextLine();

        if (commands.equals("bye")) {
            return;
        }

        boolean inLoop = true;

        while (inLoop) {
            System.out.println(horizontalLine);
            System.out.println("\t" + commands);
            System.out.println(horizontalLine);
            commands = input.nextLine();
            if (commands.equals("bye")) {
                inLoop = false;
            }
        }
    }

}

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Hello! I'm ChatGP0");
        System.out.println("     " + "What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
        String input = scan.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + list.get(i));
                }
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     " + "added: " + input);
                System.out.println("    ____________________________________________________________\n");
                list.add(input);
            }
            input = scan.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + "Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
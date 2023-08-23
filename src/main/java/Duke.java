import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm ChatGP0");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + input);
            System.out.println("    ____________________________________________________________\n");
            input = scan.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
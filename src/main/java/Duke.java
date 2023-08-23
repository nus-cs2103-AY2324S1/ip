import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";

        // Greeting
        System.out.println(line);
        System.out.println("Hello! I'm Eepy\nWhat can I do for you?");
        System.out.println(line);

        // Get input and echo
        Scanner in = new Scanner(System.in);
        while (true) {
            String s = in.nextLine();
            System.out.println("    " + line);

            if (s.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    " + line);
                break;
            }
            System.out.println("    " + s);
            System.out.println("    " + line);
        }
    }
}

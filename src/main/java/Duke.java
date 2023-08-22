import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Derek";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(line);
        }
    }
}

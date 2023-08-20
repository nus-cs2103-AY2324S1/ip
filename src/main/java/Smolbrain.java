import java.util.Scanner;

public class Smolbrain {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Smolbrain\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.next();
            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________\n");
                break;
            }
            System.out.println(input);
            System.out.println("____________________________________________________________\n");
        }

    }
}

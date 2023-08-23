import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");

        while (true) {
            Scanner obj = new Scanner(System.in);
            String input = obj.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else {
                System.out.println(input);
            }
        }
    }

}

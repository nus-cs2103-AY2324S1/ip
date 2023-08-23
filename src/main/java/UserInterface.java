import java.util.Scanner;

public class UserInterface {
    Scanner sc;
    public UserInterface(Scanner sc) {
        this.sc = sc;
    }

    public void start() {
        System.out.println("Hello! I'm Ducky\nWhat can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

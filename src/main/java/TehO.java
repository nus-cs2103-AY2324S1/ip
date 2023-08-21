import java.util.Scanner;

public class TehO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm TehO \nWhat can I do for you?");

        while (true) {
            String userCommand = sc.nextLine();

            if (userCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(userCommand);
            }
        }
    }
}

import java.util.Scanner;

public class Pogo {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Pogo\nWhat can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);
        String quitMessage = "Bye. Hope to see you again soon!";

        while (true) {
            String input = scanner.nextLine();
            System.out.println(horizontalLine);
            if (input.equals("bye")) {
                System.out.println(quitMessage);
                break;
            }
            System.out.println(input);
            System.out.println(horizontalLine);
        }
    }
}

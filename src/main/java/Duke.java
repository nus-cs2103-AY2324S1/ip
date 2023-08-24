import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String farewell = " Bye. Hope to see you again soon!";
        String message = "____________________________________________________________\n"
                + " Hello! I'm [YOUR CHATBOT NAME]\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(message);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            System.out.println("----------------------------------------------");
            System.out.println(input);
            System.out.println("----------------------------------------------");

            if(input.equalsIgnoreCase("bye")) {
                System.out.println("----------------------------------------------");
                System.out.println(farewell);
                System.out.println("----------------------------------------------");
                break;
            }
        }
    }
}

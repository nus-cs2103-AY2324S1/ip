import java.util.Scanner;

public class Duke {
    private static String chatBotName = "Doctor101";

    public static void Greets() {
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
    }

    public static void Bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void Echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Bye();
                scanner.close();
                break;
            }
            System.out.println(input);
        }

    }
    public static void main(String[] args) {
       Greets();

         Echo();
    }

}

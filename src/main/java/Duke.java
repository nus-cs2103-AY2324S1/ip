import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String botName = "Dude";
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            System.out.println("You just said: " + input);
        } while (!input.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}

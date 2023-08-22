import java.util.Scanner;

/**
 * The main class for Echo Chatbot
 *
 * @author Jason Ray
 */
public class Echo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String LINE_BREAK = "----------------------------------------------------------";
        System.out.println(LINE_BREAK);
        System.out.println("Welcome. My name is Echo");
        System.out.println("What can I do for you?");
        System.out.println(LINE_BREAK + "\n");
        System.out.println("Type 'bye' to exit\n");

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(LINE_BREAK);
            System.out.println(input);
            System.out.println(LINE_BREAK + "\n");
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            System.out.println(LINE_BREAK);
            System.out.println("I hope you enjoy my service. Thank you and goodbye");
            System.out.println(LINE_BREAK);
        }
    }
}

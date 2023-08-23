import java.util.Scanner;
public class Duke {
    private static String chatbot = "chuababyy chatbot";
    private static String line = "------------------------";

    public static void hiGreeting() {
        System.out.println(line);
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void byeGreeting() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again!");
        System.out.println(line);
    }
    public static void main(String[] args) {
        hiGreeting();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        byeGreeting();
    }
}

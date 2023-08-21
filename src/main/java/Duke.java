import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        giveChatbotIntro();
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = scanner.nextLine();
            printHorizontalLine();
            processInput(userInput);
            printHorizontalLine();
        } while (!userInput.equals("bye"));
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void giveChatbotIntro() {
        printHorizontalLine();
        System.out.println("Hello! I'm Jeffrey, your personal AI chatbot, ready to serve you today\n");
        System.out.println("What can I do for you?\n");
        printHorizontalLine();
    }

    public static void giveChatbotOutro() {
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("Remember, the universe is vast, but I'm always here for you :D\n");
    }

    public static void processInput(String input) {
        switch (input) {
            case "bye":
                giveChatbotOutro();
                break;
            default:
                System.out.println(input);
                break;
        }
    }
}

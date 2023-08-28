import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("What's your name?");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        Chatbot chatbot = new Chatbot(userInput);
        chatbot.start(scanner);
    }
}
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ChatManager chat = new ChatManager();
        Scanner sc = new Scanner(System.in);
        while (chat.getIsActive()) {
            String userInput = sc.nextLine();
            chat.handleInput(userInput);
        }
    }
}

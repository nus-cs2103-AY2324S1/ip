import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ChatView chatView = new ChatView();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            chatView.commandInput(command);
        }
    }
}

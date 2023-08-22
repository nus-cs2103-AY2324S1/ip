import java.util.Scanner;

public class Duke {

    private static final Chatbot chatbot = Chatbot.getInstance();

    public static void main(String[] args) {

        // Perform some setups.
        chatbot.addEventListener(message -> {
            if (message.getSender() != Chatbot.MessageSender.USER) {
                System.out.println(message.getMessage());
                System.out.println();
            }
        });

        // Start processing the conversation.
        Scanner scanner = new Scanner(System.in);

        chatbot.openConversation();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                break;
            }
            chatbot.sendMessageFromUser(line);
        }

        chatbot.closeConversation();
        scanner.close();
    }
}

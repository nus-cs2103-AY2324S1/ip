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

        do {
            System.out.print("> ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String line = scanner.nextLine();
            System.out.println();

            chatbot.sendMessageFromUser(line);

            if (chatbot.isConversationClosed()) {
                break;
            }
        } while (true);

        chatbot.closeConversation();
        scanner.close();
    }
}

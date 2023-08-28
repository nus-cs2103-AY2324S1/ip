import java.util.Scanner;

/**
 * The chatbot CLI entry-point, which manages CLI interactive interactions.
 */
public class Duke {

    private static final Chatbot chatbot = Chatbot.getSharedInstance();

    public static void main(String[] args) {

        // Performs initial setup to listen to new messages.
        chatbot.addEventListener(message -> {
            if (message.getSenderType() != ChatMessage.SenderType.USER) {
                System.out.println(message.getMessage());
                System.out.println();
            }
        });

        // Start processing the conversation.
        Scanner scanner = new Scanner(System.in);

        chatbot.openConversation();

        while (chatbot.isConversationOpen()) {
            System.out.print("> ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String line = scanner.nextLine();
            System.out.println();

            chatbot.sendMessageFromUser(line);
        }

        chatbot.closeConversation();
        scanner.close();
    }
}
